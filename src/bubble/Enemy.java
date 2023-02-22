package bubble;

import java.nio.channels.InterruptedByTimeoutException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Enemy extends JLabel implements Movable{

	// 위치 상태
	private int x;
	private int y;
	
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	// 적군 속도 상태
	private final int SPEED =3 ;
	private final int JUMPSPEED = 5;
	
	// 이미지
	private ImageIcon enemyL, enemyR;

	private EnemyWay enemyWay;
	public Enemy() {
		initData();
		setInitLayout();
		
		up();
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
		
		enemyWay = EnemyWay.LEFT;
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
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
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
				while(true) {
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
			while(true) {
				 y += JUMPSPEED;
				 setLocation(x,y);
				 try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			 }
		}).start();
		down = false;
	}
	
}
