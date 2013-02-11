import javax.swing.SwingUtilities;

import javax.swing.JPanel;
import javax.swing.JFrame;

import javax.swing.JTextArea;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.DefaultListModel;
import java.awt.ComponentOrientation;

import javax.swing.JScrollPane;

public class ServerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane messageArea = null;
	private JTextArea textArea = null;
	private JTextField inputField = null;
	private JButton discButton = null;
	private JList userList = null;
	private DefaultListModel listModel = null;
	private JButton sendButton = null;
	private Server chatServer = null;
	private JTextField statusLine = null;
	/**
	 * This method initializes messageArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JScrollPane getMessageArea() {
		if (messageArea == null) {
			textArea = new JTextArea(16, 299);
			messageArea = new JScrollPane(textArea);
			messageArea.setBounds(new Rectangle(164, 16, 299, 137));
			messageArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		}
		return messageArea;
	}

	/**
	 * This method initializes inputField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getInputField() {
		if (inputField == null) {
			inputField = new JTextField();
			inputField.setBounds(new Rectangle(165, 163, 299, 19));
		}
		return inputField;
	}

	/**
	 * This method initializes discButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDiscButton() {
		if (discButton == null) {
			discButton = new JButton();
			discButton.setBounds(new Rectangle(271, 194, 103, 17));
			discButton.setText("Disconnect");
			discButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (userList.getSelectedIndex() != -1) {
						String user = userList.getSelectedValue().toString();
						chatServer.disconnect(user);
					}
				}
			});
		}
		return discButton;
	}

	/**
	 * This method initializes userList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getUserList() {
		if (userList == null) {
			userList = new JList();
			userList.setBounds(new Rectangle(30, 15, 108, 165));
			listModel = new DefaultListModel();
			userList.setModel(listModel);
		}
		return userList;
	}

	/**
	 * This method initializes sendButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSendButton() {
		if (sendButton == null) {
			sendButton = new JButton();
			sendButton.setBounds(new Rectangle(166, 193, 88, 17));
			sendButton.setFont(new Font("Dialog", Font.BOLD, 12));
			sendButton.setText("Send");
			sendButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (userList.getSelectedIndex() != -1) {
						String user = userList.getSelectedValue().toString();
						String message = inputField.getText();
						chatServer.send(user, message);
					}
				}
			});
		}
		return sendButton;
	}

	/**
	 * This method initializes statusLine	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getStatusLine() {
		if (statusLine == null) {
			statusLine = new JTextField();
			statusLine.setBounds(new Rectangle(31, 226, 436, 18));
			statusLine.setEnabled(false);
		}
		return statusLine;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ServerGUI thisClass = new ServerGUI();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}

	/**
	 * This is the default constructor
	 */
	public ServerGUI(Server obj) {
		
		super();
		chatServer = obj;
		initialize();
	}
	
	public ServerGUI() {		
		super();
		initialize();
	}	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(499, 285);
		this.setContentPane(getJContentPane());
		this.setTitle("BG12-ChatServer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getMessageArea(), null);
			jContentPane.add(getInputField(), null);
			jContentPane.add(getDiscButton(), null);
			jContentPane.add(getUserList(), null);
			jContentPane.add(getSendButton(), null);
			jContentPane.add(getStatusLine(), null);
		}
		return jContentPane;
	}

	public void showMessage(String text) {
		textArea.append("\n" + text);
    
	}

	public void showStatus(String text) {
		// TODO Auto-generated method stub
		statusLine.setText(text);	
	}

	public void showUser(String userName) {
		// TODO Auto-generated method stub
		listModel.addElement(userName);
	}

	public void deleteUser(String text) {
		// TODO Auto-generated method stub
		listModel.removeElement(text);
		
	}		

}  //  @jve:decl-index=0:visual-constraint="55,23"
