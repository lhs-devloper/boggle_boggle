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

	private BubbleFrame mContext;
	private BackgroundBubbleService backgroundBubbleService;

	// 버블은 player에 의존하고있다.
	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
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
		this.x = mContext.getPlayer().getX();
		this.y = mContext.getPlayer().getY();
		setIcon(this.bubble);
		setSize(50, 50);
		setLocation(this.x, this.y);
		initThread();
	}

	private void initThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (mContext.getPlayer().getpWay() == PlayerWay.LEFT) {
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
			// 적군 위치 감지 - 버위 값 계산 → + → 절대값 구해서
			// x, y ← 적군
//			System.out.println("적군 x 위치: " + mContext.getEnemy().getX());
			
			// 물방에 x 좌표 값이 90
			// 적군에 x 좌표 값이 150
			if(Math.abs(x - mContext.getEnemy().getX())<10
					&& Math.abs(y - mContext.getEnemy().getY())<50) {
				crash();
			}
			
			
			// 적군 위치 감소
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
			if(Math.abs(x - mContext.getEnemy().getX())<10
					&& Math.abs(y - mContext.getEnemy().getY())<50) {
				// 적군이 살아있는 상태에만 crash() 호출
				if(mContext.getEnemy().getState() == 0) {					
					crash();
				}
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

	public void crash() {
//		mContext.getEnemy() 0, 1 물방울
		mContext.getEnemy().setState(1);
		setIcon(bubbled);
		mContext.remove(mContext.getEnemy());
		mContext.repaint();
	}
}
