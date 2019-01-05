# -*- coding: utf-8 -*-
"""
Created on Thu Dec 27 21:56:31 2018

@author: HCS
"""

from re import sub
from os import listdir
from collections import Counter
from itertools import chain
from numpy import array
from jieba import cut
from sklearn.naive_bayes import MultinomialNB
#存放所有文件中的单词
#每个元素是一个子列表，其中存放一个文件中的单词 
allWords =[]

def getWordsFromFile(txtFile): 
    words =[]
    with open(txtFile, encoding='utf8') as fp: 
        for line in fp:
            line = line.strip()
            #过滤干扰字符或无效字符
            line = sub (r'[.【】0-9、——。，！~\*]', '', line)
            line = cut(line)
            #过滤长度为1的词
            line = filter (lambda word: len(word)>1, line)
            words.extend (line)
            words = [str(i) for i in words]
    return words


def getTopNWords(topN):
    #按文件编号顺序处理当前文件夹中所有记事本文件 
    #共151封邮件丙容，0.txt到126.txt是垃圾邮件内容 
    #127.txt到150.txt为正常邮件内容 
    txtFiles = ['贝叶斯邮件分类训练集//'+str(i)+'.txt' for i in range (151)] 
    #获取全部单词
    for txtFile in txtFiles:
        allWords.append(getWordsFromFile(txtFile))
    #获取并返回出现次数最多的前topN个单词
    freq = Counter(chain(*allWords))
    return [w[0] for w in freq.most_common(topN)]
#全部训练集中出现次数最多的前400个单词 
topWords = getTopNWords(600)
    
#获取特征向量，前400个单词的每个单词在每个邮件中出现的频率 
vector =[]
for words in allWords:
    temp = list(map(lambda x: words.count(x), topWords)) 
    vector.append(temp) 
vector = array(vector)
#邮件标签，1表示垃圾邮件，0表示正常邮件 
labels = array([1]*127 + [0]*24)


#创建模型，使用己知训练集进行训练
model = MultinomialNB()
model.fit (vector, labels)

def _predict(txtFile):
    words = getWordsFromFile(txtFile)
    currentVector = array(tuple(map(lambda x: words.count(x), topWords))) 
    result = model.predict(currentVector.reshape(1, -1)) 
    return True if result==1 else False
    #如果可以通过则为True，否则为垃圾信息
    
def predict(String):
    f = open('temp.txt', 'wb')
    f.write(String.encode())
    f.close()
    return _predict('temp.txt')


    
