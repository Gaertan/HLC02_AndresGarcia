package com.example.hlc02_andresgarcia.views.main.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hlc02_andresgarcia.R;
import com.example.hlc02_andresgarcia.modelo.negocio.dominio.Bar;
import com.example.hlc02_andresgarcia.views.main.fragments.timePicker.TimePickerFragment;

import java.util.Calendar;
import java.util.Locale;

public class FormularioActivity extends AppCompatActivity implements TimePickerFragment.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    private EditText editTextNombre, editTextEmail, editTextFecha, editTextTextoMultiLinea,editTextTimeForm;
    private Button fechaReservaButton;
    private CheckBox checkBoxMascota;
    private TextView textViewEnviando;
    private Bar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //toma el bar que se pasa como parametro, porque con logica implementada necesita tomar su correo
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("bar")) {
            Bar bar = (Bar) intent.getSerializableExtra("bar");this.bar=bar;}
        
        //inicializar datos, set on clics
        setContentView(R.layout.layout_form_reserva);
        textViewEnviando = findViewById(R.id.textViewEnviando);
        editTextNombre = findViewById(R.id.editTextNombreForm);
        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextFecha = findViewById(R.id.editTextDateForm);
        editTextTextoMultiLinea = findViewById(R.id.editTextTextMultiLine);
        editTextTimeForm = findViewById(R.id.editTextTimeForm);
        fechaReservaButton = findViewById(R.id.ButtonFechaReservaForm);

        checkBoxMascota = findViewById(R.id.checkBoxMascotaForm);
        textViewEnviando.setText(getString(R.string.enviando_a_literal) + ": " + bar.getNombre());
        fechaReservaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(FormularioActivity.this, FormularioActivity.this, year, month, day);
                datePickerDialog.show();

                TimePickerFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.setOnTimeSetListener(FormularioActivity.this);
                timePickerFragment.show(getSupportFragmentManager(), "timePicker");
            }
        });
        Button buttonEnviar = findViewById(R.id.button);
        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarCampos();
            }


        });



    }

    private void validarCampos() {
        //toma valores de campos y los comprueba
        String nombre = editTextNombre.getText().toString();
        String email = editTextEmail.getText().toString();
        String fecha = editTextFecha.getText().toString();
        String hora = editTextTimeForm.getText().toString();

        if (nombre.isEmpty() || email.isEmpty() || fecha.isEmpty() || hora.isEmpty()) {
            mostrarToast("Todos los campos son obligatorios.");
            return;
        }

        if (!validarMail(email)) {
            mostrarToast("Introduce un correo electrónico válido.");
            return;
        }
        if (!validarFecha()) {
            mostrarToast("La fecha no puede ser anterior a la actual.");
            return;
        }
        mostrarToast("Todo está bien, nos pondremos en contacto contigo.");

        
        //limpia campos y "manda correo"(sin implementar)
        editTextNombre.setText("");
        editTextEmail.setText("");
        editTextFecha.setText("");
        editTextTextoMultiLinea.setText("");

        mandarEmail();
    }

    private void mostrarToast(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }

    public static boolean validarMail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
    private boolean validarFecha() {
        String fechaTexto = editTextFecha.getText().toString();
        String horaTexto = editTextTimeForm.getText().toString();

        //Separa el texto de fecha en año, mes y día
        String[] partesFecha = fechaTexto.split("/");
        int year = Integer.parseInt(partesFecha[2]);
        int month = Integer.parseInt(partesFecha[1]) - 1; // El mes debe empezar desde 0
        int day = Integer.parseInt(partesFecha[0]);

        //Separa el texto de hora en hora y minuto
        String[] partesHora = horaTexto.split(":");
        int hourOfDay = Integer.parseInt(partesHora[0]);
        int minute = Integer.parseInt(partesHora[1]);
        //Crea una fecha a partir de la fecha y tiempo actual y comprueba si es anterior o no a la otra
        Calendar currentDate = Calendar.getInstance();
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(year, month, day, hourOfDay, minute);

        return selectedDate.before(currentDate);
    }

    private void mandarEmail(){
        //TODO : implementar logica(no se pide en el enunciado)

    }

    @Override
    public void onTimeSet(int hourOfDay, int minute) {
        //Actualiza el campo de texto con la fecha(hora) seleccionada
        String time = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
        editTextTimeForm.setText(time);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        //Actualiza el campo de texto con la fecha seleccionada
        String date = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year);
        editTextFecha.setText(date);

    }


}
        
        
        
