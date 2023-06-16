/**
 * Copyright 2023, QuickLink Solutions - All Rights Reserved.
 */
package com.quicklink.easyml.driver.learn;

import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.quicklink.easyml.driver.history.BEasyMLHistoryImport;

/**
 * BEasyMLHistoryEntry - Insert description here.
 *
 * @author    Alessandro Gastaldello
 * @creation  26 May 2023
 * @version   $Revision: 1.2 $
 * @since     Baja 1.0
 *
 */
@NiagaraType
/**
 * Define the history label.
 */
@NiagaraProperty(
  name = "historyLabel",
  type = "String",
  defaultValue = "",
  flags = Flags.READONLY
)
/**
 * Define the history type.
 */
@NiagaraProperty(
  name = "historyType",
  type = "String",
  defaultValue = "",
  flags = Flags.READONLY
)
/**
 * Define the history device.
 */
@NiagaraProperty(
  name = "historyDevice",
  type = "String",
  defaultValue = "",
  flags = Flags.READONLY
)
/**
 * Define the history name.
 */
@NiagaraProperty(
  name = "historyName",
  type = "String",
  defaultValue = "",
  flags = Flags.READONLY
)
/**
 * Define the history interval.
 */
@NiagaraProperty(
  name = "historyInterval",
  type = "String",
  defaultValue = "",
  flags = Flags.READONLY
)
/**
 * Define the history units.
 */
@NiagaraProperty(
  name = "historyUnits",
  type = "String",
  defaultValue = "",
  flags = Flags.READONLY
)
public class BEasyMLHistoryEntry
  extends BComponent
{
  
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.easyml.driver.learn.BEasyMLHistoryEntry(317932803)1.0$ @*/
/* Generated Sun May 28 23:10:42 CEST 2023 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "historyLabel"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historyLabel} property.
   * Define the history label.
   * @see #getHistoryLabel
   * @see #setHistoryLabel
   */
  public static final Property historyLabel = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code historyLabel} property.
   * Define the history label.
   * @see #historyLabel
   */
  public String getHistoryLabel() { return getString(historyLabel); }
  
  /**
   * Set the {@code historyLabel} property.
   * Define the history label.
   * @see #historyLabel
   */
  public void setHistoryLabel(String v) { setString(historyLabel, v, null); }

////////////////////////////////////////////////////////////////
// Property "historyType"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historyType} property.
   * Define the history type.
   * @see #getHistoryType
   * @see #setHistoryType
   */
  public static final Property historyType = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code historyType} property.
   * Define the history type.
   * @see #historyType
   */
  public String getHistoryType() { return getString(historyType); }
  
  /**
   * Set the {@code historyType} property.
   * Define the history type.
   * @see #historyType
   */
  public void setHistoryType(String v) { setString(historyType, v, null); }

////////////////////////////////////////////////////////////////
// Property "historyDevice"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historyDevice} property.
   * Define the history device.
   * @see #getHistoryDevice
   * @see #setHistoryDevice
   */
  public static final Property historyDevice = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code historyDevice} property.
   * Define the history device.
   * @see #historyDevice
   */
  public String getHistoryDevice() { return getString(historyDevice); }
  
  /**
   * Set the {@code historyDevice} property.
   * Define the history device.
   * @see #historyDevice
   */
  public void setHistoryDevice(String v) { setString(historyDevice, v, null); }

////////////////////////////////////////////////////////////////
// Property "historyName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historyName} property.
   * Define the history name.
   * @see #getHistoryName
   * @see #setHistoryName
   */
  public static final Property historyName = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code historyName} property.
   * Define the history name.
   * @see #historyName
   */
  public String getHistoryName() { return getString(historyName); }
  
  /**
   * Set the {@code historyName} property.
   * Define the history name.
   * @see #historyName
   */
  public void setHistoryName(String v) { setString(historyName, v, null); }

////////////////////////////////////////////////////////////////
// Property "historyInterval"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historyInterval} property.
   * Define the history interval.
   * @see #getHistoryInterval
   * @see #setHistoryInterval
   */
  public static final Property historyInterval = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code historyInterval} property.
   * Define the history interval.
   * @see #historyInterval
   */
  public String getHistoryInterval() { return getString(historyInterval); }
  
  /**
   * Set the {@code historyInterval} property.
   * Define the history interval.
   * @see #historyInterval
   */
  public void setHistoryInterval(String v) { setString(historyInterval, v, null); }

////////////////////////////////////////////////////////////////
// Property "historyUnits"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historyUnits} property.
   * Define the history units.
   * @see #getHistoryUnits
   * @see #setHistoryUnits
   */
  public static final Property historyUnits = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code historyUnits} property.
   * Define the history units.
   * @see #historyUnits
   */
  public String getHistoryUnits() { return getString(historyUnits); }
  
  /**
   * Set the {@code historyUnits} property.
   * Define the history units.
   * @see #historyUnits
   */
  public void setHistoryUnits(String v) { setString(historyUnits, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEasyMLHistoryEntry.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  /**
   * Default constructor.
   */
  public BEasyMLHistoryEntry()
  {
  }
  
  public BEasyMLHistoryEntry(String label, String type, String device, String name, String interval, String units)
  {
    setHistoryLabel(label);
    setHistoryType(type);
    setHistoryDevice(device);
    setHistoryName(name);
    setHistoryInterval(interval);
    setHistoryUnits(units);
  }
  
  public final boolean is(BComponent component)
  {
    if (component instanceof BEasyMLHistoryImport)
      return ((BEasyMLHistoryImport) component).getHistoryId().getDeviceName().equals(getHistoryDevice()) &&
          ((BEasyMLHistoryImport) component).getHistoryId().getHistoryName().equals(getHistoryName());
    return false;
  }
}