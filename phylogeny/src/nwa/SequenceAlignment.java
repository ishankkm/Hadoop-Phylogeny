package nwa;
       
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class SequenceAlignment {
	
	Path in;
	Path out;
	
	public SequenceAlignment(String pathIn, String pathOut){
		
		this.in = new Path(pathIn);
		this.out = new Path(pathOut);
		
	}

	public void run() throws Exception {
				 
		Configuration conf = new Configuration();
		
		//FileSystem fileSystem = FileSystem.get(conf);
		FileSystem localFS = FileSystem.getLocal(conf); 
		Path src = new Path("hdfs://master:54310/user/hduser/Genome/nwa-out");
		FileSystem fileSystem = src.getFileSystem(conf);
		Path dst = new Path("/home/hduser/tmp/tmp/merge.txt");
		    
		Job job = new Job(conf, "SequenceAlignment");
		job.setJarByClass(SequenceAlignment.class);
				
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		    
		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);
		    
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		out.getFileSystem(conf).delete(out, true);
//		    
//		FileInputFormat.addInputPath(job, in);
//		FileOutputFormat.setOutputPath(job, out);	
		
		FileInputFormat.addInputPath(job, in);
	    FileOutputFormat.setOutputPath(job, out);
		    
		job.waitForCompletion(true);
		
//		if(localFS.exists(dst))
//			localFS.delete(dst, true);
//		
		
		FileUtil.copyMerge(fileSystem, src, localFS, dst, false, conf, "");
			
	}
	        
}