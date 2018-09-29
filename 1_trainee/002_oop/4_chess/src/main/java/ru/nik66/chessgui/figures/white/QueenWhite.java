package ru.nik66.chessgui.figures.white;

import ru.nik66.chess.exceptions.ImpossibleMoveException;
import ru.nik66.chessgui.figures.Cell;
import ru.nik66.chessgui.figures.Figure;

public class QueenWhite implements Figure {

    private final Cell position;

    public QueenWhite(Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] steps;
        if ((this.position.x == dest.x) && (this.position.y != dest.y)) {
            steps = new Cell[Math.abs(this.position.y - dest.y)];
            for (int i = 0; i < steps.length; i++) {
                steps[i] = Cell.findCell(
                        this.position.x,
                        (this.position.y > dest.y) ? this.position.y - i - 1 : this.position.y + i + 1
                );
            }
        } else if ((this.position.x != dest.x) && this.position.y == dest.y) {
            steps = new Cell[Math.abs(this.position.x - dest.x)];
            for (int i = 0; i < steps.length; i++) {
                steps[i] = Cell.findCell(
                        (this.position.x > dest.x) ? this.position.x - i - 1 : this.position.x + i + 1,
                        this.position.y
                );
            }
        } else if (Math.abs(this.position.x - dest.x) == Math.abs(this.position.y - dest.y)) {
            steps = new Cell[Math.abs(this.position.x - dest.x)];
            int rowOffset = (this.position.x < dest.x) ? 1 : -1;
            int colOffset = (this.position.y < dest.y) ? 1 : -1;
            int x = this.position.x + rowOffset;
            int y = this.position.y + colOffset;
            for (int i = 0; i < steps.length; i++) {
                steps[i] = Cell.findCell(x, y);
                x += rowOffset;
                y += colOffset;
            }
        } else {
            throw new ImpossibleMoveException("Wrong way for the White Queen.");
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new QueenWhite(dest);
    }
}
