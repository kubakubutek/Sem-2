import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.Action;
import javafx.stage.FileChooser;
import java.io.FileWriter;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.*;
/**
 * Class L5Z1 is a main class of the program
 * @version 1.0
 * @autor Jakub Kowal
 */
public class L5Z1 extends Application {

    /**
     * Consruktor of L5Z1 main class
     * @param stage
     */
    @Override
    public void start(Stage stage) {        
        /** BorderPane responsible for layout of the application */
        BorderPane root = new BorderPane();
        /** Pane being used to show shapes */
        Pane canvas = new Pane();
        canvas.setId("canvas");
        canvas.setStyle("-fx-background-color: #FFFFFF;");
        canvas.setMinWidth(900);
        canvas.setMinHeight(500);
        /** ColorPicker being used to change color of the shapes */
        ColorPicker colorPicker = new ColorPicker();
        canvas.getChildren().add(colorPicker);
        colorPicker.setVisible(false);
        colorPicker.setId("colorPicker");
        /** VBox containg all button used to create shapes */
        VBox sidebar = new VBox(5);        
        sidebar.setMaxWidth(100);
        sidebar.setMinWidth(100);
        /** Menubar containing menus */
        MenuBar mainMenu = new MenuBar();
        /** Menu used for basic info about application */
        Menu menu1 = new Menu("Info");
        /** Menu used for loading and saving shapes */
        Menu load = new Menu("Load");
        /** MenuItem of custom class Guide containing the guide for application */
        Guide guide = new Guide();
        /** Label used in creating shapes to create just 1 */
        final Label choice= new Label("");
        /** Label containg current state of shift key */
        final Label shift= new Label("false");
        shift.setId("shift");
        shift.setVisible(false);
        /** Label containing the number of shapes */
        final Label number = new Label("0");
        number.setId("number1");
        number.setVisible(false);
        /** Label containing the position of the mouse */
        Label mousePos = new Label("");
        mousePos.setId("mousePos");
        mousePos.setVisible(false);
        /** Label containing the position of the mouse when clicked */
        Label mouseClicked= new Label("");
        mouseClicked.setVisible(false);
        /** MenuItem of class Info containing basic info about the program and it's author */
        Info info = new Info();
        /** Button responsible for creating circle */
        Button circleBtn = new Button("Circle");
        circleBtn.setMaxWidth(100);
        /** Button responsible for creating triangle */
        Button triangleBtn = new Button("Triangle");
        triangleBtn.setMaxWidth(100);
        /** Button responsible for creating rectangle */
        Button rectangleBtn = new Button("Rectangle");
        rectangleBtn.setMaxWidth(100);
        /** Main scene of the application */
        Scene scene = new Scene(root, 1000, 500, Color.WHITESMOKE);
        root.setTop(mainMenu);
        root.setCenter(canvas);
        root.setRight(sidebar);       
        /** LoadItem responsible for loading shapes */
        LoadItem loadItem = new LoadItem(scene);
        /** SaveItem responsible for saving shapes */
        SaveItem saveItem = new SaveItem(scene);
        /**
         * Event handler for shift key being pressed
         */
        root.setOnKeyPressed(e->{
            if(e.isShiftDown()){
                shift.setText("true");
            }else{
                shift.setText("false");
            }
        });

        /**
         * Event handler for shift key being released
         */
        root.setOnKeyReleased(e->{
            if(e.isShiftDown()){
                shift.setText("true");
            }else{
                shift.setText("false");
            }
        });        

        /**
         * Event handler for mouse being moved out of canvas
         */
        sidebar.setOnMouseMoved(e->{
            mousePos.setText("Mouse out of canvas");
        });
        /**
         * Event handler for mouse being moved inside of canvas
         */
        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getSceneX()<=canvas.getWidth() && event.getSceneY()<=canvas.getHeight()){
                    mousePos.setText("Mouse position: " + event.getSceneX() + ", " + event.getSceneY());   
                }                                       
            }
        });
        /**
         * Event handler for mouse being moved out of canvas
         */
        canvas.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePos.setText("Mouse out of canvas");
            }
        });
        /**
         * Event handler for mouse being clicked
         */
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            /**
             * Method that handles mouse click event used to generate shapes
             * @param event
             */
            public void handle(MouseEvent event) {
                mouseClicked.setText("Mouse clicked at: " + event.getX() + ", " + event.getY());
                
                if(choice.getText().equals("c")){
                        Circles circle = new Circles(scene);
                        circle.setCenterX(event.getX());
                        circle.setCenterY(event.getY());                     
                        canvas.getChildren().add(circle);
                        number.setText(Integer.toString(Integer.parseInt(number.getText())+1));
                }else{
                if(choice.getText().equals("t")){                    
                        Triangle triangle = new Triangle(scene);
                        triangle.getPoints().addAll(new Double[]{
                            event.getX(), event.getY(),
                            event.getX()+30, event.getY()+50,
                            event.getX()-30, event.getY()+50
                        });

                        canvas.getChildren().add(triangle);                        
                    }else{
                    if(choice.getText().equals("r")){
                        Rectang rectangle = new Rectang(scene);
                        canvas.getChildren().add(rectangle);
                        rectangle.setX(event.getX());
                        rectangle.setY(event.getY());
                    }}}                
                choice.setText("");
                }
        });
        /**
         * Event handler for handling circle button action
         */
        circleBtn.setOnAction(e->{
            choice.setText("c");
        });
        /**
         * Event handler for handling triangle button action
         */
        triangleBtn.setOnAction(e->{
            choice.setText("t");
        });
        /**
         * Event handler for handling rectangle button action
         */
        rectangleBtn.setOnAction(e->{
            choice.setText("r");
        });        
        
        sidebar.getChildren().addAll(circleBtn, triangleBtn, rectangleBtn,mousePos,mouseClicked,shift,number);
        sidebar.setAlignment(javafx.geometry.Pos.TOP_RIGHT);
        
        menu1.getItems().addAll(guide, info);
        load.getItems().addAll(loadItem,saveItem);
        mainMenu.getMenus().addAll(menu1,load);

        stage.setTitle("Paint 7D");
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Main method of the program that launches the application
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}