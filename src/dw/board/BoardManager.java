package dw.board;
import dw.graphics.GraphicsDrawing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BoardManager
{
	// File
	private String filePath;
	private boolean fileSaved;
	
	// Frame
	private int framePosX;
	private int framePosY;
	private int frameSizeX;
	private int frameSizeY;
	
	// Portals
	//private Portal[] portalArray;
	private int portalCount;
	
	// Tiles
	private int[][] tileElevation;
	private BufferedImage[ ][ ] tileImage = new BufferedImage[101][81];
	private int[ ][ ] tileType = new int[101][81];
	
	public BoardManager()
	{
		
	}

	public void render(Graphics g, int width, int height)
	{
		renderBackground(g);
		renderTiles(g);
	}
	
	public void renderBackground(Graphics g)
	{
		g.setColor(GraphicsDrawing.getColorRGB(149, 224, 65));
		g.fillRect(framePosX, framePosY, frameSizeX, frameSizeY);
	}
	
	public void renderTiles(Graphics g)
	{
		
	}

}