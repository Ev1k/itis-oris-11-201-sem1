package com.example.chatbot;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SnakeGame {
    private GraphicsContext gc;
    double cnt = 0.0;
    private int width = 20;
    private int height = 20;
    private int cellSize = 20;

    private double speed = 0.5;

    private boolean gameOver = false;
    private Direction direction = Direction.RIGHT;

    public void startGame(Stage gameStage) {
        gameStage.setTitle("Snake Game");
        Text text = new Text();
        text.setFont(new Font(40));
        text.setX(100);
        text.setY(10);
        text.setText(cnt+"");
        Circle circle = new Circle(50, 50, 5);
        Rectangle rectangle = new Rectangle((int) (Math.random() * width * cellSize), (int) (Math.random() * height * cellSize), 10, 10);
        Rectangle rectDeath = new Rectangle((int) (Math.random() * width * cellSize), (int) (Math.random() * height * cellSize), 10, 10);
        rectangle.setFill(Paint.valueOf("red"));
        rectDeath.setFill(Paint.valueOf("green"));
        Group gameRoot = new Group();
        Canvas canvas = new Canvas(width * cellSize, height * cellSize);
        gameRoot.getChildren().add(canvas);
        Scene gameScene = new Scene(gameRoot, width * cellSize, height * cellSize);
        GridPane pane = new GridPane();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gameScene.setOnKeyPressed(event -> {
                    KeyCode code = event.getCode();
                    switch (code) {
                        case UP:
                            direction = Direction.UP;
                            break;
                        case DOWN:
                            direction = Direction.DOWN;
                            break;
                        case LEFT:
                            direction = Direction.LEFT;
                            break;
                        case RIGHT:
                            direction = Direction.RIGHT;
                            break;
                    }
                }
        );

        // Создание текстового поля для отображения секундомера
        TextField timerField = new TextField();
        timerField.setEditable(false);
        timerField.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        timerField.setAlignment(Pos.CENTER);
        timerField.setPrefSize(100, 50);
        timerField.setText("0");
        // Создание таймера
//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
//            // Увеличение значения секундомера каждую секунду
//            int seconds = Integer.parseInt(timerField.getText());
//            seconds++;
//            timerField.setText(String.valueOf(seconds));
//        }));

        Timeline gameLoop = new Timeline(new KeyFrame(Duration.seconds(speed), event -> {
            if (!gameOver) {
                cnt += speed;
                if (cnt == 30) {
                    speed = 0.1;
                }
                System.out.println(cnt);
                moveCircle(circle);
                collizion(circle, rectangle);
                collizion(circle, rectDeath);
            }
        }));

        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        ObservableList children = gameRoot.getChildren();
        children.addAll(circle, rectangle, rectDeath);

        gameStage.setScene(gameScene);
        gameStage.show();
    }

    private void collizion(Circle c, Rectangle r) {
        if (
                (((r.getX() <= c.getCenterX()+c.getRadius()) && (r.getX() >= c.getCenterX() - c.getRadius())) // съел справа
                        && (c.getCenterY()+c.getRadius() >=r.getY()) && (c.getCenterY() - c.getRadius() >= r.getY() - r.getHeight()))
                        || ((r.getX() + r.getWidth() <= c.getCenterX()+c.getRadius()) && (r.getX() + r.getWidth() >= c.getCenterX() - c.getRadius())) // съел слева
                        && (c.getCenterY()+c.getRadius() >=r.getY()) && (c.getCenterY() - c.getRadius() >= r.getY() - r.getHeight())
        ) {
            if (r.getFill() != Paint.valueOf("green")) {
                eat(c, r);
            } else gameOver = true;
        }
    }

    private void eat(Circle circle, Rectangle r) {
        circle.setRadius(circle.getRadius()+5);
        r.setX((int) (Math.random() * width * cellSize));
        r.setY((int) (Math.random() * height * cellSize));
    }

    private void moveCircle(Circle circle) {
        switch (direction) {
            case UP:
                circle.setCenterY(circle.getCenterY() - 10);
                break;
            case DOWN:
                circle.setCenterY(circle.getCenterY() + 10);
                break;
            case LEFT:
                circle.setCenterX(circle.getCenterX() - 10);
                break;
            case RIGHT:
                circle.setCenterX(circle.getCenterX() + 10);
                break;
        }
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
