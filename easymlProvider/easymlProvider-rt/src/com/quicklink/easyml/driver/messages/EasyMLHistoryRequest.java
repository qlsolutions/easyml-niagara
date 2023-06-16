/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver.messages;

import com.tridium.basicdriver.message.Message;
import com.tridium.basicdriver.message.ReceivedMessage;
import com.tridium.json.JSONObject;

/**
 * EasyMLHistoryRequest is the super class for serial messages.
 * 
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.4 $ $Date: 2014/10/27 12:45:00 $
 * @since     Baja 1.0
 * 
 */

public class EasyMLHistoryRequest
  extends EasyMLMessage
{

////////////////////////////////////////////////////////////
// Constructor
////////////////////////////////////////////////////////////
  
  /**
   * Constructor.
   */
   public EasyMLHistoryRequest(String historyId, long from, long to, int limit)
   {
     super("POST", "/easyml/serie/" + historyId, buildData(from, to, limit));
   }
   
   private static JSONObject buildData(long from , long to, int limit)
   {
     JSONObject data = new JSONObject();
     data.put("from", from);
     data.put("to", to);
     data.put("limit", limit);
     
     return data;
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
    EasyMLHistoryResponse respMessage = new EasyMLHistoryResponse(this);
    respMessage.readResponse(respMsg.getData());
    respMsg = null;
    return respMessage;
  }
}