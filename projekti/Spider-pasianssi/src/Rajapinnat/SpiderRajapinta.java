package Rajapinnat;

import java.util.ArrayList;

/**
 * SpiderRajapinta määrittelee spiderin metodit.
 * @author Piia
 */

public interface SpiderRajapinta {
    
    public boolean peliLoppui();
    public boolean jaa();
    public boolean siirto(int indeksi, int pinosta, int pinoon);
    public ArrayList<PinoRajapinta> getPinot();
    public int montaJakoa();
}
