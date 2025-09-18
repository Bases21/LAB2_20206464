package com.example.lab2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2.databinding.ActivityEditSwitchBinding;

public class EditSwitchActivity extends AppCompatActivity {

    private ActivityEditSwitchBinding binding;
    private int switchPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditSwitchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        setupSpinners();
        loadSwitchData();
        setupButtons();
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

    private void loadSwitchData() {
        switchPosition = getIntent().getIntExtra("position", -1);
        String marca = getIntent().getStringExtra("marca");
        String modelo = getIntent().getStringExtra("modelo");
        int cantidadPuertos = getIntent().getIntExtra("cantidadPuertos", 0);
        String tipo = getIntent().getStringExtra("tipo");
        String estado = getIntent().getStringExtra("estado");

        binding.editMarca.setText(marca);
        binding.editModelo.setText(modelo);
        binding.editCantidadPuertos.setText(String.valueOf(cantidadPuertos));

        String[] tipos = {"Administrable", "No Administrable"};
        for (int i = 0; i < tipos.length; i++) {
            if (tipos[i].equals(tipo)) {
                binding.spinnerTipo.setSelection(i);
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
                saveSwitch();
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
        resultIntent.putExtra("action", "update");
        resultIntent.putExtra("position", switchPosition);
        resultIntent.putExtra("marca", marca);
        resultIntent.putExtra("modelo", modelo);
        resultIntent.putExtra("cantidadPuertos", cantidadPuertos);
        resultIntent.putExtra("tipo", tipo);
        resultIntent.putExtra("estado", estado);

        Toast.makeText(this, "Switch actualizado", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro que desea Borrar?")
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteSwitch();
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

    private void deleteSwitch() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("action", "delete");
        resultIntent.putExtra("position", switchPosition);

        Toast.makeText(this, "Switch eliminado", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}