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
	    
		// iniate configuration and job
		Configuration conf = new Configuration(); 
	    @SuppressWarnings("deprecation")
		Job job = new Job(conf, "PokerHand");

	    // set testing and training data parameters within the job
		job.getConfiguration().set("traindata", args[0]);
		job.getConfiguration().set("testdata", args[1]);
	    
		// establish the driver, mapper, and reducer classes
		job.setJarByClass(KNNDriver.class);
		job.setMapperClass(KNNMapper.class);
	    job.setReducerClass(KNNReducer.class);
	    
	    // establish output key value classes
	    job.setOutputKeyClass(Text.class);
	    job.setOutputValueClass(IntWritable.class);
	        
	    // establish inut and output format classes
	    job.setInputFormatClass(TextInputFormat.class);
	    job.setOutputFormatClass(TextOutputFormat.class);
	        
	    // establish input and output directories
		FileInputFormat.setInputPaths(job, new Path(args[2]));
	    FileOutputFormat.setOutputPath(job, new Path(args[3]));
	        
	    // management parameter set
	    job.waitForCompletion(true);
	    
	 } // end Main method

} // end KNNDriver CLass
