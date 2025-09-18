package com.example.lab2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab2.databinding.ActivityEditRouterBinding;

public class EditRouterActivity extends AppCompatActivity {

    private ActivityEditRouterBinding binding;
    private int routerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditRouterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();
        setupSpinner();
        loadRouterData();
        setupButtons();
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

    private void loadRouterData() {
        routerPosition = getIntent().getIntExtra("position", -1);
        String marca = getIntent().getStringExtra("marca");
        String modelo = getIntent().getStringExtra("modelo");
        String velocidad = getIntent().getStringExtra("velocidad");
        String estado = getIntent().getStringExtra("estado");

        binding.editMarca.setText(marca);
        binding.editModelo.setText(modelo);
        binding.editVelocidad.setText(velocidad);

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
                saveRouter();
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
        resultIntent.putExtra("action", "update");
        resultIntent.putExtra("position", routerPosition);
        resultIntent.putExtra("marca", marca);
        resultIntent.putExtra("modelo", modelo);
        resultIntent.putExtra("velocidad", velocidad);
        resultIntent.putExtra("estado", estado);

        Toast.makeText(this, "Router actualizado", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro que desea Borrar?")
                .setPositiveButton("ACEPTAR", new  DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteRouter();
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

    private void deleteRouter() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("action", "delete");
        resultIntent.putExtra("position", routerPosition);

        Toast.makeText(this, "Router eliminado", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}