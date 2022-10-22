import java.util.*;
import java.io.*;

public class Simulation {

   static int currentTime;
   static Scanner inputStream = null;
   static int counter;
   static float sum = 0;

   public static void main(String[] args) {
       // Forms a list of type Event.
       EventList list = new EventList();

       // Forms the queue, representing the bank line. The type is Event.
       ArrayDeque<Event> queue = new ArrayDeque<Event>();

       // InputStream used to read from file.
       Scanner inputStream = null;

       // Represents our text file name.
       String txtFile = "assg8_input.txt";

       // Variables used for the program to function and initialize different values.
       int time;
       int transTime;
       Event newEvent;
       int numPeople = 0;
       double avg;

       // Attempts to open a file, if it fails, the catch method prints an error msg.
       try {

           inputStream = new Scanner(new File(txtFile));

       } catch (FileNotFoundException e) {
           System.out.println("No such file.");
       }

       // If the file is successfully opened, the program continues.
       finally {

           // If the file has another line, the time and transTime is initialized with the
           // integers on the line.
           if (inputStream.hasNextLine()) {
               time = inputStream.nextInt();
               transTime = inputStream.nextInt();
               inputStream.nextLine();

               // The list adds the arrival event to the list with the corresponding time and
               // transTime.
               list.insert(new Event('A', time, transTime));

               // Updates the current time.
               currentTime = list.retrieve().time;

               System.out.println("Simulation Begins");

               counter++;
           }

           /*
            * Performs the processing of the events and continues until the list is empty,
            * meaning all events have finished.
            */
           while (list.isEmpty() == false) {

               newEvent = null;

               // Tries to retrieve the first item in the list. If there is nothing, program
               // exits.
               try {
                   newEvent = list.retrieve();
               }

               // Catch method is used to print the final statistics and catch any exception to
               // exit program.
               catch (IndexOutOfBoundsException e) {
                   System.out.print("\nFinal Statistics: \n");
                   System.out.print("Total number of people processed: " + numPeople + "\n");

                   // Finds the average wait time of the program by dividing sum of wait times by
                   // number of people.
                   avg = sum / numPeople;

                   System.out.printf("Average of time spent waiting:    %.1f", avg);

                   System.exit(0);
               }

               // If the event is an arrival event, call the processArrival method to process
               // the event.
               if (newEvent.checkType()) {
                   processArrival(newEvent, txtFile, list, queue);
                   numPeople++;

               }
               // If the event is not an arrival event, process the departure of the customer.
               else {

                   processDeparture(newEvent, list, queue);

               }

           }

       }

   }

   /**
    * This method is used to process the arrivals of any customers. The method
    * updates the list and the queue by adding the customer to the queue and
    * deleting them from the list.
    * 
    * @param newEvent: Represents the event to be processed.
    * @param txtFile:  This parameter of type String represents the file name.
    * @param list:     Represents the list of events.
    * @param queue:    Represents the queue, which is the bank line.
    */
   public static void processArrival(Event newEvent, String txtFile, EventList list, ArrayDeque<Event> queue) {

       int time;
       int transTime;
       boolean atFront;
       int num = 0;

       System.out.print(newEvent.toString());

       // Checks the bank line status.
       atFront = queue.isEmpty();

       // newEventtime = arrival;

       // Adds the event being processed to the line.
       queue.add(newEvent);

       // Removes the event from the list since it is now in the bank line.
       list.remove();

       /*
        * If the return value of atFront is true, the line is empty, so this event can
        * automatically be processed.
        */

       if (atFront) {

           currentTime = newEvent.time + newEvent.transTime;

           list.insert(new Event('D', currentTime));

       }

       // Tries to open the file to add the next arrival event.
       try {

           inputStream = new Scanner(new File(txtFile));

       }

       catch (FileNotFoundException e) {
           System.out.println("No such file");
       }

       /*
        * If the file has a next line, get that lines arrival event information and add
        * it to the event list.
        */

       if (inputStream.hasNextLine()) {
           while (num < counter)

           {
               inputStream.nextLine();
               num++;

           }

           if (inputStream.hasNextInt()) {
               time = inputStream.nextInt();
               transTime = inputStream.nextInt();
               list.insert(new Event('A', time, transTime));

           }

           counter++;

       }

   }

   /**
    * This method processes a departure event. It removes the event from the queue,
    * meaning that event has finished, and removes an event from the list.
    * 
    * @param departure: Represents the event being processed as a departure.
    * @param list:      Represents the list of events.
    * @param queue:     Represents the bank line
    */
   public static void processDeparture(Event departure, EventList list, ArrayDeque<Event> queue) {

       System.out.print(departure.toString());

       // Removes the arrival from the queue since they have been processed.
       queue.removeFirst();


       // Update the list by removing the event from queue.
       list.remove();

       /*
        * If the bank line is not empty, add the wait time to the sum, update the
        * current time, and add the next events departure information into the event
        * list.
        */

       if (!queue.isEmpty()) {

           sum += currentTime - queue.getFirst().time;
           currentTime = currentTime + queue.getFirst().transTime;

           list.insert(new Event('D', currentTime));

       }

   }

}
