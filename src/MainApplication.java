import com.preprocess.activities.DiscretizeActivity;
import com.preprocess.activities.SummaryActivity;
import com.preprocess.datautil.*;
import com.preprocess.model.*;

import java.io.*;

public class MainApplication {
	
	public static void main(String[] args) {
		try {			
			Data data = DataReader.read(args[2]);
			switch (args[1]) {
				case "summary": 
					SummaryActivity.WriteOutput(data, args[4]);
					break;
				case "discretize": {
					DiscretizeActivity discretize = new DiscretizeActivity(data,args[5],args[6]);
					if (args[6].equalsIgnoreCase("sau")) discretize.setDepth(args[7]);
					discretize.Process(args[3],args[4]);
				}
			}
		}catch(FileNotFoundException e) {
			System.out.println("Can't not find input file");
		}catch(IOException e) {
			System.out.println("Some errors happend");
		}
	}
}
