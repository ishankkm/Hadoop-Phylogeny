import java.io.IOException;
        
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

public class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	private Text word = new Text();

    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        
    	int score = 0;
    	
    	String temp = key.toString();    	
    	String arr[] = temp.split("\t");
    	    	
    	NeedlemanWunsch nw = new NeedlemanWunsch(arr[1], arr[3]);

    	nw.process();
    	nw.backtrack();
    	
    	word.set(arr[0] + " " + arr[2]);
    	
    	score = nw.printScoreAndAlignments();
        
        context.write(word, new IntWritable(score));
    }
 }