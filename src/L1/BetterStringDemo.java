package L1;

public class BetterStringDemo {
    public static void main(String args[]){
        String myName = "Petronius";
        String occupation = "Reorganization Specialist";

        myName += " Arbiter";
        myName += " ";
        myName += "(" + occupation + ")";
        System.out.println("Name = " + myName);

    }
}
