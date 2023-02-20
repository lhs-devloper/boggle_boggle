package bubble;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Movable {
	private int x;
	private int y;
	
	private boolean isRight;
	private boolean isLeft;

	private ImageIcon playerL, playerR;

	public Player() {
		initData();
		setInitLayout();
	}

	private void initData() {
		this.playerL = new ImageIcon("images/playerL.png");
		this.playerR = new ImageIcon("images/playerR.png");
	}

	private void setInitLayout() {
		this.x = 55;
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
		new Thread(()->{
			this.isLeft = true;
			while(this.isLeft) {
				this.x -= 1;
				setLocation(this.x, this.y);
				try {
					Thread.sleep(10);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		setIcon(this.playerR);
		new Thread(()->{
			this.isRight = true;
			while(this.isRight) {
				this.x += 1;
				setLocation(this.x, this.y);	
				try {
					Thread.sleep(10);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	@Override
	public void up() {
		// TODO Auto-generated method stub

	}

	@Override
	public void down() {
		// TODO Auto-generated method stub

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
}
