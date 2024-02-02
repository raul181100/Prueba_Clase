package com.example.prueba_clase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListElement> listaPaises;
    private LayoutInflater mInflater;
    private Context context;
    private OnItemClickListener listener;

    public ListAdapter(List<ListElement> listaPaises, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.listaPaises = listaPaises;
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ciudad, pais, anio;

        public ViewHolder(View itemView) {
            super(itemView);
            ciudad = itemView.findViewById(R.id.ciudadTextView);
            pais = itemView.findViewById(R.id.paisTextView);
            anio = itemView.findViewById(R.id.fechaTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAbsoluteAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public int getItemCount() {
        return listaPaises.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_element, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListElement pais = listaPaises.get(position);
        holder.ciudad.setText(pais.getCiudad());
        holder.pais.setText(pais.getPais());
        holder.anio.setText(pais.getAnio());
    }
}
