package com.example.simuladordeloteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button = findViewById(R.id.GerarNumero);
        TextView saida = findViewById(R.id.saidaGerador);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numerosGerados = gerarNumeros();
                saida.setText(numerosGerados);
            }
        });
    }

    // Agora `Comparar(View v)` está FORA do `onCreate()`, correto!
    public void Comparar(View v) {
        TextView saida = findViewById(R.id.saidaGerador);

        // 🔹 Recupera `numeroResultado` corretamente da Intent original

        String numeroResultado = getIntent().getParcelableExtra("numeroResultado");

        // 🔹 Envia ambos os números para `MainActivity3`
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("numeroGerado", saida.getText().toString());
        startActivity(intent);
    }



    // 🔹 `gerarNumeros()` agora está **dentro da classe**, corrigido!
    private String gerarNumeros() {
        Random random = new Random();
        HashSet<Integer> numerosSet = new HashSet<>();

        while (numerosSet.size() < 6) {
            int num1 = random.nextInt(50) + 1;
            numerosSet.add(num1);
        }

        ArrayList<Integer> numerosLista = new ArrayList<>(numerosSet);
        Collections.sort(numerosLista);

        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < numerosLista.size(); i++) {
            resultado.append(String.format("%02d", numerosLista.get(i)));
            if (i < numerosLista.size() - 1) {
                resultado.append("-");
            }
        }

        return resultado.toString(); // Retorna os números corretamente
    }
}



