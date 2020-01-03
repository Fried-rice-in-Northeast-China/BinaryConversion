package com.example.binaryconversion;

import java.math.BigInteger;

public class Calculate {

    /**
     * number   要转换的数
     * from     原数的进制
     * to       要转换成的进制
     */
    public static String binaryChange(String number, int from, int to) {
        return new BigInteger(number, from).toString(to);
    }

    /* 判断输入是否合法 */
    public static boolean inputRight(String input, int binary){
        if(input.length() > 0){
            char[] inputItem = input.toCharArray();
            // 数字上限
            int flag = binary - 1;
            // 十进制以内
            if(binary <= 10){
                for(int i = 0; i < inputItem.length; i++){
                    if(isANum(inputItem[i])){
                        if(Integer.parseInt(inputItem[i] + "") > flag){
                            return false;
                        }
                    }
                    else{
                        return false;
                    }
                }
            }
            // 十六进制
            else{
                for(int i = 0; i < inputItem.length; i++){
                    if(!isANum(inputItem[i])){
                        if(isALetter(inputItem[i])){
                            if(!(isLessThanLetter(inputItem[i], 'f') || isLessThanLetter(inputItem[i], 'F'))) {
                                return false;
                            }
                        }
                        else {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }

    /* 判断单个字符是否为数字 */
    private static boolean isANum(char c){
        int ascii = (int) c;
        if(ascii >= 48 && ascii <= 57) {
            return true;
        }
        else {
            return false;
        }
    }

    /* 判断单个字符是否为字母 */
    private static boolean isALetter(char c){
        int ascii = (int) c;
        if((ascii >= 65 && ascii <= 90) || (ascii >= 97 && ascii <= 122)) {
            return true;
        }
        else {
            return false;
        }
    }

    /* 判断字母 A 是否小于字母 B */
    private static boolean isLessThanLetter(char a, char b){
        int aAscii = (int) a;
        int bAscii = (int) b;
        if((aAscii >= 65 && aAscii <= bAscii) || (aAscii >= 97 && aAscii <= bAscii)) {
            return true;
        }
        else {
            return false;
        }
    }

}
