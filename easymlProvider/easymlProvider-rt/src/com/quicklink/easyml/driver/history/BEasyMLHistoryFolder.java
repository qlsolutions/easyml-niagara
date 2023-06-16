/**
 * Copyright 2023, QuickLink Solutions - All Rights Reserved.
 */
package com.quicklink.easyml.driver.history;

import javax.baja.driver.history.BArchiveFolder;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.nre.annotations.NiagaraType;

/**
 * BEasyMLHistoryFolder - Insert description here.
 *
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.2.2.3 $
 * @since     Baja 1.0
 *
 */
@NiagaraType
public class BEasyMLHistoryFolder
  extends BArchiveFolder
{
  
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.easyml.driver.history.BEasyMLHistoryFolder(2979906276)1.0$ @*/
/* Generated Fri May 26 17:21:05 CEST 2023 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEasyMLHistoryFolder.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  /**
   * Empty constructor.
   */
  public BEasyMLHistoryFolder()
  {
  }
}