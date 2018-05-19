package submission;
import java.awt.BorderLayout;

import javax.swing.*;

public class Main {

	public static void startGUI() {
		
		JFrame frame = new JFrame("Chat Lab");
		ChatGUI instance = new ChatGUI("https://fury.cse.buffalo.edu/chat/");
		frame.getContentPane().add(instance.getChatPanel(), BorderLayout.CENTER);
		frame.getContentPane().add(instance.getControlPanel(), BorderLayout.SOUTH);

		frame.setSize(20, 40);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				startGUI();
			}
		});
	}
}
