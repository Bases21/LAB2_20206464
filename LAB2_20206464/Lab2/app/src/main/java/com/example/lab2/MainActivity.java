package com.example.lab2;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.lab2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupButtons();
    }

    private void setupButtons() {
        binding.btnRouters.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RoutersActivity.class);
            startActivity(intent);
        });

        binding.btnSwitches.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SwitchesActivity.class);
            startActivity(intent);
        });

        binding.btnSwitches.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SwitchesActivity.class);
            startActivity(intent);
        });
        binding.btnAccessPoints.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AccessPointsActivity.class);
            startActivity(intent);
        });
        binding.btnReporte.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ReporteActivity.class);
            startActivity(intent);
        });
    }
}