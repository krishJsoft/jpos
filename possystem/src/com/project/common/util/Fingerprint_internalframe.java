package com.project.common.util;

import java.awt.image.*;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.RenderingHints;

public class Fingerprint_internalframe extends JPanel {
	BufferedImage image;
	BufferedImage scaledimage;
	int n, m;
	public Dimension dim;

	public Fingerprint_internalframe(File imageLocation) {
		try {
			image = ImageIO.read(imageLocation);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/************************************************/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/******** scale image. ***************/
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		int width = getWidth();
		int height = getHeight();
		int iwidth = image.getWidth();
		int iheight = image.getHeight();
		double xScale = (double) width / iwidth;
		// System.out.println(" X scale  "+xScale);
		double yScale = (double) height / iheight;
		// System.out.print(" Y scale  "+yScale);
		double scale = Math.min(xScale, yScale); // scale to fit
		// System.out.println(" Total math  "+scale);
		// Math.max(xScale, yScale); // scale to fill
		int width2 = (int) (scale * iwidth);
		int height2 = (int) (scale * iheight);

		int x = (width - width2) / 2;
		int y = (height - height2) / 2;
		g2.drawImage(image, x, y, width2, height2, this);
		/******************************/
		// g.drawImage(image,0,0,this);
	}

	public Dimension getPreferredSize() {
		// setLocation();
		return new Dimension(258, 258);
	}

}
