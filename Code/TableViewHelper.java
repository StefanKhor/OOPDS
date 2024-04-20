import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * A class that is created to ease the creation of table in javafx
 * Using javafx Table to show the aids matching of the aid distribution system
 */
public class TableViewHelper{

    /**
     * Default constructor
     */

    TableViewHelper(){};

    
    /**
     * Used to convert the arraylist of AllAid into ObservableArrayList
     * @param AllAidsList All The Aids Lists in the class type of AllAid
     * @return a list of observable list of distributed aids
     */
    public static ObservableList<AllAid> getAllAidTableList(ArrayList<AllAid> AllAidsList){
        return(FXCollections.<AllAid>observableArrayList(AllAidsList));
    }

    /**
     * Used to return the column of table with column name Donor
     * @return tablecolumn format as to display in the table with setted unique property ("donorname")
     */
    public static TableColumn<AllAid,String> getDONORname(){
        TableColumn<AllAid,String> column = new TableColumn<>("Donor");
        PropertyValueFactory<AllAid, String> idCellValueFactory = new PropertyValueFactory<>("donorname");
        column.setCellValueFactory(idCellValueFactory);
        return column;
    }

    /**
     * Used to return the column of table with column name Phone
     * @return tablecolumn format as to display in the table with setted unique property ("phonenum")
     */
    public static TableColumn<AllAid,String> getDONORphone(){
        TableColumn<AllAid,String> column = new TableColumn<>("Phone");
        PropertyValueFactory<AllAid, String> idCellValueFactory = new PropertyValueFactory<>("phonenum");
        column.setCellValueFactory(idCellValueFactory);
        return column;
    }

    /**
     * Used to return the column of table with column name NGO
     * @return tablecolumn format as to display in the table with setted unique property ("ngoname")
     */
    public static TableColumn<AllAid,String> getNGOname(){
        TableColumn<AllAid,String> column = new TableColumn<>("NGO");
        PropertyValueFactory<AllAid, String> idCellValueFactory = new PropertyValueFactory<>("ngoname");
        column.setCellValueFactory(idCellValueFactory);
        return column;
    }

    /**
     * Used to return the column of table with column name Manpower
     * @return tablecolumn format as to display in the table with setted unique property ("manpower")
     */
    public static TableColumn<AllAid,Integer> getNGOmanpower(){
        TableColumn<AllAid,Integer> column = new TableColumn<>("Manpower");
        PropertyValueFactory<AllAid, Integer> manpowerCellValueFactory = new PropertyValueFactory<>("manpower");
        column.setCellValueFactory(manpowerCellValueFactory);
        return column;
    }

    /**
     * Used to return the column of table with column name Aids
     * @return tablecolumn format as to display in the table with setted unique property ("aidname")
     */
    public static TableColumn<AllAid,String> getAIDname(){
        TableColumn<AllAid,String> column = new TableColumn<>("Aids");
        PropertyValueFactory<AllAid, String> idCellValueFactory = new PropertyValueFactory<>("aidname");
        column.setCellValueFactory(idCellValueFactory);
        return column;
    }

    /**
     * Used to return the column of table with column name Quantity
     * @return tablecolumn format as to display in the table with setted unique property ("aidqty")
     */
    public static TableColumn<AllAid,Integer> getAIDqty(){
        TableColumn<AllAid,Integer> column = new TableColumn<>("Quantity");
        PropertyValueFactory<AllAid, Integer> idCellValueFactory = new PropertyValueFactory<>("aidqty");
        column.setCellValueFactory(idCellValueFactory);
        return column;
    }

    /**
     * Used to return the column of table with column name Status
     * @return tablecolumn format as to display in the table with setted unique property ("aidstatus")
     */
    public static TableColumn<AllAid,String> getAIDstatus(){
        TableColumn<AllAid,String> column = new TableColumn<>("Status");
        PropertyValueFactory<AllAid, String> statusCellValueFactory = new PropertyValueFactory<>("aidstatus");
        column.setCellValueFactory(statusCellValueFactory);
        return column;
    }
}