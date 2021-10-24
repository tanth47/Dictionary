package com.example.dictapp;

import com.sun.speech.freetts.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

public class ToolController extends PrimaryController {
    @FXML
    static Stage popUp;

    @FXML
    TextField englishWord = new TextField();

    @FXML
    TextField vietnameseWord = new TextField();

    @FXML
    TextField phonetic = new TextField();

    @FXML
    TextField wordDelete = new TextField();

    @FXML
    Button OK = new Button();

    @FXML
    Button Cancle = new Button();

    public void addWordScene(ActionEvent event) throws IOException {
        popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setTitle("Add a new word");
        FXMLLoader fxmlLoader = new FXMLLoader(ToolController.class.getResource("AddWord.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        popUp.setScene(scene);
        popUp.show();
    }

    public void deleteWordScene(ActionEvent event) throws IOException {
        popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setTitle("Delete a word");
        FXMLLoader fxmlLoader = new FXMLLoader(ToolController.class.getResource("DeleteWord.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        popUp.setScene(scene);
        popUp.show();
    }

    public void closeStage() {
        popUp.close();
    }

    public void addWord() {
        Management.addWord(englishWord.getText().toLowerCase(), vietnameseWord.getText().toLowerCase());
        popUp.close();
    }

    public void deleteWord() {
        Management.deleteWord(wordDelete.getText().toLowerCase());
        popUp.close();
    }

    public void TextToSpeech() {
        VoiceManager freeVM;
        Voice voice;
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();// Allocating Voice
            try {
                voice.speak(searching_word.getText());// Calling speak() method
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } else {
            throw new IllegalStateException("Cannot find voice: kevin16");
        }
    }

    public void apiButton() throws Exception {
        String temp = Translator.callUrlAndParseResult("en", "vi", searching_word.getText());

        Definition.setText(temp);
    }
}
