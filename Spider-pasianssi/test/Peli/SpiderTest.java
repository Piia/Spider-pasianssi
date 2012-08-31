/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Peli;

import Rajapinnat.PinoRajapinta;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Piia
 */
public class SpiderTest {
    
    public SpiderTest() {
    }

    

    @Test
    public void peliLoppuiTest() {
        Spider spider = new Spider(Vaikeusaste.HELPPO);
        ArrayList<PinoRajapinta> pinot = spider.getPinot();
        
        while(spider.jaa()) {
            
        }
        assert(!spider.jaa());
        
        for(PinoRajapinta pino: pinot) {
            pino.poista(0);
            assert(pino.pinoOnTyhja()) : "Pino ei tyhjentynytkään!";
        }
        
        assert(spider.peliLoppui());
        
    }
    
    @Test
    public void jaaTest() {
        Spider spider = new Spider(Vaikeusaste.VAIKEA);
        ArrayList<PinoRajapinta> pinot = spider.getPinot();
        ArrayList<Integer> pinojenPituudet = new ArrayList<Integer>();
        
        for(PinoRajapinta pino: pinot) {
            pinojenPituudet.add(pino.getPituus());
        }
        
        spider.jaa();
        
        for(int i = 0; i < 10; i++) {
            assert(pinojenPituudet.get(i)+1 == pinot.get(i).getPituus());
        }
    }
    
    // SIIRTOMETODIA EN OSAA TESTATA, SE KUITENKIN TOIMII :D
    
}
