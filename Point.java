/**************************************************************/
/* Josh Lindoo
/* Login ID: lind6441
/* CS-203, Summer 2013
/* Programming Assignment 2
/* Point class: object used to represent a point in a 2d plane
/**************************************************************/

public class Point {
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
	
	public String toString() {
		return "(" + xCoord + "," + yCoord + ")";
	}
	
}
