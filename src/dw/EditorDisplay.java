package dw;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditorDisplay
{
	private JFrame frame;
	private Canvas canvas;
	
	public EditorDisplay(String title, int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		// Create the frame
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		// Debug
		System.out.println("Width: " + width + ", Height: " + height);
		
		// Create a JPanel
		JPanel panel = new JPanel();
		panel.addKeyListener(keyboard);
		frame.add(panel);
		panel.requestFocusInWindow();
		
		// Create the canvas
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.addMouseListener (mouse);
		canvas.addMouseMotionListener (mouse);
		
		// Add the canvas to the frame
		frame.add(canvas);
		frame.pack();
	}
	
	public Canvas getCanvas()
	{
		return canvas;
	}
}