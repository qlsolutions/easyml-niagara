/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver;

import javax.baja.driver.history.BHistoryNetworkExt;
import javax.baja.driver.loadable.BLoadableNetwork;
import javax.baja.driver.point.BTuningPolicyMap;
import javax.baja.license.Feature;
import javax.baja.log.Log;
import javax.baja.naming.SlotPath;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BBoolean;
import javax.baja.sys.BComponentEvent;
import javax.baja.sys.BFacets;
import javax.baja.sys.BRelTime;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Subscriber;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.IFuture;

import com.tridium.basicdriver.util.BBasicCoalescingWorker;
import com.tridium.basicdriver.util.BBasicWorker;

/**
 * BEasyMLNetwork - Insert description here.
 *
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.5 $
 * @since     Baja 1.0
 *
 */
@NiagaraType
/**
 * A container for tuning policies which determines how
 * and when proxy points are read and written.
 */
@NiagaraProperty(
  name = "tuningPolicies",
  type = "BTuningPolicyMap",
  defaultValue = "new BTuningPolicyMap()"
)
/**
 * Define the history network policies.
 */
@NiagaraProperty(
  name = "historyPolicies",
  type = "BHistoryNetworkExt",
  defaultValue = "new BHistoryNetworkExt()"
)
/**
 * The basic asynchronous queue worker thread.
 * The default is a BBasicCoalescingWorker.
 * Its intended use is for posting asynchronous
 * requests (such as learns), that when processed
 * may post one or more message requests to the
 * dispatcher.
 */
@NiagaraProperty(
  name = "worker",
  type = "BBasicWorker",
  defaultValue = "new BBasicCoalescingWorker()",
  flags = Flags.HIDDEN
)
/**
 * Define the socket connection timeout.
 */
@NiagaraProperty(
  name = "socketConnectionTimeout",
  type = "BRelTime",
  defaultValue = "BRelTime.makeSeconds(30)",
  facets = @Facet("BFacets.make(BFacets.make(BFacets.SHOW_MILLISECONDS, BBoolean.TRUE), BFacets.MIN, BRelTime.makeSeconds(3))")
)
/**
 * Define the maximum fails response until the network will be consider down.
 */
@NiagaraProperty(
  name = "maxFailsUntilDown",
  type = "int",
  defaultValue = "2",
  facets = @Facet("BFacets.makeInt(2, 5)")
)
public class BEasyMLNetwork
  extends BLoadableNetwork
{
  

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.easyml.driver.BEasyMLNetwork(2004675235)1.0$ @*/
/* Generated Mon Jun 12 22:59:07 CEST 2023 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "tuningPolicies"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code tuningPolicies} property.
   * A container for tuning policies which determines how
   * and when proxy points are read and written.
   * @see #getTuningPolicies
   * @see #setTuningPolicies
   */
  public static final Property tuningPolicies = newProperty(0, new BTuningPolicyMap(), null);
  
  /**
   * Get the {@code tuningPolicies} property.
   * A container for tuning policies which determines how
   * and when proxy points are read and written.
   * @see #tuningPolicies
   */
  public BTuningPolicyMap getTuningPolicies() { return (BTuningPolicyMap)get(tuningPolicies); }
  
  /**
   * Set the {@code tuningPolicies} property.
   * A container for tuning policies which determines how
   * and when proxy points are read and written.
   * @see #tuningPolicies
   */
  public void setTuningPolicies(BTuningPolicyMap v) { set(tuningPolicies, v, null); }

////////////////////////////////////////////////////////////////
// Property "historyPolicies"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historyPolicies} property.
   * Define the history network policies.
   * @see #getHistoryPolicies
   * @see #setHistoryPolicies
   */
  public static final Property historyPolicies = newProperty(0, new BHistoryNetworkExt(), null);
  
  /**
   * Get the {@code historyPolicies} property.
   * Define the history network policies.
   * @see #historyPolicies
   */
  public BHistoryNetworkExt getHistoryPolicies() { return (BHistoryNetworkExt)get(historyPolicies); }
  
  /**
   * Set the {@code historyPolicies} property.
   * Define the history network policies.
   * @see #historyPolicies
   */
  public void setHistoryPolicies(BHistoryNetworkExt v) { set(historyPolicies, v, null); }

////////////////////////////////////////////////////////////////
// Property "worker"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code worker} property.
   * The basic asynchronous queue worker thread.
   * The default is a BBasicCoalescingWorker.
   * Its intended use is for posting asynchronous
   * requests (such as learns), that when processed
   * may post one or more message requests to the
   * dispatcher.
   * @see #getWorker
   * @see #setWorker
   */
  public static final Property worker = newProperty(Flags.HIDDEN, new BBasicCoalescingWorker(), null);
  
  /**
   * Get the {@code worker} property.
   * The basic asynchronous queue worker thread.
   * The default is a BBasicCoalescingWorker.
   * Its intended use is for posting asynchronous
   * requests (such as learns), that when processed
   * may post one or more message requests to the
   * dispatcher.
   * @see #worker
   */
  public BBasicWorker getWorker() { return (BBasicWorker)get(worker); }
  
  /**
   * Set the {@code worker} property.
   * The basic asynchronous queue worker thread.
   * The default is a BBasicCoalescingWorker.
   * Its intended use is for posting asynchronous
   * requests (such as learns), that when processed
   * may post one or more message requests to the
   * dispatcher.
   * @see #worker
   */
  public void setWorker(BBasicWorker v) { set(worker, v, null); }

////////////////////////////////////////////////////////////////
// Property "socketConnectionTimeout"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code socketConnectionTimeout} property.
   * Define the socket connection timeout.
   * @see #getSocketConnectionTimeout
   * @see #setSocketConnectionTimeout
   */
  public static final Property socketConnectionTimeout = newProperty(0, BRelTime.makeSeconds(30), BFacets.make(BFacets.make(BFacets.SHOW_MILLISECONDS, BBoolean.TRUE), BFacets.MIN, BRelTime.makeSeconds(3)));
  
  /**
   * Get the {@code socketConnectionTimeout} property.
   * Define the socket connection timeout.
   * @see #socketConnectionTimeout
   */
  public BRelTime getSocketConnectionTimeout() { return (BRelTime)get(socketConnectionTimeout); }
  
  /**
   * Set the {@code socketConnectionTimeout} property.
   * Define the socket connection timeout.
   * @see #socketConnectionTimeout
   */
  public void setSocketConnectionTimeout(BRelTime v) { set(socketConnectionTimeout, v, null); }

////////////////////////////////////////////////////////////////
// Property "maxFailsUntilDown"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code maxFailsUntilDown} property.
   * Define the maximum fails response until the network will be consider down.
   * @see #getMaxFailsUntilDown
   * @see #setMaxFailsUntilDown
   */
  public static final Property maxFailsUntilDown = newProperty(0, 2, BFacets.makeInt(2, 5));
  
  /**
   * Get the {@code maxFailsUntilDown} property.
   * Define the maximum fails response until the network will be consider down.
   * @see #maxFailsUntilDown
   */
  public int getMaxFailsUntilDown() { return getInt(maxFailsUntilDown); }
  
  /**
   * Set the {@code maxFailsUntilDown} property.
   * Define the maximum fails response until the network will be consider down.
   * @see #maxFailsUntilDown
   */
  public void setMaxFailsUntilDown(int v) { setInt(maxFailsUntilDown, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEasyMLNetwork.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  /**
   * Constructor.
   */
  public BEasyMLNetwork()
  {
    setFlags(upload, Flags.HIDDEN);
    setFlags(download, Flags.HIDDEN);
  }
  
////////////////////////////////////////////////////////////////
// DeviceNetwork
////////////////////////////////////////////////////////////////

  /**
   * Return device type.
   */
  public Type getDeviceType()
  {
    return BEasyMLServer.TYPE;
  }

  /**
   * Returns folder type.
   */
  public Type getDeviceFolderType()
  {
    return BEasyMLServerFolder.TYPE;
  }

////////////////////////////////////////////////////////////////
//Lifecycle
////////////////////////////////////////////////////////////////
 
  /**
   * Callback when network is started.
   */
  public final void started() throws Exception
  {
    super.started();
    
    String name = getName();
    if (!SlotPath.isValidName(name))
      name = SlotPath.escape(name);
    if (log == null)
      log = getLog();
    synchronized (log)
    {
      log = Log.getLog(name);
    }
  }

  /**
   * Register this component under "BBasicNetwork.class".
   */
  public Type[] getServiceTypes()
  {
    return new Type[] { getType() };
  }

  public void serviceStarted() throws Exception
  {
    subscriber = new NameSubscriber(this);
    subscriber.subscribe(getParent().asComponent());
  }

  public void serviceStopped() throws Exception
  {
    subscriber.unsubscribeAll();
    subscriber = null;
  }

////////////////////////////////////////////////////////////////
//Ping
////////////////////////////////////////////////////////////////

  /**
   * Post a ping request
   */
  public final IFuture postPing()
  {
    doPing();
    return null;
  }

  /**
   * Callback when network is ping.
   */
  public final void doPing()
  {
    if (isFatalFault())
      return;
    
    try
    { 
      // get the license Feature
      Feature feature = getLicenseFeature();
      if (feature == null)
        return;
      
      // do basic check to see if driver even works
      feature.check();
    }
    
    catch(Exception e)
    {
      configFatal("Unlicensed: " + e);
    }
  }

////////////////////////////////////////////////////////////////
//Log
////////////////////////////////////////////////////////////////
  
  public Log getLog()
  {
    String name = getName();
    if (!SlotPath.isValidName(name))
      name = SlotPath.escape(name);
    return Log.getLog(name);
  }
  
  protected void updateLog()
  {
    String name = getName();
    if (!SlotPath.isValidName(name))
      name = SlotPath.escape(name);
    Log log = Log.getLog(name);
    if (this.log == null)
      this.log = getLog();
    synchronized(this.log)
    {
      if (this.log != null)
      {
        log.setSeverity(this.log.getSeverity());
        if (!log.getLogName().equals(this.log.getLogName()))
          Log.deleteLog(this.log.getLogName());
      }
      this.log = log;
    }
  }
  
  protected void processNameSubscriberEvent(BComponentEvent event)
  {
    try
    {
      if (event.getId() == BComponentEvent.PROPERTY_RENAMED && event.getSlot().equals(getPropertyInParent()))
        updateLog();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
  
  protected Subscriber getNameSubscriber()
  {
    return subscriber;
  }
  
  private class NameSubscriber
    extends Subscriber
  {
    public void event(BComponentEvent event)
    {
      network.processNameSubscriberEvent(event);
    }

    public NameSubscriber(BEasyMLNetwork network)
    {
      this.network = network;
    }
    
    private BEasyMLNetwork network;
  }
  
////////////////////////////////////////////////////////////////
//BLoadableNetwork Overrides
////////////////////////////////////////////////////////////////

  /**
   * Async tasks are pushed onto the asynchronous worker queue by default.
   */
  public final IFuture postAsync(Runnable r)
  {
    return post(r);
  }

////////////////////////////////////////////////////////////////
//Posting to Worker Queues
////////////////////////////////////////////////////////////////

  /**
   * Post the runnable task to the asynchronous worker queue. This queue is intended to handle
   * asynchronous operations, such as learns, that may also post message requests to the dispatcher.
   */
  public final IFuture post(Runnable r)
  {
    return getWorker().post(r);
  }

////////////////////////////////////////////////////////////////
// Licensing
////////////////////////////////////////////////////////////////  

  /**
   * Return the license feature
   */
  public final Feature getLicenseFeature()
  {
    return null;
  }

////////////////////////////////////////////////////////////////
//Attributes
////////////////////////////////////////////////////////////////

  private Log log = null;
  protected Subscriber subscriber;
}