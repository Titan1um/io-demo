package com.jun.iodemo.localIO;

import java.io.*;

public class Reader_Writer {

    /**
     * output记得flush
     * flush()
     *
     * 虽然可以直接读int to char，但是用byte去读写会快将近10倍
     */
    public static void main(String args[]) {
        //两个基类
        Reader reader;
        /**
         *  常用方法：
         *  int read()
         *  int read(char[] chars)
         *  int read(char[] chars,int off,int len)
         *  long skip(long n)
         *  void close()
         */
        Writer writer;
        /**
         *  常用方法:
         *  void write(char[] chars)
         *  void write(char[] chars,int off,int len)
         *  void write(int c)
         *  void write(String str)
         *  void write(String str,int off,int len)
         *
         *  Writer append(char c)
         *  Writer append(CharSequence csq):
         *  将指定字符序列添加到此writer
         *
         *  Writer append(CharSequence csq, int start, int end):
         *  将指定字符序列的子序列添加到此writer.Appendable
         *
         *  void flush()
         *  void close()
         */


        //字符转换流
        InputStreamReader inputStreamReader;
        OutputStreamWriter outputStreamWriter;
        //字符转换流的直接子类，便携字符文件的读写
        FileReader fileReader;
        FileWriter fileWriter;


        //字符缓冲流
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;


        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream("test_Stream_Writer_Reader.txt"));
            outputStreamWriter.write("Fuck you,dude\r\n");
            outputStreamWriter.append("yo, mtfk.\n");
            outputStreamWriter.flush();
            outputStreamWriter.close();

            inputStreamReader = new InputStreamReader(new FileInputStream("test_Stream_Writer_Reader.txt"));
            int len;
            while ((len = inputStreamReader.read()) != -1) {
                System.out.print((char) len);
            }
            inputStreamReader.close();


            fileWriter = new FileWriter(new File("test_Stream_Writer_Reader.txt"), true);
            fileWriter.append("hello?\n");
            fileWriter.append("mtfk??\n");
            fileWriter.flush();
            fileWriter.close();

            fileReader = new FileReader(new File("test_Stream_Writer_Reader.txt"));
            int len2;
            while ((len2 = fileReader.read()) != -1) {
                System.out.print((char) len2);
            }
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("test_Stream_Writer_Reader"))));
            bufferedWriter.write("- ur dead,dude\n");
            bufferedWriter.write(("- copy that\n"));
            bufferedWriter.append("- cool\n");
            bufferedWriter.flush();
            bufferedWriter.close();

            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("test_Stream_Writer_Reader"))));
            int len;
            while ((len = bufferedReader.read()) != -1) {
                System.out.print((char) len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
