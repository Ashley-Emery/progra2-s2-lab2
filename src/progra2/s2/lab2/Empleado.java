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
        this.horasTrabajadas = horasTrabajadas;
        this.fechaContratacion = Calendar.getInstance();
    }
    
    
    
}
