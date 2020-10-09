package com.example.calculatorapplication.lexeme;

public abstract class Lexeme {
    public boolean isInteger() {
        return this instanceof IntegerLexeme;
    }

//    public boolean isOperation() {
//        return this instanceof IntegerLexeme;
//    }
}
