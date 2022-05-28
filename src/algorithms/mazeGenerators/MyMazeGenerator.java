package algorithms.mazeGenerators;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * class which generate a random maze
 */
public class MyMazeGenerator extends AMazeGenerator {

    /**
     * generate the maze with creation function
     * @param rows the number of the rows in the maze
     * @param columns the number of the columns in the maze
     * @return a random maze
     */
    @Override
    public Maze generate(int rows, int columns){
        Maze mymaze =  new Maze(rows,columns);
        if(mymaze.getStartPosition()== null)
            return null;
        for(int i=0; i< mymaze.get_length_row();i++){
            for (int j=0; j<mymaze.get_length_col();j++){
                mymaze.set_position(i,j,1);
            }
        }
        mymaze.set_position(mymaze.start.getRowIndex(),mymaze.start.getColumnIndex(), 0);
        creation(mymaze.start.getRowIndex(),mymaze.start.getColumnIndex(), mymaze);
        mymaze.set_position(mymaze.end.getRowIndex(),mymaze.end.getColumnIndex(), 0);
        return mymaze;
    }

    /**
     * the main function of this class. generate a random maze
     * @param r the number of the rows
     * @param c the number of the columns
     * @param maze a new maze
     */
    public void creation(int r, int c, Maze maze) {
        if(maze == null)
            return;
        Integer[] randDirs = generateRandomDirections();
        Stack<Position> positionStack = new Stack<>();
        positionStack.push(maze.start);
        Position now;
        while(positionStack.size()>0) {

            for (int i = 0; i < randDirs.length; i++) {
                switch (randDirs[i]) {
                    case 1: // Up
                        //　check if possible
                        if (r - 2 < 0) {
                            continue;
                        }

                        if (maze.get_position(r - 2,c) != 0) {
                            maze.set_position(r - 2,c, 0);
                            maze.set_position(r - 1,c, 0);
                            now = new Position(r-2,c);
                            positionStack.push(now);
                            r = now.getRowIndex();
                            c = now.getColumnIndex();
                            i=0;

                        }
                        break;
                    case 2: // Right
                        // check if possible
                        if (c + 2 >= maze.get_length_col()) {
                            continue;
                        }
                        if (maze.get_position(r ,c+2) != 0) {
                            maze.set_position(r,c + 2,0);
                            maze.set_position(r,c + 1,0);
                            now = new Position(r,c+2);
                            positionStack.push(now);
                            r = now.getRowIndex();
                            c = now.getColumnIndex();
                            i=0;

                        }
                        break;
                    case 3: // Down
                        // check if possible
                        if (r + 2 >= maze.get_length_row()) {
                            continue;
                        }
                        if (maze.get_position(r + 2,c) != 0) {
                            maze.set_position(r + 2,c, 0);
                            maze.set_position(r + 1,c, 0);
                            now = new Position(r+2,c);
                            positionStack.push(now);
                            r = now.getRowIndex();
                            c = now.getColumnIndex();
                            i=0;
                        }
                        break;
                    case 4: // Left
                        // check if possible
                        if (c - 2 < 0)
                            continue;
                        if (maze.get_position(r ,c-2) != 0) {
                            maze.set_position(r,c-2,0);
                            maze.set_position(r,c-1,0);
                            now = new Position(r,c-2);
                            positionStack.push(now);
                            r = now.getRowIndex();
                            c = now.getColumnIndex();
                            i=0;

                        }
                        break;
                }

            }

            positionStack.pop();
            if (positionStack.size() == 0)
                break;
            now = positionStack.peek();
            r = now.getRowIndex();
            c = now.getColumnIndex();
        }
    }


    /**
     * function who randomize a direction
     * @return a list of random directions
     */
    public Integer[] generateRandomDirections() {
        ArrayList<Integer> randoms = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            randoms.add(i + 1);
        Collections.shuffle(randoms);

        return randoms.toArray(new Integer[4]);
    }
}
