package adapter;

public class BancarioAdapter implements PagoMatricula {

    private final SistemaBancarioExterno banco;

    public BancarioAdapter(SistemaBancarioExterno banco) {
        this.banco = banco;
    }

    @Override
    public void pagar(String estudiante, double monto) {

        String cuenta   = "CTA-" + estudiante.toUpperCase().replace(" ", "");
        String concepto = "Pago de matricula - " + estudiante;
        banco.procesarDebito(cuenta, monto, concepto);
    }
}
