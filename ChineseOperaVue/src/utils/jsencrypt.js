import Cookies from 'js-cookie';
import CryptoJS from 'crypto-js';


const secretKey = "wangguangan";


export function EncryptedPassword(password) {
    const encryptedPassword = CryptoJS.AES.encrypt(password, secretKey).toString();
    return encryptedPassword;
}

export function DecryptPassword(password) {
    if (password) {
        const bytes = CryptoJS.AES.decrypt(password, secretKey);
        const decryptedPassword = bytes.toString(CryptoJS.enc.Utf8);
        return decryptedPassword;
    }
    return null;
}
