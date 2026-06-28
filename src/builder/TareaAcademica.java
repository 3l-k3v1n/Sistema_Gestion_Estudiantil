package builder;

public class TareaAcademica {

    private final String titulo;
    private final String materia;
    private final String estudiante;
    private final String fechaEntrega;
    private final int    porcentaje;

    private TareaAcademica(Builder b) {
        this.titulo       = b.titulo;
        this.materia      = b.materia;
        this.estudiante   = b.estudiante;
        this.fechaEntrega = b.fechaEntrega;
        this.porcentaje   = b.porcentaje;
    }

    @Override
    public String toString() {
        return "  Tarea    : " + titulo       + "\n" +
                "  Materia  : " + materia      + "\n" +
                "  Estudiante: " + estudiante  + "\n" +
                "  Entrega  : " + fechaEntrega + "\n" +
                "  Valor    : " + porcentaje   + "%";
    }

    public static class Builder {
        private final String titulo;
        private final String materia;
        private String estudiante   = "Sin asignar";
        private String fechaEntrega = "Sin fecha";
        private int    porcentaje   = 10;

        public Builder(String titulo, String materia) {
            if (titulo == null || titulo.isBlank())
                throw new IllegalArgumentException("El título es obligatorio");
            if (materia == null || materia.isBlank())
                throw new IllegalArgumentException("La materia es obligatoria");
            this.titulo  = titulo;
            this.materia = materia;
        }

        public Builder estudiante(String estudiante) {
            this.estudiante = estudiante;
            return this;
        }

        public Builder fechaEntrega(String fecha) {
            this.fechaEntrega = fecha;
            return this;
        }

        public Builder porcentaje(int porcentaje) {
            if (porcentaje < 1 || porcentaje > 100)
                throw new IllegalArgumentException("Porcentaje debe estar entre 1 y 100");
            this.porcentaje = porcentaje;
            return this;
        }

        public TareaAcademica build() {
            return new TareaAcademica(this);
        }
    }
}
