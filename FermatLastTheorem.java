//Fermat's Last Theorem
//FermatLastTheorem.jar
//FermatLastTheorem.java, Parameters.java, missingOutput.txt
//After running FermatLastTheorem.java, the results will be sent to the missingOutput.txt
//Vanitha Sannaobappa
//vanithasannaobappa@lewisu.edu
//Software Engineering, CPSC 60500, Sections 3
//Nov 11, 2022
//This program searches for "near misses" for Fermat's last theorem

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FermatLastTheorem {        //main class

    public static void main(String[]args){
        Scanner ui = new Scanner(System.in);      //user input
        try{
            FileWriter outputFile = new FileWriter("missingOutput.txt");     //creating a separate file for the output
            System.out.println("--------------------------------------------------------------------------------------");
            System.out.println("You will enter the value for N and K. \nN is the value for the power to use in the equation and K is the limit for the x and y possibilities. ");
            System.out.println("\nEnter a number greater than 2 and less than 12 for N: ");

            //get the user input for n and k and print errors in case the user enters the wrong digits using while loops.
            int n = ui.nextInt();
            while (n <= 2 || n > 12) {
                System.out.print("Error: Enter a number greater than 2 and less than 12 for N: ");
                n = ui.nextInt();
            }

            System.out.println("Enter the value for K: ");
            int k = ui.nextInt();
            while (k<=10) {
                System.out.print("Error: Enter a number greater than 10 for K: ");
                k = ui.nextInt();
            }

            for(int x=10;x<k;x++){                      //for loop to calculate the results for near misses
                for(int y=10;y<k;y++){
                    int xPOWOfn =(int) Math.pow(x, n);
                    int yPOWOfn =(int) Math.pow(y, n);
                    //findZ();
                    Parameters p = findZ(n, xPOWOfn, yPOWOfn);
                    if(thisIsANearMiss(p.z,xPOWOfn,yPOWOfn,n)){
                        printNearMisses(p.z,xPOWOfn,yPOWOfn,n,outputFile,p.r);
                    }

                }//for y
            }//for x
            outputFile.close();
        }catch(IOException e){            //catching exceptions for the file
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        ui.close();
    }//main

    static Parameters findZ(int n,int xPOWOfn,int yPOWOfn){       //method used to find the appropriate z value for the x,y Parametersing
        Parameters res;
        int z,diff1=0,diff2=0;
        for(z=2;Math.pow(z,n)<(xPOWOfn + yPOWOfn);z++){     //this loop is used to find the closest z value
            if(Math.pow(z+1,n)>(xPOWOfn + yPOWOfn)){
                diff1 =(xPOWOfn+yPOWOfn) - (int) Math.pow(z,n);
                diff2 = (int) Math.pow(z+1,n)-(xPOWOfn+yPOWOfn);
                if(diff1<=diff2)return res = new Parameters(z, getRForZ(z,xPOWOfn, yPOWOfn,n));
                if(diff1>diff2)return res = new Parameters(z+1, getRForZPlusOne(z,xPOWOfn, yPOWOfn,n));
            }
        }
        return new Parameters(z, getRForZ(z,xPOWOfn, yPOWOfn,n));
    }

    static boolean thisIsANearMiss(int z, int x,int y, int n){      //method to see if there is a any near miss
        if(z == 2)return false;

        return true;
    }

    static void printNearMisses(int z, int x,int y,int n,FileWriter file,double r){
        //this method will print any near misses to the file (missingOutput.txt)
        try{
            file.write("\nX: "+(int)Math.pow(x, 1.0 / n)+ " Y: "+(int)Math.pow(y, 1.0 / n)+" Z: "+z+" RELATIVE MISS: "+ r);
        }catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }//catch
    }

    static Double getRForZ(int z,int xPOWOfn, int yPOWOfn,int n){       //method used to get R value for Z
        return (xPOWOfn + yPOWOfn) -(Math.pow(z,n));

    }

    static Double getRForZPlusOne(int z,int xPOWOfn, int yPOWOfn,int n){  //method used to get R value for Z+1
        return (Math.pow(z,n)) - (xPOWOfn + yPOWOfn);

    }


}
