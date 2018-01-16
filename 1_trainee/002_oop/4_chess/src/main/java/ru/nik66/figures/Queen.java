package ru.nik66.figures;

import ru.nik66.Cell;
import ru.nik66.exceptions.ImpossibleMoveException;

public class Queen extends Figure {

    public Queen(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] result;

        if ((this.position.getX() == dest.getX()) && (this.position.getY() != dest.getY())) {
            result = new Cell[Math.abs(this.position.getY() - dest.getY())];
            for (int i = 0; i < result.length; i++) {
                result[i] = new Cell(this.position.getX(),
                        (this.position.getY() > dest.getY()) ? this.position.getY() - i - 1 : this.position.getY() + i + 1);
            }
        } else if ((this.position.getX() != dest.getX()) && (this.position.getY() == dest.getY())) {
            result = new Cell[Math.abs(this.position.getX() - dest.getX())];
            for (int i = 0; i < result.length; i++) {
                result[i] = new Cell(
                        (this.position.getX() > dest.getX()) ? this.position.getX() - i - 1 : this.position.getX() + i + 1,
                        this.position.getY());
            }
        } else if (Math.abs(this.position.getX() - dest.getX()) == Math.abs(this.position.getY() - dest.getY())) {
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
        } else {
            throw new ImpossibleMoveException("Wrong way for the Queen.");
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Queen(dest);
    }

}
