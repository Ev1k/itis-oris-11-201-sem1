package com.example.sqrltrbl.game.systems;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import lombok.Setter;
import com.example.sqrltrbl.game.entities.DynamicEntity;
import com.example.sqrltrbl.game.entities.StaticEntity;

import java.util.List;

public class RenderingSystem extends StaticEntitySystem {
    @Setter
    private int playerId;
    private Canvas canvas;

    @Setter
    private int winningPlayer;

    public RenderingSystem(int playerId, Canvas canvas) {
        this.playerId = playerId;
        this.canvas = canvas;
        this.winningPlayer = -1;
    }

    @Override
    public void update(double dt) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image squirrelImg = new Image("C:\\Users\\Эвелина\\HomeWorks_2_sem\\itis-oris-11-201-sem1\\semestrial2\\src\\main\\java\\com\\example\\sqrltrbl\\images\\squirrel.png");
        Image squirrelNutImg = new Image("C:\\Users\\Эвелина\\HomeWorks_2_sem\\itis-oris-11-201-sem1\\semestrial2\\src\\main\\java\\com\\example\\sqrltrbl\\images\\squirrelWithNut.png");
        Image nutImg = new Image("C:\\Users\\Эвелина\\HomeWorks_2_sem\\itis-oris-11-201-sem1\\semestrial2\\src\\main\\java\\com\\example\\sqrltrbl\\images\\nut.png");
        Image treeImg = new Image("C:\\Users\\Эвелина\\HomeWorks_2_sem\\itis-oris-11-201-sem1\\semestrial2\\src\\main\\java\\com\\example\\sqrltrbl\\images\\tree.png");
        Image landImg = new Image("C:\\Users\\Эвелина\\HomeWorks_2_sem\\itis-oris-11-201-sem1\\semestrial2\\src\\main\\java\\com\\example\\sqrltrbl\\images\\land.png");

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if (winningPlayer == -1) {
            List<DynamicEntity> dynamicEntities = entities.stream()
                    .filter(e -> e instanceof DynamicEntity)
                    .map(e -> (DynamicEntity) e)
                    .toList();

            List<StaticEntity> staticEntities = entities.stream()
                    .filter(e -> !(e instanceof DynamicEntity))
                    .toList();

            staticEntities.forEach(e -> {
                if (e.getNutOrHollow() == 1) {
                    gc.drawImage(nutImg, e.getX(), e.getY(), e.getWidth(), e.getHeight());
                } else if (e.getNutOrHollow() == 2) {
                    gc.drawImage(treeImg, e.getX(), e.getY(), e.getWidth(), e.getHeight());
                } else {
                    gc.drawImage(landImg, e.getX(), e.getY(), e.getWidth(), e.getHeight());
                }
            });

            dynamicEntities.stream()
                    .filter(e -> e.getId() != playerId)
                    .forEach(e -> {
                        gc.drawImage(squirrelImg, e.getX(), e.getY(), e.getWidth(), e.getHeight());
                    });

            dynamicEntities.stream()
                    .filter(e -> e.getId() == playerId)
                    .forEach(e -> {
                        gc.drawImage(squirrelImg, e.getX(), e.getY(), e.getWidth(), e.getHeight());
                    });
        } else {
            if(winningPlayer == playerId){
                gc.fillText("You won!", 200, 200);
            }else{
                gc.fillText("Player " + winningPlayer + " won", 200, 200);
            }
        }
    }
}
