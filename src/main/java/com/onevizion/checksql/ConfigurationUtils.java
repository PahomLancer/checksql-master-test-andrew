package com.onevizion.checksql;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

//import org.jdom2.Document;
//import org.jdom2.Element;
//import org.jdom2.filter.Filters;
//import org.jdom2.xpath.XPathExpression;
//import org.jdom2.xpath.XPathFactory;

import com.onevizion.checksql.vo.Configuration;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ConfigurationUtils {

    public static Configuration loadConfiguration(/*Document*/ String doc) {
        return loadConfigurationFromFile(doc);
    }

    private static Configuration loadConfigurationFromFile(/*Document*/ String doc) {
        Configuration configuration = new Configuration();
        //List<String> skipTablesSqlList = new ArrayList<String>();
        //List<String> skipTablesPlSqlList = new ArrayList<String>();

        JSONParser parser = new JSONParser();
        try {
            JSONObject object = (JSONObject) parser.parse(
                    new FileReader(doc));
            JSONArray remote_owner = (JSONArray) object.get("remote_owner");
            String temp = remote_owner.toString().replaceAll("[\\[\\]]", "").replaceAll("\"", "");
            configuration.setRemoteOwner(temp.substring(0, temp.indexOf("/")-1)+temp.substring(temp.indexOf("/")));
            JSONArray local_owner = (JSONArray) object.get("local_owner");
            temp = local_owner.toString().replaceAll("[\\[\\]]", "").replaceAll("\"", "");
            if (temp.trim().length() == 0) {
                configuration.setLocalOwner(null);
            }
            else {
                configuration.setLocalOwner(temp.substring(0, temp.indexOf("/")-1)+temp.substring(temp.indexOf("/")));
            }
            
            
            if (StringUtils.isNotBlank(configuration.getLocalOwner())
                    /*&& StringUtils.isNotBlank(configuration.getLocalUser())*/) {
                configuration.setUseSecondTest(true);
            }
                  
        } catch (IOException | ParseException ex) {
            java.util.logging.Logger.getLogger(CheckSqlApp.class.getName())
                    .log(Level.SEVERE, null, ex);
        } 
        
        //XPathExpression<Element> xpath = XPathFactory.instance().compile("root", Filters.element());
        //for (Element elem : xpath.evaluate(doc)) {
            //configuration.setRemoteOwner(elem.getChildText("remote_owner"));
            //configuration.setRemoteUser(elem.getChildText("remote_user"));
            //configuration.setLocalOwner(elem.getChildText("local_owner"));
            //configuration.setLocalUser(elem.getChildText("local_user"));
            /*if (elem.getChild("sql") != null) {
            	configuration.setEnabledSql(Boolean.parseBoolean(elem.getChild("sql").getChildText("enabled")));
                String skipTablesSql = elem.getChild("sql").getChildText("disable-tables");
                if (StringUtils.isNotBlank(skipTablesSql)) {
                    skipTablesSqlList = Arrays.asList(skipTablesSql.split(","));
                }
                configuration.setSkipTablesSql(skipTablesSqlList);

            }*/
            /*if (elem.getChild("pl_sql") != null) {
            	configuration.setEnabledPlSql(Boolean.parseBoolean(elem.getChild("pl_sql").getChildText("enabled")));
            	String skipTablesPlSql = elem.getChild("pl_sql").getChildText("disable-tables");
                if (StringUtils.isNotBlank(skipTablesPlSql)) {
                    skipTablesPlSqlList = Arrays.asList(skipTablesPlSql.split(","));
                }
                configuration.setSkipTablesPlSql(skipTablesPlSqlList);
            }*/

            //if (StringUtils.isNotBlank(configuration.getLocalOwner())
            //        /*&& StringUtils.isNotBlank(configuration.getLocalUser())*/) {
            //    configuration.setUseSecondTest(true);
            //}
        //}

        return configuration;
    }

}