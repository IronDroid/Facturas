package org.ajcm.facturas.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.ajcm.facturas.R;
import org.ajcm.facturas.dataset.DBAdapter;
import org.ajcm.facturas.dataset.models.QRFactura;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jhonny on 12-05-15.
 */
public class FacturaAdapter extends RecyclerView.Adapter<FacturaAdapter.ViewHolder> {
    private ArrayList<QRFactura> mDataset;
    private DBAdapter dbAdapter;

    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView totalFactura;
        public TextView fechaEmision;
        public TextView ci;
        public ViewHolder(View v) {
            super(v);
            totalFactura = (TextView) v.findViewById(R.id.total_factura);
            fechaEmision = (TextView) v.findViewById(R.id.fecha_emision);
            ci = (TextView) v.findViewById(R.id.ci);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FacturaAdapter(ArrayList<QRFactura> myDataset, Context context) {
        mDataset = myDataset;
        notifyDataSetChanged();
        this.context = context;
        dbAdapter = new DBAdapter(this.context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FacturaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(this.context)
                .inflate(R.layout.factura_row_layout, null);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.totalFactura.setText(mDataset.get(position).getExtras());
        holder.fechaEmision.setText(mDataset.get(position).getFecha_emision());
        holder.ci.setText(mDataset.get(position).getNumero_factura());
        holder.ci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dbAdapter.open();
                    dbAdapter.setClassModel(QRFactura.class);
                    dbAdapter.delete(mDataset.get(position).get_id());
                    Toast.makeText(context, "eliminado: "+ mDataset.get(position).get_id(), Toast.LENGTH_SHORT).show();
                    mDataset.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}