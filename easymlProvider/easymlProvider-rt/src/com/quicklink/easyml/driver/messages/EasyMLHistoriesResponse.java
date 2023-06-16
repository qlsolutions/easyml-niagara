/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver.messages;

import com.tridium.json.JSONArray;
import com.tridium.json.JSONObject;

/**
 * EasyMLHistoriesResponse is the get version response message.
 * 
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.4 $ $Date: 2014/10/27 12:45:00 $
 * @since     Baja 1.0
 * 
 */
public class EasyMLHistoriesResponse
  extends EasyMLResponse implements EasyMLMessageConst
{

////////////////////////////////////////////////////////////
// Constructor
////////////////////////////////////////////////////////////

  /**
   * Constructor with the specified request.
   */
  public EasyMLHistoriesResponse(EasyMLHistoriesRequest request)
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
    if (json != null && json.has("series"))
    {
      try
      {
        this.series = json.getJSONArray("series");
        setSuccessfulResponse(true);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public JSONArray getSeries()
  {
    return this.series;
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
    sb.append("*** Received EasyMLHistoriesResponse: ").append(series.length()).append(" series ***");
    return sb.toString();
  }

///////////////////////////////////////////////////////////
//Attributes
///////////////////////////////////////////////////////////

  private JSONArray series = new JSONArray();
}