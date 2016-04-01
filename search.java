import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class search implements Runnable{
	
	Map<String, String> textIndex;
	String stringToSearch;
	public search(String toSearch, Map<String, String> argtextIndex) {
		textIndex = argtextIndex;
		stringToSearch = toSearch;
	}
	
	private synchronized void search(){
		System.out.println("Searching thread running...");
		boolean txtFound = false;
		for(String k : textIndex.keySet()){
			BufferedReader in = null;
			try {
				in = new BufferedReader(new FileReader(textIndex.get(k))); 
				
				String buffer;
				while ((buffer = in.readLine()) != null) {
					if(buffer.contains(stringToSearch)){
						System.out.println("Text found in: " +textIndex.get(k));
						txtFound = true;
						
						
					}
				}
				if(txtFound == false){
					System.out.println("Text not found");
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	public void run(){
		search();
	}
}
