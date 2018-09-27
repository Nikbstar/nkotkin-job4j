package ru.nik66.chessgui;

import ru.nik66.chess.exceptions.FigureNotFoundException;
import ru.nik66.chess.exceptions.ImpossibleMoveException;
import ru.nik66.chess.exceptions.OccupiedWayException;
import ru.nik66.chessgui.figures.Cell;
import ru.nik66.chessgui.figures.Figure;

public class Logic {

    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) throws ImpossibleMoveException, FigureNotFoundException, OccupiedWayException {
        boolean result = false;
        int index = this.findBy(source);
        if (index == -1) {
            throw new FigureNotFoundException("Cell is empty.");
        }
        Cell[] steps = this.figures[index].way(dest);
        if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
            for (Cell step : steps) {
                if (findBy(step) != -1) {
                    throw new OccupiedWayException("Way is occupied.");
                }
            }
            result = true;
            this.figures[index] = this.figures[index].copy(dest);
        }
        return result;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int result = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                result = index;
                break;
            }
        }
        return result;
    }
}
