package cn.vtrump.testspringboot1.encryptdecrypt;

public class EncryptDecrypt {

    private final String ruleWord;//自定义的加密规则

    public EncryptDecrypt(String ruleWord) {
        this.ruleWord = ruleWord;
    }

    /*
    加密算法
    parameter:密码明文
    return:加密后的密码
     */
    public String encrypt(String sourceString){
        char[] r = ruleWord.toCharArray();
        int n = r.length;
        char[] s = sourceString.toCharArray();
        int m = s.length;
        for(int k = 0; k < m; k++){
            int word = s[k] + r[k%n];
            s[k] = (char) word;
        }
        return new String(s);
    }

    /*
    解密算法
    parameter:加密后的密码
    return:密码明文
    */
    public String decrypt(String sourceString){
        char[] r = ruleWord.toCharArray();
        int n = r.length;
        char[] s = sourceString.toCharArray();
        int m = s.length;
        for(int k = 0; k < m; k++){
            int word = s[k] - r[k%n];
            s[k] = (char) word;
        }
        return new String(s);
    }
}
