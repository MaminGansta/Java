/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sapper;

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
    
    
    public Controller(Stage primaryStage){
       view = new View(primaryStage, this, 0, 7, 7);
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
    
    public void flagControll(boolean flag){
        view.flagsControll(flag);
    }

}
