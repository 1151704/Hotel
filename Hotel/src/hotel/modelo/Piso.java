/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.modelo;

import java.util.ArrayList;

/**
 *
 * @author OMAR MONTES
 */
public class Piso {

    private int numero;
    private Habitacion[] habitaciones;

    public Piso() {
    }

    public Piso(int numero, int numeroHabitaciones) {
        this.numero = numero;
        this.habitaciones = new Habitacion[numeroHabitaciones];
    }

    public ArrayList<Habitacion> habitacionesListado(HabitacionEstado estado) {

        ArrayList<Habitacion> listado = new ArrayList<>();

        for (Habitacion habitacion : this.habitaciones) {
            if (habitacion.getEstado() == estado) {
                listado.add(habitacion);
            }
        }

        return (listado);
    }

    public Habitacion getHabitacion(int indice) {

        if (indice >= 0 && indice < this.habitaciones.length) {
            return this.habitaciones[indice];
        }

        return (null);
    }

    public void setHabitacion(Habitacion habitacion, int indice) {
        if (indice >= 0 && indice < this.habitaciones.length) {
            this.habitaciones[indice] = habitacion;
        }
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Habitacion[] getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Habitacion[] habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
    public String toString() {
        return "" + numero;
    }

}
