package ru.nik66.chessgui.figures.white;

import ru.nik66.chess.exceptions.ImpossibleMoveException;
import ru.nik66.chessgui.figures.Cell;
import ru.nik66.chessgui.figures.Figure;

public class KingWhite implements Figure {

    private final Cell position;

    public KingWhite(Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return position;
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        if (!((Math.abs(this.position.x - dest.x) <= 1) && (Math.abs(this.position.y - dest.y) <= 1))) {
            throw new ImpossibleMoveException("Wrong way for the White King.");
        }
        return new Cell[]{dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingWhite(dest);
    }
}
