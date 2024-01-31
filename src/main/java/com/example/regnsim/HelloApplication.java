package com.example.regnsim;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    ArrayList<Rectangle> rain = new ArrayList<>();
    Rectangle body = new Rectangle(-30, 200, 30, 100);
    Label hit = new Label("Hits: ");
    Group root;
    int frame = 0;
    int hits = 0;
    int d = 0;
    double Vy = 0.1 *5;
    double Vx = -0.1 *5 ;

    double angle = Math.atan(Vy / Vx);

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        root = new Group();
        Scene scene = new Scene(root, 1500, 600);
        root.getChildren().addAll(hit);
        stage.setTitle("rainSim");
        stage.setScene(scene);
        stage.show();
        runner();

    }

    void runner() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(5), (ActionEvent event) -> {
            System.out.println(angle);
            frame++;
            if (frame % 30 == 0) {
                for (int i = 0; i < 200; i++) {
                    Rectangle raine = new Rectangle(i * 5 + 500, 0, 1, 1);
                    root.getChildren().add(raine);
                    rain.add(raine);
                }
            }
            for (int i = 0; i < rain.size(); i++) {
                Rectangle r = rain.get(i);
                r.setY(r.getY() + Vy);
                r.setX(r.getX() + Vx);
                if (r.getY() >= body.getY() && r.getY() <= body.getY() + 100 && r.getX() <= body.getX() + 30 && r.getX() >= body.getX() && d == 1) {
                    rain.remove(r);
                    hits++;
                    hit.setText("Hits: " + hits);
                }
                if (r.getY() >= 600 || r.getX() <= 0|| r.getX() >= 1000) {
                    rain.remove(r);
                    root.getChildren().remove(r);
                }
            }
            if (frame > 200 && rain.get(0).getY() > body.getY() + 110) {

                if (d == 0) {
                    root.getChildren().add(body);
                    d = 1;
                }

                body.setX(body.getX() + 0.03 * 5);
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

}