package org.ajcm.facturas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.ajcm.facturas.R;
import org.ajcm.facturas.adapters.FacturaAdapter;
import org.ajcm.facturas.dataset.DBAdapter;
import org.ajcm.facturas.dataset.ModelAbstract;
import org.ajcm.facturas.dataset.models.QRFactura;
import org.ajcm.facturas.utils.DBUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;


public class MainActivity extends AppCompatActivity  {

    private Toolbar toolbar;
    private ArrayList<QRFactura> dataModels;
    private DBAdapter db;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataModels = new ArrayList<>();

        db = new DBAdapter(this);

        try {
            db.open();
            db.setClassModel(QRFactura.class);
            ArrayList<ModelAbstract> dbAll = db.getAll(new QRFactura());
            for (int i = 0; i < dbAll.size(); i++) {
                QRFactura factura = (QRFactura) dbAll.get(i);
                dataModels.add(factura);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new FacturaAdapter(dataModels);
        mRecyclerView.setAdapter(mAdapter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DBUtils.classToSQL(QRFactura.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    static final int REQUEST_SCANNER = 10;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            startActivityForResult(new Intent(MainActivity.this, ScannerActivity.class), REQUEST_SCANNER);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            QRFactura factura = setResultQR(data.getStringExtra("text"));
            try {
                db.open();
                db.setClassModel(QRFactura.class);
                db.add(factura);
                dataModels.add(factura);
                mAdapter = new FacturaAdapter(dataModels);
                mRecyclerView.setAdapter(mAdapter);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "codigo QR invalido ;(", Toast.LENGTH_LONG).show();
        }
    }

    public QRFactura setResultQR(String data) {
        StringTokenizer tokenizer = new StringTokenizer(data, "|");
        Vector<String> vs = new Vector<>();
        while (tokenizer.hasMoreTokens()){
            String s = tokenizer.nextToken();
            Log.e("data", s);
            vs.add(s);
        }
        QRFactura factura = new QRFactura();
        factura.setNit(vs.get(0));
        factura.setNumero_factura(vs.get(1));
        factura.setNumero_autorizacion(vs.get(2));
        factura.setFecha_emision(vs.get(3));
        factura.setExtras(vs.get(4));
        factura.setImporte_compra(vs.get(5));
        factura.setCodigo_control(vs.get(6));
        factura.setNit_ndi_ci(vs.get(7));
        factura.setImporte_ventas(vs.get(8));
        factura.setFecha_limite_emision(vs.get(9));
        factura.setImporte_ice(vs.get(10));
        factura.setNombre_razon_social("0");
        factura.setNombre_razon_social_comprador("0");
        return factura;
    }
}
