/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2.s2.lab2;
import java.util.Calendar;
/**
 *
 * @author ferna
 */
public class EmpleadoVentas extends Empleado{
    
    private double ventasMensuales[]=new double[12];
    private double comision=0.05;

    public EmpleadoVentas(double comision, int codigoUnico, String nombre, double salarioBase, double horasTrabajadas) {
        super(codigoUnico, nombre, salarioBase, horasTrabajadas);
        this.comision = comision;
    }
    
    public void registroVentas(double monto)
    {
        if(monto<=0)
            return;
        
        Calendar fecha=Calendar.getInstance();
        int mes=fecha.get(Calendar.MONTH);
        
        ventasMensuales[mes]+=monto;
    }
    
    public double calculoComision()
    {
        Calendar fecha=Calendar.getInstance();
        int mes=fecha.get(Calendar.MONTH);
        double calcComision=ventasMensuales[mes]*comision;
        return calcComision;
    }
    
    @Override
    public double calcularPago()
    {
        double pagoProporcional=super.calcularPago();
        double pagoComision=pagoProporcional+calculoComision();
        return pagoComision;
    }
    
    public double calculoAnual()
    {
        double total=0;
        for(int i=0;i<ventasMensuales.length;i++)
        {
            total+=ventasMensuales[i];
        }
        return total;
    }
    
    public String toString()
    {
        return super.mostrarInformacion()+" Ventas anuales: "+calculoAnual();
    }
    
    public String tipo() {
        return "Ventas";
    }
    
    public double ventasAnuales() {
        return calculoAnual();
    }
    
    public String Reporte() {
        return mostrarInformacion() +
               ", Ventas Anuales: " + calculoAnual() +
               ", " + etiquetaPago() + ": " + calcularPago();
    }
    
}
