/**
 * Copyright 2023, QuickLink Solutions - All Rights Reserved.
 */
package com.quicklink.easyml.driver.history;

import java.util.logging.Logger;

import javax.baja.driver.history.BHistoryImport;
import javax.baja.driver.util.BDescriptorState;
import javax.baja.history.BBooleanTrendRecord;
import javax.baja.history.BCollectionInterval;
import javax.baja.history.BEnumTrendRecord;
import javax.baja.history.BHistoryConfig;
import javax.baja.history.BHistoryService;
import javax.baja.history.BIHistory;
import javax.baja.history.BNumericTrendRecord;
import javax.baja.history.BStringTrendRecord;
import javax.baja.history.BTrendRecord;
import javax.baja.history.db.BHistoryDatabase;
import javax.baja.history.db.HistoryDatabaseConnection;
import javax.baja.naming.SlotPath;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.Action;
import javax.baja.sys.BAbsTime;
import javax.baja.sys.BFacets;
import javax.baja.sys.BValue;
import javax.baja.sys.Context;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.timezone.BTimeZone;
import javax.baja.util.BNameList;
import javax.baja.util.BTypeSpec;
import javax.baja.util.IFuture;
import javax.baja.util.Invocation;

import com.quicklink.easyml.driver.BEasyMLServer;
import com.quicklink.easyml.driver.messages.EasyMLHistoryRequest;
import com.quicklink.easyml.driver.messages.EasyMLHistoryResponse;
import com.quicklink.easyml.driver.messages.EasyMLMessage;

/**
 * BEasyMLHistoryImport - Insert description here.
 *
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.2.2.3 $
 * @since     Baja 1.0
 *
 */
@NiagaraType
/**
 * Define the record type in the local history.
 */
@NiagaraProperty(
  name = "historyType",
  type = "String",
  defaultValue = "\"\"",
  flags = Flags.READONLY
)
/**
 * Define the interval in the local history.
 */
@NiagaraProperty(
  name = "interval",
  type = "BCollectionInterval",
  defaultValue = "BCollectionInterval.DEFAULT",
  flags = Flags.READONLY
)
/**
 * Define the facets in the local history.
 */
@NiagaraProperty(
  name = "facets",
  type = "BFacets",
  defaultValue = "BFacets.NULL"
)
/**
 * Define the systemtags in the local history.
 */
@NiagaraProperty(
  name = "systemTags",
  type = "BNameList",
  defaultValue = "BNameList.NULL"
)
public class BEasyMLHistoryImport
  extends BHistoryImport
{
  

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.easyml.driver.history.BEasyMLHistoryImport(1909724376)1.0$ @*/
/* Generated Wed Jun 07 22:11:06 CEST 2023 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "historyType"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historyType} property.
   * Define the record type in the local history.
   * @see #getHistoryType
   * @see #setHistoryType
   */
  public static final Property historyType = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code historyType} property.
   * Define the record type in the local history.
   * @see #historyType
   */
  public String getHistoryType() { return getString(historyType); }
  
  /**
   * Set the {@code historyType} property.
   * Define the record type in the local history.
   * @see #historyType
   */
  public void setHistoryType(String v) { setString(historyType, v, null); }

////////////////////////////////////////////////////////////////
// Property "interval"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code interval} property.
   * Define the interval in the local history.
   * @see #getInterval
   * @see #setInterval
   */
  public static final Property interval = newProperty(Flags.READONLY, BCollectionInterval.DEFAULT, null);
  
  /**
   * Get the {@code interval} property.
   * Define the interval in the local history.
   * @see #interval
   */
  public BCollectionInterval getInterval() { return (BCollectionInterval)get(interval); }
  
  /**
   * Set the {@code interval} property.
   * Define the interval in the local history.
   * @see #interval
   */
  public void setInterval(BCollectionInterval v) { set(interval, v, null); }

////////////////////////////////////////////////////////////////
// Property "facets"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code facets} property.
   * Define the facets in the local history.
   * @see #getFacets
   * @see #setFacets
   */
  public static final Property facets = newProperty(0, BFacets.NULL, null);
  
  /**
   * Get the {@code facets} property.
   * Define the facets in the local history.
   * @see #facets
   */
  public BFacets getFacets() { return (BFacets)get(facets); }
  
  /**
   * Set the {@code facets} property.
   * Define the facets in the local history.
   * @see #facets
   */
  public void setFacets(BFacets v) { set(facets, v, null); }

////////////////////////////////////////////////////////////////
// Property "systemTags"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code systemTags} property.
   * Define the systemtags in the local history.
   * @see #getSystemTags
   * @see #setSystemTags
   */
  public static final Property systemTags = newProperty(0, BNameList.NULL, null);
  
  /**
   * Get the {@code systemTags} property.
   * Define the systemtags in the local history.
   * @see #systemTags
   */
  public BNameList getSystemTags() { return (BNameList)get(systemTags); }
  
  /**
   * Set the {@code systemTags} property.
   * Define the systemtags in the local history.
   * @see #systemTags
   */
  public void setSystemTags(BNameList v) { set(systemTags, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEasyMLHistoryImport.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  /**
   * Empty constructor.
   */
  public BEasyMLHistoryImport()
  {
  }

  public void doExecute()
  {
    synchronized(syncObj)
    {
      while(executeInProgress) 
      try { syncObj.wait(); }
      catch(InterruptedException e) { executeFail("Interrupted history import execution."); }
      executeInProgress = true;
    }
    
    BEasyMLServer server = (BEasyMLServer)getDevice();

    if (!server.getStatus().isValid())
    {
      logTrace("Server invalid status, on thread " + Thread.currentThread().getName());
      setState(BDescriptorState.idle);
      updateStatus();
      synchronized(syncObj)
      {
        executeInProgress = false;
        syncObj.notify();
      }
      return;
    }
    
    long t0 = System.currentTimeMillis();
    try
    {
      // call this just to start the execute in progress
      executeInProgress(); 
      if (getHistoryId().isNull())
      {
        executeFail("History id is null.");
        synchronized(syncObj)
        {
          executeInProgress = false;
          syncObj.notify();
        }
        return;
      }
  
      logTrace("Begin import on thread " + Thread.currentThread().getName());

      // get the service and the database object
      BHistoryService service = (BHistoryService)Sys.getService(BHistoryService.TYPE);
      BHistoryDatabase db = service.getDatabase();
      
      if (db == null)
      {
        executeFail("Local history database not available.");
        synchronized(syncObj)
        {
          executeInProgress = false;
          syncObj.notify();
        }
        return;
      }
      
      // build the history id
      BHistoryConfig config = new BHistoryConfig();
      config.setId(getHistoryId());
      config.setRecordType(BTypeSpec.make(makeRecordType(getHistoryType())));      
      config.setTimeZone(BTimeZone.getLocal());
      config.setSystemTags(getSystemTags());
      config.setInterval(getInterval());
      
      // create a history configuration object (this tells the history certain information about how to store the history)
      config = makeLocalConfig(config);
      config.setSourceHandle(getHandleOrd());
      
      // set up the history config facets  
      BFacets facets = (BFacets)config.get(VALUE_FACETS);
        
      if (facets != null)
        config.set(VALUE_FACETS, getFacets().newCopy()); 
      else
        config.add(VALUE_FACETS, getFacets().newCopy());
            
      // create a history if there isn't one
      HistoryDatabaseConnection conn = db.getDbConnection(null);
      if (!conn.exists(getHistoryId()))
        conn.createHistory(config);
      else
        conn.reconfigureHistory(config);
      
      // get the history we are going to add too
      BIHistory history = conn.getHistory(getHistoryId());
      
      // append the record to the history
      BAbsTime lastTimestamp = conn.getLastTimestamp(history);

      logTrace("Last timestamp from the history: " + lastTimestamp.toString());

      // parse the response records
      String historyId = SlotPath.escape(getHistoryId().getDeviceName() + "/" + getHistoryId().getHistoryName());
      int limit = server.getHistories().getRequestRecords();
  
      boolean done = false;
      String error = "";
      int count = 0;
      
      do
      {
        long start = conn.getLastTimestamp(history).getMillis();
        if (start > 0)
          start = start + 1;

        EasyMLMessage request = new EasyMLHistoryRequest(historyId, start, 0, limit);
        EasyMLHistoryResponse response = (EasyMLHistoryResponse)server.sendSyncMessage(request, server.getResponseTimeout(), server.getRetryCount());
        
        if (response == null || !response.getSuccessfulResponse())
        {
          error = "Response timeout";
          done = true;
        }
        else
        {
          BTrendRecord records[] = response.getRecords();
          
          // append the records to the history
          for (int i=0; i<records.length; ++i)
            conn.append(history, records[i]);
          
          if (records.length == 0)
            done = true;
          
          count += records.length;
        }
      }
      while (!done);

      logTrace("Number of new records to appended: " + count);

      if (error.length() > 0)
      {
        executeFail(error);
        synchronized(syncObj)
        {
          executeInProgress = false;
          syncObj.notify();
        }
        return;
      }

      executeOk();
    }
    catch(Exception e)
    {
      executeFail(e);    
      logError("Error creating the history...");
      e.printStackTrace();
    }
    finally
    {
      long ms = System.currentTimeMillis() - t0;
      logTrace("End export (" + ms + "ms)");
      
      synchronized(syncObj)
      {
        executeInProgress = false;
        syncObj.notify();
      }
    }
  }
  
  public static Type makeRecordType(String type)
  {
    Type recordType = BStringTrendRecord.TYPE;
    if (type.equals("bool"))
      recordType = BBooleanTrendRecord.TYPE;
    else if (type.equals("numeric"))
      recordType = BNumericTrendRecord.TYPE;
    else if (type.equals("enum"))
      recordType = BEnumTrendRecord.TYPE;
    else if (type.equals("string"))
      recordType = BStringTrendRecord.TYPE;
    return recordType;
  }
  

  /**
   * Post an archive request to the async thread.
   */
  protected IFuture postExecute(Action action, BValue arg, Context context)
  {
    // if the station is running
    if (!isRunning())
      return null;

    // return the device object
    BEasyMLServer server = (BEasyMLServer)getDevice();
    
    if (!server.getStatus().isValid())
      return null;
    
    // post the request as async onto the driver history worker thread
    return server.getHistoryWorker().postAsync(new Invocation(this, action, arg, context));
  }
  
  /**
   * logTrace
   */
  private void logTrace(String msg)
  {
    LOG.fine("EasyMLHistoryImport " + getHistoryId() + " " + msg);
  }

  /**
   * logError
   */
  private void logError(String msg)
  {
    LOG.severe("EasyMLHistoryImport " + getHistoryId() + " " + msg);
  }
  
////////////////////////////////////////////////////////////////
//Attributes
////////////////////////////////////////////////////////////////
  
  private static final Logger LOG = Logger.getLogger("easyml");
  private Object syncObj = new Object();
  boolean executeInProgress = false;
  private static final String VALUE_FACETS = "valueFacets";
}