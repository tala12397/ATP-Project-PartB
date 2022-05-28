package algorithms.mazeGenerators;

/**
 * a class which generate a simple maze with simple algorithm
 */
public class SimpleMazeGenerator extends AMazeGenerator {

    /**
     * the main function in the class. generate the maze.
     * @param rows the number of the rows in the maze
     * @param columns the number of the columns in the maze
     * @return a new maze.
     */
    public Maze generate(int rows, int columns){
        Maze simplemaze = new Maze(rows,columns);
        if(simplemaze.start.getRowIndex()<simplemaze.end.getRowIndex()){
            int counter = simplemaze.start.getRowIndex() + 1;
            while(counter <= simplemaze.end.getRowIndex()){
                simplemaze.set_position(counter,simplemaze.start.getColumnIndex(), 0);
                counter++;
            }
            if(simplemaze.start.getColumnIndex()<simplemaze.end.getColumnIndex()){
                int counte = simplemaze.start.getColumnIndex() + 1;
                while(counte <= simplemaze.end.getColumnIndex()){
                    simplemaze.set_position(simplemaze.start.getRowIndex(),counte , 0);
                    counte++;
                }

            }
            else{
                int counte = simplemaze.end.getColumnIndex() + 1;
                while(counte <= simplemaze.start.getColumnIndex()){
                    simplemaze.set_position(simplemaze.end.getRowIndex(),counte ,0);
                    counte++;
                }
            }

        }
        else{
            int counter = simplemaze.end.getRowIndex() + 1;
            while(counter <= simplemaze.start.getRowIndex()){
                simplemaze.set_position(counter,simplemaze.end.getColumnIndex(), 0);
                counter++;
            }
            if(simplemaze.start.getColumnIndex()<simplemaze.end.getColumnIndex()){
                int counte = simplemaze.start.getColumnIndex() + 1;
                while(counte <= simplemaze.end.getColumnIndex()){
                    simplemaze.set_position(simplemaze.end.getRowIndex(),counte, 0);
                    counte++;
                }

            }
            else{
                int counte = simplemaze.end.getColumnIndex() + 1;
                while(counte <= simplemaze.start.getColumnIndex()){
                    simplemaze.set_position(simplemaze.start.getRowIndex(),counte, 0);
                    counte++;
                }
            }
        }

        return simplemaze;

    }

}
