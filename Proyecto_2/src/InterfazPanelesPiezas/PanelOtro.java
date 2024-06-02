package InterfazPanelesPiezas;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import Exceptions.MensajedeErrorException;

public class PanelOtro extends JPanel{
	private JPanel panel;
	private JTextField extra;

	public PanelOtro() {
		panel = new JPanel(new GridLayout(1,2,20,20));
		
		JLabel m1 = new JLabel("Ingresea información extra que haya de la Pieza: ",JLabel.CENTER);
		m1.setOpaque(false);
		m1.setFont(new Font("Nirmala UI",Font.BOLD,20));
		m1.setForeground(new Color(0, 90, 26));
        panel.add(m1);
		
		extra = new JTextField("");
		extra.setSize(100, 20);
		extra.setFont(new Font("Nirmala UI",Font.PLAIN,18));
		panel.add(extra);
		
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
	public ArrayList<String> getInfo() throws MensajedeErrorException{
		if (extra.getText().equals("") ) {
			throw new MensajedeErrorException("No deje espacios en blanco");
		}
		ArrayList<String> resp = new ArrayList<String>(Arrays.asList(extra.getText()));		
		return resp;
	}

}
