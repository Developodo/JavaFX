package com.carlosserrano.proyectojavafx.utils;

import com.carlosserrano.proyectojavafx.App;
import com.carlosserrano.proyectojavafx.model.Connection;
import java.util.List;
import java.util.prefs.Preferences;

public class PreferencesUtil {
    public static Connection getPreference(){
        Connection result=null;
        Preferences prefs= Preferences.userNodeForPackage(App.class);
        String nameC=prefs.get("conn", null);
        if(nameC!=null){
            List<Connection> conns=XMLUtil.loadDataXML();
            Connection search=new Connection(nameC);
            int index=conns.indexOf(search);
            if(index>-1){
                result=conns.get(index);
            }else{
                setPreference(null);
            }
        }
        
        return result;
    }
    
    public static void setPreference(String nameC){
        Preferences prefs= Preferences.userNodeForPackage(App.class);
        if(nameC!=null){
            prefs.put("conn",nameC);
        }else{
            prefs.remove("conn");
        }
        
    }
}
