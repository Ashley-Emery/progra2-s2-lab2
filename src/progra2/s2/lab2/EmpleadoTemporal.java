package progra2.s2.lab2;
import java.util.Calendar;

/**
 *
 * @author marye
 */
public class EmpleadoTemporal extends Empleado {

    private Calendar fechaFinContrato;

    public EmpleadoTemporal(int codigoUnico, String nombre, double salarioBase, double horasTrabajadas, Calendar fechaFinContrato) {
        super(codigoUnico, nombre, salarioBase, horasTrabajadas);
        this.fechaFinContrato = fechaFinContrato;
    }

    @Override
    public double calcularPago() {
        Calendar hoy = Calendar.getInstance();

        if (fechaFinContrato != null && hoy.after(fechaFinContrato)) {
            return 0.0;
        }
        return super.calcularPago();
    }

    public void actualizarContrato(Calendar nuevoFinContrato) {
        fechaFinContrato = nuevoFinContrato;
    }

    public String mostrarInformacion() {
        return super.mostrarInformacion() + "Fecha de fin de contrato: " + fechaFinContrato.getTime();
    }
    
    public String tipo() {
        return "Temporal";
    }
    
    public String etiquetaPago() {
        return "Pago condicionado";
    }
    

    
}
