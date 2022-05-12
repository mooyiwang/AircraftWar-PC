package edu.hitsz.application;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {


        /***
         * ！！！！非程序入口！！！！！！
         * 此类仅用于开发时测试没某些代码
         * 与主程序无关
         */


        MusicThreadLoop thread2 = new MusicThreadLoop("bgm.wav");
            thread2.start();
            System.out.println(thread2.isInterrupted());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A");
            thread2.setStop();
            System.out.println(thread2.isInterrupted());
            System.out.println(thread2.getStop());










    }
}
