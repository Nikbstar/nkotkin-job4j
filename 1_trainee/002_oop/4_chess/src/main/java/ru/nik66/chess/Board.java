package ru.nik66.chess;

import ru.nik66.chess.exceptions.FigureNotFoundException;
import ru.nik66.chess.exceptions.ImpossibleMoveException;
import ru.nik66.chess.exceptions.OccupiedWayException;
import ru.nik66.chess.figures.Figure;

public class Board {

    public static final int ROW_2 = 6;
    public static final int ROW_7 = 1;
    public static final int ROWS = 8;
    public static final int COLUMNS = 8;

    public Figure[][] figures = new Figure[ROWS][COLUMNS];

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        if (this.figures[source.getX()][source.getY()] == null) {
            throw new FigureNotFoundException("Cell is empty.");
        }

        Cell[] way = this.figures[source.getX()][source.getY()].way(dest);

        for (Cell cell : way) {
            if (!(this.figures[cell.getX()][cell.getY()] == null)) {
                throw new OccupiedWayException("Way is occupied.");
            }
        }

        this.figures[dest.getX()][dest.getY()] = this.figures[source.getX()][source.getY()].copy(dest);
        this.figures[source.getX()][source.getY()] = null;

        return true;
    }

}
