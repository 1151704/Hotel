/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.controlador;

import hotel.modelo.Habitacion;
import hotel.modelo.HabitacionEstado;
import hotel.modelo.Hotel;
import hotel.modelo.Persona;
import hotel.modelo.Piso;
import hotel.vista.Habitaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author OMAR MONTES
 */
public class ControladorHabitaciones implements ActionListener {

    private Hotel modelo;
    private Habitaciones vista;
    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Persona> personas;

    public ControladorHabitaciones(Hotel hotel) {
        this.modelo = hotel;
        this.vista = new Habitaciones();
        this.initHabitaciones();
    }

    private void initHabitaciones() {

        this.vista.setVisible(true);

        this.vista.comboEstados.removeAllItems();
        this.vista.comboEstados.setModel(new DefaultComboBoxModel(HabitacionEstado.values()));

        this.vista.comboPisos.removeAllItems();
        for (Piso piso : this.modelo.getPisos()) {
            this.vista.comboPisos.addItem(piso.toString());
        }
        actionListener(this);
    }

    private void actionListener(ActionListener controlador) {
        this.vista.btnListar.addActionListener(controlador);
        this.vista.btnPersonas.addActionListener(controlador);
    }

    private void actionHabitacionListar() {

        int idPiso = this.vista.comboPisos.getSelectedIndex();
        int idEstado = this.vista.comboEstados.getSelectedIndex();
        Piso piso = this.modelo.getPiso(idPiso);
        HabitacionEstado estado = HabitacionEstado.values()[idEstado];

        habitaciones = new ArrayList<>();
        if (piso != null && estado != null) {
            habitaciones = piso.habitacionesListado(estado);
        }
        this.vista.tablaHabitaciones.setModel(new HabitacionTableModel(habitaciones));
        actionHabitacionPersonas();
    }

    private void actionHabitacionPersonas() {

        try {

            int idHabitacion = this.vista.tablaHabitaciones.getSelectedRow();

            Habitacion habitacion = habitaciones.get(idHabitacion);

            personas = new ArrayList<>();
            if (habitacion != null) {
                personas = habitacion.getListaPersonas();
            }
            this.vista.tablaPersonas.setModel(new PersonaTableModel(personas));
        } catch (Exception e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // VALIDAR ACTION
            if (e.getActionCommand().contentEquals("actionHabitacionListar")) {
                actionHabitacionListar();
            }
            if (e.getActionCommand().contentEquals("actionHabitacionPersonas")) {
                actionHabitacionPersonas();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public Habitaciones getVista() {
        return vista;
    }
}
