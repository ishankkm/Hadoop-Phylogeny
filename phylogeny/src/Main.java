import cart.CartesianProductTest;
import crr.DataPreprocessor;
import nwa.SequenceAlignment;

public class Main {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		long start = System.currentTimeMillis();
		
		String crrIn = args[0];
		String crrOut = "hdfs://master:54310/user/hduser/Genome/dp-out";
		
		String cartIn = "hdfs://master:54310/user/hduser/Genome/dp-out/part*";
		String cartOut = "hdfs://master:54310/user/hduser/Genome/cart-out";
		
		String nwaIn = "hdfs://master:54310/user/hduser/Genome/cart-out/part*";
		String nwaOut = "hdfs://master:54310/user/hduser/Genome/nwa-out";
		
		DataPreprocessor dp = new DataPreprocessor(crrIn, crrOut);
		CartesianProductTest cpt = new CartesianProductTest(cartIn,cartOut);
		SequenceAlignment sa = new SequenceAlignment(nwaIn,nwaOut);
		
		try{
			dp.run();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			cpt.run();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			sa.run();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		long finish = System.currentTimeMillis();
		System.out.println("Time in ms: " + (finish - start));
		

	}

}
