package bubble;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Movable {
	private int x;
	private int y;

	private boolean isRight;
	private boolean isLeft;
	private boolean isUp;
	private boolean isDown;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	// 점프
	private boolean isJump;
	private ImageIcon playerL, playerR;

	private final int SPEED = 4;
	private final int JUMPSPEED = 2;

	public Player() {
		initData();
		setInitLayout();
	}

	private void initData() {
		this.playerL = new ImageIcon("images/playerL.png");
		this.playerR = new ImageIcon("images/playerR.png");

		isLeft = false;
		isRight = false;
		isUp = false;
		isDown = false;
		leftWallCrash = false;
		rightWallCrash = false;
		isJump = false;
	}

	private void setInitLayout() {
//		this.x = 55;
		this.x = 500;
		this.y = 535;
		// 좌표기반, 라벨에 크기를 지정해야 한다.
		setSize(50, 50);
		setLocation(this.x, this.y);
		// JLabel 에 아이콘을 셋팅하는 메소드
		setIcon(this.playerR);
	}

	@Override
	public void left() {
		// TODO Auto-generated method stub
		setIcon(this.playerL);
		new Thread(() -> {
			this.isLeft = true;
			while (this.isLeft) {
				this.x -= SPEED;
				setLocation(this.x, this.y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		setIcon(this.playerR);
		new Thread(() -> {
			this.isRight = true;
			while (this.isRight) {
				this.x += SPEED;
				setLocation(this.x, this.y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void up() {
		new Thread(() -> {
			this.isUp = true;
			this.isJump = true;
			for (int i = 0; i < 130 / JUMPSPEED; i++) {
				this.y -= JUMPSPEED;
				setLocation(this.x, this.y);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.isUp = false;
			down();
		}).start();
	}

	@Override
	public void down() {
		this.isDown = true;
		new Thread(() -> {
			while(this.isDown) {
				this.y += this.JUMPSPEED;
				setLocation(this.x, this.y);
				try {
					Thread.sleep(3);					
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				this.isDown = false;
				this.isJump = true;
			}
		}).start();
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public boolean isUp() {
		return isUp;
	}

	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}

	public boolean isDown() {
		return isDown;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

	public boolean isJumped() {
		return isJump;
	}
	public void setJumped(boolean isJump) {
		this.isJump = isJump;
	}
}
