package com.jun.iodemo;

import java.io.*;

public class Stream {

    /**
     * output记得flush
     * flush()
     */
    public static void main(String args[]) {
        //俩基类
        InputStream inputStream;
        /**
         *  常用方法:
         *  int read():
         *  读一个字节
         *
         *  int read(byte[] b):
         *  读len个字节
         *
         *  int read(byte[] b, int off, int len)
         *  从offset起读len个字节
         *
         *  long skip(long n)
         *
         *  close()
         */
        OutputStream outputStream;
        /**
         *  常用方法：
         *  void write(byte[] b)
         *
         *  void write(byte[] b, int off, int len)
         *
         *  void write(int b):
         *  将指定字节写入此输出流
         */


        //字节文件操作流
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;

        //字节缓冲流(高效流)
        BufferedInputStream bufferedInputStream;
        BufferedOutputStream bufferedOutputStream;


        //file
        try {
            fileOutputStream = new FileOutputStream(new File("testFileStream.txt"));
            fileOutputStream.write("ABCD".getBytes());
            fileOutputStream.close();

            fileOutputStream = new FileOutputStream("testFileStream.txt", true);
            fileOutputStream.write("\r\n".getBytes());
            fileOutputStream.write("hello,mtfk".getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();

            inputStream = new FileInputStream(new File("testFileStream.txt"));
            int i = 0;
            byte[] bytes = new byte[2];
            while ((i = inputStream.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, i));
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        //buffered
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("testBuffered.txt")));
            bos.write("hello mtfk\r\n".getBytes());
            bos.write("whats up? dude? go fuck ur self".getBytes(), 0, 15);
            bos.flush();
            bos.close();

            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("testBuffered.txt"));
            int len = 0;
            byte[] bytes = new byte[1];
            while ((len = bis.read(bytes)) != -1) {
                System.out.print(new String(bytes));
            }
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
