package com.example.hlc02_andresgarcia.views.main.fragments.fragmentBarDetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import com.example.hlc02_andresgarcia.R;
import com.example.hlc02_andresgarcia.modelo.negocio.dominio.Bar;
import com.example.hlc02_andresgarcia.modelo.negocio.dominio.Servicios;
import com.example.hlc02_andresgarcia.views.main.fragments.recyclerViewmain.MyBarRecyclerViewAdapter;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentDetailsBar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentDetailsBar extends Fragment {

    //NOTA NO OLVIDARSE CAMBIAR MANIFEST PARA TTS
    private TextToSpeech textToSpeech;
    private Bar bar;

    public interface OnBarDetailClickListener {
        void onBarDetailClick(String comando,Bar bar);
    }

    private OnBarDetailClickListener mListener;


    public FragmentDetailsBar() {
        // Constructor vacío requerido.
    }

    public static FragmentDetailsBar newInstance(Bar bar,OnBarDetailClickListener mListener) {
        //Inicializa el fragmento, obteniendo el bar pasado y el listener(main activity)
        FragmentDetailsBar fragment = new FragmentDetailsBar();
        Bundle args = new Bundle();
        args.putSerializable("bar", bar);
        fragment.setArguments(args);
        fragment.mListener = mListener;
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bar = (Bar) getArguments().getSerializable("bar");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //infla la vista de navegacion
        View view = inflater.inflate(R.layout.element_bar_details, container, false);

        if (bar != null) {
            //Enlazar vistas de la GUI con los componentes
            TextView nombreTextView = view.findViewById(R.id.TextViewCardBarNombre);
            ImageView imageView = view.findViewById(R.id.imageViewCardBar);
            TextView serviciosTexto = view.findViewById(R.id.textViewserviciosBarDetails);
            TextView detallesTexto = view.findViewById(R.id.editTextTextMultiLineBarDetails);

            Button mapaButton = view.findViewById(R.id.buttonCardBarMapa);
            Button videoButton = view.findViewById(R.id.buttonCardBarVideo);
            Button llamarButton = view.findViewById(R.id.buttonCardBarLlamar);
            Button audioButton = view.findViewById(R.id.buttonCardBarAudio);
            Button reservaButton = view.findViewById(R.id.buttonCardBarReserva);

            //Establece los valores de los componentes con los datos del objeto Bar
            nombreTextView.setText(bar.getNombre());
            detallesTexto.setText(bar.getDescripcion());

            imageView.setImageResource(getResources().getIdentifier(bar.getImg(), "drawable", getContext().getPackageName()));

            StringBuilder servicios = new StringBuilder();
            if (bar.getServicios() != null) {
                for (Servicios servicio : bar.getServicios()) {
                    servicios.append(servicio.getDescripcion()).append("\n");


                }
            }

            serviciosTexto.setText(servicios);
        //On clicks

            mapaButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + bar.getDireccion());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });

            videoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(bar.getVideo()));
                    startActivity(intent);
                }
            });
            llamarButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + bar.getTelefono()));
                    startActivity(intent);
                }
            });
            audioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //toma descripcion y la lee mediante tts
                    reproducirAudioguia(detallesTexto.getText().toString());                }
            });
            reservaButton.setOnClickListener(new View.OnClickListener() {
                //llama al metodo de la activity principal para abrir registro
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onBarDetailClick("registrar", bar);
                    }
                }
            });
        //inicializa TTS
            textToSpeech = new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status == TextToSpeech.SUCCESS) {
                        textToSpeech.setLanguage(Locale.getDefault());
                    }
                }
            });

        }

        return view;
    }


    @Override
    public void onDestroyView() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroyView();
    }

    private void reproducirAudioguia(String texto) {
        if (textToSpeech != null) {
            textToSpeech.speak(texto, TextToSpeech.QUEUE_FLUSH, null, null);
        }


    }


}