package com.example.practicazamora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    EditText txtNumeros;
    ProgressBar prgsNumero;
    TextView lblResult;


    TimePicker dpTime;
    DatePicker dpDate;
    boolean isRun = false;
    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializerControllers();
    }
    private void InitializerControllers(){
        dpTime = (TimePicker)findViewById(R.id.dpTime);
        dpDate = (DatePicker)findViewById(R.id.dpDate);
        txtNumeros = (EditText)findViewById(R.id.txtNumeros);
        prgsNumero = (ProgressBar)findViewById(R.id.prgsNumero);
        lblResult = (TextView)findViewById(R.id.lblResult);
    }
    public void SetearNumero(View v){

        int hora=0;
        int minuto=0;

        if(Build.VERSION.SDK_INT < 23){
            hora = dpTime.getCurrentHour();;
            minuto = dpTime.getCurrentMinute();

        } else{
            hora = dpTime.getHour();
            minuto = dpTime.getMinute();
        }


        int dia = dpDate.getDayOfMonth();
        int mes = dpDate.getMonth()+1;
        int year = dpDate.getYear();
        String Nmes;

        switch (mes){
            case 1:
                Nmes = "enero";
                break;
            case 2:
                Nmes = "febrero";
                break;
            case 3:
                Nmes = "marzo";
                break;
            case 4:
                Nmes = "abril";
                break;
            case 5:
                Nmes = "mayo";
                break;
            case 6:
                Nmes = "junio";
                break;
            case 7:
                Nmes = "julio";
                break;
            case 8:
                Nmes = "agosto";
                break;
            case 9:
                Nmes = "septiembre";
                break;
            case 10:
                Nmes = "octubre";
                break;
            case 11:
                Nmes = "noviembre";
                break;
            case 12:
                Nmes = "diciembre";
                break;
            default: Nmes = "Enero";
                break;

        }
        String tiempo;
        if(hora==13){
            tiempo="1:"+minuto+"pm";
        }else if(hora==14){
            tiempo="2:"+minuto+"pm";
        }else if(hora==15){
            tiempo="3:"+minuto+"pm";
        }else if(hora==16){
            tiempo="4:"+minuto+"pm";
        }else if(hora==17){
            tiempo="5:"+minuto+"pm";
        }else if(hora==18){
            tiempo="6:"+minuto+"pm";
        }else if(hora==19){
            tiempo="7:"+minuto+"pm";
        }else if(hora==20){
            tiempo="8:"+minuto+"pm";
        }else if(hora==21){
            tiempo="9:"+minuto+"pm";
        }else if(hora==22){
            tiempo="10:"+minuto+"pm";
        }else if(hora==23){
            tiempo="11:"+minuto+"pm";
        }else if(hora==24){
            tiempo="12:"+minuto+"pm";
        }else{
            tiempo=hora+":"+minuto+"am";
        }

        try {

            int porcentaje = Integer.parseInt(txtNumeros.getText().toString());
            final Timer t = new Timer();

            if(porcentaje<=100) {
                if (!isRun) {
                    TimerTask tt = new TimerTask() {
                        public void run() {
                            counter++;
                            prgsNumero.setProgress(counter);
                            if (counter == porcentaje) {
                                t.cancel();
                            }

                        }
                    };
                    t.schedule(tt, 0, 100);
                }
            }else{
                Toast.makeText(this, "Solo puede ingresar números del 0 al 100 ",Toast.LENGTH_SHORT).show();
            }
            isRun=true;
            //Seteando hora y fecha con el mismo botón



            lblResult.setText("Es el día "+dia
                    +" de "+Nmes
                    +" del "+year
                    +"\n hora: "+tiempo);
        }catch(NumberFormatException e){
            Toast.makeText(this, "Solo puede ingresar números. ",Toast.LENGTH_SHORT).show();
        }
    }

    public void ResetearNumero(View v){
        prgsNumero.setProgress(0);
        lblResult.setText("");
        txtNumeros.setText("");
        isRun=false;
        counter=0;
    }

}