package ru.nik66.chessgui.figures.black;

import ru.nik66.chessgui.figures.Cell;
import ru.nik66.chessgui.figures.Figure;

public class KingBlack implements Figure {

    private final Cell position;

    public KingBlack(Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new KingBlack(dest);
    }
}
