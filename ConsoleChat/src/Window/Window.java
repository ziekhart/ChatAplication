package Window;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import Utility.Logger;

public abstract class Window {
	
	protected JFrame frame;
	
	protected static final int TEXT_FIELD_LENGTH = 20;
	
	public Window() {
		frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}

	public JFrame getFrame(){
		return frame;
	}
	
	protected void center(){
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((screen.getWidth() - frame.getWidth()) /2);
		int y = (int) ((screen.getHeight() - frame.getHeight()) /2);
		frame.setLocation(x, y);
	}
	
	protected void finalize(){
		Logger.logInfo("Created new window " + frame.getTitle());
		center();
		frame.setVisible(true);
	}
}