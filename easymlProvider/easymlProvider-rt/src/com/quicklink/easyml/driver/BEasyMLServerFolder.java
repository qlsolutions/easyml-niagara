/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver;

import javax.baja.driver.BDeviceFolder;
import javax.baja.sys.BComponent;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.nre.annotations.NiagaraType;

/**
 * BEasyMLServerFolder - Insert description here.
 *
 * @author    Alessandro Gastaldello
 * @creation  06 Mar 2023
 * @version   $Revision: 1.1 $
 * @since     Baja 1.0
 *
 */
@NiagaraType
public class BEasyMLServerFolder
  extends BDeviceFolder
{
  

/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.easyml.driver.BEasyMLServerFolder(2979906276)1.0$ @*/
/* Generated Fri May 26 17:21:05 CEST 2023 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEasyMLServerFolder.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public BEasyMLServerFolder()
  {
  }
  
  public boolean isParentLegal(BComponent parent)
  {
    return (parent instanceof BEasyMLNetwork) || (parent instanceof BEasyMLServerFolder);
  }
}