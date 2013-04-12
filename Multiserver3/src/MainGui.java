import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui extends JFrame implements ActionListener{
	 
	public MainGui()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900 ,620);
		setVisible(true);
		
		
		JPanel contentPane = (JPanel)getContentPane();
		contentPane.setBorder(new EmptyBorder(10,10,10,10));
		
		JMenuBar menuzeile = new JMenuBar();								//Menüzeile 
		setJMenuBar(menuzeile);
		
		JMenu datei = new JMenu("kasse");
		menuzeile.add(datei);
		JMenu hilfe = new JMenu("Hilfe");
		menuzeile.add(hilfe);
		
		//Panel panelbutton = new Panel();
	
		 Image image = Toolkit.getDefaultToolkit().createImage("C:/Users/Klug/Desktop/Info/projekt Onlineshop/vorschlag theresa.png");
		 	JLabel btBild = new JLabel(new ImageIcon(image));
		 	//btBild.setBounds(new Rectangle(2, 2, 699, 434));
		 	//btBild.setText("JLabel");
		
		
		/**JButton	infostand = new JButton("Infostand");
		panelbutton.add(BorderLayout.CENTER,infostand);
		JButton kasse = new JButton("Kasse");
		panelbutton.add(BorderLayout.CENTER,kasse);
		
		infostand.setPreferredSize(new Dimension(500,200));
		kasse.setPreferredSize(new Dimension(500,200));
		**/
		getContentPane().add(btBild);
		 	
	}

	
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args)
	{
		MainGui gui= new MainGui();
	}
	
}
