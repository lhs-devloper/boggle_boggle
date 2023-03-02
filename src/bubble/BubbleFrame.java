package bubble;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


// ** main 가지는 클래스는 프로그램에 사용하는 참조변수를 다 알 수 있다.
public class BubbleFrame extends JFrame {
	private BubbleFrame mContext = this;
	
	private JLabel backgroundMap;
	private Player player;
	private Enemy enemy;
	
	private BGM bgm;

	public BubbleFrame() {
		initData();
		setInitLayout();
		addEventListener();

		// player new 되어있는상태
		// 약속 run 메소드안에 동작을 처리한다.
		new Thread(new BackgourndPlayerService(player)).start();
		new Thread(new BackgourndEnemyService(enemy)).start();
	}

	private void initData() {
		this.backgroundMap = new JLabel(new ImageIcon("images/backgroundMap.png"));
		
		this.bgm = new BGM("music/boggle.wav");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(backgroundMap);
		setSize(1000, 640);
		// 1. 부모에 주소값을 자식 객체한테 던져 주면 된다.
		// 2. 콜백메소드 활용서 구현할 수 있다.
		this.player = new Player(mContext);
		this.enemy = new Enemy();
	}

	private void setInitLayout() {
		setLayout(null);
		add(player);
		add(enemy);
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
//						System.out.println(player.getY());
					}
					break;
					
				case KeyEvent.VK_SPACE:
					player.attack();
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

	public Player getPlayer() {
		return player;
	}
	
	public Enemy getEnemy() {
		return enemy;
	}
	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	// ***** 자바 프로그램 중 main 함수를 가지는 클래스는
	// 프로그램에서 사용하는 모든 참조값을 알 수 잇다.
	public static void main(String[] args) {
		new BubbleFrame();
	} // end of main
}// end of class