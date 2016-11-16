import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import javax.naming.Context;
import javax.security.auth.login.Configuration;


public class KNNMapper {
	
	public void setup (Context context) {

		try {

		  Configuration conf = context.getConfiguration();

		  FileSystem fs = FileSystem.get(conf);

		  String filename = context.getConfiguration().get("traindata");

		  Path p = new Path(filename);

		  fs = p.getFileSystem(conf);

		  setupTrainData(fs, p.toString());

		} catch (IOException ioe) {

		  System.err.println("Error reading train data file.");

		  System.exit(0);

		}

		private void setupTrainData(FileSystem fs, String filename) throws IOException {

		  // put the training datasets into a list of array [[suit,rank,suit,rank,....][label]]

		  BufferedReader reader = null;

		  try {

		    FSDataInputStream src = fs.open( new Path(filename));

		    reader = new BufferedReader(new InputStreamReader(src));

		  } catch (IllegalArgumentException ill) {

		    System.err.println(ill.getMessage());

		  } catch (IOException ioe) {

		  System.err.println(ioe.getMessage());

		  } catch (Exception e) {

		    System.err.println(e.getMessage());

		  }

		  String line = reader.readLine();

		  // read line by line, parse to desired data structure.

		}

} // end Mapper class
