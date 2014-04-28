package icst.cyberlab.phylogenetic.upgma.action;

import icst.cyberlab.phylogenetic.upgma.core.Point;

public class MatrixAction {
	
	public static Point findPointMinValueInMatrix(float[][] matrix){
		Point point = new Point();
		point.setValue(matrix[1][0]);
		point.setCol(0);
		point.setRow(1);
		for (int row = 1; row < matrix.length; row++) {
			for (int col = 0; col < row; col++) {				
				if(matrix[row][col] < point.getValue()){
					point.setRow(row);
					point.setCol(col);
					point.setValue(matrix[row][col]);
				}
			}			
		}		
		return point;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		float[][] matrix = {{0, 1, 2, 3, 3},
							{1, 0, 2, 3, 3},
							{2, 2, 0, 3, 3},
							{3, 3, 3, 0, 1}, 
							{3, 3, 3, 1, 0}};
		Point point = MatrixAction.findPointMinValueInMatrix(matrix);
		System.out.println(point.getValue() + " : " + point.getRow() + " : " + point.getCol());
	}
}
