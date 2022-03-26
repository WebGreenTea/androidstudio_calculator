//6206021610072 สหวุฒิ บุญยืน
package com.example.calculator_6206021610072;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bdivide,bmulti,bminus,bplus,bpoint,bc,bswitch,bcal;
    ImageButton bback;
    EditText inputarea;
    //TextView test;
    ArrayList<String> number = new ArrayList<String>();
    ArrayList<Character> Oper = new ArrayList<Character>();
    int operRange = 0;
    int numRange = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //test = (TextView) findViewById(R.id.test);
        inputarea = (EditText) findViewById(R.id.inputarea);
        inputarea.setText("0");
        number.add("0");
        b0 = (Button) findViewById(R.id.b0);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        bdivide = (Button) findViewById(R.id.bdivide);
        bmulti = (Button) findViewById(R.id.bmulti);
        bminus = (Button) findViewById(R.id.bminus);
        bplus = (Button) findViewById(R.id.bplus);
        bpoint = (Button) findViewById(R.id.bpoint);
        bc = (Button) findViewById(R.id.bc);
        bswitch = (Button) findViewById(R.id.bswitch);
        bcal = (Button) findViewById(R.id.bcal);
        bback = (ImageButton) findViewById(R.id.bback);
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        bdivide.setOnClickListener(this);
        bmulti.setOnClickListener(this);
        bminus.setOnClickListener(this);
        bplus.setOnClickListener(this);
        bpoint.setOnClickListener(this);
        bc.setOnClickListener(this);
        bswitch.setOnClickListener(this);
        bcal.setOnClickListener(this);
        bback.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.b0){
            setinput("0");
        }else if(id == R.id.b1){
            setinput("1");
        }else if(id == R.id.b2){
            setinput("2");
        }else if(id == R.id.b3){
            setinput("3");
        }else if(id == R.id.b4){
            setinput("4");
        }else if(id == R.id.b5){
            setinput("5");
        }else if(id == R.id.b6){
            setinput("6");
        }else if(id == R.id.b7){
            setinput("7");
        }else if(id == R.id.b8){
            setinput("8");
        }else if(id == R.id.b9){
            setinput("9");
        }else if(id == R.id.bpoint){
            setinputpoint();
        }else if(id == R.id.bc){
            inputarea.setText("0");
            Oper.clear();
            number.clear();
            operRange = 0;
            numRange = 0;
            number.add("0");
        }else if(id == R.id.bcal){
            Double re = getresult();
            Oper.clear();
            number.clear();
            operRange = 0;
            numRange = 0;
            number.add(Double.toString(re));
            inputarea.setText(Double.toString(re));

        }else if(id == R.id.bback){
            backspace();
        }else if(id == R.id.bplus){
            setoper('+');
        }else if(id == R.id.bminus){
            setoper('-');
        }else if(id == R.id.bdivide){
            setoper('/');
        }else if(id == R.id.bmulti){
            setoper('*');
        }else if(id == R.id.bswitch){
            String pre = inputarea.getText().toString();
            if(!is2oper(pre)){//ใส่ติดลบได้
                if(!Character.isDigit(pre.charAt(pre.length()-1))){ //ใส่ลบหลังเครื่องหมาย (+-)
                    inputarea.setText(pre+"-");
                    number.add("-");
                    numRange+=1;
                }else{//ใส่ลบแทรกหน้าเลข
                    String x = number.get(numRange);
                    double n = Double.parseDouble(x);
                    String toadd = pre.substring(pre.length()-x.length(),pre.length());
                    pre = pre.substring(0,pre.length()-x.length());
                    if(n < 0){
                        x = x.substring(1);
                        toadd = toadd.substring(1);
                    }else{
                        x = "-"+x;
                        toadd = "-"+toadd;
                    }
                    number.set(numRange,x);
                    inputarea.setText(pre+toadd);
                }
            }
        }
        //settest();
    }

    private boolean is2oper(String x){
        if(!Character.isDigit(x.charAt(x.length()-1)) && (!Character.isDigit(x.charAt(x.length()-2))) && (x.charAt(x.length()-2) != '.')){
            return true;
        }else{
            return false;
        }
    }
    private void setinputpoint(){
        String pre = inputarea.getText().toString();
        if(!havepoint()){
            if(!Character.isDigit(pre.charAt(pre.length()-1))){//ข้างหน้าเป็นเครื่อหมาย
                inputarea.setText(pre + '.');
                if(is2oper(pre)){
                    number.set(numRange,number.get(numRange)+'.');
                }else{
                    number.add(".");
                    numRange+=1;
                }

            }else{
                number.set(numRange,number.get(numRange)+".");
                inputarea.setText(pre + '.');
            }
        }

    }
    private boolean havepoint(){
        String lastN = number.get(numRange);
        for(int i =0;i<lastN.length();i++){
            if(lastN.charAt(i) == '.'){
                return true;
            }
        }
        return false;
    }
    private void setinput(String input){
        String pre = inputarea.getText().toString();

        if(!(pre.equals("0") && input.equals("0"))) {
            if( Character.isDigit(pre.charAt(pre.length()-1)) || pre.charAt(pre.length()-1) == '.' || is2oper(pre)){
                if(pre.equals("0")){
                    inputarea.setText(input);
                    number.set(0,input);
                }else{
                    inputarea.setText(pre + input);
                    number.set(numRange,number.get(numRange)+input);
                }
            }else{//ก่อนหน้าเป็นเครื่องหมาย
                inputarea.setText(pre + input);
                number.add(input);
                numRange+=1;
            }
        }

    }

    private void setoper(char oper){
        String pre = inputarea.getText().toString();
        if(Character.isDigit(pre.charAt(pre.length()-1)) || pre.charAt(pre.length()-1) == '.'){
            String toadd = pre;
            /*if(pre.charAt(pre.length()-1) == '.'){
                toadd = toadd.substring(0,toadd.length()-1);
                String n = number.get(numRange);
                number.set(numRange,n.substring(0,n.length()-1));
            }*/
            toadd =toadd+oper;
            inputarea.setText(toadd);
            Oper.add(oper);
            operRange = Oper.size()-1;

        }else{
            if(!is2oper(pre)) {
                String toadd = pre.substring(0,pre.length()-1)+oper;
                inputarea.setText(toadd);
                Oper.set(Oper.size()-1,oper);
            }
        }

    }
    private void backspace(){
        String pre = inputarea.getText().toString();
        if(pre.length()>0) {
            inputarea.setText(pre.substring(0, pre.length() - 1));
            if(inputarea.getText().toString().length()==0){
                inputarea.setText("0");
                number.clear();
                number.add("0");
            }else{
                if(!Character.isDigit(pre.charAt(pre.length()-1)) && !(pre.charAt(pre.length()-1) == '.') && !is2oper(pre)){//ถ้าตัวที่ลบเป็นเครื่องหมาย
                    Oper.remove(Oper.size()-1);
                    operRange-=1;
                    if(operRange == -1){
                        operRange = 0;
                    }
                }else{
                    pre = inputarea.getText().toString();
                    if(Character.isDigit(pre.charAt(pre.length()-1)) || pre.charAt(pre.length()-1) == '.' || is2oper(pre)){//ถ้าลบแล้วหลังสุดเป็นเลข

                        String num = number.get(numRange);
                        number.set(numRange,num.substring(0,num.length()-1));
                    }else{
                        number.remove(number.size()-1);
                        numRange-=1;
                    }
                }
            }

        }

    }
    public double getresult(){
        double result = 0;
        BigDecimal tem;
        //ถ้ามีเครื่องหมายติดมาตัวสุดท้าย จะตัดเครื่องหมายสุดท้ายออก
        while (Oper.size()>=number.size()){
            Oper.remove(Oper.size()-1);
        }
        for(int i =0;i<number.size();i++){
            try {
                double n = Double.parseDouble(number.get(i));
            }catch (Exception e){
                number.set(i,"0");
            }
        }

        MathContext mc = new MathContext(15, RoundingMode.HALF_UP) ;
        //คูณหารก่อน
        for(int i=0;i<Oper.size();i++){
            char Op = Oper.get(i);
            if(Op == '*' || Op == '/'){
                BigDecimal left = new BigDecimal(number.get(i));
                BigDecimal right = new BigDecimal(number.get(i+1));
                if(Op =='*'){
                    tem = left.multiply(right,mc);
                }else{
                    tem = left.divide(right,mc);
                }
                number.set(i,tem.toString());
                number.remove(i+1);
                Oper.remove(i);
                i=-1;
            }
        }
        //บวก ลบ
        for(int i =0;i<Oper.size();i++){
            char Op = Oper.get(i);
            BigDecimal left = new BigDecimal(number.get(i));
            BigDecimal right = new BigDecimal(number.get(i+1));
            if(Op == '+'){
                tem = left.add(right,mc);
            }else{
                tem = left.subtract(right,mc);
            }
            number.set(i,tem.toString());
            number.remove(i+1);
            Oper.remove(i);
            i=-1;
        }
        return Double.parseDouble(number.get(0));
    }
    /*
    public void settest(){
        String num = "";
        String o = "";
        for(int i = 0;i<number.size();i++){
            num+=number.get(i);
            num+="|";
        }
        for(int i = 0;i<Oper.size();i++){
            o+=Oper.get(i);
            o+="|";
        }
        test.setText(num+"\n"+o);
    }*/
}