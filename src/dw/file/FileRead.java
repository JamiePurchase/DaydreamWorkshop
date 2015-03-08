package dw.file;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class FileRead
{
	public String readPath;
	
	public FileRead(String newPath)
	{
		readPath = "C:/Eclipse/Workspace/Daydream/data/" + newPath;
	}
	
	public String[] FileReadData() throws IOException
	{
		FileReader fr = new FileReader(readPath);
		BufferedReader tr = new BufferedReader(fr);
		int lineCount = FileReadLines();
		String[] textData = new String[lineCount];
		for(int line=0;line<lineCount;line+=1)
		{
			textData[line] = tr.readLine();
		}
		tr.close();
		return textData;
	}
	
	private int FileReadLines() throws IOException
	{
		FileReader fr = new FileReader(readPath);
		BufferedReader bf = new BufferedReader(fr);
		int lineCount = 0;
		while(bf.readLine() != null)
		{
			lineCount+=1;
		}
		bf.close();
		return lineCount;
	}

}