package org.ajcm.facturas.utils;

import android.util.Log;

import java.lang.reflect.Field;

/**
 * Created by jhonny on 13-05-15.
 */
public class DBUtils {

    public static String classToSQL(Class<?> classDB){
        String resSQL = "CREATE TABLE ";
        String tableName = classDB.getSimpleName();
        resSQL = resSQL + tableName + " (_id INTEGER PRIMARY KEY AUTOINCREMENT";
        Field[] fields = classDB.getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            Class<?> declaringClass = fields[i].getType();
            String columnName = fields[i].getName();
            String typeData = declaringClass.getSimpleName();
            if (typeData.equalsIgnoreCase("String")){
                typeData = "TEXT";
            } else {
                typeData = "INTEGER";
            }
            resSQL = resSQL + ", "+ columnName + " " + typeData;
        }
        resSQL += ")";
        return resSQL;
    }
}
