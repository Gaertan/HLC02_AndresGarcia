package com.example.hlc02_andresgarcia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hlc02_andresgarcia.views.main.NavegacionPrincipal;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    Animation animSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //MyApplication myApplication = (MyApplication) this.getApplication();
       // myApplication.logoutUser();
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        } catch (Exception e) {
            Toast.makeText(this,
                    "Hubo una excepcion en main", Toast.LENGTH_LONG).show();
            System.out.println(e);
        }


        //creo la carpeta "anim" donde voy a definir que va a hacer cada animación
        animSplash = AnimationUtils.loadAnimation(this, R.anim.anim_splash);


        //uno cada elemento
        ImageView logo = findViewById(R.id.logo);
        TextView nombre = findViewById(R.id.textBottom);
        TextView nombreCiclo = findViewById(R.id.textProgramacion2);
        TextView slogan = findViewById(R.id.textProgramacion);

        Button boton = findViewById(R.id.buttonLogin);
        boton.setOnClickListener((View.OnClickListener) this);

        //lanzo las animaciones
        logo.setAnimation(animSplash);
        nombreCiclo.setAnimation(animSplash);
        slogan.setAnimation(animSplash);
        nombre.setAnimation(animSplash);
        boton.setAnimation(animSplash);

    }

    @Override
    public void onClick(View view) {
        // Intent intent = new Intent(this, LoginScreen.class); DEPRECATED
        Intent intent = new Intent(this, NavegacionPrincipal.class);
        startActivity(intent);
    }
}
