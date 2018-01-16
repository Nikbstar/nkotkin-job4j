package ru.nik66.figures;

import ru.nik66.Board;
import ru.nik66.Cell;
import ru.nik66.exceptions.ImpossibleMoveException;

public class Pawn extends Figure {

    public Pawn(Cell position) {
        super(position);
    }

    @Override
    public Cell[] way(Cell dest) throws ImpossibleMoveException {
        Cell[] result;
        if (this.position.getX() == Board.ROW_2 && Math.abs(this.position.getX() - dest.getX()) <= 2 && this.position.getY() == dest.getY()) {
            result = new Cell[Math.abs(this.position.getX() - dest.getX())];
            for (int i = 0; i < result.length; i++) {
                result[i] = new Cell(this.position.getX() - i - 1, this.position.getY());
            }
        } else if (this.position.getX() < Board.ROW_2 && Math.abs(this.position.getX() - dest.getX()) == 1 && this.position.getY() == dest.getY()) {
            result = new Cell[] {dest};
        } else {
            throw new ImpossibleMoveException("Wrong way for the Pawn.");
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new Pawn(dest);
    }
}
