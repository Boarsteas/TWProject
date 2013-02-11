
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JButton;

public class ChatNameWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel nickNameText = null;
	private JTextField nickName = null;
	private JButton jButton = null;
	/**
	 * This is the default constructor
	 */
	public ChatNameWindow() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("ChatClient");
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			nickNameText = new JLabel();
			nickNameText.setText("Bitte Nickname für den Chat angeben");
			nickNameText.setBounds(new Rectangle(0, 0, 284, 16));
			nickNameText.setHorizontalAlignment(SwingConstants.CENTER);
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(nickNameText, null);
			jContentPane.add(getNickName(), null);
			jContentPane.add(getJButton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes nickName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNickName() {
		if (nickName == null) {
			nickName = new JTextField();
			nickName.setBounds(new Rectangle(47, 33, 186, 23));
		}
		return nickName;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(75, 89, 138, 33));
			jButton.setText("OK");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					Chatter.startChat(getNickName().getText()); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButton;
	}

}  //  @jve:decl-index=0:visual-constraint="178,45"
