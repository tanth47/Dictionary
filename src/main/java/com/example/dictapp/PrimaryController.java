package com.example.dictapp;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.sun.speech.freetts.*;
import java.util.LinkedList;
import java.util.Locale;

public class PrimaryController {
    @FXML
    TextField searching_word = new TextField("");

    @FXML
    ListView<String> listView;

    @FXML
    Label Definition = new Label("");

    @FXML
    Button add;

    DictionaryManagement Management = new DictionaryManagement();

    public void SuggestWord() {
       // Definition.setText("");
        listView.getItems().clear();
        LinkedList<String> L = Management.dictionarySearcher(searching_word.getText().toLowerCase());
        for (String a : L) {
            listView.getItems().add(a);
        }
    }

    @FXML
    public void keyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
            Definition.setText(Management.dictionaryLookup(searching_word.getText()));
        }
    }


    public void PrintDefinition() {
        Definition.setText(Management.dictionaryLookup(searching_word.getText()));
    }

    public void getSelectedWordInSuggestedList() {
        ObservableList<String> selectedIndices = listView.getSelectionModel().getSelectedItems();
        if (selectedIndices.size() == 0) {
            return;
        }
        searching_word.setText(selectedIndices.get(0));
        PrintDefinition();
    }


}