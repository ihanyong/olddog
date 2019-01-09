package com.old.dog.javafx.test;/**
 * Created by hanyong on 2018/5/7.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.text.MessageFormat;
import java.util.Date;


/**
 * @author hanyong
 * @Date 2018/5/7
 */
public class HelloWorld extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        Button button = new Button();
        button.setText("Say 'hello world'");
        button.setOnAction(new EventHandler<ActionEvent>() {
            private int times = 1;
            @Override
            public void handle(ActionEvent event) {
//                Button button1= (Button) event.getSource();
//                button1.setText("hello");

                String msg = MessageFormat.format("Say 'hello world' {0, number, 000} times on {1,date,yyyy-MM-dd HH:mm:ss:SSS}", times++, new Date());
                primaryStage.setTitle(msg);

                System.out.println(msg);
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(button);

        Scene scene = new Scene(root, 300, 250);


        primaryStage.setTitle("hello world");
        primaryStage.setScene(scene);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }
}
