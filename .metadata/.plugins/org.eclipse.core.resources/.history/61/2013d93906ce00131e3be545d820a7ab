import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

public class Reduce extends Reducer<LongWritable, Text, LongWritable, Text> {

    public void reduce(LongWritable key, Text values, Context context) 
      throws IOException, InterruptedException {
        
    	//String str = key.toString();//replace("\n", "").replace("\r", "");
    	
    	//String[] temp = StringUtils.split(str, "\n", 2);
    	
    	//System.out.print(str);
    	
    	//key.set(str);
    	
        context.write(key,values);
    }
 }