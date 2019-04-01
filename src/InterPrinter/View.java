package InterPrinter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Member;

public class View extends JFrame implements ActionListener, IConstructorObserver {
    private JPanel newInstancePanel;
    private JTextField classNameTextArea;
    private JList constructorlist;
    private Controller controller;
    private DefaultListModel constructorModel;

    JButton classNameGetButton;
    View(){
        init();
        placeNewInstance();
        controller = new Controller(this);
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
        constructorlist.addMouseListener(new MouseAdapter() {
            // ダブルクリックで要素を取得
            public void mouseClicked(MouseEvent evt) {
                JList list = (JList)evt.getSource();
                if (evt.getClickCount() == 2) {
                    String selectedValue = (String)list.getSelectedValue();
                    System.out.println(selectedValue);
                    if (selectedValue != null) {
                        controller.selectedValue(selectedValue);
                    }
                }
            }
        });

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
