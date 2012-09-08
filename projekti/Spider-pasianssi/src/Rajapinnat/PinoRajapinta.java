package Rajapinnat;

import java.util.List;

/**
 * PinoRajapinta määrittelee pinon metodit.
 * @author Piia
 */

public interface PinoRajapinta {
    
    public void lisaa(KorttiRajapinta kortti);
    public void lisaa(List<KorttiRajapinta> kortteja);
    public void poista(int indeksi);
    public boolean voikoLisata(KorttiRajapinta kortti);
    public boolean voikoPoistaa(int indeksi);
    public boolean pinoOnTyhja();
    public void paivita();
    public List<KorttiRajapinta> getPino();
    public List<KorttiRajapinta> getOsaPino(int indeksi);
    public KorttiRajapinta getKortti(int indeksi);
    public int getPituus();
    public int getAlinPoistettava();
}
