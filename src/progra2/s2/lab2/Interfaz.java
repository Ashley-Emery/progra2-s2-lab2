/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2.s2.lab2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.text.NumberFormat;
import java.util.Date;
/**
 *
 * @author ferna
 */
public class Interfaz extends JFrame{
    
    public Interfaz()
    {
        super("Gestion de Empleados");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,600);
        setResizable(false);
        
        JTabbedPane pestanias =new JTabbedPane();
        setContentPane(pestanias); 
        
        pestanias.add("Registrar Empleado", registrarEmpleado());
        pestanias.add("Registrar Horas ", registrarHoras());
        pestanias.add("Registrar Ventas",registrarVentas());
        pestanias.add("Actualizar Contrato", actualizarFinContrato());
        pestanias.add("Calcular Pago", calcularPagoMensual());
        pestanias.add("Generar Reporte",generarReporte());
        pestanias.add("Buscar Empleado",generarReporte());
        
    }
    
    private JPanel registrarEmpleado()
    {
        JPanel panel=new JPanel(null);
        JLabel lblTipo = new JLabel("Seleccione el tipo de empleado:");
        JComboBox<String> cboxTipo=new JComboBox<>(new String[]{"Estandar","Temporal","Ventas"});
        JLabel lblCodigo=new JLabel("Ingrese el codigo del empleado: ");
        JTextField txtCodigo=new JTextField();
        JLabel lblNombre=new JLabel("Ingrese el nombre del empleado: ");
        JTextField txtNombre=new JTextField();
        JLabel lblSalario=new JLabel("Ingrese el salario base del usuario: ");
        JTextField txtSalario=new JTextField();
        JLabel lblFecha=new JLabel("Fecha de contratacion (DD/MM/yyyy)");
        JTextField txtFecha=new JTextField();
        
        //para empleados especiales
        JLabel lblContrato=new JLabel("Fin de contrato(solo temporales), (DD/MM/yyyy)");
        JTextField txtContrato=new JTextField();
        JLabel lblComision=new JLabel("Comision (solo ventas): ");
        JTextField txtComision=new JTextField("0.5%");
        
        JButton btnRegistrar=new JButton("Registrar Empleado"); 
        
        lblTipo.setBounds(30, 30, 200, 25);
        cboxTipo.setBounds(300, 30, 150, 25);
        lblCodigo.setBounds(30, 70, 250, 25);
        txtCodigo.setBounds(300, 70, 200, 25);
        lblNombre.setBounds(30, 110, 250, 25);
        txtNombre.setBounds(300, 110, 200, 25);
        lblSalario.setBounds(30, 150, 250, 25);
        txtSalario.setBounds(300, 150, 200, 25);
        lblFecha.setBounds(30, 190, 250, 25);
        txtFecha.setBounds(300, 190, 200, 25);
        lblContrato.setBounds(30, 230, 300, 25);
        txtContrato.setBounds(300, 230, 200, 25);
        lblComision.setBounds(30, 270, 250, 25);
        txtComision.setBounds(300, 270, 200, 25);
        btnRegistrar.setBounds(200, 320, 180, 35);
        
        txtContrato.setVisible(false);
        lblContrato.setVisible(false);
        lblComision.setVisible(false);
        txtComision.setVisible(false);
        
        panel.add(lblTipo);
        panel.add(cboxTipo);
        panel.add(lblCodigo);
        panel.add(txtCodigo);
        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblSalario);
        panel.add(txtSalario);
        panel.add(lblContrato);
        panel.add(txtContrato);
        panel.add(lblComision);
        panel.add(txtComision);
        panel.add(btnRegistrar);
        
        cboxTipo.addActionListener(e -> {
            String tipo = cboxTipo.getSelectedItem().toString();
            txtContrato.setVisible(tipo.equals("Temporal"));
            lblContrato.setVisible(tipo.equals("Temporal"));
            txtComision.setVisible(tipo.equals("Ventas"));
            lblComision.setVisible(tipo.equals("Ventas"));
        });
        btnRegistrar.addActionListener(e -> {
        try {
            String tipo = cboxTipo.getSelectedItem().toString();
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            String nombre = txtNombre.getText().trim();
            double salario = Double.parseDouble(txtSalario.getText().trim());
            double horasIniciales = 0.0;

            boolean ok = false;
            switch (tipo) {
                case "Estándar":
                    //ok = empresa.registrarEmpleado(new Empleado(codigo, nombre, salario, horasIniciales));
                    break;
                case "Temporal":
                    java.sql.Date fin = java.sql.Date.valueOf(txtFin.getText().trim());
                    //ok = empresa.registrarEmpleado(new EmpleadoTemporal(codigo, nombre, salario, horasIniciales, fin));
                    break;
                case "Ventas":
                    double com = Double.parseDouble(txtCom.getText().trim());
                    //ok = empresa.registrarEmpleado(new EmpleadoVentas(com, codigo, nombre, salario, horasIniciales));
                    break;
            }

            JOptionPane.showMessageDialog(p, ok
                ? "Empleado registrado correctamente."
                : "Error: código duplicado o datos inválidos.");

            if (ok) {
                txtCodigo.setText("");
                txtNombre.setText("");
                txtSalario.setText("");
                txtFin.setText("");
                txtCom.setText("0.05");
                cbTipo.setSelectedIndex(0);
                txtFin.setEnabled(false);
                txtCom.setEnabled(false);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(p, "Error: " + ex.getMessage());
        }
    });
        
        
        
        
        
        return panel;
        
    }
    
    private JPanel registrarHoras()
    {
        JPanel panel=new JPanel(null);
        return panel;
    }
    
    private JPanel registrarVentas()
    {
        JPanel panel =new JPanel(null);
        return panel;
    }
    
    private JPanel actualizarFinContrato()
    {
        JPanel panel=new JPanel(null);
        return panel;
    }
    
    private JPanel calcularPagoMensual()
    {
        JPanel panel=new JPanel();
        return panel;
    }
    
    private JPanel generarReporte()
    {
        JPanel panel=new JPanel();
        return panel;
    }
    
    private JPanel buscarEmpleado()
    {
        JPanel panel=new JPanel();
        return panel;
    }
    public static void main(String [] args)
    {
        SwingUtilities.invokeLater(()->new Interfaz().setVisible(true));
    }
}
