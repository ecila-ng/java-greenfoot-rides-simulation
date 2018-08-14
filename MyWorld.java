import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.FileDialog;
import java.io.*;
import java.util.Scanner;

/**
 * The world for Project 3 with a size of 800 x 600
 * 
 * @author Tran Nguyen 
 * @version 0.429
 */
public class MyWorld extends World
{
    public static final int MAX_RIDES=14;  //maximum number of rides possible
    private int currentSize = 0; //current size of the array

    MyRides myRides[]; //an array that contains all the rides
    Person thisLine[]; //an array that contains people in one line

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 800x600 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 

        //To make sure there will only be maximum 14 rides at once
        myRides = new MyRides[MAX_RIDES];

        addObject(new loadButton(), 50,20); //add the load button to the world
        addObject(new mergeButton(), 150,20); //add the merge button to the world
        addObject(new sortByNameButton(), 250,20); //add the sort by name button to the world
        addObject(new sortByQueueLength(), 400,20); //add the sort by queue length button to the world

    }

    /**
     * Load rides into the array
     * 
     */
    public void load()
    {
        //If "load" is clicked when there is already an array of rides...
        if (currentSize != 0)
        {
            for (int i = 0; i < currentSize; i++)
            {
                removeObject(myRides[i]); //remove all the rides in the world
            }
            currentSize = 0; //and set the size of the array back to 0
        }

        removeObjects(getObjects(Person.class)); //also remove all people in line of those rides (if applicable)
        removeObjects(getObjects(addButton.class)); //also remove all add...
        removeObjects(getObjects(releaseButton.class));//...and release buttons

        //Pops up a file dialog for the user to choose 
        FileDialog fd = null;
        fd = new FileDialog(fd, "Choose a file", FileDialog.LOAD); 
        fd.setVisible(true);

        String directory = fd.getDirectory(); // the location of the file
        String name = fd.getFile(); // the name of the file
        String fullName = directory + name; //a complete path to the file

        File rideFile = new File(fullName); //create a new file from the complete path

        Scanner rideReader = null;

        //Return an error message in case the scanner cannot open/load the file
        try 
        {
            rideReader = new Scanner(rideFile);
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("Could not open " + fullName);
            return;
        }

        boolean full = false; //to make sure it stops loading after 14 rides (=full)

        //Scan the file loaded until it ends
        while(rideReader.hasNext() && !full)
        {
            //If there are still spaces in the array...
            if (currentSize < myRides.length) {
                MyRides newRides = new MyRides(rideReader.nextLine()); //create a new ride with the name loaded from the file
                myRides[currentSize] = newRides; //put that new ride into the array of rides (aka rides list)

                //For each ride in the rides list, create an image of text from the ride's name
                GreenfootImage textImage = new GreenfootImage(myRides[currentSize].getName(), 16, Color.BLUE, Color.WHITE);
                myRides[currentSize].setImage(textImage); //then set that image to that ride

                addObject(myRides[currentSize], 80, 65 + 40*currentSize); //finally add it to the world

                currentSize++; //size of the rides list increases by one
            } 
            else //If there is no space left in the array...
            {
                full = true; //oops it's full, stop loading
            }
        }
    }

    /**
     * Add more rides to the array
     * 
     */
    public void merge()
    {
        //Pops up a file dialog for the user to choose
        FileDialog fd = null;
        fd = new FileDialog(fd, "Choose a file", FileDialog.LOAD);
        fd.setVisible(true);

        String directory = fd.getDirectory(); // the location of the file
        String name = fd.getFile(); // the name of the file
        String fullName = directory + name; //a complete path to the file

        File rideFile = new File(fullName); //create a new file from the complete path

        Scanner rideReader = null;

        //Return an error message in case the scanner cannot open/load the file
        try 
        {
            rideReader = new Scanner(rideFile);
        }
        catch (FileNotFoundException fnfe)
        {
            System.out.println("Could not open " + fullName);
            return;
        }

        boolean full = false; //to make sure it stops loading after 14 rides (=full)

        //Scan the file loaded until it ends
        while(rideReader.hasNext() && !full)
        {
            //If there are still spaces in the array...
            if (currentSize < myRides.length) {
                MyRides newRides = new MyRides(rideReader.nextLine()); //create a new ride with the name loaded from the file
                myRides[currentSize] = newRides; //put that new ride into the array of rides (aka rides list)

                //For each ride in the rides list, create an image of text from the ride's name
                GreenfootImage textImage = new GreenfootImage(myRides[currentSize].getName(), 16, Color.BLUE, Color.WHITE);
                myRides[currentSize].setImage(textImage); //then set that image to that ride

                addObject(myRides[currentSize], 80, 65 + 40*currentSize); //finally add it to the world

                currentSize++; //size of the rides list increases by one
            } 
            else //If there is no space left in the array...
            {
                full = true; //oops it's full, stop loading
            }
        }
    } 

    /**
     * Sort the rides list alphabetically
     * 
     */
    public void sortByName()
    {
        boolean swapMade; //to track whether any swaps are made

        do
        {
            swapMade=false; //no swaps was made
            
            //For each ride in the rides list...
            for(int i = 0; i < currentSize-1; i++)
            {
                //compares its name with the name of the rides next to it, then swaps if necessary
                if (myRides[i].getRidesName().compareTo(myRides[i+1].getRidesName()) > 0)
                {
                    MyRides temp = myRides[i];
                    myRides[i] = myRides[i+1];
                    myRides[i+1] = temp;
                    swapMade=true;
                }
            }
        }
        while(swapMade);
        
        redrawArrayContents(); //redraw the array after all swaps have been made
    }

    /**
     * Sort the rides list by its queue length (by the number of people in line)
     * 
     */
    public void sortByQueueLength()
    {
        boolean swapMade; //to track whether any swaps are made

        do
        {
            swapMade=false; //no swaps was made

            //For each ride in the rides list...
            for(int i = 0; i < currentSize-1; i++)
            {
                //compares its current size (or number of people in lines) to its neighbor, then swaps if necessary
                if (myRides[i].getCurrentSize() < myRides[i+1].getCurrentSize())
                {
                    MyRides temp = myRides[i];
                    myRides[i] = myRides[i+1];
                    myRides[i+1] = temp;
                    swapMade=true;
                }
            }
        }
        while(swapMade);

        redrawArrayContents(); //redraw the array after all swaps have been made
    }

    /**
     * Redraw the array after sorting, move objects to new location
     * 
     */
    private void redrawArrayContents()
    {
        //For each ride in the rides list...
        for(int i =0; i <currentSize; i++)
        {
            myRides[i].moveTo(80, 65 + 40*i); //move to the new location
        }
    }
}