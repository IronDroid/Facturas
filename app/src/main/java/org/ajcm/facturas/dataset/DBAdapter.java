package org.ajcm.facturas.dataset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jhonny on 13-05-15.
 */
public class DBAdapter {
    private static final String TAG = "DBAdapter";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FacturaDB";
    private Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    private Class<?> classModel;
    private String tableName;

    public DBAdapter(Context context) {
        this.context = context;
        DBHelper = new DatabaseHelper(this.context, DATABASE_NAME, DATABASE_VERSION);
    }

    public void setClassModel(Class<?> classModel) {
        this.classModel = classModel;
        this.tableName = classModel.getSimpleName();
    }

    public String getPath() {
        return db.getPath();
    }

    public ArrayList<ModelAbstract> getAll(ModelAbstract modelAbstract) {
        SQLiteDatabase db = DBHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        Cursor query = db.query(tableName, null, null, null, null, null, null);
        ArrayList<ModelAbstract> listModels = new ArrayList<>();
        while (query.moveToNext()){
            listModels.add(modelAbstract.setData(query));
        }
        query.close();
        db.close();
        return listModels;
    }

    /**
     * Return values for a single row with the specified id
     *
     * @param id The unique id for the row o fetch
     * @return All column values are stored as properties in the ContentValues object
     */
    public ModelAbstract get(long id, ModelAbstract modelAbstract) {
        SQLiteDatabase db = DBHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        Cursor cur = db.query(tableName, null, "_id = " + id, null, null, null, null);
        if (cur.moveToNext()) {
            modelAbstract= modelAbstract.setData(cur);
        }
        cur.close();
        db.close();
        return modelAbstract;
    }

    /**
     * Add a new row to the database table
     *
     * @return The unique id of the newly added row
     */
    public long add(ModelAbstract modelAbstract) {
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        if (db == null) {
            return 0;
        }
        long id = db.insert(tableName, null, modelAbstract.getData());
        db.close();
        return id;
    }

    /**
     * Delete the specified row from the database table. For simplicity reasons, nothing happens if
     * this operation fails.
     *
     * @param id The unique id for the row to delete
     */
    public void delete(long id) {
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.delete(tableName, "_id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    /**
     * Updates a row in the database table with new column values, without changing the unique id of the row.
     * For simplicity reasons, nothing happens if this operation fails.
     *
     * @param id       The unique id of the row to update
     * @param modelAbstract    The data value
     */
    public void update(long id, ModelAbstract modelAbstract) {
        SQLiteDatabase db = DBHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.update(tableName, modelAbstract.getData(), "_id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        DBHelper.close();
    }

    public void loadDB() throws Exception {
        if (!DBHelper.checkDataBase(context)) {
            DBHelper.copydatabase(context);
        }
    }
}
