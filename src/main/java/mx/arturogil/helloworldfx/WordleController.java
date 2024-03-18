package mx.arturogil.helloworldfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.HashSet;
import java.util.Set;

public class WordleController {

    @FXML
    private HBox letterBox;

    @FXML
    private TextField wordInputField;

    @FXML
    private VBox cardContainer;

    private String wordToGuess;
    private Set<Character> correctLetters;
    private Set<String> wordsEntered;

    @FXML
    public void initialize() {

    }

    public void initializeGame(String word) {
        wordToGuess = word.toUpperCase();
        correctLetters = new HashSet<>();
        wordsEntered = new HashSet<>();
        displayWordToGuess();
    }

    @FXML
    void handleWordInput(ActionEvent event) {
        String input = wordInputField.getText().toUpperCase();
        if (input.length() != wordToGuess.length()) {
            showAlert("La palabra ingresada tiene una longitud incorrecta.");
            return;
        }
        addWordToEnteredList(input);
        addWordCard(input);
        updateCorrectLetters(input);
        wordInputField.clear();
    }

    private void displayWordToGuess() {
        letterBox.getChildren().clear();
        for (char c : wordToGuess.toCharArray()) {
            Text letterText = new Text();
            letterText.getStyleClass().add("letter-label");
            letterText.setFill(Color.WHITE);
            if (correctLetters.contains(c)) {
                letterText.setText(Character.toString(c));
            } else {
                letterText.setText("_ ");
            }
            letterBox.getChildren().add(letterText);
        }
    }

    private void addWordCard(String word) {
        Text wordText = new Text(word);
        wordText.getStyleClass().add("card");
        wordText.setFill(Color.WHITE);
        cardContainer.getChildren().add(wordText);
    }

    private void addWordToEnteredList(String word) {
        wordsEntered.add(word);
    }

    private void updateCorrectLetters(String input) {
        for (int i = 0; i < input.length(); i++) {
            char userInputChar = input.charAt(i);
            char wordToGuessChar = wordToGuess.charAt(i);
            if (userInputChar == wordToGuessChar) {
                correctLetters.add(userInputChar);
            }
        }
        displayWordToGuess();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wordle");
        alert.setHeaderText("Juegos Oscar");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
