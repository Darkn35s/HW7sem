#97-122
import hashlib
from threading import Thread
import numpy as np
import time
def reversed3(var): 
    if len(var) == 1:
        return var
    return var[-1] + reversed3(var[:-1])

DicSys26={
  0: 'a',
  1: 'b',
  2: 'c',
  3: 'd',
  4: 'e',
  5: 'f',
  6: 'g',
  7: 'h',
  8: 'i',
  9: 'j',
  10: 'k',
  11: 'l',
  12: 'm',
  13: 'n',
  14: 'o',
  15: 'p',
  16: 'q',
  17: 'r',
  18: 's',
  19: 't',
  20: 'u',
  21: 'v',
  22: 'w',
  23: 'x',
  24: 'y',
  25: 'z'
   }

def GenPSWD(LowerBound, UppperBound,ThreadId, SoughtData):
    for i in range(LowerBound, UppperBound):
        tmp=TransferFrom10(i)
        hash_object = hashlib.sha256(tmp.encode('utf-8'))
        hex_dig = hash_object.hexdigest()
        for Sought in SoughtData:
            if Sought==hex_dig:
                print("Thread-"+str(ThreadId)+"   Найдено совпадение: "+tmp+"   "+hex_dig)
    return 0


def TransferFrom10(temp):
    Sys26temp=""
    for i in range(5):
        Sys26temp+=DicSys26[temp%26]
        temp=temp//26
    return reversed3(Sys26temp)




N=26**5-1
ThreadCnt=int(input())
SoughtSHA=["1115dd800feaacefdf481f1f9070374a2a81e27880f187396db67958b207cbad","3a7bd3e2360a3d29eea436fcfb7e44c735d117c42d1c1835420b6b9942dd4f1b","74e1bb62f8dabb8125a58852b63bdf6eaef667cb56ac7f7cdba6d7305c50a22f"]
Start=time.perf_counter()
ItrPerThread=N//ThreadCnt
UppBnd=ItrPerThread
LwrBnd=0
threads=[]
for j in range(ThreadCnt):
    th = Thread(target=GenPSWD, args=(LwrBnd, UppBnd, j, SoughtSHA, ))
    threads.append(th)
    LwrBnd=UppBnd
    UppBnd+=ItrPerThread
    if UppBnd==N:
        UppBnd=N

for thread in threads:
    thread.start()


for thread in threads:
    thread.join()
End=time.perf_counter()
print("\n \n \n Время выпонения:"+str(End-Start))





























