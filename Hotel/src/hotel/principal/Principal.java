/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.principal;

import hotel.controlador.ControladorMenu;
import hotel.modelo.Hotel;

/**
 *
 * @author OMAR MONTES
 */
public class Principal {

    public static void main(String[] args) {

        Hotel hotel = new Hotel("Hotel", 3, 5);
        hotel.iniciarHotel();
        ControladorMenu menu = new ControladorMenu(hotel);

    }

}
