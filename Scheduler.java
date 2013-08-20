/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import java.util.*;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.shape.*;
import javafx.scene.effect.*;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;

import javafx.stage.Stage;


public class Scheduler extends Application {
  
    public static void main(String[] args) {
        Application.launch(Scheduler.class, args);
    }
    
    static final int BLOCK_WIDTH = 16;
    static final int BLOCK_HEIGHT = 21;
    static final int ZERO_X = 56;
    static final int ZERO_Y = 226;
    static final Time ZERO_TIME = new Time(6,0);
    
    @Override
    public void start(Stage window) {
        window.setTitle("Employee Scheduler created by Kyle Bentsen");
        /*
         * Using an "AreaChart"  with 2 NumberAxis with calculated values
         * creates a pseudo-grid needed to display the fill-to-need data.
         * The grid "squares" are measured to be 16 pixels wide by 21 pixels high (16 x 21)
         * The zero of the coordinate system begins at pixel (56, 226) of the window.
         * Please note: the axis values and AreaChart dimensions have been 
         * carefully calculated so that the above data remain consistent
         * while it is used throughout the program.
         */
 
        Scene scene = new Scene(new Group(), 1300, 800, Color.WHITE);
        final NumberAxis timeAxis = new NumberAxis(6, 24, 1);
        timeAxis.setLabel("Time of day (24 hour time)");
        timeAxis.setMinorTickCount(4);
        final NumberAxis fillAxis = new NumberAxis(-20, 10, 2);
        fillAxis.setLabel("Fill to need");
        fillAxis.setMinorTickCount(2);
        final AreaChart<Number,Number> ac = new AreaChart<Number,Number>(timeAxis,fillAxis);
        ac.setPrefSize(1222, 706);
        
        Line zeroLine = new Line();
        zeroLine.setStartX(56);
        zeroLine.setStartY(225.5);
        zeroLine.setEndX(1207);
        zeroLine.setEndY(225.5);
        zeroLine.setStrokeWidth(1.5);
        
        /*  Used to coordinate test functionality
            final Label coordsLabel = new Label("0, 0");
            coordsLabel.setTextFill(Color.BLACK);
            coordsLabel.setStyle("-fx font: 12 arial;");
        */
        
        /*final Rectangle testRect = new Rectangle (56, 226 - 84, 16, 84);
        testRect.setArcHeight(10);
        testRect.setArcWidth(10);
        testRect.setFill(Color.BLACK);*/
        
        ((Group) scene.getRoot()).getChildren().add(ac);
        ((Group) scene.getRoot()).getChildren().add(zeroLine);
        //((Group) scene.getRoot()).getChildren().addAll(ac, testRect);
        //((Group) scene.getRoot()).getChildren().addAll(ac, coordsLabel);

        /* Displays pixel coordinates next to mouse cursor as it is moved around the window
        scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) 
            {
                //System.out.println(String.format("Mouse at (%d, %d)", (int)me.getX(), (int)me.getY()));
                coordsLabel.setTranslateX(me.getSceneX() + 25);
                coordsLabel.setTranslateY(me.getSceneY());
                coordsLabel.setText(String.format("(%d, %d)", (int)me.getX(), (int)me.getY()));

            }
        });
        */
      
        /*
         * Randomly draws rectangles on the grid. Used to test bar display
         * functionality
        Random randGen = new Random();
        InnerShadow barEffect = new InnerShadow();
        barEffect.setOffsetX(2.0f);
        barEffect.setOffsetY(0.3f);
        Rectangle testBars[] = new Rectangle[72];
        for(int i = 0; i < testBars.length; i++)
        {
            testBars[i] = new Rectangle(0, 0, 0, 0);
            int yVal = randGen.nextInt(31) - 20;
            if(yVal < 0)
            {
                testBars[i].setX(ZERO_X + (i * BLOCK_WIDTH));
                testBars[i].setY(ZERO_Y);
                testBars[i].setWidth(BLOCK_WIDTH);
                testBars[i].setHeight(Math.abs(yVal) * BLOCK_HEIGHT);
                testBars[i].setFill(Color.RED);
            }
            else
            {
                testBars[i].setX(ZERO_X + (i * BLOCK_WIDTH));
                testBars[i].setY(ZERO_Y - (yVal * BLOCK_HEIGHT));
                testBars[i].setWidth(BLOCK_WIDTH);
                testBars[i].setHeight((yVal) * BLOCK_HEIGHT);
                testBars[i].setFill(Color.GREEN);
            }
            //testBars[i].setArcWidth(5);
            //testBars[i].setArcHeight(5);
            testBars[i].setEffect(barEffect);
            ((Group) scene.getRoot()).getChildren().add(testBars[i]);
        }
        */
        TimeCounter tc1 = new TimeCounter(6, 0, 0);
        TimeCounter tc2 = new TimeCounter(8, 0, -2);
        TimeCounter tc3 = new TimeCounter(10, 2, -4);
        DrawBar(scene, tc1);
        DrawBar(scene, tc2);
        DrawBar(scene, tc3);
        //window.setResizable(false);
        window.setScene(scene);
        window.setVisible(true);
        //TimeCounter tcTest[] = new TimeCounter[72];
        //tcTest[0].
    }
    
   
    
    public void DrawBar(Scene  scene, TimeCounter tc)
    {
        InnerShadow barEffect = new InnerShadow();
        barEffect.setOffsetX(2.0f);
        barEffect.setOffsetY(0.3f);
        Rectangle fillBar = new Rectangle(0, 0, 0, 0);
        int notchCount = TimeCountFromZero(tc);
        if(tc.getCount() < 0)
        {
            fillBar.setX(ZERO_X + (notchCount * BLOCK_WIDTH));
            fillBar.setY(ZERO_Y);
            fillBar.setWidth(BLOCK_WIDTH);
            fillBar.setHeight(Math.abs(tc.getCount()) * BLOCK_HEIGHT);
            fillBar.setFill(Color.RED);
        }
        else
        {
            fillBar.setX(ZERO_X + (notchCount * BLOCK_WIDTH));
            fillBar.setY(ZERO_Y - (tc.getCount() * BLOCK_HEIGHT));
            fillBar.setWidth(BLOCK_WIDTH);
            fillBar.setHeight((tc.getCount()) * BLOCK_HEIGHT);
            fillBar.setFill(Color.GREEN);
        }

        //testBars[i].setArcWidth(5);
        //testBars[i].setArcHeight(5);
        fillBar.setEffect(barEffect);
        ((Group) scene.getRoot()).getChildren().add(fillBar);
   
    }
    
    
    public int TimeCountFromZero(TimeCounter tc)
    {
        int h = tc.getHour() - ZERO_TIME.getHour();
        int m = tc.getMinute() - ZERO_TIME.getMinute();
        
        return (4 * h) + m;
    }
}
