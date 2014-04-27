import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.ReflectionUtils;

public class CustomOutputFormat<LongWritable, Text> extends TextOutputFormat<LongWritable, Text>{

	public RecordWriter<LongWritable, Text> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
		
		Configuration conf = job.getConfiguration();
        boolean isCompressed = getCompressOutput(job);
        String keyValueSeparator = conf.get("mapred.textoutputformat.separator", "\t");
        CompressionCodec codec = null;
        String extension = "";
        if (isCompressed) {
                Class<? extends CompressionCodec> codecClass = getOutputCompressorClass(job, GzipCodec.class);
                codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
                extension = codec.getDefaultExtension();
        }
        
        Path file = getDefaultWorkFile(job, extension);
        FileSystem fs = file.getFileSystem(conf);
        
        if (!isCompressed) {
                FSDataOutputStream fileOut = fs.create(file, false);
                return new CustomRecordWriter<LongWritable, Text>(fileOut, keyValueSeparator);
        } else {
                FSDataOutputStream fileOut = fs.create(file, false);
                return new CustomRecordWriter<LongWritable, Text>(new DataOutputStream(codec.createOutputStream(fileOut)), keyValueSeparator);
        }
		
		
	}
	
	protected static class CustomRecordWriter<LongWritable, Text> extends RecordWriter<LongWritable, Text>{
		
		private static final String utf8 = "UTF-8";
	    private static final byte[] newline;
	    protected DataOutputStream out;
	    private final String customOutputSeparator;

	    static {
	      try {
	        newline = "".getBytes(utf8);
	      } catch (UnsupportedEncodingException uee) {
	        throw new IllegalArgumentException("can't find " + utf8 + " encoding");
	      }
	    }	    
		
	    public CustomRecordWriter(DataOutputStream out, String keyValueSeparator) {
	    	
	        this.out = out;
	        try {
	        	
	          this.customOutputSeparator = keyValueSeparator;
	        } catch (IllegalArgumentException uee) {
	        	
	          throw new IllegalArgumentException("can't find " + utf8 + " encoding");
	        }
	      }

	    public CustomRecordWriter(DataOutputStream out) {
	    	this(out, " ");
	    }
	    
	    private void writeObject(Object o) throws IOException {
	        
	        out.write(o.toString().getBytes(utf8));
	        
	      }
	    
	    @Override
	    public synchronized void write(LongWritable key, Text value) throws IOException, InterruptedException {
	      
	    	boolean nullKey = key == null;
	        boolean nullValue = value == null;
	        
	        if (nullKey && nullValue) {
	          return;
	        }
	        if (!nullKey) {
	          //writeObject(key);
	        }
	        if (!(nullKey || nullValue) && !key.toString().equalsIgnoreCase("0")) {
	          out.write(customOutputSeparator.getBytes(utf8));
	        }
	        if (!nullValue) {
	          writeObject(value);
	        }
	        out.write(newline);
	    	
//	    	if (key == null) {
//	    		return;
//	    	}
//	      
//	    	out.write(key.toString().getBytes(utf8));
	    }
	    
	    public synchronized void close(TaskAttemptContext context) throws IOException {
	    	out.close();
	    }
	}
}