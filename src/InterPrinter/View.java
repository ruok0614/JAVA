package InterPrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Member;

public class View extends JFrame implements ActionListener,IObserver {
    private JPanel newInstancePanel;
    private JTextField classNameTextArea;
    private JList constructorlist;
    private Controller controller;
    private DefaultListModel constructorModel;

    JButton classNameGetButton;
    View(){
        init();
        placeNewInstance();
        controller = new Controller();
        controller.addObserver(this);
    }


    /**
     *　サイズ，タイトルをセットします．
     */
    private void init(){
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("InterPrinter");
        //位置を適切にしてくれるやつ
        setLocationByPlatform(true);
    }

    /**
     * クラス名を指定して表示するために必要なオブジェクトを配置します
     */
    private void placeNewInstance(){
        newInstancePanel = new JPanel();

        classNameTextArea = new JTextField(20);
        classNameGetButton = new JButton("取得");
        classNameGetButton.addActionListener(this);
        newInstancePanel.add(classNameTextArea);
        newInstancePanel.add(classNameGetButton);


        Container contentPane = getContentPane();
        contentPane.add(newInstancePanel, BorderLayout.CENTER);

        constructorModel = new DefaultListModel();
        constructorlist = new JList(constructorModel);
        JScrollPane constructorPanel = new JScrollPane(constructorlist);
        constructorlist.setLayoutOrientation(JList.VERTICAL);

        newInstancePanel.add(constructorPanel,BorderLayout.CENTER);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == classNameGetButton){
            controller.pushedclassNameGetButton(classNameTextArea.getText());
        }

    }

    @Override
    public void showConstructor(Member[] constructorArray){
        for (Member m:constructorArray){
            if(m.getDeclaringClass() == Object.class)
                continue;
            String decl = m.toString();
            System.out.println(decl);
            constructorModel.addElement(decl);



        }
        constructorlist.ensureIndexIsVisible(constructorModel.getSize() + 1);

    }
}
