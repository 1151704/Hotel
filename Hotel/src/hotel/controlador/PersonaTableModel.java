/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.controlador;

import hotel.modelo.Persona;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author OMAR MONTES
 */
public class PersonaTableModel extends AbstractTableModel {

    protected ArrayList<Persona> personas;

    public PersonaTableModel(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    @Override
    public int getRowCount() {
        return this.personas.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona persona = this.personas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return persona.getNombre();
            case 1:
                return persona.getApellido();
            case 2:
                return persona.getCedula();
            case 3:
                return persona.getTelefono();
            case 4:
                return persona.getResponsable() ? "SI" : "NO";
        }
        return ("");
    }

    @Override
    public String getColumnName(int aCol) {
        switch (aCol) {
            case 0:
                return "Nombre";
            case 1:
                return "Apellido";
            case 2:
                return "Cedula";
            case 3:
                return "Telefono";
            case 4:
                return "Responsable";
        }
        return "";
    }

}
