package com.example.reread;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Document;

public class ComecarQuiz extends AppCompatActivity {

    private Button btnAnterior, btnProximo;

    private TextView txtViewTexto;

    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comecar_quiz);

        btnAnterior = findViewById(R.id.btnAnterior);
        btnProximo = findViewById(R.id.btnProximo);
        txtViewTexto = findViewById(R.id.txtViewTexto);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String usuarioUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documento = db.collection("usuarios").document(usuarioUid);
        documento.addSnapshotListener((documentSnapshot, error) -> {
            if(documentSnapshot != null) {
                String nomeUsuario = documentSnapshot.getString("nome");
               txtViewTexto.setText(txtViewTexto.getText().toString().replace("Fulano", nomeUsuario));
            }
        });

        btnAnterior.setOnClickListener(view -> {
            Intent cadastro = new Intent(getApplicationContext(), CadastroActivity.class);
            startActivity(cadastro);
        });

        btnProximo.setOnClickListener(view -> {
            Intent quiz = new Intent(getApplicationContext(), QuizActivity.class);
            startActivity(quiz);
        });
    }
}