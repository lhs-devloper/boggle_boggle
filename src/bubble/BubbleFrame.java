package bubble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame{
	private JLabel backgroundMap;
	private Player player;
	
	
	public BubbleFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}
	
	private void initData() {
		backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
		player = new Player();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(backgroundMap);
		setSize(1000, 640);
	}
	private void setInitLayout() {
		setLayout(null);
		add(player);
		setResizable(false);
		setLocationRelativeTo(backgroundMap);
		setVisible(true);
	}
	private void addEventListener() {
		this.addKeyListener(new KeyAdapter() {	
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				switch(e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				case KeyEvent.VK_LEFT:
					player.setLeft(false);	
					break;
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					if(!player.isRight()) {
						player.right();
					}
					break;
				case KeyEvent.VK_LEFT:
					if(!player.isLeft()) {
						player.left();				
					}
					break;
				}
			}
		});
	}
	


	public static void main(String[] args) {
		new BubbleFrame();
	} // end of main
}// end of class