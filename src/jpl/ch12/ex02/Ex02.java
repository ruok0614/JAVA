package jpl.ch12.ex02;

public class Ex02 {
    // (1) PassengerVehicleオブジェクトの定員を負の値に設定しようとした

    // → IllegalArgumentException



    // (2) オブジェクトがその初期値を設定するのに使用する設定ファイルに、文法エラーが見つかった。

    /* → Properties#loadFromXML InvalidPropertiesFormatException

     *   Gson JsonSyntaxException?

     */



    // (3) プログラマが指定した単語を文字列の配列から検索するメソッドが、その単語を発見できない。

    // → null



    // (4) openメソッドへ指定されたファイルが存在しない。

    // → FileNotFoundException



    // (5) openメソッドへ指定されたファイルは存在するが、セキュリティにより使用できない。

    // → SecurityException



    // (6) リモートのサーバプロセスにネットワークコネクションを確立しようとするが、リモートのマシンと接続できない。

    // → ConnectException, IOException



    // (7) リモートのサーバプロセスとのやり取りの最中に、ネットワークコネクションが切れる。

    // → ConnectException, IOException, SocketException
}
