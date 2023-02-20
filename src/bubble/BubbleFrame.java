package bubble;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame{
	private JLabel backgroundMap;
	public BubbleFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(backgroundMap);
		setSize(1000, 640);
	}
	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(backgroundMap);
		setVisible(true);
	}
	private void addEventListener() {
		
	}
	
	public static void main(String[] args) {
		new BubbleFrame();
	} // end of main
}// end of class