# 导入数据请求模块
import requests
# 导入正则表达式模块
import re
# 导入json模块
import json
import os
import subprocess
# TODO 记得更改你要的url和你自己的cookie

# 发送请求

class BilibiliSpider:
        def __init__(self):
                self.url = 'https://www.bilibili.com/video/BV1d54y1g7db'
                self.cookie = "buvid_fp_plain=undefined; buvid4=03940449-9BE7-3463-3097-B7B9C73B716004692-022050710-tIPa9o7%2Byx9Z%2BHgxi1UJCw%3D%3D; DedeUserID=2068832162; DedeUserID__ckMd5=e3b8efce7de57c36; CURRENT_BLACKGAP=0; buvid3=4998B764-C812-9444-26A0-50FF35AD1D7F86921infoc; b_nut=1702481086; _uuid=71018C185-AE9F-928E-CE61-72F8D46A6FCC87403infoc; enable_web_push=DISABLE; header_theme_version=CLOSE; rpdid=0zbfVH2Tfd|2pT9J8tO|4ie|3w1RoCJS; hit-dyn-v2=1; CURRENT_QUALITY=80; FEED_LIVE_VERSION=V_WATCHLATER_PIP_WINDOW3; fingerprint=d817c85c18480d6c4907c083660277bf; is-2022-channel=1; home_feed_column=5; buvid_fp=d817c85c18480d6c4907c083660277bf; LIVE_BUVID=AUTO7517290513428176; PVID=2; browser_resolution=2229-1161; SESSDATA=783c2e07%2C1745243347%2C628af%2Aa2CjDKRFW1lrNiO6c6bg_CXm_zomwFymm0xwzhzCDLwvNf8wWlSysiBX7AwVc1CuxHtXwSVjdxRjlPR2puemlka293bEdsUGNkZHVoQU9HazFRZEtxMVE5R3JoU3lhRHR2MXNPWmhFSTZYZkd1VEx5cnBEc293SlRvSkR4TnZsRU1LenNrSE94WUZBIIEC; bili_jct=cd208a227400614eef764e7494dcfdb0; bili_ticket=eyJhbGciOiJIUzI1NiIsImtpZCI6InMwMyIsInR5cCI6IkpXVCJ9.eyJleHAiOjE3MzAwMjIwMTAsImlhdCI6MTcyOTc2Mjc1MCwicGx0IjotMX0.aBJ_k9lbG0fOFB1II1D_HNBrHF2b5gk2C837lWKQ0zA; bili_ticket_expires=1730021950; bmg_af_switch=1; bmg_src_def_domain=i0.hdslb.com; sid=6bcr6fof; CURRENT_FNVAL=4048; b_lsid=98410E33A_192C70E5C36; bp_t_offset_2068832162=992481661234970624"
                self.headers = {
                        # Referer 防盗链 告诉服务器你请求链接是从哪里跳转过来的
                        "Referer": "https://www.bilibili.com/video/BV1454y187Er/",
                        # User-Agent 用户代理, 表示浏览器/设备基本身份信息
                        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0",
                        "Cookie": self.cookie
                }
                self.video_file_path = ""
                self.audio_file_path = ""
                self.output_file_path = ""
        #解析视频信息
        def SaveData(self):
                response = requests.get(url=self.url, headers=self.headers)
                html = response.text
                # 解析数据: 提取视频标题
                title = re.findall('title="(.*?)"', html)[0]
                # print(title)
                # 提取视频信息
                info = re.findall('window.__playinfo__=(.*?)</script>', html)[0]
                # info -> json字符串转成json字典
                print(info)
                json_data = json.loads(info)

                # 提取视频链接
                video_url = json_data['data']['dash']['video'][0]['baseUrl']
                print(video_url)
                # 提取音频链接
                audio_url = json_data['data']['dash']['audio'][0]['baseUrl']
                print(audio_url)
                video_content = requests.get(url=video_url, headers=self.headers).content
                # 获取音频内容
                audio_content = requests.get(url=audio_url, headers=self.headers).content
                # 保存数据
                self.video_file_path = 'video\\'+title+'.mp4'
                self.audio_file_path = 'video\\'+title+'.mp3'
                self.output_file_path = 'final\\'+title+'.mp4'
                with open(self.video_file_path, mode='wb') as v:
                        v.write(video_content)
                with open(self.audio_file_path, mode='wb') as a:
                        a.write(audio_content)
        def Compound(self):

                command = [
                        'ffmpeg',
                        '-i', self.video_file_path,
                        '-i', self.audio_file_path,
                        '-c:v', 'copy',
                        '-c:a', 'copy',
                        self.output_file_path
                ]
                subprocess.run(command, check=True)
                print(f"视频和音频合成成功，输出文件为: {self.output_file_path}")
                os.remove(self.video_file_path)
                os.remove(self.audio_file_path)



if __name__ == '__main__':
        bilibiliSpider = BilibiliSpider()
        bilibiliSpider.SaveData()
        bilibiliSpider.Compound()
