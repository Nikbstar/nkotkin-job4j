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
            if ((this.position.getX() > dest.getX()) && (this.position.getY() > dest.getY())) {
                for (int i = 0; i < result.length; i++) {
                    result[i] = new Cell(this.position.getX() - i - 1, this.position.getY() - i - 1);
                }
            } else if ((this.position.getX() > dest.getX()) && (this.position.getY() < dest.getY())) {
                for (int i = 0; i < result.length; i++) {
                    result[i] = new Cell(this.position.getX() - i - 1, this.position.getY() + i + 1);
                }
            } else if ((this.position.getX() < dest.getX()) && (this.position.getY() < dest.getY())) {
                for (int i = 0; i < result.length; i++) {
                    result[i] = new Cell(this.position.getX() + i + 1, this.position.getY() + i + 1);
                }
            } else if ((this.position.getX() < dest.getX()) && (this.position.getY() > dest.getY())) {
                for (int i = 0; i < result.length; i++) {
                    result[i] = new Cell(this.position.getX() + i + 1, this.position.getY() - i - 1);
                }
            }
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Bishop(dest);
    }

}
