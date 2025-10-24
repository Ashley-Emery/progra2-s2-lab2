/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progra2.s2.lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ferna
 */
public class Interfaz extends JFrame {

    private Empresa empresa = new Empresa();

    public Interfaz() {
        super("Gestion de Empleados");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 750);
        setResizable(false);

        JTabbedPane pestanias = new JTabbedPane();
        setContentPane(pestanias);

        pestanias.add("Registrar Empleado", registrarEmpleado());
        pestanias.add("Registrar Horas ", registrarHoras());
        pestanias.add("Registrar Ventas", registrarVentas());
        pestanias.add("Actualizar Contrato", actualizarFinContrato());
        pestanias.add("Calcular Pago", calcularPagoMensual());
        pestanias.add("Generar Reporte", generarReporte());
        pestanias.add("Buscar Empleado", buscarEmpleado());

    }

    private JPanel registrarEmpleado() {
        JPanel panel = new JPanel(null);
        JLabel lblTipo = new JLabel("Seleccione el tipo de empleado:");
        JComboBox<String> cboxTipo = new JComboBox<>(new String[]{"Estandar", "Temporal", "Ventas"});
        JLabel lblCodigo = new JLabel("Ingrese el codigo del empleado: ");
        JTextField txtCodigo = new JTextField();
        JLabel lblNombre = new JLabel("Ingrese el nombre del empleado: ");
        JTextField txtNombre = new JTextField();
        JLabel lblSalario = new JLabel("Ingrese el salario base del usuario: ");
        JTextField txtSalario = new JTextField();
        JLabel lblFecha = new JLabel("Fecha de contratacion (DD/MM/yyyy)");
        JTextField txtFecha = new JTextField();

        //para empleados especiales
        JLabel lblContrato = new JLabel("Fin de contrato(solo temporales), (DD/MM/yyyy)");
        JTextField txtContrato = new JTextField();
        JLabel lblComision = new JLabel("Comision (solo ventas): ");
        JTextField txtComision = new JTextField("0.5%");

        JButton btnRegistrar = new JButton("Registrar Empleado");

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

                boolean estado = false;
                switch (tipo) {
                    case "Estandar":
                        estado = empresa.registrarEmpleado(new Empleado(codigo, nombre, salario, horasIniciales));
                        break;
                    case "Temporal":
                            Calendar fin = Calendar.getInstance();
                        try {
                            String textoFecha = txtContrato.getText().trim();
                            java.text.SimpleDateFormat formato = new java.text.SimpleDateFormat("yyyy-MM-dd");
                            java.util.Date fecha = formato.parse(textoFecha);

                            fin.setTime(fecha);

                            estado = empresa.registrarEmpleado(new EmpleadoTemporal(codigo, nombre, salario, horasIniciales, fin));
                        } catch (java.text.ParseException ex) {
                            
                            JOptionPane.showMessageDialog(panel, "Formato de fecha inválido. Usa yyyy-MM-dd");
                            
                        }
                        estado = empresa.registrarEmpleado(new EmpleadoTemporal(codigo, nombre, salario, horasIniciales, fin));
                        break;
                    case "Ventas":
                        double com = Double.parseDouble(txtComision.getText().trim());
                        estado = empresa.registrarEmpleado(new EmpleadoVentas(com, codigo, nombre, salario, horasIniciales));
                        break;
                }
                if(estado)
                {
                    JOptionPane.showMessageDialog(this, "Empleado registrado correctamente");
                }
                else
                    JOptionPane.showMessageDialog(this, "Codigo duplicado o datos invalidos");

                if (estado) {
                    txtCodigo.setText("");
                    txtNombre.setText("");
                    txtSalario.setText("");
                    txtContrato.setText("");
                    txtComision.setText("0.05");
                    cboxTipo.setSelectedIndex(0);
                    txtContrato.setEnabled(false);
                    txtComision.setEnabled(false);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage());
            }
        });

        return panel;

    }

    private JPanel registrarHoras() {
        JPanel panel = new JPanel(null);

        JLabel lblCod = new JLabel("Código:");
        JTextField txtCod = new JTextField();
        JLabel lblHoras = new JLabel("Horas a sumar:");
        JTextField txtHoras = new JTextField();
        JButton btn = new JButton("Registrar");

        lblCod.setBounds(30, 30, 120, 25);
        txtCod.setBounds(170, 30, 150, 25);
        lblHoras.setBounds(30, 70, 120, 25);
        txtHoras.setBounds(170, 70, 150, 25);
        btn.setBounds(170, 110, 150, 35);

        panel.add(lblCod); panel.add(txtCod);
        panel.add(lblHoras); panel.add(txtHoras);
        panel.add(btn);

        btn.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCod.getText().trim());
                double horas = Double.parseDouble(txtHoras.getText().trim());
                boolean ok = empresa.registrarHoras(codigo, horas);
                JOptionPane.showMessageDialog(panel, ok ? "Horas registradas" : "Empleado no encontrado o horas inválidas");
                if (ok) { txtCod.setText(""); txtHoras.setText(""); }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Datos inválidos (código entero / horas numéricas).");
            }
        });

        return panel;
    }


    private JPanel registrarVentas() {
        JPanel panel = new JPanel(null);

        JLabel lblCod = new JLabel("Código (Ventas):");
        JTextField txtCod = new JTextField();
        JLabel lblMonto = new JLabel("Monto de venta:");
        JTextField txtMonto = new JTextField();
        JButton btn = new JButton("Agregar venta (mes actual)");

        lblCod.setBounds(30, 30, 140, 25);
        txtCod.setBounds(190, 30, 150, 25);
        lblMonto.setBounds(30, 70, 140, 25);
        txtMonto.setBounds(190, 70, 150, 25);
        btn.setBounds(190, 110, 200, 35);

        panel.add(lblCod); panel.add(txtCod);
        panel.add(lblMonto); panel.add(txtMonto);
        panel.add(btn);

        btn.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCod.getText().trim());
                double monto = Double.parseDouble(txtMonto.getText().trim());
                boolean ok = empresa.registrarVentas(codigo, monto);
                JOptionPane.showMessageDialog(panel, ok ? "Venta registrada en el mes actual" : "No es empleado de Ventas o datos inválidos");
                if (ok) { txtCod.setText(""); txtMonto.setText(""); }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Datos inválidos (código entero / monto numérico).");
            }
        });

        return panel;
    }


    private JPanel actualizarFinContrato() {
        JPanel panel = new JPanel(null);

        JLabel lblCod = new JLabel("Código (Temporal):");
        JTextField txtCod = new JTextField();
        JLabel lblFecha = new JLabel("Nueva fecha fin (DD/MM/yyyy):");
        JTextField txtFecha = new JTextField();
        JButton btn = new JButton("Actualizar");

        lblCod.setBounds(30, 30, 160, 25);
        txtCod.setBounds(210, 30, 150, 25);
        lblFecha.setBounds(30, 70, 200, 25);
        txtFecha.setBounds(240, 70, 120, 25);
        btn.setBounds(210, 110, 150, 35);

        panel.add(lblCod); panel.add(txtCod);
        panel.add(lblFecha); panel.add(txtFecha);
        panel.add(btn);

        btn.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCod.getText().trim());
                Calendar fin = parseFechaDDMM(txtFecha.getText().trim());
                boolean ok = empresa.actualizarFinContrato(codigo, fin);
                JOptionPane.showMessageDialog(panel, ok ? "Fecha de fin actualizada" : "Empleado no encontrado o no es Temporal");
                if (ok) { txtCod.setText(""); txtFecha.setText(""); }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Código inválido (entero).");
            } catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(panel, "Fecha inválida. Usa DD/MM/yyyy");
            }
        });

        return panel;
    }


    private JPanel calcularPagoMensual() {
        JPanel panel = new JPanel(null);

        JLabel lblCod = new JLabel("Código:");
        JTextField txtCod = new JTextField();
        JButton btn = new JButton("Calcular pago");
        JLabel lblRes = new JLabel("Pago: —");

        lblCod.setBounds(30, 30, 120, 25);
        txtCod.setBounds(170, 30, 150, 25);
        btn.setBounds(170, 70, 150, 35);
        lblRes.setBounds(30, 120, 400, 25);

        panel.add(lblCod); panel.add(txtCod);
        panel.add(btn); panel.add(lblRes);

        btn.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCod.getText().trim());
                Double pago = empresa.calcularPagoMensual(codigo);
                if (pago == null) {
                    lblRes.setText("Pago: Empleado no encontrado");
                } else {
                    lblRes.setText(String.format("Pago: L %.2f", pago));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Código inválido (entero).");
            }
        });

        return panel;
    }


    private JPanel generarReporte() {
        JPanel panel = new JPanel(null);

        JButton btn = new JButton("Generar reporte");
        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);

        btn.setBounds(30, 20, 160, 35);
        scroll.setBounds(30, 70, 800, 420);

        panel.add(btn);
        panel.add(scroll);

        btn.addActionListener(e -> area.setText(empresa.generarReporte()));

        return panel;
    }


    private JPanel buscarEmpleado() {
        JPanel panel = new JPanel(null);

        JLabel lblCod = new JLabel("Código:");
        JTextField txtCod = new JTextField();
        JButton btn = new JButton("Buscar");
        JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);

        lblCod.setBounds(30, 20, 100, 25);
        txtCod.setBounds(140, 20, 150, 25);
        btn.setBounds(310, 20, 120, 25);
        scroll.setBounds(30, 60, 800, 430);

        panel.add(lblCod); panel.add(txtCod);
        panel.add(btn); panel.add(scroll);

        btn.addActionListener(e -> {
            try {
                int codigo = Integer.parseInt(txtCod.getText().trim());
                Empleado emp = empresa.buscarPorCodigo(codigo);
                area.setText(emp == null ? "No encontrado" : emp.Reporte());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Código inválido (entero).");
            }
        });

        return panel;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Interfaz().setVisible(true));
    }
    
    private Calendar parseFechaDDMM(String texto) throws java.text.ParseException {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        java.util.Date f = sdf.parse(texto);
        Calendar cal = Calendar.getInstance();
        cal.setTime(f);
        return cal;
    }

}
