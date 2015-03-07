package dw.control;
import dw.graphics.GraphicsDrawing;
import dw.graphics.GraphicsStyle;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Graphics;

public class ControlDialog
{
	// Dialog
	private String dialogTitle;
	private int dialogPosX;
	private int dialogPosY;
	private int dialogSizeX;
	private int dialogSizeY;
	
	// Form
	private ControlButton[] formButton = new ControlButton[10];
	private int formButtonCount = 0;
	private ControlText[] formText = new ControlText[10];
	private int formTextCount = 0;
	
	public ControlDialog(String newTitle, int newPosX, int newPosY, int newSizeX, int newSizeY)
	{
		dialogTitle = newTitle;
		dialogPosX = newPosX;
		dialogPosY = newPosY;
		dialogSizeX = newSizeX;
		dialogSizeY = newSizeY;
	}
	
	public int formAddButton(ControlButton newButton)
	{
		int buttonID = formButtonCount;
		formButton[formButtonCount] = newButton;
		formButtonCount += 1;
		return buttonID;
	}
	
	public int formAddText(ControlText newText, boolean focus)
	{
		int textID = formTextCount;
		formText[formTextCount] = newText;
		if(focus){formText[formTextCount].focus();}
		formTextCount += 1;
		return textID;
	}

	public void render(Graphics g, InputMouse mouse)
	{
		// Background
		g.setColor(GraphicsStyle.getColour("FrameBackground"));
		g.setColor(GraphicsDrawing.getColorRGB(0,0,150));
		g.fillRect(dialogPosX, dialogPosY, dialogSizeX, dialogSizeY);
		
		// Form Button
		for(int button=0;button<formButtonCount;button+=1)
		{
			formButton[button].render(g, mouse);
		}
		
		// Form Text
		for(int text=0;text<formTextCount;text+=1)
		{
			formText[text].render(g, mouse);
		}
	}
	
	public void tick(InputKeyboard keyboard, InputMouse mouse)
	{
		tickForm(keyboard, mouse);
	}
	
	public void tickForm(InputKeyboard keyboard, InputMouse mouse)
	{
		// Form Button
		/*for(int button=0;button<formButtonCount;button+=1)
		{
			formButton[button].render(g, mouse);
		}*/
		
		// Form Text
		for(int text=0;text<formTextCount;text+=1)
		{
			if(formText[text].hasFocus() && keyboard.getKeyPressedAlpha()){formText[text].valueAppend(keyboard.getKeyPressed());}
		}
	}

}