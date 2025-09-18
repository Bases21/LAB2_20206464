package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.lab2.adapters.RoutersAdapter;
import com.example.lab2.databinding.ActivityRoutersBinding;
import com.example.lab2.models.Router;
import java.util.ArrayList;
import java.util.List;

public class RoutersActivity extends AppCompatActivity {

    private ActivityRoutersBinding binding;
    private List<Router> routersList;
    private RoutersAdapter adapter;
    private static final int ADD_ROUTER_REQUEST = 1;
    private static final int EDIT_ROUTER_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoutersBinding.inflate(getLayoutInflater());
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
        routersList = new ArrayList<>();
        routersList.add(new Router("Cisco", "ISR 4451-X", "1 Gbps", "Operativo"));
        routersList.add(new Router("TP-Link", "Archer C6", "2 Gbps", "En reparación"));
        routersList.add(new Router("Aruba", "Instant On AP22", "1 Gbps", "Dado de baja"));
        routersList.add(new Router("Cisco", "Catalyst 2960-X", "3 Gbps", "Operativo"));
    }

    private void setupRecyclerView() {
        adapter = new RoutersAdapter(routersList, this::onRouterClick);
        binding.recyclerViewRouters.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerViewRouters.setAdapter(adapter);
    }

    private void setupFab() {
        binding.fabAddRouter.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddRouterActivity.class);
            startActivityForResult(intent, ADD_ROUTER_REQUEST);
        });
    }

    private void onRouterClick(Router router, int position) {
        Intent intent = new Intent(this, EditRouterActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("marca", router.getMarca());
        intent.putExtra("modelo", router.getModelo());
        intent.putExtra("velocidad", router.getVelocidadSoportada());
        intent.putExtra("estado", router.getEstado());
        startActivityForResult(intent, EDIT_ROUTER_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == ADD_ROUTER_REQUEST) {
                String marca = data.getStringExtra("marca");
                String modelo = data.getStringExtra("modelo");
                String velocidad = data.getStringExtra("velocidad");
                String estado = data.getStringExtra("estado");

                Router newRouter = new Router(marca, modelo, velocidad, estado);
                routersList.add(newRouter);
                adapter.notifyItemInserted(routersList.size() - 1);

            } else if (requestCode == EDIT_ROUTER_REQUEST) {
                int position = data.getIntExtra("position", -1);
                String action = data.getStringExtra("action");

                if ("delete".equals(action)) {
                    routersList.remove(position);
                    adapter.notifyItemRemoved(position);
                } else if ("update".equals(action)) {
                    String marca = data.getStringExtra("marca");
                    String modelo = data.getStringExtra("modelo");
                    String velocidad = data.getStringExtra("velocidad");
                    String estado = data.getStringExtra("estado");

                    Router updatedRouter = new Router(marca, modelo, velocidad, estado);
                    routersList.set(position, updatedRouter);
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