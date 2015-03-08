package dw.module;
import dw.control.ControlButton;
import dw.control.ControlDialog;
import dw.control.ControlText;
import dw.input.InputKeyboard;
import dw.input.InputMouse;

public class ModuleManager
{
	// Application Data
	private int appWidth;
	private int appHeight;
	private InputKeyboard appKeyboard;
	private InputMouse appMouse;
	
	// Dialog (OLD)
	private String dialogRequest;
	private boolean dialogRequestActive = false;
	
	// Dialog
	private ControlDialog dialogAppAbout;
	private ControlDialog dialogAppProjectNew;
	private ControlDialog dialogIntro;
	
	// Modules
	private String module;
	private Module moduleApp;
	private Module moduleBoard;
	
	public ModuleManager(int width, int height, InputKeyboard keyboard, InputMouse mouse)
	{
		appWidth = width;
		appHeight = height;
		appKeyboard = keyboard;
		appMouse = mouse;
		init();
	}
	
	public ControlDialog dialogFetch(String dialog)
	{
		if(dialog=="About"){return dialogAppAbout;}
		if(dialog=="ProjectNew"){return dialogAppProjectNew;}
		return dialogIntro;
	}
	
	public Module getActive()
	{
		if(module=="App"){return moduleApp;}
		if(module=="Board"){return moduleBoard;}
		return moduleApp;
	}
	
	public void init()
	{
		initDialog();
		initModule();
	}
	
	public void initDialog()
	{
		initDialogAppAbout();
		initDialogAppProjectNew();
		initDialogIntro();
	}
	
	public void initDialogAppAbout()
	{
		dialogAppAbout = new ControlDialog("About", (appWidth/2)-400, (appHeight/2)-250, 800, 500);
		//dialogAppAbout.
		dialogAppAbout.formAddButton(new ControlButton("AboutOk", "OK", (appWidth/2)-50, 400, 100, 50, "DISMISS", ""));
	}
	
	public void initDialogAppProjectNew()
	{
		dialogAppProjectNew = new ControlDialog("New Project", (appWidth/2)-400, (appHeight/2)-250, 800, 500);
		dialogAppProjectNew.formAddText(new ControlText("NewProjectName", 30, 400, 400, 200, 50, appMouse), true);
	}
	
	public void initDialogIntro()
	{
		
	}
	
	public void initModule()
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
		moduleBoard = new ModuleBoard(appWidth, appHeight, appKeyboard, appMouse);
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
		setActive(module);

		// Debug
		System.out.println("Requested the "+module+" module");
	}
	
	public void setActive(String active)
	{
		module = active;
	}
	
}