package com.example.exam06_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView TV_result, TV_record;
    ImageButton Btn_number[];
    ImageButton btn_add, btn_sub, btn_mul, btn_div, btn_op, btn_claer;

    int btn_id[] = {
            R.id.imgbtn_zero, R.id.imgbtn_one, R.id.imgbtn_two, R.id.imgbtn_three,
            R.id.imgbtn_four, R.id.imgbtn_five, R.id.imgbtn_six,
            R.id.imgbtn_seven, R.id.imgbtn_eight, R.id.imgbtn_nine
    };

    Integer number = 0, result = 0;
    int opcode = 0;
    String str = "";

    final int ADD = 1, SUB = 2, MUL = 3, DIV = 4; //자바는 전처리 지시어를 못써서 #define 못쓴다.
                                                    //final : 상수 선언 (c언어의 const)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TV_result = (TextView)findViewById(R.id.tv_result);
        TV_record = (TextView)findViewById(R.id.tv_record);

        Btn_number = new ImageButton[10];
        for(int i = 0; i < 10; i++)
        {
            Btn_number[i] = (ImageButton)findViewById(btn_id[i]);
            Btn_number[i].setOnClickListener(mClickListener);
        }

        btn_add = (ImageButton)findViewById(R.id.imgbtn_plus);
        btn_sub = (ImageButton)findViewById(R.id.imgbtn_ninus);
        btn_mul = (ImageButton)findViewById(R.id.imgbtn_multi);
        btn_div = (ImageButton)findViewById(R.id.imgbtn_division);
        btn_op = (ImageButton)findViewById(R.id.imgbtn_equal);
        btn_claer = (ImageButton)findViewById(R.id.imgbtn_clear);

        btn_add.setOnClickListener(mClickListener_op);
        btn_sub.setOnClickListener(mClickListener_op);
        btn_mul.setOnClickListener(mClickListener_op);
        btn_div.setOnClickListener(mClickListener_op);
        btn_op.setOnClickListener(mClickListener_op);
        btn_claer.setOnClickListener(mClickListener_op);
    }


    ImageButton.OnClickListener mClickListener = new ImageButton.OnClickListener(){

        @Override
        public void onClick(View v) {

           switch (v.getId()){
               case R.id.imgbtn_zero :
                   if(number / 10 != 0 || number != 0)
                       str += "0";
                   number = (number * 10) + 0;
                   break;
               case R.id.imgbtn_one :
                   str += "1";
                   number = number * 10 + 1;
                   break;
               case R.id.imgbtn_two :
                   str += "2";
                   number = number * 10 + 2;
                   break;
               case R.id.imgbtn_three :
                   str += "3";
                   number = number * 10 + 3;
                   break;
               case R.id.imgbtn_four :
                   str += "4";
                   number = number * 10 + 4;
                   break;
               case R.id.imgbtn_five :
                   str += "5";
                   number = number * 10 + 5;
                   break;
               case R.id.imgbtn_six :
                   str += "6";
                   number = number * 10 + 6;
                   break;
               case R.id.imgbtn_seven :
                   str += "7";
                   number = number * 10 + 7;
                   break;
               case R.id.imgbtn_eight :
                   str += "8";
                   number = number * 10 + 8;
                   break;
               case R.id.imgbtn_nine :
                   str += "9";
                   number = number * 10 + 9;
                   break;
           } //switch
           TV_record.setText(str);
        }
    };

    ImageButton.OnClickListener mClickListener_op = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(opcode != 0) { //연산한 적이 있으면 number 변수만 초기화
                switch (v.getId()) {
                    case R.id.imgbtn_plus:
                        opcode = ADD; number = 0;
                        str += " + "; TV_record.setText(str);
                        break;
                    case R.id.imgbtn_ninus:
                        opcode = SUB; number = 0;
                        str += " - "; TV_record.setText(str);
                        break;
                    case R.id.imgbtn_multi:
                        opcode = MUL; number = 0;
                        str += " * "; TV_record.setText(str);
                        break;
                    case R.id.imgbtn_division:
                        opcode = DIV; number = 0;
                        str += " / "; TV_record.setText(str);
                        break;
                }
            }
            else { //연산이 처음이면(opcode에 값이 없으면) result에 number 저장해주고 number 초기화
                switch (v.getId()){
                    case R.id.imgbtn_plus :
                        opcode = ADD; result = number; number = 0;
                        str += " + "; TV_record.setText(str);
                        break;
                    case R.id.imgbtn_ninus :
                        opcode = SUB; result = number; number = 0;
                        str += " - "; TV_record.setText(str);
                        break;
                    case R.id.imgbtn_multi :
                        opcode = MUL; result = number; number = 0;
                        str += " * "; TV_record.setText(str);
                        break;
                    case R.id.imgbtn_division :
                        opcode = DIV; result = number; number = 0;
                        str += " / "; TV_record.setText(str);
                        break;

                }
            }
            switch (v.getId())
            {
                case R.id.imgbtn_equal :

                    switch (opcode){
                        case 1 :
                            result = result + number;
                            break;
                        case 2 :
                            result = result - number;
                            break;
                        case 3 :
                            result = result * number;
                            break;
                        case 4 :
                            result = result / number;
                            break;
                    }
                   // TV_record.setText(result.toString());
                    TV_result.setText(" = " + result);
                    //str = result.toString();
                    number = 0;
                    break;
                case R.id.imgbtn_clear :
                    result = 0; number = 0; opcode = 0; str = "";
                    TV_result.setText(""); TV_record.setText("");
                    break;
            }
        }
    };
}
