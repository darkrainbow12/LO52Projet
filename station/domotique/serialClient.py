# -*- coding: utf-8 -*-
import serial
from time import sleep
from collections import deque
import demjson
import numpy
import pickle
import copy




ser = serial.Serial('/dev/ttyS0', 9600);

sensors = {}
room = {}
timeStamp = 0

while True :

   if ord(ser.read()) == 0x7E and ser.inWaiting() >=3 :
      sensor = {'lastValues' : deque(), 'lastMinutesMean' : deque(), 'lastHoursMean' : deque()}
      arduino = ord(ser.read())
      pinNum = ord(ser.read())
      value= ord(ser.read())

      try :
	 sensors = room[arduino]
         sensor = sensors[pinNum]
         meanMinutesQueue = sensor['lastMinutesMean']
         meanHoursQueue = sensor['lastHoursMean']
         valueQueue=sensor['lastValues']     
         valueQueue.appendleft(value)
            
	 if len(sensor['lastValues'])>0 and len(sensor['lastValues'])<110:
            meanMinute=0
	    tempValues=copy.deepcopy(valueQueue)
	    for i in range (len(sensor['lastValues'])) :
               meanMinute+=tempValues.pop()
            meanMinute=meanMinute/(len(sensor['lastValues']))
	    if len(meanMinutesQueue)>0:
               meanMinutesQueue.popleft()
            meanMinutesQueue.appendleft(meanMinute)   
         if len(sensor['lastValues']) == 120 :
            meanMinute=0
            for i in range (59) :
               meanMinute+=valueQueue.pop()
            meanMinute=meanMinute/60
            valueQueue.appendleft(value)
            meanMinutesQueue.appendleft(meanMinute)           

         if len(meanMinutesQueue)>0 and len(meanMinutesQueue)<110:
            meanHour=0
	    meanMinutesQueueTemp = copy.deepcopy(meanMinutesQueue)
            for i in range (len(meanMinutesQueueTemp)) :
               meanHour+=meanMinutesQueueTemp.pop()
            meanHour=meanHour/(len(meanMinutesQueue)+1)
	    if len(meanHoursQueue)>0:
	       meanHoursQueue.popleft()
            meanHoursQueue.appendleft(meanHour)
         if len(meanMinutesQueue)==120 : 
            meanHour=0
            for i in range (59) :
               meanHour+=meanMinutesQueue.pop()
            meanHour=meanHour/60
            meanHoursQueue.appendleft(meanHour)
         if len(meanHoursQueue)==24 :
            meanHoursQueue.pop()

         sensor['lastMinutesMean'] = meanMinutesQueue
         sensor['lastHoursMean'] = meanHoursQueue
         sensor['lastValues'] = valueQueue
         sensors[pinNum] = sensor
      except KeyError :
         valueQueue=deque()
         valueQueue.appendleft(value)
	 sensor['lastValues'] = valueQueue
         sensor['lastMinutesMean'] = deque()
         sensor['lastHoursMean'] = deque()
         sensors[pinNum] = sensor
      timeStamp = timeStamp+1
      room[arduino] = sensors
      print (timeStamp)
      if timeStamp%240 == 0 : 
         with open('data.txt','w') as outfile : 
            outfile.write(demjson.encode(room))
 


