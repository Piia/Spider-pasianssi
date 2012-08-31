package Peli;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import org.junit.Test;

/**
 *
 * @author Piia
 */
public class KorttiTest {
    
    public KorttiTest() {
    }

    
    @Test
    public void konstruktoriAsettaaMaan() {
        Kortti kortti = new Kortti(Maa.HERTTA, 10);
        assert(Maa.HERTTA == kortti.getMaa());        
    }
    
    @Test
    public void konstruktoriAsettaaArvon() {
        Kortti kortti = new Kortti(Maa.PATA, 10);
        assert(10 == kortti.getArvo());
    }
    
    @Test
    public void konstruktoriAsettaaNakyvyyden() {
        Kortti kortti = new Kortti(Maa.RUUTU, 11);
        assert(!kortti.getNakyvyys());
    }
    
    @Test
    public void korttiKaantyyOikeinPain() {
        Kortti kortti = new Kortti(Maa.RISTI, 1);
        kortti.setNakyvaksi();
        assert(kortti.getNakyvyys());
    }
}
