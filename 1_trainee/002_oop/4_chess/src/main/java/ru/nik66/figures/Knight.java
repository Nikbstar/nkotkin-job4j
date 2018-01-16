package ru.nik66.figures;

import ru.nik66.Cell;
import ru.nik66.exceptions.ImpossibleMoveException;

public class Knight extends Figure {

    public Knight(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        if (!(((Math.abs(this.position.getX() - dest.getX()) == 1) && (Math.abs(this.position.getY() - dest.getY()) == 2))
                || ((Math.abs(this.position.getX() - dest.getX()) == 2) && (Math.abs(this.position.getY() - dest.getY()) == 1)))) {
            throw new ImpossibleMoveException("Wrong way for the Knight.");
        }

        return new Cell[] {dest};
    }

    @Override
    public Figure copy(Cell dest) {
        return new Knight(dest);
    }
}
