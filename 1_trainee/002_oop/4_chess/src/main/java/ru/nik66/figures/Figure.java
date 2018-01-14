package ru.nik66.figures;

import ru.nik66.Cell;
import ru.nik66.exceptions.ImpossibleMoveException;

public abstract class Figure {

    public final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    public abstract Cell[] way(Cell dest) throws ImpossibleMoveException;

    public abstract Figure copy(Cell dest);

}
