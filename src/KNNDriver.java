import java.io.IOException;
import java.util.*;        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class KNNDriver {
	
	public static void main(String[] args) throws Exception {
	    
		Configuration conf = new Configuration();    
	    Job job = new Job(conf, "PokerHand");
	    
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	        
	    job.setMapperClass(KNNMapper.class);
	    job.setReducerClass(KNNReducer.class);
	        
	    job.setInputFormatClass(TextInputFormat.class);
	    job.setOutputFormatClass(TextOutputFormat.class);
	        
	    FileInputFormat.addInputPath(job, new Path(args[1]));
	    FileOutputFormat.setOutputPath(job, new Path(args[2]));
	        
	    job.setJarByClass(KNNDriver.class);
	    job.waitForCompletion(true);
	 } // end Main method

} // end KNNDriver CLass
