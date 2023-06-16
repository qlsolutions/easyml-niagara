/*
 * Copyright 2023 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver.ui;

import javax.baja.driver.BDevice;
import javax.baja.driver.ui.device.BDeviceManager;
import javax.baja.driver.ui.device.DeviceController;
import javax.baja.driver.ui.device.DeviceExtsColumn;
import javax.baja.driver.ui.device.DeviceModel;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.Lexicon;
import javax.baja.workbench.mgr.MgrColumn;
import javax.baja.workbench.mgr.MgrController;
import javax.baja.workbench.mgr.MgrModel;
import javax.baja.workbench.mgr.MgrTypeInfo;

import com.quicklink.easyml.driver.BEasyMLServer;
import com.quicklink.easyml.driver.BEasyMLServerFolder;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.annotations.AgentOn;

/**
 * BEasyMLServerManager - Insert description here.
 *
 * @author    Alessandro Gastaldello
 * @creation  18 July 2023
 * @version   $Revision: 1.3 $
 * @since     Baja 1.0
 *
 */
@NiagaraType(
  agent =   @AgentOn(
    types = {"easymlProvider:EasyMLNetwork", "easymlProvider:EasyMLServerFolder"}
  )
)
public class BEasyMLServerManager
  extends BDeviceManager
{
  
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.easyml.driver.ui.BEasyMLServerManager(4159522112)1.0$ @*/
/* Generated Fri May 26 23:22:32 CEST 2023 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEasyMLServerManager.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


////////////////////////////////////////////////////////////////
//Constructor
////////////////////////////////////////////////////////////////

  public BEasyMLServerManager()
  {
  }

////////////////////////////////////////////////////////////////
//Support
////////////////////////////////////////////////////////////////

   protected MgrModel makeModel() { return new EasyMLServerModel(this); }
   protected MgrController makeController() { return new EasyMLServerController(this); }

////////////////////////////////////////////////////////////////
//Model
////////////////////////////////////////////////////////////////
   
   class EasyMLServerModel
     extends DeviceModel
   {
     EasyMLServerModel(BDeviceManager manager) { super(manager); }
                                 
     protected MgrColumn[] makeColumns()
     {
       return cols;
     }

     public Type[] getIncludeTypes()
     {
       return new Type[] { BEasyMLServerFolder.TYPE, BEasyMLServer.TYPE };
     }
     
     /**
      * Get the list of types supported by the new operation. The first entry in the list should be
      * the default type.
      */
     public MgrTypeInfo[] getNewTypes()
     {
       return MgrTypeInfo.makeArray(BEasyMLServer.TYPE);
     }    
   }

////////////////////////////////////////////////////////////////
// Controller
////////////////////////////////////////////////////////////////
  
  class EasyMLServerController
    extends DeviceController
  {
    EasyMLServerController(BDeviceManager mgr) { super(mgr); }
  }

////////////////////////////////////////////////////////////////
// Attributes
////////////////////////////////////////////////////////////////

  static Lexicon lex = Lexicon.make(BEasyMLServerManager.class); 
  
  // base class columns
  MgrColumn colName         = new MgrColumn.Name();
  MgrColumn colType         = new MgrColumn.Type();
  MgrColumn colDeviceExts   = new DeviceExtsColumn(new BEasyMLServer());
  MgrColumn colStatus       = new MgrColumn.Prop(BDevice.status);
  MgrColumn colEnabled      = new MgrColumn.Prop(BDevice.enabled, MgrColumn.EDITABLE | MgrColumn.UNSEEN);
  MgrColumn colHealth       = new MgrColumn.Prop(BDevice.health, 0);

  // DixellDevice specific columns
  MgrColumn colIpAddress    = new MgrColumn.Prop(BEasyMLServer.ipAddress, MgrColumn.EDITABLE);
  MgrColumn colIpPort       = new MgrColumn.Prop(BEasyMLServer.ipPort, MgrColumn.EDITABLE);
  MgrColumn colCredentials  = new MgrColumn.Prop(BEasyMLServer.credentials, MgrColumn.UNSEEN | MgrColumn.EDITABLE);
  MgrColumn colUseSsl       = new MgrColumn.Prop(BEasyMLServer.useSsl, MgrColumn.EDITABLE);
  MgrColumn colHostId       = new MgrColumn.Prop(BEasyMLServer.hostId, MgrColumn.READONLY);
  MgrColumn colApiType      = new MgrColumn.Prop(BEasyMLServer.apiType, MgrColumn.READONLY);
  MgrColumn colApiVersion   = new MgrColumn.Prop(BEasyMLServer.apiVersion, MgrColumn.READONLY);

  MgrColumn[] cols = {
      colName, colType, colDeviceExts,
      colIpAddress, colIpPort, colCredentials, colUseSsl,
      colHostId, colApiType, colApiVersion,
      colStatus, colEnabled, colHealth
  };
}