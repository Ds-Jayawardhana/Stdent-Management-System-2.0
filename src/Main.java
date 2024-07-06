import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Initilaizing a string to print the whole menu without using If condition sone by one//
        String prn_op = """
                *************************
                *      Menu Section     *
                *************************
                1. Check available seats
                2. Register student (with ID)
                3. Delete student
                4. Find student (with student ID)
                5. Store student details into a file
                6. Load student details from the file to the system
                7. View the list of students based on their names
                8. Add Student name and Module Marks
                0. Enter 0 to exit the programme
                ****************************************************
                """;
        //Initializing the Scanner in the main method to get inputs throughout the programme//
        Scanner scan = new Scanner(System.in);
        //Defining a 2D Array with 2 rows and 100 columns//
        String[][] students = new String[2][100];
        Student[] studentObjects = new Student[100];

        while (true) {
            System.out.println(prn_op);
            System.out.print("Enter an option to execute: ");
            String option = scan.next();
            scan.nextLine();

            //Initialaixing Switch to run Main menu one by one//
            switch (option) {
                case "1":
                    System.out.println("----------------Check available seats--------------------");
                    System.out.println();
                    check_Seats(students);
                    break;
                case "2":
                    System.out.println("-----------------Register student (with ID)----------------");
                    System.out.println();
                    register(students, scan);
                    break;
                case "3":
                    System.out.println("----------------------Delete Student------------------------");
                    System.out.println();
                    dlt_students(students, scan);
                    break;
                case "4":
                    System.out.println("-----------------------Find Student--------------------------");
                    System.out.println();
                    find_student(students, scan);
                    break;
                case "5":
                    System.out.println("------------------------Store students-------------------------");
                    System.out.println();
                    store_students(students);
                    break;
                case "6":
                    System.out.println("--------------------------Load Students-------------------------");
                    System.out.println();
                    load_file(students);
                    break;
                case "7":
                    System.out.println("------------------------View the student by name------------------");
                    System.out.println();
                    stud_sort(students);
                    break;
                case "8":
                    System.out.println("----------------Add student name and module marks------------------");
                    System.out.println();
                    mod_marks(students, scan, studentObjects);
                    break;
                case "0":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option please enter number between 1 to 8");
                    System.out.println();
                    break;
            }
        }
    }

    /*Programe iterates thorugh the 2D array and cehcks the null values exsisting in the array and count them
    to get the number of available seats to register.*/

    private static void check_Seats(String[][] students) {
        int count = 0;
        for (int l = 0; l < students.length; l++) {//iterate through out the colums
            for (int j = 0; j < students[l].length; j++) {//itereate throughout the rows
                if (students[l][j] == null) {
                    count = count + 1;
                }


            }
        }
        System.out.println("The Number of Seats Available is" + " " + (count / 2));
    }

    /*Programe iterate throught the 2D array find the firstnull values to replace it with student details
      and Show if there is no any avaiable seats
    */

    private static void register(String[][] students, Scanner scan) {
        int availableSeats = 0;//initilazing the number of available seats
        for (int l = 0; l < students.length; l++) {
            for (int j = 0; j < students[l].length; j++) {
                if (students[l][j] == null) {
                    availableSeats++;//incrementing the number of available seats
                }
            }
        }
        if (availableSeats < 1) {//checking is there avaialable seats
            System.out.print("Sorry there are no Seats Available");

        } else {
            String answer = "yes";
            while (!answer.equals("no")) {
                System.out.println("Enter the Student ID Number(W2083055)");//Asking user to enter the student ID
                String Student_ID = scan.next();
                if (!(Student_ID.length() == 8) && !Student_ID.startsWith("w")) {//Verifying the user Entered ID is a correct one or not
                    System.out.println("Student ID Must Contain 8 Characters & Must Start with W");
                    return;
                }
                /*Initializing already exsist boolean to false to check
                 user enterd details alredy exsisting in the array */
                boolean alreadyexsist = false;
                for (int i = 0; i < students[0].length; i++) {//iterating throught the 0 th index in array columns


                    /*This if condition execute only if array row is not null and
                     if user enterd details in the array row data
                     */
                    if (students[0][i] != null && students[0][i].equals(Student_ID)) {
                        alreadyexsist = true;//if user enterd details are in the array make the alreadyexsist boolean to true
                    }
                }

                if (alreadyexsist) {
                    System.out.println("Student Already Exsisting");

                } else {//this runs if user entered details are not already exsisting
                    System.out.println("Enter the name of the Student");
                    String Student_Name = scan.next();
                        /*This included variable use to chcek wheather there is any data in the array rows and columns
                         befor saving user inputed data into the 2d array */
                    boolean included = false;//initilazing the boolean include as false
                    for (int l = 0; l < students.length && !included; l++) {
                        for (int j = 0; j < students[l].length; j++) {
                            if (students[l][j] == null) {//check if the row is null
                                students[0][j] = Student_ID;//Assinging student id to array row data 1
                                students[1][j] = Student_Name;// Assinging student name to array row data 2
                                included = true;//make included variable into true
                                break;

                            }

                        }

                    }
                }
                System.out.println("Do you want to add more student Details(yes/no)");
                answer = scan.next().toLowerCase();//Converting use inputed data into lowercase
                while (!answer.equals("yes") && !answer.equals("no")) {
                    System.out.println("Please enter only 'yes' or 'no':");
                    answer = scan.next().toLowerCase();
                }
            }
        }
    }

    private static void find_student(String students[][], Scanner scan) {
        System.out.print("Enter the student Id(W2083055) :-");
        String stud_Id = scan.next().toLowerCase();

        try {
            for (int l = 0; l < students.length; l++) {//iterate thorught the columns of the array
                for (int j = 0; j < students[l].length; j++) {// iterating through the rows of the array

                    if (students[0][j].equals(stud_Id)) {//chcek if user enterd student id equals to data in the row
                        System.out.println("-----------------------------");
                        System.out.println("Student Id:-" + students[0][j]);
                        System.out.println("Name:-" + students[1][j]);
                        System.out.println("-----------------------------");
                        System.out.println();
                        return;
                    }

                }

            }
            /*If there is a null value in the row programme crashes with NulPointerException error
             *By handling that exception user can easily knows there is no data in that row
             */
        } catch (NullPointerException e) {
            System.out.println("Student Not Found,Please try again");//catch the nulpointerexception and prnt user to there is no any data
        }
    }
    /*Defining a method to store user entered student details into a txt file.
      Without saving in a file if a user enters data it will automaaticaly erase the data from RAM and
      can't rerieve again when user open the programme again.By saving to file it allows user to save
      student details in the ROM of the device
     */

    private static void store_students(String students[][]) {
        try {
            File file = new File("std_dtls.txt");//defining the path name to progame

            boolean file_created = file.createNewFile();//creating a new file
            if (file_created) {
                System.out.println("File Created" + file.getName());//Print user that file succefuly Created

            } else {
                if (file.exists()) {//chcek if the file already exsist in the directory
                    System.out.println("File Already Exsisting");
                } else {
                    /*Displayin error if tthere is any error while creating the file */
                    System.out.println("There is an error When Creating the" + file.getName() + "File");
                }
            }
        } catch (IOException e) {//catch IOEXCEPTIONS
            e.printStackTrace();
        }
        try (FileWriter fileWriter = new FileWriter("std_dtls.txt")) {//defining file writer object

            for (int j = 0; j < students[0].length; j++) {//Iterating throught the array
                if (students[0][j] != null) {
                    fileWriter.write(students[0][j] + "," + students[1][j] + "\n");//Writing student details in the array to the .txt file


                }
            }
            System.out.println("Successfully Data Saved");

        } catch (IOException e) {
            System.out.println(students);
            e.printStackTrace();
        }

    }


    /*Loading data from a file to the array of the programme to
      if there is any previously saved student details*/

    private static void load_file(String students[][]) {
        try {

            File file = new File("std_dtls.txt");//defining the path name to the programme
            if (file.exists()) {
                Scanner file_reader = new Scanner(file);//Defining file reader object
                int i = 0;
                while (file_reader.hasNextLine()) {//going throught the lines in file until there are lines
                    /*file reader going throught the lines in the file and
                     save it to text variable line by line */
                    String text = file_reader.nextLine();
                    String[] dtls = text.split(",");//split the line by the character ","
                    if (dtls.length == 2 && i < students[0].length) {//cehcek if tthe splited dtls varaib;e
                        students[0][i] = dtls[0];//assigning to rows in the array
                        students[1][i] = dtls[1];
                        i++;


                    } else if (i >= students[0].length) {
                        System.out.println("Data does not loaded to file");

                    }
                }
                file_reader.close();//Closing the file reader object
                System.out.println("Student Data Loaded Successfully");

            } else {
                System.out.println("The file" + file.getName() + "not existing");
            }
        } catch (IOException e) {
            System.out.println("Error While Loading The file");
            e.printStackTrace();
        }

    }

    /*Sorting the Student details which enterd by the user by the name of the student
      in this method we are using Bubble sort algorithm to sort out the name of the students in
      alphabatical order
     */
    private static void stud_sort(String students[][]) {
        int count = 0;//defining a variable to get the count of the non null data in the array

        for (int l = 0; l < students[0].length; l++) {//Iterating throught the 0 th index of the array row
            if (students[0][l] != null) count++;//chcek if that rows are null & incrment the count by one
            else {
                System.out.println("There is no Any Student Details");
                break;
            }
        }


        for (int l = 0; l < count - 1; l++) {//run the loop when l is than the count
            for (int j = 0; j < count - 1 - l; j++) {
                /*comparing the data in a the column with other column
                  first programme ignores the case if the letters to compare
                  */
                if (students[1][j].compareToIgnoreCase(students[1][j + 1]) > 0) {

                    String tempName = students[1][j];//defininng the temp name to assing the row data
                    students[1][j] = students[1][j + 1];//assining the one after rowe data in the other column to previous one
                    students[1][j + 1] = tempName;//assining the data with temp name

                    //Swapping the student ID like with relavant to student names
                    String tempId = students[0][j];
                    students[0][j] = students[0][j + 1];
                    students[0][j + 1] = tempId;
                }
            }
        }


        System.out.println("------------------------");
        for (int i = 0; i < count; i++) {
            //Priting student names after sorting by the names
            System.out.println("Student Name: " + students[1][i]);
            System.out.println("Student ID: " + students[0][i]);
            System.out.println("------------------------");
        }
    }

    /*In the delete method user can enter the student id of the student which want to delte
     programme find out the the correct column of the array which relavant to the user entred Student Id
     and replace it null */
    private static void dlt_students(String students[][], Scanner scan) {
        System.out.println("Enter the the student Id to Delete");
        String stud_Id = scan.next().toLowerCase();
        if (!(stud_Id.length() == 8) && !stud_Id.startsWith("w")) {//Verifying the user Entered ID is a correct one or not
            System.out.println("Student ID Must Contain 8 Characters & Must Start with W");
            return;
        }
        //conveing the user enterd thee string tto lowercase
        for (int l = 0; l < students[0].length; l++) {//iterating throught the 0 th data row

            //If condition chceks if there is any data and user entered is exsisting to replace with null(delete data)
            if (students[0][l] != null && students[0][l].equals(stud_Id)) {
                students[0][l] = null;
                students[1][l] = null;
                System.out.println("Students details with " + stud_Id + "Deleted");
                System.out.println();
                return;
            }

        }
        //if there is no any student details Programme details user to there is no student details found

        System.out.println("Student Not Found Try again");
        System.out.println();

    }



    private static void mod_marks(String[][] students, Scanner scan, Student[] studentObjects) {
            int counts = 0;
            System.out.println("Enter a. or b. ");
            System.out.println("a. Add Student Name");
            System.out.println("b. Module marks 1,2 and 3");

            String ans = scan.next().toLowerCase();

            if (ans.equals("a")) {
                System.out.println("Enter the Student ID to add name");
                String studentID = scan.next();
                for (int i = 0; i < students[0].length; i++) {
                    if (students[0][i] != null && students[0][i].equals(studentID)) {
                        System.out.println("Enter the Student Name");
                        String studentName = scan.next();
                        Student newStudent = new Student(studentName, studentID);
                        studentObjects[counts] = newStudent;
                        counts++;
                        students[1][i] = studentName;
                        System.out.println("Student Name Updated");
                        System.out.println(newStudent.getName());
                        return;
                    }
                }

                System.out.println("Student Not Found");
            } else if (ans.equals("b")) {
                System.out.println("Enter the Student ID to add marks");
                String studentID = scan.next();
                for (Student student : studentObjects) {
                    if (student != null && student.getStudentID().equals(studentID)) {
                        int index = indexOf(studentObjects, studentID);
                        Module[] modules = new Module[3];
                        for (int k = 0; k < 3; k++) {
                            System.out.println("Enter marks for module " + (k + 1) + ":");
                            Double mark = scan.nextDouble();
                            Module module = new Module();
                            module.setMarks(mark);
                            modules[k] = module;
                        }
                        student.setModules(modules);
                        System.out.println("Marks of the Student Added Successfully");
                        return;
                    }
                }
                System.out.println("Student Not Found");
            }else if (ans.equals("c")) {
                    printAllStudentMarks(studentObjects);

            } else {
                System.out.println("Enter Correct input a or b");
            }
        }

        public static int indexOf(Student[] arr, String target) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != null && arr[i].getStudentID().equals(target)) {
                    return i; // Return index if found
                }
            }
            return -1;
        }
    private static void printAllStudentMarks(Student[] studentObjects) {
        boolean studentsFound = false;
        for (Student student : studentObjects) {
            if (student != null) {
                studentsFound = true;
                System.out.println("Student: " + student.getName() + " (ID: " + student.getStudentID() + ")");
                Module[] modules = student.getModules();
                for (int i = 0; i < modules.length; i++) {
                    if (modules[i] != null) {
                        System.out.println("  Module " + (i + 1) + ": " + modules[i].getMarks());
                    } else {
                        System.out.println("  Module " + (i + 1) + ": No marks entered");
                    }
                }
                System.out.println("  Average: " + student.Average());
                System.out.println("  Grade: " + student.calcGrade());
                System.out.println();
            }
        }
        if (!studentsFound) {
            System.out.println("No students found in the system.");
        }
    }
    }



