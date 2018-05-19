package submission;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class ChatGUI {

	
	private String url;
	private JTextField username;
	private JTextField message;
	private Message msg;
	private JTextArea textArea;
	private JButton button1;
	private JButton button;
	private JLabel label1;
	private JLabel label;
	private JPanel chatPanel;
	private JPanel controlPanel;
	
	public ChatGUI(String url) {
		this.url = url;
		this.msg = new Message(url);
		this.textArea = new JTextArea(20,30);
		this.username = new JTextField(8);
		this.message = new JTextField(22);
		this.button = new JButton("Send");
		this.button1 = new JButton("Refresh");
		this.label1 = new JLabel("Username:");
		this.label = new JLabel("Message:");
		this.chatPanel = new JPanel();
		this.controlPanel = new JPanel();
	}
	
	public JPanel getChatPanel() {
		
		this.textArea.setText(msg.jsonToFormat(msg.getFromAPI()));
		chatPanel.add(textArea);
		
		return chatPanel;
	}
	
	public JPanel getControlPanel(){
				
		this.button.addActionListener(new SendListener(this.url,this,this.msg));
		
		this.button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				update();				
			}
			
		});

		controlPanel.add(this.label1);
		controlPanel.add(this.username);
		controlPanel.add(this.label);
		controlPanel.add(this.message);
		controlPanel.add(this.button);
		controlPanel.add(this.button1);
		
		return controlPanel;
	}

	public void update() {
		this.textArea.setText(this.msg.jsonToFormat((this.msg.getFromAPI())));
	}
	
	public JTextField getUsername() {
		return username;
	}

	public void setUsername(JTextField username) {
		this.username = username;
	}

	public JTextField getMessage() {
		return message;
	}

	public void setMessage(JTextField message) {
		this.message = message;
	}
}
