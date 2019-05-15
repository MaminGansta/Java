/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sapper;

import auxiliary_items.CellButton;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author lil
 */
public class View {
    
    private Controller controller;
    private VBox root;
    private GridPane field;
    private CellButton[][] buttons;
    private Scene scene;
    private Stage primaryStage;
    private int fieldSize;
    private int totalScore;
    private int bombs;
    private int bombsFlags;
    private Text bombsFlagsText;
    
    private Menu createMenu(){
        
        Menu level = new Menu("level");
        MenuItem lvlEz = new CheckMenuItem("Ez");
        CheckMenuItem lvlHard = new CheckMenuItem("Hard");
        
        lvlEz.setOnAction((ActionEvent a) ->{
            controller.game(0);
        });
        
        lvlHard.setOnAction((ActionEvent a) ->{
            controller.game(1);
        });
        
        level.getItems().addAll(lvlEz, lvlHard);
        return level;
    } 
    
    private void gameOver(int lvl){
              
    }
    
    public void flagsControll(boolean flag){
        if (flag)
            bombsFlags--;
        else
            bombsFlags++;
        
        bombsFlagsText.setText(bombsFlags + "    ");
    }
    
    private void createField(){
        
        buttons = new CellButton[fieldSize][fieldSize];
        
          for (int i = 0; i < fieldSize; i++)
            for (int j = 0; j < fieldSize; j++){
                buttons[i][j] = new CellButton(i, j, controller);
            }
          
        field = new GridPane();
        field.setAlignment(Pos.CENTER);
              
         for (int i = 0; i < fieldSize; i++)
            for (int j = 0; j < fieldSize; j++){
                field.add(buttons[i][j], j, i);
            }
         
        root.getChildren().add(field);
    }
    
    public void updateField(int[] cords, int val){     
        
        if (!buttons[cords[0]][cords[1]].getText().equals("")) 
            return;
            
            buttons[cords[0]][cords[1]].setText(val + "");
        
         if (val == 1){
             buttons[cords[0]][cords[1]].setStyle("-fx-text-fill: blue; -fx-font: 15 arial");
         }
         
         if (val == 2){
             buttons[cords[0]][cords[1]].setStyle("-fx-text-fill: green; -fx-font: 15 arial");
         }
         
         if (val == 3){
             buttons[cords[0]][cords[1]].setStyle("-fx-text-fill: red; -fx-font: 15 arial");
         }
         
         if (val == 4){
             buttons[cords[0]][cords[1]].setStyle("-fx-text-fill: darkmagenta; -fx-font: 15 arial");
         }
         
         if (val == 5){
             buttons[cords[0]][cords[1]].setStyle("-fx-text-fill: darkorange; -fx-font: 15 arial");
         }
         
         if (val >= 6){
             buttons[cords[0]][cords[1]].setStyle("-fx-text-fill: midnightblue; -fx-font: 15 arial");
         }
         
         if (val == 9){
              int c = 0x23FA;
              buttons[cords[0]][cords[1]].setText(Character.toString((char)c));
                // buttons[cords[0]][cords[1]].setStyle("-fx-text-fill: blue; -fx-font: 10 arial");
         }
         
         totalScore--;
         //System.out.println( val +" "+ totalScore);
         
         if (totalScore - bombs == 0)
             System.out.println("Congratulations");
    }
    
    public void setLvl(int lvl, int fieldSize, int bombs){
        
        this.fieldSize = fieldSize;
        this.bombs = bombs;
        this.bombsFlags = bombs;
        
        root = new VBox(10);
        root.setAlignment(Pos.TOP_CENTER);
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(createMenu());
        
        bombsFlagsText = new Text(bombsFlags + "    ");
        Label bombsFlagsLabel = new Label("bombs: ");  
        bombsFlagsLabel.setStyle("-fx-text-fill: red; -fx-font: 15 arial");
        bombsFlagsText.setStyle("-fx-text-fill: red; -fx-font: 15 arial");
        
        HBox displadeBombs = new HBox(10);
        displadeBombs.setAlignment(Pos.TOP_RIGHT);
        displadeBombs.getChildren().addAll(bombsFlagsLabel, bombsFlagsText);
        
       
        root.getChildren().addAll(menuBar, displadeBombs);
        
        if (lvl == 0)
            scene = new Scene(root, 232, 280);
        else 
            scene = new Scene(root, 695, 740);   
                   
        
        createField();
        totalScore = fieldSize * fieldSize;
        
        primaryStage.setResizable(false);       
        primaryStage.setTitle("Sapper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
    public View(Stage primaryStage, Controller controller, int lvl, int fieldSize, int bombs){
        this.primaryStage = primaryStage;
        this.controller = controller;
        setLvl(lvl, fieldSize, bombs);
    } 
   
}
