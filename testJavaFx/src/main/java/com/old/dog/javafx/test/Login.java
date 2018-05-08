package com.old.dog.javafx.test;/**
 * Created by hanyong on 2018/5/7.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.MessageFormat;
import java.util.Date;


/**
 * @author hanyong
 * @Date 2018/5/7
 */
public class Login extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("login");
        primaryStage.setScene(genSceneOfLogin());
        primaryStage.show();

    }

    private Scene genSceneOfLogin() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);


        Button btn = new Button("Sign In");
        HBox hbbtn = new HBox(10);
        hbbtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbbtn.getChildren().add(btn);

        grid.add(hbbtn, 1, 4);


        Text actiontraget = new Text();
        grid.add(actiontraget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            private int flag = 0;
            @Override
            public void handle(ActionEvent event) {
                if (flag++ % 2 == 0) {
                    actiontraget.setFill(Color.GREEN);
                    actiontraget.setText("Sign in button pressed");
                } else {
                    actiontraget.setFill(Color.RED);
                    actiontraget.setText("Please change the input");
                }

            }
        });

        grid.setGridLinesVisible(false);
        return new Scene(grid, 300, 275);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
