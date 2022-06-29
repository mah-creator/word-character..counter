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

        /**
         * this method is called whenever any change occures to inputted text in the text area.
         * Based on that the updateCountBasedOn(textArea's text) will be invoked after changes take place
         * so a real-time counting is guaranteed 
         * */  
        @Override
        public void replaceText(int start, int end, String text) {
            // changes to text are take place
            super.replaceText(start, end, text);

            // updatin count
            updateCountBasedOn(textArea.getText());
        }
    }


    
}