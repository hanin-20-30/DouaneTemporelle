package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 1) ربط العناصر بالـ layout
        View racine = findViewById(R.id.root_result_cm);
        TextView tvMessage = findViewById(R.id.tv_message_cm);
        Button btnNouveau = findViewById(R.id.btn_nouveau_cm);

        // 2) استقبال القرار والاسم
        boolean approuve = getIntent().getBooleanExtra(Cles.cle(Cles.MATRICULE, Cles.VERDICT), false);
        String nom = getIntent().getStringExtra(Cles.cle(Cles.MATRICULE, Cles.NOM));

        // 3) تغيير لون الخلفية والرسالة حسب القرار
        if (approuve) {
            racine.setBackgroundColor(Color.parseColor("#2E7D32")); // أخضر
            tvMessage.setText("Bon voyage " + nom + " !");
        } else {
            racine.setBackgroundColor(Color.parseColor("#C62828")); // أحمر
            tvMessage.setText(nom + " est en état d'arrestation temporelle");
        }

        // 4) الرجوع إلى MainActivity وإغلاق كل الأنشطة السابقة
        btnNouveau.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}