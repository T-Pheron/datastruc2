import java.util.ArrayList;

public class Project1 {

    public class People {
        String name;
        String surename;
        int[] born;
        int social;
        ArrayList<Integer> PCRTest = new ArrayList<Integer>();

        People(String name, String surename, int[] born, int social) {
            this.name = name;
            this.surename = surename;
            this.born = born;
            this.social = social;
        }
    }

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

    public static void main(String[] args) {
        AVLTree arrayPCR = new AVLTree();
        AVLTree arrayPeople = new AVLTree();

    }

}