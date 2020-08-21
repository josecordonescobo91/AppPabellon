package com.example.pabellonlh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.ArrayLinkedVariables;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Main_Horario_Activity extends AppCompatActivity {
    ListView lvDatos;
    private AsyncHttpClient cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__horario_);
        lvDatos = (ListView) findViewById(R.id.lvDatos);
        cliente = new AsyncHttpClient ();
        String consulta = "http://jose-cordones.es/app/obtener.php";
        EnviarRecibirDatos(consulta);
        //obtenerHorario();

    }


    public void EnviarRecibirDatos(String URL){

        Toast.makeText(getApplicationContext(), ""+URL, Toast.LENGTH_SHORT).show();

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

        for(int i=0;i<ja.length();i+=9){

            try {

                lista.add(" MOMENTO:                "+ja.getString(i+1)+ "\n" + " LUNES:                       "+ja.getString(i+2)+" \n"+" MARTES:                    "+ja.getString(i+3)+" \n"+" MIERCOLES:              "+ja.getString(i+4)+"\n "+"JUEVES:                     "+ja.getString(i+5)+"\n"+" VIERNES:                    "+ja.getString(i+6)+"\n"+" SABADO:                    "+ja.getString(i+7)+"\n"+" DOMINGO:                  "+ja.getString(i+8));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        lvDatos.setAdapter(adaptador);



    }






















   /* private void obtenerHorario (){
        String url = "http://jose-cordones.es/obtener.php";
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    listarHorario (new String (responseBody));

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }*/

   /* private void listarHorario(String respuesta){

        ArrayList <Horario> lista = new ArrayList <Horario>();
        try{
            String res = respuesta.replace(",", "");
            //String repuestasin = respuesta.replace("u", "w");
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for (int i= 0; i<jsonArreglo.length(); i++){
                Horario h = new Horario();
                h.setMomento((jsonArreglo.getJSONObject(i).getString("momento").replaceAll("M", "j"))+ "\n");
                h.setLunes((jsonArreglo.getJSONObject(i).getString("lunes").replaceAll("lunes", "lu") ) + "\n");;
                h.setMartes((jsonArreglo.getJSONObject(i).getString("martes").replaceAll("=", "") )+ "\n");
                h.setMiercoles((jsonArreglo.getJSONObject(i).getString("miercoles").replaceAll("=", "") ) + "\n");
                h.setJueves((jsonArreglo.getJSONObject(i).getString("jueves").replaceAll("'", "")  )+ "\n");
                h.setViernes((jsonArreglo.getJSONObject(i).getString("viernes").replaceAll("'", "") )+ "\n");
                h.setSabado((jsonArreglo.getJSONObject(i).getString("sabado").replaceAll("'", "") )+ "\n");
                h.setDomingo((jsonArreglo.getJSONObject(i).getString("domingo").replaceAll("'", "") )+ "\n");
                lista.add(h);

            }

            ArrayAdapter <Horario> a = new ArrayAdapter(this,android.R.layout.simple_list_item_1, lista);


            lvDatos.setAdapter(a);
           // lvDatos.Items.Add(a);

        }catch (Exception el) {
            el.printStackTrace();
        }
    }*/
}
