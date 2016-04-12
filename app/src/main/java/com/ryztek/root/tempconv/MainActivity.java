package com.ryztek.root.tempconv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String strFrom="Celsius";
    private String strTo="Fahrenheit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public void onStart(){
        super.onStart();
        EditText et=(EditText)findViewById(R.id.txt_input); //refer tot he EditText, txt_input
        et.addTextChangedListener(new ChangeListener()); //register the view with the text change listener
        et.setHint("Enter celsius value");
    }

    private class ChangeListener implements TextWatcher {
        public void beforeTextChanged(CharSequence s, int start, int before, int count){
        }

        public void onTextChanged(CharSequence s, int start, int before, int count){
            convert(strFrom,strTo);  //do conversions
        }

        public void afterTextChanged(Editable ed){
        }
    }

    public void convert(String from, String to){
        EditText et=(EditText) findViewById(R.id.txt_input);
        TextView tv=(TextView) findViewById(R.id.txt_result);
        double cvalue=0.0;
        double fvalue=0.0;
        if(et.getText().toString().length()>0){
            if(from.equals("Celsius")) //convert from Celsius to Fahrenheit
            {
                cvalue=Double.parseDouble(et.getText().toString());
                fvalue=32+(cvalue*180.0/100.0);
                tv.setText(fvalue+to);
            }
            else{ //convert from Fahrenheit to Celsus
                fvalue=Double.parseDouble(et.getText().toString());
                cvalue=(fvalue-32)*100.0/180.0;
                tv.setText(cvalue+to);
            }
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        EditText et=(EditText)findViewById(R.id.txt_input);

        switch(view.getId()) {
            case R.id.ctof_radio:
                if (checked){ //the first RadioButton is selected
                    strFrom="Celsius";
                    strTo="Fahrenheit";
                    et.setHint("Enter celsius value");
                }
                break;
            case R.id.ftoc_radio:
                if (checked){ //the second RadioButton is selected
                    strFrom="Fahrenheit";
                    strTo="Celsius";
                    et.setHint("Enter fahrenheit value");
                }
                break;
        }
    }
}
