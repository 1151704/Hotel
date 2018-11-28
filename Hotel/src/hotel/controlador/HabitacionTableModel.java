/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.controlador;

import hotel.modelo.Habitacion;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author OMAR MONTES
 */
public class HabitacionTableModel extends AbstractTableModel {

    protected ArrayList<Habitacion> habitaciones;

    public HabitacionTableModel(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
    public int getRowCount() {
        return this.habitaciones.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Habitacion habitacion = this.habitaciones.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return habitacion.getNumero();
            case 1:
                return habitacion.getMaximoOcupantes();
            case 2:
                return habitacion.getTemporada() != null ? habitacion.getTemporada().toString() : "";
            case 3:
                return habitacion.getEstado();
        }
        return ("");
    }

    @Override
    public String getColumnName(int aCol) {
        switch (aCol) {
            case 0:
                return "Numero";
            case 1:
                return "Maximo";
            case 2:
                return "Temporada";
            case 3:
                return "Estado";
        }
        return "";
    }

}
