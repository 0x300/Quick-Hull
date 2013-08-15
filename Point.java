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
	
	public Point(double xCoord, double yCoord) {
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	public double getX() {
		return xCoord;
	}
	
	public double getY() {
		return yCoord;
	}
	
	public int compareTo(Point point) {
		if(xCoord - point.getX() > 0) return 1;
		else if(xCoord - point.getX() == 0) {
			if(yCoord - point.getY() > 0) return 1;
			else return -1;
		}
		else return -1;
	}
	
	public String toString() {
		return "(" + xCoord + "," + yCoord + ")";
	}
	
}
