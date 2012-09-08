package Kayttoliittyma;

import javax.swing.JFrame;


/**
 * Tämä luokka käynnistää graafisen käyttöliittymän kutsumalla 
 * kayttoliittyma-luokan run-metodia.
 * 
 * @author Piia
 */

public class Main extends JFrame {

    
    public static void main(String[] args) {
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        kayttoliittyma.run();
    }
}
