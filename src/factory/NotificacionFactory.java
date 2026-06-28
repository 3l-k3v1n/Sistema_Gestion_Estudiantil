package factory;

public class NotificacionFactory {

    public static Notificacion crear(String tipo) {
        switch (tipo.toUpperCase()) {
            case "EMAIL": return new NotificacionEmail();
            case "SMS":   return new NotificacionSMS();
            default: throw new IllegalArgumentException("Tipo no soportado: " + tipo);
        }
    }
}
