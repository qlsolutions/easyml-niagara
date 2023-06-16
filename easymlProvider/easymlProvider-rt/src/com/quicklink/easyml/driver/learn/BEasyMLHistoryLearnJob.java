/**
o * Copyright 2023, QuickLink Solutions - All Rights Reserved.
 */
package com.quicklink.easyml.driver.learn;

import javax.baja.job.BSimpleJob;
import javax.baja.naming.SlotPath;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BajaRuntimeException;
import javax.baja.sys.Context;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.quicklink.easyml.driver.BEasyMLServer;
import com.quicklink.easyml.driver.messages.EasyMLHistoriesRequest;
import com.quicklink.easyml.driver.messages.EasyMLHistoriesResponse;
import com.quicklink.easyml.driver.messages.EasyMLMessage;
import com.tridium.json.JSONArray;
import com.tridium.json.JSONObject;

/**
 * BEasyMLHistoryLearnJob - Insert description here.
 *
 * @author    Alessandro Gastaldello
 * @creation  26 May 2023
 * @version   $Revision: 1.14 $
 * @since     Baja 1.0
 *
 */
@NiagaraType
public class BEasyMLHistoryLearnJob
  extends BSimpleJob
{
  
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.ntrendiq.learn.BTrendIqLearnHistoryJob(2979906276)1.0$ @*/
/* Generated Wed Aug 10 10:24:27 CEST 2016 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEasyMLHistoryLearnJob.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  /**
   * Empty constructor for JAVA introspection.
   */
  public BEasyMLHistoryLearnJob()
  {
  }

  public BEasyMLHistoryLearnJob(BEasyMLServer server)
  {
    this.server = server;
  }
  
  /* (non-Javadoc)
   * @see javax.baja.job.BSimpleJob#run(javax.baja.sys.Context)
   */
  public void run(Context cx) throws Exception
  {
    if (!server.getStatus().isValid())
      throw new Exception("Server status is not valid.");

    EasyMLMessage request = new EasyMLHistoriesRequest();
    EasyMLHistoriesResponse response = (EasyMLHistoriesResponse)server.sendSyncMessage(request, server.getResponseTimeout(), server.getRetryCount());

    progress(10);
    if (response != null && response.getSuccessfulResponse())
    {
      JSONArray series = response.getSeries();
      int serieStep = 90 / series.length();
      for (int i=0; i<series.length(); ++i)
      {
        if (cancel)
          break;
        
        try
        {
          JSONObject serie = series.getJSONObject(i);
          String displayName = serie.getString("displayName");
          log().message("Fetching data for serie " + displayName);

          // decode the history id
          String id = SlotPath.unescape(serie.getString("id"));
          int index = id.indexOf('/');
          if (index != -1)
            addLearnedHistory(serie.getString("displayName"), serie.getString("type"), id.substring(0, index), id.substring(index+1), serie.getString("interval"), serie.getString("units"));
          else
            log().failed("Invalid history id for serie " + displayName);
        }
        catch(Exception e)
        {
          log().failed("Error in fetching data for serie " + series.get(i), e);
        }
        
        progress((i+1)*serieStep);
      }
    }
    else
      throw new BajaRuntimeException("Discovering error: No answer.");

    // We've finished so update the progress bar to 100
    progress(100);
    if (cancel)
      throw new BajaRuntimeException("Discovering Job Cancel");
  }
  
  public void doCancel(Context cx)
  {
    super.doCancel(cx);
    cancel = true;
  }
  
  void addLearnedHistory(String label, String type, String device, String name, String interval, String units)
  {
    String learnName = SlotPath.escape(device + "_" + name);
    if (get(learnName) == null) // If a learn entry does not yet exist for this history
    {
      BEasyMLHistoryEntry entry = new BEasyMLHistoryEntry(label, type, device, name, interval, units);
      add(learnName, entry);
      log().message("Found serie (" + device + "): " + name);
    }
    else
      log().failed("History already present (" + device + "):" + name);
  }

////////////////////////////////////////////////////////////////
//Attributes
////////////////////////////////////////////////////////////////

  private BEasyMLServer server = null;
  private boolean cancel = false;
}