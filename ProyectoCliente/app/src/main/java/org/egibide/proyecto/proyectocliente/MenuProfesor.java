package org.egibide.proyecto.proyectocliente;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MenuProfesor extends ActionBarActivity {

    final static String ACT_ACrearAula = "org.egibide.proyecto.proyectocliente.activity_crear_aula";
    final static String ACT_SiguienteAlumno = "org.egibide.proyecto.proyectocliente.activity_siguiente_alumno";
    final static String ACT_BBorrarAula = "org.egibide.proyecto.proyectocliente.activity_borrar_aula";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_profesor);


        Button bCrearAula = (Button)findViewById(R.id.BCrearAula);

        bCrearAula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.BCrearAula:
                        Intent act = new Intent(MenuProfesor.this, CrearAula.class);
                        act.putExtra(ACT_ACrearAula, false);
                        startActivity(act);
                        break;
                }
            }
        });

        Button bBorrarAula = (Button)findViewById(R.id.BBorrarAula);

        bBorrarAula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.BBorrarAula:
                        Intent act = new Intent(MenuProfesor.this, BorrarAula.class);
                        act.putExtra(ACT_BBorrarAula, false);
                        startActivity(act);
                        break;
                }
            }
        });

        Button bSiguienteAlumno = (Button)findViewById(R.id.BSiguienteAlumno);

        bSiguienteAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.BSiguienteAlumno:
                        Intent act = new Intent(MenuProfesor.this, SiguienteAlumno.class);
                        act.putExtra(ACT_SiguienteAlumno, false);
                        startActivity(act);
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_profesor, menu);
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
