/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sapper;

import auxiliary_items.CellButton;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ArrayList<CellButton[][]> fieldsForLvl = new  ArrayList<>();
    private Scene scene;
    private Stage primaryStage;
    private int fieldSize;
    private int totalScore;
    private int bombs;
    private int bombsFlags;
    private Text bombsFlagsText;
    private int lvl;
    private Random rand = new Random();
    

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
    
    private void dialogOfendGame(boolean res){
        
        Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText(null);
            
        
        if (res){
            ImageView congr = new ImageView(new Image("congratulations.jpg"));
            congr.setFitHeight(200);
            congr.setFitWidth(350);
            alert.setGraphic(congr);
            alert.setContentText("Congratulation!!!  Try agen?");
        }
        else{
            alert.setContentText("Try agen?");
        }
        
        alert.showAndWait();
        controller.game(lvl);
    }
    
    private void gameOver(int[] cords){
        controller.showBombs(cords);
        dialogOfendGame(false);
    }
    
    public void flagsControll(boolean flag){
        if (flag)
            bombsFlags--;
        else
            bombsFlags++;
        
        bombsFlagsText.setText(bombsFlags + "    ");
    }
    
    private void createField(){
        
        buttons = lvl == 0 ? fieldsForLvl.get(0) : fieldsForLvl.get(1); 
        field.getChildren().clear();
          
              
         for (int i = 0; i < fieldSize; i++)
            for (int j = 0; j < fieldSize; j++){
                field.add(buttons[i][j], j, i);
                buttons[i][j].setText("");
                buttons[i][j].setGraphic(null);
                buttons[i][j].flagDefolt();
            }
         
        root.getChildren().add(field);
    }
    
    private ImageView getBompPic(){
        ImageView bombPic = new ImageView(new Image("bomb.jpg"));
        bombPic.setFitHeight(10); 
        bombPic.setFitWidth(10);
        return bombPic;
        
    }
    
    public void updateField(int[] cords, int val){
        
        if (!buttons[cords[0]][cords[1]].getText().equals("")) 
            return;
        
        if (val < 9){
            buttons[cords[0]][cords[1]].setText(val + "");
            
            if (buttons[cords[0]][cords[1]].getGraphic() != null){
                buttons[cords[0]][cords[1]].setGraphic(null);
                flagsControll(false);
            }
        }
        
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
         
         if (val >= 6 && val < 9){
             buttons[cords[0]][cords[1]].setStyle("-fx-text-fill: midnightblue; -fx-font: 15 arial");
         }
         
         if (val == 9){
              gameOver(cords);
         }
         
         if (val == 10){
             buttons[cords[0]][cords[1]].setGraphic(getBompPic());
//             upgreadeLvl();
//              try {
//                  Thread.sleep(rand.nextInt(300));
//              } catch (InterruptedException ex) {
//                  Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//              }
             return;
         }
         
         totalScore--;
         
         if (totalScore - bombs == 0)
             dialogOfendGame(true);
    }
    
    public void setLvl(int lvl, int fieldSize, int bombs){
        
        this.fieldSize = fieldSize;
        this.bombs = bombs;
        this.bombsFlags = bombs;
        this.lvl = lvl;
        
        root = new VBox(10);
        root.setAlignment(Pos.TOP_CENTER);
        
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(createMenu());
        
        bombsFlagsText = new Text(bombsFlags + "    ");
        Label bombsFlagsLabel = new Label("bombs: ");
        bombsFlagsLabel.setStyle("-fx-text-fill: tomato; -fx-font: 15 arial");
        bombsFlagsText.setStyle("-fx-text-fill: red; -fx-font: 15 arial");
        
        HBox displadeBombs = new HBox(10);
        displadeBombs.setAlignment(Pos.TOP_RIGHT);
        displadeBombs.getChildren().addAll(bombsFlagsLabel, bombsFlagsText);
        
       
        root.getChildren().addAll(menuBar, displadeBombs);
        
        if (lvl == 0)
            scene = new Scene(root, 235, 283);
        else 
            scene = new Scene(root, 695, 740);  
                   
        
        createField();
        totalScore = fieldSize * fieldSize;
        
        primaryStage.setResizable(false);   
        primaryStage.setTitle("Sapper");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

//    private void upgreadeLvl(){ //doesn't work
//        VBox a = new VBox(root);
//        primaryStage.setScene(new Scene(a, 232, 280));
//        primaryStage.show();
//    }

    public View(Stage primaryStage, Controller controller, int[] sizeForFields){
        this.primaryStage = primaryStage;
        this.controller = controller;
        
        for (int k = 0; k < sizeForFields.length; k++){
            buttons = new CellButton[sizeForFields[k]][sizeForFields[k]];
            for (int i = 0; i < sizeForFields[k]; i++){
                    for (int j = 0; j < sizeForFields[k]; j++){
                        buttons[i][j] = new CellButton(i, j, controller);
                    }
            }
            fieldsForLvl.add(buttons);
         }
        
        field = new GridPane();
        field.setAlignment(Pos.CENTER);
        
       

    }
   
}
