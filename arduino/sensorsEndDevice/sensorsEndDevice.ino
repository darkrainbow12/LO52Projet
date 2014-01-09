int pinA0 = A0;
int pinA1 = A1;
int pinA2 = A2;
int pinA3 = A3;
int pinA4 = A4;
int pinA5 = A5;

int arduinoId = 1;
// variables:
int sensorValue = 0;         // the sensor value
int sensorMin = 1023;        // minimum sensor value
int sensorMax = 0;           // maximum sensor value
int calibrate(int sensorValue);
void sendFrame(int arduinoId, int sensorPin, int sensorValue);

void setup(){
  Serial.begin(9600);
  delay(2000);
  pinMode(pinA0, INPUT);
  pinMode(pinA1, INPUT);
  pinMode(pinA2, INPUT);
  pinMode(pinA3, INPUT);
  pinMode(pinA4, INPUT);

}
void loop(){
  sensorValue = calibrate(analogRead(pinA0));
  sendFrame(arduinoId,pinA0,sensorValue);
  sensorValue = calibrate(analogRead(pinA1));
  sendFrame(arduinoId,pinA1,sensorValue);
  sensorValue = calibrate(analogRead(pinA2));
  sendFrame(arduinoId,pinA2,sensorValue);
  sensorValue = calibrate(analogRead(pinA3));
  sendFrame(arduinoId,pinA3,sensorValue);
  sensorValue = calibrate(analogRead(pinA4));
  sendFrame(arduinoId,pinA4,sensorValue);
  delay(3000);
}

int calibrate(int sensorValue){
  // record the maximum sensor value
  if (sensorValue > sensorMax) {
    sensorMax = sensorValue;
  }

  // record the minimum sensor value
  if (sensorValue < sensorMin) {
    sensorMin = sensorValue;
  }
  sensorValue = map(sensorValue, sensorMin, sensorMax, 0, 100);
  sensorValue = constrain(sensorValue, 0, 100);
  return sensorValue;
}

void sendFrame(int arduinoId, int sensorPin, int sensorValue){
  Serial.write((byte)0x7E);
  Serial.write((int)arduinoId);
  Serial.write((int)sensorPin);
  Serial.write((int)sensorValue);
}




