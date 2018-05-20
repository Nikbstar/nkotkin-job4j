package ru.nik66.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {

    private final Rectangle rectangle;

    public RectangleMove(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void run() {
        int x = 1;
        int y = 1;
        while (true) {
            double getX = this.rectangle.getX();
            double getY = this.rectangle.getY();
            if (getX <= 0 || getX >= 290) {
                x *= -1;
            }
            if (getY <= 0 || getY >= 290) {
                y *= -1;
            }
            this.rectangle.setX(getX + x);
            this.rectangle.setY(getY + y);
            try {
                Thread.sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
