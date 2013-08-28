/**************************************************************/
/* Josh Lindoo
/* Login ID: lind6441
/* CS-203, Summer 2013
/* Programming Assignment 1
/* Prog1 class: Inputs datafile of points and calcs convex hull
/**************************************************************/

import java.io.*;
import java.util.*;

public class Prog2 {
	//global vars
	public static boolean debug = false; //toggles debug mode
	public static ArrayList<Point> points = new ArrayList<Point>(); //arraylist of all points from input
	public static ArrayList<Point> convexHull = new ArrayList<Point>(); //arraylist of point in hull
	
	public static long startTime = 0; //start time of algorithm
	public static long endTime = 0; //end time of algorithm
	
	/**************************************************************/
	/* Method: main
	/* Purpose: Control program flow
	/* Parameters:
	/* String[] args: filename or -debug filename
	/* Returns: void
	/**************************************************************/
	public static void main(String[] args) {
		//local vars
		int numArgs = args.length; //number of args
		
		//check arguments
		switch(numArgs) {
			case 1: //arg filename
				importData(args[0]);
				
				startTime = System.nanoTime();
				findHull();
				endTime = System.nanoTime();
				
				printHull();
				
				//print timing result
				System.out.println("Elapsed Time: " + (endTime-startTime) + 
						" nanoseconds");
				break;
				
			case 2: //arg -debug flag and filename
				
				//set debug flag
				if(args[0].compareTo("-debug") == 0) debug = true;
				else System.out.println("Error: First parameter must be \"-debug\"");
				
				importData(args[1]);
				
				startTime = System.nanoTime();
				findHull();
				endTime = System.nanoTime();
				
				printHull();
				
				//print timing result
				System.out.println("Elapsed Time: " + (endTime-startTime) + 
						" nanoseconds");
				
				//System.out.println("Hull:\n" + hull); //print hull
				break;
				
			default: //invalid number of args
				System.out.println("Error: Invalid number of parameters");
				break;
		} //end switch
		
	} //end main()
	
	/**************************************************************/
	/* Method: importData
	/* Purpose: import datafile and save in arrays
	/* Parameters:
	/* String fileName
	/* Returns: void
	/**************************************************************/
	public static void importData(String fileName) {
		Scanner fileScan; //scanner for input file
		try {
			fileScan = new Scanner(new File(fileName));
			
			//import points into arraylist
			while(fileScan.hasNext()) { 
				points.add(new Point(Double.parseDouble(fileScan.next()), Double.parseDouble(fileScan.next())));
			}
			
			//sort list of points by x value
			Collections.sort(points);
			
		}
		catch(FileNotFoundException exc) {
			System.out.println("Error: " + exc.getMessage() + ".. exiting");
			System.exit(0);
		}
		catch(NumberFormatException exc) {
			System.out.println("Error: " + exc.getMessage() + 
					".. all inputs must be a double or an integer");
		}
		
	}
	
	/**************************************************************/
	/* Method: findHull
	/* Purpose: Find the convex hull of the input
	/* Parameters:
	/* Returns: void
	/**************************************************************/
	public static void findHull() {
		
		Point pointA = points.get(0); //left most point
		Point pointB = points.get(points.size()-1); //right most point
		
		convexHull.add(pointA); //add left most point to hull
		convexHull.add(pointB); //add right most point to hull
		
		ArrayList<Point> sublist1 = new ArrayList<Point>(); //right side of line AB
		ArrayList<Point> sublist2 = new ArrayList<Point>(); //right side of line BA
		
		Line lineAB = new Line(pointA, pointB); //line going one way
		Line lineBA = new Line(pointB, pointA); //line going the other way
		
		sublist1 = rightSidePoints(lineAB); //upper points
		sublist2 = rightSidePoints(lineBA); //lower points
		
		quickHull(sublist1, lineAB);
		quickHull(sublist2, lineBA);
		
	}
	
	/**************************************************************/
	/* Method: quickHull
	/* Purpose: Find the convex hull of the input
	/* Parameters:
	/* Returns: void
	/**************************************************************/
	public static void quickHull(ArrayList<Point> pointSet, Line line) {
		
		if(pointSet.isEmpty()) return;
		
		//find farthest point
		double highestDist = 0; //track index of farthest point
		Point farthestPoint = null;
		
		Iterator<Point> pointIterator = pointSet.iterator();
		while(pointIterator.hasNext()) {
			Point currPoint = pointIterator.next(); //current point
			double distance = line.distFromLine(currPoint); //distance
			if(distance < highestDist) {
				highestDist = distance;
				farthestPoint = currPoint;
			}
		}
		
		convexHull.add(farthestPoint);
		
		Line lineAB = new Line(line.getPoint1(), farthestPoint); //line from first point to the farthest point
		Line lineBC = new Line(farthestPoint, line.getPoint2()); //line from the farthest point to the second point
		
		ArrayList<Point> sublist1 = new ArrayList<Point>(); //left side of line AB
		ArrayList<Point> sublist2 = new ArrayList<Point>(); //left side of line BA
		
		sublist1 = rightSidePoints(lineAB); //upper points
		sublist2 = rightSidePoints(lineBC); //lower points
		
		quickHull(sublist1, lineAB);
		quickHull(sublist2, lineBC);
		
	}
	
	/**************************************************************/
	/* Method: isSamePoint
	/* Purpose: check if two points are the same
	/* Parameters:
	/* int point1, int point2	the indices of the two points to 
	/* 							check
	/* Returns: boolean: 		whether or not the points are 
	/* 							duplicates
	/**************************************************************/
	public static boolean isSamePoint(int point1, int point2) {
		if(points.get(point1).getX() == points.get(point2).getX() && 
				points.get(point1).getY() == points.get(point2).getY()) return true;
		else return false;
	}
	
	/**************************************************************/
	/* Method: pointPosition
	/* Purpose: calculate the position of a point relative to a line
	/* Parameters:
	/* Point initPoint -- the starting point for a line
	/* Point endPoint -- the ending point for a line
	/* Point testPoint -- the point to check the pos. for
	/* Returns: double -- the distance from the line
	/**************************************************************/
	public static double pointPosition(Point initPoint, Point endPoint, Point testPoint) {
		Line currLine = new Line(initPoint, endPoint);
		return currLine.distFromLine(testPoint);
	}
	
	/**************************************************************/
	/* Method: rightSidePoints
	/* Purpose: Find points to the right side of a line
	/* Parameters:
	/* Line line -- the line to find points to the right of
	/* Returns: ArrayList<Point> -- an arraylist of the points to the right of the line
	/**************************************************************/
	public static ArrayList<Point> rightSidePoints(Line line) {
		Iterator<Point> pointIterator = points.iterator();
		ArrayList<Point> rightPoints = new ArrayList<Point>();
		
		while(pointIterator.hasNext()) {
			//get current point
			Point currPoint = pointIterator.next();
			
			//if it is to the right of AB
			if(line.isRight(currPoint))
				rightPoints.add(currPoint);
		}
		
		return rightPoints;
	}
	
	/**************************************************************/
	/* Method: printHull
	/* Purpose: Print the points that make up the hull
	/* Parameters:
	/* none
	/* Returns: void
	/**************************************************************/
	public static void printHull() {
		Iterator<Point> iter = convexHull.iterator();
		System.out.println("\n\nConvex Hull: ");
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}