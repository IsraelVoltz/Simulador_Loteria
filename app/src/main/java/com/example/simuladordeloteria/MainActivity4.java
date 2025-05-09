package com.example.simuladordeloteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView saidaFinal = findViewById(R.id.Iguais);


        Intent intent = getIntent();
        String numerosIguais = intent.getStringExtra("numerosIguais");

        saidaFinal.setText("Números Iguais: "+numerosIguais);
    }
    public void irMain5(View v){
        Intent i = new Intent(MainActivity4.this, MainActivity5.class);
        startActivity(i);
    }

}
