package org.ajcm.facturas.dataset;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by jhonny on 13-05-15.
 */
public interface ModelAbstract {
    public ContentValues getData();
    public ModelAbstract setData(Cursor cursor);
}
