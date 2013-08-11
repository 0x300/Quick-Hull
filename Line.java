/**************************************************************/
/* Josh Lindoo
/* Login ID: lind6441
/* CS-203, Summer 2013
/* Programming Assignment 2
/* Line class: used to represent a line defined by two points
/* Requirements: uses point class
/**************************************************************/

public class Line {
	//fields
	private Point point1;
	private Point point2;
	
	//equation of this line
	private double aCoef = 0;
	private double bCoef = 0;
	private double cCoef = 0;
	
	public Line(int x1, int y1, int x2, int y2) { 
		point1 = new Point(x1,y1);
		point2 = new Point(x2,y2);
		aCoef = y2 - y1;
		bCoef = x1 - x2;
		bCoef = x1*y2 - y1*x2;
	}
	
	public Line(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
		aCoef = this.point2.getY() - this.point1.getY();
		bCoef = this.point1.getX() - this.point2.getX();
		cCoef = this.point1.getX()*this.point2.getY() - 
					this.point1.getY()*this.point2.getX();
	}
	
	public int compareTo(Point point) {
		//calculate and temporarily save sign to see where point falls
		//on the graph
		double sign = aCoef*point.getX() + bCoef*point.getY() - cCoef;
		
		if(sign > 0) return 1; //point is on side 1
		if(sign < 0) return -1; //point is on side 2
		else return 0; //point is on this line
	}
	
	public String toString() {
		return aCoef + "x + " + bCoef + "y = " + cCoef;
	}
}
