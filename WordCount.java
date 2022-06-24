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

    private Text count = new Text("0 words || 0 characters");
    private WordCount.MyTextArea textArea = this.new MyTextArea();
    private StringBuffer textBuffer = new StringBuffer();

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        textArea.wrapTextProperty().set(true);
        textArea.setFont(new Font(15));

        count.setFont(Font.font(Font.getFamilies().get(107), FontWeight.EXTRA_BOLD, 20));

        BorderPane pane = new BorderPane(textArea);
        pane.setBottom(count);
        pane.setPadding(new Insets(5));

        primaryStage.setScene(new Scene(pane));
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300);

        primaryStage.show();
    }
    
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