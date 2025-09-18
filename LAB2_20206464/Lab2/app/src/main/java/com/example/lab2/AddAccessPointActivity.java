package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2.databinding.ActivityAddAccessPointBinding;

public class AddAccessPointActivity extends AppCompatActivity {

    private ActivityAddAccessPointBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAccessPointBinding.inflate(getLayoutInflater());
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
        String[] frecuencias = {"2.4 GHz", "5GHz", "Dual Band"};
        ArrayAdapter<String> frecuenciaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, frecuencias);
        frecuenciaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerFrecuencia.setAdapter(frecuenciaAdapter);

        String[] estados = {"Operativo", "En reparación", "Dado de baja"};
        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estados);
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerEstado.setAdapter(estadoAdapter);
    }

    private void setupSaveButton() {
        binding.btnSave.setOnClickListener(v -> {
            if (validateFields()) {
                saveAccessPoint();
            }
        });
    }

    private boolean validateFields() {
        if (binding.editMarca.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor ingrese la marca", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (binding.editAlcance.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor ingrese el alcance", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void saveAccessPoint() {
        String marca = binding.editMarca.getText().toString().trim();
        String frecuencia = binding.spinnerFrecuencia.getSelectedItem().toString();
        String alcance = binding.editAlcance.getText().toString().trim();
        String estado = binding.spinnerEstado.getSelectedItem().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("marca", marca);
        resultIntent.putExtra("frecuencia", frecuencia);
        resultIntent.putExtra("alcance", alcance);
        resultIntent.putExtra("estado", estado);

        Toast.makeText(this, "Access Point guardado", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}