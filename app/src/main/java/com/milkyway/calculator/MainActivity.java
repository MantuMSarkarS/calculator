package com.milkyway.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.milkyway.calculator.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String str, str2,instanceValues;
    private boolean isTrue = true, addition, subtraction, square, pi, invert, sin, cos, tan, log,
            multiply, division, modulus, isDelete, sqrt;
    List<Double> randomNumbers = new ArrayList<Double>();
    private double temp = 0.0, temp1 = 1.0, multiDigitNo;
    private int intNumber = 0;
    private Vibrator vibrator;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        if(savedInstanceState != null)
            mBinding.instantPreview.setText(savedInstanceState.getString("instantPreview"));

        mBinding.setActivity(this);
        mBinding.values.setOnClickListener(view -> closeKeyboard(MainActivity.this));

    }


    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "1");
                vibrate();
                break;
            case R.id.two:
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "2");
                vibrate();
                break;
            case R.id.three:
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "3");
                vibrate();
                break;
            case R.id.four:
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "4");
                vibrate();
                break;
            case R.id.five:
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "5");
                vibrate();
                break;
            case R.id.six:
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "6");
                vibrate();
                break;
            case R.id.seven:
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "7");
                vibrate();
                break;
            case R.id.eight:
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "8");
                vibrate();
                break;
            case R.id.nine:
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "9");
                vibrate();
                break;
            case R.id.zero:
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "0");
                vibrate();
                break;
            case R.id.decimal:
                moveCursorEnd();
                if (mBinding.values.getText().toString().contains(".")) {

                } else {
                    mBinding.values.setText(mBinding.values.getText() + ".");
                }
                vibrate();
                break;
            case R.id.addition:
                moveCursorEnd();
                vibrate();
                if (endsWithOperator())
                    replace("+");
                else {
                    if (!mBinding.values.getText().toString().equals("")) {
                        append("+");
                        addition = true;
                    }
                }

                break;
            case R.id.subtraction:
                moveCursorEnd();
                vibrate();

              /*  if(mBinding.values.getText().toString().contains("")){
                    Log.d("TAG", "without no ");
                    append("-");
                }*/
                if (endsWithOperator()) {
                    Log.d("TAG", "after no ");
                    replace("-");
                } else {
                    if (!mBinding.values.getText().toString().equals("")) {
                        Log.d("TAG", "if not empty ");
                        append("-");
                        subtraction = true;
                    }
                }
                break;
            case R.id.divide:
                moveCursorEnd();
                vibrate();
                if (endsWithOperator())
                    replace("÷");
                else {
                    if (!mBinding.values.getText().toString().equals("")) {
                        append("÷");
                        division = true;
                    }
                }
                break;
            case R.id.multiply:
                moveCursorEnd();
                vibrate();
                if (endsWithOperator())
                    replace("×");
                else {
                    if (!mBinding.values.getText().toString().equals("")) {
                        append("×");
                        multiply = true;
                    }
                }
                break;
            case R.id.modulus:
                moveCursorEnd();
                vibrate();
                if (endsWithOperator())
                    replace("%");
                else {
                    if (!mBinding.values.getText().toString().equals("")) {
                        append("%");
                        modulus = true;
                    }
                }
                break;
            case R.id.delete:
                moveCursorEnd();
                vibrate();
                str = mBinding.values.getText().toString();
                if (isDelete) {
                    if (mBinding.instantPreview.getText().length() != 0) {
                        mBinding.instantPreview.setText(null);
                        mBinding.values.setText(null);
                    }
                }
                if (str.length() != 0) {
                    str = str.substring(0, str.length() - 1);
                }
                mBinding.values.setText(str);
                randomNumbers.clear();
                break;
            case R.id.allclear:
                vibrate();
                mBinding.values.setText(null);
                mBinding.instantPreview.setText(null);
                randomNumbers.clear();
                break;
            case R.id.plusOrMinus:
                vibrate();
                if (isTrue) {
                    moveCursorEnd();
                    if (endsWithOperator())
                        replace("+");
                    else {
                        if (!mBinding.values.getText().toString().equals("")) {
                            append("+");
                            isTrue = false;
                        }
                    }
                } else {
                    moveCursorEnd();
                    if (endsWithOperator())
                        replace("-");
                    else {
                        if (!mBinding.values.getText().toString().equals("")) {
                            append("-");
                            isTrue = true;
                        }
                    }
                }
            case R.id.equal:
                vibrate();
                moveCursorEnd();
                str2 = mBinding.values.getText().toString();
                /*if(){

                }*/
                if (addition) {
                    Matcher m = Pattern.compile("-?\\d*\\.?\\d+").matcher(str2);
                    while (m.find()) {
                        multiDigitNo = Double.parseDouble(m.group());
                        randomNumbers.add(multiDigitNo);
                    }
                    for (int i = 0; i <= randomNumbers.size() - 1; i++) {
                        try {
                            temp += randomNumbers.get(i);
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                    }
                    if (temp % 1 == 0) {
                        intNumber = (int) temp;
                        instanceValues=str2 + "=" + intNumber;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(intNumber));
                    } else {
                        instanceValues=str2 + "=" + temp;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(temp));
                    }
                    addition = false;
                    randomNumbers.clear();
                    temp = 0;
                    isDelete = true;
                }
                if (subtraction) {
                    Matcher matcher = Pattern.compile("-?\\d*\\.?\\d+").matcher(str2);
                    while (matcher.find()) {
                        randomNumbers.add(Double.parseDouble(matcher.group()));
                    }
                    for (int i = 0; i <= randomNumbers.size() - 1; i++) {
                        try {
                            temp += randomNumbers.get(i);
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                    }
                    if (temp % 1 == 0) {
                        intNumber = (int) temp;
                        instanceValues=str2 + "=" + intNumber;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(intNumber));
                    } else {
                        instanceValues=str2 + "=" + temp;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(temp));
                    }
                    subtraction = false;
                    randomNumbers.clear();
                    temp = 0;
                    isDelete = true;
                }
                if (multiply) {
                    Matcher matcher = Pattern.compile("-?\\d*\\.?\\d+").matcher(str2);
                    while (matcher.find()) {
                        randomNumbers.add(Double.parseDouble(matcher.group()));
                    }
                    Log.d("TAG", "multiply: ");
                    for (int i = 0; i <= randomNumbers.size() - 1; i++) {
                        try {
                            temp1 = temp1 * randomNumbers.get(i);
                            Log.d("TAG", "temp1: " + temp1);
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                    }
                    if (temp % 1 == 0) {
                        intNumber = (int) temp;
                        instanceValues=str2 + "=" + intNumber;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(intNumber));
                    } else {
                        instanceValues=str2 + "=" + temp;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(temp));
                    }
                    temp1 = 1.0;
                    multiply = false;
                    randomNumbers.clear();
                    isDelete = true;
                }
                if (division) {
                    Matcher matcher = Pattern.compile("-?\\d*\\.?\\d+").matcher(str2);
                    while (matcher.find()) {
                        randomNumbers.add(Double.parseDouble(matcher.group()));
                    }
                    for (int i = 0; i <= randomNumbers.size() - 1; i++) {
                        try {
                            temp1 = randomNumbers.get(i) / randomNumbers.get(i + 1);
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                    }
                    if (temp % 1 == 0) {
                        intNumber = (int) temp;
                        instanceValues=str2 + "=" + intNumber;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(intNumber));
                    } else {
                        instanceValues=str2 + "=" + temp;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(temp));
                    }
                    division = false;
                    randomNumbers.clear();
                    temp1 = 1.0;
                    isDelete = true;
                }
                if (modulus) {
                    Matcher matcher = Pattern.compile("-?\\d*\\.?\\d+").matcher(str2);
                    while (matcher.find()) {
                        randomNumbers.add(Double.parseDouble(matcher.group()));
                    }
                    for (int i = 0; i <= randomNumbers.size() - 1; i++) {
                        try {
                            temp1 = randomNumbers.get(i) / 100;
                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                    }
                    if (temp % 1 == 0) {
                        intNumber = (int) temp;
                        instanceValues=str2 + "=" + intNumber;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(intNumber));
                    } else {
                        instanceValues=str2 + "=" + temp;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(temp));
                    }
                    modulus = false;
                    randomNumbers.clear();
                    temp1 = 1.0;
                    isDelete = true;
                }
                if (sqrt) {
                    str2 = mBinding.values.getText().toString();
                    Matcher m = Pattern.compile("-?\\d*\\.?\\d+").matcher(str2);
                    while (m.find()) {
                        multiDigitNo = Double.parseDouble(m.group());
                        temp = Math.sqrt(multiDigitNo);
                    }
                    if (temp % 1 == 0) {
                        intNumber = (int) temp;
                        instanceValues=str2 + "=" + intNumber;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(intNumber));
                    } else {
                        instanceValues=str2 + "=" + temp;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(temp));
                    }
                    sqrt = false;
                    temp = 0.0;
                }
                if (square) {
                    str2 = mBinding.values.getText().toString();
                    Matcher m = Pattern.compile("-?\\d*\\.?\\d+").matcher(str2);
                    while (m.find()) {
                        multiDigitNo = Double.parseDouble(m.group());
                        randomNumbers.add(multiDigitNo);
                    }
                    for (int i = 0; i <= randomNumbers.size() - 1; i++) {
                        try {
                            // temp1=Math.pow(randomNumbers.get(i), randomNumbers.get(i+1));
                            double number = randomNumbers.get(i);
                            double count = randomNumbers.get(i + 1);

                            for (int j = 0; j < count; j++) {
                                while (count != 0) {
                                    temp1 *= number;
                                    --count;
                                }
                            }
                            if (temp % 1 == 0) {
                                intNumber = (int) temp;
                                instanceValues=str2 + "=" + intNumber;
                                mBinding.instantPreview.setText(instanceValues);
                                mBinding.values.setText(String.valueOf(intNumber));
                            } else {
                                instanceValues=str2 + "=" + temp;
                                mBinding.instantPreview.setText(instanceValues);
                                mBinding.values.setText(String.valueOf(temp));
                            }

                        } catch (IndexOutOfBoundsException e) {
                            e.printStackTrace();
                        }
                    }
                   /* if (temp1 % 1 == 0) {
                        intNumber = (int) temp1;
                        if(intNumber < 2){

                        }
                        mBinding.instantPreview.setText(mBinding.values.getText().toString() + "=" + intNumber);
                        mBinding.values.setText(String.valueOf(intNumber));
                    } else {
                        mBinding.instantPreview.setText(mBinding.values.getText().toString() + "=" + temp1);
                        mBinding.values.setText(String.valueOf(temp1));
                    }*/
                    square = false;
                    temp1 = 1.0;
                }
                if (invert) {
                    str2 = mBinding.values.getText().toString();
                    Matcher m = Pattern.compile("-?\\d*\\.?\\d+").matcher(str2);
                    while (m.find()) {
                        multiDigitNo = Double.parseDouble(m.group());
                        temp = 1 / multiDigitNo;
                    }
                    if (temp % 1 == 0) {
                        intNumber = (int) temp;
                        instanceValues=str2 + "=" + intNumber;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(intNumber));
                    } else {
                        instanceValues=str2 + "=" + temp;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(temp));
                    }
                    invert = false;
                    temp = 0.0;
                }
                if (sin) {
                    str2 = mBinding.values.getText().toString();
                    Matcher m = Pattern.compile("-?\\d*\\.?\\d+").matcher(str2);
                    while (m.find()) {
                        multiDigitNo = Double.parseDouble(m.group());
                        temp = Math.sin(30);
                        Log.d("TAG", "onClick: " + temp);
                    }
                    if (temp % 1 == 0) {
                        intNumber = (int) temp;
                        instanceValues=str2 + "=" + intNumber;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(intNumber));
                    } else {
                        instanceValues=str2 + "=" + temp;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(temp));
                    }
                    sin = false;
                    temp = 0.0;
                }
                if (cos) {
                    str2 = mBinding.values.getText().toString();
                    Matcher m = Pattern.compile("-?\\d*\\.?\\d+").matcher(str2);
                    while (m.find()) {
                        multiDigitNo = Double.parseDouble(m.group());
                        temp = Math.cos(multiDigitNo);
                    }
                    if (temp % 1 == 0) {
                        intNumber = (int) temp;
                        instanceValues=str2 + "=" + intNumber;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(intNumber));
                    } else {
                        instanceValues=str2 + "=" + temp;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(temp));
                    }
                    cos = false;
                    temp = 0.0;
                }
                if (tan) {
                    str2 = mBinding.values.getText().toString();
                    Matcher m = Pattern.compile("-?\\d*\\.?\\d+").matcher(str2);
                    while (m.find()) {
                        multiDigitNo = Double.parseDouble(m.group());
                        temp = Math.tan(Math.toRadians(multiDigitNo));
                    }
                    if (temp % 1 == 0) {
                        intNumber = (int) temp;
                        instanceValues=str2 + "=" + intNumber;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(intNumber));
                    } else {
                        instanceValues=str2 + "=" + temp;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(temp));
                    }
                    tan = false;
                    temp = 0.0;
                }
                if (log) {
                    str2 = mBinding.values.getText().toString();
                    Matcher m = Pattern.compile("-?\\d*\\.?\\d+").matcher(str2);
                    while (m.find()) {
                        multiDigitNo = Double.parseDouble(m.group());
                        temp = Math.log(multiDigitNo);
                    }
                    if (temp % 1 == 0) {
                        intNumber = (int) temp;
                        instanceValues=str2 + "=" + intNumber;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(intNumber));
                    } else {
                        instanceValues=str2 + "=" + temp;
                        mBinding.instantPreview.setText(instanceValues);
                        mBinding.values.setText(String.valueOf(temp));
                    }
                    log = false;
                    temp = 0.0;
                }
            case R.id.values:
                mBinding.values.setOnClickListener(view -> closeKeyboard(MainActivity.this));
                break;
            case R.id.sin:
                vibrate();
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "sin(");
                sin = true;
                break;
            case R.id.cos:
                vibrate();
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "cos(");
                cos = true;
                break;
            case R.id.tan:
                vibrate();
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "tan(");
                tan = true;
                break;
            case R.id.log:
                vibrate();
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "log(");
                log = true;
                break;
            case R.id.mr:
            case R.id.mc:
            case R.id.pi:
                vibrate();
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "π");
                pi = true;
                break;
            case R.id.in:
                vibrate();
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "ln(");
                invert = true;
                break;
            case R.id.e:
            case R.id.m_minus:
            case R.id.m_plus:
            case R.id.factorial:
                vibrate();
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "!");
                break;

            case R.id.first_paran:
                vibrate();
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "(");
                break;
            case R.id.second_paran:
                vibrate();
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + ")");
                break;
            case R.id.square:
                vibrate();
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "^");
                square = true;
                break;
            case R.id.squareroot:
                vibrate();
                moveCursorEnd();
                mBinding.values.setText(mBinding.values.getText() + "√");
                sqrt = true;
                break;
            case R.id.grade:
            case R.id.grade1:
        }
    }

    public void vibrate() {
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(60);
    }

    public boolean endsWithOperator() {
        return mBinding.values.getText().toString().endsWith("+")
                || mBinding.values.getText().toString().endsWith("-")
                || mBinding.values.getText().toString().endsWith("×")
                || mBinding.values.getText().toString().endsWith("÷")
                || mBinding.values.getText().toString().endsWith("%");
    }

    public void replace(String str) {
        mBinding.values.getText().replace(mBinding.values.getText().length() - 1, mBinding.values.getText().length(), str);
    }

    public void append(String str) {
        mBinding.values.getText().append(str);
    }

    public void moveCursorEnd() {
        if (mBinding.values.getText().length() >= 0) {
            mBinding.values.post(() -> mBinding.values.setSelection(mBinding.values.getText().length()));
        }
    }

    public void closeKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();
        if (focusedView != null) {
            assert inputManager != null;
            inputManager.hideSoftInputFromWindow(focusedView.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("instantPreview",instanceValues);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        if(savedInstanceState != null)
        mBinding.instantPreview.setText(savedInstanceState.getString("instantPreview"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBinding.values.setOnClickListener(view -> closeKeyboard(MainActivity.this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding.values.setOnClickListener(view -> closeKeyboard(MainActivity.this));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mBinding.values.setOnClickListener(view -> closeKeyboard(MainActivity.this));
    }

}
