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
	private double sACoef = 0;
	private double sBCoef = 0;
	private double sCCoef = 0;
	
	public Line(int sX1, int sY1, int sX2, int sY2) { 
		point1 = new Point(sX1,sY1);
		point2 = new Point(sX2,sY2);
		sACoef = sY2 - sY1;
		sBCoef = sX1 - sX2;
		sCCoef = sX1*sY2 - sY1*sX2;
	}
	
	public Line(Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
		sACoef = this.point2.getY() - this.point1.getY();
		sBCoef = this.point1.getX() - this.point2.getX();
		sCCoef = this.point1.getX()*this.point2.getY() - 
					this.point1.getY()*this.point2.getX();
	}
	
	public int compareTo(Point point) {
		//calculate and temporarily save sign to see where point falls
		//on the graph
		double sign = sACoef*point.getX() + sBCoef*point.getY() - sCCoef;
		
		if(sign > 0) return 1; //point is on side 1
		if(sign < 0) return -1; //point is on side 2
		else return 0; //point is on this line
	}
	
	public String toString() {
		return sACoef + "x + " + sBCoef + "y = " + sCCoef;
	}
}
