package org.egibide.proyecto.proyectocliente;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MenuAlumno extends ActionBarActivity {

    final static String ACT_Registrarse = "org.egibide.proyecto.proyectocliente.activity_registrar";
    final static String ACT_ConsultarTurno = "org.egibide.proyecto.proyectocliente.activity_consultar_turno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_alumno);

        Button bRegistrarse = (Button)findViewById(R.id.BRegistrarse);

        bRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.BRegistrarse:
                        Intent act = new Intent(MenuAlumno.this, Registrar.class);
                        act.putExtra(ACT_Registrarse, false);
                        startActivity(act);
                        break;
                }
            }
        });

        Button bConsultarTurno = (Button)findViewById(R.id.BConsultarTurno);

        bConsultarTurno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.BConsultarTurno:
                        Intent act = new Intent(MenuAlumno.this, ConsultarTurno.class);
                        act.putExtra(ACT_ConsultarTurno, false);
                        startActivity(act);
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_alumno, menu);
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
