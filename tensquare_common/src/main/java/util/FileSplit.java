package util;

import java.io.*;

public class FileSplit {

    /**
     * 将大文件拆分为多个小文件，拆分后文件名为：smallFilePrefixi.smallFileSuffix(i从1开始)
     *
     * @param bigFilePath     需要拆分的文件路径
     * @param smallFilePrefix 拆分后的文件名
     * @param smallFileSuffix 拆分后文件后缀
     * @param splitNum        要分割的块数减一
     * @param fileLines       需要拆分文件的行数
     */
    public void fileSplit(String bigFilePath, String smallFilePrefix, String smallFileSuffix, int splitNum, int fileLines) {
        long timer = System.currentTimeMillis();
        int bufferSize = 20 * 1024 * 1024;//设读取文件的缓存为20MB

        try {
            //建立缓冲文本输入流
            File file = new File(bigFilePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            InputStreamReader inputStreamReader = new InputStreamReader(bufferedInputStream);
            BufferedReader input = new BufferedReader(inputStreamReader, bufferSize);

//            int splitNum = 112 - 1;//要分割的块数减一
//            int fileLines = 10722;//输入文件的行数
            long perSplitLines = fileLines / (splitNum - 1);//每个块的行数
            for (int i = 0; i <= splitNum; ++i) {
                //分割
                //每个块建立一个输出
                FileWriter output = new FileWriter(smallFilePrefix + (i + 1) + smallFileSuffix);
                String line = null;
                //逐行读取，逐行输出
                for (long lineCounter = 0; lineCounter < perSplitLines && (line = input.readLine()) != null; ++lineCounter) {
                    output.append(line + "\r\n");
                }
                output.flush();
                output.close();
                output = null;
            }
            input.close();
            timer = System.currentTimeMillis() - timer;
            System.out.println("处理时间：" + timer);
        } catch (Exception e) {

        }
    }
}
