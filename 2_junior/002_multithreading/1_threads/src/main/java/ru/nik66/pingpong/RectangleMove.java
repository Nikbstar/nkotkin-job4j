package ru.nik66.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {

    private final Rectangle rectangle;

    public RectangleMove(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void run() {
        int x = 2;
        int y = 1;
        while (true) {
            if (Thread.interrupted()) {
                return;
            }
            double getX = this.rectangle.getX();
            double getY = this.rectangle.getY();
            if (getX <= 0 || getX >= 300) {
                x *= -1;
            }
            if (getY <= 0 || getY >= 300) {
                y *= -1;
            }
            this.rectangle.setX(getX + x);
            this.rectangle.setY(getY + y);
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
