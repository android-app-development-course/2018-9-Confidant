# -*- coding: utf-8 -*-
"""
Created on Sat Jan  5 16:02:43 2019

@author: HCS
"""
import numpy as np
import pandas as pd
from keras import models
from keras import layers
import matplotlib.pyplot as plt
from keras import  regularizers
from sklearn.model_selection import train_test_split


#载入数据
savedir = '微博邮件分类训练结果'
'''
dic = set()
for i in ['IT', '财经', '传媒', '动漫', '房产', '广告公共', '健康', '教育', '旅游', 
          '美食', '女性', '汽车', '人文艺术', '生活', '时尚', '体育', '文学出版', '校园', 
          '游戏', '娱乐', '育儿']:
    tempData = pd.read_excel(savedir+'//'+i+'.xlsx')
    for i in tempData:
        for j in tempData[i]:
            dic.add(j)
'''
dic = pd.read_excel('向量名.xlsx', header=None)
dic = [i[0] for i in dic.values]
dic.append('种类hahaha')
data = pd.DataFrame([], columns=dic)
kind = ['IT', '财经', '传媒', '动漫', '房产', '广告公共', '健康', '教育', '旅游', 
          '美食', '女性', '汽车', '人文艺术', '生活', '时尚', '体育', '文学出版', '校园', 
          '游戏', '娱乐', '育儿']
for i in kind:
    tempData = pd.read_excel(savedir+'//'+i+'.xlsx')
    for k in range(len(tempData)):
        tempSeries = pd.Series(np.zeros(len(dic)), index=dic)
        for j in tempData.loc[k]:
            tempSeries[j] = 1
        tempSeries['种类hahaha'] = i
        data = data.append(tempSeries, ignore_index=True)

m = dict()
for i in range(len(kind)):
    m[kind[i]] = i

X = data.drop('种类hahaha', axis=1)
y = data['种类hahaha'].map(m)


'''
x_train, x_val, y_train, y_val = train_test_split(X.values, y.values, test_size=0.3)
print('载入数据完成')


# 定义模型
model = models.Sequential()
model.add(layers.Dense(128, activation='relu', input_shape=(len(dic)-1,)))
model.add(layers.Dense(64, activation='relu'))
model.add(layers.Dense(32, activation='relu'))
model.add(layers.Dense(16, activation='softmax'))
print('定义模型完成')


# 编译模型
model.compile(optimizer='rmsprop',
              loss='categorical_crossentropy',
              metrics=['accuracy'])
print('编译模型完成')


# 训练模型
history = model.fit(x_train,
                    y_train,
                    epochs=20,
                    batch_size=512,
                    validation_data=(x_val, y_val))
print('训练模型完成')
'''
import pickle
pic