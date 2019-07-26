int adc_max=710;        //Reemplazar por valor adc_max entregado por el sketch: volt_ac_cal
int adc_min=330;        //Reemplazar por valor adc_min entregado por el sketch: volt_ac_cal
float volt_multi=212;   //Reemplazar por el "voltaje ac rms" entregado por un multimetro
float volt_multi_p;
float volt_multi_n;
float adc_sample;

void setup() {
  pinMode(2,OUTPUT);
  pinMode(4,OUTPUT);
  pinMode(6,OUTPUT);
  Serial.begin(115200);
  volt_multi_p = volt_multi * 1.4142;   //Voltaje pico= Voltaje RMS * 1.4142 (Corriente Monofasica)
  volt_multi_n = volt_multi_p * -1;
}


void loop() {
  
  float volt_rms=get_voltage(); //Voltage eficaz (V-RMS)
  if(volt_rms<25){      //si se pierde la conexion se vuelve cero
    volt_rms=0;
  }
  if(volt_rms>230){                         //identificador de falla
    digitalWrite(2,1); //led rojo
    digitalWrite(4,0);
    digitalWrite(6,0); 

    Serial.println("1");// EL VOLTAJE ES DE MENOR DE 230 BAJA DE ENERGIA
    Serial.println(volt_rms,2);
    Serial.println("001-M");
    delay(100);     
  }
  else if(volt_rms>=210 && volt_rms<=230){  //suministro correcto
    digitalWrite(2,0); 
    digitalWrite(4,1); //led verde
    digitalWrite(6,0);

    Serial.println("2"); // VOLTAJE CORRECT 53    
    Serial.println(volt_rms,2);
    Serial.println("001-M");
    delay(100);     
  }
  else if(volt_rms>0 && volt_rms<210){     //
    digitalWrite(2,0); 
    digitalWrite(4,0);
    digitalWrite(6,1); // led amarillo

    Serial.println("3");// SUBA de voltaje
    Serial.println(volt_rms,2);
    Serial.println("001-M");
    
    delay(100);     
  }

  else if(volt_rms==0){                    
    digitalWrite(2,1); // led rojo
    digitalWrite(4,0);
    digitalWrite(6,0); 

    Serial.println("4"); // SIN SUMINISTRO 
    Serial.println(volt_rms,2); 
    Serial.println("001-M");
    delay(100);     
  }
 
}

float get_voltage(void)
{
  
  float volt_inst=0;
  float Sumatoria=0;
  float volt;
  long tiempo_init=millis();
  int N=0;
  
  while( (millis() - tiempo_init) < 500)//Duración 0.5 segundos(Aprox. 30 ciclos de 60Hz)
  { 
    //adc_sample = analogRead(A0) - 510;////voltaje del sensor
    //volt_inst = map(adc_sample,-188,188,-310,310);
    
    adc_sample = analogRead(A0);    //voltaje del sensor
    volt_inst = map(adc_sample,adc_min,adc_max,volt_multi_n,volt_multi_p);
    Sumatoria = Sumatoria+sq(volt_inst);    //Sumatoria de Cuadrados
    N = N+1;
    delay(1);
  }
  
  //Serial.print("N: ");
  //Serial.println(N);
  
  volt=sqrt((Sumatoria)/N); //ecuación del RMS
  return(volt);
}
