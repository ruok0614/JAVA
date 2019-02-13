package jpl.ch20.ex;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * 24ビットBMP画像を読み込んで何かしらの画像処理をするクラス
 */
public class ImageFilter {

    final String DIR_NAME  = "C:\\Users\\qazws\\IdeaProjects\\java\\src\\jpl\\ch20\\ex\\";
    final String FILE_NAME  = "tes.bmp";
    final int HEADER_SIZE_POSITION = 14;
    final int HEADER_INFO_SIZE_LENGTH = 4;
    final int HEADER_INFO_WIDTH_LENGTH = 4;
    final int HEADER_INFO_HEIGHT_LENGTH = 4;
    File file;
    InputStream fInS;
    int infoHeaderLength;
    int imgWidth;
    int imgHeight;

    public ImageFilter(){
        file = new File(DIR_NAME + FILE_NAME);
        try {
            fInS = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(DIR_NAME + "out.bmp");

            byte[] fileHeader = new byte[HEADER_SIZE_POSITION];
            fInS.read(fileHeader,0,HEADER_SIZE_POSITION);
            fos.write(fileHeader, 0, HEADER_SIZE_POSITION);

            byte[] iHeadLenByteArray = new byte[HEADER_INFO_SIZE_LENGTH];
            // 情報ヘッダの最初の要素が情報ヘッダのサイズ
            fInS.read(iHeadLenByteArray,0,HEADER_INFO_SIZE_LENGTH);
            infoHeaderLength = nByteConversion(iHeadLenByteArray);
            fos.write(iHeadLenByteArray, 0, HEADER_INFO_SIZE_LENGTH);

            byte[] imgWidthByteArray = new byte[HEADER_INFO_WIDTH_LENGTH];
            fInS.read(imgWidthByteArray,0,HEADER_INFO_WIDTH_LENGTH);
            imgWidth = nByteConversion(imgWidthByteArray);

            byte[] imgHeightByteArray = new byte[HEADER_INFO_HEIGHT_LENGTH];
            fInS.read(imgHeightByteArray,0,HEADER_INFO_HEIGHT_LENGTH);
            imgHeight = nByteConversion(imgHeightByteArray);

            // ファイルヘッダのサイズも情報ヘッダの最初の要素なので-4する
            byte[] infoHeader = new byte[infoHeaderLength - HEADER_INFO_SIZE_LENGTH];
            fInS.read(infoHeader,0,infoHeaderLength - HEADER_INFO_SIZE_LENGTH);

            fos.write(infoHeader, 0, infoHeaderLength - HEADER_INFO_SIZE_LENGTH);

            byte[] buffer = new byte[3];
            int len = 0;

            while((len = fInS.read(buffer)) != -1) {
                buffer[0] = 0;
                fos.write(buffer, 0, len);
            }

            //ファイルに書き込む
            fos.flush();
            //ファイルをクローズする
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * n桁のバイト配列を10進数に変換します
     */
    public int nByteConversion(byte[] bytes){
        int result = 0;
        for(int i = 0; i <bytes.length; i++){
            if(bytes[i] < 0 ){
                bytes[i] = bytes[i];
            }
            result += bytes[i] * Math.pow(256,i);
        }
        return result;
    }
    public static void main(String args[]){
        ImageFilter imf = new ImageFilter();
    }
}
