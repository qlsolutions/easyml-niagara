/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver.history;

import javax.baja.driver.history.BHistoryDeviceExt;
import javax.baja.nre.annotations.Facet;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.sys.BComponent;
import javax.baja.sys.BFacets;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.quicklink.easyml.driver.BEasyMLServer;

/**
 * BEasyMLHistoryServerExt - Insert description here.
 *
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.1 $
 * @since     Baja 1.0
 *
 */
@NiagaraType
/**
 * Define the records limit per request during the import.
 */
@NiagaraProperty(
  name = "requestRecords",
  type = "int",
  defaultValue = "1000",
  facets = @Facet("BFacets.makeInt(1, Integer.MAX_VALUE)")
)
public class BEasyMLHistoryServerExt
  extends BHistoryDeviceExt
{
  
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.easyml.driver.history.BEasyMLHistoryServerExt(2885894335)1.0$ @*/
/* Generated Thu Jun 08 13:48:22 CEST 2023 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "requestRecords"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code requestRecords} property.
   * Define the records limit per request during the import.
   * @see #getRequestRecords
   * @see #setRequestRecords
   */
  public static final Property requestRecords = newProperty(0, 1000, BFacets.makeInt(1, Integer.MAX_VALUE));
  
  /**
   * Get the {@code requestRecords} property.
   * Define the records limit per request during the import.
   * @see #requestRecords
   */
  public int getRequestRecords() { return getInt(requestRecords); }
  
  /**
   * Set the {@code requestRecords} property.
   * Define the records limit per request during the import.
   * @see #requestRecords
   */
  public void setRequestRecords(int v) { setInt(requestRecords, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEasyMLHistoryServerExt.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public BEasyMLHistoryServerExt()
  {
  }
  
  public boolean isParentLegal(BComponent parent)
  {
    return parent instanceof BEasyMLServer;
  }
  
  public Type getImportDescriptorType()
  {
    return BEasyMLHistoryImport.TYPE;
  }
  
  public Type getArchiveFolderType()
  {
    return BEasyMLHistoryFolder.TYPE;
  }
}