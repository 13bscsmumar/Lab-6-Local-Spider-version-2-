import java.awt.List;
import java.io.File;
import java.util.ArrayList;

public class lab06 {
	public static void main(String[] args){
		ArrayList<String> fileNames = new ArrayList<String>();
		File[] demFiles = new File("E:\\").listFiles();
		
		for(File f: demFiles){
			System.out.println(f.getPath());
			if(f.isHidden()){
				continue;	
			}else{
				fileNames.add(f.getAbsolutePath());
			}
			
		}
		
		for(String name : fileNames){
			Thread someThread = new Thread(new crawler(name));
			someThread.start();
		}
	}
}
