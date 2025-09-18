package com.example.lab2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab2.R;
import com.example.lab2.models.Router;
import java.util.List;

public class RoutersAdapter extends RecyclerView.Adapter<RoutersAdapter.ViewHolder> {

    private List<Router> routers;
    private OnRouterClickListener listener;

    public interface OnRouterClickListener {
        void onRouterClick(Router router, int position);
    }

    public RoutersAdapter(List<Router> routers, OnRouterClickListener listener) {
        this.routers = routers;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_router, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Router router = routers.get(position);
        holder.bind(router, position);
    }

    @Override
    public int getItemCount() {
        return routers.size();
    }

    public void addRouter(Router router) {
        routers.add(router);
        notifyItemInserted(routers.size() - 1);
    }

    public void updateRouter(int position, Router router) {
        routers.set(position, router);
        notifyItemChanged(position);
    }

    public void removeRouter(int position) {
        routers.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMarca, txtModelo, txtVelocidad, txtEstado;

        public ViewHolder(View itemView) {
            super(itemView);
            txtMarca = itemView.findViewById(R.id.txtMarca);
            txtModelo = itemView.findViewById(R.id.txtModelo);
            txtVelocidad = itemView.findViewById(R.id.txtVelocidad);
            txtEstado = itemView.findViewById(R.id.txtEstado);
        }

        public void bind(Router router, int position) {
            txtMarca.setText("Marca: " + router.getMarca());
            txtModelo.setText("Modelo: " + router.getModelo());
            txtVelocidad.setText("Velocidad soportada: " + router.getVelocidadSoportada());
            txtEstado.setText("Estado: " + router.getEstado());

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onRouterClick(router, position);
                }
            });
        }
    }
}