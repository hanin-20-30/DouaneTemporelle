package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNom, etNaissance, etDestination;
    private Spinner spMotif;
    private Button btnInspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1) ربط العناصر بالـ layout
        etNom = findViewById(R.id.et_nom_cm);
        etNaissance = findViewById(R.id.et_naissance_cm);
        etDestination = findViewById(R.id.et_destination_cm);
        spMotif = findViewById(R.id.sp_motif_cm);
        btnInspecter = findViewById(R.id.btn_inspecter_cm);

        btnInspecter.setOnClickListener(v -> {
            String nom = etNom.getText().toString().trim();
            String naiss = etNaissance.getText().toString().trim();
            String dest = etDestination.getText().toString().trim();

            // 2) التحقق من أن الحقول غير فارغة
            if (nom.isEmpty()) { etNom.setError("Nom obligatoire"); return; }
            if (naiss.isEmpty()) { etNaissance.setError("Année obligatoire"); return; }
            if (dest.isEmpty()) { etDestination.setError("Année obligatoire"); return; }

            String motif = spMotif.getSelectedItem().toString();

            // 3) Intent صريح نحو النشاط الثاني مع المفاتيح المبنية بدالة ثابتة
            Intent intent = new Intent(MainActivity.this, VerificationActivity.class);
            intent.putExtra(Cles.cle(Cles.MATRICULE, Cles.NOM), nom);
            intent.putExtra(Cles.cle(Cles.MATRICULE, Cles.NAISSANCE), Integer.parseInt(naiss));
            intent.putExtra(Cles.cle(Cles.MATRICULE, Cles.DESTINATION), Integer.parseInt(dest));
            intent.putExtra(Cles.cle(Cles.MATRICULE, Cles.MOTIF), motif);
            startActivity(intent);
        });
    }
}