import requests
from bs4 import BeautifulSoup
import pandas as pd
import time
import numpy as np
url = r'https://passport.weibo.cn/sso/login'


# 构造参数字典
data = {'username': '13560090589',
        'password': 'hcs123234',
        'savestate': '1',
        'r': r'',
        'ec': '0',
        'pagerefer': '',
        'entry': 'mweibo',
        'wentry': '',
        'loginfrom': '',
        'client_id': '',
        'code': '',
        'qq': '',
        'mainpageflag': '1',
        'hff': '',
        'hfp': ''}


# headers，防屏
headers = {
'Accept': r'*/*',
'Accept-Encoding': 'gzip, deflate, br',
'Accept-Language': 'zh-CN,zh;q=0.9',
'Connection': 'keep-alive',
'Content-Length': '148',
'Content-Type': r'application/x-www-form-urlencoded',
'Cookie': r'',
'Host': 'passport.weibo.cn',
'Origin': r'https://passport.weibo.cn',
'Referer': r'https://passport.weibo.cn/signin/login?',
'User-Agent': r'Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Mobile Safari/537.36',
}


# 模拟登录
session = requests.session()
session.post(url=url, data=data, headers=headers)





# 定义资源前缀
Pic = "http:////wx1.sinaimg.cn//large//"
savedData = pd.DataFrame([], 
                         columns=['用户ID','用户名','发表时间',
                                  '头像图片地址','原始文本','是否有图片','图片地址1','图片地址2',
                                  '图片地址3','图片地址4','图片地址5','图片地址6','图片地址7',
                                  '图片地址8','图片地址9','视频地址','是否有转发','转发文字',
                                  'zf是否有图片','zf图片地址1','zf图片地址2','zf图片地址3','zf图片地址4',
                                  'zf图片地址5','zf图片地址6','zf图片地址7','zf图片地址8','zf图片地址9',
                                  '转发视频','转发用户ID','转发用户名'])
# 爬取
def getText(item):
    text = item.get('raw_text', None)
    if text == None:
        text = item.get('text', None)
    soup = BeautifulSoup(text, "lxml")
    
    return soup.get_text()

def getZfPic(item):
    pic = pd.Series(np.zeros(10), index=['zf是否有图片','zf图片地址1','zf图片地址2','zf图片地址3','zf图片地址4',
                                  'zf图片地址5','zf图片地址6','zf图片地址7','zf图片地址8','zf图片地址9'])
    for i in ['zf是否有图片','zf图片地址1','zf图片地址2','zf图片地址3','zf图片地址4',
                                  'zf图片地址5','zf图片地址6','zf图片地址7','zf图片地址8','zf图片地址9']:
        pic[i] = np.nan    
    if item.get('pic_ids', None) != None and item['pic_ids'] != []:
        pic['zf是否有图片'] = 1
        for i,url in enumerate(item['pics']):
            pic['zf图片地址'+str(i+1)] = url['url']
    else:
        pic['zf是否有图片'] = 0
    return pic
  
def getPic(item):
    pic = pd.Series(np.zeros(10), index=['是否有图片','图片地址1','图片地址2',
                                  '图片地址3','图片地址4','图片地址5','图片地址6','图片地址7',
                                  '图片地址8','图片地址9'])
    for i in ['是否有图片','图片地址1','图片地址2',
                                  '图片地址3','图片地址4','图片地址5','图片地址6','图片地址7',
                                  '图片地址8','图片地址9']:
        pic[i] = np.nan
        
    if item['pic_ids'] != []:
        pic['是否有图片'] = 1
        for i,url in enumerate(item['pics']):
            pic['图片地址'+str(i+1)] = url['url']
    else:
        pic['是否有图片'] = 0
    return pic

def getVideo(item):
    if item.get('page_info', None) == None or item['page_info'].get('media_info', None) == None:
        return None
    media_info = item['page_info']['media_info']
    video = media_info.get('stream_url_hd', None)
    if video == None:
        video = media_info.get('stream_url', None)
    return video

def getZuan(item):
    zhuan = pd.Series([False, None, None, None, None, None], 
                      index = ['是否有转发','转发文字','转发图片',
                     '转发视频','转发用户ID','转发用户名'])
    if item.get('retweeted_status', None) == None:
        return zhuan
    temp = item['retweeted_status']
    zhuan['是否有转发'] = True
    zhuan['转发文字'] = getText(temp)
    zhuan = pd.concat([zhuan, getZfPic(temp)])
    zhuan['转发视频'] = getVideo(temp)
    if temp.get('user', None) != None:
        zhuan['转发用户ID'] = temp['user'].get('id', None)
        zhuan['转发用户名'] = temp['user'].get('screen_name', None)
    return zhuan


# 爬取
headers_items = {
    'Accept': r'application/json, text/plain, */*',
    'MWeibo-Pwa':'1',
    'Referer': r'https://m.weibo.cn/',
    'User-Agent' :  r'Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Mobile Safari/537.36',
    'X-Requested-With' : 'XMLHttpRequest'
}
session.headers = headers_items
nextID = ''
AllID = []
for j in range(1):
    # 刷新
    nextID=''
    savedData = pd.DataFrame([], 
                 columns=['用户ID','用户名','发表时间',
                                  '头像图片地址','原始文本','是否有图片','图片地址1','图片地址2',
                                  '图片地址3','图片地址4','图片地址5','图片地址6','图片地址7',
                                  '图片地址8','图片地址9','视频地址','是否有转发','转发文字',
                                  'zf是否有图片','zf图片地址1','zf图片地址2','zf图片地址3','zf图片地址4',
                                  'zf图片地址5','zf图片地址6','zf图片地址7','zf图片地址8','zf图片地址9',
                                  '转发视频','转发用户ID','转发用户名'])
    for i in range(20):
        goto = False
        r = session.get(r'https://m.weibo.cn/feed/friends?' + nextID)
        r_json = r.json()['data']
        nextID = 'max_id=' + str(r_json['next_cursor'])
        if r_json['ad'] == []:
            for item in r_json['statuses']:
                if item['id'] in AllID:
                    goto = True
                    break;
                AllID.append(item['id'])
                currentData = pd.Series([])
                currentData['用户ID'] = item['user']['id']
                currentData['用户名'] = item['user']['screen_name']
                currentData['发表时间'] = item['created_at']
                currentData['头像图片地址'] = item['user']['avatar_hd']
                currentData['原始文本'] = getText(item)
                currentData = pd.concat([currentData, getPic(item)])
                currentData['视频地址'] = getVideo(item)
                currentData = pd.concat([currentData, getZuan(item)])
                savedData.loc[item['id']] = currentData
        time.sleep(10) #每十秒执行一次，每次爬取20条，共执行20次，得到400条，每次执行时间为200秒
        if goto == True:
            break;
    savedData.to_csv('samples.csv', encoding='utf-8', mode='a', header=False)
    # 由于CSV文件没有带有编码信息，所以可能出现乱码
    time.sleep(90) #每15分钟执行一次

    
    
    
    

        
        
    