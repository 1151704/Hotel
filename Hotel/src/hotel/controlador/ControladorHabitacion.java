/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.controlador;

import hotel.modelo.Habitacion;
import hotel.modelo.Piso;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author OMAR MONTES
 */
public class ControladorHabitacion implements ActionListener {

    private Piso piso;
    private Habitacion modelo;
    private hotel.vista.Habitacion vista;

    public ControladorHabitacion(Piso piso, Habitacion modelo) {
        this.piso = piso;
        this.modelo = modelo;
        this.vista = new hotel.vista.Habitacion();
        this.initHabitacion();
    }

    private void initHabitacion() {
        this.vista.setVisible(true);

        this.vista.txtPiso.setText(this.piso.toString());
        this.vista.txtNumero.setText(this.modelo.getNumero() + "");

        this.recargarDatos();

        actionListener(this);

    }

    private void actionListener(ActionListener controlador) {
        this.vista.btnActualizar.addActionListener(controlador);
        this.vista.btnDesocupar.addActionListener(controlador);
    }

    private void recargarDatos() {

        this.vista.txtEstado.setText(this.modelo.getEstado().toString());
        this.vista.txtMaximo.setText(this.modelo.getMaximoOcupantes() + "");
        this.vista.txtPersonas.setText(this.modelo.getNumeroOcupantes() + "");
        this.vista.txtPrecio.setText(this.modelo.getPrecioAlquiler() + "");
        this.vista.txtTemporada.setText(this.modelo.getTemporada() != null ? this.modelo.getTemporada().toString() : "");

    }

    private void actionAdminHabiActualizar() {

        try {

            int maximo = Integer.parseInt(this.vista.txtMaximo.getText());
            float precio = Float.parseFloat(this.vista.txtPrecio.getText());

            this.modelo.setMaximoOcupantes(maximo);
            this.modelo.setPrecioAlquiler(precio);

            this.recargarDatos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this.vista, "Formato de maximo de personas y/o precio de alquier incorrecto");
        }
    }

    private void actionAdminHabiDesocupar() {
        this.modelo.desocuparHabitacion();
        this.recargarDatos();
    }

    public hotel.vista.Habitacion getVista() {
        return vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // VALIDAR ACTION
            if (e.getActionCommand().contentEquals("actionAdminHabiActualizar")) {
                actionAdminHabiActualizar();
            }
            if (e.getActionCommand().contentEquals("actionAdminHabiDesocupar")) {
                actionAdminHabiDesocupar();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
