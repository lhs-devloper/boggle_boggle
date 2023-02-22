package bubble;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bubble extends JLabel implements Movable {
	// 버블에 위치 상태
	private int x, y;

	// 버블에 움직임 방향 상태
	private boolean left;
	private boolean right;
	private boolean up;

	// 적군을 맞춘 상태 : 0 기본, 1, (적을 가둔 물방을)
	private int state;

	private ImageIcon bubble; // 기본 물방울 이미지
	private ImageIcon bubbled; // 적을 가둔 상태 이미지
	private ImageIcon bomb; // 물방울이 터진 상태 이미지

	private Player player;
	private BackgroundBubbleService backgroundBubbleService;

	// 버블은 player에 의존하고있다.
	public Bubble(Player player) {
		this.player = player;
		initData();
		backgroundBubbleService = new BackgroundBubbleService(this);
		setInitLayout();
	}

	private void initData() {
		this.bubble = new ImageIcon("images/bubble.png");
		this.bubbled = new ImageIcon("images/bubbled.png");
		this.bomb = new ImageIcon("images/bomb.png");

		this.left = false;
		this.right = false;
		this.up = false;
		this.state = 0;
	}

	private void setInitLayout() {
		// 플레이어가 있는 위치에 태어나야 한다.
		this.x = player.getX();
		this.y = player.getY();
		setIcon(this.bubble);
		setSize(50, 50);
		setLocation(this.x, this.y);
		initThread();
	}

	private void initThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (player.getpWay() == PlayerWay.LEFT) {
					// 왼쪽 발사
					left();
				} else {
					// 오른쪽 발사
					right();
				}
			}
		}).start();
	}

	@Override
	public void left() {
		// 익명 클래스로 THread
		left = true;
		for (int i = 0; i < 400; i++) {
			this.x--;
			setLocation(this.x, this.y);
			if(backgroundBubbleService.leftWall()) {
				break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 400; i++) {
			this.x++;
			setLocation(this.x, this.y);
			if(backgroundBubbleService.rightWall()) {
				break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while(true) {
			this.y--;
			setLocation(this.x, this.y);
			if(backgroundBubbleService.topWall()) {
				break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		clearBubble();
	}
	// 메소드 행위(동사) → (목적어)
	private void clearBubble() {
		try {
			Thread.sleep(2000);
			setIcon(bomb);
			// 터진다음에 0.5초 다음에 그림을 지우기
			Thread.sleep(500);
			setIcon(null);
			// repaint(); // 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
