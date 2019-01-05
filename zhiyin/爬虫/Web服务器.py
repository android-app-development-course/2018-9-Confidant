
import socket
import pandas as pd
# 创建一个socket套接字，该套接字还没有建立连接# -*- coding: utf-8 -*-
"""
Created on Thu Dec 27 20:54:21 2018

@author: HCS
"""
data = pd.read_excel('test.xlsx')
out = data.loc[[2,4]].to_json(orient='index').encode()

while (True):
    # socket.AF_INET, socket.SOCK_STREAM
    server = socket.socket()
    # 绑定监听端口，这里必须填本机的IP192.168.27.238，localhost和127.0.0.1是本机之间的进程通信使用的
    server.bind(('192.168.199.217', 8080))
    # 开始监听，并设置最大连接数
    server.listen(1)
    print(u'waiting for connect...')
    # 等待连接，一旦有客户端连接后，返回一个建立了连接后的套接字和连接的客户端的IP和端口元组
    connect, (host, port) = server.accept()
    print(u'the client %s:%s has connected.' % (host, port))
    # 接受客户端的数据
    data = connect.recv(2048)
    times = len(out)//2048
    if data == b'data':
        for i in range(times):
            connect.send(out[i*2028:i*2028+2048])
        connect.send(out[times*2048:])
        connect.send(b'huchaoshunlixiongbochenzijieluojiajun')
    else:
        print('EOR')
    # 结束socket
    server.close()

