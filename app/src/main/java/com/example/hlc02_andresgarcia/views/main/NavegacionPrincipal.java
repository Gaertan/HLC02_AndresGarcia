package com.example.hlc02_andresgarcia.views.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hlc02_andresgarcia.MainActivity;
import com.example.hlc02_andresgarcia.R;
import com.example.hlc02_andresgarcia.modelo.negocio.dominio.Bar;
import com.example.hlc02_andresgarcia.views.main.fragments.FormularioActivity;
import com.example.hlc02_andresgarcia.views.main.fragments.dialogFragmentAbout.DialogAbout;
import com.example.hlc02_andresgarcia.views.main.fragments.fragmentBarDetails.FragmentDetailsBar;
import com.example.hlc02_andresgarcia.views.main.fragments.recyclerViewmain.BarFragmentList;
import com.example.hlc02_andresgarcia.views.main.fragments.recyclerViewmain.MyBarRecyclerViewAdapter;

import java.util.ArrayList;

public class NavegacionPrincipal extends AppCompatActivity implements MyBarRecyclerViewAdapter.OnBarClickListener, FragmentDetailsBar.OnBarDetailClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //instancia la vista y añade la toolbar.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion_principal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    //si no se ha restaurado su estado anterior(no tiene), infla la vista con el primer fragmento,el de los bares
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView, BarFragmentList.newInstance(1))
                    .commitNow();
        }
    }







    //-------------------------------------------flujo y resultado dialogo confirmar

    @Override
    public void onBackPressed() {
        volverOcerrar();
    }
    private void volverOcerrar(){
        //comprueba si se puede volver en fragmentos, si no, abre de nuevo la main activity(splash screen)
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }}

    @Override
    public void onBarDetailClick(String comando,Bar bar) {
        //se podria haber implementado todos los botones/logica de la pantalla de detalles del bar en esta activity
        //y en este metodo hacer un if() llamando a metodos dependiendo del boton(gracias a pasar su comando)
        mostrarFormulario(bar);
    }

    private void mostrarFormulario(Bar bar){
        //nueva activity, se le pasa el bar que se recibe en el clic del fragmento detalles
        Intent intent = new Intent(this, FormularioActivity.class);
        intent.putExtra("bar", bar);
        startActivity(intent);
    }


    @Override
    public void onBarClick(Bar bar) {
        mostrarDetallesBar(bar);
    }
    private void mostrarDetallesBar(Bar bar) {
        //Reemplaza el fragmento actual por el FragmentDetailsBar con el Bar seleccionado en el recycler
        //notese que se pasa esta activity como listener para comunicarse
        FragmentDetailsBar fragmentDetailsBar = FragmentDetailsBar.newInstance(bar,this);
        cambiarFragmento(fragmentDetailsBar);
    }

    public void cambiarFragmento(Fragment fragment) {
        //toma un fragmento y cambia el contenido del contenedor de fragmentos
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, fragment)
                .addToBackStack(null)
                .commit();
    }


    //-------------------------------------------menús


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    //obtiene el elemento de menu seleccionado y llama el metodo relacionado
        int id = item.getItemId();
        if(id ==  R.id.menu_acerca_de){
            showScrollDialogAbout();
         /*   Toast.makeText(this,
                    "Has pulsado la opción acerca_de", Toast.LENGTH_SHORT).show();
*/
            return true;
        }
        return false;
    }

    private void showScrollDialogAbout() {
        //instancia el dialogo de about del menu
        FragmentManager fm = getSupportFragmentManager();
        DialogAbout dialog = DialogAbout.newInstance("");
        dialog.show(fm, "fragment_about_dialog");
    }



}