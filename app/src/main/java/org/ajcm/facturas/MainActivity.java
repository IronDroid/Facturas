package org.ajcm.facturas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android_s14.rvh.DataModel;
import com.android_s14.rvh.RecyclerViewBuilder;
import com.google.zxing.Result;

import org.ajcm.facturas.Adapters.FacturaAdapter;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class MainActivity extends AppCompatActivity  {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        List<DataModel> imageData = getAllData();
        new RecyclerViewBuilder(this)
                .using(recyclerView)
                .setRowLayout(R.layout.factura_row_layout)
                .setData(imageData)
                .build();

    }

    private List<DataModel> getAllData(){
        List<DataModel> dataModels = new ArrayList<>();
        dataModels.add(new FacturaAdapter("A"));
        dataModels.add(new FacturaAdapter("B"));
        dataModels.add(new FacturaAdapter("C"));
        dataModels.add(new FacturaAdapter("D"));
        dataModels.add(new FacturaAdapter("E"));
        dataModels.add(new FacturaAdapter("F"));
        return dataModels;
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
            Toast.makeText(this, data.getStringExtra("text"), Toast.LENGTH_SHORT).show();
        }
    }
}
