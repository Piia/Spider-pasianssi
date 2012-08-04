package Peli;

import Rajapinnat.KorttiRajapinta;


public class Main {
    
    public static void main(String[] args) {
        Pakka pakka = new Pakka(Vaikeusaste.NORMAALI);
        for(KorttiRajapinta kortti: pakka.getPakka()) {
            System.out.println(kortti);
        }
    }
}
