package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    private TextView tvNom, tvAge, tvMotif;
    private Button btnApprouver, btnRefuser;
    private String nom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // 1) ربط العناصر بالـ layout
        tvNom = findViewById(R.id.tv_nom_cm);
        tvAge = findViewById(R.id.tv_age_cm);
        tvMotif = findViewById(R.id.tv_motif_cm);
        btnApprouver = findViewById(R.id.btn_approuver_cm);
        btnRefuser = findViewById(R.id.btn_refuser_cm);

        // 2) استقبال البيانات بنفس المفاتيح
        Intent recu = getIntent();
        nom = recu.getStringExtra(Cles.cle(Cles.MATRICULE, Cles.NOM));
        int naissance = recu.getIntExtra(Cles.cle(Cles.MATRICULE, Cles.NAISSANCE), 0);
        int destination = recu.getIntExtra(Cles.cle(Cles.MATRICULE, Cles.DESTINATION), 0);
        String motif = recu.getStringExtra(Cles.cle(Cles.MATRICULE, Cles.MOTIF));

        // 3) العمر الزمني = سنة الوجهة - سنة الميلاد
        int ageTemporel = destination - naissance;

        tvNom.setText("Nom : " + nom);
        tvAge.setText("Âge temporel : " + ageTemporel + " ans");
        tvMotif.setText("Motif : " + motif);

        // 4) إذا كان الدافع تعديل الماضي والفرق عدد أولي نعطّل زر الموافقة
        if (motif.equals(getString(R.string.motif_modification)) && estPremier(ageTemporel)) {
            btnApprouver.setEnabled(false);
        }

        btnApprouver.setOnClickListener(v -> ouvrirVerdict(true));
        btnRefuser.setOnClickListener(v -> ouvrirVerdict(false));
    }

    // 5) إرسال القرار والاسم إلى النشاط الثالث
    private void ouvrirVerdict(boolean approuve) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(Cles.cle(Cles.MATRICULE, Cles.VERDICT), approuve);
        intent.putExtra(Cles.cle(Cles.MATRICULE, Cles.NOM), nom);
        startActivity(intent);
    }

    // 6) دالة التحقق من العدد الأولي
    private boolean estPremier(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}