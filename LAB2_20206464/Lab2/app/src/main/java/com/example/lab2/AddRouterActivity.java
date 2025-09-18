package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2.databinding.ActivityAddRouterBinding;

public class AddRouterActivity extends AppCompatActivity {

    private ActivityAddRouterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRouterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        setupSpinner();
        setupSaveButton();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupSpinner() {
        String[] estados = {"Operativo", "En reparación", "Dado de baja"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerEstado.setAdapter(adapter);
    }

    private void setupSaveButton() {
        binding.btnSave.setOnClickListener(v -> {
            if (validateFields()) {
                saveRouter();
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
        if (binding.editVelocidad.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Por favor ingrese la velocidad", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void saveRouter() {
        String marca = binding.editMarca.getText().toString().trim();
        String modelo = binding.editModelo.getText().toString().trim();
        String velocidad = binding.editVelocidad.getText().toString().trim();
        String estado = binding.spinnerEstado.getSelectedItem().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("marca", marca);
        resultIntent.putExtra("modelo", modelo);
        resultIntent.putExtra("velocidad", velocidad);
        resultIntent.putExtra("estado", estado);

        Toast.makeText(this, "Router guardado", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}