package jpl.ch20.ex;

public class ColorImage {
    private byte blue[][];
    private byte green[][];
    private byte red[][];

    private int width;
    private int height;

    ColorImage(int width, int height){
        this.width = width;
        this.height = height;
        blue = new byte[this.width][this.height];
        green = new byte[this.width][this.height];
        red = new byte[this.width][this.height];
    }

    public void setPixcel(int x, int y, byte b, byte g, byte r) {
        blue[x][y] = b;
        green[x][y] = g;
        red[x][y] = r;
    }

    public byte[] convertBuffer() {
        int size = width * height * 3;
        byte[] buf = new byte[size];
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                buf[3 * x + y * width * 3] = blue[x][y];
                buf[3 * x + y * width * 3 + 1] = green[x][y];
                buf[3 * x + y * width * 3 + 2] = red[x][y];
            }
        }
        return buf;
    }

    public void averageFilter(int kernelSize) {
        double[][] filterCoefficient = new double[kernelSize][kernelSize];
        for (int y = 0; y < kernelSize; y++){
            for (int x = 0; x < kernelSize; x++) {
                filterCoefficient[x][y] = 1/Math.pow(kernelSize, 2);
            }
        }

        blue = filter(blue,filterCoefficient);
        green = filter(green,filterCoefficient);
        red = filter(red,filterCoefficient);

    }

    public void ySobelFilter(){
        double[][] filterCoefficient = {{1,2,1},{0,0,0},{-1,-2,-1}};

        blue = filter(blue,filterCoefficient);
        green = filter(green,filterCoefficient);
        red = filter(red,filterCoefficient);

    }

    public void xSobelFilter(){
        double[][] filterCoefficient = {{1,0,-1},{2,0,-2},{1,0,-1}};

        blue = filter(blue,filterCoefficient);
        green = filter(green,filterCoefficient);
        red = filter(red,filterCoefficient);

    }

    public void gaussianFilter(int kernelSize, double stddDev){
        double[][] filterCoefficient = new double[kernelSize][kernelSize];
        double sum = 0;
        for (int y = 0; y < kernelSize; y++){
            for (int x = 0; x < kernelSize; x++) {
                filterCoefficient[x][y] = ( 1/(Math.sqrt(2 * Math.PI)*stddDev) ) * Math.exp(- (Math.pow(kernelSize, 2) + Math.pow(kernelSize, 2))/2*Math.pow(stddDev, 2));
                sum += ( 1/(Math.sqrt(2 * Math.PI)*stddDev) ) * Math.exp(- (Math.pow(kernelSize, 2) + Math.pow(kernelSize, 2))/2*Math.pow(stddDev, 2));
            }
        }
        for (int y = 0; y < kernelSize; y++){
            for (int x = 0; x < kernelSize; x++) {
                filterCoefficient[x][y] = filterCoefficient[x][y] * (1/sum);
            }
        }

        blue = filter(blue,filterCoefficient);
        green = filter(green,filterCoefficient);
        red = filter(red,filterCoefficient);

    }
    private byte[][] filter(byte[][] input, double[][] filterCoefficient){
        byte[][] out = new byte[this.width][this.height];
        for (int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                double res = 0;
                int center = filterCoefficient.length/2 + 1;
                int maxMargin = filterCoefficient.length/2;
                for (int fy = 0; fy < filterCoefficient.length; fy++) {
                    for (int fx = 0; fx < filterCoefficient[0].length; fx++) {
                        byte tmp = 0;
                        if( ((x + (maxMargin - fx)) >= width) || ((x + (maxMargin - fx)) < 0) ) {
                            if(  ((y + (maxMargin - fy)) >= height) || ((y + (maxMargin - fy)) < 0) ) {
                                tmp = input[x - (maxMargin - fx)][y - (maxMargin - fy)];
                            } else {
                                tmp = input[x - (maxMargin - fx)][y + (maxMargin - fy)];
                            }
                        } else if( ((y + (maxMargin - fy)) >= height) || ((y + (maxMargin - fy)) < 0) ) {
                            if(  ((x + (maxMargin - fx)) >= width) || ((x + (maxMargin - fx)) < 0) ) {
                                tmp = input[x - (maxMargin - fx)][y - (maxMargin - fy)];
                            }else {
                                tmp = input[x + (maxMargin - fx)][y - (maxMargin - fy)];
                            }
                        } else{
                            //System.out.printf("x: %d ,y: %d,fx: %d ,fy: %d\n",x,y,fx,fy);
                            tmp = input[x + (maxMargin - fx)][y + (maxMargin - fy)];
                        }
                        res += intToByte( (int)(byteToInt(tmp) * filterCoefficient[fx][fy]) );
                    }
                }
                //System.out.println((byte)res);
                out[x][y] = (byte)res;
            }
        }
        return out;
    }

    private int byteToInt(byte b){
        int res;
        if(b < 0){
            res = (int)b + 256;
        }else {
            res = (int)b;
        }
        return res;
    }
    private byte intToByte(int i){
        int res;
        if(i > 127){
            res = i - 255;
        }else {
            res = i;
        }
        return (byte)res;
    }

}
