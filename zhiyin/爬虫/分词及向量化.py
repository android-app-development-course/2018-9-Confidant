import jieba
import re
from gensim.models import Word2Vec
import jieba.analyse
import os
import pandas as pd

#keywords_2 = jieba.analyse.textrank(sentence, topK=10, withWeight=False, allowPOS=('ns', 'n', 'vn', 'v'))
Files_path = '微博分类语料//'
datalist = {}

for i in ['IT', '财经', '传媒', '动漫', '房产', '广告公共', '健康', '教育', '旅游', 
          '美食', '女性', '汽车', '人文艺术', '生活', '时尚', '体育', '文学出版', '校园', 
          '游戏', '娱乐', '育儿']:
  currentlist = os.listdir(Files_path+i)
  data = pd.DataFrame([], columns=range(10))
  for j in currentlist:
      keywords=[]
      f = open(Files_path+i+'//'+j, 'r', errors='ignore')
      currentText = f.read()
      keywords = jieba.analyse.textrank(currentText, topK=10, withWeight=False, allowPOS=('ns', 'n', 'vn', 'v'))
      data = data.append(pd.Series(keywords), ignore_index=True)
      f.close()
  datalist[i] = data

savedir = '微博邮件分类训练结果'
for i in datalist.keys(): 
    datalist[i].to_excel(savedir+'//'+i+'.xlsx', index=False)



    