/**
 * Use for constructing usertype of donor and ngo
 * Stores the user login info such as username and password
 */
public class User {
    // DATA FIELDS
    private String username;
    private String password;

    /**
     * Default constructor for User class
     */
    public User() {
    }

    /**
     * Overloaded constructor for user class without password insertion
     * @param username the unique username used in the aid distribution system
     */ 
    public User(String username){
        this.username = username;
    }

    /**
     * Overloaded constructor for user with username and password
     * @param username the unique username used in the aid distribution system
     * @param password the password of user used to login the aid distribution system
     */ 
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Getter of the username of the user in STRING
     * @return the username of the user 
     */
    public String getUsername(){
        return username;
    }

    /**
     * Getter of the password of the user in STRING
     * @return the password of the user
     */
    public String getpassword(){
        return this.password;
    }

    /**
     *  Convert the User class into csv string 
     *  @return only username if password is null. Otherwise, return csv string of username and password
     */
    public String toCSVString() {
        if(password == null)
            return username;
        return username + "," + password;
    }

    /**
     * Return the construction of the admin user type
     * @return the CSV string representation for ADMIN
     */
    public static String REGadmin(){
        return "ADMIN,ADMIN,ADMIN,ADMIN,ADMIN";
    }

}