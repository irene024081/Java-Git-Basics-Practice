import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);

    private int getIntInput() {
        int choice = 0;
        while (choice == 0) {
            try {
                choice = reader.nextInt();
                reader.nextLine();
                if (choice == 0) {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                reader.nextLine();
                System.out.println("Error: INVALID INPUT. Please try again");
            }
        }
        return choice;
    }

    public void printClubOptions() {
        System.out.println("1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multi Club");
    }

    public int getChoice() {
        System.out.println("WELCOME TO OZONE FITNESS CENTER");
        System.out.println("===============================");
        System.out.println("1) Add Member");
        System.out.println("2) Remove Member");
        System.out.println("3) Display Member Information");
        System.out.println();
        System.out.println("Please select an option (or Enter -1 to quit)");
        int choice = getIntInput();
        return choice;
    }

    public String addMembers(LinkedList<Member> members){
        String name;
        int club;
        String returnMemberStr;
        double fees;
        int memberID;
        Member member;

        Calculator<Integer> cal;
        System.out.println("Please enter the member name");
        name = reader.nextLine();

        printClubOptions();
        System.out.println("Please enter the club ID");
        club = getIntInput();
        while (club < 1 || club > 4){
            System.out.println("The value you entered is invalid, please try again");
            club = getIntInput();
        }

        if (members.size() > 0){
            memberID = members.getLast().getMemberID() + 1;
        }
        else{
            memberID = 1;
        }

        if (club != 4){
            cal = (n) -> {
                switch (n){
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                    default:
                        return -1;
                }
            };
            fees = cal.calculateFees(club);
            member = new SingleClubMember('S',memberID,name,fees,club);
            members.add(member);
            returnMemberStr = member.toString();
            System.out.println("\nSTATUS: Single Club Member added\n");

        }
        else{
            cal = (n) -> {
                switch (n){
                    case 4:
                        return 1200;
                    default:
                        return -1;
                }
            };
            fees = cal.calculateFees(club);
            member = new MultiClubMember('M',memberID,name,fees,club);
            members.add(member);
            returnMemberStr = member.toString();
            System.out.println("\nSTATUS: Multi Club Member added\n");
        }
        return returnMemberStr;
    }

    public void removeMember(LinkedList<Member> members){
        int memberID;
        System.out.println("Please enter the member id to be removed");
        memberID = getIntInput();
        for (int i = 0; i < members.size(); i++){
            if (members.get(i).getMemberID() == memberID){
                members.remove(i);
                System.out.println("Member " + memberID + " has been removed");
                return;
            }
        }
        System.out.println("Member " + memberID + " is not found");
    }

    public void printMemberInfo(LinkedList<Member> members){
        int memberID;

        System.out.println("Please enter the member id");
        memberID = getIntInput();
        for (int i = 0; i < members.size(); i++){
            System.out.println(members.get(i).getMemberID());
            if (members.get(i).getMemberID() == memberID){
                String[] memberInfo = members.get(i).toString().split(",");
                if (memberInfo[0] == "S"){
                    System.out.println("Member Type = " + memberInfo[0]);
                    System.out.println("Member ID = " + memberInfo[1]);
                    System.out.println("Member Name = " + memberInfo[2]);
                    System.out.println("Membership Fees = " + memberInfo[3]);
                    System.out.println("Club ID = " + memberInfo[4]);
                }
                else if (memberInfo[0] == "M"){
                    System.out.println("Member Type = " + memberInfo[0]);
                    System.out.println("Member ID = " + memberInfo[1]);
                    System.out.println("Member Name = " + memberInfo[2]);
                    System.out.println("Membership Fees = " + memberInfo[3]);
                    System.out.println("Membership Points = " + memberInfo[4]);
                }
            }
        }

    }


}
