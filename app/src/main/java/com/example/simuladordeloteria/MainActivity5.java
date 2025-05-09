package com.example.simuladordeloteria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigInteger;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity5 extends AppCompatActivity {
    private double totalProbabilidade = 0;
    private int contadorClicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        TextView saidaCalc = findViewById(R.id.saidaCalc);
        Button button5 = findViewById(R.id.Calcular);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                contadorClicks++; // 🔹 Incrementa contador
                double probabilidade = calcularProbabilidade();
                totalProbabilidade += probabilidade; // 🔹 Soma a nova probabilidade

                saidaCalc.setText(
                        "Chance de ganhar: " + String.format("%.10f", probabilidade) + "%" +
                                "\nTotal acumulado: " + String.format("%.10f", totalProbabilidade) + "%" +
                                "\nBotão pressionado " + contadorClicks + " vezes"
                );
            }
        });
    }

    private double calcularProbabilidade() {
        // Cálculo da combinação C(n, k) = n! / (k!(n-k)!)
        BigInteger totalCombinacoes = calcularFatorial(50).divide(calcularFatorial(6).multiply(calcularFatorial(50 - 6)));

        // Probabilidade de acerto (1 combinação vencedora / total de combinações)
        return (1.0 / totalCombinacoes.doubleValue()) * 100;
    }

    private BigInteger calcularFatorial(int num) {
        BigInteger resultado = BigInteger.ONE;
        for (int i = 1; i <= num; i++) {
            resultado = resultado.multiply(BigInteger.valueOf(i));
        }
        return resultado;
    }

}
