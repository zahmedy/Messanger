package submission;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendListener implements ActionListener {

	private Message msg;
	private ChatGUI gui;
	
	public SendListener(String url,ChatGUI gui,Message msg) {
		this.msg = msg;
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(!gui.getUsername().getText().isEmpty() && !gui.getMessage().getText().isEmpty()) {
			msg.sendToAPI(gui.getUsername().getText(), gui.getMessage().getText());
			String response =msg.getFromAPI();
			msg.jsonToFormat(response);
			gui.update();
		}

	}

}
