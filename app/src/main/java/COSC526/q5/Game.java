package COSC526.q5;

import java.util.Random;

class Game {
    private int number;
    private int chances;

    public Game() {
        //game constructor creates a instance of the random class
        Random random = new Random();
        //assign number to a random number from 1 - 100
        this.number = random.nextInt(1, 100);
        //set chances
        this.chances = 8;
    }

    public int getNumber() {
        return number;
    }

    public int getChances() {
        return chances;
    }

    public void decrementChances() {
        chances--;
    }
}
