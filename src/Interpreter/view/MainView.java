package Interpreter.view;

import Interpreter.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;

public class MainView extends JFrame implements ConstructorObserver, MethodHolderObserver, FieldHolderObserver,ObjectHolderObserver {
    private int width = 300;
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

    public MainView(){
        init();
    }

    private void init(){
        setSize(width,height);
        // ユーザーがこのフレームの「クローズ」を開始したときに、デフォルトで実行される処理を設定します。
        setTitle("Interprete");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        context = new Context();
        mainPanel = new JPanel();
        context.getConstructorHolder().addObserver(this);
        context.getMethodHolder().addObserver(this);
        context.getFieldHolder().addObserver(this);
        context.getObjectHolder().addObserver(this);
        ConstructorList();
        objectList();
        methodList();
        fieldList();


    }

    public void ConstructorList(){
        classNameTextArea = new JTextField(20);
        classNameGetButton = new JButton("取得");



        classNameGetButton.addActionListener(e -> {
            if(e.getSource() == classNameGetButton){
                System.out.println(classNameTextArea.getText());
                context.getConstructorHolder().searchConstructor(classNameTextArea.getText());
            }
        });
        mainPanel.add(classNameTextArea);
        mainPanel.add(classNameGetButton);

        Container contentPane = getContentPane();
        contentPane.add(mainPanel, BorderLayout.CENTER);

        constructorModel = new DefaultListModel();
        constructorList = new JList(constructorModel);
        JScrollPane constructorPanel = new JScrollPane(constructorList);
        constructorList.setLayoutOrientation(JList.VERTICAL);
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

        mainPanel.add(constructorPanel,BorderLayout.CENTER);

    }

    public void InstancePanel(String argsText){
        JFrame instanceJframe = new JFrame("new Instance");
        instanceJframe.setSize(250,200);
        instanceJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                context.getConstructorHolder().newInstance(constructorList.getSelectedIndex(),argsTextArea.getText(),objNameArea.getText());
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

    public void methodList(){
        methodModel = new DefaultListModel();
        methodList = new JList(methodModel);
        JScrollPane ObjectPanel = new JScrollPane(methodList);
        methodList.setLayoutOrientation(JList.VERTICAL);
        JLabel label = new JLabel("メソッド一覧");
        mainPanel.add(label);
        mainPanel.add(ObjectPanel,BorderLayout.LINE_END);
    }

    public void fieldList(){
        fieldModel = new DefaultListModel();
        fieldList = new JList(fieldModel);
        JScrollPane fieldPanel = new JScrollPane(fieldList);
        fieldList.setLayoutOrientation(JList.VERTICAL);
        JLabel label = new JLabel("フィールド一覧");
        mainPanel.add(label);
        mainPanel.add(fieldPanel,BorderLayout.LINE_END);

        JTextField fieldTextArea = new JTextField(20);
        JButton fieldChangeButton = new JButton("変更");
        mainPanel.add(fieldTextArea);
        mainPanel.add(fieldChangeButton);
        fieldList.addMouseListener(new MouseAdapter() {
            // ダブルクリックで要素を取得
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 1) {
                    fieldTextArea.setText(context.getFieldHolder().getFieldValue(list.getSelectedIndex()).toString());
                }
            }
        });


    }

    private void showField(int selectedIndex) {

    }

    public void objectList(){
        objectModel = new DefaultListModel();
        objectList = new JList(objectModel);
        JScrollPane objectPanel = new JScrollPane(objectList);
        objectList.setLayoutOrientation(JList.VERTICAL);
        JLabel label = new JLabel("オブジェクト一覧");
        mainPanel.add(label);
        mainPanel.add(objectPanel,BorderLayout.LINE_END);
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

    public void showFieldList(List<Field> fieldlist){
        fieldModel.removeAllElements();
        for (Field f:fieldlist){
            if(f.getDeclaringClass() == Object.class)
                continue;
            String decl = f.toString();
            System.out.println(decl);
            fieldModel.addElement(decl);
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
}
