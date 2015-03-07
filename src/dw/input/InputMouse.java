package dw.input;
import dw.input.InputMouseNexus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class InputMouse extends MouseAdapter implements MouseMotionListener
{
	// Location
	public int mouseCoordsX;
	public int mouseCoordsY;
	public boolean mouseOnScreen;
	
	// Actions
	public boolean mouseActionPressedL = false;
	public boolean mouseActionPressedR = false;
	public boolean mouseActionPressedW = false;
	
	// Nexus
	public InputMouseNexus[] mouseNexus = new InputMouseNexus[50];
	public int mouseNexusCount = 0;
	
	public void mouseActionDone()
	{
		mouseActionPressedL = false;
		mouseActionPressedR = false;
		mouseActionPressedW = false;
	}
    
    public void mouseDragged(MouseEvent e)
    {
		mouseCoordsX = e.getX();
		mouseCoordsY = e.getY();
    }

	public void mouseEntered (MouseEvent e)
	{
		mouseOnScreen = true;
	}
	
	public void mouseExited (MouseEvent e)
	{
		mouseOnScreen = false;
	}
    
    public void mouseMoved(MouseEvent e)
    {
		mouseCoordsX = e.getX();
		mouseCoordsY = e.getY();
    }

	@Override
	public void mousePressed (MouseEvent e)
	{
		mouseCoordsX = e.getX();
		mouseCoordsY = e.getY();
		if(e.getButton() == MouseEvent.BUTTON1){mouseActionPressedL = true;}
		if(e.getButton() == MouseEvent.BUTTON2){mouseActionPressedW = true;}
		if(e.getButton() == MouseEvent.BUTTON3){mouseActionPressedR = true;}
	}

	@Override
	public void mouseReleased (MouseEvent e)
	{
		
	}
	
	public void nexusAdd(String ref, int posX, int posY, int width, int height)
	{
		mouseNexusCount+=1;
		mouseNexus[mouseNexusCount] = new InputMouseNexus(ref, posX, posY, width, height);
	}
	
	public int nexusCheck()
	{
		return nexusCheck(mouseCoordsX,mouseCoordsY);
	}
	
	public int nexusCheck(int posX, int posY)
	{
		for(int x=1;x<=mouseNexusCount;x+=1)
		{
			if(posX>=mouseNexus[x].posX1 && posX<=mouseNexus[x].posX2 && posY>=mouseNexus[x].posY1 && posY<=mouseNexus[x].posY2)
			{
				return x;
			}
		}
		return 0;
	}
	
	public String nexusCheckRef()
	{
		return nexusCheckRef(mouseCoordsX,mouseCoordsY);
	}
	
	public String nexusCheckRef(int posX, int posY)
	{
		for(int x=1;x<=mouseNexusCount;x+=1)
		{
			if(posX>=mouseNexus[x].posX1 && posX<=mouseNexus[x].posX2 && posY>=mouseNexus[x].posY1 && posY<=mouseNexus[x].posY2)
			{
				return mouseNexus[x].reference;
			}
		}
		return "";
	}
	
	public void nexusClear()
	{
		mouseNexus = new InputMouseNexus[50];
		mouseNexusCount = 0;
	}
}