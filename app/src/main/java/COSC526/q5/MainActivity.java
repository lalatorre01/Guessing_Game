package COSC526.q5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Game game;
    private TextView chancesTextView;
    private TextView changingText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        game = new Game();
    }
    public void submit(View v){
        String correctGuessMessage = "Congrats! You guessed the right number";
        String chancesExceededMessage = "Game over! You've used up all your chances";
        String greaterThanMessage = "Number is greater than your guess";
        String lessThanMessage = "Number is less than your guess";
        //read input, button, and textviews
        EditText userGuess = findViewById(R.id.userInput);
        String userGuessString = userGuess.getText().toString();
        int userGuessInt = Integer.parseInt(userGuessString);
        chancesTextView = findViewById(R.id.chancesTextView);
        changingText = findViewById(R.id.changingText);
        button = findViewById(R.id.submitButton);

        //decrement chances
        game.decrementChances();
        //get chances
        int chances = game.getChances();
        //get number
        int number = game.getNumber();

        if(userGuessInt == number){
            //display chances
            chancesTextView.setText(String.format("You have %d Guesses", chances));
            //display correct guess message
            changingText.setText(correctGuessMessage);
            //disable input box, disable submit button
            userGuess.setEnabled(false);
            button.setEnabled(false);
            //show dialog box
            showDialogBox();

        }
        else if (chances == 0){
            //display chances
            chancesTextView.setText(String.format("You have %d Guesses", chances));
            //display chances exceeded message
            changingText.setText(chancesExceededMessage);
            //disable input box, disable submit button
            userGuess.setEnabled(false);
            button.setEnabled(false);
            //show dialog box
            showDialogBox();

        }
        else if (number > userGuessInt){
            //display chances
            chancesTextView.setText(String.format("You have %d Guesses", chances));
            //display number is greater than guess message
            changingText.setText(greaterThanMessage);

        }
        else{
            //display chances
            chancesTextView.setText(String.format("You have %d Guesses", chances));
            //display number is less than guess message
            changingText.setText(lessThanMessage);
        }


    }
    private void showDialogBox(){
        //create dialog box
        AlertDialog.Builder dialogBox = new AlertDialog.Builder(this);
        //set message on dialog box
        dialogBox.setMessage("Want to play again?");
        //create an event handler for dialog box
        DialogBoxListener temp = new DialogBoxListener();
        //add event handler to dialog box buttons
        dialogBox.setPositiveButton("Yes", temp);
        dialogBox.setNegativeButton("No", temp);
        dialogBox.setNeutralButton("Cancel", temp);
        //show dialog box
        dialogBox.show();
    }
    private class DialogBoxListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int id) {
            //dialog box commands
            if(id == -1){ //user decides to play another game
                //create new game instance
                game = new Game();
                //set content view
                setContentView(R.layout.activity_main);
            }
            else if(id == -2){
                //kills the app
                MainActivity.this.finish();
            }
            else; //does nothing

        }
    }


    }