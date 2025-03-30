import request from '@/utils/request.js'

// 获取音频播放器图片的函数
export function getAudioPictureUrl(tag){
    const audioPathMap= {
        "京剧": "jingju",
        "黄梅戏": "huangmeixi",
        "豫剧": "yuju",
        "川剧":"chuanju",
        "越剧":"yueju",
        "评剧":"pingju",
        "秦腔":"qinqiang",
        "晋剧":"jinju",
        "豫剧":"yuju",
        "花鼓戏":"huaguxi"
    };
    
    const serverAddress = "https://chineseopera.oss-cn-wulanchabu.aliyuncs.com/";
    const path = audioPathMap[tag];
    const dir = "picture/"
    const suffix = ".png";
    const pictureUrl = `${serverAddress}${dir}${path}${suffix}`;
    return pictureUrl;
}

export function getAudioListByTag(params){
    return request({
        url:'/audio/getaudiobytag',
        method:'get',
        params:params
    })
}


export function uploadFile(data){
    return request({
        url:'/file/upload',
        method:'post',
        headers: {
            "Content-Type": "multipart/form-data",  
            isToken: false
        },
        data:data
    })
}
export function addAudioBatch(data){
    return request({
        url:'/audio/addaudiobatch',
        method:'post',
        data:data
    })
}