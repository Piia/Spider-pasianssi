package Tekstikayttoliittyma;

import Peli.Spider;
import Peli.Vaikeusaste;
import Rajapinnat.PinoRajapinta;
import Rajapinnat.SpiderRajapinta;
import java.util.ArrayList;
import java.util.Scanner;

public class Tekstikayttoliittyma {

    private Vaikeusaste vaikeusaste;
    private Scanner lukija;
    private SpiderRajapinta spider;

    public Tekstikayttoliittyma() {
        this.lukija = new Scanner(System.in);

    }

    public void kaynnista() {
        alkuPuheet();
        valitseVaikeusaste();
        this.spider = new Spider(vaikeusaste);
        System.out.println("Onnea peliin! \n");

        while (!spider.peliLoppui()) {
            tulostaPeli();

            if (jaetaanko()) {
                continue;
            }

            siirto();

        }

        System.out.println("Peli loppui! Onnea!");
    }

    private void alkuPuheet() {
        System.out.println("********************");
        System.out.println("* SPIDER-PASIANSSI *");
        System.out.println("********************");
        System.out.println();
    }

    private void valitseVaikeusaste() {
        System.out.println("Valitse vaikeusaste!");
        System.out.println("1=HELPPO, 2=NORMAALI tai 3=VAIKEA");
        System.out.print(">> ");

        while (true) {
            int valinta = Integer.parseInt(lukija.nextLine());
            if (valinta == 1) {
                this.vaikeusaste = Vaikeusaste.HELPPO;
                break;

            } else if (valinta == 2) {
                this.vaikeusaste = Vaikeusaste.NORMAALI;
                break;

            } else if (valinta == 3) {
                this.vaikeusaste = Vaikeusaste.VAIKEA;
                break;
            }
            System.out.println("Väärä valinta, yritä uudelleen!");
            System.out.print(">> ");
        }
        System.out.println();
    }

    private void tulostaPeli() {
        ArrayList<PinoRajapinta> pinot = spider.getPinot();

        System.out.print("  ");
        for (int i = 0; i < 10; i++) {
            System.out.print(String.format("%-4s", "\u263A" + i));
        }
        System.out.println("\n ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        for (int i = 0; i < pisinPinonPituus(pinot); i++) {
            System.out.print(String.format("%-3s", i + "|"));
            for (PinoRajapinta pino : pinot) {

                if (pino.getPituus() < i + 1) {
                    System.out.print(String.format("%-4s", "\u3000"));
                } else {
                    System.out.print(pino.getKortti(i).toString());

                }
            }
            System.out.println();
        }

    }

    private int pisinPinonPituus(ArrayList<PinoRajapinta> pinot) {
        int pisin = 0;
        for (PinoRajapinta pino : pinot) {
            if (pino.getPituus() > pisin) {
                pisin = pino.getPituus();
            }
        }
        return pisin;
    }

    private void siirto() {
        System.out.print("Pinosta: ");
        int pinosta;
        while (true) {
            try {
                pinosta = Integer.parseInt(lukija.nextLine());
                if (pinosta >= 0 && pinosta < 10) {
                    break;
                }

            } catch (NumberFormatException numberFormatException) {
                System.out.print("Yritä uudelleen: ");
            }
        }

        System.out.print("Riviltä: ");
        int rivilta;
        while (true) {
            try {
                rivilta = Integer.parseInt(lukija.nextLine());
                if (rivilta >= 0 && rivilta < spider.getPinot().get(pinosta).getPituus()) {
                    break;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Yritä uudelleen: ");
            }
        }

        System.out.print("Pinoon: ");
        int pinoon;
        while (true) {
            try {
                pinoon = Integer.parseInt(lukija.nextLine());
                if (pinoon >= 0 && pinoon < 10) {
                    break;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Yritä uudelleen: ");
            }
        }

        boolean siirtoOnnistui = spider.siirto(rivilta, pinosta, pinoon);
        if (siirtoOnnistui == false) {
            System.out.println("Siirto epäonnistui!");
        }
        System.out.println();
    }

    private boolean jaetaanko() {
        System.out.print("JAETAANKO('joo'/enter): ");
        String syote = lukija.nextLine();
        if (syote.equalsIgnoreCase("joo")) {
            spider.jaa();
            return true;
        }
        return false;
    }
}
