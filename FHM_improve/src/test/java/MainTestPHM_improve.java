import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;


/**
 * Example of how to use the PHM algorithm from the source code.
 * @author Philippe Fournier-Viger, 2016
 */

public class MainTestPHM_improve {

	public static void main(String [] arg) throws IOException{
		boolean improve = true;

		String output = ".//output.txt";
		int thresholdID = 0;
		String datasetName = "chess";

		MySql mySql = new MySql();
		if (improve){
			mySql.TRUNCATETable("phm_improve",datasetName);
		}else {
			mySql.TRUNCATETable("phm",datasetName);
		}



		// =======================
		// EXAMPLE FROM THE ARTICLE : 
		String input = fileToPath("Chess_phm.txt");
		int min_utility = 10000000;
		int minPeriodicity = 1;  // minimum periodicity parameter (a number of transactions)
		int maxPeriodicity = 50;  // maximum periodicity parameter (a number of transactions)
		int minAveragePeriodicity = 1;  // minimum average periodicity (a number of transactions)
		int maxAveragePeriodicity = 20;  // maximum average periodicity (a number of transactions)
		// =======================
		
		//===== Optional parameters (new, 2017)==//
		// Minimum number of items that patterns should contain
		int minimumLength = 1;
		// Maximum number of items that patterns should contain
		int maximumLength = Integer.MAX_VALUE;
		//===========================//

		// Applying the PHM algorithm
		//AlgoPHM algorithm = new AlgoPHM();
		// To disable some optimizations:
		//algorithm.setEnableEUCP(false); 
		//algorithm.setEnableESCP(false);

		int length = 1000000;
		for (int i=0;i<1;i++){
			thresholdID = thresholdID+1;
			min_utility = min_utility-length;
			AlgoPHM_improve algorithm = new AlgoPHM_improve();

			algorithm.setMinimumLength(minimumLength);
			algorithm.setMaximumLength(maximumLength);

			algorithm.runAlgorithm(thresholdID,improve,input, output, min_utility,
					minPeriodicity, maxPeriodicity, minAveragePeriodicity,
					maxAveragePeriodicity);

			algorithm.printStats(thresholdID,datasetName);
		}

		// set the pattern length constraints
//		algorithm.setMinimumLength(minimumLength);
//		algorithm.setMaximumLength(maximumLength);
//
//		// Run the algorithm
//		algorithm.runAlgorithm(thresholdID,improve,input, output, min_utility,
//				minPeriodicity, maxPeriodicity, minAveragePeriodicity,
//				maxAveragePeriodicity);
//
//		// Print statistics about the execution of the algorithm
//		algorithm.printStats(thresholdID,datasetName);

	}

	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = MainTestPHM_improve.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}
