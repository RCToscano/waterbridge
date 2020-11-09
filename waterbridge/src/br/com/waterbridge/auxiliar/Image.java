package br.com.waterbridge.auxiliar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	public static void main(String[] args) throws IOException {
		
		BufferedImage image1 = ImageIO.read(new File("C:\\Temp\\teste.png")); //read existing file

		//modify it
		Graphics g = image1.createGraphics();
		g.setColor(Color.RED);
		g.drawString("Pressao", 20, 20);
		g.dispose();
		
		//now create a new image
		BufferedImage image2 = new BufferedImage(256, 256, BufferedImage.TYPE_4BYTE_ABGR);
		
		g = image2.createGraphics();
		g.drawImage(image1, 64, 64, null);
		
		ImageIO.write(image2, "png", new File("C:\\Temp\\teste_novo.png"));
	}
}
