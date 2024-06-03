package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Grafico extends JPanel{
	private TreeMap<String,Integer> pagos;
	
	public Grafico(TreeMap<String,Integer> p ){
		pagos=p;

	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        int x = 0;
        int y = 200;
        for(Map.Entry<String,Integer> entry : pagos.entrySet()) {
        	x = getCordenadas(entry.getKey());
        	int val = entry.getValue(); 
        	
        	g2d.setPaint(Color.black);
        	g2d.drawString(entry.getKey(), x, y);
        	
	        
	    	g2d.drawRect(x, y+50, 57, 57);
	    	
    		if (val==10) {
    	        g2d.setPaint(new Color(0, 59, 20) );
    		}else if (val==8) {
    	        g2d.setPaint(new Color(2, 119, 41) );
    		}else if (val==6) {
    	        g2d.setPaint(new Color(0, 174, 59 ) );
    		}else if (val==4) {
    	        g2d.setPaint(new Color(85, 255, 98 ) );
    		}else if (val==2) {
    	        g2d.setPaint(new Color(162, 255, 169 ) );
    		}else if (val==0) {
    	        g2d.setPaint(new Color(242, 255, 243 ) );
    		}
    		g2d.fillRect(x, y+50, 57,57);
        }
	}

	
	public int getCordenadas(String mes) {
		int resp = 0;
		if (mes.equals("Enero")) {
			 resp=48;
		 }else if (mes.equals("Febrero")) {
			 resp=107;
		 }else if (mes.equals("Marzo")) {
			 resp=166;
		 }else if (mes.equals("Abril")) {
			 resp=225;
		 }else if (mes.equals("Mayo")) {
			 resp=284;
		 }else if (mes.equals("Junio")) {
			 resp=284;
		 }else if (mes.equals("Julio")) {
			 resp=343;
		 }else if (mes.equals("Agosto")) {
			 resp=402;
		 }else if (mes.equals("Septiembre")) {
			 resp=461;
		 }else if (mes.equals("Octubre")) {
			 resp=520;
		 }else if (mes.equals("Noviembre")) {
			 resp=579;
		 }else if (mes.equals("Diciembre")) {
			 resp=636;
		 }
		return resp;
	}
	
}