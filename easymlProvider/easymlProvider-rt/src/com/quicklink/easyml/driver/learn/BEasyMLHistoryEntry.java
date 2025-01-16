/**
 * Copyright 2023, QuickLink Solutions - All Rights Reserved.
 */
package com.quicklink.easyml.driver.learn;

import javax.baja.naming.SlotPath;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.BEnumRange;
import javax.baja.sys.BFacets;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.units.BUnit;

import com.quicklink.easyml.driver.history.BEasyMLHistoryImport;
import com.tridium.json.JSONArray;
import com.tridium.json.JSONObject;

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
 * Define the history facets.
 */
@NiagaraProperty(
  name = "historyFacets",
  type = "BFacets",
  defaultValue = "BFacets.NULL",
  flags = Flags.READONLY
)
/**
 * Define the history tags.
 */
@NiagaraProperty(
  name = "historyTags",
  type = "String",
  defaultValue = "",
  flags = Flags.READONLY
)
public class BEasyMLHistoryEntry
  extends BComponent
{
  
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.easyml.driver.learn.BEasyMLHistoryEntry(1356987367)1.0$ @*/
/* Generated Thu Jan 16 08:34:02 CET 2025 by Slot-o-Matic (c) Tridium, Inc. 2012 */

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
// Property "historyFacets"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historyFacets} property.
   * Define the history facets.
   * @see #getHistoryFacets
   * @see #setHistoryFacets
   */
  public static final Property historyFacets = newProperty(Flags.READONLY, BFacets.NULL, null);
  
  /**
   * Get the {@code historyFacets} property.
   * Define the history facets.
   * @see #historyFacets
   */
  public BFacets getHistoryFacets() { return (BFacets)get(historyFacets); }
  
  /**
   * Set the {@code historyFacets} property.
   * Define the history facets.
   * @see #historyFacets
   */
  public void setHistoryFacets(BFacets v) { set(historyFacets, v, null); }

////////////////////////////////////////////////////////////////
// Property "historyTags"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code historyTags} property.
   * Define the history interval.
   * @see #getHistoryTags
   * @see #setHistoryTags
   */
  public static final Property historyTags = newProperty(Flags.READONLY, "", null);
  
  /**
   * Get the {@code historyTags} property.
   * Define the history interval.
   * @see #historyTags
   */
  public String getHistoryTags() { return getString(historyTags); }
  
  /**
   * Set the {@code historyTags} property.
   * Define the history interval.
   * @see #historyTags
   */
  public void setHistoryTags(String v) { setString(historyTags, v, null); }

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
  
  public BEasyMLHistoryEntry(String label, String type, String device, String name, String interval, String units, JSONArray range, JSONArray tags)
  {
    setHistoryLabel(label);
    setHistoryType(type);
    setHistoryDevice(device);
    setHistoryName(name);
    setHistoryInterval(interval);
    
    String tagList = "";
    for (int i=0; i<tags.length(); ++i)
    {
      if (tagList.length() > 0)
        tagList += ", ";
      tagList += tags.getString(i);
    }
    setHistoryTags(tagList);
    
    BFacets facets = BFacets.NULL;
    try
    {
      if (type.equals("numeric"))
      {
        if (units.length() > 0)
          facets = BFacets.make(BFacets.UNITS, BUnit.getUnit(units));
      }
      else if (type.equals("enum"))
      {
        int[] ordinals = new int[range.length()];
        String[] options = new String[range.length()];
        for (int i=0; i<range.length(); ++i)
        {
          JSONObject obj = range.getJSONObject(i);
          ordinals[i] = obj.getInt("ordinal");
          options[i] = SlotPath.escape(obj.getString("tag"));
        }
        
        facets = BFacets.makeEnum(BEnumRange.make(ordinals, options));
      }
      else if (type.equals("bool"))
      {
        String trueText = "true";
        String falseText = "false";
        for (int i=0; i<range.length(); ++i)
        {
          JSONObject obj = range.getJSONObject(i);
          if (obj.getInt("ordinal") == 0)
            falseText = obj.getString("tag");
          if (obj.getInt("ordinal") == 1)
            trueText = obj.getString("tag");
        }
        
        facets = BFacets.makeBoolean(trueText, falseText);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    setHistoryFacets(facets);
  }
  
  public final boolean is(BComponent component)
  {
    if (component instanceof BEasyMLHistoryImport)
      return ((BEasyMLHistoryImport) component).getHistoryId().getDeviceName().equals(getHistoryDevice()) &&
          ((BEasyMLHistoryImport) component).getHistoryId().getHistoryName().equals(getHistoryName());
    return false;
  }
}