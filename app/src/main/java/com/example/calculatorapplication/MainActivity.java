package com.example.calculatorapplication;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.calculatorapplication.lexeme.OperationType;
import com.example.calculatorapplication.utils.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    private Calculator calculator = new Calculator();
    private TextView textView;
    private View.OnClickListener onNumberClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int digit = ActivityUtils.getDigitByNumberId(v.getId());
            calculator.insertDigit(digit);
            textView.setText(calculator.getStringPresentation());
        }
    };
    private View.OnClickListener onOperationClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.minus:
                    calculator.insertOperation(OperationType.SUBTRACTION);
                    break;
                case R.id.plus:
                    calculator.insertOperation(OperationType.ADDITION);
                    break;
                case R.id.div:
                    calculator.insertOperation(OperationType.DIVISION);
                    break;
                case R.id.multiply:
                    calculator.insertOperation(OperationType.MULTIPLICATION);
                    break;
                case R.id.delSymb:
                    calculator.removeChar();
                    break;
                case R.id.ac:
                    calculator.removeAll();
                    break;
                case R.id.sign:
                    calculator.changeSign();
                    break;
                case R.id.equals:
                    try {
                        calculator.calculate();
                    } catch (ArithmeticException e) {
                        textView.setText("Exception!");
                        return;
                    }
                    break;
            }
            textView.setText(calculator.getStringPresentation());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ActivityUtils.getNumberIds().forEach(numberId -> {
                findViewById(numberId).setOnClickListener(onNumberClickListener);
            });
            ActivityUtils.getOperationsIds().forEach(operationId -> {
                findViewById(operationId).setOnClickListener(onOperationClickListener);
            });
        }

    }
}