package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.lab2.adapters.SwitchesAdapter;
import com.example.lab2.databinding.ActivitySwitchesBinding;
import com.example.lab2.models.Switch;
import java.util.ArrayList;
import java.util.List;

public class SwitchesActivity extends AppCompatActivity {

    private ActivitySwitchesBinding binding;
    private List<Switch> switchesList;
    private SwitchesAdapter adapter;
    private static final int ADD_SWITCH_REQUEST = 1;
    private static final int EDIT_SWITCH_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySwitchesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        initializeData();
        setupRecyclerView();
        setupFab();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initializeData() {
        switchesList = new ArrayList<>();
        switchesList.add(new Switch("TP-Link", "TL-SG108", 8, "Administrable", "Operativo"));
        switchesList.add(new Switch("Cisco", "Catalyst 2960-X", 24, "Administrable", "En reparación"));
        switchesList.add(new Switch("Aruba", "2530-48G", 48, "No Administrable", "En reparación"));
    }

    private void setupRecyclerView() {
        adapter = new SwitchesAdapter(switchesList, this::onSwitchClick);
        binding.recyclerViewSwitches.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewSwitches.setAdapter(adapter);
    }

    private void setupFab() {
        binding.fabAddSwitch.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddSwitchActivity.class);
            startActivityForResult(intent, ADD_SWITCH_REQUEST);
        });
    }

    private void onSwitchClick(Switch switchItem, int position) {
        Intent intent = new Intent(this, EditSwitchActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("marca", switchItem.getMarca());
        intent.putExtra("modelo", switchItem.getModelo());
        intent.putExtra("cantidadPuertos", switchItem.getCantidadPuertos());
        intent.putExtra("tipo", switchItem.getTipo());
        intent.putExtra("estado", switchItem.getEstado());
        startActivityForResult(intent, EDIT_SWITCH_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == ADD_SWITCH_REQUEST) {
                String marca = data.getStringExtra("marca");
                String modelo = data.getStringExtra("modelo");
                int cantidadPuertos = Integer.parseInt(data.getStringExtra("cantidadPuertos"));
                String tipo = data.getStringExtra("tipo");
                String estado = data.getStringExtra("estado");

                Switch newSwitch = new Switch(marca, modelo, cantidadPuertos, tipo, estado);
                switchesList.add(newSwitch);
                adapter.notifyItemInserted(switchesList.size() - 1);

            } else if (requestCode == EDIT_SWITCH_REQUEST) {
                int position = data.getIntExtra("position", -1);
                String action = data.getStringExtra("action");

                if ("delete".equals(action)) {
                    switchesList.remove(position);
                    adapter.notifyItemRemoved(position);
                } else if ("update".equals(action)) {
                    String marca = data.getStringExtra("marca");
                    String modelo = data.getStringExtra("modelo");
                    int cantidadPuertos = Integer.parseInt(data.getStringExtra("cantidadPuertos"));
                    String tipo = data.getStringExtra("tipo");
                    String estado = data.getStringExtra("estado");

                    Switch updatedSwitch = new Switch(marca, modelo, cantidadPuertos, tipo, estado);
                    switchesList.set(position, updatedSwitch);
                    adapter.notifyItemChanged(position);
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}