package org.egibide.proyecto.proyectocliente;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Conexion extends ActionBarActivity {

    Button bConectar;
    EditText direccion;
    EditText puerto;
    final static String ACT_LOGIN = "org.egibide.proyecto.proyectocliente.activity_login";
    private Datos dc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conexion);

        FileInputStream filein = null;

        bConectar = (Button)findViewById(R.id.BConectar);
        direccion = (EditText)findViewById(R.id.DireccionText);
        puerto = (EditText)findViewById(R.id.PuertoText);

        try {

            filein = openFileInput("conexion.dat");

            try {

                if (filein.hashCode() > 0) {

                    ObjectInputStream is = new ObjectInputStream(filein);

                    Datos aux = (Datos) is.readObject();

                    if (aux != null) {

                        dc = aux;

                        direccion.setText(dc.getIp());
                        puerto.setText(Integer.toString(dc.getPuerto()));

                        String mensaje = aux.getIp()+" "+Integer.toString(aux.getPuerto());
                        Log.v("DATOS", mensaje);

                    }

                    is.close();

                }

            } catch (ClassNotFoundException ex) {

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        bConectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.BConectar:

                        String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

                        Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
                        Matcher matcher = pattern.matcher(direccion.getText().toString());

                        if (matcher.matches()) {

                            dc = new Datos();

                            dc.setIp(direccion.getText().toString());
                            dc.setPuerto(Integer.parseInt(puerto.getText().toString()));

                            DatosConexion.setIp(direccion.getText().toString());
                            DatosConexion.setPuerto(Integer.parseInt(puerto.getText().toString()));

                            FileOutputStream fileos = null;

                            try {

                            fileos = openFileOutput("conexion.dat", Context.MODE_PRIVATE);

                            ObjectOutputStream dataos = new ObjectOutputStream(fileos);

                            dataos.writeObject(dc);
                            dataos.close();
                            String datos = dc.getIp()+" "+Integer.toString(dc.getPuerto());
                            Log.v("DATOS CONEXION", datos);

                            } catch (FileNotFoundException fileE) {fileE.printStackTrace();
                            } catch (IOException e) {e.printStackTrace();}


                            Intent act = new Intent(Conexion.this, Login.class);
                            act.putExtra(ACT_LOGIN, false);
                            startActivity(act);

                        }else{

                            Toast.makeText(Conexion.this, "ERROR: IP NO VALIDA.", Toast.LENGTH_SHORT).show();

                        }

                    break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_conexion, menu);
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


}
