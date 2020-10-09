package com.example.calculatorapplication.lexeme;

public class OperationLexeme extends Lexeme {
    public OperationType type;

    public OperationLexeme(OperationType type) {
        this.type = type;
    }
}
