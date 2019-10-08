import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Random;
import static java.lang.Math.*;


public class SCC5_Spiro extends Application
{
    Random rnd = new Random();
//************************************************************************************************************
    //those are the variables you should play with to get different results, have fun

    private double R = rnd.nextInt(150) + 1;            //outer circle radius
    private double r = rnd.nextInt(150) + 1;            //inner circle radius
    private double O = rnd.nextInt(150) + 1;            //offset from the center of the inner circle
    private double z = 1.5;                                 //zoom multiplier
    private String sgColor = "#ff0000";                     //color of the spirograph

    //here are some neat combinations i found:
    // (4,1,2.33,20) (105,24,15,2) (105,80,15,4) (96,105,30,4) (45,59,109,2)
    //just copy and paste in the setParameters below

    //if you want a randomly generated spirograph just comment the setParameters line
    private void OwO()
    {
        setParameters(94,30,40,1.5);          //they are in "R, r, O, z" order
    }
// ***********************************************************************************************************

    private GraphicsContext gc;
    private double time = 0.0;


    private Parent getRoot()
    {
        Pane root = new Pane();
        root.setPrefSize(600, 600);               //scene settings
        root.setStyle("-fx-background-color: #000000");

        Canvas canvas = new Canvas(600, 600);
        gc = canvas.getGraphicsContext2D();                            //canvas setup
        root.getChildren().add(canvas);

        at.start();                                                    //start the timer

        return root;
    }

    @Override
    public void start(Stage primaryStage)
    {
        OwO();
        primaryStage.setTitle("Spirograph Generator");                 //stage name
        primaryStage.setScene(new Scene(getRoot()));                   //link scene to stage
        primaryStage.show();                                           //display the stage
        System.out.println("R: " + R);
        System.out.println("r: " + r);
        System.out.println("O: " + O);
    }

    private AnimationTimer at = new AnimationTimer() {
        @Override
        public void handle(long l) {
            time += 0.034;
            draw();
        }
    };

    private void draw()
    {
        Point2D uwu = math();

        gc.setStroke(Color.valueOf(sgColor));

        double drawX = 300 + uwu.getX();
        double drawY = 300 + uwu.getY();

        gc.strokeOval(drawX, drawY, 1, 1);
    }

    private Point2D math()
    {
        double x = (R-r)*cos(r/R*time) + O*cos((1-r/R)*time);       //formula for x
        double y = (R-r)*sin(r/R*time) - O*sin((1-r/R)*time);       //formula for y

        return new Point2D(x, y).multiply(z);
    }

    private void setParameters(double x1, double x2, double x3, double x4)      //convenience for the user
    {
        R = x1;
        r = x2;
        O = x3;
        z = x4;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
