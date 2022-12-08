package com.example.alumnos.adaptadores;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alumnos.R;
import com.example.alumnos.VerActivity;
import com.example.alumnos.entidades.Alumnos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaAlumnosAdapter extends RecyclerView.Adapter<ListaAlumnosAdapter.AlumnosViewHolder> {
    ArrayList<Alumnos> listaAlumnos;
    ArrayList<Alumnos> listaOriginal;

    public ListaAlumnosAdapter(ArrayList<Alumnos> listaAlumnos){
        this.listaAlumnos= listaAlumnos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaAlumnos);
    }

    @NonNull
    @Override
    public AlumnosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_alumno, null,
                false);
        return new AlumnosViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AlumnosViewHolder holder, int position) {

        holder.viewMatricula.setText(listaAlumnos.get(position).getMatriculaA());
        holder.viewNombre.setText(listaAlumnos.get(position).getNombre());
        holder.viewApellidos.setText(listaAlumnos.get(position).getApellidos());
        holder.viewSexo.setText(listaAlumnos.get(position).getSexo());
        holder.viewFecha.setText(listaAlumnos.get(position).getFecha());

    }

    public void filtradro(final String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud== 0){
            listaAlumnos.clear();
            listaAlumnos.addAll(listaOriginal);
        }else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Alumnos> collecion = listaAlumnos.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                listaAlumnos.clear();
                listaAlumnos.addAll(collecion);
            }else{
                for (Alumnos c: listaOriginal){
                    if (c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaAlumnos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class AlumnosViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewApellidos, viewSexo, viewFecha, viewMatricula;

        public AlumnosViewHolder(@NonNull View itemView) {
            super(itemView);

            viewMatricula=itemView.findViewById(R.id.viewMatricula);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewApellidos = itemView.findViewById(R.id.viewApellidos);
            viewSexo = itemView.findViewById(R.id.viewSexo);
            viewFecha = itemView.findViewById(R.id.viewFecha);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("MATRICULA", listaAlumnos.get(getAdapterPosition()).getMatricula());
                    context.startActivity(intent);
                }
            });
        }
    }
}

