/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Predmet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class ModelTabele extends AbstractTableModel{
    ArrayList<Predmet> listaPredmeta;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    public ModelTabele() {
        listaPredmeta = new ArrayList<>();
    }
    
     
    @Override
    public int getRowCount() {
        return listaPredmeta.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       Predmet p = listaPredmeta.get(rowIndex);
       
       switch(columnIndex){
           case 0 : return p.getAdvokat();
           case 1: return p.getKlijent();
           case 2: return sdf.format(p.getDatum());
           case 3: return p.getNaziv();
           default: return null;
       }
    }

    @Override
    public String getColumnName(int column) {
           switch(column){
           case 0 : return "Advokat";
           case 1: return "Klijent";
           case 2: return "Datum";
           case 3: return "Naziv";
           default: return null;
       }
    }

    public void dodajPredmet(Predmet predmet) {
       listaPredmeta.add(predmet);
       fireTableDataChanged();  
    }

   

    public void obrisiPredmet(int red) {
       listaPredmeta.remove(red);
       fireTableDataChanged();  
    }

    public ArrayList<Predmet> dajPredmete() {
       return listaPredmeta;
    }
    
    
    
}
