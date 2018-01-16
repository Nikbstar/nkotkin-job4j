package ru.nik66.figures;

import ru.nik66.Cell;
import ru.nik66.exceptions.ImpossibleMoveException;

public class Bishop extends Figure {

    public Bishop(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] result;

        if (Math.abs(this.position.getX() - dest.getX()) != Math.abs(this.position.getY() - dest.getY())) {
            throw new ImpossibleMoveException("Wrong way for the Bishop.");
        } else {
            result = new Cell[Math.abs(this.position.getX() - dest.getX())];
            int rowOffset = (this.position.getX() < dest.getX()) ? 1 : -1;
            int colOffset = (this.position.getY() < dest.getY()) ? 1 : -1;
            int x = this.position.getX() + rowOffset;
            int y = this.position.getY() + colOffset;
            for (int i = 0; i < result.length; i++) {
                result[i] = new Cell(x, y);
                x += rowOffset;
                y += colOffset;
            }
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }

}
