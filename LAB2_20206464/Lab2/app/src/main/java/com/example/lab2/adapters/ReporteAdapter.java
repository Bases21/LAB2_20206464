package com.example.lab2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab2.R;
import com.example.lab2.models.EquipoReporte;
import com.example.lab2.models.GrupoReporte;
import java.util.List;

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ViewHolder> {

    private List<GrupoReporte> grupos;

    public ReporteAdapter(List<GrupoReporte> grupos) {
        this.grupos = grupos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reporte_grupo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GrupoReporte grupo = grupos.get(position);
        holder.bind(grupo);
    }

    @Override
    public int getItemCount() {
        return grupos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtEstadoGrupo;
        private LinearLayout layoutEquipos;

        public ViewHolder(View itemView) {
            super(itemView);
            txtEstadoGrupo = itemView.findViewById(R.id.txtEstadoGrupo);
            layoutEquipos = itemView.findViewById(R.id.layoutEquipos);
        }

        public void bind(GrupoReporte grupo) {
            txtEstadoGrupo.setText(grupo.getEstado() + " (" + grupo.getCantidad() + ")");

            layoutEquipos.removeAllViews();

            for (EquipoReporte equipo : grupo.getEquipos()) {
                View equipoView = LayoutInflater.from(itemView.getContext())
                        .inflate(R.layout.item_equipo_simple, layoutEquipos, false);

                TextView txtTipoEquipo = equipoView.findViewById(R.id.txtTipoEquipo);
                TextView txtMarcaModelo = equipoView.findViewById(R.id.txtMarcaModelo);

                txtTipoEquipo.setText(equipo.getTipo());
                txtMarcaModelo.setText(equipo.getMarca() + " " + equipo.getModelo());

                layoutEquipos.addView(equipoView);
            }
        }
    }
}