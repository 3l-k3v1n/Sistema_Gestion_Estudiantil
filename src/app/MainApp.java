package app;

import adapter.BancarioAdapter;
import adapter.PagoMatricula;
import adapter.SistemaBancarioExterno;
import builder.TareaAcademica;
import factory.Notificacion;
import factory.NotificacionFactory;
import singleton.ConfiguracionSistema;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ConfiguracionSistema config = ConfiguracionSistema.getInstancia();

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   SISTEMA ACADÉMICO DE CONSOLA       ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("  " + config);
        System.out.println();

        int opcion = -1;
        while (opcion != 0) {
            System.out.println("------ MENÚ PRINCIPAL ------");
            System.out.println("1. Enviar notificación a estudiante  (Factory)");
            System.out.println("2. Crear tarea académica             (Builder)");
            System.out.println("3. Procesar pago de matrícula        (Adapter)");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                opcion = -1;
            }

            System.out.println();

            switch (opcion) {
                case 1 -> menuNotificacion(sc);
                case 2 -> menuTarea(sc);
                case 3 -> menuPago(sc);
                case 0 -> System.out.println("Hasta luego.");
                default -> System.out.println("Opción no válida, intente de nuevo.\n");
            }
        }

        sc.close();
    }

    private static void menuNotificacion(Scanner sc) {
        System.out.println("=== ENVIAR NOTIFICACIÓN (Factory) ===");
        System.out.print("Nombre del estudiante: ");
        String estudiante = sc.nextLine().trim();

        System.out.print("Tipo de notificación (EMAIL / SMS): ");
        String tipo = sc.nextLine().trim();

        System.out.print("Mensaje: ");
        String mensaje = sc.nextLine().trim();

        try {
            Notificacion notif = NotificacionFactory.crear(tipo);
            System.out.println("\nEnviando notificación...");
            notif.enviarMensaje(estudiante, mensaje);
            System.out.println("Notificación enviada con éxito.\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    private static void menuTarea(Scanner sc) {
        System.out.println("=== CREAR TAREA ACADÉMICA (Builder) ===");
        System.out.print("Título de la tarea: ");
        String titulo = sc.nextLine().trim();

        System.out.print("Materia: ");
        String materia = sc.nextLine().trim();

        System.out.print("Estudiante responsable: ");
        String estudiante = sc.nextLine().trim();

        System.out.print("Fecha de entrega (ej: 06-02-2026): ");
        String fecha = sc.nextLine().trim();

        System.out.print("Porcentaje de la nota (1-100): ");
        int porcentaje = 10;
        try {
            porcentaje = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Porcentaje inválido, se usará 10% por defecto.");
        }

        try {
            // BUILDER: construye la tarea paso a paso con métodos encadenados
            TareaAcademica tarea = new TareaAcademica.Builder(titulo, materia)
                    .estudiante(estudiante)
                    .fechaEntrega(fecha)
                    .porcentaje(porcentaje)
                    .build();

            System.out.println("\nTarea creada exitosamente:");
            System.out.println(tarea);
            System.out.println();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        }
    }

    private static void menuPago(Scanner sc) {
        System.out.println("=== PAGO DE MATRÍCULA (Adapter) ===");
        System.out.print("Nombre del estudiante: ");
        String estudiante = sc.nextLine().trim();

        System.out.print("Monto a pagar ($): ");
        double monto = 0;
        try {
            monto = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Monto inválido.\n");
            return;
        }

        PagoMatricula pago = new BancarioAdapter(new SistemaBancarioExterno());
        System.out.println("\nProcesando pago...");
        pago.pagar(estudiante, monto);
        System.out.println("Matrícula registrada para: " + estudiante + "\n");
    }
}
