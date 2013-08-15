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
				quickHull();
				endTime = System.nanoTime();
				
				//System.out.println("Hull:\n" + hull); //print hull
				
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
				quickHull();
				endTime = System.nanoTime();
				
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
			
			for(Point point: points){
				  System.out.println(point);
			}
			
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
	/* Method: calcHull
	/* Purpose: Find the convex hull of the input
	/* Parameters:
	/* Returns: void
	/**************************************************************/
	public static void calcHull() {
		
		
		
	}
	
	/**************************************************************/
	/* Method: quickHull
	/* Purpose: Find the convex hull of the input
	/* Parameters:
	/* Returns: void
	/**************************************************************/
	public static void quickHull() {
		
		
		
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
	/* Method: main
	/* Purpose: Control program flow
	/* Parameters:
	/* String[] args: title of book to find
	/* Returns: void
	/**************************************************************/
	public static void addEdgeToHull(double x1, double y1, double x2, double y2) {
		//add points if not duplicates
//		if(!hull.contains(x1 + ", " + y1))
//			hull += "(" + x1 + ", " + y1 + ")\n";
//		
//		if(!hull.contains(x2 + ", " + y2))
//			hull += "(" + x2 + ", " + y2 + ")\n";
	}
}