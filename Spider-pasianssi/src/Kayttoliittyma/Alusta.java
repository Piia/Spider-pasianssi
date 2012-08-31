package Kayttoliittyma;

import Peli.Spider;
import Peli.Vaikeusaste;
import Rajapinnat.SpiderRajapinta;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Alusta extends JPanel {

    private SpiderRajapinta spider;
    private ArrayList<Piirturi> piirturit;
    private Vaikeusaste vaikeusaste;
    
    private boolean yksiKorttiValittu;
    private int valittuPinosta;
    private int valittuIndeksi;

    public Alusta(Vaikeusaste vaikeusaste) {
        super(new GridLayout());
        
        this.yksiKorttiValittu = false;
        this.piirturit = new ArrayList<Piirturi>();
        this.vaikeusaste = vaikeusaste;
        this.spider = new Spider(vaikeusaste);
        
        
        for (int i = 0; i < 10; i++) {
            Piirturi p = new Piirturi(this, spider.getPinot().get(i), i);p.setEnabled(true);
            p.setVisible(true);
            p.setEnabled(true);
            p.setLocation(pinonX(i), 50);
            piirturit.add(p);
            this.add(p);
        }
        
    }

    // Any difference?
    @Override
    public void paint(Graphics g) {
        this.setBackground(new Color(34, 139, 34));
        for(Piirturi p: piirturit) {
            p.paint(g);
        }
        
    }

    
    @Override
    public void repaint(){
        
        if(spider != null){
            for(Piirturi p: piirturit) {
                p.repaint();
            }
        }
        super.repaint();
    }

    public void jaa() {
        if (spider == null) {
            return;
        }
        spider.jaa();
        this.repaint();
    }

    public int montaJakoa() {
        if (spider == null) {
            return 0;
        }
        return spider.montaJakoa();
    }

    protected void korttiValittu(int pino, int indeksi) {
        if (yksiKorttiValittu == false) {
            valittuPinosta = pino;
            valittuIndeksi = indeksi;
            yksiKorttiValittu = true;

        } else if (valittuPinosta == pino) {
            valittuIndeksi = indeksi;

        } else {
            siirto(valittuIndeksi, valittuPinosta, pino);
            yksiKorttiValittu = false;
        }
    }

    private void siirto(int indeksi, int pinosta, int pinoon) {
        spider.siirto(indeksi, pinosta, pinoon);
        for(Piirturi p: piirturit) {
            p.setValittuPois();
        }
        this.repaint();
        
        if(spider.peliLoppui()) {
            JOptionPane.showMessageDialog (null, "Onneksi olkoon!", 
                    "Peli loppui!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private int pinonX(int pino) {
        int kortinLeveys = 72;
        return 50 + pino * kortinLeveys + pino * 25;
    }
}
