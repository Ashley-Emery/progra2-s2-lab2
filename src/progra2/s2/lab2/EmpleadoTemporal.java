package progra2.s2.lab2;
import java.util.Calendar;

/**
 *
 * @author marye
 */
public class EmpleadoTemporal extends Empleado {

    private Calendar fechaFinContrato;

    public EmpleadoTemporal(int codigoUnico, String nombre, double salarioBase, double horasTrabajadas) {
        super(codigoUnico, nombre, salarioBase, horasTrabajadas);
        this.fechaFinContrato = fechaFinContrato;
    }

    public double pagoCondicionado() {
        Calendar hoy = Calendar.getInstance();

        if (hoy.after(fechaFinContrato)) {
            return super.calcularPago();
        }
        return 0;
    }

    public void actualizarContrato(Calendar nuevoFinContrato) {
        fechaFinContrato = nuevoFinContrato;
    }

    public String mostrarInformacion() {
        return super.mostrarInformacion() + "Fecha de fin de contrato: " + fechaFinContrato.getTime();
    }
}
