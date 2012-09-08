/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Peli;

import Rajapinnat.KorttiRajapinta;
import java.util.ArrayList;
import org.junit.Test;

/**
 *
 * @author Piia
 */
public class PinoTest {
    
    public PinoTest() {
    }

    
    
    @Test
    public void lisaaYksiKorttiTest() {
        Pino pino = new Pino();
        
        Kortti kortti1 = new Kortti(Maa.HERTTA, 10);
        pino.lisaa(kortti1);
        assert(pino.getKortti(0).equals(kortti1));
        
        Kortti kortti2 = new Kortti(Maa.RISTI, 2);
        pino.lisaa(kortti2);
        assert(pino.getKortti(1).equals(kortti2));
    }
    
    @Test
    public void lisaaListaKorttejaTest() {
        Pino pino = new Pino();
        
        ArrayList<KorttiRajapinta> lista = new ArrayList<KorttiRajapinta>();
        Kortti kortti1 = new Kortti(Maa.RUUTU, 1);
        Kortti kortti2 = new Kortti(Maa.RISTI, 2);
        Kortti kortti3 = new Kortti(Maa.HERTTA, 3);
        Kortti kortti4 = new Kortti(Maa.PATA, 4);
        lista.add(kortti1);
        lista.add(kortti2);
        lista.add(kortti3);
        lista.add(kortti4);
        
        pino.lisaa(lista);
        
        assert(pino.getKortti(0).equals(kortti1));
        assert(pino.getKortti(1).equals(kortti2));
        assert(pino.getKortti(2).equals(kortti3));
        assert(pino.getKortti(3).equals(kortti4));
        
    }
    
    @Test
    public void poistaYksiKorttiTest() {
        Pino pino = new Pino();
        pino.lisaa(new Kortti(Maa.PATA, 2));
        pino.lisaa(new Kortti(Maa.PATA, 3));
        
        assert(pino.getPituus() == 2);
        
        pino.poista(1);
        assert(pino.getPituus() == 1);
    }
    
    @Test
    public void poistaUseitaKorttejaTest() {
        Pino pino = new Pino();
        
        pino.lisaa(new Kortti(Maa.RUUTU, 6));
        pino.lisaa(new Kortti(Maa.RUUTU, 7));
        pino.lisaa(new Kortti(Maa.RUUTU, 8));
        pino.lisaa(new Kortti(Maa.RUUTU, 9));
        
        
        pino.poista(1);
        assert(pino.getPituus() == 1);
    }
    
    @Test
    public void poistaKunPinoOnTyhjaTest() {
        Pino pino = new Pino();
        assert(pino.getPituus() == 0);
        
        pino.poista(0);
    }
    
    @Test
    public void poistaLiianIsollaIndeksillaTest() {
        Pino pino = new Pino();
        pino.lisaa(new Kortti(Maa.RISTI, 12));
        pino.lisaa(new Kortti(Maa.RISTI, 13));
        
        pino.poista(2);
        assert(pino.getPituus() == 2);
    }
    
    @Test
    public void voikoLisataTest() {
        Pino pino = new Pino();
        pino.lisaa(new Kortti(Maa.RISTI, 13));
        pino.lisaa(new Kortti(Maa.RISTI, 12));
        pino.lisaa(new Kortti(Maa.RISTI, 11));
        pino.lisaa(new Kortti(Maa.RISTI, 10));
        
        assert(pino.voikoLisata(new Kortti(Maa.HERTTA, 9)));
        assert(pino.voikoLisata(new Kortti(Maa.PATA, 9)));
        assert(pino.voikoLisata(new Kortti(Maa.RISTI, 9)));
        assert(pino.voikoLisata(new Kortti(Maa.RUUTU, 9)));
        
        assert(!pino.voikoLisata(new Kortti(Maa.RISTI, 8)));
        assert(!pino.voikoLisata(new Kortti(Maa.RISTI, 10)));
        
    }
    
    @Test
    public void voikoPoistaaTest() {
        Pino pino = new Pino();
        Kortti kortti1 = new Kortti(Maa.HERTTA, 10);
        Kortti kortti2 = new Kortti(Maa.HERTTA, 9);
        Kortti kortti3 = new Kortti(Maa.HERTTA, 8);
        
        pino.lisaa(kortti1);
        pino.lisaa(kortti2);
        pino.lisaa(kortti3);
        
        assert(!pino.voikoPoistaa(2)); //Kortti on väärinpäin.;
        
        kortti1.setNakyvaksi();
        assert(!pino.voikoPoistaa(0)); //Päällä olevat kortit väärinpäin.
        
        kortti2.setNakyvaksi();
        kortti3.setNakyvaksi();
        assert(pino.voikoPoistaa(2));
        assert(pino.voikoPoistaa(1));
        assert(pino.voikoPoistaa(0));
    }
    
    @Test
    public void pinoOnTyhjaTest() {
        Pino pino = new Pino();
        assert(pino.getPituus() == 0);
        assert(pino.pinoOnTyhja());
    }
    
    @Test
    public void pinoEiOleTyhjaTest() {
        Pino pino = new Pino();
        pino.lisaa(new Kortti(Maa.HERTTA, 4));
        assert(pino.getPituus() == 1);
        assert(!pino.pinoOnTyhja());
    }
    
    
    @Test
    public void paivitaTest() {
        Pino pino = new Pino();
        Kortti kortti1 = new Kortti(Maa.RISTI, 2);
        pino.lisaa(kortti1);
        for(int i = 13; i > 0; i--) {
            Kortti kortti = new Kortti(Maa.HERTTA, i);
            kortti.setNakyvaksi();
            pino.lisaa(kortti);
        }
        
        assert(pino.getPituus() == 14);
        
        pino.paivita();
        assert(pino.getPituus() == 1);
        assert(kortti1.getNakyvyys() == true);
    }
    
    @Test
    public void getAlinPoistettavaTest() {
        Pino pino = new Pino();      
        
        Kortti kortti1 = new Kortti(Maa.HERTTA, 11);
        Kortti kortti2 = new Kortti(Maa.RISTI, 10);
        pino.lisaa(kortti1);
        pino.lisaa(kortti2);
        assert(pino.getAlinPoistettava() == -1);
        
        kortti1.setNakyvaksi();
        kortti2.setNakyvaksi();
        assert(pino.getAlinPoistettava() == 1);
        
        Kortti kortti3 = new Kortti(Maa.RUUTU, 11);
        Kortti kortti4 = new Kortti(Maa.RUUTU, 10);
        kortti3.setNakyvaksi();
        kortti4.setNakyvaksi();
        pino.lisaa(kortti3);
        pino.lisaa(kortti4);
        assert(pino.getAlinPoistettava() == 2);
        
        Kortti kortti5 = new Kortti(Maa.RISTI, 13);
        Kortti kortti6 = new Kortti(Maa.RISTI, 12);
        Kortti kortti7 = new Kortti(Maa.RISTI, 11);
        Kortti kortti8 = new Kortti(Maa.RISTI, 10);
        kortti5.setNakyvaksi();
        kortti6.setNakyvaksi();
        kortti7.setNakyvaksi();
        kortti8.setNakyvaksi();
        pino.lisaa(kortti5);
        pino.lisaa(kortti6);
        pino.lisaa(kortti7);
        pino.lisaa(kortti8);
        assert(pino.getAlinPoistettava() == 4);
        
    }
    
    
}
