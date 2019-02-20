package jpl.ch20.ex;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * 24ビットBMP画像を読み込んで何かしらの画像処理をするクラス
 */
public class ImageFilter {

    //private final String DIR_NAME  = "C:\\Users\\p000527232\\mygit\\JAVA\\src\\jpl\\ch20\\ex\\";
    private final String DIR_NAME  = "C:\\Users\\qazws\\IdeaProjects\\java\\src\\jpl\\ch20\\ex\\";
    private final String FILE_NAME  = "tes.bmp";
    private final int HEADER_SIZE_POSITION = 14;
    private final int HEADER_INFO_SIZE_LENGTH = 4;
    private final int HEADER_INFO_WIDTH_LENGTH = 4;
    private final int HEADER_INFO_HEIGHT_LENGTH = 4;

    private File file;
    private InputStream fInS;
    private int infoHeaderLength;
    private int imgWidth;
    private int imgHeight;
    private FileOutputStream fos;
    private BufferedOutputStream bos;
    private BufferedInputStream bis;


    public ImageFilter(){
        file = new File(DIR_NAME + FILE_NAME);
        try {
            fInS = new FileInputStream(file);
            fos = new FileOutputStream(DIR_NAME + "out.bmp");
            bis = new BufferedInputStream(fInS);
            bos = new BufferedOutputStream(fos);

            // ファイルヘッダー読み取り
            byte[] fileHeader = new byte[HEADER_SIZE_POSITION];
            fInS.read(fileHeader,0,HEADER_SIZE_POSITION);
            fos.write(fileHeader, 0, HEADER_SIZE_POSITION);

            byte[] iHeadLenByteArray = new byte[HEADER_INFO_SIZE_LENGTH];
            // 情報ヘッダの最初の要素が情報ヘッダのサイズ
            fInS.read(iHeadLenByteArray,0,HEADER_INFO_SIZE_LENGTH);
            infoHeaderLength = nByteConversion(iHeadLenByteArray);
            fos.write(iHeadLenByteArray, 0, HEADER_INFO_SIZE_LENGTH);

            //　ヘッダー情報読み取り（画像の幅）
            byte[] imgWidthByteArray = new byte[HEADER_INFO_WIDTH_LENGTH];
            fInS.read(imgWidthByteArray,0,HEADER_INFO_WIDTH_LENGTH);
            imgWidth = nByteConversion(imgWidthByteArray);
            fos.write(imgWidthByteArray, 0, HEADER_INFO_WIDTH_LENGTH);

            //　ヘッダー情報読み取り（画像の高さ）
            byte[] imgHeightByteArray = new byte[HEADER_INFO_HEIGHT_LENGTH];
            fInS.read(imgHeightByteArray,0,HEADER_INFO_HEIGHT_LENGTH);
            imgHeight = nByteConversion(imgHeightByteArray);
            fos.write(imgWidthByteArray, 0, HEADER_INFO_HEIGHT_LENGTH);

            //　ヘッダー情報読み取り（その他）
            byte[] infoHeader = new byte[infoHeaderLength - HEADER_INFO_SIZE_LENGTH - HEADER_INFO_WIDTH_LENGTH -HEADER_INFO_HEIGHT_LENGTH];
            fInS.read(infoHeader,0,infoHeaderLength - HEADER_INFO_SIZE_LENGTH -  HEADER_INFO_WIDTH_LENGTH -HEADER_INFO_HEIGHT_LENGTH);
            fos.write(infoHeader, 0, infoHeaderLength -  HEADER_INFO_SIZE_LENGTH- HEADER_INFO_WIDTH_LENGTH -HEADER_INFO_HEIGHT_LENGTH);

            int len = 0;

            ColorImage img = new ColorImage(imgWidth,imgHeight);
            byte[] buffer = new byte[3];
            for (int y = 0; y < imgHeight; y++){
                for (int x = 0; x < imgWidth; x++){
                    fInS.read(buffer);
                    img.setPixcel(x,y,buffer[0],buffer[1],buffer[2]);
                }
            }
            // img.averageFilter(5);
            // img.ySobelFilter();
            img.gaussianFilter(3,1);
            byte[] imgBuf = img.convertBuffer();



            fos.write(imgBuf,0,imgHeight*imgWidth*3);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //ファイルをクローズする
            try {
                if(bis != null)
                    bis.close();
                if(bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * n桁のバイト配列を10進数に変換します
     * @param {byte[]} bytes - 10進数変換したいbyte配列
     * @return {int} - 変換結果
     */
    public int nByteConversion(byte[] bytes){
        int result = 0;
        for(int i = 0; i <bytes.length; i++){
            if(bytes[i] < 0 ){
                result += (256 + bytes[i])* Math.pow(256,i);
            } else {
                result += bytes[i] * Math.pow(256, i);
            }
        }
        return result;
    }
    public static void main(String args[]){
        ImageFilter imf = new ImageFilter();
    }
}
