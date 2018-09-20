package ru.nik66.chess.figures;

import ru.nik66.chess.Cell;
import ru.nik66.chess.exceptions.ImpossibleMoveException;

public class Rook extends Figure {

    public Rook(Cell position) {
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
        } else {
            throw new ImpossibleMoveException("Wrong way for the Rook.");
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Rook(dest);
    }

}
