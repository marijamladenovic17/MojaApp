/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Advokat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class ModelTabelePrimer extends AbstractTableModel{
    ArrayList<Advokat> lista;

    public ModelTabelePrimer(ArrayList<Advokat> lista) {
        this.lista = lista;
    }

    public ModelTabelePrimer() {
        lista = new ArrayList<>();
    }

    
    
    
    @Override
    public int getRowCount() {
       return lista.size();
    }

    @Override
    public int getColumnCount() {
       return 1;
    }

    @Override
    public String getColumnName(int column) {
        return "sviAdvokati";
    }
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      Advokat a = lista.get(rowIndex);
      return a.toString();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
       return true;
    }
    
    
}
