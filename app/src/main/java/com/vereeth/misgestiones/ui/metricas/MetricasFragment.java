package com.vereeth.misgestiones.ui.metricas;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vereeth.misgestiones.AdaptadorRegistros;
import com.vereeth.misgestiones.R;
import com.vereeth.misgestiones.RecyclerTouchListener;
import com.vereeth.misgestiones.controllers.RegistrosController;
import com.vereeth.misgestiones.modelos.Registro;
import com.vereeth.misgestiones.ui.registros.RegistrosFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MetricasFragment extends Fragment {


    private List<Registro> listaDeRegistros;
    private RecyclerView recyclerView;
    private AdaptadorRegistros adaptadorRegistros;
    private RegistrosController registrosController;
    private MetricasViewModel metricasViewModel;
    private String actualizarDia, actualizarMes, actualizarAno;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        metricasViewModel =
                ViewModelProviders.of(this).get(MetricasViewModel.class);
        View root = inflater.inflate(R.layout.fragment_metricas, container, false);
        /*final TextView textView = root.findViewById(R.id.text_registro);
        registroViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        // Lo siguiente sí es nuestro ;)
        // Definir nuestro controlador
        registrosController = new RegistrosController(getActivity());

        // Instanciar vistas
        recyclerView = root.findViewById(R.id.recyclerViewRegistros);


        // Por defecto es una lista vacía,
        // se la ponemos al adaptador y configuramos el recyclerView
        listaDeRegistros = new ArrayList<>();
        adaptadorRegistros = new AdaptadorRegistros(listaDeRegistros);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptadorRegistros);

        // Una vez que ya configuramos el RecyclerView le ponemos los datos de la BD
        refrescarListaDeRegistros();

        // Listener de los clicks en la lista, o sea el RecyclerView
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override // Un toque sencillo
            public void onClick(View view, int position) {
                // Pasar a la actividad EditarRegistroActivity.java
                Registro registroSeleccionado = listaDeRegistros.get(position);

                long idRegistro = registroSeleccionado.getId();
                int diaRegistro = registroSeleccionado.getDia();
                int mesRegistro = registroSeleccionado.getMes();
                int anoRegistro = registroSeleccionado.getAno();
                int positivosRegistro = registroSeleccionado.getPositivos();
                int negativosRegistro = registroSeleccionado.getNegativos();
                int portasRegistro = registroSeleccionado.getPortas();
                int daRegistro = registroSeleccionado.getDA();
                int horasRegistro = registroSeleccionado.getHoras();
                int cumplimientoRegistro = registroSeleccionado.getCumplimiento();
                int rphRegistro = registroSeleccionado.getRPH();
                int efectividadRegistro = registroSeleccionado.getEfectividad();

                final Registro registro = new Registro(diaRegistro, mesRegistro, anoRegistro, positivosRegistro, negativosRegistro, portasRegistro, daRegistro, horasRegistro, cumplimientoRegistro, rphRegistro, efectividadRegistro, idRegistro);

                final Dialog dialogRegistro = new Dialog(getActivity());
                dialogRegistro.setContentView(R.layout.dialog_registro);

                ImageButton btnRestarHora = dialogRegistro.findViewById(R.id.btnRestarHora);
                ImageButton btnAgregarHora = dialogRegistro.findViewById(R.id.btnAgregarHora);
                ImageButton btnRestarPorta = dialogRegistro.findViewById(R.id.btnRestarPorta);
                ImageButton btnAgregarPorta = dialogRegistro.findViewById(R.id.btnAgregarPorta);
                ImageButton btnRestarDA = dialogRegistro.findViewById(R.id.btnRestarDA);
                ImageButton btnAgregarDA = dialogRegistro.findViewById(R.id.btnAgregarDA);
                ImageButton btnRestarPositivo = dialogRegistro.findViewById(R.id.btnRestarPositivo);
                ImageButton btnAgregarPositivo = dialogRegistro.findViewById(R.id.btnAgregarPositivo);
                ImageButton btnRestarNegativo = dialogRegistro.findViewById(R.id.btnRestarNegativo);
                ImageButton btnAgregarNegativo = dialogRegistro.findViewById(R.id.btnAgregarNegativo);

                final TextView tvDia = dialogRegistro.findViewById(R.id.tvDia);
                final TextView tvMes = dialogRegistro.findViewById(R.id.tvMes);
                final TextView tvAno = dialogRegistro.findViewById(R.id.tvAno);
                final TextView tvEfectividad = dialogRegistro.findViewById(R.id.tvEfectividad);
                final TextView tvRPH = dialogRegistro.findViewById(R.id.tvRPH);
                final TextView tvHoras = dialogRegistro.findViewById(R.id.tvHoras);
                final TextView tvCumplimiento = dialogRegistro.findViewById(R.id.tvCumplimiento);
                final TextView tvPortas = dialogRegistro.findViewById(R.id.tvPortas);
                final TextView tvDA = dialogRegistro.findViewById(R.id.tvDA);
                final TextView tvNegativos = dialogRegistro.findViewById(R.id.tvNegativos);
                final TextView tvPositivos = dialogRegistro.findViewById(R.id.tvPositivos);

                LinearLayout barraFecha = dialogRegistro.findViewById(R.id.barraFecha);

                Button btnCerrarDialog = dialogRegistro.findViewById(R.id.btnCerrar);
                Button btnGuardarDialog = dialogRegistro.findViewById(R.id.btnGuardar);

                dialogRegistro.show();
                Window window = dialogRegistro.getWindow();
                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                actualizarDia = String.valueOf(registro.getDia());
                actualizarMes = String.valueOf(registro.getMes());
                actualizarAno = String.valueOf(registro.getAno());

                tvDia.setText(actualizarDia + "/");
                tvMes.setText(actualizarMes + "/");
                tvAno.setText(actualizarAno);
                tvPositivos.setText(String.valueOf(registro.getPositivos()));
                tvNegativos.setText(String.valueOf(registro.getNegativos()));
                tvPortas.setText(String.valueOf(registro.getPortas()));
                tvDA.setText(String.valueOf(registro.getDA()));
                tvHoras.setText(String.valueOf(registro.getHoras()));
                tvCumplimiento.setText(String.valueOf(registro.getCumplimiento()));
                tvRPH.setText(String.valueOf(registro.getRPH()));
                tvEfectividad.setText(String.valueOf(registro.getEfectividad()));


                RegistrosFragment.modificarValor(false, btnRestarHora, tvHoras);
                RegistrosFragment.modificarValor(true, btnAgregarHora, tvHoras);
                RegistrosFragment.modificarValor(false, btnRestarPorta, tvPortas);
                RegistrosFragment.modificarValor(true, btnAgregarPorta, tvPortas);
                RegistrosFragment.modificarValor(false, btnRestarDA, tvDA);
                RegistrosFragment.modificarValor(true, btnAgregarDA, tvDA);
                RegistrosFragment.modificarValor(false, btnRestarPositivo, tvPositivos);
                RegistrosFragment.modificarValor(true, btnAgregarPositivo, tvPositivos);
                RegistrosFragment.modificarValor(false, btnRestarNegativo, tvNegativos);
                RegistrosFragment.modificarValor(true, btnAgregarNegativo, tvNegativos);

                barraFecha.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final Calendar c = Calendar.getInstance();
                        int mYear = c.get(Calendar.YEAR);
                        int mMonth = c.get(Calendar.MONTH);
                        int mDay = c.get(Calendar.DAY_OF_MONTH);


                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {
                                        monthOfYear += 1;
                                        actualizarDia = String.valueOf(dayOfMonth);
                                        actualizarMes = String.valueOf(monthOfYear);
                                        actualizarAno = String.valueOf(year);
                                        tvDia.setText(actualizarDia + "/");
                                        tvMes.setText(actualizarMes + "/");
                                        tvAno.setText(actualizarAno);
                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }

                });

                btnGuardarDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int nuevoDia = Integer.parseInt(actualizarDia);
                        int nuevoMes = Integer.parseInt(actualizarMes);
                        int nuevoAno = Integer.parseInt(actualizarAno);
                        int nuevoPositivos = Integer.parseInt(tvPositivos.getText().toString());
                        int nuevoNegativos = Integer.parseInt(tvNegativos.getText().toString());
                        int nuevoPortas = Integer.parseInt(tvPortas.getText().toString());
                        int nuevoDA = Integer.parseInt(tvDA.getText().toString());
                        int nuevoHoras = Integer.parseInt(tvHoras.getText().toString());
                        int nuevoCumplimiento = Integer.parseInt(tvCumplimiento.getText().toString());
                        int nuevoRPH = Integer.parseInt(tvRPH.getText().toString());
                        int nuevoEfectividad = Integer.parseInt(tvEfectividad.getText().toString());


                        Registro registroConNuevosCambios = new Registro(nuevoDia, nuevoMes, nuevoAno, nuevoPositivos, nuevoNegativos, nuevoPortas, nuevoDA, nuevoHoras, nuevoCumplimiento, nuevoRPH, nuevoEfectividad, registro.getId());
                        registrosController.guardarCambios(registroConNuevosCambios);
                        dialogRegistro.cancel();
                        refrescarListaDeRegistros();
                    }
                });

                btnCerrarDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogRegistro.cancel();
                    }
                });
            }

            @Override // Un toque largo
            public void onLongClick(View view, int position) {
                final Registro registroParaEliminar = listaDeRegistros.get(position);
                AlertDialog dialog = new AlertDialog
                        .Builder(getActivity())
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                registrosController.eliminarRegistro(registroParaEliminar);
                                refrescarListaDeRegistros();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("¿Eliminar al registro del " + registroParaEliminar.getDia() + "/" + registroParaEliminar.getMes() + "/" + registroParaEliminar.getAno() + "?")
                        .create();
                dialog.show();

            }
        }));


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        refrescarListaDeRegistros();
    }

    public void refrescarListaDeRegistros() {
        if (adaptadorRegistros == null) return;
        listaDeRegistros = registrosController.obtenerRegistros();
        adaptadorRegistros.setListaDeRegistros(listaDeRegistros);
        adaptadorRegistros.notifyDataSetChanged();
    }

}

