import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

    private int getIntInput(){
        int choice = 0;
        while(choice == 0)
        {
            try{
                System.out.println("Please enter an integer other than 0");
                choice = reader.nextInt();
                if(choice == 0){
                    reader.nextLine();
                    throw new InputMismatchException("Entered 0, Please enter an integer other than 0");
                }
            }
            catch (InputMismatchException e){
                reader.nextLine();
                System.out.println("Error: INVALID INPUT. Please try again");
            }
        }
        return choice;
    }
}
