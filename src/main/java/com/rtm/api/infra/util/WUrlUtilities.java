package com.rtm.api.infra.util;

public class WUrlUtilities 
{
    public static final String BASE_WM_URL = "http://177.44.248.13:8080/WaterManager";
    
    public static final Integer LIMIT = 15;
    public static final String FORMAT = "FORMAT=JSON";
    public static final String LAST_ID = "op=LAST";
    
    public static final String SELECT = "op=SELECT&LIMIT=" + LIMIT.toString() + "&OFFSET=%s&" + FORMAT;
    public static final String INSERT = "?op=INSERT&VENDORID=6018590627095&PRODUCTID=%s&LATITUDE=%s&LONGITUDE=%s&VALUE=%s";
}
