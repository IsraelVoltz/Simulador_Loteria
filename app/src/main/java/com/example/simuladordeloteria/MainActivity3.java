package com.example.simuladordeloteria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        TextView saida1 = findViewById(R.id.compGerado);
        TextView saida2 = findViewById(R.id.compResultado);
        Button botaoGerar = findViewById(R.id.Comparar);
        Button botaoIrAcertos = findViewById(R.id.irAcertos); // Certifique-se de que esse ID existe no XML

        // 🔹 Gera número automaticamente ao abrir
        String numeroAutoGerado = gerarResultado();
        saida1.setText("Número Resultado: " + numeroAutoGerado);

        // 🔹 Configura botão para gerar número na `saida2`
        botaoGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numeroBotaoGerado = gerarResultado();
                saida2.setText("Seu Número: " + numeroBotaoGerado);
            }
        });
    }


    public void irAcertos(View v) {
        TextView saida1 = findViewById(R.id.compGerado);
        TextView saida2 = findViewById(R.id.compResultado);

        // 🔹 Pegando os números gerados
        String numeroResultado = saida1.getText().toString().replace("Número Resultado: ", "");
        String numeroGerado = saida2.getText().toString().replace("Seu Número: ", "");

        // 🔹 Comparando os números
        String numerosIguais = compararNumeros(numeroResultado, numeroGerado);

        // 🔹 Envia apenas os números iguais para `MainActivity4`
        Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
        intent.putExtra("numerosIguais", numerosIguais);
        startActivity(intent);
    }

    // 🔹 Método para comparar os números gerados e identificar os iguais
    private String compararNumeros(String numeros1, String numeros2) {
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();

        Collections.addAll(set1, numeros1.split("-"));
        Collections.addAll(set2, numeros2.split("-"));

        set1.retainAll(set2); // Mantém apenas os números iguais

        return set1.isEmpty() ? "Nenhum número igual" : String.join("-", set1);
    }

    // 🔹 Método de geração de números aleatórios
    private String gerarResultado() {
        Random random = new Random();
        HashSet<Integer> numerosSet = new HashSet<>();

        while (numerosSet.size() < 6) {
            int numero = random.nextInt(50) + 1;
            numerosSet.add(numero);
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

        return resultado.toString();
    }
}
