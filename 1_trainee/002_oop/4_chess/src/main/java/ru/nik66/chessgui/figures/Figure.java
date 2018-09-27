package ru.nik66.chessgui.figures;

import ru.nik66.chess.exceptions.ImpossibleMoveException;

public interface Figure {

    Cell position();

    Cell[] way(Cell dest) throws ImpossibleMoveException;

    default String icon() {
        return String.format(
                "%s.png", this.getClass().getSimpleName()
        );

    }

    Figure copy(Cell dest);


}
