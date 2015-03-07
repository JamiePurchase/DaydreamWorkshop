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
	
	// Dialog
	private String dialogRequest;
	private boolean dialogRequestActive = false;
	
	// Modules (OLD)
	private int moduleActive;
	private Module[] moduleArray = new Module[10];
	private int moduleCount;
	
	// Modules
	private String module;
	private Module moduleApp;
	
	public ModuleManager(String module, int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		appWidth = width;
		appHeight = height;
		appKeyboard = keyboard;
		appMouse = mouse;
		init();
		
		moduleArray[0] = getModule(module);
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
	
	private Module getModule(String module)
	{
		//if(module=="ProjectNew"){return new ModuleProjectNew(appWidth, appHeight, appKeyboard, appMouse, 0, 0, appWidth, appHeight);}
		// NOTE: We shouldn't need this function
		return new ModuleApp(appWidth, appHeight, appKeyboard, appMouse);
	}
	
	public void init()
	{
		initModuleApp();
		initModuleAudio();
		initModuleBoard();
		initModuleCharacter();
		initModuleItem();
		initModuleQuest();
		initModuleTileset();
	}
	
	public void initModuleApp()
	{
		moduleApp = new ModuleApp(appWidth, appHeight, appKeyboard, appMouse);
	}
	
	public void initModuleAudio()
	{
		//moduleAudio = new ModuleAudio(appWidth, appHeight, appKeyboard, appMouse);
	}
	
	public void initModuleBoard()
	{
		
	}
	
	public void initModuleCharacter()
	{
		
	}
	
	public void initModuleItem()
	{
		
	}
	
	public void initModuleQuest()
	{
		
	}
	
	public void initModuleTileset()
	{
		
	}
	
	public void requestDialog(String dialog)
	{
		dialogRequest = dialog;
		dialogRequestActive = true;
	}
	
	public boolean requestDialogActive()
	{
		return dialogRequestActive;
	}
	
	public void requestDialogDone()
	{
		dialogRequest = "";
		dialogRequestActive = false;
	}
	
	public String requestDialogString()
	{
		return dialogRequest;
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