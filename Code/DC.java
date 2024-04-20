import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * A distribution center representing the type of matching of aids donated and received from the donors and NGOs
 * Type of aids matching consists of 1-to-1, 1-to-many, many-to-1, many-to-many
 */
public class DC {

    /**
     * Default constructor
     */

    DC(){};

    
    final private static String userfile = "src/user.csv";
    final private static String AidsFile = "src/aids.csv";

    /** To return the arraylist of aid  where it stores all the aids donated and received, including those havent distributed, will show as "-"
    * @param username is used to display the aids for selected users
    * ADMIN is used to display the global view of DC (ALL THE MATCHINGS OF AIDS INCLUDING DONOR AND NGO)
    * @return the list of all aids from the aids.csv file
    */
    public static ArrayList<AllAid> getALLaidsfromcsv(String username){
        // item[0] is username of donors and item[3] is username of ngo 
        // item[1] is name of donor and item[2] is phone number of donors
        // item[4] is name of ngo and item[5] is manpower of ngo
        // items[6] is aids , item[7] is quantity , item [8] is aid status

        ArrayList<AllAid> AllAids = new ArrayList<>();

        // read aids.csv into a list of lines.
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(AidsFile));

            for (int i = 0; i < lines.size(); i++) {

                String[] items = lines.get(i).split(",");
                if(username.equals("ADMIN")){
                        Integer quantity = Integer.parseInt(items[7]);
                        Integer manpower = Integer.parseInt(items[5]);
                        AllAid a = new AllAid(new Donor(items[0],items[1],items[2]),new NGO(items[3],items[4],manpower), items[6],quantity,items[8]);
                        AllAids.add(a);
                        }

                else{
                    if(items[0].equals(username) || items[3].equals(username)){
                        int quantity = Integer.parseInt(items[7]);
                        Integer manpower = Integer.parseInt(items[5]);
                        AllAid a = new AllAid(new Donor(items[0],items[1],items[2]),new NGO(items[3],items[4],manpower), items[6],quantity,items[8]);
                        AllAids.add(a);
                    }
                }
            }
        }
         catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Occured");
        }

        return AllAids;
    }

    /**
     * Return arraylist of allaid  for the completed matching aid
     * @return all the completed  for the completed matching aid
     */
    public static ArrayList<AllAid> getcompletedaidsfromcsv(){
        // item[0] is username of donors and item[3] is username of ngo 
        // item[1] is name of donor and item[2] is phone number of donors
        // item[4] is name of ngo and item[5] is manpower of ngo
        // items[6] is aids , item[7] is quantity , item [8] is aid status

        ArrayList<AllAid> AllAids = new ArrayList<>();

        // read aids.csv into a list of lines.
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(AidsFile));

            for (int i = 0; i < lines.size(); i++) {

                String[] items = lines.get(i).split(",");
                if(!(items[0].equals("-") || items[1].equals("-") || items[2].equals("-") || 
                        items[3].equals("-") || items[4].equals("-") || items[5].equals("-"))){
                        Integer quantity = Integer.parseInt(items[7]);
                        Integer manpower = Integer.parseInt(items[5]);
                        AllAid a = new AllAid(new Donor(items[0],items[1],items[2]),new NGO(items[3],items[4],manpower), items[6],quantity,items[8]);
                        AllAids.add(a);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Occured");
        }
        return AllAids;
    }


    /**
     * Return the ArrayList OF DonatedAid that store the donated aids received from the donors
     * @return the aids donated by the donors
     */
    public static ArrayList<DonatedAid> getDONATEDaidsfromcsv(){
        // item[0] is username of donors and item[3] is username of ngo 
        // item[1] is name of donor and item[2] is phone number of donors
        // item[4] is name of ngo and item[5] is manpower of ngo
        // items[6] is aids , item[7] is quantity

        ArrayList<DonatedAid> AllAids = new ArrayList<>();

        // read aids.csv into a list of lines.
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(AidsFile));

            for (int i = 0; i < lines.size(); i++) {
                String[] items = lines.get(i).split(",");
                // if no ngo is detected ("-")
                if((items[3].equals("-") || items[4].equals("-") || items[5].equals("-"))){
                    int quantity = Integer.parseInt(items[7]);
                    DonatedAid a = new DonatedAid(new Donor(items[0],items[1],items[2]), items[6],quantity);
                    AllAids.add(a);
                }
            }}
         catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Occured");
        }
        return AllAids;
    }

    /**
     * Return the ArrayList of RequestAid that store the list of aids requested by the NGOs
     * @return the list of aids requested by the NGOs
     */
    public static ArrayList<RequestedAid> getREQaidsfromcsv(){
        // item[0] is username of donors and item[3] is username of ngo 
        // item[1] is name of donor and item[2] is phone number of donors
        // item[4] is name of ngo and item[5] is manpower of ngo
        // items[6] is aids , item[7] is quantity

        ArrayList<RequestedAid> AllAids = new ArrayList<>();

        // read aids.csv into a list of lines.
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(AidsFile));

            for (int i = 0; i < lines.size(); i++) {
                String[] items = lines.get(i).split(",");
                // if no donor is detected ("-")
                if((items[0].equals("-") || items[1].equals("-") || items[2].equals("-"))){
                    int quantity = Integer.parseInt(items[7]);
                    Integer manpower = Integer.parseInt(items[5]);
                    RequestedAid a = new RequestedAid(new NGO(items[3],items[4],manpower),items[6],quantity);
                    AllAids.add(a);
                }
            }}
         catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Occured");
        }
        return AllAids;
    }


    /**
     * Return the hashmap of the information of the donors user account
     * @return the hashmap of the donors which key is username(unique) and value is Donor class
     */
    public static HashMap<String, Donor> getdonorfromcsv() {
        HashMap<String, Donor> donors = new HashMap<>();

        // read user.csv into a list of lines.
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(userfile));

            for (int i = 0; i < lines.size(); i++) {

                String[] items = lines.get(i).split(",");

                // CHECK IF THE USER IS DONOR
                if (items[0].equals("DONOR")) {
                    // items[0] is USERTYPE, items[1] is USERNAME, items[2] is PASSWORD , item[3] is
                    // NAME ,item[4] is PHONE NUMBER

                    Donor d = new Donor(items[1], items[2], items[3], items[4]);
                    donors.put(items[1], d);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Occured");
        }
        return donors;
    }

    /**
     * Return the hashmap of the information of the ngo user account
     * @return the hashmap of the ngos which key is username(unique) and value is NGO class
     */
    public static HashMap<String, NGO> getNGOfromcsv() {
        HashMap<String, NGO> ngos = new HashMap<>();
        // read user.csv into a list of lines.
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(userfile));
            for (int i = 0; i < lines.size(); i++) {
                String[] items = lines.get(i).split(",");
                // CHECK IF THE USER IS DONOR
                if (items[0].equals("NGO")) {
                    // items[0] is USERTYPE, items[1] is USERNAME, items[2] is PASSWORD , item[3] is
                    // NAME ,item[4] is manpower
                    Integer manpower = Integer.parseInt(items[4]);
                    NGO d = new NGO(items[1], items[2], items[3], manpower);
                    ngos.put(items[1], d);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Occured");
        }
        return ngos;
    }

    /**
     * Return the hashmap of the information of the ngo user account
     * @return the hashmap of the ngos which key is NGO name(unique) and value is NGO class
     */
    public static HashMap<String, NGO> getNGOnameandcountfromcsv() {
        HashMap<String, NGO> ngos = new HashMap<>();
        // read user.csv into a list of lines.
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(userfile));
            for (int i = 0; i < lines.size(); i++) {
                String[] items = lines.get(i).split(",");
                // CHECK IF THE USER IS DONOR
                if (items[0].equals("NGO")) {
                    // items[0] is USERTYPE, items[1] is USERNAME, items[2] is PASSWORD , item[3] is
                    // NAME ,item[4] is manpower
                    Integer manpower = Integer.parseInt(items[4]);
                    NGO d = new NGO(items[1], items[2], items[3], manpower);
                    ngos.put(items[3], d);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Occured");
        }
        return ngos;
    }

    /**
     * Used to check if the name of donor or ngo that is gonna register has duplicated name in the user.csv
     * @return the hashset of the ngos and donors name
     */
    public static HashSet<String> getallnamefromcsv() {
        HashSet<String> names = new HashSet<>();

        // read user.csv into a list of lines.
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(userfile));
            for (int i = 0; i < lines.size(); i++) {
                String[] items = lines.get(i).split(",");
                // CHECK IF THE USER IS DONOR
                names.add(items[3]);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Occured");
        }
        return names;
    }
    /**
     * To update the new registered user (donors or NGO) into the user.csv file
     * Any update is done is stored into the hashmap and the hashmaps are converted into csv strings
     * @param ngos the information of the NGO (hashmap form)
     * @param donors the information of the donors (hashmap form)
     * @throws IOException to declare an exception
     */
    public static void RegNewUser(Map<String, NGO> ngos,Map<String, Donor> donors) throws IOException {
        // create a stringbuilder and add all the entries inside
        StringBuilder tempsb = new StringBuilder();
        for(var entry : ngos.entrySet()){
            tempsb.append(entry.getValue().toCSVString() + "\n");
        }
        for(var entry : donors.entrySet()){
            tempsb.append(entry.getValue().toCSVString() + "\n");
        }
        tempsb.append(User.REGadmin());
        Files.write(Paths.get(userfile),tempsb.toString().getBytes());
    }

    /**
     * To update the latest arraylist of AllAid into aid.csv file
     * @param new_aids_list arraylist of AllAid (including the newly added aid)
     * @throws IOException to declare an execption
     */
    public static void updateaid(ArrayList<AllAid> new_aids_list) throws IOException {
        StringBuilder tempsb = new StringBuilder();
        for(int i = 0;i < new_aids_list.size();i++)
            tempsb.append(new_aids_list.get(i).toCSVString() + "\n");
        Files.write(Paths.get(AidsFile),tempsb.toString().getBytes());
    }

    /**
     * To match the donor and NGO who are donating or requesting the same name(type) and quantity of aids.
     * Match aids 1-to-1 (1 donor to 1 NGO)
     * The aids after matched are deleted and a completed matching aid is produced
     */
    public static void one_to_one(){
        ArrayList<RequestedAid> Request = DC.getREQaidsfromcsv();
        ArrayList<DonatedAid> Donate = DC.getDONATEDaidsfromcsv();
        ArrayList<AllAid> new_aids = DC.getcompletedaidsfromcsv();

        for(int r = 0;r < Request.size();r++){
            for(int d = 0;d < Donate.size();d++){
                if((Request.get(r).getAidname().equals(Donate.get(d).getAidname()) &&
                    Request.get(r).getQuantity().equals(Donate.get(d).getQuantity()))){
                        Donate.get(d).setqty(0);
                        new_aids.add(new AllAid(Donate.get(d).getDonor(),
                                                Request.get(r).getNgo(),
                                                Request.get(r).getAidname(),
                                                Request.get(r).getQuantity(),
                                                "Reserved"));
                        Request.get(r).setqty(0);
                }
            }
        }
        update_after_match(Request,Donate,new_aids);
    
    }

    /**
     * To match a ngo with fews donors if their(donor) quantity of aid donated have not reached the quantity of aid ngo requested
     * with condition that the aid donated and requested is same
     * Match aids many-to-1 (many donors to 1 NGO)
     * The aids after matched are deleted if aid quantity is finished(0) and completed matching aids is produced
     */
    public static void many_to_one(){
        ArrayList<RequestedAid> Request = DC.getREQaidsfromcsv();
        ArrayList<DonatedAid> Donate = DC.getDONATEDaidsfromcsv();
        ArrayList<AllAid> new_aids = DC.getcompletedaidsfromcsv();

        for(int r = 0;r < Request.size();r++){
            for(int d = 0;d < Donate.size();d++){
                // break loop if finished matching ,then will goes to next donated aid
                if(Request.get(r).getQuantity().equals(0))
                    break;

                if(Request.get(r).getAidname().equals(Donate.get(d).getAidname()) &&
                    Donate.get(d).getQuantity()  <= Request.get(r).getQuantity() &&
                    !Donate.get(d).getQuantity().equals(0)){

                        Request.get(r).setqty(Request.get(r).getQuantity()-Donate.get(d).getQuantity());
                        new_aids.add(new AllAid(Donate.get(d).getDonor(),
                                                Request.get(r).getNgo(),
                                                Donate.get(d).getAidname(),
                                                Donate.get(d).getQuantity(),
                                                "Reserved"));
                        Donate.get(d).setqty(0);
                    }
                }
            }
        update_after_match(Request,Donate,new_aids);

    }
    
    
    /**
     * To match a donor with fews ngo if their(ngo) quantity of aid requested have not reached the quantity of aid donor donated
     * with condition that the aid donated and requested is same
     * Match aids 1-to-many (1 donor to many NGOs)
     * The aids after matched are deleted if aid quantity is finished(0) and completed matching aid is produced
     */
    public static void one_to_many(){
        ArrayList<RequestedAid> Request = DC.getREQaidsfromcsv();
        ArrayList<DonatedAid> Donate = DC.getDONATEDaidsfromcsv();
        ArrayList<AllAid> new_aids = DC.getcompletedaidsfromcsv();

        for(int d = 0;d < Donate.size();d++){
            for(int r = 0;r < Request.size();r++){
                // break loop if finished matching ,then will goes to next donated aid
                if(Donate.get(d).getQuantity().equals(0))
                    break;

                if(Request.get(r).getAidname().equals(Donate.get(d).getAidname()) &&
                    Donate.get(d).getQuantity()  >= Request.get(r).getQuantity() &&
                    !Request.get(r).getQuantity().equals(0)){

                        Donate.get(d).setqty(Donate.get(d).getQuantity()-Request.get(r).getQuantity());
                        new_aids.add(new AllAid(Donate.get(d).getDonor(),
                                                Request.get(r).getNgo(),
                                                Donate.get(d).getAidname(),
                                                Request.get(r).getQuantity(),
                                                "Reserved"));
                        Request.get(r).setqty(0);
                    }
            }
        }
    update_after_match(Request,Donate,new_aids);
    }

    /**
     * To match fews donor with fews ngo for the condition whereby they requested and donated the same aid name
     * Match aids many-to-many (many donors to many NGOs)
     * The aids after matched are deleted if aid quantity is finished(0) and completed matching aid is produced
     */
    public static void many_to_many(){
        ArrayList<RequestedAid> Request = DC.getREQaidsfromcsv();
        ArrayList<DonatedAid> Donate = DC.getDONATEDaidsfromcsv();
        ArrayList<AllAid> new_aids = DC.getcompletedaidsfromcsv();

        for(int r = 0; r < Request.size(); r++){
            for(int d = 0;d < Donate.size();d++){
                if(Request.get(r).getAidname().equals(Donate.get(d).getAidname()) 
                    && !Donate.get(d).getQuantity().equals(0)
                    && !Request.get(r).getQuantity().equals(0)){
                    Integer count = 0;
                    if(Request.get(r).getQuantity() >= Donate.get(d).getQuantity()){
                        count = Donate.get(d).getQuantity();
                        Request.get(r).setqty(Request.get(r).getQuantity()-Donate.get(d).getQuantity());
                        Donate.get(d).setqty(0);
                    }
                    else if(Request.get(r).getQuantity() < Donate.get(d).getQuantity()){
                        count = Request.get(r).getQuantity();
                        Donate.get(d).setqty(Donate.get(d).getQuantity()-Request.get(r).getQuantity());
                        Request.get(r).setqty(0);
                    }
                    new_aids.add(new AllAid(Donate.get(d).getDonor(),
                                            Request.get(r).getNgo(),
                                            Donate.get(d).getAidname(),
                                            count,"Reserved"));
                    }
                }
            }
        update_after_match(Request,Donate,new_aids);
    }
    
    /**
     * Update the aids.csv file after matching(1 to 1, 1 to many, many to 1, many to many)
     * @param Request the arraylist of RequestedAid that havent finish matching (null donor)
     * @param Donate the arraylist of DonatedAid that havent finish matching (null ngo)
     * @param new_aids the arraylist of AllAid that completed matching (no null ngo or null donor occur)
     * The aids after matched are deleted if aid quantity is finished(0) and completed matching aid is produced
     */
    public static void update_after_match(ArrayList<RequestedAid> Request,ArrayList<DonatedAid> Donate,ArrayList<AllAid> new_aids){
        for(int i=0; i<Request.size(); i++) {
            if(!Request.get(i).getQuantity().equals(0)){
                new_aids.add(Request.get(i).converttoAllAid());
            }
        }
        
        for(int i=0; i<Donate.size(); i++) {
            if(!Donate.get(i).getQuantity().equals(0)){
                new_aids.add(Donate.get(i).converttoAllAid());
            }
        }

        try {
            updateaid(new_aids);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Used to update the aid status from "Reserved" to "Completed" of the selected ngo
     * @param name name of ngo inserted by user
     */
    public static void update_collected_aid_status_for(String name){
        ArrayList<RequestedAid> Request = DC.getREQaidsfromcsv();
        ArrayList<DonatedAid> Donate = DC.getDONATEDaidsfromcsv();
        // get all aids that has been completed matching
        ArrayList<AllAid> allaid = DC.getcompletedaidsfromcsv();

        for(int i = 0; i < allaid.size(); i++) {
            if(allaid.get(i).getNgoname().equals(name)){
                allaid.get(i).setaidstatus("Collected");
            }
        }
        update_after_match(Request,Donate,allaid);
    }

    /**
     * Used to check if the ngo inserted is available in the matched aids csv file
     * @param name name of ngo inserted by user
     * @return true if the ngo name do not found in the matched aid of aids csv file, false if the ngo name found in the matched aid of aids csv file
     */
    public static boolean check_ngo_name_exists(String name){
        ArrayList<AllAid> allaid = DC.getcompletedaidsfromcsv();
        
        // exists
        for(int i = 0; i < allaid.size(); i++) {
            if(allaid.get(i).getNgoname().equals(name)){
                return false;
            }
        }
        // not exists
        return true;
    }

    /**
     * Used to check if the ngo name has all completed aids 
     * @param name name of ngo inserted by user
     * @return true if the all the aid of the selected ngo is under status "Completed", false if the there is some aid status of the selected ngo is "Reserved"
     */
    public static boolean check_if_all_completed_aid(String name){
        ArrayList<AllAid> allaid = DC.getcompletedaidsfromcsv();
        
        // exists
        for(int i = 0; i < allaid.size(); i++) {
            if(allaid.get(i).getNgoname().equals(name) && allaid.get(i).getAidstatus().equals("Reserved")){
                return false;
            }
        }
        // not exists
        return true;
    }

}