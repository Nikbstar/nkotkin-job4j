package ru.nik66.chessgui.figures.white;

import ru.nik66.chessgui.figures.Cell;
import ru.nik66.chessgui.figures.Figure;

public class PawnWhite implements Figure {

    private final Cell position;

    public PawnWhite(Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell dest) {
        Cell[] steps = new Cell[0];
        if (this.position.y == dest.y - 1 && this.position.x == dest.x) {
            steps = new Cell[] {dest};
        }
        if (this.position.y == 1 && this.position.y == dest.y - 2 && this.position.x == dest.x) {
            steps = new Cell[2];
            steps[0] = Cell.findCell(dest.x, dest.y - 1);
            steps[1] = dest;
        }
        return steps;
     }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest);
    }
}
