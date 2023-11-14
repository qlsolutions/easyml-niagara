/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver.messages;

import java.io.OutputStream;

import javax.baja.log.Log;

import com.tridium.basicdriver.message.Message;
import com.tridium.json.JSONObject;

/**
 * EasyMLMessage is the super class for serial request messages.
 * Attributes and methods common to all request messages should reside here.
 * 
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.3 $ $Date: 2014/10/27 18:09:31 $
 */

public abstract class EasyMLMessage
  extends Message implements EasyMLMessageConst
{

////////////////////////////////////////////////////////////
//  Constructor
////////////////////////////////////////////////////////////
  
 /**
  * Empty default constructor
  */
  public EasyMLMessage(String method, String path, JSONObject content)
  {
    this.method = method;
    this.path = path;
    this.content = content;
  }
  
////////////////////////////////////////////////////////////
// WebCtrlMessage
////////////////////////////////////////////////////////////

  /**
   * Get the HTTP method to use in the request.
   */
  public String getMethod()
  {
    return method;
  }

  /**
   * Get the complete URI to use for the request to the server.
   */
  public String getPath()
  {
    return path;
  }
  
  /**
   * Specify the item to parse in the response content. 
   */  
  public JSONObject getContent()
  {
    return content;
  }
  
////////////////////////////////////////////////////////////
//Message
////////////////////////////////////////////////////////////

  /**
   * Write this message to the given output stream.
   */
  public void write(OutputStream out)
  {    
    try
    {
      String request = content.toString(2);

      // some debug
      if (log.isTraceOn())
      {
        log.message(">>>>>>>> JSON Request (" + getPath() + ") >>>>>>>>>\n");
        log.message(request);
        log.message(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
      }

      byte[] input = request.getBytes("utf-8");
      out.write(input, 0, input.length);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  /**
   * Return a debug string for this message.
   */
  public String toDebugString()
  {
    return "*** " + this.getClass().getName() + ": Method(" + getMethod().toUpperCase() + ") Path(" + getPath() + ") ***";
  }
  
///////////////////////////////////////////////////////////
// Attributes
///////////////////////////////////////////////////////////
  
  private String path;
  private String method;
  private JSONObject content;
  public static final Log log = Log.getLog("EasyMLMessage"); 
}