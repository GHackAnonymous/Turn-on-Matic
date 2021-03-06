package org.egibide.proyecto.proyectocliente;

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


public class BorrarAula extends ActionBarActivity {

    Socket sock;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    EditText codigoText;
    TextView bRTextView;
    Button bConsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_aula);

        codigoText = (EditText)findViewById(R.id.CodigoText);
        bRTextView = (TextView)findViewById(R.id.BRTextView);
        bConsultar = (Button)findViewById(R.id.BConsultar);
        bConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.BConsultar:
                        new HiloConexion().execute();
                        break;
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_borrar_aula, menu);
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

    public class HiloConexion extends AsyncTask<Void, Void, String> {


        @Override
        protected String doInBackground(Void... params) {

            String l = DatosConexion.getIp()+" "+DatosConexion.getPuerto();
            String resultado = "";
            Log.v(null, l);

            try{


                try {
                    sock = new Socket(DatosConexion.getIp(),DatosConexion.getPuerto());
                    out = new ObjectOutputStream(sock.getOutputStream());
                    in = new ObjectInputStream(sock.getInputStream());
                } catch (IOException e) {e.printStackTrace();}

                out.writeUTF("borrarAula");

                Log.v("codigo Introducido: ", codigoText.getText().toString());
                out.writeObject(codigoText.getText().toString());

                Log.v(null,"ENVIADO");

                resultado = (String) in.readObject();
                Log.v(null,resultado);
                String resul = "AULA BORRADA "+codigoText.getText().toString();
                bRTextView.setText(resul);

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
            return resultado;
        }
        protected void onPostExecute(String resultado){

            Toast.makeText(BorrarAula.this,resultado, Toast.LENGTH_SHORT).show();
        }
    }
}
