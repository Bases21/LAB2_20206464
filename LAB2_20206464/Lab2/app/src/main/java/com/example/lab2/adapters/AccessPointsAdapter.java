package com.example.lab2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab2.R;
import com.example.lab2.models.AccessPoint;
import java.util.List;

public class AccessPointsAdapter extends RecyclerView.Adapter<AccessPointsAdapter.ViewHolder> {

    private List<AccessPoint> accessPoints;
    private OnAccessPointClickListener listener;

    public interface OnAccessPointClickListener {
        void onAccessPointClick(AccessPoint accessPoint, int position);
    }

    public AccessPointsAdapter(List<AccessPoint> accessPoints, OnAccessPointClickListener listener) {
        this.accessPoints = accessPoints;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_access_point, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AccessPoint accessPoint = accessPoints.get(position);
        holder.bind(accessPoint, position);
    }

    @Override
    public int getItemCount() {
        return accessPoints.size();
    }

    public void addAccessPoint(AccessPoint accessPoint) {
        accessPoints.add(accessPoint);
        notifyItemInserted(accessPoints.size() - 1);
    }

    public void updateAccessPoint(int position, AccessPoint accessPoint) {
        accessPoints.set(position, accessPoint);
        notifyItemChanged(position);
    }

    public void removeAccessPoint(int position) {
        accessPoints.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMarca, txtFrecuencia, txtAlcance, txtEstado;

        public ViewHolder(View itemView) {
            super(itemView);
            txtMarca = itemView.findViewById(R.id.txtMarca);
            txtFrecuencia = itemView.findViewById(R.id.txtFrecuencia);
            txtAlcance = itemView.findViewById(R.id.txtAlcance);
            txtEstado = itemView.findViewById(R.id.txtEstado);
        }

        public void bind(AccessPoint accessPoint, int position) {
            txtMarca.setText("Marca: " + accessPoint.getMarca());
            txtFrecuencia.setText("Frecuencia: " + accessPoint.getFrecuencia());
            txtAlcance.setText("Alcance: " + accessPoint.getAlcance() + " metros");
            txtEstado.setText("Estado: " + accessPoint.getEstado());

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onAccessPointClick(accessPoint, position);
                }
            });
        }
    }
}