/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.controlador;

import hotel.modelo.Habitacion;
import hotel.modelo.Hotel;
import hotel.modelo.Piso;
import hotel.vista.Administracion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author OMAR MONTES
 */
public class ControladorAdministracion implements ActionListener {

    private Hotel modelo;
    private Administracion vista;

    public ControladorAdministracion(Hotel modelo) {
        this.modelo = modelo;
        this.vista = new Administracion();
        this.initAdministracion();
    }

    private void initAdministracion() {

        this.vista.setVisible(true);
        this.cargarComboPisos();
        this.actionListener(this);
        this.actionAdminPiso();
    }
    
    
    private void actionListener(ActionListener controlador) {
        this.vista.comboPiso.addActionListener(controlador);
    }

    private void cargarComboPisos() {

        this.vista.comboPiso.removeAllItems();
        for (Piso piso : this.modelo.getPisos()) {
            this.vista.comboPiso.addItem(piso.toString());
        }

    }

    private Piso getPisoSelect() {

        try {
            return (this.modelo.getPiso(this.vista.comboPiso.getSelectedIndex()));
        } catch (Exception e) {
        }

        return (null);
    }

    public void actionAdminPiso() {

        Piso piso = this.getPisoSelect();

        if (piso != null) {

            this.vista.contenedorHabitaciones.removeAll();
            ControladorHabitacion controlHab;
            for (Habitacion hab : piso.getHabitaciones()) {

                controlHab = new ControladorHabitacion(piso, hab);

                this.vista.contenedorHabitaciones.addTab("#" + hab.getNumero(), controlHab.getVista());

            }

        }

    }

    public Administracion getVista() {
        return vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // VALIDAR ACTION
            if (e.getActionCommand().contentEquals("actionAdminPiso")) {
                actionAdminPiso();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
