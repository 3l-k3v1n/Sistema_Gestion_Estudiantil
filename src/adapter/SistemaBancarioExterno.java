package adapter;

public class SistemaBancarioExterno {

    public void procesarDebito(String cuentaOrigen, double valor, String concepto) {
        System.out.println("  [BANCO] Procesando débito...");
        System.out.println("  [BANCO] Cuenta  : " + cuentaOrigen);
        System.out.println("  [BANCO] Valor   : $" + valor);
        System.out.println("  [BANCO] Concepto: " + concepto);
        System.out.println("  [BANCO] Transacción aprobada.");
    }
}
