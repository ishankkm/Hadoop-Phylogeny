package upgma;

public class Point {
	private int row;
	private int col;
	private float value;
	
	public Point(){
		row = 0;
		col = 0;
		value = 0;
	}
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	
	
}
