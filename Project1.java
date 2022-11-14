import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Project1 {

    AVLTree arrayPCR = new AVLTree();
    AVLTree arrayPeople = new AVLTree();
    static Scanner keybordProject = new java.util.Scanner(System.in);

    Project1() {
    }

    // Class to store patient information
    public class People {
        String name;
        String surname;
        int[] born;
        int social;
        ArrayList<Integer> PCRTest = new ArrayList<Integer>();

        People(String name, String surname, int[] born, int social) {
            this.name = name;
            this.surname = surname;
            this.born = born;
            this.social = social;
        }

        void addTestNumber(int referenceTest) {
            PCRTest.add(referenceTest);
        }
    }

    // class to store PCR information
    public class PCR {
        int[] date;
        int[] time;
        int socialNumberPatient;
        int reference;
        boolean result;
        String note;

        PCR(int[] date, int[] time, int socialNumberPatient, int reference, boolean result, String note) {
            this.date = date;
            this.time = time;
            this.socialNumberPatient = socialNumberPatient;
            this.reference = reference;
            this.result = result;
            this.note = note;
        }
    }

    // Function used to create a patient
    void createPatient() {
        String name;
        String surname;
        int jjBorn;
        int mmBorn;
        int aaaaBorn;
        int social;

        System.out.println("Please enter the name of patient");
        name = keybordProject.next();
        while (name == null) {
            System.out.println("Error - try again\n Name :");
            name = keybordProject.next();
        }
        System.out.println("Please enter the surname of patient");
        surname = keybordProject.next();
        while (surname == null) {
            System.out.println("Error - try again\n Name :");
            surname = keybordProject.next();
        }

        System.out.println("Date of born :");
        System.out.println("Day :");
        jjBorn = keybordProject.nextInt();
        while (jjBorn < 1 || jjBorn > 31) {
            System.out.println("Error - try again\n Day :");
            jjBorn = keybordProject.nextInt();
        }
        System.out.println("Month :");
        mmBorn = keybordProject.nextInt();
        while (mmBorn < 1 || mmBorn > 12) {
            System.out.println("Error - try again\n Month :");
            mmBorn = keybordProject.nextInt();
        }
        System.out.println("Year :");
        aaaaBorn = keybordProject.nextInt();
        while (aaaaBorn < 1900) {
            System.out.println("Error - try again\n Year :");
            aaaaBorn = keybordProject.nextInt();
        }

        System.out.println("Please enter the social number of patient");
        social = keybordProject.nextInt();
        while (social < 1) {
            System.out.println("Error - try again\n Social number :");
            social = keybordProject.nextInt();
        }

        int[] born = new int[3];
        born[0] = jjBorn;
        born[1] = mmBorn;
        born[2] = aaaaBorn;
        People people = new People(name, surname, born, social);
        arrayPeople.insertValue(social, people);

        System.out.println("The patient has been registered" + "\n\n\n\n");

    }

    // Function used for find a patient
    boolean findPeople(int socialNumberPatient) {
        if (arrayPeople.searchValue(socialNumberPatient) == true) {
            return true;
        } else
            return false;
    }

    // Function used to add un test to a patient
    void addTestToPatient(int socialNumberPatient, int referenceTest) {
        People people = (Project1.People) arrayPeople.getData(socialNumberPatient);
        people.addTestNumber(referenceTest);
        arrayPeople.updateData(socialNumberPatient, people);
    }

    // Function used to create create and add un pcr to a patient
    void addPCRTest() {
        int jjDate;
        int mmDate;
        int aaaaDate;
        int hhTime;
        int mmTime;
        int socialNumberPatient;
        int reference;
        boolean result;
        String note;

        System.out.println("Please enter the PCR test information");
        System.out.println("Date of test :");
        System.out.println("Day :");
        jjDate = keybordProject.nextInt();
        while (jjDate < 1 || jjDate > 31) {
            System.out.println("Error - try again\n Day :");
            jjDate = keybordProject.nextInt();
        }
        System.out.println("Month :");
        mmDate = keybordProject.nextInt();
        while (mmDate < 1 || mmDate > 12) {
            System.out.println("Error - try again\n Month :");
            mmDate = keybordProject.nextInt();
        }
        System.out.println("Year :");
        aaaaDate = keybordProject.nextInt();
        while (aaaaDate < 1900) {
            System.out.println("Error - try again\n Year :");
            aaaaDate = keybordProject.nextInt();
        }
        System.out.println("\nTime of test :");
        System.out.println("Hour :");
        hhTime = keybordProject.nextInt();
        while (hhTime < 0 || hhTime > 23) {
            System.out.println("Error - try again\n Hour :");
            hhTime = keybordProject.nextInt();
        }
        System.out.println("Minute :");
        mmTime = keybordProject.nextInt();
        while (mmTime < 0 || mmTime > 59) {
            System.out.println("Error - try again\n Minute :");
            mmTime = keybordProject.nextInt();
        }

        Random rand = new Random();
        int randNumber = 1000 + rand.nextInt(10000);
        while (arrayPCR.searchValue(randNumber) != false) {
            randNumber = 1000 + rand.nextInt(10000);
        }
        reference = randNumber;

        String resultTest;
        System.out.println("Result of the test : ");
        System.out.println("Positif : Yes or No ?");
        keybordProject.nextLine();
        resultTest = keybordProject.next();
        while (!resultTest.equals("Yes") && !resultTest.equals("yes") && !resultTest.equals("No")
                && !resultTest.equals("no")) {
            System.out.println("Error - try again\n Positif : Yes or No ?");
            resultTest = keybordProject.next();
        }
        if (resultTest.equals("Yes") || resultTest.equals("yes"))
            result = true;
        else
            result = false;

        System.out.println("Insert note on the test :");
        note = keybordProject.next();

        String respond = "try";
        do {
            System.out.println("What is the social number of patient ?");
            socialNumberPatient = keybordProject.nextInt();
            if (findPeople(socialNumberPatient) == true) {
                addTestToPatient(socialNumberPatient, reference);
                respond = "exit";
            } else {
                System.out.println(
                        "The patient is not found in the database. Are you sure about the entry? \nSocial number patient : "
                                + socialNumberPatient);
                System.out.println("Yes - No ?");
                respond = keybordProject.next();
                while (!respond.equals("Yes") && !respond.equals("yes") && !respond.equals("No")
                        && !respond.equals("no")) {
                    System.out.println("Error - Try again");
                    System.out.println("Yes - No ?");
                    respond = keybordProject.next();
                }

                if (respond.equals("No") || respond.equals("no")) {
                    respond = "try";
                } else {
                    createPatient();
                }
            }
        } while (respond == "try");

        int[] date = new int[3];
        date[0] = jjDate;
        date[1] = mmDate;
        date[2] = aaaaDate;
        int[] time = new int[3];
        time[0] = hhTime;
        time[1] = mmTime;
        PCR pcr = new PCR(date, time, socialNumberPatient, reference, result, note);
        arrayPCR.insertValue(reference, pcr);

        System.out.println("\n\nThe test was well recorded! Reference number : " + reference + "\n\n\n\n");

    }

    // Function used to search and show PCR information
    void searchPCR() {
        int reference;
        System.out.println("Please enter the PCR test reference :");
        reference = keybordProject.nextInt();
        while (arrayPCR.searchValue(reference) == false && reference != 0) {
            System.out.println(
                    "The test reference was not found in the database, please re-order or enter 0 to quit :");
            reference = keybordProject.nextInt();
        }
        if (reference == 0)
            reference = 1;
        else {
            viewPCR(reference);
        }

    }

    // Function used to show all PCR test of a patient
    void viewAllPCRofPatient() {
        int socialNumber;
        System.out.println("Please enter the social number of patient :");
        socialNumber = keybordProject.nextInt();
        while (arrayPeople.searchValue(socialNumber) == false && socialNumber != 0) {
            System.out.println(
                    "The social number was not found in the database, please re-order or enter 0 to quit :");
            socialNumber = keybordProject.nextInt();
        }
        if (socialNumber == 0)
            socialNumber = 1;
        else {
            viewPeople(socialNumber);
            viewPCRofPatient(socialNumber);
        }

    }

    // Function to show informations of PCR test
    void viewPCR(int reference) {
        PCR pcr = (Project1.PCR) arrayPCR.getData(reference);
        System.out.println("\n\n\n\n" + "Reference of the test : " + pcr.reference);
        System.out.println("Date : " + pcr.date[0] + "/" + pcr.date[1] + "/" + pcr.date[2]);
        System.out.println("Time : " + pcr.time[0] + ":" + pcr.time[1]);
        System.out.println("Result : " + pcr.result);
        System.out.println("Note : " + pcr.note);
        viewPeople(pcr.socialNumberPatient);
    }

    // Function used to show information of PCR test for a search witch a patient
    void viewPCRofPatient(int socialNumber) {
        People people = (Project1.People) arrayPeople.getData(socialNumber);
        for (int i = 0; i < people.PCRTest.size(); i++) {
            PCR pcr = (Project1.PCR) arrayPCR.getData(people.PCRTest.get(i));
            if (pcr != null)
                System.out.println("Reference of the test : " + pcr.reference + " - Result : " + pcr.result);
        }
        if (people.PCRTest.size() == 0)
            System.out.println("No test for this patient");
        System.out.println("\n\n\n\n");
    }

    // Function used to swhow information of a patient
    void viewPeople(int socialNumber) {
        People people = (Project1.People) arrayPeople.getData(socialNumber);
        System.out.println("\n\n\n\n" + "Information of people :");
        System.out.println("Name : " + people.name);
        System.out.println("Surname : " + people.surname);
        System.out.println("Date of born: " + people.born[0] + "/" + people.born[1] + "/" + people.born[2]);
        System.out.println("Social number :" + people.social + "\n\n\n\n");
    }

    // Function used to delete a PCR test
    void deleteTest() {
        int reference;
        System.out.println("Please enter the reference of the test which must be delete :");
        reference = keybordProject.nextInt();
        while (arrayPCR.searchValue(reference) == false && reference != 0) {
            System.out.println(
                    "The reference test was not found in the database, please re-order or enter 0 to quit :");
            reference = keybordProject.nextInt();
        }
        if (reference == 0)
            reference = 1;
        else {
            PCR pcr = (Project1.PCR) arrayPCR.getData(reference);
            deleteTestInPatient(pcr.socialNumberPatient, reference);
            arrayPCR.deleteValue(reference);
            System.out.println("Test delete !" + "\n\n\n\n");
        }

    }

    // Function used to delete test in infomation of patient
    void deleteTestInPatient(int socialNumber, int referenceTest) {
        People people = (Project1.People) arrayPeople.getData(socialNumber);
        for (int i = 0; i < people.PCRTest.size(); i++) {
            if (referenceTest == people.PCRTest.get(i)) {
                people.PCRTest.remove(i);
                break;
            }
        }
        arrayPeople.updateData(socialNumber, people);
    }

    // Function used to delete people and test of people
    void deletePeople() {
        int socialNumber;
        System.out.println("Please enter the social number of the patient which must be delete :");
        socialNumber = keybordProject.nextInt();
        while (arrayPeople.searchValue(socialNumber) == false && socialNumber != 0) {
            System.out.println(
                    "The reference test was not found in the database, please re-order or enter 0 to quit :");
            socialNumber = keybordProject.nextInt();
        }
        if (socialNumber == 0)
            socialNumber = 1;
        else {
            People people = (Project1.People) arrayPeople.getData(socialNumber);
            for (int i = 0; i < people.PCRTest.size(); i++) {
                arrayPCR.deleteValue(people.PCRTest.get(i));
            }
            arrayPeople.deleteValue(socialNumber);
            System.out.println("People delete !" + "\n\n\n\n");
        }

    }

    // Function used to create patient for test
    void patientForTest() {
        int[] born = new int[3];
        born[0] = 12;
        born[1] = 12;
        born[2] = 1990;
        People people1 = new People("Hul", "Kilo", born, 123456);
        arrayPeople.insertValue(123456, people1);

        int[] born1 = new int[3];
        born1[0] = 12;
        born1[1] = 10;
        born1[2] = 2020;
        People people2 = new People("Charle", "Julie", born1, 7412369);
        arrayPeople.insertValue(7412369, people2);
    }

    // Function used to create patient for test
    void testForTest() {
        int[] date = new int[3];
        date[0] = 12;
        date[1] = 10;
        date[2] = 2022;
        int[] time = new int[2];
        time[0] = 12;
        time[1] = 01;
        PCR pcr1 = new PCR(date, time, 123456, 1420, false, "Test 1");
        arrayPCR.insertValue(1420, pcr1);
        addTestToPatient(123456, 1420);

        int[] date1 = new int[3];
        date1[0] = 12;
        date1[1] = 10;
        date1[2] = 2022;
        int[] time1 = new int[2];
        time1[0] = 02;
        time1[1] = 22;
        PCR pcr2 = new PCR(date, time, 123456, 5523, false, "Test 2");
        arrayPCR.insertValue(5523, pcr2);
        addTestToPatient(123456, 5523);
    }

    public static void main(String[] args) {

        Project1 project1 = new Project1();
        boolean statuWhile = true;
        project1.patientForTest();
        project1.testForTest();

        do {

            System.out.println("Welcome to the Centrum of Health PCR Test Registration System");
            System.out.println("Please enter the number of what you want to do :");

            System.out.println("1 - Insert a PCR test result");
            System.out.println("2 - Find the test result");
            System.out.println("3 - List all PCR tests for a given patient");
            System.out.println("4 - Register a patient");
            System.out.println("5 - Delete a PCR test");
            System.out.println("6 - Delete a patient");
            System.out.println("7 - Close app");
            int number = keybordProject.nextInt();
            while (number < 1 || number > 7) {
                System.out.println("Error - The number entered is not an option  ");
                System.out.println("Please enter the number of what you want to do :");

                System.out.println("1 - Insert a PCR test result");
                System.out.println("2 - Find the test result");
                System.out.println("3 - List all PCR tests for a given patient");
                System.out.println("4 - Register a patient");
                System.out.println("5 - Delete a PCR test");
                System.out.println("6 - Delete a patient");
            }

            switch (number) {
                case 1:
                    project1.addPCRTest();
                    break;
                case 2:
                    project1.searchPCR();
                    break;
                case 3:
                    project1.viewAllPCRofPatient();
                    break;
                case 4:
                    project1.createPatient();
                    break;
                case 5:
                    project1.deleteTest();
                    break;
                case 6:
                    project1.deletePeople();
                    break;
                case 7:
                    statuWhile = false;
                    break;
            }
        } while (statuWhile == true);

    }

}