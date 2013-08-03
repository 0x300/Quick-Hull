/**************************************************************/
/* Josh Lindoo
/* Login ID: lind6441
/* CS-203, Summer 2013
/* Programming Assignment 1
/* Prog1 class: Inputs datafile of points and calcs convex hull
/**************************************************************/

import java.io.*;
import java.util.*;

public class Prog1 {
	//global vars
	public static boolean debug = false; //toggles debug mode
	public static double[] xCoords; //array of x coordinates
	public static double[] yCoords; //array of y coordinates
	
	public static String hull = ""; //string representation of convex hull
	
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
				calcConvexHull();
				endTime = System.nanoTime();
				System.out.println("Hull:\n" + hull);
				System.out.println("Elapsed Time: " + (endTime-startTime) + 
						" nanoseconds");
				break;
				
			case 2: //arg -debug flag and filename
				if(args[0].compareTo("-debug") == 0) debug = true;
				else {
					System.out.println("Error: First parameter must be \"-debug\"");
				}
				importData(args[1]);
				calcConvexHull();
				System.out.println("Hull:\n" + hull);
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
			
			//calculate number of points in file
			int numPoints = 0; //total number of points possible
			while(fileScan.hasNext()) {
				fileScan.nextLine();
				numPoints++;
			}
			if(numPoints > 0) {
				xCoords = new double[numPoints];
				yCoords = new double[numPoints];
			}
			
			//import points into array
			fileScan = new Scanner(new File(fileName));
			for(int index = 0; index < numPoints; index++) {
				xCoords[index] = Double.parseDouble(fileScan.next()) ; //store xCoord of point
				yCoords[index] = Double.parseDouble(fileScan.next()) ; //store yCoord of point
				if(debug) System.out.println("DEBUG: Importing: (" + xCoords[index] + 
						", " + yCoords[index] + ")");
			}
			System.out.println();
		}
		catch(FileNotFoundException exc) {
			System.out.println("Error: " + exc.getMessage() + ".. exiting");
			System.exit(0);
		}
		catch(NumberFormatException exc) {
			System.out.println("Error: " + exc.getMessage() + 
					".. input must be a double");
		}
		
	}
	
	/**************************************************************/
	/* Method: calcConvexHull
	/* Purpose: Find the convex hull of the input
	/* Parameters:
	/* Returns: void
	/**************************************************************/
	public static void calcConvexHull() {
		
		double currA; //a const for current line
		double currB; //b const for current line
		double currC; //c const for current line
		
		double x1; //x coord of first point
		double x2; //x coord of second point
		double x3;//x coord of third point
		double y1; //y coord of first point
		double y2; //y coord of second point
		double y3; //y coord of third point
		
		int greaterPoints = 0; //number of points above line
		int lesserPoints = 0; //number of points bellow line
		int equalPoints = 0; //number of points on line
		
		//iterate over all primary points
		for(int point1 = 0; point1 < xCoords.length; point1++) {
			x1 = xCoords[point1];
			y1 = yCoords[point1];
			
			//iterate over all secondary points
			for(int point2 = 1; point2 < xCoords.length; point2++) {
				x2 = xCoords[point2];
				y2 = yCoords[point2];
				if(debug) System.out.println("DEBUG: Evaluating: (" + x1 + 
						", " + y1 + ")" + " and (" + x2 + ", " + y2 + ")");
				
				//if the points aren't the same exact point
				if(!isSamePoint(point1, point2)) {
					//calculate line between primary and secondary points
					currA = y2 - y1;
					currB = x1 - x2;
					currC = x1*y2 - y1*x2;
					
					if(debug) System.out.println("DEBUG: Current line eqn: " + 
							currA + "x + " + currB + "y = " + currC);
					
					//iterate over all other points
					for(int point3 = 0; point3 < xCoords.length; point3++) {
						x3 = xCoords[point3];
						y3 = yCoords[point3];
						
						//if point is bellow the line, increase lesserPoints
						if(currA*x3 + currB*y3 - currC > 0) {
							lesserPoints++;
						}
						
						//if point is above the line, increase greaterPoints
						else if(currA*x3 + currB*y3 - currC < 0) {
							greaterPoints++;
						}
						
						//else if, the point is on the line, increase equalPoints
						else if(!isSamePoint(point3, point1) && 
								!isSamePoint(point3, point2)) {
							equalPoints++;
						}
					}
					
					if(debug) {
						System.out.println("DEBUG: Points above: " + greaterPoints);
						System.out.println("DEBUG: Points bellow: " + lesserPoints);
						System.out.println("DEBUG: Points on line: " + equalPoints + "\n");
					}
					
					//check to see if all points are on the same side
					if(greaterPoints > 0 && lesserPoints == 0) {
						addEdgeToHull(x1,y1,x2,y2);
					} else if(lesserPoints > 0 && greaterPoints == 0) {
						addEdgeToHull(x1,y1,x2,y2);
					}
					
					//reset number of each point case
					greaterPoints = 0;
					lesserPoints = 0;
					equalPoints = 0;
					
				}
				else {
					if(debug) System.out.println("DEBUG: Found duplicate "
							+ "point.. skipping\n");
				}
			}
		}
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
		if(xCoords[point1] == xCoords[point2] && 
				yCoords[point1] == yCoords[point2]) return true;
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
		if(!hull.contains(x1 + ", " + y1))
			hull += "(" + x1 + ", " + y1 + ")\n";
		
		if(!hull.contains(x2 + ", " + y2))
			hull += "(" + x2 + ", " + y2 + ")\n";
	}
}