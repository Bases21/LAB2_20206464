package com.example.lab2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.lab2.adapters.ReporteAdapter;
import com.example.lab2.databinding.ActivityReporteBinding;
import com.example.lab2.models.AccessPoint;
import com.example.lab2.models.EquipoReporte;
import com.example.lab2.models.GrupoReporte;
import com.example.lab2.models.Router;
import com.example.lab2.models.Switch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReporteActivity extends AppCompatActivity {

    private ActivityReporteBinding binding;
    private List<GrupoReporte> gruposReporte;
    private ReporteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReporteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        generateReport();
        setupRecyclerView();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void generateReport() {
        List<EquipoReporte> todosLosEquipos = new ArrayList<>();

        todosLosEquipos.add(new EquipoReporte("Router", "Cisco", "ISR 4451-X", "Operativo"));
        todosLosEquipos.add(new EquipoReporte("Router", "TP-Link", "Archer C6", "En reparación"));
        todosLosEquipos.add(new EquipoReporte("Router", "Aruba", "Instant On AP22", "Dado de baja"));
        todosLosEquipos.add(new EquipoReporte("Router", "Cisco", "Catalyst 2960-X", "Operativo"));

        todosLosEquipos.add(new EquipoReporte("Switch", "TP-Link", "TL-SG108", "Operativo"));
        todosLosEquipos.add(new EquipoReporte("Switch", "Cisco", "Catalyst 2960-X", "En reparación"));
        todosLosEquipos.add(new EquipoReporte("Switch", "Aruba", "2530-48G", "En reparación"));

        todosLosEquipos.add(new EquipoReporte("AP", "TP-Link", "", "Operativo"));
        todosLosEquipos.add(new EquipoReporte("AP", "Ubiquiti", "UniFi AC Lite", "En reparación"));
        todosLosEquipos.add(new EquipoReporte("AP", "Cisco", "Aironet 1850", "Dado de baja"));

        Map<String, List<EquipoReporte>> equiposPorEstado = new HashMap<>();

        for (EquipoReporte equipo : todosLosEquipos) {
            String estado = equipo.getEstado();
            if (!equiposPorEstado.containsKey(estado)) {
                equiposPorEstado.put(estado, new ArrayList<>());
            }
            equiposPorEstado.get(estado).add(equipo);
        }

        gruposReporte = new ArrayList<>();

        String[] estadosOrdenados = {"Operativo", "En reparación", "Dado de baja"};

        for (String estado : estadosOrdenados) {
            if (equiposPorEstado.containsKey(estado)) {
                List<EquipoReporte> equipos = equiposPorEstado.get(estado);
                gruposReporte.add(new GrupoReporte(estado, equipos.size(), equipos));
            }
        }
    }

    private void setupRecyclerView() {
        adapter = new ReporteAdapter(gruposReporte);
        binding.recyclerViewReporte.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewReporte.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}