import requests
from bs4 import BeautifulSoup

url=r'https://passport.weibo.cn/sso/login'


#构造参数字典
data={'username':'13560090589',
      'password':'hcs123234',
      'savestate':'1',
      'r':r'',
      'ec':'0',
      'pagerefer':'',
      'entry':'mweibo',
      'wentry':'',
      'loginfrom':'',
      'client_id':'',
      'code':'',
      'qq':'',
      'mainpageflag':'1',
      'hff':'',
      'hfp':''}

#headers，防屏
headers = {'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36',
            'Accept':'text/html;q=0.9,*/*;q=0.8',
            'Accept-Charset':'ISO-8859-1,utf-8;q=0.7,*;q=0.3',
            'Connection':'close',
            'Referer':'https://passport.weibo.cn/signin/login',
            'Host':'passport.weibo.cn'
            }
#模拟登录
session=requests.session()
session.post(url=url,data=data,headers=headers)
#测试，爬取周志华微博原创内容
for page in range(1,42):
    response=session.get('https://weibo.cn/zhouzh2012?filter=1&page=%d' % page).content
    soup=BeautifulSoup(response,'lxml')
    infos=soup.find_all('span','ctt')
    for info in infos[3:]:
        print (info.get_text())