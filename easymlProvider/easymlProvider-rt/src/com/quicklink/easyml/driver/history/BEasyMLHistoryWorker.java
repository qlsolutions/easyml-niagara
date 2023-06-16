/**
 * Copyright 2023, QuickLink Solutions - All Rights Reserved.
 */
package com.quicklink.easyml.driver.history;

import javax.baja.spy.SpyWriter;
import javax.baja.sys.BFacets;
import javax.baja.sys.BInteger;
import javax.baja.sys.Context;
import javax.baja.sys.NotRunningException;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.BThreadPoolWorker;
import javax.baja.util.CoalesceQueue;
import javax.baja.util.IFuture;
import javax.baja.util.ThreadPoolWorker;
import javax.baja.util.Worker;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.annotations.Facet;

/**
 * BEasyMLHistoryWorker manages the queue and worker for 
 * asynchronous operations on a single database.
 *
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.2 $ $Date: 2011/07/26 15:53:54 $
 * @since     Baja 1.0
 */
@NiagaraType
/**
 * the size of the working queue
 */
@NiagaraProperty(
  name = "maxQueueSize",
  type = "int",
  defaultValue = "1000",
  facets = @Facet("BFacets.makeInt(null, 1, Integer.MAX_VALUE)")
)
public class BEasyMLHistoryWorker
  extends BThreadPoolWorker
{ 
  /**
   * Slot for the <code>maxThreads</code> property.
   * Max number of concurrent threads for working.
   * The default is one thread.
   * Each thread uses one TCP Connection to communicate with 
   * the device, so there will be as many connections created 
   * as there are threads.
   * @see javax.baja.util.BThreadPoolWorker#getMaxThreads
   * @see javax.baja.util.BThreadPoolWorker#setMaxThreads
   */
  public static final Property maxThreads = newProperty(0, 1, BFacets.make(BFacets.MIN, BInteger.make(1)));

  
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.easyml.driver.history.BEasyMLHistoryWorker(1605333903)1.0$ @*/
/* Generated Fri May 26 17:21:05 CEST 2023 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "maxQueueSize"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code maxQueueSize} property.
   * the size of the working queue
   * @see #getMaxQueueSize
   * @see #setMaxQueueSize
   */
  public static final Property maxQueueSize = newProperty(0, 1000, BFacets.makeInt(null, 1, Integer.MAX_VALUE));
  
  /**
   * Get the {@code maxQueueSize} property.
   * the size of the working queue
   * @see #maxQueueSize
   */
  public int getMaxQueueSize() { return getInt(maxQueueSize); }
  
  /**
   * Set the {@code maxQueueSize} property.
   * the size of the working queue
   * @see #maxQueueSize
   */
  public void setMaxQueueSize(int v) { setInt(maxQueueSize, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEasyMLHistoryWorker.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

////////////////////////////////////////////////////////////////
// Constructor
////////////////////////////////////////////////////////////////

  public BEasyMLHistoryWorker()
  {
    super();
  }

  public BEasyMLHistoryWorker(int maxThreads)
  {                                      
    super(maxThreads);
  }                           
  
////////////////////////////////////////////////////////////////
// BWorker
////////////////////////////////////////////////////////////////
  
  /**
   * Post an action to be run asynchronously.
   */
  public IFuture postAsync(Runnable r)
  {         
    if (!isRunning() || queue == null)
      throw new NotRunningException();
    queue.enqueue(r);
    return null;
  }

  /**
   * Start running this task.
   */
  public Worker getWorker()
  {        
    if (worker == null) 
    {
      // Issue 8925 - Expose the max queue size as a property
      // so that the user can adjust it as needed.
      queue = new CoalesceQueue(getMaxQueueSize());
      worker = new ThreadPoolWorker(queue);
    }
    return worker;
  }

  protected String getWorkerThreadName()
  {                
    return "EasyMLHistoryWorker:" + getParent().getName();
  }

////////////////////////////////////////////////////////////////
// BComponent Overrides
////////////////////////////////////////////////////////////////

  /**
   * Callback when a property (or possibly a ancestor of
   * that property) is modified on this component.
   */
  public void changed(Property property, Context context)
  {
    super.changed(property, context);
    
    // Issue 8925 - Since we now expose the max queue size as a property,
    // trap user changes to the property so that we can reinitialize the queue.
    if (isRunning() && property.equals(maxQueueSize) && (queue != null))
    {
      stopWorker();
      queue = null;
      worker = null;
      getWorker();
      startWorker();
    }
  }
  
////////////////////////////////////////////////////////////////
// Spy
////////////////////////////////////////////////////////////////

  public void spy(SpyWriter out)
    throws Exception
  {
    getWorker().spy(out);
  }

////////////////////////////////////////////////////////////////
// Attributes
////////////////////////////////////////////////////////////////
  
  CoalesceQueue queue;
  ThreadPoolWorker worker;
}