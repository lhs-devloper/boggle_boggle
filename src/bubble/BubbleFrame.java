package bubble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {
	private JLabel backgroundMap;
	private Player player;

	private BGM bgm;

	public BubbleFrame() {
		initData();
		setInitLayout();
		addEventListener();

		// player new 되어있는상태
		// 약속 run 메소드안에 동작을 처리한다.
		new Thread(new BackgourndgroudnPlayerService(player)).start();
	}

	private void initData() {
		this.backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
		this.player = new Player();
		this.bgm = new BGM("music/boggle.wav");
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
				switch (e.getKeyCode()) {
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
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					// 여러번 누르더라도 한번만 호출 되게 방어적 코드 작성
					// false
					// true ← 변경!!
					// 한번 더 ← 현재 상태 값 true
					if (!player.isRight() && !player.isLeftWallCrash()) {
						player.right();
					}
					break;
				case KeyEvent.VK_LEFT:
					if (!player.isLeft() && !player.isRightWallCrash()) {
						player.left();
					}
					break;
				case KeyEvent.VK_UP:
					if (!player.isUp() && !player.isJumped()) {
						player.up();
						System.out.println(player.getY());
					}
					break;
//				case KeyEvent.VK_DOWN:
//					if (!player.isDown()) {
//						player.down();
//					}
//					break;
				}
			}
		});
	}

	public static void main(String[] args) {
		new BubbleFrame();
	} // end of main
}// end of class