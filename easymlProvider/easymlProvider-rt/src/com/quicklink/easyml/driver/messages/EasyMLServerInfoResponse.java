/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver.messages;

import com.tridium.json.JSONObject;

/**
 * EasyMLServerInfoResponse is the get version response message.
 * 
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.4 $ $Date: 2014/10/27 12:45:00 $
 * @since     Baja 1.0
 * 
 */
public class EasyMLServerInfoResponse
  extends EasyMLResponse implements EasyMLMessageConst
{

////////////////////////////////////////////////////////////
// Constructor
////////////////////////////////////////////////////////////

  /**
   * Constructor with the specified request.
   */
  public EasyMLServerInfoResponse(EasyMLServerInfoRequest request)
  {
    super(request);
  }
  
////////////////////////////////////////////////////////////
// WebCtrlResponse
////////////////////////////////////////////////////////////
  
  public void parseResponse(JSONObject json)
    throws Exception
  {
    setSuccessfulResponse(false);
    if (json != null)
    {
      setSuccessfulResponse(true);
      if (json.has("host_id"))
        this.hostId = json.getString("host_id");
      else
        setSuccessfulResponse(false);

      if (json.has("type"))
        this.type = json.getString("type");
      else
        setSuccessfulResponse(false);

      if (json.has("version"))
        this.version = json.getString("version");
      else
        setSuccessfulResponse(false);
    }
  }
  
  public String getHostId()
  {
    return this.hostId;
  }

  public String getType()
  {
    return this.type;
  }

  public String getVersion()
  {
    return this.version;
  }

////////////////////////////////////////////////////////////
//Message
////////////////////////////////////////////////////////////

  /**
   * Return a debug string for this message.
   */
  public String toDebugString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("*** Received EasyMLServerInfoResponse: ")
        .append(hostId)
        .append(",").append(type)
        .append(",").append(version)
        .append(" ***");
    return sb.toString();
  }

///////////////////////////////////////////////////////////
//Attributes
///////////////////////////////////////////////////////////
  
  private String hostId = "";
  private String type = "";
  private String version = "";
}