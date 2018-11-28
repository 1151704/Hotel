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
import hotel.modelo.Temporada;
import hotel.vista.FormRegistro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author OMAR MONTES
 */
public class ControladorRegistro implements ActionListener {

    private Hotel modelo;
    private FormRegistro vista;
    private ArrayList<ControladorPersona> controlPersona;

    public ControladorRegistro(Hotel modelo) {
        this.modelo = modelo;
        this.vista = new FormRegistro();
        this.initRegistro();
    }

    private void initRegistro() {
        this.vista.setVisible(true);
        cargarComboPiso();
        cargarComboTemporada();
        actionListener(this);
    }

    private void cargarComboTemporada() {

        this.vista.comboTemporada.removeAllItems();
        this.modelo.getTemporadas().forEach((temp) -> {
            this.vista.comboTemporada.addItem(temp.toString());
        });
    }

    private void cargarComboPiso() {

        this.vista.comboPiso.removeAllItems();
        for (Piso piso : this.modelo.getPisos()) {
            this.vista.comboPiso.addItem(piso.toString());
        }
        cargarComboHabitacion();
    }

    private void cargarComboHabitacion() {

        Piso piso = getPisoSelect();

        if (piso != null) {
            this.vista.comboHabitacion.removeAllItems();
            for (Habitacion hab : piso.getHabitaciones()) {
                this.vista.comboHabitacion.addItem(hab.toString());

            }
        }
        pintarFormulariosPersonas(getHabitacionSelect());
    }

    private Temporada getTemporadaSelect() {
        try {
            return (this.modelo.getTemporada(this.vista.comboTemporada.getSelectedIndex()));
        } catch (Exception e) {
        }
        return (null);
    }

    private Piso getPisoSelect() {
        try {
            return (this.modelo.getPiso(this.vista.comboPiso.getSelectedIndex()));
        } catch (Exception e) {
        }
        return (null);
    }

    private Habitacion getHabitacionSelect() {
        try {

            Piso piso = getPisoSelect();

            return (piso.getHabitacion(this.vista.comboHabitacion.getSelectedIndex()));

        } catch (Exception e) {
        }
        return (null);
    }

    private void actionListener(ActionListener controlador) {
        this.vista.comboPiso.addActionListener(controlador);
        this.vista.comboHabitacion.addActionListener(controlador);
        this.vista.btnRegistrar.addActionListener(controlador);
    }

    private void pintarFormulariosPersonas(Habitacion habitacion) {
        if (habitacion != null && habitacion.getEstado() == HabitacionEstado.DISPONIBLE) {
            controlPersona = new ArrayList<>();
            ControladorPersona persona;

            this.vista.tabUsuarios.removeAll();
            this.vista.btnRegistrar.setEnabled(true);
            for (int i = 0; i < habitacion.getMaximoOcupantes(); i++) {
                persona = new ControladorPersona();
                controlPersona.add(persona);
                this.vista.tabUsuarios.addTab("Persona #" + (i + 1), persona.getVista());
            }
        } else {
            this.vista.tabUsuarios.removeAll();
            this.vista.btnRegistrar.setEnabled(false);
        }

    }

    private void actionRegistroPiso() {
        cargarComboHabitacion();
    }

    private void actionRegistroHabitacion() {
        Habitacion hab = getHabitacionSelect();
        if (hab != null) {
            pintarFormulariosPersonas(hab);
        }
    }

    private Persona[] getVectorPersonas(ArrayList<Persona> lista) {

        Persona[] vector = new Persona[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            vector[i] = lista.get(i);
        }

        return vector;
    }

    private void actionRegistroAlquilar() {
        if (controlPersona != null) {

            ArrayList<Persona> personasLista = new ArrayList<>();

            for (ControladorPersona p : controlPersona) {
                if (p.getPersona() != null) {
                    personasLista.add(p.getPersona());
                }
            }
            Habitacion hab = getHabitacionSelect();
            Piso piso = getPisoSelect();

            if (personasLista.size() > 0 && hab != null && piso != null) {
                Persona[] personasVector = getVectorPersonas(personasLista);

                if (hab.alquilarHabitacion(personasVector, getTemporadaSelect())) {
                    JOptionPane.showMessageDialog(null, "Hecho");
                } else {
                    JOptionPane.showMessageDialog(null, "Debe registrarse un unico responsable");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ninguno");
        }
    }

    public FormRegistro getVista() {
        return vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // VALIDAR ACTION
            if (e.getActionCommand().contentEquals("actionRegistroPiso")) {
                actionRegistroPiso();
            }
            if (e.getActionCommand().contentEquals("actionRegistroHabitacion")) {
                actionRegistroHabitacion();
            }
            if (e.getActionCommand().contentEquals("actionRegistroAlquilar")) {
                actionRegistroAlquilar();
            }
        } catch (Exception exc) {
        }
    }

}
