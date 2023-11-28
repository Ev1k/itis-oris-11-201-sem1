package com.example.chatbot;

import com.example.chatbot.api.Currency;
import com.example.chatbot.api.Weather;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    private TextArea chatArea;
    private TextField inputField;
    @Override
    public void start(Stage stage) throws IOException {

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Form");
        GridPane pane = new GridPane();
        ButtonType start = new ButtonType("Start", ButtonBar.ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().addAll(start);
        dialog.getDialogPane().setContent(pane);

        dialog.setResultConverter(button -> {
                    if (button.equals(start)) {
                        return "hello";
                    }
                    return null;
                }
        );

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(System.out::println);

        chatArea = new TextArea();
        inputField = new TextField();

        Button sendButton = new Button("Send");
        sendButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String message = inputField.getText();
                String command = inputField.getText().split(" ")[0];
                String city = message.split(" ")[1];
                if (message.equalsIgnoreCase("команда")) {
                    chatArea.appendText("Список команд:\n1. команда - показать список команд\n2. погода [город]- показать текущую погоду\n3. валюта - показать курс валют\n");
                } else if (message.equalsIgnoreCase("погода")) {
                    try {
                        chatArea.appendText("Погода сегодня:"+ Weather.getWeather(city) +" ...\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else if (message.equalsIgnoreCase("валюта")) {
                    chatArea.appendText("Курс валют на сегодня: "+ " eur: " + Currency.getEur() + " us: "+ Currency.getUs() + "...\n");
                } else {
                    chatArea.appendText("Вы: " + message + "\n");
                }
                inputField.clear();
            }
        });

        VBox root = new VBox(chatArea, inputField, sendButton);
        Scene scene = new Scene(root, 400, 300);

        stage.setTitle("Chat Window");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}