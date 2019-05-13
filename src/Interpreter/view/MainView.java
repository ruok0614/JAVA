package Interpreter.view;

import Interpreter.Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.*;
import java.util.List;
import Interpreter.Model.ErrorMessage;

public class MainView extends JFrame implements ConstructorObserver, MethodHolderObserver, FieldHolderObserver,ObjectHolderObserver {
    private int width = 500;
    private int height =650;
    private JPanel mainPanel;
    private JTextField classNameTextArea;
    private Context context;
    private JButton classNameGetButton;
    private DefaultListModel constructorModel;
    private JList constructorList;
    private DefaultListModel methodModel;
    private JList methodList;
    private DefaultListModel fieldModel;
    private JList fieldList;
    private DefaultListModel objectModel;
    private JList objectList;
    private DefaultTableModel tableModel;

    private final String ERROR = "ERROR";

    public MainView(){
        init();
    }

    /**
     * メインフレームのセット
     */
    private void init(){
        setSize(width,height);
        // ユーザーがこのフレームの「クローズ」を開始したときに、デフォルトで実行される処理を設定します。
        setTitle("Interprete");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        context = new Context();
        mainPanel = new JPanel();
        // mainPanel.setLayout(new GridLayout(3, 2));
        context.getConstructorHolder().addObserver(this);
        context.getMethodHolder().addObserver(this);
        context.getFieldHolder().addObserver(this);
        context.getObjectHolder().addObserver(this);
        ConstructorList();
        objectList();
        methodList();
        fieldList();


    }

    /**
     * クラス名入力バーと検索ボタン，コンストラクター一覧を表示するリストを追加する
     */
    public void ConstructorList(){
        JPanel inputClassPanel = new JPanel();
        GridBagLayout inputClassGrid = new GridBagLayout ();
        inputClassPanel.setLayout(inputClassGrid);

        classNameTextArea = new JTextField(20);
        classNameGetButton = new JButton("取得");

        classNameTextArea.setText("java.lang.String");

        classNameGetButton.addActionListener(e -> {
            if(e.getSource() == classNameGetButton){
                try {
                    context.getConstructorHolder().searchConstructor(classNameTextArea.getText());
                } catch (ClassNotFoundException e1) {
                    JOptionPane.showMessageDialog(this, ErrorMessage.NOT_CONSTRUCTOR, ERROR,
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        inputClassPanel = addComponent(inputClassPanel,inputClassGrid,classNameTextArea,0,0,1,1);
        inputClassPanel = addComponent(inputClassPanel,inputClassGrid,classNameGetButton,1,0,1,1);


        Container contentPane = getContentPane();
        contentPane.add(mainPanel, BorderLayout.CENTER);

        JPanel constructorPanel = new JPanel();
        constructorPanel.setLayout(new BoxLayout(constructorPanel, BoxLayout.Y_AXIS));
        constructorModel = new DefaultListModel();
        constructorList = new JList(constructorModel);
        JScrollPane constructorPane = new JScrollPane(constructorList);
        constructorList.setLayoutOrientation(JList.VERTICAL);
        // ダブルクリックで変数を生成できるようにする
        constructorList.addMouseListener(new MouseAdapter() {
            // ダブルクリックで要素を取得
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    String selectedValue = (String)list.getSelectedValue();
                    //context.getConstructorHolder().createObject(list.getSelectedIndex());
                    InstancePanel(selectedValue);
                    System.out.println(selectedValue);
                    if (selectedValue != null) {
                        //context.getConstructorHolder().createObject(list.getSelectedIndex());
                    }
                }
            }
        });
        constructorPanel.add(inputClassPanel);
        constructorPanel.add(constructorPane,BorderLayout.CENTER);
        mainPanel.add(constructorPanel);

    }

    /**
     * インスタンス生成のため引数と変数名を表示させるウィンドウを生成する
     * @param argsText フィールド名
     */
    public void InstancePanel(String argsText){
        JFrame instanceJframe = new JFrame("new Instance");
        instanceJframe.setSize(250,200);
        instanceJframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel instancePanel = new JPanel();
        Container contentPane = instanceJframe.getContentPane();
        JLabel argsLabel = new JLabel("引数");
        HintTextField argsTextArea = new HintTextField(20);
        argsTextArea.setHint(argsText);
        JLabel nameLabel = new JLabel("名前");
        JTextField objNameArea = new JTextField(20);
        JButton generateButton = new JButton("生成");
        generateButton.addActionListener(e -> {
            if(e.getSource() == generateButton){
                try {
                    context.getConstructorHolder().newInstance(constructorList.getSelectedIndex(),transArgs(argsTextArea.getText()),objNameArea.getText());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this,ErrorMessage.ARGMENT_ILLEGAL,ERROR,JOptionPane.ERROR_MESSAGE);
                }
                instanceJframe.dispose();
            }
        });
        instancePanel.add(argsLabel);
        instancePanel.add(argsTextArea);
        instancePanel.add(nameLabel);
        instancePanel.add(objNameArea);
        instancePanel.add(generateButton);
        contentPane.add(instancePanel,BorderLayout.CENTER);
        instanceJframe.setVisible(true);
    }

    /**
     * メソッド一覧を表示するリスト
     */
    public void methodList(){
        JPanel methodPanel = new JPanel();
        methodPanel.setLayout(new BoxLayout(methodPanel, BoxLayout.Y_AXIS));

        methodModel = new DefaultListModel();
        methodList = new JList(methodModel);
        JScrollPane ObjectPanel = new JScrollPane(methodList);
        methodList.setLayoutOrientation(JList.VERTICAL);
        JLabel label = new JLabel("メソッド一覧");
        methodPanel.add(label);
        methodPanel.add(ObjectPanel,BorderLayout.LINE_END);
        mainPanel.add(methodPanel);
    }

//    public void fieldList(){
//        JPanel fieldPanel = new JPanel();
//        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
//        fieldModel = new DefaultListModel();
//        fieldList = new JList(fieldModel);
//        JScrollPane fieldPane = new JScrollPane(fieldList);
//        fieldList.setLayoutOrientation(JList.VERTICAL);
//        JLabel label = new JLabel("フィールド一覧");
//        fieldPanel.add(label);
//        fieldPanel.add(fieldPane,BorderLayout.LINE_END);
//        mainPanel.add(fieldPanel);
//
//        JPanel changeFieldPanel = new JPanel();
//        GridBagLayout changeFieldGrid = new GridBagLayout ();
//        changeFieldPanel.setLayout(changeFieldGrid);
//
//        JTextField fieldTextArea = new JTextField(20);
//        JButton fieldChangeButton = new JButton("変更");
//        changeFieldPanel = addComponent(changeFieldPanel,changeFieldGrid,fieldTextArea,0,0,1,1);
//        changeFieldPanel = addComponent(changeFieldPanel,changeFieldGrid,fieldChangeButton,1,0,1,1);
//        mainPanel.add(changeFieldPanel);
//
//        fieldList.addMouseListener(new MouseAdapter() {
//            // ダブルクリックで要素を取得
//            public void mouseClicked(MouseEvent evt) {
//                JList list = (JList)evt.getSource();
//                if (evt.getClickCount() == 1) {
//                    fieldTextArea.setText(context.getFieldHolder().getFieldValue(list.getSelectedIndex()).toString());
//                }
//            }
//        });
//    }

    /**
     * フィールドリストを表形式で表示する
     */
    public void fieldList() {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("フィールド一覧");
        fieldPanel.add(label);

        String[] title = {"名前","値"};
        tableModel = new DefaultTableModel(title,0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0) {
                    return false;
                }
                else{
                    return true;
                }
            }
        };
        //①タイトル行だけのテーブルを作成
        JTable table = new JTable(tableModel);
        javax.swing.JScrollPane fieldPane = new JScrollPane(table);


        fieldPanel.add(fieldPane);
        mainPanel.add(fieldPanel);
    }

    private void showField(int selectedIndex) {

    }

    /**
     * このインタプリタが保持するフィールド一覧を表示する
     */
    public void objectList(){
        JPanel objectPanel = new JPanel();
        objectPanel.setLayout(new BoxLayout(objectPanel, BoxLayout.Y_AXIS));
        objectModel = new DefaultListModel();
        objectList = new JList(objectModel);
        JScrollPane objectPane = new JScrollPane(objectList);
        objectList.setLayoutOrientation(JList.VERTICAL);
        JLabel label = new JLabel("オブジェクト一覧");
        objectPanel.add(label);
        objectPanel.add(objectPane,BorderLayout.LINE_END);
        mainPanel.add(objectPanel);
        objectList.addMouseListener(new MouseAdapter() {
            // ダブルクリックで要素を取得
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    int selectedindex = list.getSelectedIndex();
                    context.getObjectHolder().showFieldAndMethod(selectedindex);
                }
            }
        });

    }

    @Override
    public void showConstructor(Constructor[] constructorArray){
        constructorModel.removeAllElements();
        for (Member m:constructorArray){
            if(m.getDeclaringClass() == Object.class)
                continue;
            String decl = m.toString();
            System.out.println(decl);
            constructorModel.addElement(decl);

        }
        constructorList.ensureIndexIsVisible(constructorModel.getSize() + 1);
    }

    @Override
    public void showSetFieldProperty(Constructor constructor){

    }

    public void showMethodList(List<Method> methodlist){
        methodModel.removeAllElements();
        for (Method m:methodlist){
            if(m.getDeclaringClass() == Object.class)
                continue;
            String decl = m.toString();
            System.out.println(decl);
            methodModel.addElement(decl);

        }
        methodList.ensureIndexIsVisible(methodModel.getSize() + 1);
    }

//    public void showFieldList(List<Field> fieldlist){
//        fieldModel.removeAllElements();
//        for (Field f:fieldlist){
//            if(f.getDeclaringClass() == Object.class)
//                continue;
//            String decl = f.toString();
//            System.out.println(decl);
//            fieldModel.addElement(decl);
//        }
//        fieldList.ensureIndexIsVisible(fieldModel.getSize() + 1);
//    }
    public void showFieldList(List<Field> fieldlist){
        tableModel.setRowCount(0);
        int r = 0;
        for (Field f:fieldlist){
            String s[] = new String[2];
            if(f.getDeclaringClass() == Object.class)
                continue;
            String decl = f.toString();
            System.out.println(decl);
            s[0] = decl;
            s[1] = context.getFieldHolder().getFieldValue(f).toString();

            tableModel.addRow(s);
            r++;
        }
        fieldList.ensureIndexIsVisible(fieldModel.getSize() + 1);
    }
    public void showObjectList(List<OBJ> obj){
        objectModel.removeAllElements();
        for (OBJ o:obj){
            String decl = o.getName();
            System.out.println(decl);
            objectModel.addElement(decl);

        }
    }

    private JPanel addComponent(JPanel inP,GridBagLayout gbl, Component c, int x, int y, int w, int h) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gbl.setConstraints(c, gbc);
        inP.add(c);
        return inP;
    }

    private Object transArgs(String args){
        // ''で囲まている場合はcharで返す
        if(checkType(args,'\'')){
            if(args.length() == 3)
                return fetch(args).charAt(0);
            else{
                JOptionPane.showMessageDialog(this,ErrorMessage.ARGMENT_ILLEGAL,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        // ""の場合は文字列で返す
        if(checkType(args,'\"')){
            return fetch(args);
        }
        // 数字に変換できればintできなければ文字列で返す
        else{
            try {
                return Integer.parseInt(args);
            }catch(Error e){
                return args;
            }
        }
    }

    private boolean checkType(String args, char target){
        int len = args.length() - 1;
        if(args.charAt(0) == target){
            if(args.charAt(len) == target){
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * 文字列の最初の文字と最後の文字を削除したものを返します．
     * @param str 最初と最後以外を取り出したい文字列
     * @return 最初と最後以外を取り出した文字列
     */
    private String fetch(String str){
        int len = str.length();
        return str.substring(1,len);
    }
}
