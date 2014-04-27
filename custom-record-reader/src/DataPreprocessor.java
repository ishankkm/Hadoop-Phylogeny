//import java.io.IOException;
//import java.util.*;
        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
        
public class DataPreprocessor{
	
	Path in;
	Path out;
	
//	public DataPreprocessor(String pathIn, String pathOut){
//		
//		this.in = new Path(pathIn);
//		this.out = new Path(pathOut);
//	}
	

	public static void main(String[] args) throws Exception {
			 
		String regex = "^[>].*";
		 
		Configuration conf = new Configuration();
		conf.set("record.delimiter.regex", regex);
		//conf.set("textinputformat.record.delimiter", "");
		//conf.set("mapreduce.output.textoutputformat.separator", ";");
		    
		Job job = new Job(conf, "wordcount");
		job.setJarByClass(DataPreprocessor.class);
		
		Path output1 = new Path(args[1]);
		output1.getFileSystem(conf).delete(output1, true);
		
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		    
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		    
		job.setInputFormatClass(PatternInputFormat.class);
		//job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(CustomOutputFormat.class);
		    
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
		//System.out.println(total);
	}
        
}
