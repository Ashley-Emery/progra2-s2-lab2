/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2.s2.lab2;

/**
 *
 * @author ashley
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class Empresa {
    
    private final List<Empleado> empleados = new ArrayList<>();
    
    public boolean registrarEmpleado(Empleado nuevo){
        
        if (nuevo == null)
            return false;
        
        if (buscarPorCodigo(nuevo.getCodigoUnico()) != null) 
            return false;
        
        empleados.add(nuevo);
        return true;   
    }
    
    public Empleado buscarPorCodigo(int codigo) {
        
        for (Empleado emp : empleados) {
            
            if (emp.getCodigoUnico() == codigo) {
                return emp;
            }
            
        }
        
        return null;
    }

    
    public boolean registrarHoras(int codigo, double horas) {
        Empleado emp = buscarPorCodigo(codigo);
        
        if (emp != null && horas > 0) {
            emp.registrarHoras(horas);
            return true;
        }
        
        return false;
    }
    
    public boolean registrarVentas(int codigo, double monto) {
        
        Empleado emp = buscarPorCodigo(codigo);
        
        if (emp != null && monto > 0) {
            
            emp.registroVentas(monto);
            return true;
        }
        
        return false;
    }
    
    public boolean actualizarFinContrato(int codigo, Calendar nuevaFecha) {
        
        Empleado emp = buscarPorCodigo(codigo);

        if (emp != null && nuevaFecha != null) {
            
            emp.actualizarContrato(nuevaFecha);
            return true;
            
        }

        return false;
    }
    
    public double calcularPagoMensual(int codigo) {
        
        Empleado emp = buscarPorCodigo(codigo);
        
        return (emp == null) ? 0.0 : emp.calcularPago();
        
    }
    
    public String generarReporte() {
        
        String reporte = "Reporte de Empleados\n\n";

        String Estandar = "Empleados Estándar\n";
        String Temporal = "Empleados Temporales\n";
        String Ventas   = "Empleados Ventas\n";

        int contEstandar = 0;
        int contTemporal = 0;
        int contVentas   = 0;

        for (Empleado emp : empleados) {
            
            String t = emp.tipo();
            
            if ("VENTAS".equals(t)) {
                
                contVentas++;
                Ventas = Ventas + "- " + emp.Reporte() + "\n";
                
            } else if ("TEMPORAL".equals(t)) {
                
                contTemporal++;
                Temporal = Temporal + "- " + emp.Reporte() + "\n";
                
            } else {
                
                contEstandar++;
                Estandar = Estandar + "- " + emp.Reporte() + "\n";
                
            }
        }

        if (contEstandar == 0) {
            Estandar = Estandar + "(Sin registros)\n";
        }
        
        if (contTemporal == 0) {
            Temporal = Temporal + "(Sin registros)\n";
        }
        
        if (contVentas   == 0) {
            Ventas   = Ventas   + "(Sin registros)\n";
        }

        reporte = reporte + Estandar + "\n" + Temporal + "\n" + Ventas + "\n";

        reporte = reporte + "Totales -> Estándar: " + contEstandar +
                  ", Temporales: " + contTemporal +
                  ", Ventas: " + contVentas + "\n";

        return reporte;
    }

    
}
