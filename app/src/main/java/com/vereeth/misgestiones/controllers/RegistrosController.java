package com.vereeth.misgestiones.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import com.vereeth.misgestiones.AyudanteBaseDeDatos;
import com.vereeth.misgestiones.modelos.Registro;


public class RegistrosController {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String TABLA_REGISTROS = "registros";

    public RegistrosController(Context contexto) {
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(contexto);
    }


    public int eliminarRegistro(Registro registro) {

        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        String[] argumentos = {String.valueOf(registro.getId())};
        return baseDeDatos.delete(TABLA_REGISTROS, "id = ?", argumentos);
    }

    public long nuevoRegistro(Registro registro) {
        // writable porque vamos a insertar
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("dia", registro.getDia());
        valoresParaInsertar.put("mes", registro.getMes());
        valoresParaInsertar.put("ano", registro.getAno());
        valoresParaInsertar.put("positivos", registro.getPositivos());
        valoresParaInsertar.put("negativos", registro.getNegativos());
        valoresParaInsertar.put("portas", registro.getPortas());
        valoresParaInsertar.put("da", registro.getDA());
        valoresParaInsertar.put("horas", registro.getHoras());
        valoresParaInsertar.put("cumplimiento", registro.getCumplimiento());
        valoresParaInsertar.put("rph", registro.getRPH());
        valoresParaInsertar.put("efectividad", registro.getEfectividad());
        return baseDeDatos.insert(TABLA_REGISTROS, null, valoresParaInsertar);
    }

    public int guardarCambios(Registro registroEditado) {
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaActualizar = new ContentValues();
        valoresParaActualizar.put("dia", registroEditado.getDia());
        valoresParaActualizar.put("mes", registroEditado.getMes());
        valoresParaActualizar.put("ano", registroEditado.getAno());
        valoresParaActualizar.put("positivos", registroEditado.getPositivos());
        valoresParaActualizar.put("negativos", registroEditado.getNegativos());
        valoresParaActualizar.put("portas", registroEditado.getPortas());
        valoresParaActualizar.put("da", registroEditado.getDA());
        valoresParaActualizar.put("horas", registroEditado.getHoras());
        valoresParaActualizar.put("cumplimiento", registroEditado.getCumplimiento());
        valoresParaActualizar.put("rph", registroEditado.getRPH());
        valoresParaActualizar.put("efectividad", registroEditado.getEfectividad());
        // where id...
        String campoParaActualizar = "id = ?";
        // ... = idRegistro
        String[] argumentosParaActualizar = {String.valueOf(registroEditado.getId())};
        return baseDeDatos.update(TABLA_REGISTROS, valoresParaActualizar, campoParaActualizar, argumentosParaActualizar);
    }

    public ArrayList<Registro> obtenerRegistros() {
        ArrayList<Registro> registros = new ArrayList<>();
        // readable porque no vamos a modificar, solamente leer
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getReadableDatabase();
        // SELECT dia, mes, ano, positivos, negativos, id
        String[] columnasAConsultar = {"dia", "mes", "ano", "positivos", "negativos", "portas", "da", "horas", "cumplimiento", "rph", "efectividad", "id"};
        Cursor cursor = baseDeDatos.query(
                TABLA_REGISTROS,//from registros
                columnasAConsultar,
                null,
                null,
                null,
                null,
                "ano DESC, mes DESC, dia DESC"
        );

        if (cursor == null) {
            /*
                Salimos aquí porque hubo un error, regresar
                lista vacía
             */
            return registros;

        }
        // Si no hay datos, igualmente regresamos la lista vacía
        if (!cursor.moveToFirst()) return registros;

        // En caso de que sí haya, iteramos y vamos agregando los
        // datos a la lista de registros
        do {
            // El 0 es el número de la columna, como seleccionamos
            // dia, mes, ano, positivos, id entonces la dia es 0, mes 1 y ano es 2
            int diaObtenidoDeBD = cursor.getInt(0);
            int mesObtenidoDeBD = cursor.getInt(1);
            int anoObtenidoDeBD = cursor.getInt(2);
            int positivosObtenidoDeBD = cursor.getInt(3);
            int negativosObtenidoDeBD = cursor.getInt(4);
            int portasObtenidoDeBD = cursor.getInt(5);
            int daObtenidoDeBD = cursor.getInt(6);
            int horasObtenidoDeBD = cursor.getInt(7);
            int cumplimientoObtenidoDeBD = cursor.getInt(8);
            int rphObtenidoDeBD = cursor.getInt(9);
            int efectividadObtenidoDeBD = cursor.getInt(10);

            long idRegistro = cursor.getLong(11);
            Registro registroObtenidoDeBD = new Registro(diaObtenidoDeBD, mesObtenidoDeBD, anoObtenidoDeBD, positivosObtenidoDeBD, negativosObtenidoDeBD, portasObtenidoDeBD, daObtenidoDeBD, horasObtenidoDeBD,
                    cumplimientoObtenidoDeBD, rphObtenidoDeBD, efectividadObtenidoDeBD, idRegistro);
            registros.add(registroObtenidoDeBD);
        } while (cursor.moveToNext());

        // Fin del ciclo. Cerramos cursor y regresamos la lista de registros :)
        cursor.close();
        return registros;
    }
}