/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import db.BBPodataka;
import domen.Advokat;
import domen.Klijent;
import domen.Predmet;
import domen.VrstaPostupka;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

/**
 *
 * @author PC
 */
public class Kontroler {
    
   public static Kontroler instance;
    BBPodataka dbb;
    ArrayList<Advokat> listaAdvokata;
    public Kontroler() {
        dbb = new BBPodataka();
        listaAdvokata = new ArrayList<>();
        Advokat a1 = new Advokat("Marija", "Mladenovic", "maki", "maki", "Izvrsni postupak");
        Advokat a2 = new Advokat("Aleksa", "Saric", "alek", "alek", "Parnicni postupak");
        listaAdvokata.add(a1);
        listaAdvokata.add(a2);
    }

    public static Kontroler getInstance() {
        if(instance==null) {
            instance = new Kontroler();
                    
        }
        return instance;
    }

    public Advokat daLiPostojiAdvokat(String username, String pass) {
        for (Advokat advokat : listaAdvokata) {
            if(advokat.getUsername().equals(username) && advokat.getPassword().equals(pass)) return advokat;
        }
        return null;
    }

    public ArrayList<Advokat> getListaAdvokata() {
        return listaAdvokata;
    }

    public ArrayList<Klijent> vratiKlijente() {
        ArrayList<Klijent> listaKlijenta = new ArrayList<>();
       try {
           dbb.ucitajDriver();
           dbb.otvoriKonekciju();
           listaKlijenta = dbb.dajklIJENTE();
           //dbb.commit();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       }finally{
               try {
                   dbb.zatvoriKonekciju();
               } catch (SQLException ex1) {
                   Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
               }
            }
        
        return listaKlijenta;
    }

    public ArrayList<VrstaPostupka> vratiListuPostupaka() {
       ArrayList<VrstaPostupka> listaVP = new ArrayList<>();
       try {
           dbb.ucitajDriver();
           dbb.otvoriKonekciju();
           listaVP = dbb.dajVP();
          // dbb.commit();
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
       }finally{
               try {
                   dbb.zatvoriKonekciju();
               } catch (SQLException ex1) {
                   Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
               }
            }
        
        return listaVP;
    }

   

    public boolean sacuvajPredmete(ArrayList<Predmet> listaPredmeta) {
        boolean sacuvano = false;
       try {
           dbb.ucitajDriver();
           dbb.otvoriKonekciju();
           for (Predmet predmet : listaPredmeta) {
               dbb.sacuvajPredmet(predmet);
           }
           dbb.commit();
           sacuvano = true;
           
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
           try {
               dbb.rollback();
               sacuvano= false;
           } catch (SQLException ex1) {
               Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
           }
       } catch (SQLException ex) {
           Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            try {
               dbb.rollback();
               sacuvano = false;
           } catch (SQLException ex1) {
               Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
           }finally{
               try {
                   dbb.zatvoriKonekciju();
               } catch (SQLException ex1) {
                   Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex1);
               }
            }
            
       }
       return sacuvano;
    }

    

    
   
    
   
}
