# -*- coding: utf-8 -*-
"""
Created on Thu Dec  2 00:26:58 2021

@author: madca
"""

import time
import threading
from threading import Thread

def sleepMe(i):
    print("Поток %s засыпает на 5 секунд.\n" % threading.current_thread())
    time.sleep(5)
    print("Поток %s сейчас проснулся." % threading.current_thread())

# Cоздаем только четыре потока
for i in range(10):
    th = Thread(target=sleepMe, args=(i, ))
    th.start()