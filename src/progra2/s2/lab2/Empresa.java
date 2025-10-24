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
    
    public boolean Empresa(Empleado nuevo){
        
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
}
