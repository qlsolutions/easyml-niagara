/**
 * Copyright 2022 QuickLink Solutions, All Rights Reserved.
 */
package com.quicklink.easyml;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.baja.history.BBooleanTrendRecord;
import javax.baja.history.BEnumTrendRecord;
import javax.baja.history.BHistoryConfig;
import javax.baja.history.BHistoryDevice;
import javax.baja.history.BHistoryId;
import javax.baja.history.BHistoryRecord;
import javax.baja.history.BHistoryService;
import javax.baja.history.BHistorySpace;
import javax.baja.history.BIHistory;
import javax.baja.history.BNumericTrendRecord;
import javax.baja.history.BStringTrendRecord;
import javax.baja.history.db.BHistoryDatabase;
import javax.baja.history.db.HistoryDatabaseConnection;
import javax.baja.nre.annotations.NiagaraProperty;
import javax.baja.nre.annotations.NiagaraType;
import javax.baja.nre.util.TextUtil;
import javax.baja.sys.BAbsTime;
import javax.baja.sys.BFacets;
import javax.baja.sys.BIcon;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.units.BUnit;
import javax.baja.util.BTypeSpec;
import javax.baja.web.BWebServlet;
import javax.baja.web.WebOp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tridium.history.BHistory;
import com.tridium.history.BHistoryTimeQuery;
import com.tridium.history.HistoryTableCursor;
import com.tridium.json.JSONArray;
import com.tridium.json.JSONException;
import com.tridium.json.JSONObject;

/**
 * BEasyMLProvider - API Servlet..
 *
 * @author    Alessandro Gastaldello
 * @creation  03 Sep 2022
 * @version   $Revision: 1.0 $
 * @since     Baja 1.0
 *
 */
@NiagaraType
@NiagaraProperty(
  name = "servletName",
  type = "String",
  flags = Flags.HIDDEN,
  override = true,
  defaultValue = "easyml"
)
public class BEasyMLProvider
  extends BWebServlet
{
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $com.quicklink.easyml.BEasyMLProvider(1845218854)1.0$ @*/
/* Generated Sun Sep 04 00:37:18 CEST 2022 by Slot-o-Matic (c) Tridium, Inc. 2012 */

////////////////////////////////////////////////////////////////
// Property "servletName"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the {@code servletName} property.
   * @see #getServletName
   * @see #setServletName
   */
  public static final Property servletName = newProperty(Flags.HIDDEN, "easyml", null);
  
  /**
   * Get the {@code servletName} property.
   * @see #servletName
   */
  public String getServletName() { return getString(servletName); }
  
  /**
   * Set the {@code servletName} property.
   * @see #servletName
   */
  public void setServletName(String v) { setString(servletName, v, null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  @Override
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BEasyMLProvider.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  /**
   * Default constructor.
   */
  public BEasyMLProvider()
  {
  }
  
  @Override
  public void service(WebOp op)
    throws Exception
  {
    String method = TextUtil.toLowerCase(op.getRequest().getMethod());
    if (method.equals("options"))
      doOptions(op);
    else
      super.service(op);
  }
  
  @Override
  public void doGet(WebOp op)
    throws Exception
  {
    enableCors(op);
    preventCaching(op);
    
    String urlPath = op.getRequest().getRequestURI();
    
    // clean the servlet name from the uri
    urlPath = urlPath.substring(getServletName().length() + 1);
    
    // uri sanity check
    if (urlPath.endsWith("/"))
      urlPath = urlPath.substring(0, urlPath.length() - 1);
    
    StringTokenizer st = new StringTokenizer(urlPath, "/");
    int tokens = st.countTokens();
    if (tokens == 0)
      processAbout(op);
    else
    {
      String api = st.nextToken();
      String[] paths = new String[tokens - 1];
      for (int i=0; i<paths.length; ++i)
        paths[i] = st.nextToken();
      
      if (api.equalsIgnoreCase("about"))
        processAbout(op);
      else if (api.equalsIgnoreCase("series"))
        processSeries(op);
      else if (api.equalsIgnoreCase("serie"))
        processSerie(op, paths, false);
      else
        op.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND, "API request non found");         
    }
  }
  
  @Override
  public void doPost(WebOp op) throws Exception
  {
    enableCors(op);
    preventCaching(op);
    
    String urlPath = op.getRequest().getRequestURI();
    
    // clean the servlet name from the uri
    urlPath = urlPath.substring(getServletName().length() + 1);
    
    // uri sanity check
    if (urlPath.endsWith("/"))
      urlPath = urlPath.substring(0, urlPath.length() - 1);
    
    StringTokenizer st = new StringTokenizer(urlPath, "/");
    int tokens = st.countTokens();
    if (tokens == 0)
      op.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND, "API request non found");
    else
    {
      String api = st.nextToken();
      String[] paths = new String[tokens - 1];
      for (int i=0; i<paths.length; ++i)
        paths[i] = st.nextToken();
      
      if (api.equalsIgnoreCase("serie"))
        processSerie(op, paths, true);
      else
        op.getResponse().sendError(HttpServletResponse.SC_NOT_FOUND, "API request non found");  
    }
  }
  
  private void processAbout(WebOp op)
    throws IOException
  {
    JSONObject json = new JSONObject();

    json.put("host_id", Sys.getHostId());
    json.put("version", getType().getModule().getAllModuleInfo()[0].getVendorVersion().toString());
    json.put("type", "Niagara");
    
    // write response
    writeResponse(op, json);
  }
  
  private void processSeries(WebOp op)
    throws IOException
  {
    JSONObject json = new JSONObject();
    JSONArray series = new JSONArray();
    
    BHistoryService service = (BHistoryService)Sys.getService(BHistoryService.TYPE);
    BHistoryDatabase db = service.getDatabase();
    HistoryDatabaseConnection connection = db.getDbConnection(op);

    BHistoryDevice[] devices = db.listDevices();
    for (int i=0; i<devices.length; ++i)
    {
      BHistoryDevice device = devices[i];
      BHistorySpace space = (BHistorySpace)device.getSpace();
      BIHistory[] histories = space.listHistories(device);
      
      for (int j=0; j<histories.length; ++j)
      {
        BHistoryConfig config = histories[j].getConfig();
        String type = getRecordType(config.getRecordType());
        if (type.length() == 0)
          continue;
        
        JSONObject history = new JSONObject();
        history.put("id", config.getId().getDeviceName() + "/" + config.getId().getHistoryName());
        history.put("displayName", histories[j].getDisplayName(op));
        history.put("type", type);
        if (type.equals("numeric"))
        {
          BFacets facets = (BFacets)config.get("valueFacets");
          BUnit units = (BUnit)facets.get("units", BUnit.NULL);
          history.put("units", units.isNull() ? "" : units.getSymbol());
        }
        else
          history.put("units", "");
        history.put("interval", config.getInterval().isIrregular() ? "irregular" : Long.toString(config.getInterval().getInterval().getMillis()));
        history.put("tags", config.getSystemTags().getNames());
        history.put("recordCount", connection.getRecordCount(histories[j]));
        history.put("firstTimestamp", connection.getFirstTimestamp(histories[j]).getMillis());
        history.put("lastTimestamp", connection.getLastTimestamp(histories[j]).getMillis());
        
        series.put(history);
      }
    }
    
    json.put("series", series);
      
    // write response
    writeResponse(op, json);
  }
  
  private void processSerie(WebOp op, String[] paths, boolean post)
    throws IOException
  {
    BAbsTime from = BAbsTime.NULL;
    BAbsTime to = BAbsTime.NULL;
    int limit = Integer.MAX_VALUE;
    
    // decode post parameters
    if (post)
    {
      // Check the content encoding. If it is gzip then wrapper the input stream as a GZIPInputStream.
      String zip = op.getRequest().getHeader("content-encoding");
      InputStream in = op.getRequest().getInputStream();
    
      if (zip != null && zip.indexOf("gzip") != -1)
        in = new GZIPInputStream(in);
      
      StringBuffer buffer = new StringBuffer();
      byte data[] = new byte[4096];
      int i;
      while ((i = in.read(data)) != -1)
        buffer.append(new String(data, 0, i));
      JSONObject params = new JSONObject(buffer.toString());
      if (params.has("limit"))
        limit = params.getInt("limit");
      if (params.has("from"))
        from = BAbsTime.make(params.getLong("from"));
      if (params.has("to"))
        to = BAbsTime.make(params.getLong("to"));        
    }
    
    if (paths.length >= 2)
    {
      BHistoryId historyId = BHistoryId.make(paths[0], paths[1]);
      BHistoryService service = (BHistoryService) Sys.getService(BHistoryService.TYPE);
      BHistoryDatabase db = service.getDatabase();
      HistoryDatabaseConnection connection = db.getDbConnection(op);
      
      BIHistory history = connection.getHistory(historyId);
      if (history != null)
      {
        BHistoryConfig config = history.getConfig();
        String type = getRecordType(config.getRecordType());
        if (type.length() != 0)
        {
          JSONObject json = new JSONObject();
          
          json.put("id", config.getId().getDeviceName() + "/" + config.getId().getHistoryName());
          json.put("displayName", history.getDisplayName(op));
          json.put("type", type);
          if (type.equals("numeric"))
          {
            BFacets facets = (BFacets)config.get("valueFacets");
            BUnit units = (BUnit)facets.get("units", BUnit.NULL);
            json.put("units", units.isNull() ? "" : units.getSymbol());
          }
          else
            json.put("units", "");
          json.put("interval", config.getInterval().isIrregular() ? "irregular" : Long.toString(config.getInterval().getInterval().getMillis()));
          json.put("tags", config.getSystemTags().getNames());
          json.put("recordCount", connection.getRecordCount(history));
          json.put("firstTimestamp", connection.getFirstTimestamp(history).getMillis());
          json.put("lastTimestamp", connection.getLastTimestamp(history).getMillis());

          // write response
          if (post)
          {
            if (from.isAfter(to))
              op.getResponse().sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid timerange interval.");
            else
            {
              BHistoryTimeQuery timeQuery = new BHistoryTimeQuery((BHistory)history, from, to, false);
              HistoryTableCursor cursor = (HistoryTableCursor)timeQuery.cursor();
              
              int counter = 0;
              JSONArray records = new JSONArray();
              while (cursor.next())
              {
                ++counter;
                if (limit != 0 && counter > limit)
                  break;
                
                JSONObject record = new JSONObject();
                BHistoryRecord historyrecord = (BHistoryRecord)cursor.get();
                record.put("timestamp", historyrecord.getTimestamp().getMillis());
                
                // add the value field based on history type
                if (historyrecord.getType().is(BStringTrendRecord.TYPE))
                  record.put("value", ((BStringTrendRecord)historyrecord).getValue());
                else if (historyrecord.getType().is(BBooleanTrendRecord.TYPE))
                  record.put("value", Boolean.toString(((BBooleanTrendRecord)historyrecord).getValue()));
                else if (historyrecord.getType().is(BEnumTrendRecord.TYPE))
                  record.put("value", Integer.toString(((BEnumTrendRecord)historyrecord).getValue().getOrdinal()));
                else if (historyrecord.getType().is(BNumericTrendRecord.TYPE))
                  record.put("value", Double.toString(((BNumericTrendRecord)historyrecord).getValue()));
                record.put("tags", "");

                records.put(record);
              }
              
              json.put("data", records);
              writeResponse(op, json);
            }
          }
          else
            writeResponse(op, json);
        }
        else
          op.getResponse().sendError(HttpServletResponse.SC_BAD_REQUEST, "Serie type not supported.");
      }
      else
        op.getResponse().sendError(HttpServletResponse.SC_BAD_REQUEST, "Serie not found.");
    }
    else
      op.getResponse().sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid serie id.");
  }
  
  private void writeResponse(WebOp op, JSONObject json)
    throws JSONException, IOException
  {
    String encoding = op.getRequest().getHeader("Accept-Encoding"); 
    boolean supportsGzip = false;
    if (encoding != null && encoding.toLowerCase().indexOf("gzip") > -1)
      supportsGzip = true;
    if (supportsGzip)
    {
      op.setContentType("application/json");
      op.getResponse().setHeader("Content-Encoding", "gzip");
      GZIPOutputStream gzos = new GZIPOutputStream(op.getResponse().getOutputStream());
      gzos.write(json.toString(2).getBytes());
      gzos.flush();
      gzos.close();
    }
    else
    {
      op.setContentType("application/json");
      PrintWriter writer = op.getWriter();
      writer.println(json.toString(2));
      writer.flush();
    }
  }

  private void doOptions(WebOp op)
    throws Exception
  {
    enableCors(op);
    HttpServletResponse response = op.getResponse();
    response.setStatus(HttpServletResponse.SC_OK);
    response.setContentLength(0);
    response.getWriter().close();
  }
  
  private void enableCors(WebOp op)
    throws Exception
  {
    HttpServletRequest request = op.getRequest();
    String value = request.getHeader("origin");
    if (value == null || value.length() == 0)
      return;
    
    HttpServletResponse response = op.getResponse();
    value = TextUtil.replace(TextUtil.replace(value, "\n", "\\n"), "\r", "\\r");
    response.setHeader("Access-Control-Allow-Origin", value);
    response.setHeader("Access-Control-Allow-Credentials", "true");
    response.setHeader("Access-Control-Max-Age", "604800");
    
    value = request.getHeader("access-control-request-headers");
    if (value != null)
    {
      value = TextUtil.replace(TextUtil.replace(value, "\n", "\\n"), "\r", "\\r");
      response.addHeader("Access-Control-Allow-Headers", value);
    }
    
    value = request.getHeader("access-control-request-methods");
    if (value != null)
    {
      value = TextUtil.replace(TextUtil.replace(value, "\n", "\\n"), "\r", "\\r");
      response.addHeader("Access-Control-Allow-Methods", value);
    }
  }

  protected void preventCaching(WebOp op)
  {
    HttpServletResponse response = op.getResponse();
    long time = System.currentTimeMillis();
    response.setDateHeader("Last-Modified", time);
    response.setDateHeader("Expires", time);
    response.setHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
    response.setHeader("Pragma", "no-cache");
  }
  
  private String getRecordType(BTypeSpec recordType)
  {
    String type = "";
    if (recordType.equals(BBooleanTrendRecord.TYPE.getTypeSpec()))
      type = "bool";
    else if (recordType.equals(BNumericTrendRecord.TYPE.getTypeSpec()))
      type = "numeric";
    else if (recordType.equals(BEnumTrendRecord.TYPE.getTypeSpec()))
      type = "enum";
    else if (recordType.equals(BStringTrendRecord.TYPE.getTypeSpec()))
      type = "string";
    return type;
  }
  
  @Override
  public BIcon getIcon()
  {
    return icon;
  }
  
////////////////////////////////////////////////////////////////
//Attributes
////////////////////////////////////////////////////////////////

  private static final BIcon icon = BIcon.std("deviceData.png");
}