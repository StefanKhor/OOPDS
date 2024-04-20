import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Main class for the javafx application program
 * GUI for Aid Distribution System
 * Inherited from the Application superclass
 */
public class Main extends Application {

    // DECLARE TO BE ABLE TO SWITCH SCENE
    private Stage currentstage;

    // constants
    private final String csssheet = "src/font.css";
    private final String logosrc = "src/icon.png";

    // list of users in hashmap
    private Map<String, Donor> DONORmap = DC.getdonorfromcsv();
    private Map<String, NGO> NGOmap = DC.getNGOfromcsv();
    private ArrayList<AllAid> AllAidsList = DC.getALLaidsfromcsv("ADMIN");

    // update functions
    private void updateuserfile() {
        DONORmap = DC.getdonorfromcsv();
        NGOmap = DC.getNGOfromcsv();
    }

    // update all the aids into allaidslist (no matter nullngo or nulldonor)
    private void updateaidfile() {
        AllAidsList = DC.getALLaidsfromcsv("ADMIN");
    }


    // Override the start method in the Application class
    @Override

    ////////////////////////////////// Starting of the stage
    ////////////////////////////////// ////////////////////////////////////////////////////////////////////
    /**
     * To start the stage of the javafx application
     */
    public void start(Stage primaryStage) throws Exception {

        // ASSIGN THE STAGE TO THE CURRENTSTAGE OUTSITE THE FUNCTION
        currentstage = primaryStage;

        // icon for application top left and menu bar
        Image icon = new Image(logosrc);
        primaryStage.getIcons().add(icon);

        // Scene scene1 = loginscene();
        Scene scene1 = setupScene(firstdcscene());

        // title of Scene
        primaryStage.setTitle("AID DISTRIBUTION SYSTEM");
        primaryStage.sizeToScene();
        primaryStage.setScene(scene1);
        primaryStage.show();

    }

    ////////////////////////////////// LOGIN SCENE
    ////////////////////////////////// ////////////////////////////////////////////////////////////////////

    /**
     * To create a gridpane scene that can obtain information which are username and
     * password
     * The event handler of funcbutton can redirect the user to respective user
     * scene (donor / ngo)
     * 
     * @param function 1 is for login scene , 2 is for register scene
     * @return Gridpane of login or register scene
     */
    public GridPane REGLOGscene(int function) {
        // 1 is for login screen
        // 2 is for register screen
        // Create a gridpane with properties
        GridPane gpane = new GridPane();
        gpane.setPadding(new Insets(12, 13, 14, 15));
        // gpane.setHgap(6);
        // gpane.setVgap(6);
        // gpane.setAlignment(Pos.CENTER);

        // Nodes inside pane
        Text msg = new Text();

        Label lusername = new Label("Username   ");
        Label lpassword1 = new Label("Password   ");
        Label lpassword2 = new Label("Password Confirmation   ");
        Label result = new Label("");

        TextField username_field = new TextField();
        TextField password1_field = new PasswordField();
        TextField password2_field = new PasswordField();

        Button RETURNBUTTON = new Button("Return");
        Button FUNCBUTTON = new Button("");

        GridPane.setHalignment(msg, HPos.CENTER);
        GridPane.setHalignment(RETURNBUTTON, HPos.CENTER);
        GridPane.setHalignment(FUNCBUTTON, HPos.CENTER);
        GridPane.setHalignment(result, HPos.CENTER);

        Alert alert = new Alert(AlertType.NONE);

        FUNCBUTTON.setMaxSize(200, 100);
        RETURNBUTTON.setMaxSize(200, 100);

        gpane.add(msg, 0, 0, 2, 1);

        // login screen
        if (function == 1) {
            msg.setText("Login");

            gpane.add(lusername, 0, 1);
            gpane.add(username_field, 1, 1);

            gpane.add(lpassword1, 0, 2);
            gpane.add(password1_field, 1, 2);

            // login button
            FUNCBUTTON.setText("Login");

            gpane.add(RETURNBUTTON, 0, 3);
            gpane.add(FUNCBUTTON, 1, 3);

            // login message label
            gpane.add(result, 0, 4, 2, 2);

            // LOGIN FUNCTION
            FUNCBUTTON.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    if (username_field.getText().equals(""))
                        result.setText("Username Is Empty");
                    else if (password1_field.getText().equals(""))
                        result.setText("Password Is Empty");
                    else if (!(NGOmap.containsKey(username_field.getText())
                            || DONORmap.containsKey(username_field.getText())))
                        result.setText("Username Do Not Exist");

                    else if (!userpass().equals(password1_field.getText()))
                        result.setText("Password Incorrect");
                    else if (getmap().equals("NGO")) {
                        alert.setAlertType(AlertType.INFORMATION);
                        alert.setContentText("NGO Account Login Successful");
                        alert.show();
                        currentstage.setScene(setupScene(DONORNGOscene(username_field.getText(), "NGO")));
                    }

                    else if (getmap().equals("DONOR")) {
                        alert.setAlertType(AlertType.INFORMATION);
                        alert.setContentText("DONOR Account Login Successful");
                        alert.show();
                        currentstage.setScene(setupScene(DONORNGOscene(username_field.getText(), "DONOR")));
                    }
                    currentstage.sizeToScene();
                }

                public String userpass() {
                    if (NGOmap.containsKey(username_field.getText()))
                        return NGOmap.get(username_field.getText()).getpassword();
                    else if (DONORmap.containsKey(username_field.getText()))
                        return DONORmap.get(username_field.getText()).getpassword();
                    return " ";
                }

                public String getmap() {
                    if (NGOmap.containsKey(username_field.getText()))
                        return "NGO";
                    else if (DONORmap.containsKey(username_field.getText()))
                        return "DONOR";
                    return " ";
                }
            });
        }

        else if (function == 2) {
            msg.setText("Register");

            // Add toggle feature for register to the gui
            ToggleGroup REGgroup = new ToggleGroup();
            ToggleButton REGdonor = new ToggleButton("Donor");
            ToggleButton REGngo = new ToggleButton(" NGO ");

            REGngo.setToggleGroup(REGgroup);
            REGdonor.setToggleGroup(REGgroup);

            REGngo.setMaxSize(200, 100);
            REGdonor.setMaxSize(200, 100);

            REGgroup.selectToggle(REGdonor);
            gpane.add(REGdonor, 0, 1);
            gpane.add(REGngo, 1, 1);
            GridPane.setHalignment(REGdonor, HPos.CENTER);
            GridPane.setHalignment(REGngo, HPos.CENTER);

            gpane.add(lusername, 0, 2);
            gpane.add(username_field, 1, 2);

            gpane.add(lpassword1, 0, 3);
            gpane.add(password1_field, 1, 3);

            // col, row

            gpane.add(lpassword2, 0, 4);
            gpane.add(password2_field, 1, 4);

            // details of the registration

            // name for both ngo and donors
            Label detail1 = new Label("Name ");
            TextField detail1_field = new TextField();
            gpane.add(detail1, 0, 5);
            gpane.add(detail1_field, 1, 5);

            // manpower for ngo and phone number for user
            Label detail2 = new Label("Phone Number");
            TextField detail2_field = new TextField();
            gpane.add(detail2, 0, 6);
            gpane.add(detail2_field, 1, 6);

            // login button
            FUNCBUTTON.setText("Register");
            gpane.add(RETURNBUTTON, 0, 7);
            gpane.add(FUNCBUTTON, 1, 7);

            // login message label
            gpane.add(result, 0, 8, 2, 2);

            REGgroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle toggled) {
                    if (toggled != null) {
                        String toggleBtn = ((ToggleButton) toggled).getText();
                        if (toggleBtn.equals("Donor")) {
                            detail2.setText("Phone Number");
                        }

                        else if (toggleBtn.equals(" NGO ")) {
                            detail2.setText("Manpower Count");
                        }
                    }
                }
            });

            // Register FUNCTION
            FUNCBUTTON.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {

                    HashSet<String> nameset = DC.getallnamefromcsv();
                    ToggleButton selectedRadioButton = (ToggleButton) REGgroup.getSelectedToggle();
                    String toggled = selectedRadioButton.getText();

                    if (username_field.getText().equals(""))
                        result.setText("Username Is Empty");
                    else if (password1_field.getText().equals("") || password2_field.getText().equals(""))
                        result.setText("Password Is Empty");
                    else if (!password1_field.getText().equals(password2_field.getText()))
                        result.setText("Password Confirmation Incorrect");
                    else if (detail1_field.getText().equals(""))
                        result.setText("Name Is Empty");
                    else if (nameset.contains(detail1_field.getText().toUpperCase()))
                        result.setText("Duplicated "+ toggled + " Name Is Found");
                    else if (detail2_field.getText().equals(""))
                        result.setText(detail2.getText() + " Is Empty");
                    else if (DONORmap.containsKey(username_field.getText())
                            || NGOmap.containsKey(username_field.getText()))
                        result.setText("Username Exist");
                    else if (username_field.getText().equals("") || password1_field.getText().equals("-") ||
                            detail1_field.getText().equals("-") || detail2_field.getText().equals("-"))
                        result.setText("Invalid Input. (-) is not Allowed");
                    else {

                        alert.setAlertType(AlertType.INFORMATION);

                        try {
                            Integer count = Integer.parseInt(detail2_field.getText());
                            if(count <=0 )
                                result.setText(detail2.getText() + " should be greater than 0");
                            // register take place
                            else{
                                if (toggled.equals("Donor")) {
                                    DONORmap.put(username_field.getText(), new Donor(username_field.getText(),
                                            password1_field.getText(), detail1_field.getText().toUpperCase(), detail2_field.getText()));

                                    alert.setContentText("Registration for Donor successful !!!");
                                } 
                                
                                else if (toggled.equals(" NGO ")) {

                                    NGOmap.put(username_field.getText(), new NGO(username_field.getText(),
                                            password1_field.getText(), detail1_field.getText().toUpperCase(), count));
                                    alert.setContentText("Registration for  NGO  successful !!!");
                                
                                }


                                try {
                                    DC.RegNewUser(NGOmap, DONORmap);
                                    updateuserfile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                alert.show();
                                currentstage.setScene(setupScene(firstdcscene()));
                                currentstage.sizeToScene();
                            }
                        } catch (Exception e) {
                            if (toggled.equals("Donor"))
                                result.setText("Only number is allowed for Phone Number");
                            else if (toggled.equals(" NGO "))
                                result.setText("Only number is allowed for Manpower Count");
                        }
                    }
                }
            });
        }

        // Return to main
        RETURNBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                currentstage.setScene(setupScene(firstdcscene()));
                currentstage.sizeToScene();
            }
        });
        return gpane;
    }

    ////////////////////////////////// DONOR, NGO and DC Table SCENE
    ////////////////////////////////// /////////////////////////////////

    /**
     * To create a VBox for construction the scene for distribution center (DC)
     * Including a table that can view all the aids matching (no matter nullngo or
     * nulldonor)
     * 
     * @return VBox layout for distribution center (DC)
     */
    public VBox viewDCscene() {
        updateaidfile();

        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);

        Label label = new Label("Distribution Center");

        Label label1 = new Label("NOTE: \n' - ' in Donor or NGO refers to aids that have not been matched yet\n' - ' in status refers to required aids requested by NGO\n ");

        Button RETURNBUTTON = new Button("Return");
        RETURNBUTTON.setMaxSize(200, 100);

        // Return to main
        RETURNBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                currentstage.setScene(setupScene(seconddcscene()));
                currentstage.sizeToScene();
            }
        });

        // Create a tableview
        TableView<AllAid> table = new TableView<>();
        // Add Rows to the tableview
        table.getItems().addAll(TableViewHelper.getAllAidTableList(AllAidsList));
        // add columns to the TableView
        table.getColumns().addAll(TableViewHelper.getDONORname(), TableViewHelper.getDONORphone(),
                TableViewHelper.getAIDname(), TableViewHelper.getAIDqty(),
                TableViewHelper.getNGOname(), TableViewHelper.getNGOmanpower(), TableViewHelper.getAIDstatus());

        // Set the column resize policy to constrained resize policy
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Set the Placeholder for an empty table
        table.setPlaceholder(new Label("No data exist yet."));
        // Nodes
        // CREATE TABLEVIEW WITH A LIST OF DISTRIBUTED AIDS
        vb.getChildren().add(label);
        VBox.setMargin(label, new Insets(10));
        vb.getChildren().add(label1);
        VBox.setMargin(label, new Insets(10));
        vb.getChildren().add(table);
        VBox.setMargin(table, new Insets(0, 10, 0, 10));
        vb.getChildren().add(RETURNBUTTON);
        VBox.setMargin(RETURNBUTTON, new Insets(10));

        return vb;

    }

    /**
     * To create a VBox for construction the scene for donor or ngo account after
     * they have logged in
     * Including a table for user to view their respective aid that is donated //
     * requested
     * 
     * @param username username of the user(unique) that is logged in
     * @param usertype types of user (DONOR or NGO)
     * @return VBox layout for donor and NGO
     */
    public VBox DONORNGOscene(String username, String usertype) {
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);

        Label label = new Label("");

        Button RETURNBUTTON = new Button("LogOut");
        RETURNBUTTON.setMaxSize(200, 100);

        Button FUNCBUTTON = new Button("");
        FUNCBUTTON.setMaxSize(200, 100);

        if (usertype.equals("DONOR")) {
            label.setText("WELCOME DONOR : " + username);
            FUNCBUTTON.setText("Donate More");
        } else if (usertype.equals("NGO")) {
            label.setText("WELCOME NGO : " + username);
            FUNCBUTTON.setText("Request More");
        }

        HBox p = new HBox();
        p.getChildren().add(FUNCBUTTON);
        p.getChildren().add(RETURNBUTTON);
        p.setAlignment(Pos.CENTER);

        // Return to main
        RETURNBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                currentstage.setScene(setupScene(firstdcscene()));
                currentstage.sizeToScene();
            }
        });

        if (usertype.equals("DONOR") || usertype.equals("NGO"))

            FUNCBUTTON.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    currentstage.setScene(setupScene(FUNCaddrequest(username, usertype)));
                    currentstage.sizeToScene();
                }
            });

        // Create a tableview
        TableView<AllAid> table = new TableView<>();
        // Add Rows to the tableview
        table.getItems().addAll(TableViewHelper.getAllAidTableList(DC.getALLaidsfromcsv(username)));
        // add columns to the TableView
        table.getColumns().addAll(TableViewHelper.getDONORname(), TableViewHelper.getDONORphone(),
                TableViewHelper.getAIDname(), TableViewHelper.getAIDqty(),
                TableViewHelper.getNGOname(), TableViewHelper.getNGOmanpower(), TableViewHelper.getAIDstatus());

        // Set the column resize policy to constrained resize policy
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Set the Placeholder for an empty table
        table.setPlaceholder(new Label("No data exist yet."));
        // Nodes
        // CREATE TABLEVIEW WITH A LIST OF DISTRIBUTED AIDS
        vb.getChildren().add(label);
        VBox.setMargin(label, new Insets(10));
        vb.getChildren().add(table);
        VBox.setMargin(table, new Insets(0, 10, 0, 10));
        vb.getChildren().add(p);
        VBox.setMargin(p, new Insets(10));

        return vb;

    }

    ///////////////////////////////// DONATE MORE OR REQUEST MORE SCENE
    ////////////////////////////////////////////////////////////////////
    /**
     * To create a gridpane for the creation of scene of donate or request more
     * scene
     * 
     * @param username the username of the current user in the system
     * @param usertype the type of the current user in the system (DONOR or NGO)
     * @return Gridpane of layout for donate or request more scene
     */
    public GridPane FUNCaddrequest(String username, String usertype) {
        GridPane newp = new GridPane();

        // Nodes
        Text msg = new Text();

        Label lnewaid = new Label("AID");
        Label lnewaidqty = new Label("Quantity");
        Label result = new Label("");

        TextField lnewaid_field = new TextField();
        TextField lnewaidqty_field = new TextField();

        Button EXITBUTTON = new Button("Return");
        Button FUNCBUTTON = new Button("");

        GridPane.setHalignment(msg, HPos.CENTER);
        GridPane.setHalignment(EXITBUTTON, HPos.CENTER);
        GridPane.setHalignment(FUNCBUTTON, HPos.CENTER);
        GridPane.setHalignment(result, HPos.CENTER);

        Alert alert = new Alert(AlertType.NONE);
        alert.setAlertType(AlertType.INFORMATION);

        FUNCBUTTON.setMaxSize(200, 100);
        EXITBUTTON.setMaxSize(200, 100);

        if (usertype.equals("DONOR")) {
            msg.setText("DONATE AID FROM " + username);
            FUNCBUTTON.setText("DONATE");
        } else if (usertype.equals("NGO")) {
            msg.setText("REQUEST AID FROM " + username);
            FUNCBUTTON.setText("REQUEST");
        }

        newp.setPadding(new Insets(13, 13, 13, 13));
        HBox.setMargin(msg, new Insets(12));
        HBox.setMargin(lnewaid, new Insets(12));
        HBox.setMargin(lnewaid_field, new Insets(1, 12, 1, 12));
        HBox.setMargin(lnewaidqty, new Insets(12));
        HBox.setMargin(lnewaidqty_field, new Insets(1, 12, 1, 12));
        HBox.setMargin(result, new Insets(12));
        HBox.setMargin(EXITBUTTON, new Insets(1, 12, 12, 12));
        HBox.setMargin(FUNCBUTTON, new Insets(1, 12, 12, 12));

        newp.add(msg, 0, 0, 2, 1);
        newp.add(lnewaid, 0, 1);
        newp.add(lnewaid_field, 1, 1);
        newp.add(lnewaidqty, 0, 2);
        newp.add(lnewaidqty_field, 1, 2);
        newp.add(result, 0, 3, 2, 2);
        newp.add(EXITBUTTON, 0, 5);
        newp.add(FUNCBUTTON, 1, 5);

        FUNCBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    Integer quantity = Integer.parseInt(lnewaidqty_field.getText());
                    if (lnewaid_field.getText().equals(""))
                        result.setText("AID Is Empty");
                    else if (lnewaidqty_field.getText().equals(""))
                        result.setText("AID QUANTITY Is Empty");
                    else if (quantity <= 0)
                        result.setText("Only positive integer allowed for quantity");
                    else if (lnewaid_field.getText().equals("-") || lnewaidqty_field.getText().equals("-"))
                        result.setText("Invalid Input. (-)  is not Allowed");
                    else {
                        if (usertype.equals("DONOR")) {
                            AllAidsList.add(new AllAid(
                                    new Donor(username, DONORmap.get(username).getDonorname(),
                                            DONORmap.get(username).getPhonenum()),
                                    NGO.nullngo(),
                                    lnewaid_field.getText(), quantity,"Available"));
                            alert.setContentText(
                                    "SUCCESSFUL DONATED: \n" + quantity + "    " + lnewaid_field.getText());
                        } else if (usertype.equals("NGO")) {
                            AllAidsList.add(new AllAid(Donor.nulldonor(),
                                    new NGO(username, NGOmap.get(username).getNgoname(),
                                            NGOmap.get(username).getManpower()),
                                    lnewaid_field.getText(), quantity,"-"));
                            alert.setContentText(
                                    "SUCCESSFUL REQUESTED FOR: \n" + quantity + "    " + lnewaid_field.getText());
                        }
                        try {
                            DC.updateaid(AllAidsList);
                            updateaidfile();
                            alert.show();
                            lnewaid_field.setText("");
                            lnewaidqty_field.setText("");
                            result.setText("");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    result.setText("Quantity must be positive integer only");
                }

            }
        });

        // EXIT AFTER COMPLETED
        EXITBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                currentstage.setScene(setupScene(DONORNGOscene(username, usertype)));
                currentstage.sizeToScene();
            }
        });
        return newp;

    }

    ////////////////////////////////// MAIN DC SCENE
    ////////////////////////////////// /////////////////////////////////

    /**
     * Act as the main screen / scene when the user start the program
     * Able to redirect user to Distribution Center / login / Register
     * 
     * @return the VBox layouts for the main menu scene (just like menu system)
     */
    public VBox firstdcscene() {
        // create gridpane with properties
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15, 5, 5, 5));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        // nodes inside GridPane
        vbox.getChildren().add(getWelcomeLogoText());

        Text txtdiscription = new Text();
        txtdiscription.setText("Please Choose A Functionality");
        vbox.getChildren().add(txtdiscription);

        Button DCBTN = new Button("Distribution Center");
        // DCBTN.setId("font-button");
        vbox.getChildren().add(DCBTN);
        // action event
        DCBTN.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent t) {
                currentstage.setScene(setupScene(seconddcscene()));
                currentstage.sizeToScene();
            }
        });

        Button LOGINBTN = new Button("Login");
        vbox.getChildren().add(LOGINBTN);
        // action event
        LOGINBTN.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent t) {

                currentstage.setScene(setupScene(REGLOGscene(1)));
                currentstage.sizeToScene();
            }
        });

        Button REGISTERBTN = new Button("Register");
        vbox.getChildren().add(REGISTERBTN);
        // action event
        REGISTERBTN.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                currentstage.setScene(setupScene(REGLOGscene(2)));
                currentstage.sizeToScene();
            }
        });

        DCBTN.setMaxSize(200, 100);
        LOGINBTN.setMaxSize(200, 100);
        REGISTERBTN.setMaxSize(200, 100);

        return vbox;
    }

    /////////////////////////////// SECOND DC SCENE
    //////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Act as the secone screen / scene when the user click on the distribution center in the first scene
     * Able to redirect user to Sorting (matching such as 1 to 1) / Viewing (in Table) / FIFO or Priority queue Collection / return to main
     * 
     * @return the VBox layouts for the second scene (just like menu system)
     */
    public VBox seconddcscene() {

        // create gridpane with properties
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(15, 5, 5, 5));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);

        // nodes inside GridPane

        vbox.getChildren().add(getWelcomeLogoText());

        Text txtdiscription = new Text();
        txtdiscription.setText("DC FUNCTIONS");
        vbox.getChildren().add(txtdiscription);

        Button SORTBTN = new Button("SORTING");

        vbox.getChildren().add(SORTBTN);

        // action event
        SORTBTN.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent t) {
                currentstage.setScene(setupScene(sortdcscene()));
                currentstage.sizeToScene();
            }
        });

        Button VIEWBTN = new Button("VIEW");
        vbox.getChildren().add(VIEWBTN);
        // action event
        VIEWBTN.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent t) {

                currentstage.setScene(setupScene(viewDCscene()));
                bigscreen();
            }
        });

        Button COLLECTBTN = new Button("QUEUE COLLECTION");
        vbox.getChildren().add(COLLECTBTN);
        // action event
        COLLECTBTN.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                currentstage.setScene(setupScene(collectdcscene()));
                currentstage.sizeToScene();
            }
        });

        Button RETURNBUTTON = new Button("RETURN");
        vbox.getChildren().add(RETURNBUTTON);
        // action event
        RETURNBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                currentstage.setScene(setupScene(firstdcscene()));
                currentstage.sizeToScene();
            }
        });

        SORTBTN.setMaxSize(200, 100);
        VIEWBTN.setMaxSize(200, 100);
        COLLECTBTN.setMaxSize(200, 100);
        RETURNBUTTON.setMaxSize(200, 100);

        return vbox;
    }

    ////////////////////////////////// SORT DC SCENE
    ////////////////////////////////// /////////////////////////////////
    /**
     * To create a gridpane for the user selection of matching scene (1-1,1-M,M-1,M-N)
     * @return Gridpane of layout for matching scene
     */
    public GridPane sortdcscene() {
        GridPane gpane = new GridPane();
        gpane.setPadding(new Insets(12, 13, 14, 15));
        gpane.setAlignment(Pos.CENTER);

        Text msg = new Text("SORTING");

        Button OOBUTTON = new Button("One-To-One ");
        Button OMBUTTON = new Button("One-To-Many");
        Button MOBUTTON = new Button("Many-To-One ");
        Button MMBUTTON = new Button("Many-To-Many");
        Button RETURNBUTTON = new Button("RETURN");

        Label result = new Label("");

        GridPane.setHalignment(msg, HPos.CENTER);
        GridPane.setHalignment(OOBUTTON, HPos.CENTER);
        GridPane.setHalignment(OMBUTTON, HPos.CENTER);
        GridPane.setHalignment(MOBUTTON, HPos.CENTER);
        GridPane.setHalignment(MMBUTTON, HPos.CENTER);
        GridPane.setHalignment(result, HPos.CENTER);
        GridPane.setHalignment(RETURNBUTTON, HPos.CENTER);

        gpane.setHgap(10);
        gpane.setVgap(10);

        OOBUTTON.setMaxSize(200, 100);
        OMBUTTON.setMaxSize(200, 100);
        MMBUTTON.setMaxSize(200, 100);
        MOBUTTON.setMaxSize(200, 100);
        RETURNBUTTON.setMaxSize(200, 100);

        gpane.add(msg, 0, 0, 2, 1);
        gpane.add(OOBUTTON, 0, 1);
        gpane.add(OMBUTTON, 1, 1);
        gpane.add(MOBUTTON, 0, 2);
        gpane.add(MMBUTTON, 1, 2);
        gpane.add(result, 0, 3, 2, 1);
        gpane.add(RETURNBUTTON, 0, 4, 2, 1);

        // Return to main
        RETURNBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                currentstage.setScene(setupScene(seconddcscene()));
                currentstage.sizeToScene();
            }
        });

        // One-To-One
        OOBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                DC.one_to_one();
                updateaidfile();
                result.setText("One-To-One operation successful");
            }
        });

        // One-To-Many
        OMBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                DC.one_to_many();
                updateaidfile();
                result.setText("One-To-Many operation successful");
            }
        });

        // One-To-One
        MOBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                DC.many_to_one();
                updateaidfile();
                result.setText("Many-To-One operation successful");
            }
        });

        // One-To-One
        MMBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                DC.many_to_many();
                updateaidfile();
                result.setText("Many-To-Many operation successful");
            }
        });

        return gpane;
    }

    ////////////////////////////////// COLLECT DC SCENE
    ////////////////////////////////// /////////////////////////////////
    /**
     * To create a gridpane for the user selection of ngo collection aid queue (FIFO or priority queue)
     * @return Gridpane of layout for ngo collection aid queue scene
     */
    public GridPane collectdcscene() {
        Map<String,NGO> NGOnamemap = DC.getNGOnameandcountfromcsv();
        Queue<NGO> NQUEUE = new java.util.LinkedList<>();
        Queue<NGO> PQUEUE  = new PriorityQueue<NGO>(NGOnamemap.size(),new NGOComparator());

        GridPane gpane = new GridPane();
        gpane.setPadding(new Insets(12, 13, 14, 15));
        gpane.setAlignment(Pos.CENTER);

        Text msg = new Text("NGO COLLECTION QUEUE SYSTEM");

        Button QBUTTON = new Button("QUEUE");
        Button DQBUTTON = new Button("DEQUEUE");

        Button RETURNBUTTON = new Button("RETURN");

        Label result = new Label("");

        Label QUEUEMSG = new Label("FIFO QUEUE : ");
        Label QUEUE_SEQ = new Label("[]");

        TextField QUEUE_field = new TextField();

        ToggleGroup QGROUP = new ToggleGroup();
        ToggleButton FIFOQ = new ToggleButton("   FIFO QUEUE   ");
        ToggleButton PQ = new ToggleButton(" PRIORITY QUEUE ");

        FIFOQ.setToggleGroup(QGROUP);
        PQ.setToggleGroup(QGROUP);
        QGROUP.selectToggle(FIFOQ);
        FIFOQ.setSelected(true);

        FIFOQ.setMaxSize(200, 100);
        PQ.setMaxSize(200, 100);
        QBUTTON.setMaxSize(200, 100);
        DQBUTTON.setMaxSize(200, 100);
        RETURNBUTTON.setMaxSize(200, 100);

        GridPane.setHalignment(msg, HPos.CENTER);
        GridPane.setHalignment(FIFOQ, HPos.CENTER);
        GridPane.setHalignment(PQ, HPos.CENTER);
        GridPane.setHalignment(QBUTTON, HPos.CENTER);
        GridPane.setHalignment(DQBUTTON, HPos.CENTER);
        GridPane.setHalignment(result, HPos.CENTER);
        GridPane.setHalignment(RETURNBUTTON, HPos.CENTER);

        gpane.setHgap(10);
        gpane.setVgap(10);

        gpane.add(msg, 0, 0, 2, 1);
        gpane.add(FIFOQ, 0, 1);
        gpane.add(PQ, 1, 1);
        gpane.add(QUEUEMSG, 0, 2);
        gpane.add(QUEUE_SEQ, 1, 2);
        gpane.add(QUEUE_field, 0, 3, 2, 1);
        gpane.add(QBUTTON, 0, 4);
        gpane.add(DQBUTTON, 1, 4);
        gpane.add(result, 0, 5, 2, 1);
        gpane.add(RETURNBUTTON, 0, 6, 2, 1);

        // Return to main
        RETURNBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                currentstage.setScene(setupScene(seconddcscene()));
                currentstage.sizeToScene();
            }
        });

        QGROUP.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle toggled) {
                if (toggled != null) {
                    String toggleBtn = ((ToggleButton) toggled).getText();
                    if (toggleBtn.equals("   FIFO QUEUE   ")) {
                        QUEUEMSG.setText("FIFO QUEUE : ");
                        NQUEUE.clear();
                        PQUEUE.clear();
                        QUEUE_SEQ.setText(NQUEUE.toString());
                        result.setText("FIFO Queue is Selected");
                    }

                    else if (toggleBtn.equals(" PRIORITY QUEUE ")) {
                        QUEUEMSG.setText("PRIORITY QUEUE : ");
                        NQUEUE.clear();
                        PQUEUE.clear();
                        QUEUE_SEQ.setText(PQUEUE.toString());                        
                        result.setText("Priority Queue is Selected");
                    }
                }
            }
        });

        QBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {

                result.setText("");

                Queue<NGO> selectedQ = new LinkedList<NGO>();

                String input = QUEUE_field.getText().toUpperCase();

                if(FIFOQ.isSelected()){
                    selectedQ = NQUEUE;
                }
                else if(PQ.isSelected()){
                    selectedQ = PQUEUE;
                }




                if(input.equals(""))
                    result.setText("Please Enter the NGO NAME");

                else if(!NGOnamemap.containsKey(input))
                    result.setText(input + " not found in NGO list");

                else if(DC.check_ngo_name_exists(input))
                    result.setText("NGO NAME not found in Completed AID");

                else if(DC.check_if_all_completed_aid(input))
                    result.setText(input + " has collected all the aids requested");

                else if(selectedQ.toString().contains(input))
                    result.setText("Duplicated key " + input + " found in the list");

                else{
                    selectedQ.offer(NGOnamemap.get(input));
                    QUEUE_SEQ.setText(selectedQ.toString());
                    result.setText(input + " is Queued");
                    QUEUE_field.setText("");
                }
            }
        });

        DQBUTTON.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                QUEUE_field.setText("");
                result.setText("");
                // if the queue is empty then update the user in the result
                Queue<NGO> selectedQ = new LinkedList<>();

                if(FIFOQ.isSelected()){
                    selectedQ = NQUEUE;
                }
                else if(PQ.isSelected()){
                    selectedQ = PQUEUE;
                }


                if(selectedQ.isEmpty())
                    result.setText("FIFO QUEUE is empty");
                else{
                    // update the aid into collected function in dc
                    NGO ngon = selectedQ.remove();
                    QUEUE_SEQ.setText(selectedQ.toString());
                    DC.update_collected_aid_status_for(ngon.getNgoname());
                    result.setText(  ngon + " has been successfully collected AID");
                    QUEUE_field.setText("");
            }
            }
        });

        return gpane;
    }

    /**
     * To display the welcome text of the system
     * 
     * @return the vBox layouts of the welcome text of the system
     */
    private VBox getWelcomeLogoText() {
        ImageView logo = new ImageView(new Image(logosrc));
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(12, 13, 14, 15));
        vBox.getChildren().add(logo);
        vBox.getChildren().add(new Label("Welcome To AID Distribution Center"));

        return vBox;
    }

    ////////////////////////////////// SetUp the scene need to be used by the
    ////////////////////////////////// program ///////////////////////////////

    /**
     * Used to return the scene given a designed vbox
     * 
     * @param tempscene VBox that is constructed and intended to make it into a
     *                  scene
     * @return scene that can be used in stage.setScene() function
     */
    public Scene setupScene(VBox tempscene) {
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane);
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().add(tempscene);

        scene.getStylesheets().add(getClass().getResource(csssheet).toExternalForm());
        return scene;
    }

    /**
     * Used to return the scene given a designed gridpane
     * Overloaded function for setupScene(VBox)
     * 
     * @param tempscene Gridpane that is constructed and intended to make it into a
     *                  scene
     * @return scene that can be used in stage.setScene() function
     */
    public Scene setupScene(GridPane tempscene) {
        StackPane pane = new StackPane();
        Scene scene = new Scene(pane);
        pane.setAlignment(Pos.CENTER);

        GridPane display = tempscene;

        pane.getChildren().add(display);

        scene.getStylesheets().add(getClass().getResource(csssheet).toExternalForm());
        return scene;
    }

    /**
     * Enlarge the current stage to the 900*600 size
     * To be able to view the full table in DC
     * Better apperances
     */
    public void bigscreen() {
        currentstage.setWidth(900);
        currentstage.setHeight(600);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Launch the javafx application
     * 
     * @param args array of sequence of characters that are passed to main function
     */
    public static void main(String[] args) {
        launch(args);
    }
}
