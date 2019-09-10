package com.example.firebaseprimeirospassos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LeituraActivity extends AppCompatActivity {

    //objetos para manipulação do banco
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextView textViewLeitura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura);
        textViewLeitura = findViewById(R.id.text_view_leitura);
        conectarBanco();

        databaseReference.child("Dicionario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                textViewLeitura.setText(
                        dataSnapshot.child("123456").child("Valor").getValue().toString()
                );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(LeituraActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
