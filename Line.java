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
	//private Point point1;
	//private Point point2;
	
	private double x1; //x coord of first point
	private double x2; //x coord of second point
	private double y1; //y coord of first point
	private double y2; //y coord of second point
	
	//equation of this line
	private double aCoef = 0;
	private double bCoef = 0;
	private double cCoef = 0;
	
	//constructor using coordinates of two points
	public Line(int x1, int y1, int x2, int y2) {
		//store x and y values
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		//calculate line coefficients
		aCoef = y2 - y1;
		bCoef = x1 - x2;
		bCoef = x1*y2 - y1*x2;
	}
	
	//constructor using Points as parameters
	public Line(Point point1, Point point2) {
		//store x and y values
		this.x1 = point1.getX();
		this.y1 = point1.getY();
		this.x2 = point2.getX();
		this.y2 = point2.getY();
		
		//calculate line coefficients
		aCoef = y2 - y1;
		bCoef = x1 - x2;
		bCoef = x1*y2 - y1*x2;
	}
	
	/**************************************************************/
	/* Method: distFromLine
	/* Purpose: Calculate the dist of a point from a line obj
	/* Parameters:
	/* Point testPoint -- point to calc dist to
	/* Returns: double -- dist to the point
	/**************************************************************/
	public double distFromLine(Point testPoint) {
		double x3 = testPoint.getX();
		double y3 = testPoint.getY();
		return (x1*y2) + (x3*y1) + (x2*y3) - (x3*y2) - (x2*y1) - (x1*y3);
	}
	
	/**************************************************************/
	/* Method: isRight
	/* Purpose: returns true if point is to the right of the line
	/* Parameters:
	/* Point testPoint -- point to test
	/* Returns: boolean -- true if is to the right
	/**************************************************************/
	public boolean isRight(Point testPoint) {
		if(distFromLine(testPoint) < 0) return true;
		else return false;
	}
	
	/**************************************************************/
	/* Method: getPoint1 and getPoint2
	/* Purpose: Accessors for the two points that make up the line
	/* Parameters:
	/* none
	/* Returns: Point -- the point requested
	/**************************************************************/
	public Point getPoint1() {
		return new Point(x1,y1);
	}
	
	public Point getPoint2() {
		return new Point(x2,y2);
	}
	
	/**************************************************************/
	/* Method: compareTo
	/* Purpose: compare point to line
	/* Parameters:
	/* Point point -- point to compare to line
	/* Returns: void
	/**************************************************************/
	public int compareTo(Point point) {
		//calculate and temporarily save sign to see where point falls
		//on the graph
		double sign = aCoef*point.getX() + bCoef*point.getY() - cCoef;
		
		if(sign > 0) return 1; //point is on left
		if(sign < 0) return -1; //point is on right
		else return 0; //point is on this line
	}
	
	/**************************************************************/
	/* Method: toString
	/* Purpose: print str representation of line eqn
	/* Parameters:
	/* String[] args: filename or -debug filename
	/* Returns: void
	/**************************************************************/
	public String toString() {
		return aCoef + "x + " + bCoef + "y = " + cCoef;
	}
}
