/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Peli;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Piia
 */
public class PakkaTest {
    
    public PakkaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    
    // OLEN TESTANNUT MANUAALISESTI, ETTÄ PAKASTA LÖYTYY TARVITTAVAT KORTIT.
    // NIITÄ OLISIKIN VAIKEA TODENTAA...
    
    @Test
    public void konstruktoriTest() {
        Pakka pakka = new Pakka(Vaikeusaste.VAIKEA);
    }
    
    @Test
    public void annaKorttiTest() {
        Pakka pakka = new Pakka(Vaikeusaste.HELPPO);
        assert(pakka.annaKortti().getClass() == Kortti.class);
    }

    @Test
    public void pakkaOnTyhjaTest() {
        Pakka pakka = new Pakka(Vaikeusaste.NORMAALI);
        for(int i = 0; i < 104; i++) {
            pakka.annaKortti();
        }
        
        assert(pakka.getPakka().isEmpty());
        assert(pakka.onTyhja());
    }
    
    @Test
    public void pakkaEiOleTyhjaTest() {
        Pakka pakka = new Pakka(Vaikeusaste.NORMAALI);
        assert(!pakka.getPakka().isEmpty());
        assert(!pakka.onTyhja());
    }
    
    @Test
    public void montaJakoaTest() {
        Pakka pakka = new Pakka(Vaikeusaste.VAIKEA);
        assert(pakka.montaJakoa() != 0);
    }
    
    
}
