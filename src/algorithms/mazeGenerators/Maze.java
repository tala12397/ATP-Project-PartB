package algorithms.mazeGenerators;

import java.io.Serializable;
import java.util.Random;

/**
 * the object maze itself
 */
public class Maze implements Serializable {
    private int [][] maze;
    Position start;
    Position end;

    /**
     * class constructor
     * @param row the number of the rows in the maze
     * @param col the number of the columns in the maze
     */
    public Maze(int row, int col){
        if(row == 0 || col == 0){
            row = 30;
            col = 30;
        }
        Random rand = new Random();
        this.maze = new int[row][col];
        for(int i=0;i<row;i++) {
            for (int j = 0; j < col; j++)
                this.maze[i][j] = rand.nextInt(2);
        }

        int startx = rand.nextInt(row);
        while(startx%2==0){
            startx = rand.nextInt(row);
        }
        int starty;
        if(startx==(row-1)){
            starty = rand.nextInt(col);
            while(starty%2==1)
                starty = rand.nextInt(col);
        }
        else{
            starty = rand.nextInt(2);
            if (starty == 1){
                starty = col-1;
            }
        }
        this.start = new Position(startx,starty);
        int endx = rand.nextInt(row);
        while(endx%2 ==0){
            endx = rand.nextInt(row);
        }
        int endy = 0;
        if(endx==(row-1) || endx == 0){
            endy = rand.nextInt(col);
        }
        while(endx==startx && endy == starty){
            endx = rand.nextInt(row);
        }
        this.end = new Position(endx,endy);
        this.maze[startx][starty] = 0;
        this.maze[endx][endy] = 0;

    }
    public Maze(byte[] bytes){
        int rows = bytes[0]*100 + bytes[1];
        int col = bytes[2]*100 + bytes[3];
        this.maze = new int[rows][col];
        this.start = new Position((bytes[4]*100) + bytes[5], (bytes[6]*100) + bytes[7]);
        this.end = new Position((bytes[8]*100)+bytes[9], (bytes[10]*100)+bytes[11]);
        int index = 12;
        for(int i = 0;i<rows;i++){
            for(int j=0;j<col;j++){
                this.maze[i][j] = bytes[index];
                index++;
            }
        }

    }

    /**
     * class constructor
     * @param row the number of the rows in the maze
     * @param col the number of the columns in the maze
     * @param num is the number of all the cells
     */
    public Maze(int row, int col, int num){
        if(row == 0 || col == 0){
            row = 30;
            col = 30;
        }
        this.maze = new int[row][col];
        for(int i=0;i<row;i++) {
            for (int j = 0; j < col; j++)
                this.maze[i][j] = num;
        }
        this.start = new Position(0,0);
        this.end = new Position(row-1, col-1);
    }
    public Position getStartPosition(){
        return this.start;
    }
    public Position getGoalPosition(){
        return this.end;
    }
    public int get_position(int r, int c){
        return this.maze[r][c];
    }
    public void set_position(int r, int c, int num_to_Set){
        if(num_to_Set == 0 || num_to_Set==1)
            this.maze[r][c] = num_to_Set;
    }
    public int get_length_row(){
        return this.maze.length;
    }
    public int get_length_col(){
        return this.maze[0].length;
    }

    public void print() {
        System.out.print("{");
        for (int i = 0; i < this.maze.length; i++) {
            if(i==0)
                System.out.print("{");
            else
                System.out.print(",{");
            for (int j = 0; j < this.maze[i].length-1; j++) {
                if(this.start.getRowIndex() == i && this.start.getColumnIndex() == j){
                    System.out.print("S,");
                }
                else if(this.end.getRowIndex() == i && this.end.getColumnIndex() == j){
                    System.out.print("E,");
                }
                else {
                    System.out.print(this.maze[i][j] + ",");
                }
            }
            if(this.start.getRowIndex() == i && this.start.getColumnIndex() == this.maze[i].length-1){
                System.out.print("S");
            }
            else if(this.end.getRowIndex() == i && this.end.getColumnIndex() == this.maze[i].length-1){
                System.out.print("E");
            }
            else {
                System.out.print(this.maze[i][this.maze[i].length-1]);
            }
            if(i==this.maze.length-1){
                System.out.print("}}");
                System.out.println();
            }
            else {
                System.out.print("}");
                System.out.println();
            }

        }
    }
    public byte[] toByteArray(){
       byte[] bytes = new byte[(maze.length * maze[0].length)+12];
       bytes[0] = (byte)(maze.length/100); //numofrows
       bytes[1] = (byte)(maze.length%100);
       bytes[2] =  (byte)(maze[0].length/100); //numofcols
        bytes[3] = (byte)(maze[0].length%100);
       bytes[4] = (byte)(start.getRowIndex()/100); //row start
        bytes[5] = (byte)(start.getRowIndex()%100);
       bytes[6] = (byte)(start.getColumnIndex()/100);
        bytes[7] = (byte)(start.getColumnIndex()%100);// col start
       bytes[8] = (byte)(end.getRowIndex()/100); // row end
        bytes[9] = (byte)(end.getRowIndex()%100);
       bytes[10] = (byte)(end.getColumnIndex()/100); // col end
        bytes[11] = (byte)(end.getColumnIndex()%100);
       int index = 12;
       for(int i = 0; i<maze.length;i++){
           for(int j=0; j<maze[0].length;j++){
               bytes[index] =(byte)maze[i][j];
               index++;
           }
       }
       return bytes;





    }


}


