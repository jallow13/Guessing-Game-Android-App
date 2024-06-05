package com.example.guessinggame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText guessEditText;
    private Button guessButton;
    private TextView feedbackTextView;
    private TextView attemptsTextView;

    private int randomNumber;
    private int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guessEditText = findViewById(R.id.guessEditText);
        guessButton = findViewById(R.id.guessButton);
        feedbackTextView = findViewById(R.id.feedbackTextView);
        attemptsTextView = findViewById(R.id.attemptsTextView);

        resetGame();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guessString = guessEditText.getText().toString();
                if (guessString.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a number", Toast.LENGTH_SHORT).show();
                } else {
                    int guess = Integer.parseInt(guessString);
                    checkGuess(guess);
                }
            }
        });
    }

    private void resetGame() {
        Random random = new Random();
        randomNumber = random.nextInt(10) + 1;
        attempts = 0;
        attemptsTextView.setText("Attempts: " + attempts);
        feedbackTextView.setText("Enter a number between 1 and 10");
        guessEditText.setText("");
    }

    private void checkGuess(int guess) {
        attempts++;
        attemptsTextView.setText("Attempts: " + attempts);

        if (guess < randomNumber) {
            feedbackTextView.setText("Too low! Try again.");
        } else if (guess > randomNumber) {
            feedbackTextView.setText("Too high! Try again.");
        } else {
            feedbackTextView.setText("Correct! You guessed the number in " + attempts + " attempts.");
            Toast.makeText(MainActivity.this, "Congratulations! You guessed the number!", Toast.LENGTH_LONG).show();
            resetGame();
        }
    }
}
