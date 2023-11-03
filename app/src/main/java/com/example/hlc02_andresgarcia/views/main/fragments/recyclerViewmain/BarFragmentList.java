package com.example.hlc02_andresgarcia.views.main.fragments.recyclerViewmain;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hlc02_andresgarcia.R;
import com.example.hlc02_andresgarcia.modelo.negocio.BarManager;
import com.example.hlc02_andresgarcia.modelo.negocio.dominio.Bar;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class BarFragmentList extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BarFragmentList() {
    }

    public static BarFragmentList newInstance(int columnCount) {
        BarFragmentList fragment = new BarFragmentList();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //crea una vista "inflandola" con el fragmento de la lista
        View view = inflater.inflate(R.layout.fragment_bar_list, container, false);

        //añade el adaptador con los elementos bar de la array que se toman mas adelante
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            //si es 1 columna,linearlayout
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            //instancia el adapter con la lista y pasandole la activity para que escuche los clics
            recyclerView.setAdapter(
                    new MyBarRecyclerViewAdapter(
                            BarManager.getInstance().getListaBares(),
                            (MyBarRecyclerViewAdapter.OnBarClickListener) this.getActivity()));
        }
        return view;
    }





}