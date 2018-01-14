package ru.nik66;

import ru.nik66.exceptions.FigureNotFoundException;
import ru.nik66.exceptions.ImpossibleMoveException;
import ru.nik66.exceptions.OccupiedWayException;
import ru.nik66.figures.Figure;

public class Board {

    public static final int BOARD_X = 8;
    public static final int BOARD_Y = 8;

    public Figure[][] figures = new Figure[BOARD_X][BOARD_Y];

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
