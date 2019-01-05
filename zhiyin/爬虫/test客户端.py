# -*- coding: utf-8 -*-
"""
Created on Thu Dec 27 21:01:09 2018

@author: HCS
"""

import socket

obj = socket.socket()
obj.connect(("192.168.199.217",3000))

ret = str(obj.recv(1024),encoding="utf-8")
print(ret)