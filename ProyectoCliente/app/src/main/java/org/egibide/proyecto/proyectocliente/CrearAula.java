package org.egibide.proyecto.proyectocliente;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class CrearAula extends ActionBarActivity {


    Socket sock;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    EditText nombreAula;
    EditText nombreProfesor;
    TextView codigoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_aula);

        nombreAula = (EditText)findViewById(R.id.NombreAulaText);
        nombreProfesor = (EditText)findViewById(R.id.NombreProfesorText);
        codigoTextView = (TextView)findViewById(R.id.CodigoTextView);
        Button bSiguiente = (Button)findViewById(R.id.BSiguiente);
        bSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.BSiguiente:
                        new HiloConexion().execute();
                    break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_crear_aula, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class HiloConexion extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {

            String l = DatosConexion.getIp()+" "+DatosConexion.getPuerto();
            Log.v(null, l);

            try{

                try {

                    sock = new Socket(DatosConexion.getIp(),DatosConexion.getPuerto());

                    out = new ObjectOutputStream(sock.getOutputStream());
                    in = new ObjectInputStream(sock.getInputStream());

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(CrearAula.this, "ERROR: CONEXION FALLIDA", Toast.LENGTH_SHORT).show();
                }

                out.writeUTF("crearAula");
                out.writeUTF(nombreAula.getText().toString());
                out.writeObject(nombreProfesor.getText().toString());

                String cod = null;

                try {

                    cod = (String) in.readObject();

                } catch (ClassNotFoundException e) {

                    e.printStackTrace();

                }

                Log.v(null, cod);

                codigoTextView.setText(cod);

                out.close();
                in.close();
                sock.close();

            } catch (Exception ex){
                try {
                    out.close();
                    in.close();
                    sock.close();
                } catch (IOException e) {e.printStackTrace();}
            }
            return null;
        }
        protected void onPostExecute(){

        }
    }
}


