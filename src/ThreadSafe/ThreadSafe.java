/* This program shows how to create multiple threads that do not overlap.
/
*/
package ThreadSafe;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadSafe {
    
    private int count = 0;
    
    public synchronized void atomic() {
        count++;
    }       //atomic synchonized class that affects the count variable
            //allows threads to use the count variable, but restricts the 
            //access to one at a time
    
    public static void main (String[] args) {
        ThreadSafe launch = new ThreadSafe();
        launch.fireOff();
    }       //creates a new instance of the fireOff object
    
    public void fireOff() {     //doStuff method to create multiple threads
        
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                
                for(int i=0; i<100 ;i++) {
                    atomic();
                }        
            }
        });     // creates a new thread and loops through the atomic method     
                
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                
                for(int i=0; i<100; i++) {
                    atomic();
                }       
            }
        });     // creates a new thread and loops through the atomic method
        
        t1.start();     // creates thread 1
        t2.start();     //creates thread 2
        
        try {
            t1.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadSafe.class.getName()).log(Level.SEVERE, null, ex);
        }       // allows the program to join the results from thread one for printing later
        
        try {
            t2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadSafe.class.getName()).log(Level.SEVERE, null, ex);
        }       // allows the program to join the results from thread two for printing later
        
        System.out.println("Count is: " + count + " Missiles launched");
        //prints the total count from both threads
    }
}