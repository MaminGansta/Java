/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sapper;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;

/**
 *
 * @author lil
 */
public class Controller {
    
    private View view;
    private Field field;
    private int fieldSize;
    private int bombs;
    private boolean[][] trip;
    
    
    public Controller(Stage primaryStage){
       view = new View(primaryStage, this, new int[]{7, 21});
    }

    
    public void game(int lvl){
       fieldSize = lvl == 0 ? 7 : 21;
       bombs = lvl == 0 ? 7 : 60;
       field = new Field(fieldSize, bombs);
       view.setLvl(lvl, fieldSize, bombs);
    }
    
   public void setCell (int[] cords){
            view.updateField(cords, field.getCell(cords[0], cords[1]));      
   }
    
    public void setContent(int[] cords){
        
        boolean[][] emptyCords;
        
        if (field.getCell(cords[0], cords[1]) != 0){
            setCell(cords);
        }else {
            
            emptyCords = field.getEmptyCells(cords[0], cords[1]);
            
            for (int i  = 0; i < fieldSize; i++)
                for (int j = 0; j < fieldSize; j++){
                    
                    if (emptyCords[i][j])
                        setCell(new int[] {i, j});
                 
                    
                    if (!emptyCords[i][j] && 
                            (((i+1<fieldSize && emptyCords[i+1][j]) || (j+1<fieldSize && emptyCords[i][j+1]))
                            || ((i-1>=0 && emptyCords[i-1][j]) || (j-1>=0 && emptyCords[i][j-1]))
                            || (i+1<fieldSize && j+1<fieldSize && i-1>=0 && j-1>=0 && (emptyCords[i-1][j-1] || emptyCords[i+1][j+1] || emptyCords[i-1][j+1] || emptyCords[i+1][j-1])))){
                                      
                        
                        setCell(new int[] {i, j});
                    }
                }
            
        }
    }
    
    public void showBombs(int[] cords){
        this.trip = new boolean[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++){
            Arrays.fill(trip[i], false);
        }
        findBombs(cords[0], cords[1]);
    }
    
    private void findBombs(int x, int y){
          if (x < 0 || y < 0 || y >= fieldSize || x >= fieldSize)
            return ;
          
        if (trip[x][y])
            return;
        else trip[x][y] = true;
        
                           
        if (field.getCell(x, y) == 9){
             
            view.updateField(new int[]{x, y}, 10);
        }
    
        findBombs(x+1, y);
        findBombs(x, y+1);
        findBombs(x-1, y);
        findBombs(x, y-1);
                
        return ;
    }
    
    public void flagControll(boolean flag){
        view.flagsControll(flag);
    }

}
