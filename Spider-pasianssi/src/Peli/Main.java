package Peli;


public class Main {
    
    public static void main(String[] args) {
        Pakka pakka = new Pakka(4);
        for(KorttiRajapinta kortti: pakka.getPakka()) {
            System.out.println(kortti);
        }
    }
}
