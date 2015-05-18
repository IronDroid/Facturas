package org.ajcm.facturas.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.ajcm.facturas.R;
import org.ajcm.facturas.dataset.models.QRFactura;

import java.util.ArrayList;

/**
 * Created by jhonny on 12-05-15.
 */
public class FacturaAdapter extends RecyclerView.Adapter<FacturaAdapter.ViewHolder> {
    private ArrayList<QRFactura> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView totalFactura;
        public TextView fechaEmision;
        public ViewHolder(View v) {
            super(v);
            totalFactura = (TextView) v.findViewById(R.id.total_factura);
            fechaEmision = (TextView) v.findViewById(R.id.fecha_emision);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public FacturaAdapter(ArrayList<QRFactura> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public FacturaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.factura_row_layout, null);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.totalFactura.setText(mDataset.get(position).getExtras());
        holder.fechaEmision.setText(mDataset.get(position).getFecha_emision());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}