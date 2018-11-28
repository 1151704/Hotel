/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.controlador;

import hotel.modelo.Hotel;
import hotel.vista.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author OMAR MONTES
 */
public class ControladorMenu implements ActionListener {

    private Hotel modelo;
    private Menu vista;

    public ControladorMenu(Hotel hotel) {
        this.modelo = hotel;
        this.vista = new Menu();
        this.initMenu();
    }

    private void initMenu() {
        this.vista.setVisible(true);
        actionListener(this);
        actionMenuListar();
    }

    private void actionListener(ActionListener controlador) {
        this.vista.btnListar.addActionListener(controlador);
        this.vista.btnRegistrar.addActionListener(controlador);
        this.vista.btnAdmin.addActionListener(controlador);
    }

    private void actionMenuListar() {
        ControladorHabitaciones habitaciones = new ControladorHabitaciones(this.modelo);
        this.vista.contenedor.setViewportView(habitaciones.getVista());
    }

    private void actionMenuRegistrar() {
        ControladorRegistro controlRegistro = new ControladorRegistro(this.modelo);
        this.vista.contenedor.setViewportView(controlRegistro.getVista());
    }

    private void actionMenuAdministrar() {
        ControladorAdministracion controlAdmin = new ControladorAdministracion(this.modelo);
        this.vista.contenedor.setViewportView(controlAdmin.getVista());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // VALIDAR ACTION
            if (e.getActionCommand().contentEquals("actionMenuListar")) {
                actionMenuListar();
            }
            if (e.getActionCommand().contentEquals("actionMenuRegistrar")) {
                actionMenuRegistrar();
            }
            if (e.getActionCommand().contentEquals("actionMenuAdministrar")) {
                actionMenuAdministrar();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
