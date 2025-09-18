package com.example.lab2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab2.R;
import com.example.lab2.models.Switch;
import java.util.List;

public class SwitchesAdapter extends RecyclerView.Adapter<SwitchesAdapter.ViewHolder> {

    private List<Switch> switches;
    private OnSwitchClickListener listener;

    public interface OnSwitchClickListener {
        void onSwitchClick(Switch switchItem, int position);
    }

    public SwitchesAdapter(List<Switch> switches, OnSwitchClickListener listener) {
        this.switches = switches;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_switch, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Switch switchItem = switches.get(position);
        holder.bind(switchItem, position);
    }

    @Override
    public int getItemCount() {
        return switches.size();
    }

    public void addSwitch(Switch switchItem) {
        switches.add(switchItem);
        notifyItemInserted(switches.size() - 1);
    }

    public void updateSwitch(int position, Switch switchItem) {
        switches.set(position, switchItem);
        notifyItemChanged(position);
    }

    public void removeSwitch(int position) {
        switches.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMarca, txtModelo, txtCantidadPuertos, txtTipo, txtEstado;

        public ViewHolder(View itemView) {
            super(itemView);
            txtMarca = itemView.findViewById(R.id.txtMarca);
            txtModelo = itemView.findViewById(R.id.txtModelo);
            txtCantidadPuertos = itemView.findViewById(R.id.txtCantidadPuertos);
            txtTipo = itemView.findViewById(R.id.txtTipo);
            txtEstado = itemView.findViewById(R.id.txtEstado);
        }

        public void bind(Switch switchItem, int position) {
            txtMarca.setText("Marca: " + switchItem.getMarca());
            txtModelo.setText("Modelo: " + switchItem.getModelo());
            txtCantidadPuertos.setText("Cantidad de Puertos: " + switchItem.getCantidadPuertos());
            txtTipo.setText("Tipo: " + switchItem.getTipo());
            txtEstado.setText("Estado: " + switchItem.getEstado());

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onSwitchClick(switchItem, position);
                }
            });
        }
    }
}