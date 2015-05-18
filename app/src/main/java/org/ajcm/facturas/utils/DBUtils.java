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
        resSQL = resSQL + tableName + " (_id integer primary key autoincrement";
        Field[] fields = classDB.getDeclaredFields();
        for (Field f: fields){
            Class<?> declaringClass = f.getType();
            String columnName = f.getName();
            String typeData = declaringClass.getSimpleName();
            if (typeData.equalsIgnoreCase("String")){
                typeData = "TEXT";
            } else {
                typeData = "INTEGER";
            }
            resSQL = resSQL + ", "+ columnName + " " + typeData;
        }
        resSQL += ")";
        Log.e("ClassToSQL", resSQL);
        return resSQL;
    }
}
