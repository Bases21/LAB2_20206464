package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.lab2.adapters.AccessPointsAdapter;
import com.example.lab2.databinding.ActivityAccessPointsBinding;
import com.example.lab2.models.AccessPoint;
import java.util.ArrayList;
import java.util.List;

public class AccessPointsActivity extends AppCompatActivity {

    private ActivityAccessPointsBinding binding;
    private List<AccessPoint> accessPointsList;
    private AccessPointsAdapter adapter;
    private static final int ADD_ACCESS_POINT_REQUEST = 1;
    private static final int EDIT_ACCESS_POINT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccessPointsBinding.inflate(getLayoutInflater());
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
        accessPointsList = new ArrayList<>();
        accessPointsList.add(new AccessPoint("TP-Link", "2.4 GHz", 30, "Operativo"));
        accessPointsList.add(new AccessPoint("Ubiquiti UniFi AC Lite", "Dual Band", 70, "En reparación"));
        accessPointsList.add(new AccessPoint("Cisco Aironet 1850", "5 GHz", 100, "Dado de baja"));
    }

    private void setupRecyclerView() {
        adapter = new AccessPointsAdapter(accessPointsList, this::onAccessPointClick);
        binding.recyclerViewAccessPoints.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewAccessPoints.setAdapter(adapter);
    }

    private void setupFab() {
        binding.fabAddAccessPoint.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddAccessPointActivity.class);
            startActivityForResult(intent, ADD_ACCESS_POINT_REQUEST);
        });
    }

    private void onAccessPointClick(AccessPoint accessPoint, int position) {
        Intent intent = new Intent(this, EditAccessPointActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("marca", accessPoint.getMarca());
        intent.putExtra("frecuencia", accessPoint.getFrecuencia());
        intent.putExtra("alcance", accessPoint.getAlcance());
        intent.putExtra("estado", accessPoint.getEstado());
        startActivityForResult(intent, EDIT_ACCESS_POINT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == ADD_ACCESS_POINT_REQUEST) {
                String marca = data.getStringExtra("marca");
                String frecuencia = data.getStringExtra("frecuencia");
                int alcance = Integer.parseInt(data.getStringExtra("alcance"));
                String estado = data.getStringExtra("estado");

                AccessPoint newAccessPoint = new AccessPoint(marca, frecuencia, alcance, estado);
                accessPointsList.add(newAccessPoint);
                adapter.notifyItemInserted(accessPointsList.size() - 1);

            } else if (requestCode == EDIT_ACCESS_POINT_REQUEST) {
                int position = data.getIntExtra("position", -1);
                String action = data.getStringExtra("action");

                if ("delete".equals(action)) {
                    accessPointsList.remove(position);
                    adapter.notifyItemRemoved(position);
                } else if ("update".equals(action)) {
                    String marca = data.getStringExtra("marca");
                    String frecuencia = data.getStringExtra("frecuencia");
                    int alcance = Integer.parseInt(data.getStringExtra("alcance"));
                    String estado = data.getStringExtra("estado");

                    AccessPoint updatedAccessPoint = new AccessPoint(marca, frecuencia, alcance, estado);
                    accessPointsList.set(position, updatedAccessPoint);
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