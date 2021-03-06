package crr;
        
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
        
public class DataPreprocessor{
	
	Path in;
	Path out;
	
	public DataPreprocessor(String pathIn, String pathOut){
		
		this.in = new Path(pathIn);
		this.out = new Path(pathOut);
	}
	

	public void run() throws Exception {
			 
		String regex = "^[>].*";
		 
		Configuration conf = new Configuration();
		conf.set("record.delimiter.regex", regex);
		    
		Job job = new Job(conf, "wordcount");
		job.setJarByClass(DataPreprocessor.class);
		
		out.getFileSystem(conf).delete(out, true);
		
		job.setOutputKeyClass(LongWritable.class);
		job.setOutputValueClass(Text.class);
		    
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		    
		job.setInputFormatClass(PatternInputFormat.class);
		job.setOutputFormatClass(CustomOutputFormat.class);
		    
		FileInputFormat.addInputPath(job, in);
		FileOutputFormat.setOutputPath(job, out);
		
		job.waitForCompletion(true);
		//System.out.println(total);
	}
        
}
