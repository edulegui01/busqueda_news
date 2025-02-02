package com.api.busqueda.utils;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DateUtils {

    private static final Map<String, String> MONTHS_MAP = new HashMap<>();

    static {
        MONTHS_MAP.put("Enero", "January");
        MONTHS_MAP.put("Febrero", "February");
        MONTHS_MAP.put("Marzo", "March");
        MONTHS_MAP.put("Abril", "April");
        MONTHS_MAP.put("Mayo", "May");
        MONTHS_MAP.put("Junio", "June");
        MONTHS_MAP.put("Julio", "July");
        MONTHS_MAP.put("Agosto", "August");
        MONTHS_MAP.put("Septiembre", "September");
        MONTHS_MAP.put("Octubre", "October");
        MONTHS_MAP.put("Noviembre", "November");
        MONTHS_MAP.put("Diciembre", "December");
    }

    public static String formatDate(String dateText) throws Exception {
        // Limpia espacios y reemplaza "p. m." / "a. m."
        dateText = dateText.trim().replace("p. m.", "PM").replace("a. m.", "AM");

        // Reemplazar el mes en español por su equivalente en inglés
        for (Map.Entry<String, String> entry : MONTHS_MAP.entrySet()) {
            if (dateText.contains(entry.getKey())) {
                dateText = dateText.replace(entry.getKey(), entry.getValue());
                break; // Solo reemplazamos el mes correcto
            }
        }

        // Imprimir para depuración
        System.out.println("Fecha transformada: " + dateText);

        // Formato original con meses en inglés
        SimpleDateFormat originalFormat = new SimpleDateFormat("MMMM dd, yyyy hh:mm a", Locale.ENGLISH);

        // Formato de salida en ISO-8601
        SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        // Parsear la fecha
        Date date = originalFormat.parse(dateText);

        // Retornar la fecha en formato ISO-8601
        return targetFormat.format(date);
    }
}

