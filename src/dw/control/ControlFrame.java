package dw.control;
import dw.graphics.GraphicsFont;
import dw.graphics.GraphicsStyle;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Graphics;

public class ControlFrame
{
	private boolean frameQuitActive;
	private String frameQuitNexus;
	private String frameRef;
	private String frameTitle;
	private int framePosX;
	private int framePosY;
	private int frameSizeX;
	private int frameSizeY;
	
	public ControlFrame(String newRef, String newTitle, int newPosX, int newPosY, int newSizeX, int newSizeY, boolean newQuit, InputMouse mouse)
	{
		frameRef = newRef;
		frameTitle = newTitle;
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
		g.setColor(GraphicsStyle.getColour("FrameFill"));
		g.fillRect(0, 0, 10, frameSizeY);
		g.fillRect(0, 0, frameSizeX, 40);
		g.fillRect(frameSizeX-10, 0, frameSizeX, frameSizeY);
		g.fillRect(0, frameSizeY-10, frameSizeX, frameSizeY);
		
		// Border
		g.setColor(GraphicsStyle.getColour("FrameBorder"));
		g.drawRect(0, 0, frameSizeX-1, frameSizeY-1);
		g.drawRect(10, 40, frameSizeX-20, frameSizeY-50);

		// Title
		g.setColor(GraphicsStyle.getColour("FrameText"));
		g.setFont(GraphicsFont.getFont("FrameTitle"));
		g.drawString(frameTitle, framePosX+28, framePosY+28);

		// Exit Button
		if(frameQuitActive==true)
		{
			g.setColor(GraphicsStyle.getColour("ButtonQuit"));
			if(mouse.nexusCheckRef()==frameQuitNexus){g.setColor(GraphicsStyle.getColour("ButtonQuitHover"));}
			g.fillRect(frameSizeX-38, framePosY+7, 26, 26);
			g.setColor(GraphicsStyle.getColour("FrameText"));
			g.drawRect(frameSizeX-38, framePosY+7, 26, 26);
			g.setFont(GraphicsFont.getFont("FrameTitle"));
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