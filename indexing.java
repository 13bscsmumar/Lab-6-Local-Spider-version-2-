import java.io.*;

public class indexing implements Runnable{
	String buffer;
	
	indexing(String contentToWrite){
		buffer = contentToWrite;
	}
	
	private synchronized void writeToFile(String toWrite){
		try {
			System.out.println("Writing to file...");
			BufferedWriter bf = new BufferedWriter(new FileWriter("E:\\myindex.txt",true));
			bf.write(buffer);
			
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void run(){
		writeToFile(buffer);
		
	}
}
