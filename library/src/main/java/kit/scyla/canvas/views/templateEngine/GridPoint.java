package kit.scyla.canvas.views.templateEngine;

/**
 * Created with IntelliJ
 * Created by Nelaupe Lucas
 * Date 27/12/14
 */
public class GridPoint {

    private int row;
    private int column;

    public GridPoint(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}
