package sapper;

import java.util.Arrays;
import java.util.Random;


public class Field {

    private int[][] field;
    private int fieldSize;
    private int bombs;
    private boolean[][] buf;


    public Field(int fieldSize, int bombs) {
        field = new int[fieldSize][fieldSize];
        this.fieldSize = fieldSize;
        this.bombs = bombs;
        makeField();
    }


    public int getCell(int x, int y){
        return field[x][y];
    }

    public boolean[][] getEmptyCells(int x, int y){
        buf = new boolean[fieldSize][fieldSize];
        for (int i = 0; i < fieldSize; i++) {
            Arrays.fill(buf[i], false);
        }
        
        DFSonMin(x, y);
        return buf;
    }

    public void  DFSonMin(int x, int y){

        if (x < 0 || y < 0 || y >= fieldSize || x >= fieldSize)
            return ;

        if (buf[x][y])
            return ;

        if (field[x][y] != 0){
            return ;
        }
        else {
            buf[x][y] = true;

             DFSonMin(x+1, y);
             DFSonMin(x, y+1);
             DFSonMin(x-1, y);
             DFSonMin(x, y-1);

        }

        return ;
    }

    private int bombsAround(int x, int y){

        int count = 0;

        if ( y+1 < fieldSize && x+1 < fieldSize && field[x+1][y+1] == 9)
            count++;

        if ( x+1 < fieldSize && field[x+1][y] == 9)
            count++;

        if ( y+1 < fieldSize && field[x][y+1] == 9)
            count++;

        if ( x-1 >= 0 && y-1 >= 0 && field[x-1][y-1] == 9)
            count++;

        if ( x-1 >= 0 && field[x-1][y] == 9)
            count++;

        if ( y-1 >= 0 && field[x][y-1] == 9)
            count++;

        if ( y+1 < fieldSize && x-1 >= 0 && field[x-1][y+1] == 9)
            count++;

        if ( x+1 < fieldSize && y-1 >= 0 && field[x+1][y-1] == 9)
            count++;

        return count;
    }


    private void makeField(){

        Random random = new Random();

        field = new int[fieldSize][fieldSize];

        int x, y;
        for (int i = 0; i < bombs; i++){
            x = random.nextInt(fieldSize);
            y = random.nextInt(fieldSize);

            if (field[x][y] != 9){
                field[x][y] = 9;
            }
            else {
                i--;
            }
        }

        for(int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++)
                System.out.print(field[i][j] + "  ");
            System.out.println();
        }

        System.out.println();

        for(int i = 0; i < fieldSize; i++)
            for(int j = 0; j < fieldSize; j++){

                if(field[i][j] == 9)
                    continue;

                field[i][j] = bombsAround(i, j);
            }

        for(int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++)
                System.out.print(field[i][j] + "  ");
            System.out.println();
        }
    }
}