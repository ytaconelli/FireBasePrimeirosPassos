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
    private TextView textViewLeitura2;
    private TextView textViewLeitura3;
    private TextView textViewMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura);
        textViewLeitura = findViewById(R.id.text_view_leitura);
        textViewLeitura2 = findViewById(R.id.text_view_leitura2);
        textViewLeitura3 = findViewById(R.id.text_view_leitura3);
        textViewMedia = findViewById(R.id.text_view_media);
        conectarBanco();

        databaseReference.child("Dicionario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Double valor1 = Double.parseDouble(
                        dataSnapshot.child("Valor1").getValue().toString()
                );
                Double valor2 = Double.parseDouble(
                        dataSnapshot.child("Valor2").getValue().toString()
                );

                Double valor3 = Double.parseDouble(
                        dataSnapshot.child("Valor3").getValue().toString()
                );

                textViewLeitura.setText(valor1.toString());
                textViewLeitura2.setText(valor2.toString());
                textViewLeitura3.setText(valor3.toString());

                Double media = (valor1 + valor2 + valor3) / 3;
                textViewMedia.setText("Média: " + media.toString());



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
