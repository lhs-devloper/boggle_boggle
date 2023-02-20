package bubble;
// 메인 화면ㅇ ㅔ그림작가 주고 있고(키보드  이벤트 리스너 받ㅇ고있음 -- 바쁨

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgourndgroudnPlayerservice implements Runnable {

	private BufferedImage image;
	private Player player;

	public BackgourndgroudnPlayerservice(Player player) {
		this.player = player;
		try {
			this.image = ImageIO.read(new File("images/backgroundMapService.png"));
		} catch (IOException e) {
			System.out.println("백그라운드 플레이어 서비스 객체에서 사용하는 이미지 경로 및 파일 명 확인");
		}

	}

	@Override
	public void run() {
		// 색상 확인
		// 왼쪽으로 갈 때는 좌표 지점을 보정해야하고
		// 오른쪽으로 갈 때는 기준 좌표 지점을 보정해야한다.
		// 기준 왼쪽
		while (true) {
			Color leftColor = new Color(image.getRGB(player.getX(), player.getY()));

			// 기준 오른쪽
			Color rightColor = new Color(image.getRGB(player.getX() + 60, player.getY()));

			if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				player.setRight(false);				
				// 벽에 충돌 했다 ←
				player.setLeftWallCrash(true);
			} else if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				player.setLeft(false);
				// 벽에 충돌 했다 →
				player.setRightWallCrash(true);
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 왼쪽 벽에 충돌 함

		}

	}
}
