package ru.nik66.chessgui.figures.white;

import ru.nik66.chess.exceptions.ImpossibleMoveException;
import ru.nik66.chessgui.figures.Cell;
import ru.nik66.chessgui.figures.Figure;

public class RookWhite implements Figure {

    private final Cell position;

    public RookWhite(Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] result;
        if ((this.position.x == dest.x) && (this.position.y != dest.y)) {
            result = new Cell[Math.abs(this.position.y - dest.y)];
            for (int i = 0; i < result.length; i++) {
                result[i] = Cell.findCell(
                        this.position.x,
                        (this.position.y > dest.y) ? this.position.y - i - 1 : this.position.y + i + 1
                );
            }
        } else if ((this.position.x != dest.x) && this.position.y == dest.y) {
            result = new Cell[Math.abs(this.position.x - dest.x)];
            for (int i = 0; i < result.length; i++) {
                result[i] = Cell.findCell(
                        (this.position.x > dest.x) ? this.position.x - i - 1 : this.position.x + i + 1,
                        this.position.y
                );
            }
        } else {
            throw new ImpossibleMoveException("Wrong way for the White Rook");
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookWhite(dest);
    }
}
