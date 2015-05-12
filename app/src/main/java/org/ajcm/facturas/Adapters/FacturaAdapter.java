package org.ajcm.facturas.Adapters;

import android.graphics.drawable.Drawable;

import com.android_s14.rvh.DataModel;

/**
 * Created by jhonny on 12-05-15.
 */
public class FacturaAdapter implements DataModel {

    private String name;

    public FacturaAdapter(String name){
        this.name = name;
    }

    @Override
    public String getTextField(int i) {
        return this.name;
    }

    @Override
    public int getTextFieldsNumber() {
        return 1;
    }

    @Override
    public Drawable getImageField(int i) {
        return null;
    }

    @Override
    public int getImageFieldsNumber() {
        return 0;
    }
}
