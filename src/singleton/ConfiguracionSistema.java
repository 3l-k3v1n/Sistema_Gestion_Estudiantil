package singleton;

public class ConfiguracionSistema {

    private static ConfiguracionSistema instancia;

    private String nombreUniversidad;
    private String ciclo;

    private ConfiguracionSistema() {
        this.nombreUniversidad = "Universidad Central";
        this.ciclo = "2026-A";
    }

    public static ConfiguracionSistema getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionSistema();
        }
        return instancia;
    }

    public String getNombreUniversidad() { return nombreUniversidad; }
    public String getCiclo()             { return ciclo; }

    @Override
    public String toString() {
        return "[" + nombreUniversidad + " | Ciclo: " + ciclo + "]";
    }
}
