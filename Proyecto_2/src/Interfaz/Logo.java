package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Logo extends JPanel{
	private String nombre;
	private JLabel titulo;
	
	public Logo(String n) {
		nombre=n;
		titulo=new JLabel(nombre,JLabel.CENTER);
		titulo.setFont(new Font("Book Antiqua", Font.BOLD, 35));
		titulo.setForeground(Color.white);
		add(titulo);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
      
        Color color1 = new Color(0, 90, 26 );
        Color color2 = new Color(0, 144, 41);
        int w = getWidth();
        int h = getHeight(); 
        GradientPaint gp = new GradientPaint(
                0,0, color1,
                0, h, color2);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        
	}
	
}
