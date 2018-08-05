/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

/**
 *
 * @author PC
 */
public class VrstaPostupka {
    private int vrstaPostupkaId;
    private String naziv;

    public VrstaPostupka() {
    }

    public VrstaPostupka(int vrstaPostupkaId, String naziv) {
        this.vrstaPostupkaId = vrstaPostupkaId;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getVrstaPostupkaId() {
        return vrstaPostupkaId;
    }

    public void setVrstaPostupkaId(int vrstaPostupkaId) {
        this.vrstaPostupkaId = vrstaPostupkaId;
    }

    @Override
    public String toString() {
        return  naziv ;
    }
    
    
}
