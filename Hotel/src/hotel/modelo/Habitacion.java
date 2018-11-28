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
public class Habitacion {

    private int numero; // numero de la habitacion
    private int maximoOcupantes; // numero maximo de ocupantes por la habitacion
    private int numeroOcupantes = 0; // numero de ocupantes que estan dentro de la habitacion
    private Persona[] ocupantes;
    private Temporada temporada;
    private Float precioAlquiler;
    private HabitacionEstado estado;

    public Habitacion() {
    }

    public Habitacion(int numero, int maximoOcupantes, Float precioAlquiler) {
        this.numero = numero;
        this.maximoOcupantes = maximoOcupantes;
        this.precioAlquiler = precioAlquiler;
        this.ocupantes = new Persona[maximoOcupantes];
        this.estado = HabitacionEstado.DISPONIBLE;
    }

    public Boolean alquilarHabitacion(Persona[] personas, Temporada temporada) {

        if (personas != null && this.habitacionDisponible() && personas.length <= ocupantes.length) {

            int totalResponsables = 0;

            for (Persona p : personas) {
                if (p.getResponsable()) {
                    totalResponsables++;
                }
            }
            if (totalResponsables == 1) {
                this.ocupantes = personas; // llenar la habitacion de personas
                this.temporada = temporada; // insertar la temporada
                this.numeroOcupantes = personas.length; // guardar cuantas personas alquilaron la habitacion
                this.estado = HabitacionEstado.OCUPADA;
                return (true); // Hecho
            } else {
                return (false); // debe existir un solo responsable en la habitacion
            }
        }

        return (false);
    }

    public Persona obtenerResponsable() {

        for (int i = 0; i < this.numeroOcupantes; i++) {

            if (this.ocupantes[i].getResponsable()) {
                return (this.ocupantes[i]);
            }

        }

        return (null);
    }

    public ArrayList<Persona> getListaPersonas() {
        ArrayList<Persona> listado = new ArrayList<>();

        for (int i = 0; i < this.numeroOcupantes; i++) {
            listado.add(this.ocupantes[i]);
        }

        return (listado);
    }

    public Boolean habitacionDisponible() {
        return this.estado == HabitacionEstado.DISPONIBLE;
    }

    public void desocuparHabitacion() {
        this.numeroOcupantes = 0;
        this.ocupantes = new Persona[this.maximoOcupantes];
        this.temporada = null;
        this.estado = HabitacionEstado.DISPONIBLE;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getMaximoOcupantes() {
        return maximoOcupantes;
    }

    public void setMaximoOcupantes(int maximoOcupantes) {
        this.maximoOcupantes = maximoOcupantes;
        desocuparHabitacion();
    }

    public int getNumeroOcupantes() {
        return numeroOcupantes;
    }

    public void setNumeroOcupantes(int numeroOcupantes) {
        this.numeroOcupantes = numeroOcupantes;
    }

    public Persona[] getOcupantes() {
        return ocupantes;
    }

    public void setOcupantes(Persona[] ocupantes) {
        this.ocupantes = ocupantes;
    }

    public Temporada getTemporada() {
        return temporada;
    }

    public void setTemporada(Temporada temporada) {
        this.temporada = temporada;
    }

    public Float getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setPrecioAlquiler(Float precioAlquiler) {
        this.precioAlquiler = precioAlquiler;
    }

    public HabitacionEstado getEstado() {
        return estado;
    }

    public void setEstado(HabitacionEstado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "#" + this.numero + ": " + this.estado;
    }

}
