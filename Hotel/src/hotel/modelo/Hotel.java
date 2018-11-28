/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.modelo;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author OMAR MONTES
 */
public class Hotel {

    private String nombre;
    private Piso[] pisos;
    private ArrayList<Temporada> temporadas;

    public Hotel() {
    }

    /**
     * Crea un nuevo hotel
     *
     * @param nombre nombre del hotel
     * @param totalPisos total de pisos del hotel
     * @param habitacionesPorPiso total de habitaciones por piso
     */
    public Hotel(String nombre, int totalPisos, int habitacionesPorPiso) {
        this.nombre = nombre;
        this.pisos = new Piso[totalPisos];

        for (int i = 0; i < totalPisos; i++) {
            this.pisos[i] = new Piso(i + 1, habitacionesPorPiso);
        }
        this.temporadas = new ArrayList<>();

    }

    public void iniciarHotel() {
        int numPiso, numHab;
        for (int piso = 0; piso < 3; piso++) {
            numPiso = (piso + 1) * 100;
            for (int hab = 0; hab < 5; hab++) {
                numHab = numPiso + hab + 1;
                this.pisos[piso].setHabitacion(new Habitacion(numHab, 3, 100000f), hab);
            }
        }
        this.temporadas.add(new Temporada(1, "Año nuevo", null, new Date(), new Date()));
        this.temporadas.add(new Temporada(2, "Año viejo", null, new Date(), new Date()));
        this.temporadas.add(new Temporada(3, "pascua", null, new Date(), new Date()));

        this.pisos[0].getHabitacion(0).alquilarHabitacion(
                new Persona[]{
                    new Persona("omar", "montes", "123456", "5555", Boolean.TRUE)
                },
                this.getTemporada(0)
        );

    }

    public ArrayList<Habitacion> habitacionesListado(HabitacionEstado estado) {

        ArrayList<Habitacion> listado = new ArrayList<>();

        for (Piso piso : this.pisos) {

            listado.addAll(piso.habitacionesListado(estado));

        }

        return (listado);
    }

    public Habitacion getHabitacion(int numeroHabitacion) {

        int piso = (numeroHabitacion / 100) - 1; // indice del piso
        int habitacion = (numeroHabitacion & 100) - 1; // indice de la habitacion

        if (piso >= 0 && piso < this.pisos.length) {

            return this.pisos[piso].getHabitacion(habitacion);

        }

        return (null);
    }

    public Piso getPiso(int indice) {

        if (indice >= 0 && indice <= this.pisos.length) {
            return this.pisos[indice];
        }

        return (null);
    }

    public Temporada getTemporada(int indice) {

        if (indice >= 0 && indice <= this.temporadas.size()) {
            return this.temporadas.get(indice);
        }

        return (null);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Piso[] getPisos() {
        return pisos;
    }

    public void setPisos(Piso[] pisos) {
        this.pisos = pisos;
    }

    public ArrayList<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(ArrayList<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

}
