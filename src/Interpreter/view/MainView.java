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

public class MainView extends JFrame implements ConstructorObserver, MethodHolderObserver, FieldHolderObserver {
    private int width = 300;
    private int height =250;
    private JPanel mainPanel;
    private JTextField classNameTextArea;
    private Context context;
    private JButton classNameGetButton;
    private DefaultListModel constructorModel;
    private JList constructorList;
    private DefaultListModel objectModel;
    private JList objectList;
    private DefaultListModel fieldModel;
    private JList fieldList;

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
        ConstructorList();
        objectList();
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
        instanceJframe.setSize(250,130);
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
                context.getConstructorHolder().newInstance(constructorList.getSelectedIndex(),argsTextArea.getText());
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

    public void objectList(){
        objectModel = new DefaultListModel();
        objectList = new JList(objectModel);
        JScrollPane ObjectPanel = new JScrollPane(objectList);
        objectList.setLayoutOrientation(JList.VERTICAL);
        mainPanel.add(ObjectPanel,BorderLayout.LINE_END);
    }

    public void fieldList(){
        fieldModel = new DefaultListModel();
        fieldList = new JList(fieldModel);
        JScrollPane fieldPanel = new JScrollPane(fieldList);
        fieldList.setLayoutOrientation(JList.VERTICAL);
        mainPanel.add(fieldPanel,BorderLayout.LINE_END);
    }

    @Override
    public void showConstructor(Constructor[] constructorArray){
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

    public void showMethodList(Method[] methodlist){
        for (Method m:methodlist){
            if(m.getDeclaringClass() == Object.class)
                continue;
            String decl = m.toString();
            System.out.println(decl);
            objectModel.addElement(decl);

        }
        objectList.ensureIndexIsVisible(objectModel.getSize() + 1);
    }

    public void showFieldList(Field[] fieldlist){
        for (Field f:fieldlist){
            if(f.getDeclaringClass() == Object.class)
                continue;
            String decl = f.toString();
            System.out.println(decl);
            fieldModel.addElement(decl);

        }
        fieldList.ensureIndexIsVisible(fieldModel.getSize() + 1);
    }
}
