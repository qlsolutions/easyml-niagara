/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver;

import java.util.logging.Level;

import javax.baja.driver.util.BPollScheduler;
import javax.baja.naming.BOrd;
import javax.baja.nav.BINavNode;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraAction;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.security.BUsernameAndPassword;
import javax.baja.spy.SpyWriter;
import javax.baja.sys.BBoolean;
import javax.baja.sys.BComponent;
import javax.baja.sys.BFacets;
import javax.baja.sys.BRelTime;
import javax.baja.sys.Clock;
import javax.baja.sys.Context;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Action;
import javax.baja.sys.SlotCursor;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.IFuture;

import com.quicklink.easyml.driver.history.BEasyMLHistoryServerExt;
import com.quicklink.easyml.driver.messages.EasyMLMessage;
import com.quicklink.easyml.driver.messages.EasyMLSendRequest;
import com.quicklink.easyml.driver.messages.EasyMLServerInfoRequest;
import com.quicklink.easyml.driver.messages.EasyMLServerInfoResponse;
import com.quicklink.easyml.driver.learn.BEasyMLHistoryLearnJob;
import com.quicklink.easyml.driver.history.BEasyMLHistoryWorker;
import com.quicklink.easyml.driver.messages.EasyMLResponse;
import com.tridium.basicdriver.BBasicDevice;
import com.tridium.basicdriver.util.BBasicCoalescingWorker;
import com.tridium.basicdriver.util.BBasicPollScheduler;
import com.tridium.basicdriver.util.BBasicWorker;

/**
 * BEasyMLServer - Insert description here.
 *
 * @author 		Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version		$Revision: 1.9 $
 * @since			Baja 1.0
 *
 */
@NiagaraType
/**
 * Define the current HostID of the server.
 */
@NiagaraProperty(
  name = "hostId",
  type = "String",
  defaultValue = "",
  flags = Flags.READONLY
)
/**
 * Define the current type of the server.
 */
@NiagaraProperty(
  name = "apiType",
  type = "String",
  defaultValue = "",
  flags = Flags.READONLY
)
/**
 * Define the current API version of the server.
 */
@NiagaraProperty(
  name = "apiVersion",
  type = "String",
  defaultValue = "",
  flags = Flags.READONLY
)
/**
 * Define the ip address of the WebCTRL system.
 */
@NiagaraProperty(
  name = "ipAddress",
  type = "String",
  defaultValue = "BEasyMLServer.noHost"
)
/**
 * Define the ip port of the WebCTRL system.
 */
@NiagaraProperty(
  name = "ipPort",
  type = "int",
  defaultValue = "443"
)
/**
 * The login credentials used to authenticate the user.
 */
@NiagaraProperty(
  name = "credentials",
  type = "BUsernameAndPassword",
  defaultValue = "new BUsernameAndPassword(\"guest\", \"guest\")"
)
/**
 * Define the use of the SSL connection.
 */
@NiagaraProperty(
  name = "useSsl",
  type = "boolean",
  defaultValue = "true"
)
/**
 * The basic communication dispatch queue worker thread.
 * Its intended use is for synchronizing access to the
 * communication handler (Comm).
 */
@NiagaraProperty(
  name = "dispatcher",
  type = "BBasicWorker",
  defaultValue = "new BBasicWorker()",
  flags = Flags.HIDDEN
)
/**
 * The basic asynchronous write (coalescing) queue
 * worker thread.  Its intended use is for posting
 * write requests that should be coalesced and then
 * handed off to the dispatcher.
 */
@NiagaraProperty(
  name = "writeWorker",
  type = "BBasicCoalescingWorker",
  defaultValue = "new BBasicCoalescingWorker()",
  flags = Flags.HIDDEN
)
@NiagaraProperty(
  name = "historyWorker",
  type = "BEasyMLHistoryWorker",
  defaultValue = "new BEasyMLHistoryWorker()",
  flags = Flags.HIDDEN
)
/**
 * The basic poll scheduler
 */
@NiagaraProperty(
  name = "pollScheduler",
  type = "BPollScheduler",
  defaultValue = "new BBasicPollScheduler()"
)
/**
 * Specifies the default number of retries to perform after a null response
 * to a basic message request
 */
@NiagaraProperty(
  name = "retryCount",
  type = "int",
  defaultValue = "1"
)
/**
 * Specifies the default maximum time to wait for a response
 * after a basic message request before determining a failure
 */
@NiagaraProperty(
  name = "responseTimeout",
  type = "BRelTime",
  defaultValue = "BRelTime.make(2000)",
  facets = @Facet("BFacets.make(BFacets.SHOW_MILLISECONDS, BBoolean.TRUE, BFacets.MIN, BRelTime.make(500))")
)
/**
 * History device extension (for managing histories)
 */
@NiagaraProperty(
  name = "histories",
  type = "BEasyMLHistoryServerExt",
  defaultValue = "new BEasyMLHistoryServerExt()"
)
/**
 * Submits a history discovery job
 * NOTE: The hidden flag only affects the visibility of this method to a user using Workbench.
 * Furthermore, the hidden flag can be removed via editing the slot sheet.
 */
@NiagaraAction(
  name = "submitHistoryDiscoveryJob",
  returnType = "BOrd",
  flags = Flags.HIDDEN
)
public class BEasyMLServer
  extends BBasicDevice
{
  public static final String noHost = "###.###.###.###";

  

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.easyml.driver.BEasyMLServer(2880476048)1.0$ @*/
/* Generated Sun May 28 11:57:21 CEST 2023 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "hostId"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code hostId} property.
   * Define the current HostID of the server.
   * @see #getHostId
   * @see #setHostId
   */
  public static final Property hostId = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code hostId} property.
   * Define the current HostID of the server.
   * @see #hostId
   */
  public String getHostId() { return getString(hostId); }
  
  /**
   * Set the {@code hostId} property.
   * Define the current HostID of the server.
   * @see #hostId
   */
  public void setHostId(String v) { setString(hostId, v, null); }

////////////////////////////////////////////////////////////////
// Property "apiType"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code apiType} property.
   * Define the current type of the server.
   * @see #getApiType
   * @see #setApiType
   */
  public static final Property apiType = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code apiType} property.
   * Define the current type of the server.
   * @see #apiType
   */
  public String getApiType() { return getString(apiType); }
  
  /**
   * Set the {@code apiType} property.
   * Define the current type of the server.
   * @see #apiType
   */
  public void setApiType(String v) { setString(apiType, v, null); }

////////////////////////////////////////////////////////////////
// Property "apiVersion"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code apiVersion} property.
   * Define the current API version of the server.
   * @see #getApiVersion
   * @see #setApiVersion
   */
  public static final Property apiVersion = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code apiVersion} property.
   * Define the current API version of the server.
   * @see #apiVersion
   */
  public String getApiVersion() { return getString(apiVersion); }
  
  /**
   * Set the {@code apiVersion} property.
   * Define the current API version of the server.
   * @see #apiVersion
   */
  public void setApiVersion(String v) { setString(apiVersion, v, null); }

////////////////////////////////////////////////////////////////
// Property "ipAddress"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code ipAddress} property.
   * Define the ip address of the WebCTRL system.
   * @see #getIpAddress
   * @see #setIpAddress
   */
  public static final Property ipAddress = newProperty(0, BEasyMLServer.noHost, null);
  
  /**
   * Get the {@code ipAddress} property.
   * Define the ip address of the WebCTRL system.
   * @see #ipAddress
   */
  public String getIpAddress() { return getString(ipAddress); }
  
  /**
   * Set the {@code ipAddress} property.
   * Define the ip address of the WebCTRL system.
   * @see #ipAddress
   */
  public void setIpAddress(String v) { setString(ipAddress, v, null); }

////////////////////////////////////////////////////////////////
// Property "ipPort"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code ipPort} property.
   * Define the ip port of the WebCTRL system.
   * @see #getIpPort
   * @see #setIpPort
   */
  public static final Property ipPort = newProperty(0, 443, null);
  
  /**
   * Get the {@code ipPort} property.
   * Define the ip port of the WebCTRL system.
   * @see #ipPort
   */
  public int getIpPort() { return getInt(ipPort); }
  
  /**
   * Set the {@code ipPort} property.
   * Define the ip port of the WebCTRL system.
   * @see #ipPort
   */
  public void setIpPort(int v) { setInt(ipPort, v, null); }

////////////////////////////////////////////////////////////////
// Property "credentials"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code credentials} property.
   * The login credentials used to authenticate the user.
   * @see #getCredentials
   * @see #setCredentials
   */
  public static final Property credentials = newProperty(0, new BUsernameAndPassword("guest", "guest"), null);
  
  /**
   * Get the {@code credentials} property.
   * The login credentials used to authenticate the user.
   * @see #credentials
   */
  public BUsernameAndPassword getCredentials() { return (BUsernameAndPassword)get(credentials); }
  
  /**
   * Set the {@code credentials} property.
   * The login credentials used to authenticate the user.
   * @see #credentials
   */
  public void setCredentials(BUsernameAndPassword v) { set(credentials, v, null); }

////////////////////////////////////////////////////////////////
// Property "useSsl"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code useSsl} property.
   * Define the use of the SSL connection.
   * @see #getUseSsl
   * @see #setUseSsl
   */
  public static final Property useSsl = newProperty(0, true, null);
  
  /**
   * Get the {@code useSsl} property.
   * Define the use of the SSL connection.
   * @see #useSsl
   */
  public boolean getUseSsl() { return getBoolean(useSsl); }
  
  /**
   * Set the {@code useSsl} property.
   * Define the use of the SSL connection.
   * @see #useSsl
   */
  public void setUseSsl(boolean v) { setBoolean(useSsl, v, null); }

////////////////////////////////////////////////////////////////
// Property "dispatcher"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code dispatcher} property.
   * The basic communication dispatch queue worker thread.
   * Its intended use is for synchronizing access to the
   * communication handler (Comm).
   * @see #getDispatcher
   * @see #setDispatcher
   */
  public static final Property dispatcher = newProperty(Flags.HIDDEN, new BBasicWorker(), null);
  
  /**
   * Get the {@code dispatcher} property.
   * The basic communication dispatch queue worker thread.
   * Its intended use is for synchronizing access to the
   * communication handler (Comm).
   * @see #dispatcher
   */
  public BBasicWorker getDispatcher() { return (BBasicWorker)get(dispatcher); }
  
  /**
   * Set the {@code dispatcher} property.
   * The basic communication dispatch queue worker thread.
   * Its intended use is for synchronizing access to the
   * communication handler (Comm).
   * @see #dispatcher
   */
  public void setDispatcher(BBasicWorker v) { set(dispatcher, v, null); }

////////////////////////////////////////////////////////////////
// Property "writeWorker"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code writeWorker} property.
   * The basic asynchronous write (coalescing) queue
   * worker thread.  Its intended use is for posting
   * write requests that should be coalesced and then
   * handed off to the dispatcher.
   * @see #getWriteWorker
   * @see #setWriteWorker
   */
  public static final Property writeWorker = newProperty(Flags.HIDDEN, new BBasicCoalescingWorker(), null);
  
  /**
   * Get the {@code writeWorker} property.
   * The basic asynchronous write (coalescing) queue
   * worker thread.  Its intended use is for posting
   * write requests that should be coalesced and then
   * handed off to the dispatcher.
   * @see #writeWorker
   */
  public BBasicCoalescingWorker getWriteWorker() { return (BBasicCoalescingWorker)get(writeWorker); }
  
  /**
   * Set the {@code writeWorker} property.
   * The basic asynchronous write (coalescing) queue
   * worker thread.  Its intended use is for posting
   * write requests that should be coalesced and then
   * handed off to the dispatcher.
   * @see #writeWorker
   */
  public void setWriteWorker(BBasicCoalescingWorker v) { set(writeWorker, v, null); }

////////////////////////////////////////////////////////////////
// Property "historyWorker"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historyWorker} property.
   * @see #getHistoryWorker
   * @see #setHistoryWorker
   */
  public static final Property historyWorker = newProperty(Flags.HIDDEN, new BEasyMLHistoryWorker(), null);
  
  /**
   * Get the {@code historyWorker} property.
   * @see #historyWorker
   */
  public BEasyMLHistoryWorker getHistoryWorker() { return (BEasyMLHistoryWorker)get(historyWorker); }
  
  /**
   * Set the {@code historyWorker} property.
   * @see #historyWorker
   */
  public void setHistoryWorker(BEasyMLHistoryWorker v) { set(historyWorker, v, null); }

////////////////////////////////////////////////////////////////
// Property "pollScheduler"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code pollScheduler} property.
   * The basic poll scheduler
   * @see #getPollScheduler
   * @see #setPollScheduler
   */
  public static final Property pollScheduler = newProperty(0, new BBasicPollScheduler(), null);
  
  /**
   * Get the {@code pollScheduler} property.
   * The basic poll scheduler
   * @see #pollScheduler
   */
  public BPollScheduler getPollScheduler() { return (BPollScheduler)get(pollScheduler); }
  
  /**
   * Set the {@code pollScheduler} property.
   * The basic poll scheduler
   * @see #pollScheduler
   */
  public void setPollScheduler(BPollScheduler v) { set(pollScheduler, v, null); }

////////////////////////////////////////////////////////////////
// Property "retryCount"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code retryCount} property.
   * Specifies the default number of retries to perform after a null response
   * to a basic message request
   * @see #getRetryCount
   * @see #setRetryCount
   */
  public static final Property retryCount = newProperty(0, 1, null);
  
  /**
   * Get the {@code retryCount} property.
   * Specifies the default number of retries to perform after a null response
   * to a basic message request
   * @see #retryCount
   */
  public int getRetryCount() { return getInt(retryCount); }
  
  /**
   * Set the {@code retryCount} property.
   * Specifies the default number of retries to perform after a null response
   * to a basic message request
   * @see #retryCount
   */
  public void setRetryCount(int v) { setInt(retryCount, v, null); }

////////////////////////////////////////////////////////////////
// Property "responseTimeout"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code responseTimeout} property.
   * Specifies the default maximum time to wait for a response
   * after a basic message request before determining a failure
   * @see #getResponseTimeout
   * @see #setResponseTimeout
   */
  public static final Property responseTimeout = newProperty(0, BRelTime.make(2000), BFacets.make(BFacets.SHOW_MILLISECONDS, BBoolean.TRUE, BFacets.MIN, BRelTime.make(500)));
  
  /**
   * Get the {@code responseTimeout} property.
   * Specifies the default maximum time to wait for a response
   * after a basic message request before determining a failure
   * @see #responseTimeout
   */
  public BRelTime getResponseTimeout() { return (BRelTime)get(responseTimeout); }
  
  /**
   * Set the {@code responseTimeout} property.
   * Specifies the default maximum time to wait for a response
   * after a basic message request before determining a failure
   * @see #responseTimeout
   */
  public void setResponseTimeout(BRelTime v) { set(responseTimeout, v, null); }

////////////////////////////////////////////////////////////////
// Property "histories"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code histories} property.
   * History device extension (for managing histories)
   * @see #getHistories
   * @see #setHistories
   */
  public static final Property histories = newProperty(0, new BEasyMLHistoryServerExt(), null);
  
  /**
   * Get the {@code histories} property.
   * History device extension (for managing histories)
   * @see #histories
   */
  public BEasyMLHistoryServerExt getHistories() { return (BEasyMLHistoryServerExt)get(histories); }
  
  /**
   * Set the {@code histories} property.
   * History device extension (for managing histories)
   * @see #histories
   */
  public void setHistories(BEasyMLHistoryServerExt v) { set(histories, v, null); }

////////////////////////////////////////////////////////////////
// Action "submitHistoryDiscoveryJob"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code submitHistoryDiscoveryJob} action.
   * Submits a history discovery job
   * NOTE: The hidden flag only affects the visibility of this method to a user using Workbench.
   * Furthermore, the hidden flag can be removed via editing the slot sheet.
   * @see #submitHistoryDiscoveryJob()
   */
  public static final Action submitHistoryDiscoveryJob = newAction(Flags.HIDDEN, null);
  
  /**
   * Invoke the {@code submitHistoryDiscoveryJob} action.
   * Submits a history discovery job
   * NOTE: The hidden flag only affects the visibility of this method to a user using Workbench.
   * Furthermore, the hidden flag can be removed via editing the slot sheet.
   * @see #submitHistoryDiscoveryJob
   */
  public BOrd submitHistoryDiscoveryJob() { return (BOrd)invoke(submitHistoryDiscoveryJob, null, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEasyMLServer.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  /**
   * Constructor.
   */
  public BEasyMLServer()
  {
    setFlags(upload, Flags.HIDDEN);
    setFlags(download, Flags.HIDDEN);
  }
  
////////////////////////////////////////////////////////////////
// Access
////////////////////////////////////////////////////////////////

  /**
   * Get the network cast to a BEasyMLNetwork
   */
  public final BEasyMLNetwork getEasyMLNetwork()
  {
    return (BEasyMLNetwork)getNetwork();
  }

////////////////////////////////////////////////////////////////
// Device
////////////////////////////////////////////////////////////////
  
  public Type getNetworkType()
  {
    return BEasyMLNetwork.TYPE;
  }

  public boolean isParentLegal(BComponent parent)
  {
    return (parent instanceof BEasyMLNetwork) || (parent instanceof BEasyMLServerFolder);
  }
  
////////////////////////////////////////////////////////////////
// Lifecycle
////////////////////////////////////////////////////////////////
  
  /**
   * Callback when network is started.
   */
  public final void started() throws Exception
  {
    super.started();
    
    if (!isDisabled() && !isFault() && !initializeNetwork())
      getLogger().warning("Unable to initialize network: " + getName());
  }
  
  public void stopped() throws Exception
  {
    commActive = false;
    networkInitialized = false;
    super.stopped();
  }
  
  public void changed(Property property, Context context)
  {
    super.changed(property, context);
    
    if (!isRunning())
      return;
    
    if (property == ipAddress)
    {
      if (getIpAddress().equals(noHost))
      {
        configFail("No IP address properly configured.");
        commActive = false;
      }
      else
      {
        if (!isDisabled() && !isFault() && !initializeNetwork())
          getLogger().warning("Unable to initialize network: " + getName());
        else
          configOk();
      }
    }
    else if (property == status)
    {
      if (!isCommActive() && !isDisabled() && !isFatalFault())
        initializeNetwork();  
      else if (isDisabled())
        commActive = false;
    }
  }

////////////////////////////////////////////////////////////////
// Ping
////////////////////////////////////////////////////////////////

  public void doPing() throws Exception
  {
    if (!isRunning() || isDisabled() || isFault())
      return;

    try
    {
      EasyMLMessage request = new EasyMLServerInfoRequest();
      EasyMLResponse response = sendSyncMessage(request, getResponseTimeout(), getRetryCount());

      if (response != null && response.getSuccessfulResponse())
      {
        setHostId(((EasyMLServerInfoResponse)response).getHostId());
        setApiType(((EasyMLServerInfoResponse)response).getType());
        setApiVersion(((EasyMLServerInfoResponse)response).getVersion());
        pingOk();
      }
      else
        pingFail("Response timeout.");
    }
    catch (Exception e)
    {
      pingFail(e.getLocalizedMessage());
    }
  }
  
////////////////////////////////////////////////////////////////
// Basic Send Message Request Helper Methods
////////////////////////////////////////////////////////////////

  protected boolean initializeNetwork()
  {
    if (!networkInitialized)
    {
      commActive = true;
      networkInitialized = true;
    }
    
    getLogger().fine("Initialize network: " + getName());
    return true;
  }

  public boolean isCommActive()
  {
    return commActive && !isDisabled() && !isFault();
  }
  
  public synchronized EasyMLResponse sendSyncMessage(EasyMLMessage message)
  {
    return sendSyncMessage(message, getResponseTimeout(), getRetryCount());
  }
  
  public synchronized EasyMLResponse sendSyncMessage(EasyMLMessage message, BRelTime timeout, int retryCount)
  {
    if (!isCommActive())
      return null;

    EasyMLSendRequest request = new EasyMLSendRequest(this, message, timeout, retryCount);
    dispatch(request);
    
    EasyMLResponse response = request.getResponse();
    
    if (response != null && getLogger().isLoggable(Level.INFO))
      getLogger().info(response.toDebugString());
    
    return response;
  }
  
////////////////////////////////////////////////////////////////
// Posting to Worker Queues
////////////////////////////////////////////////////////////////
  
  /**
   * Post the runnable task to the dispatcher comm queue. This queue is intended to synchronize
   * access to the communication handler (Comm), thus runnable tasks posted here should attempt to
   * transmit a message via Comm and accept a response if any.
   */
  public final IFuture dispatch(Runnable r)
  {
    return getDispatcher().post(r);
  }

  /**
   * Post the runnable task to the coalescing write worker queue (asynchronous). This queue is
   * intended to handle asynchronous operations, such as write requests, that should be coalesced.
   * These operations should post any message requests to the dispatcher.
   */
  public final IFuture postWrite(Runnable r)
  {
    return getWriteWorker().post(r);
  }
  
////////////////////////////////////////////////////////////////
// Presentation
////////////////////////////////////////////////////////////////
  
  /**
   * Return all the non-hidden child components.. 
   */
  public BINavNode[] getNavChildren()
  {                                                  
    loadSlots();
    BComponent[] temp = new BComponent[getSlotCount()];
    SlotCursor<Property> c = getProperties();
    int count = 0;
    while(c.nextComponent())
    {
      BComponent kid = (BComponent)c.get();
      if (Flags.isHidden(this, c.property())) continue;
      if (!kid.isNavChild()) continue;
      if (c.property() == pollScheduler) continue;
      temp[count++] = kid;
    }
    
    BComponent[] result = new BComponent[count];
    System.arraycopy(temp, 0, result, 0, count);
    return result;
  }
  
////////////////////////////////////////////////////////////////
//Actions
////////////////////////////////////////////////////////////////
  
  /**
   * Submit the history discovery job.
   */
  public BOrd doSubmitHistoryDiscoveryJob(Context cx)
  {
    return new BEasyMLHistoryLearnJob(this).submit(cx);
  }

////////////////////////////////////////////////////////////////
// Spy
////////////////////////////////////////////////////////////////
  
  /**
   * Adds basic communication statistics to the spy page, such as total messages sent/received and
   * transmission rates.
   */
  public void spy(SpyWriter out) throws Exception
  {
    super.spy(out);
    out.startProps();
    out.trTitle("EasyML Server", 2);

    long elapsedTime = Clock.ticks() - lastRateTicks;
    double sentMessagesPerSecond = (((double)(totalSentMessages - lastSentMessages)) / ((double) (((double) elapsedTime) / 1000.0)));
    double receivedMessagesPerSecond = (((double)(totalReceivedMessages - lastReceivedMessages)) / ((double) (((double) elapsedTime) / 1000.0)));
    lastSentMessages = totalSentMessages;
    lastReceivedMessages = totalReceivedMessages;
    lastRateTicks = Clock.ticks();

    out.prop("Total Sent Messages", new Long(totalSentMessages));
    out.prop("Sent Messages per Second", new Double(sentMessagesPerSecond));
    out.prop("Total Received Messages", new Long(totalReceivedMessages));
    out.prop("Received Messages per Second", new Double(receivedMessagesPerSecond));
    out.prop("Total Failed Messages (timeouts)", new Long(totalTimeoutMessages));
    out.endProps();
  }

  /**
   * This method should be called when a Message was sent out in order to update the basic
   * statistics.
   */
  public final void incrementSent()
  {
    totalSentMessages++;
  }

  /**
   * This method should be called when a Message was received in order to update the basic
   * statistics.
   */
  public final void incrementReceived()
  {
    totalReceivedMessages++;
  }

  /**
   * This method should be called when a Message failed to get a response (timeout) in order to
   * update the basic statistics.
   */
  public final void incrementTimeouts()
  {
    totalTimeoutMessages++;
  }
  
////////////////////////////////////////////////////////////////
// Attributes
////////////////////////////////////////////////////////////////

  protected boolean commActive = false;
  protected boolean networkInitialized = false;
  private long totalSentMessages = 0L;
  private long totalReceivedMessages = 0L;
  private long lastSentMessages = 0L;
  private long lastReceivedMessages = 0L;
  private long lastRateTicks = Clock.ticks();
  private long totalTimeoutMessages = 0L;
}