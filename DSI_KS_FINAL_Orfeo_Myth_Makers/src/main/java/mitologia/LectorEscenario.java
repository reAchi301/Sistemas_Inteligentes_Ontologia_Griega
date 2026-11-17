package mitologia;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mitologia.modelo.HijoDe;
import mitologia.modelo.Localiza;
import mitologia.modelo.Mision;
import mitologia.modelo.NoPosee;
import mitologia.modelo.Posee;
import mitologia.modelo.PresoDe;
import mitologia.modelo.TieneCapacidad;
import mitologia.modelo.TieneEnojo;
import mitologia.modelo.TieneFavor;

/**
 * Parser de los ficheros EscenarioFx-y.txt.
 * SOLO traduce cada linea de texto a un hecho basico (nota O del enunciado):
 * aqui no se infiere nada, eso lo hacen las reglas.
 */
public class LectorEscenario {

    /** Palabras que se ignoran al formar nombres: "Escudo de Bronce" -> EscudoBronce */
    private static final Set<String> CONECTORES = new HashSet<String>(Arrays.asList(
            "de", "del", "la", "las", "el", "los", "a", "al"));

    // Pregunta
    private static final Pattern P_ACCION = Pattern.compile("(?i)^puede (.+?) (liberar|matar|apresar) a (.+)$");
    private static final Pattern P_OBTENER = Pattern.compile("(?i)^puede (.+?) (?:obtener|conseguir) (.+)$");
    private static final Pattern P_TENER_CAP = Pattern.compile("(?i)^puede (.+?) tener la capacidad de (.+)$");
    // Condiciones (el orden importa: de la mas especifica a la mas general)
    private static final Pattern P_FAVOR = Pattern.compile("(?i)^(.+?) tiene el favor de (.+)$");
    private static final Pattern P_ENOJO = Pattern.compile("(?i)^(.+?) tiene el enojo de (.+)$");
    private static final Pattern P_CAPACIDAD = Pattern.compile("(?i)^(.+?) tiene la capacidad (?:de )?(.+)$");
    private static final Pattern P_NO_TIENE = Pattern.compile("(?i)^(.+?) no tiene (.+)$");
    private static final Pattern P_LOCALIZA = Pattern.compile("(?i)^(.+?) localiza a (.+)$");
    private static final Pattern P_PRESO = Pattern.compile("(?i)^(.+?) (?:es|esta) pres[oa] de (.+)$");
    private static final Pattern P_HIJO = Pattern.compile("(?i)^(.+?) es hij[oa] de (.+)$");
    private static final Pattern P_TIENE = Pattern.compile("(?i)^(.+?) tiene (.+)$");

    private final List<Object> hechos = new ArrayList<Object>();
    private final List<String> lineasNoEntendidas = new ArrayList<String>();
    private String textoOriginal = "";

    public void leer(Path fichero) throws IOException {
        List<String> lineas = Files.readAllLines(fichero, StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        for (String linea : lineas) {
            sb.append(linea).append("\n");
            procesarLinea(linea);
        }
        textoOriginal = sb.toString();
    }

    private void procesarLinea(String linea) {
        String l = linea.replace("¿", "").replace("?", "").trim();
        if (l.endsWith(".")) l = l.substring(0, l.length() - 1).trim();
        if (l.isEmpty() || l.toLowerCase().startsWith("condiciones")) return;

        Matcher m;
        if ((m = P_TENER_CAP.matcher(l)).matches()) {
            hechos.add(new Mision("capacidad", nombre(m.group(1)), nombre(m.group(2))));
        } else if ((m = P_ACCION.matcher(l)).matches()) {
            hechos.add(new Mision(m.group(2).toLowerCase(), nombre(m.group(1)), nombre(m.group(3))));
        } else if ((m = P_OBTENER.matcher(l)).matches()) {
            hechos.add(new Mision("obtener", nombre(m.group(1)), nombre(m.group(2))));
        } else if ((m = P_FAVOR.matcher(l)).matches()) {
            hechos.add(new TieneFavor(nombre(m.group(1)), nombre(m.group(2))));
        } else if ((m = P_ENOJO.matcher(l)).matches()) {
            hechos.add(new TieneEnojo(nombre(m.group(1)), nombre(m.group(2))));
        } else if ((m = P_CAPACIDAD.matcher(l)).matches()) {
            hechos.add(new TieneCapacidad(nombre(m.group(1)), nombre(m.group(2))));
        } else if ((m = P_NO_TIENE.matcher(l)).matches()) {
            hechos.add(new NoPosee(nombre(m.group(1)), nombre(m.group(2))));
        } else if ((m = P_LOCALIZA.matcher(l)).matches()) {
            hechos.add(new Localiza(nombre(m.group(1)), nombre(m.group(2))));
        } else if ((m = P_PRESO.matcher(l)).matches()) {
            hechos.add(new PresoDe(nombre(m.group(1)), nombre(m.group(2))));
        } else if ((m = P_HIJO.matcher(l)).matches()) {
            hechos.add(new HijoDe(nombre(m.group(1)), nombre(m.group(2))));
        } else if ((m = P_TIENE.matcher(l)).matches()) {
            hechos.add(new Posee(nombre(m.group(1)), nombre(m.group(2))));
        } else {
            lineasNoEntendidas.add(linea);
        }
    }

    /** "las Ninfas" -> Ninfas, "Escudo de Bronce" -> EscudoBronce, "Escudo-Espejo" -> EscudoEspejo */
    static String nombre(String texto) {
        StringBuilder sb = new StringBuilder();
        for (String palabra : texto.replace("-", " ").trim().split("\\s+")) {
            if (palabra.isEmpty() || CONECTORES.contains(palabra.toLowerCase())) continue;
            sb.append(Character.toUpperCase(palabra.charAt(0))).append(palabra.substring(1));
        }
        return sb.toString();
    }

    public List<Object> getHechos() { return hechos; }
    public List<String> getLineasNoEntendidas() { return lineasNoEntendidas; }
    public String getTextoOriginal() { return textoOriginal; }
}
