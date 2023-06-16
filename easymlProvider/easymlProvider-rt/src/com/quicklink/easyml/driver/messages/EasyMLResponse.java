/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver.messages;

import javax.baja.log.Log;

import com.tridium.basicdriver.message.ReceivedMessage;
import com.tridium.json.JSONObject;

/**
 * EasyMLResponse is the super class for all response messages.
 * 
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.6 $ $Date: 2014/11/01 01:09:47 $
 * @since     Baja 1.0
 * 
 */
public abstract class EasyMLResponse 
  extends ReceivedMessage implements EasyMLMessageConst
{
////////////////////////////////////////////////////////////
// Constructor
////////////////////////////////////////////////////////////

 /**
  * Constructor.
  */
  public EasyMLResponse(EasyMLMessage request)
  {
    this.request = request;
  }
  
////////////////////////////////////////////////////////////
// XAtlasResponse
////////////////////////////////////////////////////////////

  /**
   * Form the response data based on the given received bytes.
   * @throws Exception 
   */
  public void readResponse(String content)
  {
    setSuccessfulResponse(false);
    if (content.length() == 0)
      return;

    try
    {
      JSONObject decodedJSON = new JSONObject(content);
      
      // some debug
      if (log.isTraceOn())
      {
        log.message("<<<<<<<< JSON Response <<<<<<<<\n");
        log.message(content);
        log.message("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
      }

      parseResponse(decodedJSON);
    }
    catch (Exception e)
    {
      e.printStackTrace();
      faultString = e.getMessage();
      if (faultString == null)
        faultString = "Undefined fault cause";
    }
  }
  
  public abstract void parseResponse(JSONObject json)
    throws Exception;
  
  public String getFaultString()
  {
    return faultString;
  }

////////////////////////////////////////////////////////////
// Message
////////////////////////////////////////////////////////////
  
  /**
   * Return a debug string for this message.
   */
  public String toDebugString()
  {
    return "*** EasyMLResponse: " + (faultString.length() > 0 ? faultString : "Success") + " ***";
  }

///////////////////////////////////////////////////////////
// Attributes
///////////////////////////////////////////////////////////

  public static final Log log = Log.getLog("EasyMLMessage"); 
  protected EasyMLMessage request;
  protected String faultString = "";
}