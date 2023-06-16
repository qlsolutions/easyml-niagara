/*
 * Copyright 2023, QuickLink Solutions - All Rights Reserved.
 */
package com.quicklink.easyml.driver.messages;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.util.Base64;
import java.util.logging.Level;

import javax.baja.sys.BRelTime;
import javax.baja.sys.Clock;

import com.quicklink.easyml.driver.BEasyMLServer;
import com.tridium.basicdriver.util.BasicException;

/**
 * EasyMLSendRequest - Insert description here.
 *
 * @author 		Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version		$Revision: 1.7 $
 * @since			Baja 1.0
 *
 */
public class EasyMLSendRequest
  implements Runnable
{
  
  /**
   * Empty constructor.
   */
  public EasyMLSendRequest()
  {
  }

  public EasyMLSendRequest(BEasyMLServer server, EasyMLMessage request, BRelTime timeout, int retryCount)
  {
    this.server = server;
    this.request = request;
    this.timeout = timeout;
    this.retryCount = retryCount;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Runnable#run()
   */
  public void run()
  {
    execute();
  }

  synchronized void execute()
  {
    response = null;
    try
    {
      if (request == null)
      {
        // dispatchRequest received null message to send, nothing to do
        return;
      }

      response = transmit(timeout, retryCount);
    }
    catch (Exception e)
    {
      server.getNetwork().getLogger().log(Level.SEVERE, "DispatchRequest caught exception in execute(): ", e);
    }

    // notify requester that response has been received
    complete = true;
    notify();
  }
  
  public EasyMLResponse transmit(BRelTime responseTimeout, int retryCount)
    throws BasicException
  {
    response = null;
    for (int i=0; i<retryCount + 1; ++i)
    {
      HttpURLConnection connection = null;
      
      try
      {
        URL url;
        if (server.getUseSsl())
          url = new URL("https", server.getIpAddress(), server.getIpPort(), request.getPath());
        else
          url = new URL("http", server.getIpAddress(), server.getIpPort(), request.getPath());
        
        connection = (HttpURLConnection)url.openConnection();
        
        connection.setRequestMethod(request.getMethod());      
        connection.setRequestProperty("Content-Type", "application/json; charset=utf-8;");
        connection.setRequestProperty("Accept", "application/json");
        
        // set the credentials if available
        if (server.getCredentials().getUsername().length() > 0)
        {
          String authorization = server.getCredentials().getUsername() + ':' + AccessController.doPrivileged((java.security.PrivilegedAction<String>)() -> server.getCredentials().getPassword().getValue());
          authorization = Base64.getEncoder().encodeToString(authorization.getBytes());
          connection.setRequestProperty("Authorization", "Basic " + authorization);
        }
  
        if (request.getMethod().equalsIgnoreCase("get"))
          connection.setDoOutput(false);
        else
        {
          connection.setDoOutput(true);
          connection.setDoInput(true); 
          connection.setUseCaches(false);
          connection.setAllowUserInteraction(true); 
          OutputStream out = connection.getOutputStream();
          request.write(out);
        
          out.flush();
          out.close();
        }

        server.incrementSent();
        
        int status = connection.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK)
        {
          Charset charset = Charset.forName("utf8");
          InputStreamReader in = new InputStreamReader(connection.getInputStream(), charset);
          BufferedReader reader = new BufferedReader(in);
          StringBuffer strBuf = new StringBuffer();

          String read = "";
          long endTicks = Clock.ticks() + timeout.getMillis();
          boolean timeout = true;
          while ((read = reader.readLine()) != null && timeout)
          {
            strBuf.append(read);
            timeout = Clock.ticks() <= endTicks;
          }
          
          // check for the timeout and increase the counter
          if (!timeout)
            server.incrementTimeouts();
  
          in.close();

          EasyMLReceivedMessage rcvMsg = new EasyMLReceivedMessage(strBuf.toString());
          response = (EasyMLResponse)request.toResponse(rcvMsg);
        }
        else if (server.getLogger().isLoggable(Level.FINE))
          server.getLogger().fine("Connection error to " + url.toString() + " with code (" + status + ")");
          
      }
      catch (Exception e)
      {
//        e.printStackTrace();
        server.getLogger().severe("EasyMLSendRequest caught exception in execute(): " + e.getMessage());
      }
      finally
      {
        if (connection != null)
        {
          try { connection.disconnect(); }
          catch (Exception e) {}
        }
        
        if (i == retryCount)
          complete = true;
      }
      
      if (response != null && response.getSuccessfulResponse())
      {
        // check for the successful response and increase the counter
        server.incrementReceived();
        complete = true;
        break;
      }
    }
    
    return response;
  }

  public synchronized EasyMLResponse getResponse()
  {
    while(!complete)
    {
      try { wait(200); }
      catch (Exception e) { server.getLogger().log(Level.SEVERE, "EasyMLSendRequest caught exception in getResponse(): ", e); }
    }
    
    return response;
  }
  
  public String toString()
  {
    return "EasyMLSendRequest: " + request;
  }
  
////////////////////////////////////////////////////////////////
//Attributes
////////////////////////////////////////////////////////////////  

  protected BEasyMLServer server;
  protected EasyMLMessage request;  
  protected BRelTime timeout;
  protected int retryCount;
  protected EasyMLResponse response = null;
  protected boolean complete = false;
}