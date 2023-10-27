package main;
import java.awt.Color;
import java.awt.Panel;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Tabella {
	private String data[] = {"", ""};
	private DefaultTableModel tableModel;
	private int count;
	private JTable tabella;
	public JTable getTabella() {
		return tabella;
	}

	
	
    public Tabella(Panel p, JPanel pannello){        
	    String column[] = {"NOME","COGNOME"};
	    tableModel = new DefaultTableModel(column, 0);
	    tabella = new JTable(tableModel);
	    tabella.setBounds(100,30,200,1000);
	    tabella.setBackground(new Color(245,255,250));
	    tabella.setVisible(true);
	    pannello.add(tabella);
    }     
    
    public void setRow(String row[]){
    	data = row;
    }
    
    public void fillTable() {
    	count++;
    	tableModel.addRow(data);
    }
    
    public void svuotaTable() {
	    for (int i = count-1; i >= 0; i--) {
	    	tableModel.removeRow(i);
	    }
	    count = 0;
    }
    
}
