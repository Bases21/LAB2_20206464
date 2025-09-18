package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2.databinding.ActivityAddSwitchBinding;

public class AddSwitchActivity extends AppCompatActivity {

    private ActivityAddSwitchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddSwitchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        setupSpinners();
        setupSaveButton();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupSpinners() {
        String[] tipos = {"Administrable", "No Administrable"};
        ArrayAdapter<String> tipoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipos);
        tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerTipo.setAdapter(tipoAdapter);

        String[] estados = {"Operativo", "En reparación", "Dado de baja"};
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estados);
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerEstado.setAdapter(estadoAdapter);
    }

    private void setupSaveButton() {
        binding.btnSave.setOnClickListener(v -> {
            if (validateFields()) {
                saveSwitch();
            }
        });
    }

    private boolean validateFields() {
        if (binding.editMarca.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor ingrese la marca", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.editModelo.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor ingrese el modelo", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.editCantidadPuertos.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor ingrese la cantidad de puertos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void saveSwitch() {
        String marca = binding.editMarca.getText().toString().trim();
        String modelo = binding.editModelo.getText().toString().trim();
        String cantidadPuertos = binding.editCantidadPuertos.getText().toString().trim();
        String tipo = binding.spinnerTipo.getSelectedItem().toString();
        String estado = binding.spinnerEstado.getSelectedItem().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("marca", marca);
        resultIntent.putExtra("modelo", modelo);
        resultIntent.putExtra("cantidadPuertos", cantidadPuertos);
        resultIntent.putExtra("tipo", tipo);
        resultIntent.putExtra("estado", estado);

        Toast.makeText(this, "Switch guardado", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}