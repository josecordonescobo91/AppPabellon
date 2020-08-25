package com.example.pabellonlh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Main_EliminarBicicleta_Activity extends AppCompatActivity {

    String objeto;
    EditText edDia, edUsuario, edBicicleta, edID;
    TextView tvIDALQUILER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__eliminar_bicicleta_);

        Intent intent = getIntent();

        edDia = (EditText) findViewById(R.id.edDia);
        edUsuario = (EditText) findViewById(R.id.edUsuario);
        edBicicleta = (EditText) findViewById(R.id.edBicicleta);
        //edID = (EditText) findViewById(R.id.edID);
        tvIDALQUILER = (TextView) findViewById(R.id.tvIDALQUILER);


        objeto = intent.getStringExtra("objetoData");
        String[] parts = objeto.split("                   ");
        String dia = parts[0];
        String bici = parts[1];
        String usuario = parts[2];
      //  String sesionNick = parts[4];


        edDia.setText(dia);
       // String bicicleta = bici.replaceAll(" ", "");
        edBicicleta.setText(bici);
        String nick = usuario.replaceAll(" ", "");
        edUsuario.setText(nick);

        EnviarRecibirIdPersona("http://jose-cordones.es/app/consultas/obtenerIdAlquilerBici.php?nick=" + nick+"&bici="+bici+"&dia="+dia);
    }

    public void EnviarRecibirIdPersona(String URL) {

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                response = response.replace("][", ",");

                if (response.length() > 0) {
                    try {

                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson", "" + ja.length());
                        CargarListView(ja);
                        //Toast.makeText(Main_MisBicicletas_Activity.this, idPersona , Toast.LENGTH_SHORT).show();
                        //  EnviarRecibirMisBicicletas("http://jose-cordones.es/app/consultas/obtenerMisBicicletas.php?idpersona=" + idPersona);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

    }

    public void CargarListView(JSONArray ja) {

        ArrayList<String> lista = new ArrayList<>();
        String id = null;
        for (int i = 0; i < ja.length(); i += 1) {

            try {

                id = ("\n" + ja.getString(i + -1));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        tvIDALQUILER.setText(id);
        //EnviarRecibirMisBicicletas("http://jose-cordones.es/app/consultas/obtenerMisBicicletas.php?idpersona=" + id);
        // Toast.makeText(Main_MisBicicletas_Activity.this, "biiiennn  " + id, Toast.LENGTH_SHORT).show();

    }
}
