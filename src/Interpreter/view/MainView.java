package Interpreter.view;

import Interpreter.model.common.Result;
import Interpreter.model.common.StringExporter;
import Interpreter.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import Interpreter.model.ErrorMessage;

public class MainView extends JFrame implements ArrayHolderObserver,ConstructorObserver, MethodHolderObserver, FieldHolderObserver,ObjectHolderObserver {
    private int width = 900;
    private int height =800;
    private JPanel mainPanel;
    private JTextField classNameTextArea;
    private Interpreter.model.Context context;
    private JButton classNameGetButton;
    private DefaultListModel constructorModel;
    private JList constructorList;
    private DefaultListModel methodModel;
    private JList methodList;
    private DefaultListModel fieldModel;
    private JList fieldList;
    private DefaultListModel objectModel;
    private JList objectList;
    private JPanel objectPanel;
    private DefaultTableModel tableModel;
    private JList arrayList;
    private DefaultListModel arrayModel;
    private JPanel consolePanel;
    private JTextArea textArea;

    private final String ERROR = "ERROR";

    private int activeObjectIndex;

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
        context.getArrayHolder().addObserver(this);

        ConstructorList();
        objectList();
        arrayList();
        methodList();
        outPutConsole();
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
        JButton newArrayButton = new JButton("配列生成");

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
        inputClassPanel = addComponent(inputClassPanel,inputClassGrid,newArrayButton,2,0,1,1);


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
        newArrayButton.addActionListener(e -> {
            if(e.getSource() == newArrayButton){
                newArrayPanel(classNameTextArea.getText());
            }
        });


        constructorPanel.add(inputClassPanel);
        constructorPanel.add(constructorPane,BorderLayout.CENTER);
        mainPanel.add(constructorPanel);

    }

    /**
     * このインタプリタが保持するフィールド一覧を表示する
     */
    public void objectList(){
        objectPanel = new JPanel();
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
                    activeObjectIndex = list.getSelectedIndex();
                    if (!context.getObjectHolder().tryShowArray(activeObjectIndex)) {
                        arrayModel.removeAllElements();
                        context.getObjectHolder().showFieldAndMethod(activeObjectIndex);
                    }
                }
            }
        });

    }

    /**
     * このインタプリタが保持するフィールド一覧を表示する
     */
    public void arrayList(){
        JPanel  arrayPanel = new JPanel();
        arrayPanel.setLayout(new BoxLayout(arrayPanel, BoxLayout.Y_AXIS));
        arrayModel = new DefaultListModel();
        arrayList = new JList(arrayModel);
        JScrollPane objectPane = new JScrollPane(arrayList);
        arrayList.setLayoutOrientation(JList.VERTICAL);
        JLabel label = new JLabel("配列要素一覧");
        arrayPanel.add(label);
        arrayPanel.add(objectPane,BorderLayout.LINE_END);

        JPanel ObjAndArrayPanel = new JPanel();
        GridBagLayout inputClassGrid = new GridBagLayout ();
        ObjAndArrayPanel.setLayout(inputClassGrid);
        ObjAndArrayPanel = addComponent(ObjAndArrayPanel,inputClassGrid,objectPanel,0,0,1,1);
        ObjAndArrayPanel = addComponent(ObjAndArrayPanel,inputClassGrid,arrayPanel,1,0,1,1);

        mainPanel.add(ObjAndArrayPanel);
        arrayList.addMouseListener(new MouseAdapter() {
            // ダブルクリックで要素を取得
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 1) {
                    int selectedIndex = list.getSelectedIndex();
                        context.getObjectHolder().showArrayFieldAndMethod(activeObjectIndex,selectedIndex);
                }
                if (evt.getClickCount() == 2) {
                    int selectedIndex = list.getSelectedIndex();
                    ArrayChangeFieldPanel(selectedIndex);
                }
            }
        });

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

        methodList.addMouseListener(new MouseAdapter() {
            // ダブルクリックで要素を取得
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    //fieldTextArea.setText(context.getFieldHolder().getFieldValue(list.getSelectedIndex()).toString());
                    MethodPanel(list.getSelectedIndex(), list.getSelectedValue().toString());
                }
            }
        });
    }

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

        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                int fieldIndex = table.getSelectedRow();
                String value = table.getValueAt(table.getSelectedRow(),1).toString();
                try {
                    Result result = context.getObjectHolder().setField(activeObjectIndex,fieldIndex, searchAndTransType(value));
                    showInvokeResult(result);
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
        };
        table.getActionMap().put("MY_CUSTOM_ACTION", action);
        table.getInputMap().put(
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                "MY_CUSTOM_ACTION");
    }

    /**
     * コンソール表示
     */
    public void outPutConsole(){
        consolePanel = new JPanel();
        JLabel consoleLabel = new JLabel("出力");
        consolePanel.setLayout(new BoxLayout(consolePanel, BoxLayout.Y_AXIS));
        textArea = new JTextArea(3,15);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        GridBagLayout inputClassGrid = new GridBagLayout ();
        consolePanel.setLayout(inputClassGrid);
        consolePanel = addComponent(consolePanel,inputClassGrid,consoleLabel,0,0,1,1);
        consolePanel = addComponent(consolePanel,inputClassGrid,textArea,0,1,1,1);

        mainPanel.add(consolePanel);
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
                    context.getConstructorHolder().newInstance(constructorList.getSelectedIndex(), searchAndTransTypes(argsTextArea.getText()),objNameArea.getText());
                } catch (IllegalArgumentException e1) {
                    JOptionPane.showMessageDialog(this,ErrorMessage.IllegalArgumentException,ERROR,JOptionPane.ERROR_MESSAGE);
                }catch (InstantiationException e2){
                    JOptionPane.showMessageDialog(this,ErrorMessage.ARGMENT_ILLEGAL,ERROR,JOptionPane.ERROR_MESSAGE);

                }catch (InvocationTargetException e3){
                    JOptionPane.showMessageDialog(this,ErrorMessage.ARGMENT_ILLEGAL,ERROR,JOptionPane.ERROR_MESSAGE);

                }catch (ExceptionInInitializerError e4){
                    JOptionPane.showMessageDialog(this,ErrorMessage.ExceptionInInitializerError,ERROR,JOptionPane.ERROR_MESSAGE);

                }catch (IllegalAccessException e5){
                    JOptionPane.showMessageDialog(this,ErrorMessage.IllegalArgumentException,ERROR,JOptionPane.ERROR_MESSAGE);

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

    public void newArrayPanel(String className){
        JFrame instanceJframe = new JFrame("new Instance");
        instanceJframe.setSize(250,200);
        instanceJframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel instancePanel = new JPanel();
        Container contentPane = instanceJframe.getContentPane();
        JLabel argsLabel = new JLabel("配列長さ     ");
        JSpinner arraySpinner = new JSpinner();
        arraySpinner.setValue(1);
        JLabel nameLabel = new JLabel("名前");
        JTextField objNameArea = new JTextField(20);
        JButton generateButton = new JButton("生成");
        generateButton.addActionListener(e -> {
            if(e.getSource() == generateButton){
                if((int)arraySpinner.getValue() < 1){
                    JOptionPane.showMessageDialog(this,ErrorMessage.ARRAY_LENGTH_ILLEGAL,ERROR,JOptionPane.ERROR_MESSAGE);
                }

                try {
                    context.getObjectHolder().addArray(className,(int)arraySpinner.getValue(),objNameArea.getText());
                } catch (ClassNotFoundException e1){
                    JOptionPane.showMessageDialog(this,ErrorMessage.ClassNotFoundException,ERROR,JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception ee) {
                    JOptionPane.showMessageDialog(this,ErrorMessage.ARGMENT_ILLEGAL,ERROR,JOptionPane.ERROR_MESSAGE);
                }
                instanceJframe.dispose();
            }
        });

        JPanel numConfigPanel = new JPanel();
        GridBagLayout inputClassGrid = new GridBagLayout ();
        numConfigPanel.setLayout(inputClassGrid);
        numConfigPanel = addComponent(numConfigPanel,inputClassGrid,argsLabel,0,0,1,1);
        numConfigPanel = addComponent(numConfigPanel,inputClassGrid,arraySpinner,2,0,1,1);

        JPanel namePanel = new JPanel();
        namePanel.setLayout(inputClassGrid);
        namePanel = addComponent(namePanel,inputClassGrid,nameLabel,0,0,1,1);
        namePanel = addComponent(namePanel,inputClassGrid,objNameArea,1,0,1,1);

        instancePanel.add(numConfigPanel);
        instancePanel.add(namePanel);
        instancePanel.add(generateButton);
        contentPane.add(instancePanel,BorderLayout.CENTER);
        instanceJframe.setVisible(true);
    }

    public void ArrayChangeFieldPanel(int selectIndex){
        JFrame arrayFieldFrame = new JFrame("Change Array Field");
        arrayFieldFrame.setSize(250,200);
        arrayFieldFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
        JPanel arrayFieldPanel = new JPanel();
        Container contentPane = arrayFieldFrame.getContentPane();
        JLabel argsLabel = new JLabel("値");
        HintTextField fieldArea = new HintTextField(20);
        JButton generateButton = new JButton("変更");
        generateButton.addActionListener(e -> {
            if(e.getSource() == generateButton){
                try {
                    Result result = context.getObjectHolder().setArrayValue(selectIndex,activeObjectIndex,StringExporter.toClassType(fieldArea.getText()));
                    showInvokeResult(result);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this,ErrorMessage.ARGMENT_ILLEGAL,ERROR,JOptionPane.ERROR_MESSAGE);
                }
                arrayFieldFrame.dispose();
            }
        });
        arrayFieldPanel.add(argsLabel);
        arrayFieldPanel.add(fieldArea);
        arrayFieldPanel.add(generateButton);
        contentPane.add(arrayFieldPanel,BorderLayout.CENTER);
        arrayFieldFrame.setVisible(true);
    }

    public void MethodPanel(int selectIndex ,String argsText){
        JFrame methodJframe = new JFrame("invoke method");
        methodJframe.setSize(250,200);
        methodJframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel methodPanel = new JPanel();
        Container contentPane = methodJframe.getContentPane();
        JLabel argsLabel = new JLabel("引数");
        HintTextField argsTextArea = new HintTextField(20);
        argsTextArea.setHint(argsText);
        JButton generateButton = new JButton("実行");
        generateButton.addActionListener(e -> {
            if(e.getSource() == generateButton){
                try {
                    context.getObjectHolder().invoke(selectIndex,activeObjectIndex, searchAndTransTypes(argsTextArea.getText()));
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this,ErrorMessage.ARGMENT_ILLEGAL,ERROR,JOptionPane.ERROR_MESSAGE);
                }
                methodJframe.dispose();
            }
        });
        methodPanel.add(argsLabel);
        methodPanel.add(argsTextArea);
        methodPanel.add(generateButton);
        contentPane.add(methodPanel,BorderLayout.CENTER);
        methodJframe.setVisible(true);
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

    @Override
    public void showMethodList(List<Method> methodlist){
        methodModel.removeAllElements();
        if(methodlist == null){
            return;
        }
        for (Method m:methodlist){
            if(m.getDeclaringClass() == Object.class)
                continue;
            String methodName = m.toGenericString();
            methodModel.addElement(methodName);

        }
        methodList.ensureIndexIsVisible(methodModel.getSize() + 1);
    }

    @Override
    public void showFieldList(List<Field> fieldlist){
        tableModel.setRowCount(0);
        if(fieldlist == null){
            return;
        }

        int r = 0;
        for (Field f:fieldlist){
            String s[] = new String[2];
            if(f.getDeclaringClass() == Object.class)
                continue;
            String fieldName = f.toString();
            s[0] = fieldName;
            s[1] = context.getFieldHolder().getFieldValue(f).toString();

            tableModel.addRow(s);
            r++;
        }
        //fieldList.ensureIndexIsVisible(fieldlist.size());
    }

    @Override
    public void showObjectList(List<OBJ> obj){
        objectModel.removeAllElements();
        for (OBJ o:obj){
            String name  = o.getName();
            String className = o.getValue().getClass().getSimpleName();
            objectModel.addElement(String.format("%s  %s", className,name));
        }
    }

    @Override
    public void showInvokeResult(Object returnValue) {
       textArea.setText(returnValue.toString());
    }
    @Override
    public void showArray(Object[] obj){
        arrayModel.removeAllElements();
        for (Object o:obj){
            String decl;
            try {
                decl = o.toString();
            }catch (NullPointerException e){
                decl = "null";
            }
            arrayModel.addElement(decl);
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

    private Object[] searchAndTransTypes(String args){
        // ""の場合はnullを返す
        if(args.length() == 0){
            return new Object[0] ;
        }
        if(args.equals("null")){
            return null;
        }
        String pattern = "^.+\\[[1-9]+\\]$";

        String[] splitArgs = args.split(",");
        List<Object> objArgs = new ArrayList<>();
        for(String s : splitArgs) {
            Pattern p = Pattern.compile(pattern);
            if(p.matcher(s).find()){
                String str[] = s.split("\\[|\\]",0);
                String name = str[0];
                int index = Integer.parseInt(str[1]);
                objArgs.add(context.getArrayHolder().searchArrayValue(index,name));

            }
            Object obj = context.getObjectHolder().searchObj(s);
            if(obj == null){
                objArgs.add(StringExporter.toClassType(s));
                continue;
            }
            objArgs.add(obj);
        }
        return objArgs.toArray();
    }

    private Object searchAndTransType(String args){
        // ""の場合はnullを返す
        if(args.length() == 0){
            return new Object[0] ;
        }
        if(args.equals("null")){
            return null;
        }
        String pattern = "^.+\\[[1-9]+\\]$";

        Pattern p = Pattern.compile(pattern);
        if(p.matcher(args).find()){
            String str[] = args.split("\\[|\\]",0);
            String name = str[0];
            int index = Integer.parseInt(str[1]);
            return context.getArrayHolder().searchArrayValue(index,name);

        }
        Object obj = context.getObjectHolder().searchObj(args);
        if(obj == null){
            return (StringExporter.toClassType(args));
        }
        return obj;
    }


}
