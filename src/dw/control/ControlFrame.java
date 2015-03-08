package dw.control;
import dw.Editor;
import dw.graphics.GraphicsFont;
import dw.graphics.GraphicsStyle;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

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
	private boolean frameMoveActive;
	private String frameMoveHandle;
	private boolean frameMoveNow;
	private int frameMoveStartX;
	private int frameMoveStartY;
	
	public ControlFrame(String newRef, String newTitle, int newPosX, int newPosY, int newSizeX, int newSizeY, boolean newMove, boolean newQuit, InputMouse mouse)
	{
		frameRef = newRef;
		frameTitle = newTitle;
		framePosX = newPosX;
		framePosY = newPosY;
		frameSizeX = newSizeX;
		frameSizeY = newSizeY;
		frameMoveActive = newMove;
		frameMoveNow = false;
		frameQuitActive = newQuit;
		if(frameMoveActive==true){initFrameHandle(mouse);}
		if(frameQuitActive==true){initButtonQuit(mouse);}
	}
	
	public void initFrameHandle(InputMouse mouse)
	{
		frameMoveHandle = frameRef + "-handle";
		mouse.nexusAdd(frameMoveHandle, 0, 0, frameSizeX, 40);
	}
	
	public void initButtonQuit(InputMouse mouse)
	{
		frameQuitNexus = frameRef + "-quit";
		mouse.nexusAdd(frameQuitNexus, frameSizeX-38, framePosY+7, 26, 26);
	}
	
	public void render(Graphics g, InputMouse mouse)
	{
		// Padding
		/*g.setColor(GraphicsStyle.getColour("FrameFill"));
		g.fillRect(0, 0, 10, frameSizeY);
		g.fillRect(0, 0, frameSizeX, 40);
		g.fillRect(frameSizeX-10, 0, frameSizeX, frameSizeY);
		g.fillRect(0, frameSizeY-10, frameSizeX, frameSizeY);
		
		// Border
		g.setColor(GraphicsStyle.getColour("FrameBorder"));
		g.drawRect(0, 0, frameSizeX-1, frameSizeY-1);
		g.drawRect(10, 40, frameSizeX-20, frameSizeY-50);*/
		
		// Padding
		g.setColor(GraphicsStyle.getColour("FrameFill"));
		g.fillRect(framePosX, framePosY, 10, frameSizeY);
		g.fillRect(framePosX, framePosY, frameSizeX, 40);
		g.fillRect(frameSizeX-10, framePosY, frameSizeX, frameSizeY);
		g.fillRect(framePosX, frameSizeY-10, frameSizeX, frameSizeY);
		
		// Border
		g.setColor(GraphicsStyle.getColour("FrameBorder"));
		g.drawRect(framePosX, framePosY, frameSizeX-1, frameSizeY-1);
		g.drawRect(framePosX+10, framePosY+40, frameSizeX-20, frameSizeY-50);
		
		// Temp (drag)
		if(mouse.mouseActionPressedL==true && mouse.nexusCheckRef()==frameMoveHandle)
		{
			System.out.println("DRAGGING");
			g.setColor(GraphicsStyle.getColour("FrameBorder"));
			g.drawRect(1, 1, frameSizeX-2, frameSizeY-2);
			g.drawRect(2, 2, frameSizeX-3, frameSizeY-3);
		}

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
		if(mouse.mouseActionPressedL==true && mouse.nexusCheckRef()==frameMoveHandle){tickHandle(mouse);}
		//if(frameMoveNow==true){tickHandle(mouse);}
		else if(mouse.mouseActionPressedL==true){tickNexus(mouse);}
		
		//if(mouse.mouseActionPressedL==true){tickNexus(mouse);}
	}
	
	public void tickHandle(InputMouse mouse)
	{
		if(mouse.mouseActionPressedL==false)
		{
			frameMoveNow = false;
		}
		else
		{
			int offsetX = frameMoveStartX - mouse.mouseDragEndX;
			int offsetY = frameMoveStartY - mouse.mouseDragEndY;
			framePosX -= offsetX;
			framePosY -= offsetY;
		}
	}
	
	public void tickNexus(InputMouse mouse)
	{
		if(mouse.mouseNexusClick==frameMoveHandle)
		{
			frameMoveNow = true;
			frameMoveStartX = mouse.mouseCoordsX;
			frameMoveStartY = mouse.mouseCoordsY;
		}
		if(mouse.mouseNexusClick==frameQuitNexus && frameQuitActive==true)
		{
			mouse.mouseActionDone();
			// NOTE: Prompt the user to save if there are unsaved changes
			Editor.editorTerminate();
		}
	}

}