/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2.s2.lab2;

/**
 *
 * @author ashley
 */

import java.util.Calendar;

public class Empleado {
    
    public int codigoUnico;
    public String nombre;
    public Calendar fechaContratacion;
    public double salarioBase;
    public double horasTrabajadas;
    
    
    public Empleado(int codigoUnico, String nombre, double salarioBase, double horasTrabajadas){
        this.codigoUnico = codigoUnico;
        this.nombre = nombre;
        this.salarioBase = salarioBase;
        this.fechaContratacion = Calendar.getInstance();
        this.horasTrabajadas = 0;
    }
    
    public int getCodigoUnico() { 
        return codigoUnico; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public double getSalarioBase() { 
        return salarioBase; 
    }
    
    public double getHorasTrabajadas() { 
        return horasTrabajadas; 
    }
    
    public Calendar getFechaContratacion() { 
        return fechaContratacion; 
    }
    
    public void registrarHoras(double horas) {
        if (horas > 0) {
            this.horasTrabajadas += horas;
        }
    }
    
    public double calcularPago() {
        double pagoProporcional = (horasTrabajadas / 160.0) * salarioBase;
        double deduccion = pagoProporcional * 0.035;
        return pagoProporcional - deduccion;
    }
    
    public String mostrarInformacion() {
        return "Codigo: " + codigoUnico +
               ", Nombre: " + nombre +
               ", Fecha Contratación: " + fechaContratacion.getTime();
    }
    
    public void registroVentas(double monto) {}
    
    public void actualizarContrato(Calendar nuevaFecha) {}
    
}
