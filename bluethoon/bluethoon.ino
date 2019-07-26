//inputs
int in= A0;

void setup() {
  Serial.begin(9600);
  pinMode (in,INPUT);
  
}

void loop() {
 //Lee el valor analogico
 float val= analogRead (in);
 // Divide los 205 para obtener un rango de 0 a 5v
 float val2 =val/205;

 //Use serial.print para enviar la informacion en formato de texto
 Serial.print(val2);
 delay (100);// pequeño retraso entre cada envio de datos

}
