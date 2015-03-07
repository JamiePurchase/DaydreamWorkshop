package dw.module;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

public class ModuleManager
{
	// Application Data
	private int appWidth;
	private int appHeight;
	private InputKeyboard appKeyboard;
	private InputMouse appMouse;
	
	// Modules
	private int moduleActive;
	private Module[] moduleArray = new Module[10];
	private int moduleCount;
	
	public ModuleManager(int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		appWidth = width;
		appHeight = height;
		appKeyboard = keyboard;
		appMouse = mouse;
		moduleArray[0] = new ModuleApp(width, height, keyboard, mouse);
		moduleActive = 0;
		moduleCount = 1;
	}
	
	public int addModule(Module newModule, boolean newFocus)
	{
		int newPos = moduleCount;
		moduleArray[newPos] = newModule;
		if(newFocus==true){moduleActive = newPos;}
		moduleCount += 1;
		return newPos;
	}
	
	public Module getActive()
	{
		return moduleArray[moduleActive];
	}
	
	public void requestModule(String module)
	{
		// Debug
		System.out.println("Requested the "+module+" module");
		
		if(module=="ProjectNew"){addModule(new ModuleProjectNew(appWidth, appHeight, appKeyboard, appMouse, 0, 0, appWidth, appHeight), true);}
	}
	
	public void setActive(int active)
	{
		moduleActive = active;
	}
	
}