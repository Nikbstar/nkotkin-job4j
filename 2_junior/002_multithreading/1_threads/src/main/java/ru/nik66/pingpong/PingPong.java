package ru.nik66.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PingPong extends Application {

    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    @Override
    public void start(Stage primaryStage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rectangle = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rectangle);
        Thread rectangleThread = new Thread(new RectangleMove(rectangle));
        rectangleThread.start();
        primaryStage.setScene(new Scene(group, limitX, limitY));
        primaryStage.setTitle(JOB4J);
        primaryStage.setResizable(false);
        // primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.setOnCloseRequest(event -> rectangleThread.interrupt());
        primaryStage.show();
    }
}
