import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.Action;
import javax.swing.plaf.FileChooserUI;
import javax.swing.plaf.LabelUI;
import javafx.stage.FileChooser;
import java.io.FileWriter;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;

public class LoadItem extends MenuItem {
    LoadItem(Scene scene){
        super("Load Item");
        Pane canvas = (Pane) scene.lookup("#canvas");
        setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                FileChooser fileDir = new FileChooser(); 
                File file=fileDir.showOpenDialog(null);                
                try {                
                Scanner reader = new Scanner(file);
                
                    String data = reader.nextLine();
                    System.out.println(data);
                    
                        if(data.equals("c")){
                            try{
                            String color = reader.nextLine();
                            System.out.println(color);
                            String radius = reader.nextLine();
                            Circle circle = new Circle(Double.parseDouble(radius));
                            circle.setFill(Color.web(color));
                            canvas.getChildren().add(circle);
                            circle.setCenterX(canvas.getWidth()/2);
                            circle.setCenterY(canvas.getHeight()/2);
                            }catch(Exception e){
                                System.out.println("An error occurred.");
                                e.printStackTrace();
                            }
                        }else if (data.equals("t")) {
                            Triangle triangle = new Triangle(scene);
                            triangle.setFill(Color.web(reader.nextLine()));
                            triangle.getPoints().addAll(new Double[]{
                            Double.parseDouble(reader.nextLine()), Double.parseDouble(reader.nextLine()),
                            Double.parseDouble(reader.nextLine()), Double.parseDouble(reader.nextLine()),
                            Double.parseDouble(reader.nextLine()), Double.parseDouble(reader.nextLine())
                            });

                        canvas.getChildren().add(triangle);   
                            
                        }else if (data.equals("r")) {
                        Rectang rectangle = new Rectang(scene);
                        canvas.getChildren().add(rectangle);
                        rectangle.setX(canvas.getWidth()/2);
                        rectangle.setY(canvas.getHeight()/2);
                        rectangle.setFill(Color.web(reader.nextLine()));
                        rectangle.setHeight(Double.parseDouble(reader.nextLine()));
                        rectangle.setWidth(Double.parseDouble(reader.nextLine()));             
                        }                
                reader.close();
                } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                }
            }
        });
    }
}