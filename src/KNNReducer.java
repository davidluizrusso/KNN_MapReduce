import java.io.IOException;
import java.util.Iterator;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class KNNReducer extends Reducer<Text, Text, Text, Text> {

	// Mapper did all the hard work so it just needs to print the results
	public void reduce(Text key, Iterable<Text> values,	Context context) 
			throws IOException, InterruptedException {
		
		Iterator<Text> it = values.iterator();
		Text value = it.next();
		
		String s = String.format("   (%s)", String.valueOf(value));
		Text result = new Text(s);
		context.write(key, result);
	}
}
