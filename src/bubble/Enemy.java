package bubble;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel implements Movable{

	// 0, 1
	// 살아있는 상태, 물방울에 죽은 상태
	private int state;
	
	// 위치 상태
	private int x;
	private int y;
	
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
//	private int JUMPCOUNT = 0;
//	private int FIRST = 0;
	
	private boolean leftWallCrash, rightWallCrash;
	
	// 적군 속도 상태
	private final int SPEED =3 ;
	private final int JUMPSPEED = 5;
	
	// 이미지
	private ImageIcon enemyL, enemyR;

	private EnemyWay enemyWay;
	public Enemy() {
		initData();
		setInitLayout();
		left();
	}
	private void initData() {
		enemyL = new ImageIcon("images/enemyL.png");
		enemyR = new ImageIcon("images/enemyR.png");
		x = 720;
		y = 175;
		left = false;
		right = false;
		up = false;
		down = false;
		leftWallCrash= false;
		rightWallCrash = false;
		
		enemyWay = EnemyWay.LEFT;
		state = 0;
	}
	private void setInitLayout() {
		setIcon(enemyL);
		setSize(50, 50);
		setLocation(x, y);
	}
	@Override
	public void left() {
		// TODO Auto-generated method stub
		enemyWay = EnemyWay.LEFT;
		left = true;
		setIcon(enemyL);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(left) {
					x -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void right() {
		// TODO Auto-generated method stub
		enemyWay = EnemyWay.RIGHT;
		right = true;
		setIcon(enemyR);
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(right) {
					x += SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	@Override
	public void up() {
		 up = true;
		 // 람다 표현식 사용해 보기
		 new Thread(()->{
			 for(int i = 0; i<130/JUMPSPEED; i++) {
				 y -= JUMPSPEED;
				 setLocation(x,y);
				 try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 }
			 up = false;
			 down();
		 }).start();
	}
	
	@Override
	public void down() {
		down = true;
		new Thread(()->{
			while(down) {
				 y += JUMPSPEED;
				 setLocation(x,y);
				 try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				 down = false;
			 }
		}).start();
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isUp() {
		return up;
	}
	public void setUp(boolean up) {
		this.up = up;
	}
	public boolean isDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
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
	
	
}
