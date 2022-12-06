package to_do_list;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try {

            boolean running = true;

            System.out.println("ooooooooooooo                   oooooooooo.                  ooooo         o8o               .");
            System.out.println("8'   888   `8                   `888'   `Y8b                 `888'         `\"'             .o8");
            System.out.println("     888       .ooooo.           888      888  .ooooo.        888         oooo   .oooo.o .o888oo");
            System.out.println("     888      d88' `88b          888      888 d88' `88b       888         `888  d88(  \"8   888");
            System.out.println("     888      888   888 8888888  888      888 888   888       888          888  `\"Y88b.    888");
            System.out.println("     888      888   888          888     d88' 888   888       888       o  888  o.  )88b   888 . ");
            System.out.println("    o888o     `Y8bod8P'         o888bood8P'   `Y8bod8P'      o888oooood8 o888o 8\"\"888P'   \"888\"");

            //initialize arrays
            ArrayList<String> taskTitle = new ArrayList<>(); //array taskTitle
            ArrayList<Date> taskDue = new ArrayList<>(); //array taskDue
            ArrayList<String> taskDescription = new ArrayList<>(); //array taskDescription
            ArrayList<Boolean> taskCompleted = new ArrayList<>(); //array taskCompleted
            //Just lets the input and output work... date in a better form
            ArrayList<String> dateFormat = new ArrayList<>();

            //write data into arrays from file when opening
            File taskIn = new File("task_list.txt");        //title
            Scanner readTask = new Scanner(taskIn);
            while (readTask.hasNextLine()) {
                taskTitle.add(readTask.nextLine());
            }
            File descIn = new File("desc_list.txt");        //description
            Scanner readDesc = new Scanner(descIn);
            while (readDesc.hasNextLine()) {
                taskDescription.add(readDesc.nextLine());
            }
            File dueIn = new File("due_list.txt");          //due date
            Scanner readDue = new Scanner(dueIn);
            while (readDue.hasNextLine()) {
                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(readDue.nextLine());
                taskDue.add(date);
            }
            File dateDueIn = new File("due_list.txt");       //due date but ... different
            Scanner readDate = new Scanner(dateDueIn);
            while (readDate.hasNextLine()){
                dateFormat.add(readDate.nextLine());
            }
            File completeIn = new File("complete_list.txt");    //completed
            Scanner readComplete = new Scanner(completeIn);
            while (readComplete.hasNextLine()) {
                taskCompleted.add(Boolean.valueOf(readComplete.nextLine()));
            }
            //close everything up
            readTask.close();
            readDesc.close();
            readDue.close();
            readComplete.close();

            String completeYN = null;

            while (running) { // Running = True until case 6 selected

                //Header Output
                System.out.println("\nPlease make a selection:");
                System.out.println("\n1.Create New Task");
                System.out.println("2.Display All Tasks");
                System.out.println("3.Edit Task");
                System.out.println("4.Mark task as Completed");
                System.out.println("5.Remove a Task");
                System.out.println("6.Exit");

                Scanner input = new Scanner(System.in);
                int selection = input.nextInt();  //take in selection

                if (selection < 7 && selection > 0) {

                    switch (selection) {
                        case 1: //create new task

                            //header for new task
                            System.out.println("\n-----------------");
                            System.out.println("Creating New Task");
                            System.out.println("-----------------");

                            System.out.println("Enter a title:");
                            input.nextLine();
                            String taskTitleIn = input.nextLine(); //take input into taskTitle
                            taskTitle.add(taskTitleIn);// pass taskTitle to newTask as taskTitle(into array)

                            System.out.println("Enter the date due (DD-MM-YYYY):");
                            String dateIn = input.nextLine();
                            String myDate = dateIn; //testing
                            dateFormat.add(myDate);
                            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateIn);
                            taskDue.add(date); // pass date to taskDue array

                            System.out.println("Enter description:");
                            String taskDescriptionIn = input.nextLine(); //take input into taskDescription
                            taskDescription.add(taskDescriptionIn);  // pass taskDescription to newTask as taskDescription(into array)

                            taskCompleted.add(false); //sets completed flag to false

                            //footer for new task
                            System.out.println("-----------------\n");
                            break;

                        case 2: //display all tasks
                            System.out.println("Please select a task:");

                            for (int i = 0; i < taskTitle.size(); i++) { //loop until end of the array
                                int number = i + 1;
                                System.out.println(number + ". " + taskTitle.get(i));
                            }
                            int taskSelect = input.nextInt(); //task title input
                            taskSelect = taskSelect - 1; //decrements input by 1 so it gets correct array value

                            //change boolean values to Yes/No
                            if (!taskCompleted.get(taskSelect)) {
                                completeYN = "Working on it...";
                            } else if (taskCompleted.get(taskSelect)) {
                                completeYN = "Complete";
                            }
                            //task output
                            System.out.println("\n----------------------");
                            System.out.println(taskTitle.get(taskSelect));
                            System.out.println("----------------------");
                            System.out.println("Due: " + taskDue.get(taskSelect));
                            System.out.println("Status: " + completeYN);
                            System.out.println("Description: " + taskDescription.get(taskSelect));
                            System.out.println("----------------------\n\n");
                            break;

                        case 3: //edit task
                            System.out.println("\n\n\nEdit a Task"); //header for editing new task
                            System.out.println("Please Select a Task:");

                            for (int i = 0; i < taskTitle.size(); i++) { //loop until end of the array
                                int number = i + 1;
                                System.out.println(number + ". " + taskTitle.get(i));
                            }

                            int titleIn = input.nextInt();

                            System.out.println("You have selected: " + taskTitle.get(titleIn - 1));
                            System.out.println("What would you like to edit? \n1.Title\n2.Due Date\n3.Description");

                            int editIn = input.nextInt();

                            switch (editIn) {
                                case 1://edit title
                                    System.out.println("Current title: " + taskTitle.get(titleIn - 1));
                                    System.out.println("Please enter new title: ");
                                    input.nextLine();
                                    taskTitleIn = input.nextLine(); //take input into taskTitle
                                    taskTitle.set(titleIn - 1, taskTitleIn);// pass taskTitle to newTask as taskTitle(into array)
                                    break;

                                case 2://edit due date
                                    System.out.println("Current Due Date: " + taskDue.get(titleIn - 1));
                                    System.out.println("Please enter new Due Date( DD-MM-YYYY):");
                                    input.nextLine();
                                    dateIn = input.next(); //take input into taskDescription
                                    Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(dateIn);
                                    taskDue.set(titleIn - 1, date2);// pass taskDescription to newTask as taskDescription(into array
                                    dateFormat.set(titleIn -1, dateIn);

                                    break;

                                case 3://edit description
                                    System.out.println("Current Description: " + taskDescription.get(titleIn - 1));
                                    System.out.println("Please enter new Description: ");
                                    input.nextLine();
                                    taskDescriptionIn = input.nextLine(); //take input into taskDescription
                                    taskDescription.set(titleIn - 1, taskDescriptionIn);  // pass taskDescription to newTask as taskDescription(into array
                                    break;
                            }
                            break;

                        case 4:  //mark a task as complete
                            System.out.println("\n\n\nMark task as Completed"); //header for create new task
                            System.out.println("Please Select a Task:");

                            for (int i = 0; i < taskTitle.size(); i++) { //loop until end of the array
                                int number = i + 1;
                                System.out.println(number + ". " + taskTitle.get(i));
                            }

                            int completeSelect = input.nextInt();
                            taskCompleted.set((completeSelect - 1), true); //set value to true
                            System.out.println(taskTitle.get(completeSelect - 1) + " marked as completed!\n\n\n\n");
                            break;

                        case 5: //remove a task
                            System.out.println("\n\n\nRemove a Task"); //header for create new task
                            System.out.println("Please Select a Task:");

                            for (int i = 0; i < taskTitle.size(); i++) { //loop until end of the array
                                int number = i + 1;
                                System.out.println(number + ". " + taskTitle.get(i));
                            }

                            taskSelect = input.nextInt();
                            System.out.println(taskTitle.get(taskSelect - 1) + " has been deleted!\n\n");

                            taskTitle.remove(taskSelect - 1);
                            taskDue.remove(taskSelect - 1);
                            taskCompleted.remove(taskSelect - 1);
                            taskDescription.remove(taskSelect - 1);
                            break;

                        case 6: //exit

                            //output array values to text files
                            FileWriter taskOut      = new FileWriter("task_list.txt");
                            FileWriter dueOut       = new FileWriter("due_list.txt");
                            FileWriter completeOut  = new FileWriter("complete_list.txt");
                            FileWriter descOut      = new FileWriter("desc_list.txt");

                            int upperNumber = taskTitle.size();
                            for (int i = 0; i < taskTitle.size(); i++) { //loop until end of the array
                                taskOut.write (taskTitle.get(i) + "\n");
                                dueOut.write (dateFormat.get(i) + "\n");
                                completeOut.write (taskCompleted.get(i) + "\n");
                                descOut.write (taskDescription.get(i) + "\n");
                            }
                            //close files
                            taskOut.close();
                            dueOut.close();
                            completeOut.close();
                            descOut.close();

                            running = false;  // will end the program.
                            break;
                    }
                } else { //if number is outside range 1-6 is chosen output "Bad Input" and loop back
                    System.out.println("Bad Input");
                }
            }
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
