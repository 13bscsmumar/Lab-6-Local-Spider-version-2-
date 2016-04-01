import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class crawler implements Runnable{
	Thread indexingThread;
	Thread indexingThread2;
	Thread searchingThread;
	String start_point;
	public crawler(String argStartingPath){
		start_point = argStartingPath;
	}
	
	public void run() 
	{
		int MAX_DEPTH = 100;
		int currentDepth = 0;
		
		String fileSearch = "myfile";
		String txtSearch = "umar";
		
		File[] demFiles = new File(start_point).listFiles();
		Map<String, String> fileIndex = new HashMap<String, String>();
		Map<String, String> textIndex = new HashMap<String, String>();
		
		

		for(File f: demFiles){
			System.out.println(f.getPath());
			if(f.isHidden()){
				continue;	
			}else{
				fileIndex.put(f.getName(), f.getPath());
				if(f.getName().contains(".txt")){
					textIndex.put(f.getName(), f.getAbsolutePath());
					String toWrite = f.getName() + " | " +f.getAbsolutePath() + "\n";
					indexingThread = new Thread(new indexing(toWrite));
					indexingThread.start();
				}
			}
			
		}
		
		Map<String, String> temp = new HashMap<String, String>(); 
		
		for(Map.Entry<String, String> it : fileIndex.entrySet()){
			if(currentDepth==MAX_DEPTH)
				break;	
			
			String p = it.getKey(); 
			if(p.contains(".txt")){
				textIndex.put(p, fileIndex.get(p));
			}else{
				File[] localFiles = new File(fileIndex.get(p)).listFiles();
				
				for(File f : localFiles){
					if(f.isHidden()){
						continue;
					}else{
						temp.put(f.getName(), f.getAbsolutePath());
						String toWrite = f.getName() + " | " +f.getAbsolutePath() + "\n";
						indexingThread2 = new Thread(new indexing(toWrite));
						indexingThread2.start();
					}
				}
				currentDepth++;
			}
		}
		
		fileIndex.putAll(temp); 
		
		
		boolean fileFound = false;
		for(String k : fileIndex.keySet()){
			if(fileIndex.get(k).contains(fileSearch)){
				System.out.println("Found at: " +fileIndex.get(k));
				fileFound = true;
			}
		}
		
		if(fileFound==false){
			System.out.println("File not found");
		}

		searchingThread = new Thread(new search(txtSearch, textIndex));
		searchingThread.start();
		
}
}
