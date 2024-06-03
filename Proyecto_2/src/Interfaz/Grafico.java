package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
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
        int y = 125;
        for(Map.Entry<String,Integer> entry : pagos.entrySet()) {
        	x = getCordenadas(entry.getKey());
        	String text = entry.getKey();
        	int val = entry.getValue(); 
            
            
        	g2d.setPaint(new Color(1, 58, 0));
        	g2d.setFont(new Font("Nirmala UI",Font.BOLD,10));
        	g2d.drawString(entry.getKey(), x+5, y);
        	
        	g2d.setPaint(Color.black);
	    	g2d.drawRect(x, y+25, 55, 55);
	    	
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
    		g2d.fillRect(x, y+25, 55,55);
        }
        
        x = 500;
        y = 300;
        
        g2d.setPaint(new Color(1, 58, 0));
    	g2d.setFont(new Font("Nirmala UI",Font.BOLD,10));
    	g2d.drawString("Menos", x-50, y+15);
    	
        for(int i = 0; i<6;i++) {
        	g2d.setPaint(Color.black);
	    	g2d.drawRect(x, y, 25, 25);
	    	
    		if (i==5) {
    	        g2d.setPaint(new Color(0, 59, 20) );
    		}else if (i==4) {
    	        g2d.setPaint(new Color(2, 119, 41) );
    		}else if (i==3) {
    	        g2d.setPaint(new Color(0, 174, 59 ) );
    		}else if (i==2) {
    	        g2d.setPaint(new Color(85, 255, 98 ) );
    		}else if (i==1) {
    	        g2d.setPaint(new Color(162, 255, 169 ) );
    		}else if (i==0) {
    	        g2d.setPaint(new Color(242, 255, 243 ) );
    		}
    		g2d.fillRect(x, y, 25,25);
    		
    		x+=27;
        }
        
        g2d.setPaint(new Color(1, 58, 0));
    	g2d.setFont(new Font("Nirmala UI",Font.BOLD,10));
    	g2d.drawString("Más", x+20, y+15);
        
	}

	
	public int getCordenadas(String mes) {
		int resp = 0;
		if (mes.equals("Enero")) {
			 resp=47;
		 }else if (mes.equals("Febrero")) {
			 resp=106;
		 }else if (mes.equals("Marzo")) {
			 resp=165;
		 }else if (mes.equals("Abril")) {
			 resp=224;
		 }else if (mes.equals("Mayo")) {
			 resp=283;
		 }else if (mes.equals("Junio")) {
			 resp=342;
		 }else if (mes.equals("Julio")) {
			 resp=401;
		 }else if (mes.equals("Agosto")) {
			 resp=460;
		 }else if (mes.equals("Septiembre")) {
			 resp=519;
		 }else if (mes.equals("Octubre")) {
			 resp=578;
		 }else if (mes.equals("Noviembre")) {
			 resp=635;
		 }else if (mes.equals("Diciembre")) {
			 resp=692;
		 }
		return resp;
	}
	
}