package com.example.dictapp;

import com.sun.speech.freetts.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

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

    /**
     * pop up window add word.
     *
     * @author Taaaan
     */
    public void addWordScene(ActionEvent event) throws IOException {
        popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setTitle("Add a new word");
        FXMLLoader fxmlLoader = new FXMLLoader(ToolController.class.getResource("AddWord.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        popUp.setScene(scene);
        popUp.show();
    }

    /**
     * pop up window edit word.
     *
     * @author Taaaan
     */
    public void editWordScene(ActionEvent event) throws IOException {
        popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setTitle("Edit a word");
        FXMLLoader fxmlLoader = new FXMLLoader(ToolController.class.getResource("EditWord.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        popUp.setScene(scene);
        popUp.show();
    }

    /**
     * pop up window delete word.
     *
     * @author Taaaan
     */
    public void deleteWordScene(ActionEvent event) throws IOException {
        popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.setTitle("Delete a word");
        FXMLLoader fxmlLoader = new FXMLLoader(ToolController.class.getResource("DeleteWord.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        popUp.setScene(scene);
        popUp.show();
    }

    /**
     * close pop up window.
     *
     * @author Taaaan
     */
    public void closeStage() {
        popUp.close();
    }

    /**
     * add word when click button OK.
     *
     * @author Taaaan
     */
    public void addWord() {
        if (Management.explain.containsKey(englishWord.getText().toLowerCase())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Word already exist!");
            alert.showAndWait();
        } else {
            Management.addWord(englishWord.getText().toLowerCase(), (phonetic.getText() + "\n" + vietnameseWord.getText()).toLowerCase());
            popUp.close();
        }
    }

    /**
     * edit word when click button OK.
     *
     * @author Taaaan
     */
    public void editWord() {
        if (!Management.explain.containsKey(englishWord.getText().toLowerCase())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Word doesn't exist!");
            alert.showAndWait();
        } else {
            Management.editWord(englishWord.getText().toLowerCase(), vietnameseWord.getText().toLowerCase());
            popUp.close();
        }
    }

    /**
     * delete word when click button OK.
     *
     * @author Taaaan
     */
    public void deleteWord() {
        if (!Management.explain.containsKey(wordDelete.getText().toLowerCase())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Word doesn't exist!");
            alert.showAndWait();
        } else {
            Management.deleteWord(wordDelete.getText().toLowerCase());
            popUp.close();
        }
    }

    /**
     * Text To Speech.
     *
     * @author Taaaan
     */
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

    /**
     * use api to translate.
     *
     * @author Taaaan
     */
    public void apiButton() throws Exception {
        String temp = Translator.callUrlAndParseResult("en", "vi", searching_word.getText());
        Definition.setText(temp);
    }
}
