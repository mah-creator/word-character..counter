import java.util.Scanner;

import javafx.application.Application;

import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import javafx.stage.Stage;

public class WordCount extends Application {

    // creat a text area of a special type MyTextArea
    private WordCount.MyTextArea textArea = this.new MyTextArea();

    // a text representing the number of words and characters
    private Text count = new Text("0 words || 0 characters");

    // a copy of text inputted from the user in the text area
    private StringBuffer textBuffer = new StringBuffer();

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        // set some properties
        textArea.wrapTextProperty().set(true);
        textArea.setFont(new Font(15));

        count.setFont(Font.font(Font.getFamilies().get(107), FontWeight.EXTRA_BOLD, 20));

        // a pane containing all nodes
        BorderPane pane = new BorderPane(textArea);
        pane.setBottom(count);
        pane.setPadding(new Insets(5));

        primaryStage.setScene(new Scene(pane));
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300);

        primaryStage.show();
    }
    
    /**
     * updates the content of countLable(number of words and characters) based on a givin string
     */
    private void updateCountBasedOn(String s) {
        Scanner input = new Scanner(s);

        int wordsCount = 0;
        int charsCount = 0;

        while(input.hasNext()){
            wordsCount ++;
            charsCount += input.next().length();
        }

        count.setText(String.format("%d words || %d characters", wordsCount, charsCount));
    }

    /**
     * Inner Class
     * a slightly modified virsion of the TextArea class
     */

    class MyTextArea extends TextArea{

        @Override
        public void replaceText(int start, int end, String text) {
            super.replaceText(start, end, text);

            textBuffer.replace(start, end, text);
            updateCountBasedOn(textBuffer.toString());
        }
    }


    
}