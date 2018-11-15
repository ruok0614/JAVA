package jpl.ch12.ex01;

public class LinkedList<V> implements Cloneable {

    private Node header;



    /**

     * 空の連結リスト

     */

    public LinkedList() {

        header = null;

    }



    /**

     * 先頭のノードを作成して初期化

     * @param value

     */

    public LinkedList(V value) {

        add(value);

    }





    /**

     * リストの先頭の値を返す

     * @return value

     */

    public V getHeader() {

        if (header == null)

            return null;

        else

            return header.value;

    }



    /**

     * 最後尾のNodeを返す。

     * @return tailNode

     */

    private Node getTailNode() {

        if (header == null)

            return null;

        else {

            Node node = header;

            while(node.next != null)

                node = node.next;

            return node;

        }

    }



    /**

     * リストの最後尾の値を返す

     * @return value

     */

    public V getTail() {

        Node tailNode = getTailNode();

        if (tailNode == null)

            return null;

        else

            return tailNode.value;

    }







    /**

     * LinkedListのNodeの数を返す

     */

    public int size() {

        if (header == null)

            return 0;

        else {

            Node node = header;

            int size = 1;

            while(node.next != null) {

                size++;

                node = node.next;

            }

            return size;

        }

    }



    /**

     * LinkedListに要素を追加する

     */

    public void add(V value) {

        if (value == null)

            throw new IllegalArgumentException("nullだめ");

        else {

            Node tailNode = getTailNode();

            if (tailNode == null)

                header = new Node(value);

            else

                tailNode.next = new Node(value);

        }

    }



    /**

     * 指定された位置の値を返す。存在しなければnullを返す。

     * @param index

     * @return Node

     */

    protected Node getNode(int index) {

        if (header == null)

            return null;

        else if (index == 0)

            return header;

        else {

            Node node = header;

            int i = 0;

            while (node != null) {

                if (i == index)

                    return node;

                i++;

                node = node.next;

            }

            return null;

        }

    }



    /**

     * 指定されたインデックスのNodeの値を返す

     */

    public V get(int index) {

        Node node = getNode(index);

        if (node == null)

            return null;

        else

            return node.value;

    }



    /**

     * 指定されたインデックスのNodeに値を設定する

     * @param index

     * @param value

     * @return

     */

    public V set(int index, V value) {

        Node node = getNode(index);

        if (node == null)

            return null;

        else if (value == null)

            throw new IllegalArgumentException("nullはダメ");

        else {

            V oldValue = node.value;

            node.value = value;

            return oldValue;

        }

    }



    /**

     * LinkedListに引数の値が含まれているかどうか

     * @param value

     * @return

     */

    public boolean contains(V value) {

        if (header == null)

            return false;

        else {

            Node node = header;

            while (node != null) {

                if (node.value.equals(value))

                    return true;

                node = node.next;

            }

            return false;

        }

    }



    /**

     * すべてのノードを複製する

     */

    @SuppressWarnings("unchecked")

    public LinkedList<V> clone() {

        try {

            LinkedList<V> list = (LinkedList<V>) super.clone();

            if (list.header == null)

                return list;

            else {

                Node sourceNode = header;

                Node node = sourceNode.clone();

                list.header = node;

                while (sourceNode.next != null) {

                    node.next = sourceNode.next.clone();

                    node = node.next;

                    sourceNode = sourceNode.next;

                }

                return list;

            }

        } catch (CloneNotSupportedException e) {

            throw new InternalError(e.toString());

        }

    }



    @Override

    public String toString() {

        if (header == null)

            return "empty";

        else {

            String desc = "header: " + header + "\n";

            Node node = header.next;

            while (node != null) {

                desc += "-> " + node + "\n";

                node = node.next;

            }

            return desc;

        }

    }



    public Node find(V value) throws ObjectNotFoundException {

        if (header == null)

            throw new ObjectNotFoundException(value);

        else {

            Node node = header;

            do {

                if (value.equals(node.value))

                    return node;

                node = node.next;

            } while (node.next != null);

        }

        throw new ObjectNotFoundException(value);

    }



    class Node implements Cloneable {

        private V value;

        private Node next;



        /**

         * オブジェクト型のフィールドにセット

         * @param value

         */

        Node(V value) {

            this(value, null);

        }



        /**

         * オブジェクト型のフィールドと次のノードへの参照をセット

         * @param value

         * @param next

         */

        Node(V value, Node next) {

            this.value = value;

            this.next = next;

        }



        @Override

        public String toString() {

            return value.toString();

        }



        /**

         * ノードの複製を許可する

         */

        @SuppressWarnings("unchecked")

        public Node clone() {

            try {

                Node node =  (Node) super.clone();

                if (node.next != null)

                    node.next = null;

                return node;

            } catch (CloneNotSupportedException e) {

                throw new InternalError(e.toString());

            }

        }

    }

}