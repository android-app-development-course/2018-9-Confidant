# -*- coding: utf-8 -*-
"""
Created on Thu Dec 27 23:25:34 2018

@author: HCS
"""

import numpy as np
import pandas as pd
import Filtering

data = pd.read_csv('samples.csv', header=None, )
data.columns=['id', '用户ID','用户名','发表时间',
                                  '头像图片地址','原始文本','是否有图片','图片地址1','图片地址2',
                                  '图片地址3','图片地址4','图片地址5','图片地址6','图片地址7',
                                  '图片地址8','图片地址9','视频地址','是否有转发','转发文字',
                                  'zf是否有图片','zf图片地址1','zf图片地址2','zf图片地址3','zf图片地址4',
                                  'zf图片地址5','zf图片地址6','zf图片地址7','zf图片地址8','zf图片地址9',
                                  '转发视频','转发用户ID','转发用户名']

take = []
for i in range(len(data)):
    if Filtering.predict(data.loc[i]['原始文本']) == 0:
        take.append(i)
data.drop(take, inplace=True)
data['分类标签'] = '无'
x = data[['头像图片地址', '用户名', '发表时间', '分类标签', '原始文本','图片地址1','图片地址2',
                                  '图片地址3','图片地址4','图片地址5','图片地址6','图片地址7',
                                  '图片地址8','图片地址9','视频地址', '转发用户名', '转发文字','zf是否有图片','zf图片地址1','zf图片地址2','zf图片地址3','zf图片地址4',
                                  'zf图片地址5','zf图片地址6','zf图片地址7','zf图片地址8','zf图片地址9','转发视频']]
x.rename(columns={'头像图片地址':'UserImage','用户名':'Name','Details':'详细内容','转发用户名':'zhuanfa_Name','转发文字':'zhuanfa_Details'}, inplace=True)
t = x.to_json(orient='index')
x.to_csv('过滤后的数据',index=False)
t = x[0:21]

t.to_excel('test.xlsx')





