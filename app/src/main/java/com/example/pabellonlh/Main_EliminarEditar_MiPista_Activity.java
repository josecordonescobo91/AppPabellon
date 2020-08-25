package com.example.pabellonlh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;

public class Main_EliminarEditar_MiPista_Activity extends AppCompatActivity {
    //ArrayList<String> lista = new String[];
   // ArrayList<String> lista = new ArrayList<String>();
    private Adapter item;
    String idPista;
    EditText edFecha, edHora, edPista;
    Button btEliminar;
    Spinner snMaterial;

     //intent.putExtra("usuario", usuario);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__eliminar_editar__mi_pista_);

        Intent intent = getIntent();

        edFecha = (EditText) findViewById(R.id.edFecha);
        edHora = (EditText) findViewById(R.id.edHora);
        edPista = (EditText) findViewById(R.id.edPista);
        snMaterial = (Spinner) findViewById(R.id.snMaterial);
        btEliminar = (Button) findViewById(R.id.btEliminar);
        idPista = intent.getStringExtra("objetoData");
        String[] parts = idPista.split("          ");
        String fecha = parts[0];
        String hora = parts[1];
        String pista = parts[2];

        edFecha.setText(fecha);
        edHora.setText(hora);
        edPista.setText(pista);


        String nick = intent.getStringExtra("nick");

        Toast.makeText(Main_EliminarEditar_MiPista_Activity.this, nick, Toast.LENGTH_SHORT).show();



        EnviarRecibirDatos("http://jose-cordones.es/app/obtener_MiPista_Seleccionada.php?fecha="+fecha+"&horas="+hora+"&pista="+pista);


    }


    public void EnviarRecibirDatos(String URL){

        //Toast.makeText(getApplicationContext(), ""+URL, Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                response = response.replace("][",",");

                if (response.length()>0){
                    try {
                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson",""+ja.length());
                        CargarListView(ja);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

    }

    public void CargarListView(JSONArray ja){

        ArrayList<String> lista = new ArrayList<>();
        String id = null;
        String nick = null;
        String material = null;
        for(int i=0;i<ja.length();i+=2){

            try {

                id = ("\n"+ja.getString(i+-2));
                material = ("\n"+ja.getString(i+-1));
                nick = ("\n"+ja.getString(i+-0));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }



        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        Intent intent = getIntent();

        String [] SpinnerMaterial = {material+"\n", "si", "no"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, SpinnerMaterial);
        snMaterial.setAdapter(adapter);



        final String finalId = id;
        final String finalNick = nick;
        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EliminarPista("http://jose-cordones.es/app/eliminarAlquiler.php?idalquiler="+ finalId);
                Intent intent = new Intent(getApplicationContext(), Main_Inicio_Activity.class);
                intent.putExtra("usuario", finalNick);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(), "PISTA ELIMINADA", Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void EliminarPista(String URL){


        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

    }

}
