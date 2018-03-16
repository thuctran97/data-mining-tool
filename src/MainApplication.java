import com.preprocess.datautil.*;
import com.preprocess.model.*;

import java.util.*;
import java.io.*;

public class MainApplication {
	
	public static void main(String[] args) {
		try {			
			Data data = DataReader.read(args[1]);
			DataHandler handler = new DataHandler(data);
			for (String key : handler.getValueListPerAttr().keySet()) {
				System.out.println(" Attribute : " + key);
				System.out.print("\tValue list : ");
				for (String value : handler.getValueListPerAttr().get(key)) {
					System.out.print(value + "(" + handler.getValueCounter().get(value) + "), ");
				}
				System.out.println();
			}
			DataWriter.write(args[2], data);
		}catch(FileNotFoundException e) {
			System.out.println("Can't not find input file");
		}catch(IOException e) {
			System.out.println("Some errors happend");
		}
	}
}
