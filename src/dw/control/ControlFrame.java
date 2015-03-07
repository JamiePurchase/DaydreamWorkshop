package dw.control;
import dw.graphics.GraphicsDrawing;
import dw.graphics.GraphicsFont;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Color;
import java.awt.Graphics;

public class ControlFrame
{
	private Color frameBkg;
	private boolean frameQuitActive;
	private String frameQuitNexus;
	private String frameRef;
	private String frameTitle;
	private int framePosX;
	private int framePosY;
	private int frameSizeX;
	private int frameSizeY;
	
	public ControlFrame(String newRef, String newTitle, int newPosX, int newPosY, int newSizeX, int newSizeY, Color newBkg, boolean newQuit, InputMouse mouse)
	{
		frameRef = newRef;
		frameTitle = newTitle;
		frameBkg = newBkg;
		framePosX = newPosX;
		framePosY = newPosY;
		frameSizeX = newSizeX;
		frameSizeY = newSizeY;
		frameQuitActive = newQuit;
		if(frameQuitActive==true)
		{
			frameQuitNexus = frameRef + "-quit";
			mouse.nexusAdd(frameQuitNexus, frameSizeX-38, framePosY+7, 26, 26);
		}
	}
	
	public void render(Graphics g, InputMouse mouse)
	{
		// Padding
		g.setColor(frameBkg);
		g.fillRect(0, 0, 10, frameSizeY);
		g.fillRect(0, 0, frameSizeX, 40);
		g.fillRect(frameSizeX-10, 0, frameSizeX, frameSizeY);
		g.fillRect(0, frameSizeY-10, frameSizeX, frameSizeY);
		
		// Border
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, frameSizeX-1, frameSizeY-1);
		g.drawRect(10, 40, frameSizeX-20, frameSizeY-50);

		// Title
		g.setColor(Color.BLACK);
		g.setFont(GraphicsFont.getFont("ModuleFrameTitle"));
		g.drawString(frameTitle, framePosX+28, framePosY+28);

		// Exit Button
		if(frameQuitActive==true)
		{
			g.setColor(GraphicsDrawing.getColorRGB(150,25,25));
			if(mouse.nexusCheckRef()==frameQuitNexus){g.setColor(GraphicsDrawing.getColorRGB(200,25,25));}
			g.fillRect(frameSizeX-38, framePosY+7, 26, 26);
			g.setColor(Color.BLACK);
			g.drawRect(frameSizeX-38, framePosY+7, 26, 26);
			g.setFont(GraphicsFont.getFont("ModuleFrameTitle"));
			g.drawString("x", frameSizeX-30, 27);
		}
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		if(mouse.mouseActionPressedL==true){tickNexus(mouse);}
	}
	
	public void tickNexus(InputMouse mouse)
	{
		if(mouse.mouseNexusClick==frameQuitNexus && frameQuitActive==true)
		{
			// NOTE: Prompt the user to save if there are unsaved changes
			System.exit(0);
		}
	}

}