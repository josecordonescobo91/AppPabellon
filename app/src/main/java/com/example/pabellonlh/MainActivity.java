package com.example.pabellonlh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button login;
    Button horario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     /*   login = (Button)findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });*/
    }
    //Metodo para ir a la pantalla login
    public void Login (View view){
        Intent login = new Intent(this, Main_Login_Activity.class);
        startActivity(login);
    }

    public void Horario (View view){
        Intent horario = new Intent(this, Main_Horario_Activity.class);
        startActivity(horario);
    }
}
