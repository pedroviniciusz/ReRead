package com.example.reread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroActivity extends AppCompatActivity {

    private Button btnCadastro, btnEntrar;
    private EditText editTextNome, editTextEmail, editTextSenha;

    private final FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
        btnCadastro = findViewById(R.id.btnCadastro);
        btnEntrar = findViewById(R.id.btnEntrar);

    }

    @Override
    protected void onResume() {
        super.onResume();

        btnCadastro.setOnClickListener(view -> {
            String nome = editTextNome.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String senha = editTextSenha.getText().toString().trim();

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Por favor, preencha os campos corretamente.", Toast.LENGTH_LONG).show();
            } else {
                auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener((Activity) CadastroActivity.this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Cadastro realizado com sucesso", Toast.LENGTH_LONG).show();
                        salvarNome(nome);
                        goToApp();
                    } else {
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthWeakPasswordException e) {
                            Toast.makeText(getApplicationContext(), "A senha deve conter pelo menos 6 caracteres", Toast.LENGTH_LONG).show();
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            Toast.makeText(getApplicationContext(), "E-mail ou senha inválidos", Toast.LENGTH_LONG).show();
                        } catch (FirebaseAuthUserCollisionException e) {
                            Toast.makeText(getApplicationContext(), "Já existe cadastro com este e-mail!", Toast.LENGTH_LONG).show();
                        } catch (FirebaseNetworkException e) {
                            Toast.makeText(getApplicationContext(), "Sem conexão com a internet", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Log.e("Erro no cadastro", e.getMessage());
                        }
                    }
                });
            }
        });

        btnEntrar.setOnClickListener(view -> {
            Intent login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(login);
        });

    }

    private void salvarNome(String nome) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("nome", nome);

        String usuarioUid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documento = db.collection("usuarios").document(usuarioUid);
        documento.set(usuario).addOnCompleteListener((OnCompleteListener<Void>) o -> Log.d("db", "Nome de usuário salvo com sucesso"))
                .addOnFailureListener(e -> Log.d("db_error", "O nome do usuário não foi salvo " + e.getMessage()));

    }

    private void goToApp(){
        Intent login = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(login);
        finish();
    }

}