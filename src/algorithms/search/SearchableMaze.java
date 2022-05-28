package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

/**
 * a class which used to solve a maze
 */
public class SearchableMaze implements ISearchable{
    public Maze my_maze;

    /**
     * a constructor
     * @param new_maze is the maze to search
     */
    public SearchableMaze(Maze new_maze){
        if (new_maze == null)
            this.my_maze = new Maze(30,30);
        else
        this.my_maze = new_maze;
    }
    public int get_rows_size(){
        return this.my_maze.get_length_row();
    }
    public int get_col_size(){
        return this.my_maze.get_length_col();
    }

    public AState getStartState(){
        if(this.my_maze == null || my_maze.getStartPosition() == null)
            return null;
       Position start = my_maze.getStartPosition();
       MazeState start_mazestate = new MazeState(start.getRowIndex(),start.getColumnIndex(), 0);
        return start_mazestate;
    }
    public AState  getGoalState(){
        Position end = my_maze.getGoalPosition();
        MazeState end_mazestate = new MazeState(end.getRowIndex(),end.getColumnIndex(),0);
        return end_mazestate;
    }

    /**
     * this function returns all legal steps from a specific Astate
     * @param s the specific Astate
     * @return ArrayList of all the Astate
     */
    public ArrayList<AState> getAllPossibleStates(AState s){
        if(s == null)
            return null;
        ArrayList<AState> ans = new ArrayList<>();
        int r = s.getRowIndex();
        int c = s.getColumnIndex();
        if(r == 0){
            if(c==0){
                if(this.my_maze.get_position(r,c+1) == 0) //right
                    ans.add(new MazeState(r,c+1, 10));
                if((this.my_maze.get_position(r,c+1) == 0 && this.my_maze.get_position(r+1,c+1) == 0) ||  (this.my_maze.get_position(r+1,c) == 0 && this.my_maze.get_position(r+1,c+1) == 0) ) //right-down
                    ans.add(new MazeState(r+1,c+1,15));
                if(this.my_maze.get_position(r+1,c) == 0) //down
                    ans.add(new MazeState(r+1,c, 10));

            }
            else if(c == my_maze.get_length_col()-1){

                if(this.my_maze.get_position(r+1,c) == 0) //down
                    ans.add(new MazeState(r+1,c, 10));
                if((this.my_maze.get_position(r,c-1) == 0 && this.my_maze.get_position(r+1,c-1) == 0) || (this.my_maze.get_position(r+1,c) == 0 && this.my_maze.get_position(r+1,c-1) == 0) ) //left-down
                    ans.add(new MazeState(r+1,c-1, 15));
                if(this.my_maze.get_position(r,c-1) == 0) //left
                    ans.add(new MazeState(r,c-1, 10));
            }
            else{//to continue from here
                if(this.my_maze.get_position(r,c+1) == 0) //right
                    ans.add(new MazeState(r,c+1, 10));
                if((this.my_maze.get_position(r+1,c+1) == 0 && this.my_maze.get_position(r,c+1) == 0) ||(this.my_maze.get_position(r+1,c+1) == 0 &&(this.my_maze.get_position(r+1,c) == 0 ))) //right-down
                    ans.add(new MazeState(r+1,c+1, 15));
                if(this.my_maze.get_position(r+1,c) == 0) //down
                    ans.add(new MazeState(r+1,c, 10));
                if((this.my_maze.get_position(r,c-1) == 0 && this.my_maze.get_position(r+1,c-1) == 0) || (this.my_maze.get_position(r+1,c-1) == 0 && (this.my_maze.get_position(r+1,c) == 0 )))  //left-down
                    ans.add(new MazeState(r+1,c-1, 15));
                if(this.my_maze.get_position(r,c-1) == 0) //left
                    ans.add(new MazeState(r,c-1, 10));
            }
        }
        else if(r == my_maze.get_length_row()-1){
            if(c==0){

                if(this.my_maze.get_position(r-1,c) == 0) { //up
                    ans.add(new MazeState(r - 1, c, 10));

                }
                if((this.my_maze.get_position(r-1,c) == 0 && this.my_maze.get_position(r-1,c+1) == 0)||(this.my_maze.get_position(r,c+1) == 0 && this.my_maze.get_position(r-1,c+1) == 0) ) //right-up
                    ans.add(new MazeState(r-1,c+1, 15));

                if(this.my_maze.get_position(r,c+1) == 0) { //right
                    ans.add(new MazeState(r, c + 1, 10));

                }


            }
            else if(c == my_maze.get_length_col()-1){

                if(this.my_maze.get_position(r-1,c) == 0) {//up
                    ans.add(new MazeState(r - 1, c,10));

                }
                if(this.my_maze.get_position(r,c-1) == 0) { //left
                    ans.add(new MazeState(r, c - 1,10));

                }

                if((this.my_maze.get_position(r-1,c) == 0 && this.my_maze.get_position(r-1,c-1) == 0)||(this.my_maze.get_position(r,c-1) == 0 && this.my_maze.get_position(r-1,c-1) == 0) ) //left-up
                    ans.add(new MazeState(r-1,c-1,15));

            }
            else{

                if(this.my_maze.get_position(r-1,c) == 0) { //up
                    ans.add(new MazeState(r - 1, c,10));

                }
                if((this.my_maze.get_position(r-1,c) == 0 && this.my_maze.get_position(r-1,c+1) == 0)||(this.my_maze.get_position(r,c+1) == 0 && this.my_maze.get_position(r-1,c+1) == 0) ) //right-up
                    ans.add(new MazeState(r-1,c+1,15));
                if(this.my_maze.get_position(r,c+1) == 0) { //right
                    ans.add(new MazeState(r, c + 1,10));

                }
                if(this.my_maze.get_position(r,c-1) == 0) { //left
                    ans.add(new MazeState(r, c - 1,10));

                }

                if((this.my_maze.get_position(r-1,c) == 0 && this.my_maze.get_position(r-1,c-1) == 0)||(this.my_maze.get_position(r,c-1) == 0 && this.my_maze.get_position(r-1,c-1) == 0) ) //left-up
                    ans.add(new MazeState(r-1,c-1,15));

            }

        }
        else{
            if(c==0){
                if(this.my_maze.get_position(r-1,c) == 0) //up
                    ans.add(new MazeState(r-1,c,10));
                if((this.my_maze.get_position(r-1,c) == 0 && this.my_maze.get_position(r-1,c+1) == 0)||(this.my_maze.get_position(r,c+1) == 0 && this.my_maze.get_position(r-1,c+1) == 0) ) //right-up
                    ans.add(new MazeState(r-1,c+1,15));
                if(this.my_maze.get_position(r,c+1) == 0) { //right
                    ans.add(new MazeState(r, c + 1,10));
                }
                if((this.my_maze.get_position(r+1,c+1) == 0 && this.my_maze.get_position(r,c+1) == 0) ||(this.my_maze.get_position(r+1,c+1) == 0 &&(this.my_maze.get_position(r+1,c) == 0 ))) //right-down
                    ans.add(new MazeState(r+1,c+1,15));
                if(this.my_maze.get_position(r+1,c) == 0) //down
                    ans.add(new MazeState(r+1,c,10));
            }
            else if(c ==my_maze.get_length_col()-1){
                if (this.my_maze.get_position(r - 1, c) == 0) //up
                    ans.add(new MazeState(r - 1, c,10));
                if (this.my_maze.get_position(r + 1, c) == 0) //down
                    ans.add(new MazeState(r + 1, c,10));
                if ((this.my_maze.get_position(r, c - 1) == 0 && this.my_maze.get_position(r + 1, c - 1) == 0) || (this.my_maze.get_position(r + 1, c - 1) == 0 && (this.my_maze.get_position(r + 1, c) == 0)))  //left-down
                    ans.add(new MazeState(r + 1, c - 1,15));
                if (this.my_maze.get_position(r, c - 1) == 0) { //left
                    ans.add(new MazeState(r, c - 1,10));
                }
                if ((this.my_maze.get_position(r, c - 1) == 0 && this.my_maze.get_position(r - 1, c - 1) == 0) || (this.my_maze.get_position(r - 1, c) == 0 && this.my_maze.get_position(r - 1, c - 1) == 0)) //left-up
                    ans.add(new MazeState(r - 1, c - 1,15));
            }
            else {
                if (this.my_maze.get_position(r - 1, c) == 0) //up
                    ans.add(new MazeState(r - 1, c,10));
                if ((this.my_maze.get_position(r - 1, c) == 0 && this.my_maze.get_position(r - 1, c + 1) == 0) || (this.my_maze.get_position(r, c + 1) == 0 && this.my_maze.get_position(r - 1, c + 1) == 0)) //right-up
                    ans.add(new MazeState(r - 1, c + 1,15));
                if (this.my_maze.get_position(r, c + 1) == 0) { //right
                    ans.add(new MazeState(r, c + 1,10));
                }
                if ((this.my_maze.get_position(r + 1, c + 1) == 0 && this.my_maze.get_position(r, c + 1) == 0) || (this.my_maze.get_position(r + 1, c + 1) == 0 && (this.my_maze.get_position(r + 1, c) == 0))) //right-down
                    ans.add(new MazeState(r + 1, c + 1,15));
                if (this.my_maze.get_position(r + 1, c) == 0) //down
                    ans.add(new MazeState(r + 1, c,10));
                if ((this.my_maze.get_position(r, c - 1) == 0 && this.my_maze.get_position(r + 1, c - 1) == 0) || (this.my_maze.get_position(r + 1, c - 1) == 0 && (this.my_maze.get_position(r + 1, c) == 0)))  //left-down
                    ans.add(new MazeState(r + 1, c - 1,15));
                if (this.my_maze.get_position(r, c - 1) == 0) { //left
                    ans.add(new MazeState(r, c - 1,10));
                }
                if ((this.my_maze.get_position(r, c - 1) == 0 && this.my_maze.get_position(r - 1, c - 1) == 0) || (this.my_maze.get_position(r - 1, c) == 0 && this.my_maze.get_position(r - 1, c - 1) == 0)) //left-up
                    ans.add(new MazeState(r - 1, c - 1,15));
            }

        }
        return ans;
    }


}
