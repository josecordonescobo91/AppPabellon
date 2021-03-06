package com.example.pabellonlh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main_AddBicicleta_Activity extends AppCompatActivity {

    String objeto;
    EditText edDia, edBici, edUsuario, edTelefono;
    TextView tvIDPERSONA, tvIDBICI;
    Button btnAlquilarBici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__add_bicicleta_);

        edDia = (EditText) findViewById(R.id.edDia);
        edBici = (EditText) findViewById(R.id.edBici);
        edUsuario = (EditText) findViewById(R.id.edUsuario);
        edTelefono = (EditText) findViewById(R.id.edTelefono);
        tvIDPERSONA = (TextView) findViewById(R.id.tvIDPERSONA);
        tvIDBICI = (TextView) findViewById(R.id.tvIDBICI);
        btnAlquilarBici = (Button) findViewById(R.id.btnAlquilarBici);
      /*  Intent intent = getIntent();
        String usuario = intent.getStringExtra("usuario");
        String dia = intent.getStringExtra("dia");
        intent.putExtra("usuario", usuario);*/
        Intent intent = getIntent();
        objeto = intent.getStringExtra("objetoData");
        String[] parts = objeto.split("          ");
        String dia = parts[8];
        String nick = parts[7];
        String bicicleta = parts[3];
       // Toast.makeText(Main_AddBicicleta_Activity.this, bici + "  " + usuario + "  " + dia, Toast.LENGTH_SHORT).show();
        // String sesionNick = parts[4];

        edDia.setText(dia);
        String bici = bicicleta.replaceAll(" ", "");
        edBici.setText(bici);
        final String usuario = nick.replaceAll(" ", "");
        edUsuario.setText(usuario);
       // String usuario = nick.replaceAll(" ", "");

        EnviarRecibirIdPersona("http://jose-cordones.es/app/consultas/obtenerIdPersona_Telefono.php?nick=" + usuario);
        EnviarRecibirIdBicicleta("http://jose-cordones.es/app/consultas/obtenerIdBicicleta.php?dia=" + dia +"&bici="+bici);


        btnAlquilarBici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://jose-cordones.es/app/registrar_bicicleta.php");
                Intent intent = new Intent(getApplicationContext(), Main_Inicio_Activity.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
                finish();

            }
        });

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
                        //Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" , Toast.LENGTH_SHORT).show();
                        CargarListView(ja);

                        //Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" + id + "  " + telefono, Toast.LENGTH_SHORT).show();

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
      //  Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" , Toast.LENGTH_SHORT).show();
        String id = null;
        String telefono = null;
      //  Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" , Toast.LENGTH_SHORT).show();
        for (int i = 0; i < ja.length(); i += 2) {

            try {

                id = ("\n" + ja.getString(i + 0));
                telefono = ("\n" + ja.getString(i + 1));

                //Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" + id+ "  "+ telefono , Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
      //  Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" + id+ "  "+ telefono , Toast.LENGTH_SHORT).show();
        edTelefono.setText(telefono);
        tvIDPERSONA.setText(id);
       // Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" , Toast.LENGTH_SHORT).show();
    }



    public void EnviarRecibirIdBicicleta(String URL) {

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                response = response.replace("][", ",");

                if (response.length() > 0) {
                    try {

                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson", "" + ja.length());
                        //Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" , Toast.LENGTH_SHORT).show();
                        CargarListViewIdBici(ja);

                        //Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" + id + "  " + telefono, Toast.LENGTH_SHORT).show();

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

    public void CargarListViewIdBici(JSONArray ja) {

        ArrayList<String> lista = new ArrayList<>();
        //  Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" , Toast.LENGTH_SHORT).show();
        String idBici = null;
        String telefono = null;
        //  Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" , Toast.LENGTH_SHORT).show();
        for (int i = 0; i < ja.length(); i += 1) {

            try {

                idBici = ("\n" + ja.getString(i + 0));
                //telefono = ("\n" + ja.getString(i + 1));

               // Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn bieeen" + idBici, Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        //  Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" + id+ "  "+ telefono , Toast.LENGTH_SHORT).show();
       // edTelefono.setText(telefono);
        tvIDBICI.setText(idBici);
        // Toast.makeText(Main_AddBicicleta_Activity.this, "biiiennn" , Toast.LENGTH_SHORT).show();
    }

    private void ejecutarServicio(String URL1){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL1, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "REGISTRO CORRECTO", Toast.LENGTH_SHORT).show();
            }
        },new Response.ErrorListener(){
            @Override
            public  void  onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }

        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>parametros = new HashMap<String, String>();
                parametros.put("id_info_bicicletas", tvIDBICI.getText().toString());
                parametros.put("idpersona", tvIDPERSONA.getText().toString());
                parametros.put("telefono", edTelefono.getText().toString());


                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
