package bubble;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundBubbleService implements Runnable{
	
	private BufferedImage image;
	private Bubble bubble;

	public BackgroundBubbleService(Bubble bubble) {
		this.bubble = bubble;
		try {
			this.image = ImageIO.read(new File("images/backgroundMapService.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("파일 경로를 확인해주세요");
		}
	}
	public boolean leftWall() {
		// R G B 값을 int 값으로 확인할 수 있다.
		Color leftColor = new Color(image.getRGB(bubble.getX(), bubble.getY()));
		if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
			return true;
		}
		return false;
	}
	public boolean rightWall() {
		Color rightColor = new Color(image.getRGB(bubble.getX()+60, bubble.getY()));
		if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
			return true;
		}
		return false;
	}
	public boolean topWall() {		
		Color topColor = new Color(image.getRGB(bubble.getX()+5, bubble.getY()));
		if(topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
