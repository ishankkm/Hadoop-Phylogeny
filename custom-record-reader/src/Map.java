import java.io.IOException;
        
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

public class Map extends Mapper<LongWritable, Text, LongWritable, Text> {
            
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    	
    	//word.set(key + " " + value);
    	
    	//System.out.println(key);
        context.write(key, value);
    }
}