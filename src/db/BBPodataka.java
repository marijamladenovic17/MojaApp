/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.Klijent;
import domen.Predmet;
import domen.VrstaPostupka;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class BBPodataka {
    Connection konekcija;

    public BBPodataka() {
    }
    
    public void ucitajDriver() throws ClassNotFoundException{
         Class.forName("com.mysql.jdbc.Driver");
    }
    
    public void otvoriKonekciju() throws SQLException{
        String url = "jdbc:mysql://localhost/g1";
        String user = "root";
        String pass = "";
        
        konekcija = DriverManager.getConnection(url, user, pass);
        konekcija.setAutoCommit(false);
    }
    
    public void zatvoriKonekciju() throws SQLException{
        konekcija.close();
    }
    
    public  void commit() throws SQLException {
        konekcija.commit();
    }
    
    public  void rollback() throws SQLException {
        konekcija.rollback();
    }

    public ArrayList<Klijent> dajklIJENTE() {
       String upit = "SELECT * FROM  klijent ORDER BY Prezime asc";
       ArrayList<Klijent> listaKlijenta = new ArrayList<>();
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            
            while (rs.next()) {
               int klijentId = rs.getInt(1);
               String ime = rs.getString(2);
               String prezime= rs.getString(3);
               String tel = rs.getString(4);
               String elPosta = rs.getString(5);
               String adresa = rs.getString(6);
                
               Klijent k  = new Klijent(klijentId, ime, prezime, tel, elPosta, adresa);
               listaKlijenta.add(k);
               
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(BBPodataka.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return listaKlijenta;
    }

    public ArrayList<VrstaPostupka> dajVP() {
        String upit = "SELECT * FROM  vrstapostupka ORDER BY Naziv ASC";
       ArrayList<VrstaPostupka> listaVP = new ArrayList<>();
        try {
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            
            while (rs.next()) {
               int postupakId = rs.getInt(1);
               String naziv = rs.getString(2);
               VrstaPostupka vp = new VrstaPostupka(postupakId, naziv);
               listaVP.add(vp);
               
            }
            s.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(BBPodataka.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return listaVP;
    }

    public void sacuvaj(ArrayList<Predmet> listaPredmeta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void sacuvajPredmet(Predmet predmet) {
       String upit = "INSERT INTO predmet(PredmetID,Naziv,Problem,Datum,Advokat,KlijentID,VrstaPostupkaID) VALUES(?,?,?,?,?,?,?) ";
        
        try {
            PreparedStatement ps = konekcija.prepareStatement(upit);
            ps.setInt(1, vratiMax());
            ps.setString(2, predmet.getNaziv());
            ps.setString(3, predmet.getProblem());
            Date datum = new Date(predmet.getDatum().getTime());
            ps.setDate(4, datum);
            ps.setString(5, predmet.getAdvokat().toString());
            ps.setInt(6, predmet.getKlijent().getKlijentId());
            ps.setInt(7, predmet.getVrstaPostupka().getVrstaPostupkaId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BBPodataka.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private int vratiMax() {
        int max = 0;
        try {
            String upit = "SELECT MAX(PredmetID)as max FROM predmet";
            Statement s = konekcija.createStatement();
            ResultSet rs = s.executeQuery(upit);
            while (rs.next()) {
                max = rs.getInt("max");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BBPodataka.class.getName()).log(Level.SEVERE, null, ex);
        }
         return ++max;
    }
   
}
