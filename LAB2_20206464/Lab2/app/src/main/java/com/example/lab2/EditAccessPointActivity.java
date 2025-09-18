package com.example.lab2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2.databinding.ActivityEditAccessPointBinding;

public class EditAccessPointActivity extends AppCompatActivity {

    private ActivityEditAccessPointBinding binding;
    private int accessPointPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditAccessPointBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        setupSpinners();
        loadAccessPointData();
        setupButtons();
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

    private void loadAccessPointData() {
        accessPointPosition = getIntent().getIntExtra("position", -1);
        String marca = getIntent().getStringExtra("marca");
        String frecuencia = getIntent().getStringExtra("frecuencia");
        int alcance = getIntent().getIntExtra("alcance", 0);
        String estado = getIntent().getStringExtra("estado");

        binding.editMarca.setText(marca);
        binding.editAlcance.setText(String.valueOf(alcance));

        String[] frecuencias = {"2.4 GHz", "5GHz", "Dual Band"};
        for (int i = 0; i < frecuencias.length; i++) {
            if (frecuencias[i].equals(frecuencia)) {
                binding.spinnerFrecuencia.setSelection(i);
                break;
            }
        }

        String[] estados = {"Operativo", "En reparación", "Dado de baja"};
        for (int i = 0; i < estados.length; i++) {
            if (estados[i].equals(estado)) {
                binding.spinnerEstado.setSelection(i);
                break;
            }
        }
    }

    private void setupButtons() {
        binding.btnSave.setOnClickListener(v -> {
            if (validateFields()) {
                saveAccessPoint();
            }
        });

        binding.btnDelete.setOnClickListener(v -> {
            showDeleteConfirmationDialog();
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
        resultIntent.putExtra("action", "update");
        resultIntent.putExtra("position", accessPointPosition);
        resultIntent.putExtra("marca", marca);
        resultIntent.putExtra("frecuencia", frecuencia);
        resultIntent.putExtra("alcance", alcance);
        resultIntent.putExtra("estado", estado);

        Toast.makeText(this, "Access Point actualizado", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro que desea Borrar?")
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteAccessPoint();
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteAccessPoint() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("action", "delete");
        resultIntent.putExtra("position", accessPointPosition);

        Toast.makeText(this, "Access Point eliminado", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}