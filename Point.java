/**************************************************************/
/* Josh Lindoo
/* Login ID: lind6441
/* CS-203, Summer 2013
/* Programming Assignment 2
/* Point class: object used to represent a point in a 2d plane
/**************************************************************/

public class Point implements Comparable<Point>{
	//fields
	private double xCoord = 0; //point's x value
	private double yCoord = 0; //point's y value
	
	//constructor
	public Point(double xCoord, double yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	/**************************************************************/
	/* Method: getX and getY
	/* Purpose: Accessor methods
	/* Parameters:
	/* none
	/* Returns: double -- the x or y value
	/**************************************************************/
	public double getX() {
		return xCoord;
	}
	
	public double getY() {
		return yCoord;
	}
	
	/**************************************************************/
	/* Method: compareTo
	/* Purpose: Compare points by x value and then res. ties by y val
	/* Parameters:
	/* String[] args: filename or -debug filename
	/* Returns: void
	/**************************************************************/
	public int compareTo(Point point) {
		if(xCoord - point.getX() > 0) return 1;
		else if(xCoord - point.getX() == 0) {
			if(yCoord - point.getY() > 0) return 1;
			else return -1;
		}
		else return -1;
	}
	
	/**************************************************************/
	/* Method: toString
	/* Purpose: Print point info
	/* Parameters:
	/* none
	/* Returns: void
	/**************************************************************/
	public String toString() {
		return "(" + xCoord + "," + yCoord + ")";
	}
	
}
