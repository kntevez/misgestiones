package com.vereeth.misgestiones;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.vereeth.misgestiones.modelos.Registro;

public class AdaptadorRegistros extends RecyclerView.Adapter<AdaptadorRegistros.MyViewHolder> {

    private List<Registro> listaDeRegistros;

    public void setListaDeRegistros(List<Registro> listaDeRegistros) {
        this.listaDeRegistros = listaDeRegistros;
    }

    public AdaptadorRegistros(List<Registro> registros) {
        this.listaDeRegistros = registros;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View filaRegistro = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_registro, viewGroup, false);
        return new MyViewHolder(filaRegistro);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        // Obtener el registro de nuestra lista gracias al índice i
        Registro registro = listaDeRegistros.get(i);

        // Obtener los datos de la lista
        int diaRegistro = registro.getDia();
        int mesRegistro = registro.getMes();
        int anoRegistro = registro.getAno();
        int positivosRegistro = registro.getPositivos();
        int negativosRegistro = registro.getNegativos();
        int portasRegistro = registro.getPortas();
        int daRegistro = registro.getDA();
        int horasRegistro = registro.getHoras();
        int cumplimientoRegistro = registro.getCumplimiento();
        int rphRegistro = registro.getRPH();
        int efectividadRegistro = registro.getEfectividad();
        // Y poner a los TextView los datos con setText
        myViewHolder.tvColFecha.setText(String.valueOf(diaRegistro + "/" + mesRegistro));
        myViewHolder.tvColPositivos.setText(String.valueOf(positivosRegistro));
        myViewHolder.tvColNegativos.setText(String.valueOf(negativosRegistro));
        myViewHolder.tvColPortas.setText(String.valueOf(portasRegistro));
        myViewHolder.tvColDA.setText(String.valueOf(daRegistro));
        myViewHolder.tvColHoras.setText(String.valueOf(horasRegistro));
        myViewHolder.tvColCumplimiento.setText(String.valueOf(cumplimientoRegistro) + "%");
        myViewHolder.tvColRPH.setText(String.valueOf(rphRegistro));
        myViewHolder.tvColEfectividad.setText(String.valueOf(efectividadRegistro) + "%");

    }

    @Override
    public int getItemCount() {
        return listaDeRegistros.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvColFecha, tvColPositivos, tvColNegativos, tvColPortas, tvColDA, tvColHoras, tvColCumplimiento, tvColRPH, tvColEfectividad;

        MyViewHolder(View itemView) {
            super(itemView);
            this.tvColFecha = itemView.findViewById(R.id.tvColFecha);
            this.tvColPositivos = itemView.findViewById(R.id.tvColPositivos);
            this.tvColNegativos = itemView.findViewById(R.id.tvColNegativos);
            this.tvColPortas = itemView.findViewById(R.id.tvColPortas);
            this.tvColDA = itemView.findViewById(R.id.tvColDA);
            this.tvColHoras = itemView.findViewById(R.id.tvColHoras);
            this.tvColCumplimiento = itemView.findViewById(R.id.tvColCumplimiento);
            this.tvColRPH = itemView.findViewById(R.id.tvColRPH);
            this.tvColEfectividad = itemView.findViewById(R.id.tvColEfectividad);
        }
    }
}
