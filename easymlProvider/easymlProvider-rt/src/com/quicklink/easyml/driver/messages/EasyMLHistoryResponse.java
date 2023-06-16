/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver.messages;

import javax.baja.history.BBooleanTrendRecord;
import javax.baja.history.BEnumTrendRecord;
import javax.baja.history.BNumericTrendRecord;
import javax.baja.history.BStringTrendRecord;
import javax.baja.history.BTrendRecord;
import javax.baja.nre.util.Array;
import javax.baja.sys.BAbsTime;
import javax.baja.sys.BDynamicEnum;

import com.quicklink.easyml.driver.history.BEasyMLHistoryImport;
import com.tridium.json.JSONArray;
import com.tridium.json.JSONObject;

/**
 * EasyMLHistoryResponse is the get version response message.
 * 
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.4 $ $Date: 2014/10/27 12:45:00 $
 * @since     Baja 1.0
 * 
 */
public class EasyMLHistoryResponse
  extends EasyMLResponse implements EasyMLMessageConst
{

////////////////////////////////////////////////////////////
// Constructor
////////////////////////////////////////////////////////////

  /**
   * Constructor with the specified request.
   */
  public EasyMLHistoryResponse(EasyMLHistoryRequest request)
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
    if (json != null && json.has("type"))
    {
      try
      {
        BTrendRecord record = (BTrendRecord)BEasyMLHistoryImport.makeRecordType(json.getString("type")).getInstance();
        JSONArray data = json.getJSONArray("data");
        for (int i=0; i<data.length(); ++i)
        {
          JSONObject obj = data.getJSONObject(i);
          record.setTimestamp(BAbsTime.make(obj.getLong("timestamp")));
          String value = obj.getString("value");
          
          if (record.getType().is(BStringTrendRecord.TYPE))
            ((BStringTrendRecord)record).setValue(value);
          else if (record.getType().is(BBooleanTrendRecord.TYPE))
            ((BBooleanTrendRecord)record).setValue(Boolean.parseBoolean(value));
          else if (record.getType().is(BEnumTrendRecord.TYPE))
            ((BEnumTrendRecord)record).setValue(BDynamicEnum.make(Integer.parseInt(value)));
          else if (record.getType().is(BNumericTrendRecord.TYPE))
            ((BNumericTrendRecord)record).setValue(Double.parseDouble(value));
          
          records.add((BTrendRecord)record.newCopy());
        }
        
        setSuccessfulResponse(true);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public BTrendRecord[] getRecords()
  {
    return records.trim();
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
    sb.append("*** Received EasyMLHistoryResponse: ").append(records.size()).append(" records ***");
    return sb.toString();
  }

///////////////////////////////////////////////////////////
//Attributes
///////////////////////////////////////////////////////////
  
  private Array<BTrendRecord> records = new Array<BTrendRecord>(BTrendRecord.class);
}