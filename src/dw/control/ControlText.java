package dw.control;
import dw.graphics.GraphicsFont;
import dw.graphics.GraphicsStyle;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Graphics;

public class ControlText
{
	private String textRef;
	private String textValue;
	private int textLength;
	private boolean textFocus;
	private int textFocusFrame;
	private int textFocusTick;
	private int textPosX;
	private int textPosY;
	private int textSizeX;
	private int textSizeY;
	private String textNexus;

	public ControlText(String newRef, int newLength, int newPosX, int newPosY, int newSizeX, int newSizeY, InputMouse mouse)
	{
		textRef = newRef;
		textValue = "";
		textLength = newLength;
		textFocus = false;
		textPosX = newPosX;
		textPosY = newPosY;
		textSizeX = newSizeX;
		textSizeY = newSizeY;
		textNexus = newRef;
		mouse.nexusAdd(textNexus, textPosX, textPosY, textSizeX, textSizeY);
	}
	
	public void focus()
	{
		textFocus = true;
		textFocusFrame = 1;
		textFocusTick = 0;
	}
	
	public boolean hasFocus()
	{
		return textFocus;
	}

	public void render(Graphics g, InputMouse mouse)
	{
		// Background
		g.setColor(GraphicsStyle.getColour("InputFill"));
		g.fillRect(textPosX, textPosY, textSizeX, textSizeY);
		
		// Border
		g.setColor(GraphicsStyle.getColour("InputBorder"));
		g.drawRect(textPosX, textPosY, textSizeX, textSizeY);
		
		// Value
		g.setFont(GraphicsFont.getFont("InputValue"));
		g.setColor(GraphicsStyle.getColour("InputText"));
		String textDraw = textValue;
		if(textFocus==true && textFocusFrame==1){textDraw += "|";}
		g.drawString(textDraw, textPosX+15, textPosY+15);
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		if(textFocus==true)
		{
			textFocusTick += 1;
			if(textFocusTick>=10)
			{
				textFocusFrame += 1;
				textFocusTick = 0;
				if(textFocusFrame>2){textFocusFrame = 1;}
			}
		}
	}
	
	public void valueAppend(String newText)
	{
		if(textValue.length()<textLength){textValue += newText;}
	}

}