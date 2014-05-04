import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import upgma.MatrixAction;
import upgma.Node;
import upgma.Point;
import upgma.UPGMA;

public class Main {
	
	public static void main(String args[]){
		
		ArrayList<String> seqList = new ArrayList<String>();
		ArrayList<Double> valueList = new ArrayList<Double>();
		
		//double matrix[][];
		BufferedReader br = null;
		String arr[];	
		 
		try{
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader("/home/hduser/tmp/tmp/merge.txt"));
 
			while ((sCurrentLine = br.readLine()) != null) {
				
				arr = sCurrentLine.split("\t");
				valueList.add(Double.parseDouble(arr[2]));
				
				if(!seqList.contains(arr[0])) {
					
					//System.out.println(arr[0].substring(1));
					seqList.add(arr[0]);
				}
				//System.out.println(sCurrentLine);
			}
			double matrix[][] = new double[seqList.size()][seqList.size()];
			
			for (int i = 0; i < seqList.size(); i++) {
				for (int j = 0; j < seqList.size(); j++) {
					matrix[i][j] = valueList.get(i+j);
					//System.out.print(" " + matrix[i][j]);
				}
				//System.out.println();
			}
			
			UPGMA upgma = new UPGMA();
			
			Node[] nodeList = new Node[seqList.size()];
			String[] nodeString = seqList.toArray(new String[seqList.size()]);;
			
			for (int i = 0; i < nodeList.length; i++) {
				nodeList[i] = new Node(nodeString[i].substring(1));
			}
			
			while(!upgma.checkMatrixToTerminateIterate(matrix)){
				
				Point point = MatrixAction.findPointMinValueInMatrix(matrix);
				
				nodeList = upgma.clusterTwoNode(nodeList, matrix[point.getRow()][point.getCol()], point.getRow(), point.getCol());
				matrix = upgma.clusterTwoMatrix(matrix, point.getRow(), point.getCol());
				
//				for(int i=0;i<nodeList.length;i++)
//					System.out.println(nodeList[i].getNameNode());
			}
			System.out.println(nodeList[0].getNameNode());
 
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
