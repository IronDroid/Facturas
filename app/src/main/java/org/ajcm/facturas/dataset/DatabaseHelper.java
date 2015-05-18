package org.ajcm.facturas.dataset;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.ajcm.facturas.dataset.models.QRFactura;
import org.ajcm.facturas.utils.DBUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by jhonny on 13-05-15.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private String databaseName;

    public DatabaseHelper(Context context, String databaseName, int databaseVersion) {
        super(context, databaseName, null, databaseVersion);
        this.databaseName = databaseName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBUtils.classToSQL(QRFactura.class));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void copydatabase(Context context) throws IOException {
        this.getReadableDatabase();
        InputStream input = context.getAssets().open(this.databaseName);
        OutputStream output = new FileOutputStream("/data/data/" + context.getPackageName() + "/databases/" + this.databaseName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0) {
            output.write(buffer, 0, length);
        }
        output.flush();
        output.close();
        input.close();
    }

    public boolean checkDataBase(Context context) {
        SQLiteDatabase checkDB = null;
        String destPath = "/data/data/" + context.getPackageName() + "/databases/" + this.databaseName;
        try {
            checkDB = SQLiteDatabase.openDatabase(destPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            return false;
        }
        if (checkDB != null) {
            checkDB.close();
            return true;
        }
        return false;
    }
}
