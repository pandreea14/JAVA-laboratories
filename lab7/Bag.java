package org.example;

import java.util.Collections;
import java.util.Stack;

public class Bag {
    private Stack<Token> tokens = new Stack<>();

    public Bag(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    tokens.push(new Token(i, j));
                }
            }
        }
        Collections.shuffle(tokens); //randomly shuffling the tokens
    }

    public synchronized Token extractToken() {
        if (tokens.isEmpty()) {
            return null;
        }
        return tokens.pop();
    }
}
