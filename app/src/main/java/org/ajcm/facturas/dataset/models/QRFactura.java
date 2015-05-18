package org.ajcm.facturas.dataset.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.util.Log;

import org.ajcm.facturas.dataset.ModelAbstract;

import java.util.List;

/**
 * Created by jhonny on 13-05-15.
 */
public class QRFactura implements ModelAbstract{
    private String nit;
    private String nombre_razon_social;
    private String numero_factura;
    private String numero_autorizacion;
    private String fecha_emision;
    private String importe_compra;
    private String codigo_control;
    private String fecha_limite_emision;
    private String importe_ice;
    private String importe_ventas;
    private String nit_ndi_ci;
    private String nombre_razon_social_comprador;
    private String extras;

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre_razon_social() {
        return nombre_razon_social;
    }

    public void setNombre_razon_social(String nombre_razon_social) {
        this.nombre_razon_social = nombre_razon_social;
    }

    public String getNumero_factura() {
        return numero_factura;
    }

    public void setNumero_factura(String numero_factura) {
        this.numero_factura = numero_factura;
    }

    public String getNumero_autorizacion() {
        return numero_autorizacion;
    }

    public void setNumero_autorizacion(String numero_autorizacion) {
        this.numero_autorizacion = numero_autorizacion;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getImporte_compra() {
        return importe_compra;
    }

    public void setImporte_compra(String importe_compra) {
        this.importe_compra = importe_compra;
    }

    public String getCodigo_control() {
        return codigo_control;
    }

    public void setCodigo_control(String codigo_control) {
        this.codigo_control = codigo_control;
    }

    public String getFecha_limite_emision() {
        return fecha_limite_emision;
    }

    public void setFecha_limite_emision(String fecha_limite_emision) {
        this.fecha_limite_emision = fecha_limite_emision;
    }

    public String getImporte_ice() {
        return importe_ice;
    }

    public void setImporte_ice(String importe_ice) {
        this.importe_ice = importe_ice;
    }

    public String getImporte_ventas() {
        return importe_ventas;
    }

    public void setImporte_ventas(String importe_ventas) {
        this.importe_ventas = importe_ventas;
    }

    public String getNit_ndi_ci() {
        return nit_ndi_ci;
    }

    public void setNit_ndi_ci(String nit_ndi_ci) {
        this.nit_ndi_ci = nit_ndi_ci;
    }

    public String getNombre_razon_social_comprador() {
        return nombre_razon_social_comprador;
    }

    public void setNombre_razon_social_comprador(String nombre_razon_social_comprador) {
        this.nombre_razon_social_comprador = nombre_razon_social_comprador;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    @Override
    public ContentValues getData() {
        ContentValues cv = new ContentValues();
        cv.put("nit", getNit());
        cv.put("nombre_razon_social", getNombre_razon_social());
        cv.put("numero_factura", getNumero_factura());
        cv.put("numero_autorizacion", getNumero_autorizacion());
        cv.put("fecha_emision", getFecha_emision());
        cv.put("importe_compra", getImporte_compra());
        cv.put("codigo_control", getCodigo_control());
        cv.put("fecha_limite_emision", getFecha_limite_emision());
        cv.put("importe_ice", getImporte_ice());
        cv.put("importe_ventas", getImporte_ventas());
        cv.put("nit_ndi_ci", getNit_ndi_ci());
        cv.put("nombre_razon_social_comprador", getNombre_razon_social_comprador());
        cv.put("extras", getExtras());
        return cv;
    }

    @Override
    public QRFactura setData(Cursor cursor) {
        QRFactura factura = new QRFactura();
        factura.setCodigo_control(cursor.getString(1));
        factura.setExtras(cursor.getString(2));
        factura.setFecha_emision(cursor.getString(3));
        factura.setFecha_limite_emision(cursor.getString(4));
        factura.setImporte_compra(cursor.getString(5));
        factura.setImporte_ice(cursor.getString(6));
        factura.setImporte_ventas(cursor.getString(7));
        factura.setNit(cursor.getString(8));
        factura.setNit_ndi_ci(cursor.getString(9));
        factura.setNombre_razon_social(cursor.getString(10));
        factura.setNombre_razon_social_comprador(cursor.getString(11));
        factura.setNumero_autorizacion(cursor.getString(12));
        factura.setNumero_factura(cursor.getString(13));
        return factura;
    }
}
