/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver.messages;

import com.tridium.basicdriver.message.ReceivedMessage;

/**
 * EasyMLReceivedMessage is a wrapper class for a received byte array
 * message before it's contents have been interpreted.
 *
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.2 $ $Date: 2014/08/19 17:03:38 $
 */
public class EasyMLReceivedMessage
  extends ReceivedMessage
{

////////////////////////////////////////////////////////////
// Constructor
////////////////////////////////////////////////////////////

 /**
  * Constructor with message data in byte array form.
  * Also specifies the tag.
  */
  public EasyMLReceivedMessage(String data)
  {
    this.data = data;
  }

////////////////////////////////////////////////////////////
// WebCtrlReceivedMessage
////////////////////////////////////////////////////////////

  /**
   * Returns the message content.
   */
  public String getData()
  {
    return data;
  }  
  /**
   * Returns the message content.
   */
  public String toDebugString()
  {
    return data;
  }

///////////////////////////////////////////////////////////
// Attributes
///////////////////////////////////////////////////////////
  
  private String data = "";
}