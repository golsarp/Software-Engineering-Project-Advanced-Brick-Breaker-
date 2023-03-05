package ui.helpers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	//OVERVIEW: ImageLoader is the class responsible for loading an image
	//			It is designed by Pure Fabrication
	
	String FileName;

	public ImageLoader() {}

	public BufferedImage LoadImage(String fileName) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public ImageLoader(String FileName) {
		this.FileName = FileName;
	}

}
