package dw.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputKeyboard implements KeyListener
{
	private static String keyPressed = "None";
	private static boolean keyPressedAlpha = false;
	private static String keyReleased = "None";
	private boolean keyModifierAlt = false;
	private boolean keyModifierShift = false;
	
	public static String getKeyPressed()
	{
		return keyPressed;
	}
	
	public static boolean getKeyPressedAlpha()
	{
		return keyPressedAlpha;
	}
	
	public static String getKeyReleased()
	{
		return keyReleased;
	}
	
	public boolean getModifierPressed(String key)
	{
		if(key=="ALT"){return keyModifierAlt;}
		if(key=="SHIFT"){return keyModifierShift;}
		return false;
	}

    public void keyTyped(KeyEvent e)
    {
        //System.out.println("Key typed: " + e.getKeyChar());
    }

    public void keyPressed(KeyEvent e)
    {
        //System.out.println("Key pressed: " + e.getKeyChar());
        if(e.getKeyCode() == KeyEvent.VK_ENTER){keyPressed = "Enter";}
        if(e.getKeyCode() == KeyEvent.VK_SPACE){keyPressed = "Space";}
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){keyPressed = "Escape";}
        if(e.getKeyCode() == KeyEvent.VK_UP){keyPressed = "Up";}
        if(e.getKeyCode() == KeyEvent.VK_DOWN){keyPressed = "Down";}
        if(e.getKeyCode() == KeyEvent.VK_LEFT){keyPressed = "Left";}
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){keyPressed = "Right";}
        if(e.getKeyCode() == KeyEvent.VK_A){keyPressed = "A";}
        if(e.getKeyCode() == KeyEvent.VK_D){keyPressed = "D";}
        if(e.getKeyCode() == KeyEvent.VK_E){keyPressed = "E";}
        if(e.getKeyCode() == KeyEvent.VK_S){keyPressed = "S";}
        if(e.getKeyCode() == KeyEvent.VK_Q){keyPressed = "Q";}
        if(e.getKeyCode() == KeyEvent.VK_W){keyPressed = "W";}
        
        // Temp
        if(e.getKeyCode() == KeyEvent.VK_ALT){keyModifierAlt = true;}
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){keyModifierAlt = true;}
        
        // NOTE: Do some checks and set keyPressedAlpha to true if a letter was typed
    }

    public void keyReleased(KeyEvent e)
    {
        //System.out.println("Key released: " + e.getKeyChar());
        if(e.getKeyCode() == KeyEvent.VK_UP){keyReleased = "Up";}
        if(e.getKeyCode() == KeyEvent.VK_DOWN){keyReleased = "Down";}
        if(e.getKeyCode() == KeyEvent.VK_LEFT){keyReleased = "Left";}
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){keyReleased = "Right";}
        if(e.getKeyCode() == KeyEvent.VK_A){keyReleased = "A";}
        if(e.getKeyCode() == KeyEvent.VK_D){keyReleased = "D";}
        if(e.getKeyCode() == KeyEvent.VK_E){keyReleased = "E";}
        if(e.getKeyCode() == KeyEvent.VK_S){keyReleased = "S";}
        if(e.getKeyCode() == KeyEvent.VK_Q){keyReleased = "Q";}
        if(e.getKeyCode() == KeyEvent.VK_W){keyReleased = "W";}
        
        // Temp
        if(e.getKeyCode() == KeyEvent.VK_ALT){keyModifierAlt = false;}
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){keyModifierAlt = false;}
    }
    
	public static void keyPressedDone()
	{
		keyPressed = "none";
	}
    
	public static void keyReleasedDone()
	{
		keyReleased = "none";
	}
}