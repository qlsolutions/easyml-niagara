/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver.messages;

import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;

/**
 * EasyMLHistoriesRequest is the super class for serial messages.
 * 
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.4 $ $Date: 2014/10/27 12:45:00 $
 * @since     Baja 1.0
 * 
 */
public class EasyMLHistoriesRequest
  extends EasyMLMessage
{

////////////////////////////////////////////////////////////
// Constructor
////////////////////////////////////////////////////////////
  
  /**
   * Constructor.
   */
   public EasyMLHistoriesRequest()
   {
     super("GET", "/easyml/series", null);
   }

////////////////////////////////////////////////////////////
// WebCtrlPingRequest
////////////////////////////////////////////////////////////

  /**
   * Convert a received message to a response Message.
   */
  public Message toResponse(ReceivedMessage resp)
  {
    EasyMLReceivedMessage respMsg = (EasyMLReceivedMessage)resp;
    EasyMLHistoriesResponse respMessage = new EasyMLHistoriesResponse(this);
    respMessage.readResponse(respMsg.getData());
    respMsg = null;
    return respMessage;
  }
}