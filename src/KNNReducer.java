import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

public class KNNReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	public void reduce(Text key, Iterable<IntWritable> values, Context context) 
		      throws IOException, InterruptedException {
		        int sum = 0;
		        System.out.println("Reduce check");
		        context.write(key, new IntWritable(sum));
} // end reduce class 
	
	
} // end KNNReducer class 
