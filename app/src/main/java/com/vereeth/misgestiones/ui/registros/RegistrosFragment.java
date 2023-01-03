package com.vereeth.misgestiones.ui.registros;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.vereeth.misgestiones.R;
import com.vereeth.misgestiones.controllers.RegistrosController;
import com.vereeth.misgestiones.modelos.Registro;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegistrosFragment extends Fragment {

    private RegistrosController registrosController;


    private TextView tvElegirDia, tvDia, tvMes, tvAno, tvPositivos, tvNegativos, tvPortas, tvDA, tvHoras, tvCumplimiento, tvRPH, tvEfectividad;
    private ImageButton btnRestarHora, btnAgregarHora, btnRestarPorta, btnAgregarPorta, btnRestarDA, btnAgregarDA, btnRestarPositivo, btnAgregarPositivo, btnRestarNegativo, btnAgregarNegativo;
    private Button btnCrear, btnCerrar, btnGuardar;
    private CalendarView calendarView;
    private LinearLayout barraFecha, layoutCrear, layoutEdicion;
    private int mYear, mMonth, mDay, calculoEfectividad, calculoRPH, calculoCumplimiento;
    private String nuevoDia, nuevoMes, nuevoAno;
    private boolean fechaSeleccionada, abrirEdicion;

    private RegistrosViewModel registrosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        registrosViewModel =
                ViewModelProviders.of(this).get(RegistrosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registros, container, false);

        registrosController = new RegistrosController(getActivity());

        barraFecha = root.findViewById(R.id.barraFecha);
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////TEXTVIEW
        tvElegirDia = root.findViewById(R.id.tvElegirDia);
        tvDia = root.findViewById(R.id.tvDia);
        tvMes = root.findViewById(R.id.tvMes);
        tvAno = root.findViewById(R.id.tvAno);
        tvEfectividad = root.findViewById(R.id.tvEfectividad);
        tvRPH = root.findViewById(R.id.tvRPH);
        tvHoras = root.findViewById(R.id.tvHoras);
        tvCumplimiento = root.findViewById(R.id.tvCumplimiento);
        tvPortas = root.findViewById(R.id.tvPortas);
        tvDA = root.findViewById(R.id.tvDA);
        tvNegativos = root.findViewById(R.id.tvNegativos);
        tvPositivos = root.findViewById(R.id.tvPositivos);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////BOTONES
        btnRestarHora = root.findViewById(R.id.btnRestarHora);
        btnAgregarHora = root.findViewById(R.id.btnAgregarHora);
        btnRestarPorta = root.findViewById(R.id.btnRestarPorta);
        btnAgregarPorta = root.findViewById(R.id.btnAgregarPorta);
        btnRestarDA = root.findViewById(R.id.btnRestarDA);
        btnAgregarDA = root.findViewById(R.id.btnAgregarDA);
        btnRestarPositivo = root.findViewById(R.id.btnRestarPositivo);
        btnAgregarPositivo = root.findViewById(R.id.btnAgregarPositivo);
        btnRestarNegativo = root.findViewById(R.id.btnRestarNegativo);
        btnAgregarNegativo = root.findViewById(R.id.btnAgregarNegativo);

        btnCrear = root.findViewById(R.id.btnCrear);
        btnCerrar = root.findViewById(R.id.btnCerrar);
        btnGuardar = root.findViewById(R.id.btnGuardar);
        layoutCrear = root.findViewById(R.id.layoutCrear);
        layoutEdicion = root.findViewById(R.id.layoutEdicion);
        btnCrear.setVisibility(View.VISIBLE);
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        barraFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirCalendario();
                abrirEdicion = false;
            }

        });


        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ACCION BOTON GUARDAR

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fechaSeleccionada == false) {
                    Snackbar.make(view, "No hay fecha seleccionada", Snackbar.LENGTH_LONG)
                            .setAction("ELEGIR", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    abrirCalendario();
                                    abrirEdicion = true;
                                }
                            })
                            .show();
                } else {
                    edicion(View.VISIBLE);
                    btnCrear.setVisibility(View.GONE);
                }

            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int dia = Integer.parseInt(nuevoDia);
                int mes = Integer.parseInt(nuevoMes);
                int ano = Integer.parseInt(nuevoAno);
                int positivos = Integer.parseInt(tvPositivos.getText().toString());
                int negativos = Integer.parseInt(tvNegativos.getText().toString());
                int portas = Integer.parseInt(tvPortas.getText().toString());
                int da = Integer.parseInt(tvDA.getText().toString());
                int horas = Integer.parseInt(tvHoras.getText().toString());

                Registro nuevoRegistro = new Registro(dia, mes, ano, positivos, negativos, portas, da, horas, calculoCumplimiento, calculoRPH, calculoEfectividad);
                registrosController.nuevoRegistro(nuevoRegistro);

                Toast toast = Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_SHORT);
                toast.show();

                /*else {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                    alertDialog.setTitle("No se guardó");
                    alertDialog.setMessage("La hora debe ser mayor a 0");

                    alertDialog.setPositiveButton(
                            "Aceptar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = alertDialog.create();
                    alert11.show();
                }*/


            }

        });

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edicion(View.GONE);
                btnCrear.setVisibility(View.VISIBLE);
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////ACCION BOTONES PARA MODIFICAR VALORES
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


        tvHoras.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                int horas = Integer.parseInt(tvHoras.getText().toString());
                int positivos = Integer.parseInt(tvPositivos.getText().toString());
                int negativos = Integer.parseInt(tvNegativos.getText().toString());

                if ((Integer.parseInt(tvHoras.getText().toString()) > 0)) {
                    calculoCumplimiento = (horas * 100) / 6;
                    tvCumplimiento.setText(Integer.toString(calculoCumplimiento) + "%");

                    if (calculoCumplimiento >= 100) {
                        tvCumplimiento.setTextColor(getResources().getColor(R.color.colorVerde));
                    } else if (calculoCumplimiento == 0) {
                        tvCumplimiento.setTextColor(getResources().getColor(R.color.colorDefecto));
                    } else {
                        tvCumplimiento.setTextColor(getResources().getColor(R.color.colorRojo));
                    }

                } else {
                    tvCumplimiento.setTextColor(getResources().getColor(R.color.colorDefecto));
                    tvCumplimiento.setText(String.valueOf(0) + "%");
                }

                if ((Integer.parseInt(tvHoras.getText().toString()) > 0)) {
                    calculoRPH = positivos / horas;
                    tvRPH.setText(String.valueOf(calculoRPH));

                    if (calculoRPH >= 2.45) {
                        tvRPH.setTextColor(getResources().getColor(R.color.colorVerde));
                    } else {
                        tvRPH.setTextColor(getResources().getColor(R.color.colorRojo));
                    }

                } else {
                    tvRPH.setTextColor(getResources().getColor(R.color.colorDefecto));
                    tvRPH.setText(String.valueOf(0));
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        tvPositivos.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                int horas = Integer.parseInt(tvHoras.getText().toString());
                int positivos = Integer.parseInt(tvPositivos.getText().toString());
                int negativos = Integer.parseInt(tvNegativos.getText().toString());

                if ((Integer.parseInt(tvHoras.getText().toString()) > 0)) {
                    calculoRPH = positivos / horas;
                    tvRPH.setText(String.valueOf(calculoRPH));

                    if (calculoRPH >= 2.45) {
                        tvRPH.setTextColor(getResources().getColor(R.color.colorVerde));
                    } else {
                        tvRPH.setTextColor(getResources().getColor(R.color.colorRojo));
                        tvEfectividad.setTextColor(getResources().getColor(R.color.colorRojo));
                    }

                } else {
                    tvRPH.setTextColor(getResources().getColor(R.color.colorDefecto));
                    tvRPH.setText(String.valueOf(0));
                }

                if ((Integer.parseInt(tvNegativos.getText().toString())) >= 0 && (Integer.parseInt(tvPositivos.getText().toString())) > 0) {
                    calculoEfectividad = positivos * 100 / (negativos + positivos);
                    tvEfectividad.setText(String.valueOf(calculoEfectividad) + "%");

                    if (calculoEfectividad >= 78.3) {
                        tvEfectividad.setTextColor(getResources().getColor(R.color.colorVerde));
                    } else {
                        tvEfectividad.setTextColor(getResources().getColor(R.color.colorRojo));
                    }

                } else {
                    if ((Integer.parseInt(tvNegativos.getText().toString())) > 0) {
                        tvEfectividad.setTextColor(getResources().getColor(R.color.colorRojo));
                        tvEfectividad.setText(String.valueOf(0) + "%");
                    } else {
                        tvEfectividad.setTextColor(getResources().getColor(R.color.colorDefecto));
                        tvEfectividad.setText(String.valueOf(0) + "%");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        tvNegativos.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                int negativos = Integer.parseInt(tvNegativos.getText().toString());
                int positivos = Integer.parseInt(tvPositivos.getText().toString());

                if ((Integer.parseInt(tvPositivos.getText().toString())) > 0 && (Integer.parseInt(tvNegativos.getText().toString())) >= 0) {
                    calculoEfectividad = positivos * 100 / (negativos + positivos);
                    tvEfectividad.setText(String.valueOf(calculoEfectividad) + "%");

                    if (calculoEfectividad >= 78.3) {
                        tvEfectividad.setTextColor(getResources().getColor(R.color.colorVerde));
                    } else {
                        tvEfectividad.setTextColor(getResources().getColor(R.color.colorRojo));
                    }

                } else {
                    if ((Integer.parseInt(tvNegativos.getText().toString())) > 0) {
                        tvEfectividad.setTextColor(getResources().getColor(R.color.colorRojo));
                        tvEfectividad.setText(String.valueOf(0) + "%");
                    } else {
                        tvEfectividad.setTextColor(getResources().getColor(R.color.colorDefecto));
                        tvEfectividad.setText(String.valueOf(0) + "%");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        return root;
    }


    public void abrirCalendario() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        monthOfYear += 1;
                        nuevoDia = String.valueOf(dayOfMonth);
                        nuevoMes = String.valueOf(monthOfYear);
                        nuevoAno = String.valueOf(year);
                        tvElegirDia.setVisibility(View.GONE);
                        tvDia.setVisibility(View.VISIBLE);
                        tvMes.setVisibility(View.VISIBLE);
                        tvDia.setText(nuevoDia + "/");
                        tvMes.setText(nuevoMes);
                        fechaSeleccionada = true;

                        if (abrirEdicion == true) {
                            edicion(View.VISIBLE);
                            btnCrear.setVisibility(View.GONE);
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void edicion(Integer vista) {
        btnRestarHora.setVisibility(vista);
        btnAgregarHora.setVisibility(vista);
        btnRestarPorta.setVisibility(vista);
        btnAgregarPorta.setVisibility(vista);
        btnRestarDA.setVisibility(vista);
        btnAgregarDA.setVisibility(vista);
        btnRestarPositivo.setVisibility(vista);
        btnAgregarPositivo.setVisibility(vista);
        btnRestarNegativo.setVisibility(vista);
        btnAgregarNegativo.setVisibility(vista);
        layoutEdicion.setVisibility(vista);
    }

    public static void modificarValor(final Boolean sumar, ImageButton imageButton, final TextView textView) {
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int valorInt = Integer.parseInt(textView.getText().toString());
                if (sumar && valorInt < 99) {
                    textView.setText(String.valueOf(valorInt + 1));
                } else if (!sumar && valorInt > 0 || valorInt > 99) {
                    textView.setText(String.valueOf(valorInt - 1));
                }
            }
        });
    }


}