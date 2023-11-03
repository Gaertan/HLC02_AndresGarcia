package com.example.hlc02_andresgarcia.views.main.fragments.recyclerViewmain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hlc02_andresgarcia.modelo.negocio.dominio.Bar;
import com.example.hlc02_andresgarcia.R;
import com.example.hlc02_andresgarcia.views.main.NavegacionPrincipal;

import java.util.List;

public class MyBarRecyclerViewAdapter extends RecyclerView.Adapter<MyBarRecyclerViewAdapter.ViewHolder> {


    public interface OnBarClickListener {
        void onBarClick(Bar bar);
    }

    private OnBarClickListener mListener;


    //------------------------
    private final List<Bar> bares;

    public MyBarRecyclerViewAdapter(List<Bar> items, OnBarClickListener listener) {
        bares = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_bar_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bar = bares.get(position);
        holder.nombreBarTextview.setText(holder.bar.getNombre());

        // Carga la imagen desde drawable
            int resourceId = holder.itemView.getContext().getResources().getIdentifier(
                    holder.bar.getImg(), "drawable",
                    holder.itemView.getContext().getPackageName());


        holder.imageviewBar.setImageResource(resourceId);




        // Agregar el clic listener al elemento de la lista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onBarClick(holder.bar);
                }
            }
        });






    }

    @Override
    public int getItemCount() {
        return bares.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        public final TextView nombreBarTextview;
        public final ImageView imageviewBar;
        public Bar bar;

        public ViewHolder(View view) {
            super(view);
            nombreBarTextview = view.findViewById(R.id.nombreBarList);
            imageviewBar = view.findViewById(R.id.imageView);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + nombreBarTextview.getText() + "'";
        }

    }


}