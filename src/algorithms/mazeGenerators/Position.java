package algorithms.mazeGenerators;

/**
 * class which represent a cell in the maze
 */
public class Position {
        private int rows;
        private int columns;

        public Position(int r, int c){
            this.rows = r;
            this.columns = c;
        }
        public int getRowIndex(){
            return this.rows;
        }
        public int getColumnIndex(){
            return this.columns;
        }
        public String toString(){
            return "{"+this.rows+","+this.columns+"}";
        }

}
