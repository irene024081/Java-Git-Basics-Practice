import java.io.FileReader;
import java.util.LinkedList;

public class JavaProject {
    public static void main(String[] args){
        String memberStr;
        MembershipManagement management = new MembershipManagement();
        FileHandler fileHandler = new FileHandler();
        LinkedList<Member> members = fileHandler.readFile();
        int choice = management.getChoice();

        while (choice != -1){
            switch (choice){
                case 1:
                    management.addMembers(members);
                    break;
                case 2:
                    management.removeMember(members);
                    fileHandler.overWriteFile(members);
                    break;
                case 3:
                    management.printMemberInfo(members);
                    break;
                default:
                    System.out.println("INVALID INPUT, Please try again");
                    break;
            }
            choice = management.getChoice();
        }
        System.out.println("Task finished. Goodbye");
    }
}
