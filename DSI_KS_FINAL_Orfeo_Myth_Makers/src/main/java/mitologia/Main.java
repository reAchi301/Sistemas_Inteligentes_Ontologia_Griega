package mitologia;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import mitologia.modelo.Respuesta;
import mitologia.modelo.Suceso;

/**
 * Programa principal (seccion 6 del enunciado).
 * Lee todos los EscenarioFx-y.txt de "escenarios" y escribe RespuestaFx_y.txt en "soluciones".
 */
public class Main {

    private static final Pattern NOMBRE_ESCENARIO = Pattern.compile("(?i)Escenario.?F(\\d+)[-_](\\d+)\\.txt");

    public static void main(String[] args) throws IOException {
        String escenarios = "escenarios";
        String soluciones = "soluciones";
        if (args.length >= 1) escenarios = args[0];
        if (args.length >= 2) soluciones = args[1];

        Files.createDirectories(Paths.get(soluciones));
        KieContainer kContainer = KieServices.Factory.get().getKieClasspathContainer();

        for (Path fichero : buscarEscenarios(Paths.get(escenarios))) {
            Matcher m = NOMBRE_ESCENARIO.matcher(fichero.getFileName().toString());
            m.matches();
            String fase = m.group(1);
            String numero = m.group(2);
            ejecutarEscenario(kContainer, fichero, Paths.get(soluciones, "RespuestaF" + fase + "_" + numero + ".txt"),
                    "F" + fase + "_" + numero);
        }
    }

     /**Modificado para que muestre los escenarios en orden 1-n */
    private static List<Path> buscarEscenarios(Path directorio) throws IOException {
        List<Path> lista = new ArrayList<Path>();
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(directorio, "*.txt")) {
            for (Path p : ds) {
                if (NOMBRE_ESCENARIO.matcher(p.getFileName().toString()).matches()) lista.add(p);
            }
        }
        Collections.sort(lista, new Comparator<Path>() {
        	public int compare(Path a, Path b) {
                int c = Integer.compare(clave(a, 1), clave(b, 1));
                return c != 0 ? c : Integer.compare(clave(a, 2), clave(b, 2));
            }
        });
        return lista;
    }
    
    private static int clave(Path p, int grupo) {
    	Matcher m = NOMBRE_ESCENARIO.matcher(p.getFileName().toString());
    	m.matches();
    	return Integer.parseInt(m.group(grupo));
    }

    private static void ejecutarEscenario(KieContainer kContainer, Path fichero, Path salida, String id)
            throws IOException {
        // 1. Leer el escenario (solo procesamiento de datos)
        LectorEscenario lector = new LectorEscenario();
        lector.leer(fichero);
        System.out.println(lector.getTextoOriginal());
        System.out.println("\n *** Fin Descripcion Escenario ***\n\n");
        for (String l : lector.getLineasNoEntendidas()) {
            System.out.println("AVISO: linea no reconocida por el parser: " + l);
        }

        // 2. Nueva base de hechos para cada escenario
        Suceso.reiniciarContador();
        KieSession ksession = kContainer.newKieSession("ksession-mitologia");
        for (Object hecho : lector.getHechos()) ksession.insert(hecho);

        // 3. Agendas: la pila de foco se ejecuta de la ultima a la primera
        //    -> inicial (conocimiento por defecto + estado inicial), luego mitos, luego respuesta
        ksession.getAgenda().getAgendaGroup("respuesta").setFocus();
        ksession.getAgenda().getAgendaGroup("mitos").setFocus();
        ksession.getAgenda().getAgendaGroup("inicial").setFocus();
        ksession.fireAllRules();

        // 4. Escribir la respuesta (solo lectura de hechos, sin inferir nada)
        List<String> lineas = new ArrayList<String>();
        QueryResults rRes = ksession.getQueryResults("respuesta");
        for (QueryResultsRow row : rRes) lineas.add(((Respuesta) row.get("$r")).getTexto());

        List<Suceso> sucesos = new ArrayList<Suceso>();
        for (QueryResultsRow row : ksession.getQueryResults("sucesos")) sucesos.add((Suceso) row.get("$s"));
        Collections.sort(sucesos, new Comparator<Suceso>() {
            public int compare(Suceso a, Suceso b) { return Integer.compare(a.getOrden(), b.getOrden()); }
        });
        for (Suceso s : sucesos) lineas.add(s.getTexto());
        ksession.dispose();

        Files.write(salida, lineas, StandardCharsets.UTF_8);
        System.out.println("\n--- " + salida.getFileName() + " ---");
        for (String l : lineas) System.out.println(l);
        System.out.println("\n *** Fin Escenario " + id + " ***\n\n");
    }
}
