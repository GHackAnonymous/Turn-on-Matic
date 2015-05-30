package org.egibide.proyecto.proyectocliente;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Login extends ActionBarActivity {

    final static String ACT_Profesor = "org.egibide.proyecto.proyectocliente.activity_menu_profesor";
    final static String ACT_Alumno = "org.egibide.proyecto.proyectocliente.activity_menu_alumno";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button bProfesor = (Button)findViewById(R.id.BProfesor);

        bProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.BProfesor:

                        Intent act = new Intent(Login.this, MenuProfesor.class);
                        act.putExtra(ACT_Profesor, false);
                        startActivity(act);
                    break;
                }
            }
        });
        Button bAlumno = (Button)findViewById(R.id.BAlumno);

        bAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.BAlumno:

                        Intent act = new Intent(Login.this, MenuAlumno.class);
                        act.putExtra(ACT_Alumno, false);
                        startActivity(act);
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
