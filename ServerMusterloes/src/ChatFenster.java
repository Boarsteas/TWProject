import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ChatFenster implements ActionListener, WindowListener{
    private JTextArea ausgabeFeld = new JTextArea();
    private JTextField eingabeFeld = new JTextField(20);
    private Object chatter;
    private JFrame frame = new javax.swing.JFrame();
     
    public ChatFenster(Object chatter){
        this.chatter = chatter;
        Panel eingabePanel = new Panel();
        JButton connectKnopf = new JButton("Connect");
        JButton sendenKnopf = new JButton("Senden");
        JButton empfangenKnopf = new JButton("Empfangen");
        JButton disconnectKnopf = new JButton("Disconnect");
        sendenKnopf.addActionListener(this);
        empfangenKnopf.addActionListener(this);
        connectKnopf.addActionListener(this);
        disconnectKnopf.addActionListener(this);

        eingabePanel.add(eingabeFeld,BorderLayout.WEST);
        eingabePanel.add(connectKnopf,BorderLayout.EAST);
        eingabePanel.add(sendenKnopf,BorderLayout.EAST);
        eingabePanel.add(empfangenKnopf,BorderLayout.EAST);
        eingabePanel.add(disconnectKnopf,BorderLayout.EAST);
        
        JScrollPane ausgabePanel = new JScrollPane(ausgabeFeld);
        frame.addWindowListener(this);
        //frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        try{
            java.lang.reflect.Method nameGeben = chatter.getClass().getDeclaredMethod("spitznameGeben", new Class[] {});
            frame.setTitle((String)nameGeben.invoke(chatter, new Object[]{}));
        } catch (Exception exc){
            System.out.println(exc);
        }
        frame.getContentPane().add(ausgabePanel,BorderLayout.CENTER);
        frame.getContentPane().add(eingabePanel,BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(650,200));
        ausgabeFeld.setFont(new Font("Arial", Font.PLAIN, 18));
        ausgabeFeld.setEditable(false);
        frame.pack();
        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent aktion){
        if (aktion.getActionCommand().equals("Connect")) {
            //System.out.println("Connect wurde gedrückt!");
            try{
                java.lang.reflect.Method connectToServer = chatter.getClass().getDeclaredMethod("connect", new Class[] {});
                connectToServer.invoke(chatter);
            } catch (Exception exc){
                System.out.println(exc);
            }            
        }        
        if (aktion.getActionCommand().equals("Senden")) {
            //System.out.println("Senden wurde gedrückt!");
            String eingabe = eingabeFeld.getText();
            eingabeFeld.setText("");
            String befehl = eingabe.trim();
    
            try{
                java.lang.reflect.Method eingabeVerarbeiten = chatter.getClass().getDeclaredMethod("senden", new Class[] {java.lang.String.class});
                eingabeVerarbeiten.invoke(chatter, new Object[]{befehl});
            } catch (Exception exc){
                System.out.println(exc);
            }            
        }
        if (aktion.getActionCommand().equals("Empfangen")) {
            //System.out.println("Empfangen wurde gedrückt!");
            try{
                java.lang.reflect.Method empfangVerarbeiten = chatter.getClass().getDeclaredMethod("empfangen", new Class[] {});
                empfangVerarbeiten.invoke(chatter);
            } catch (Exception exc){
                System.out.println(exc);
            }            
        }
        if (aktion.getActionCommand().equals("Disconnect")) {
            //System.out.println("Disconnect wurde gedrückt!");
            try{
                java.lang.reflect.Method disconnectFromServer = chatter.getClass().getDeclaredMethod("disconnect", new Class[] {});
                disconnectFromServer.invoke(chatter);
            } catch (Exception exc){
                System.out.println(exc);
            }            
        }        
    }
    
    public void anzeigen(String text){
        frame.setVisible(true);
        ausgabeFeld.append(text+"\n");           
    }
 
    public void windowDeactivated(WindowEvent event){
    }
    public void windowActivated(WindowEvent event){
    }
    public void windowDeiconified(WindowEvent event){
    }
    public void windowIconified(WindowEvent event){
    }
    public void windowClosed(WindowEvent event){

    }
    public void windowClosing(WindowEvent event){
    	System.out.println("Window Closing Event");
        try{
            java.lang.reflect.Method shutdownClient = chatter.getClass().getDeclaredMethod("shutdown", new Class[] {});
            shutdownClient.invoke(chatter);
        } catch (Exception exc){
            System.out.println(exc);
        }
    }
    public void windowOpened(WindowEvent event){
    }
    
    
}
