package com.example.calculatorapplication;

import com.example.calculatorapplication.lexeme.IntegerLexeme;
import com.example.calculatorapplication.lexeme.Lexeme;
import com.example.calculatorapplication.lexeme.OperationLexeme;
import com.example.calculatorapplication.lexeme.OperationType;
import com.example.calculatorapplication.utils.MathUtils;

import java.util.Stack;

public class Calculator {
    private Stack<Lexeme> lexemes = new Stack<>();


    public Calculator() {
        lexemes.add(new IntegerLexeme(0));
    }


    public long calculate() throws ArithmeticException {
        // нормализуем выражение
        normalize();

        Stack<OperationType> ops = new Stack<>();
        Stack<Long> ints = new Stack<>();

        //добавляем число в стек
        long first_integer = ((IntegerLexeme) lexemes.get(0)).value;
        ints.add(first_integer);

        int pos = 1;

        while (pos < lexemes.size()) {
            OperationType op = ((OperationLexeme) lexemes.get(pos)).type;
            long curr = ((IntegerLexeme) lexemes.get(pos + 1)).value;

            // В первую очередь производим деление и умножение
            if (op == OperationType.MULTIPLICATION) {
                long pref = ints.pop();
                ints.add(pref * curr);
            } else if (op == OperationType.DIVISION) {
                // Проверярм деление на 0
                if (curr == 0) {
                    removeAll();
                    throw new ArithmeticException("division by zero");
                }

                long pref = ints.pop();
                ints.add(pref / curr);
            } else {
                ints.push(curr);
                ops.add(op);
            }
            pos += 2;
        }

        // Выполняем оставшиеся операции сложения/вычитания
        while (ops.size() != 0) {
            OperationType current_op = ops.pop();
            long second = ints.pop();
            long first = ints.pop();
            if (current_op == OperationType.ADDITION) {
                ints.add(first + second);
            }
            if (current_op == OperationType.SUBTRACTION) {
                ints.add(first - second);
            }
        }

        long result = ints.peek();
        lexemes.clear();
        lexemes.add(new IntegerLexeme(result));
        return result;
    }

    private void normalize() {
        if (!getLastLexeme().isInteger())
            lexemes.pop();
    }

    public void insertOperation(OperationType operation) {
        Lexeme last = getLastLexeme();
        if (last.isInteger()) {
            lexemes.add(new OperationLexeme(operation));
        } else {
            ((OperationLexeme) last).type = operation;
        }
    }

    public void insertDigit(int digit) {
        Lexeme last = getLastLexeme();
        if (last.isInteger()) {
            long curr_value = ((IntegerLexeme) last).value;
            ((IntegerLexeme) last).value = MathUtils.addDigitToLong(curr_value, digit);
        } else {
            lexemes.add(new IntegerLexeme(digit));
        }
    }

    public void changeSign() {
        Lexeme last = getLastLexeme();
        if (last.isInteger()) {
            ((IntegerLexeme) last).value *= -1;
        }
    }

    public void removeAll() {
        lexemes.clear();
        lexemes.add(new IntegerLexeme(0));
    }

    public void removeChar() {
        if (getLastLexeme().isInteger()) {
            IntegerLexeme lexeme = (IntegerLexeme) getLastLexeme();
            long value = lexeme.value;

            if (value >= 10) {
                lexeme.value /= 10; // убраем последнюю цифру
            } else {
                lexemes.pop();
            }
        } else {
            // getLastLexeme() = OperationLexeme
            lexemes.pop(); // удаляем операцию
        }

        if (lexemes.size() == 0)
            lexemes.add(new IntegerLexeme(0));
    }

    public String getStringPresentation() {
        StringBuilder sb = new StringBuilder();

        for (Lexeme l : lexemes) {
            if (l.isInteger()) {
                sb.append(((IntegerLexeme) l).value);
            } else {
                switch (((OperationLexeme) l).type) {
                    case ADDITION:
                        sb.append("+");
                        break;
                    case DIVISION:
                        sb.append("/");
                        break;
                    case SUBTRACTION:
                        sb.append("-");
                        break;
                    case MULTIPLICATION:
                        sb.append("*");
                        break;
                }
            }
        }
        return sb.toString();
    }

    private Lexeme getLastLexeme() {
        return lexemes.peek();
    }
}
