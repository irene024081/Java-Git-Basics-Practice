import java.util.LinkedList;
import java.io.*;

public class FileHandler {
    private String filePath = "member.csv";


    public LinkedList readFile(){
        LinkedList members = new LinkedList<>();
        String line;
        String[] lineSplit;
        Member member;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
           line = reader.readLine();
           while (line != null){
               lineSplit = line.split(",");
               String memberType = lineSplit[0];
               int memberID = Integer.parseInt(lineSplit[1]);
               String memberName = lineSplit[2];
               double memberShipFee = Double.parseDouble(lineSplit[3]);
               int clubID = Integer.parseInt(lineSplit[4]);

                if (memberType.equals('S')){
                    member = new SingleClubMember('S',
                            memberID, memberName, memberShipFee, clubID);
                }else if (memberType.equals("M")){
                    member = new SingleClubMember('S',
                            memberID, memberName, memberShipFee, clubID);
                }else {
                    throw new IOException("Wrong member type");
                }
                members.add(member);
                line = reader.readLine();
           }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return members;
    }

    public void appendFile(String mem){
        // add new line to csv file
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))){
            writer.write(mem + "\n");

        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void overWriteFile(LinkedList<Member> member){
        // when remove a member, remove one line from csv
        String s;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("members.tmp", false))){
            for (int i = 0; i < member.size(); i++){
                s = member.get(i).toString();
                writer.write(s + "\n");
            }
            File f = new File(filePath);
            File tempF = new File("members.tmp");
            f.renameTo(tempF);
            tempF.delete();
        }
        catch (IOException e){
            e.getMessage();
        }
    }

}
