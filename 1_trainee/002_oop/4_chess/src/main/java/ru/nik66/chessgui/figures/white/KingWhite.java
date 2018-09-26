package ru.nik66.chessgui.figures.white;

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
    public Cell[] way(Cell source, Cell dest) {
        return new Cell[]{dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingWhite(dest);
    }
}
