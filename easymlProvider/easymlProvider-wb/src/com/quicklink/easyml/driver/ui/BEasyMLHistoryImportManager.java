/*
 * Copyright 2014 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml.driver.ui;

import javax.baja.driver.history.BHistoryDeviceExt;
import javax.baja.driver.ui.history.ArchiveManagerController;
import javax.baja.driver.ui.history.ArchiveModel;
import javax.baja.driver.ui.history.BHistoryImportManager;
import javax.baja.driver.ui.history.HistoryLearn;
import javax.baja.driver.ui.history.ImportModel;
import javax.baja.gx.BImage;
import javax.baja.history.BCollectionInterval;
import javax.baja.history.BHistoryId;
import javax.baja.job.BJob;
import javax.baja.nre.annotations.AgentOn;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.util.Array;
import javax.baja.sys.BComplex;
import javax.baja.sys.BComponent;
import javax.baja.sys.BObject;
import javax.baja.sys.BRelTime;
import javax.baja.sys.BString;
import javax.baja.sys.BValue;
import javax.baja.sys.Context;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.ui.BWidget;
import javax.baja.ui.CommandArtifact;
import javax.baja.util.Lexicon;
import javax.baja.workbench.BWbEditor;
import javax.baja.workbench.mgr.BAbstractManager;
import javax.baja.workbench.mgr.MgrColumn;
import javax.baja.workbench.mgr.MgrController;
import javax.baja.workbench.mgr.MgrEditRow;
import javax.baja.workbench.mgr.MgrLearn;
import javax.baja.workbench.mgr.MgrState;
import javax.baja.workbench.mgr.MgrTypeInfo;
import javax.baja.workbench.mgr.folder.FolderState;

import com.quicklink.easyml.driver.BEasyMLServer;
import com.quicklink.easyml.driver.history.BEasyMLHistoryFolder;
import com.quicklink.easyml.driver.history.BEasyMLHistoryImport;
import com.quicklink.easyml.driver.learn.BEasyMLHistoryEntry;
import com.tridium.workbench.job.BJobBar;

/**
 * BEasyMLHistoryImportManager - Insert description here.
 *
 * @author    Alessandro Gastaldello
 * @creation  18 July 2023
 * @version   $Revision: 1.3 $
 * @since     Baja 1.0
 *
 */
@NiagaraType(
  agent =   @AgentOn(
    types = "easymlProvider:EasyMLHistoryServerExt"
  )
)
public class BEasyMLHistoryImportManager
  extends BHistoryImportManager
{
  
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.easyml.driver.ui.BEasyMLHistoryImportManager(1527423924)1.0$ @*/
/* Generated Fri May 26 23:22:32 CEST 2023 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEasyMLHistoryImportManager.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/


////////////////////////////////////////////////////////////////
//Constructor
////////////////////////////////////////////////////////////////

  public BEasyMLHistoryImportManager()
  {
  }

////////////////////////////////////////////////////////////////
//Support
////////////////////////////////////////////////////////////////

  protected ImportModel makeImportModel() { return new EasyMLImportModel(this); }
  protected MgrController makeController() { return new EasyMLImportController(this); }
  protected MgrLearn makeLearn() { return new EasyMLImportLearn(this); }
  protected MgrState makeState() { return new EasyMLImportState(); }

  private BEasyMLServer getEasyMLServer()
  {    
    BHistoryDeviceExt folder = (BHistoryDeviceExt)getCurrentValue();
    return (BEasyMLServer)folder.getDevice();
  }

////////////////////////////////////////////////////////////////
//Model
////////////////////////////////////////////////////////////////
   
   class EasyMLImportModel
     extends ImportModel
   {
     EasyMLImportModel(BHistoryImportManager manager) { super(manager); }
  
     public MgrTypeInfo[] getNewTypes()
     {
       return MgrTypeInfo.makeArray(BEasyMLHistoryImport.TYPE);
     }
     
     public Type[] getIncludeTypes()
     {
       return new Type[] { BEasyMLHistoryImport.TYPE, BEasyMLHistoryFolder.TYPE };
     }
     
     public Type getFolderType()
     {
       return BEasyMLHistoryFolder.TYPE;
     }
     
     protected MgrColumn[] makeColumns()
     {
       Array<MgrColumn> colArray = new Array<MgrColumn>(MgrColumn.class);

       MgrColumn[] cols = super.makeColumns();
       for (int i=0; i<cols.length; ++i)
       {
         colArray.add(cols[i]);
         if (i == 0)
         {
           colArray.add(colHistoryType);
           colArray.add(colInterval);           
         }
       }
//       MgrColumn[] mgrCol = super.makeColumns();
//       MgrColumn colCapacity = mgrCol[mgrCol.length - 2];
//       MgrColumn colRoll = mgrCol[mgrCol.length - 1];
       colArray.add(colFacets);
       colArray.add(colSystemTags);
       return (MgrColumn[])colArray.trim();
     }
   }

////////////////////////////////////////////////////////////////
// Controller
////////////////////////////////////////////////////////////////
  
  class EasyMLImportController
    extends ArchiveManagerController
  {
    EasyMLImportController(BHistoryImportManager mgr) { super(mgr); }

    public CommandArtifact doDiscover(Context cx)    
      throws Exception
    {
      learnMode.setSelected(true);
      super.doDiscover(cx);
      getLearn().setJob(getEasyMLServer().submitHistoryDiscoveryJob());
      return null;
    }
  }
  
////////////////////////////////////////////////////////////////
//Learn
////////////////////////////////////////////////////////////////
  
  private class EasyMLImportLearn
    extends HistoryLearn
  {
    public EasyMLImportLearn(BHistoryImportManager manager)
    {
      super(manager);
    }
    
    /**
     * Override the null!
     */
    protected BWidget makeJobBar()
    {
      return new BJobBar();
    }
    
    protected MgrColumn[] makeColumns()
    {
      return new MgrColumn[] {
          new MgrColumn.Prop(BEasyMLHistoryEntry.historyLabel),
          new MgrColumn.Prop(BEasyMLHistoryEntry.historyType),
          new MgrColumn.Prop(BEasyMLHistoryEntry.historyDevice),
          new MgrColumn.Prop(BEasyMLHistoryEntry.historyName),
          new MgrColumn.Prop(BEasyMLHistoryEntry.historyInterval),
          new MgrColumn.Prop(BEasyMLHistoryEntry.historyUnits)
      };
    }
    
    public MgrTypeInfo[] toTypes(Object dis)
    {
      return getModel().getNewTypes();
    }
  
    public void toRow(Object discovery, MgrEditRow row)
      throws Exception
    {
      // when the device is moved to the other table we now copy the information across to the other object
      BEasyMLHistoryEntry entry = (BEasyMLHistoryEntry)discovery;

      BHistoryId historyId = BHistoryId.make(entry.getHistoryDevice(), entry.getHistoryName());

      // set the default column data
      row.setDefaultName(entry.getName());
      row.setCell(((ArchiveModel)getManager().getModel()).idCol, historyId);
      
      colHistoryType.save(row, BString.make(entry.getHistoryType()), getCurrentContext());
      
      if (entry.getHistoryInterval().equals("irregular"))
        colInterval.save(row, BCollectionInterval.IRREGULAR, getCurrentContext());
      else
        colInterval.save(row, BCollectionInterval.make(BRelTime.make(Long.parseLong(entry.getHistoryInterval()))), getCurrentContext());
    }

    public BImage getIcon(Object dis)
    {
      return historyIcon;
    }
    
    public void jobComplete(BJob job)
    {
      super.jobComplete(job);
      updateDiscoveryRows(job);
    }
    
    public boolean isMatchable(Object dis, BComponent db)
    {
      BEasyMLHistoryEntry entry = (BEasyMLHistoryEntry)dis;
      return entry.is(db);
    }
    
    public boolean isExisting(Object dis, BComponent db)
    {
      BEasyMLHistoryEntry entry = (BEasyMLHistoryEntry)dis;
      return entry.is(db);
    }
  }
  
////////////////////////////////////////////////////////////////
//State
////////////////////////////////////////////////////////////////
  
  private class EasyMLImportState
    extends FolderState
  {
    protected void saveForOrd(BAbstractManager manager)
    {
      super.saveForOrd(manager);
      lastLearn = ((BEasyMLHistoryImportManager)manager).lastLearn;
    }
    
    protected void restoreForOrd(BAbstractManager manager)
    {
      super.restoreForOrd(manager);
  
      // restore the last learn nodes
      if (lastLearn != null)
      {
        ((BEasyMLHistoryImportManager)manager).lastLearn = lastLearn;
        
        // populate the learn table
        manager.getLearn().updateRoots(((BEasyMLHistoryImportManager)manager).lastLearn);
      }
    }
  
    private EasyMLImportState()
    {
    }
    
    BObject lastLearn[];
  }
  
////////////////////////////////////////////////////////////////
//Discovery methods
////////////////////////////////////////////////////////////////
  
  private void updateDiscoveryRows(BComponent event)
  {
    BJob learnJob = getLearn().getJob();
    if (learnJob != null)
    {
      lastLearn = (BEasyMLHistoryEntry[])event.getChildren(BEasyMLHistoryEntry.class);       
      for (int i=0; i<lastLearn.length; ++i)
        ((BEasyMLHistoryEntry)lastLearn[i]).loadSlots();
      getLearn().updateRoots(lastLearn);
    }
  }
  
////////////////////////////////////////////////////////////////
//MgrColumn
////////////////////////////////////////////////////////////////
  
  static class HistorySystemTagsProp
    extends MgrColumn.Prop
  {    
    public HistorySystemTagsProp(String name, Property property, int flags)
    {
      super(name, property, flags);
      this.myProp = property;
    }
    
    public HistorySystemTagsProp(Property property, int flags)
    {
      super(property, flags);
      this.myProp = property;
    }
    
    public HistorySystemTagsProp(Property property)
    {
      super(property);
      this.myProp = property;
    }
    
    public Object get(Object obj)
    {
      try
      {
        if (((BComplex)obj).get(this.myProp) != null && !Flags.isHidden((BComplex)obj, this.myProp))
          return super.get(obj);
      }
      catch (Exception e) {}
      return BString.DEFAULT;
    }
    
    public BValue load(MgrEditRow row)
    {
      BComponent target = row.getTarget();
      try
      {
        if (((BComplex)target).get(this.myProp) != null && !Flags.isHidden((BComplex)target, this.myProp))
          return super.load(row);
      }
      catch (Exception e) {}
      return BString.DEFAULT;
    }
    
    public void save(MgrEditRow row, BValue value, Context context)
    {
      BComponent target = row.getTarget();
      try
      {
        if (((BComplex)target).get(this.myProp) != null && !Flags.isHidden((BComplex)target, this.myProp))
          super.save(row, value, context);
      }
      catch (Exception e) {}
    }
    
    public BWbEditor toEditor(MgrEditRow[] rows, int colIndex, BWbEditor editor)
    {
      try
      {
        for (int i=0; i<rows.length; ++i)
        {
          BComponent target = rows[i].getTarget();
          if (((BComplex)target).get(this.myProp) != null && Flags.isHidden((BComplex)target, this.myProp))
            return null;
        }
        return super.toEditor(rows, colIndex, editor);
      }
      catch (Exception e) {}
      return null;
    }
  
    private Property myProp = null;
  }
  
////////////////////////////////////////////////////////////////
//Fields
////////////////////////////////////////////////////////////////
  
  private MgrColumn colHistoryType = new MgrColumn.Prop(BEasyMLHistoryImport.historyType, MgrColumn.READONLY);
  private MgrColumn colInterval    = new MgrColumn.Prop(BEasyMLHistoryImport.interval, MgrColumn.READONLY);
  private MgrColumn colFacets      = new MgrColumn.Prop(BEasyMLHistoryImport.facets, MgrColumn.EDITABLE | MgrColumn.UNSEEN);
  private MgrColumn colSystemTags  = new HistorySystemTagsProp(BEasyMLHistoryImport.systemTags, MgrColumn.EDITABLE | MgrColumn.UNSEEN);

////////////////////////////////////////////////////////////////
// Attributes
////////////////////////////////////////////////////////////////

  static Lexicon lex = Lexicon.make(BEasyMLHistoryImportManager.class);
  private BObject lastLearn[] = new BObject[0];
  private static final BImage historyIcon = BImage.make("module://icons/x16/history.png");
}