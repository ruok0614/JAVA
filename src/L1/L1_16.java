package L1;

/**
 * 練習問題1.16
 * 　BadDataSetExceptionにフィールドを追加してデータの集まりの名前と，
 * 問題を通知しているI/O例外を保持できるようにしなさい．
 * それにより，その例外をキャッチしてエラーの詳細を知ることができるようになります．
 */

class BadDataSetException extends Exception{}

//class L1_16{
//    public double [] getDataSet(String setName) throws BadDataSetException {
//        String file = setName + ".dset";
//        FileInputStream in = null;
//        try{
//            in = new FileInputStream(file);
//            return readDataSet(in);
//        }catch (IOException e){
//            throw new BadDataSetException();
//        }finally {
//            try {
//                if(in != null)
//                    in.close();
//            }catch (IOException e){
//                ;
//            }
//        }
//    }
//

//}