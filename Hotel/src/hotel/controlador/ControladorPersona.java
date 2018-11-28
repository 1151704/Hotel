/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.controlador;

import hotel.modelo.Persona;
import hotel.vista.FormPersona;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author OMAR MONTES
 */
public class ControladorPersona implements ActionListener {

    private FormPersona vista;
    private Persona persona;

    public ControladorPersona() {
        this.vista = new FormPersona();
        this.persona = null;
        this.initForm();
    }

    private void initForm() {
        this.vista.setVisible(true);
        actionListener(this);
    }

    private void actionListener(ActionListener controlador) {
        this.vista.btnGuardar.addActionListener(controlador);
    }

    private void actionPersonaGuardar() {
        this.persona = new Persona(this.vista.txtNombre.getText(),
                this.vista.txtApellido.getText(),
                this.vista.txtDocumento.getText(),
                this.vista.txtTelefono.getText(),
                this.vista.checkResponsable.isSelected());
        this.vista.lblRegistro.setVisible(true);
        this.vista.btnGuardar.setEnabled(false);
    }

    public Persona getPersona() {
        return persona;
    }

    public FormPersona getVista() {
        return vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // VALIDAR ACTION
            if (e.getActionCommand().contentEquals("actionPersonaGuardar")) {
                actionPersonaGuardar();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

}
