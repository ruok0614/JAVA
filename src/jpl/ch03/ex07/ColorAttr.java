package jpl.ch03.ex07;

import javafx.stage.Screen;

public class ColorAttr extends Attr{
    private ScreenColor myColor;

    public ColorAttr(String name,Object value){
        super(name,value);
        decodeColor();
    }

    public ColorAttr(String name){
        this(name,"transparent");
    }

    public ColorAttr(String name, ScreenColor value){
        super(name,value.toString());
    }


    public Object setValue(Object newValue){
        Object retval = super.setValue(newValue);
        decodeColor();
        return retval;
    }

    public ScreenColor setVlue(ScreenColor newValue){
        super.setValue(newValue.toString());
        ScreenColor oldValue = myColor;
        myColor = newValue;
        return oldValue;
    }

    public ScreenColor getColor(){
        return myColor;
    }

    protected void decodeColor(){
        if(getValue() == null)
            myColor = null;
        else
            myColor = new ScreenColor(getValue());
    }

    @Override
    public int hashCode(){
        return getValue().hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (this.hashCode() == obj.hashCode()){
            System.out.println(1);
            return true;
        } else {
            System.out.println(0);
            return false;
        }
    }

    public static void main(String args[]){
        ColorAttr a = new ColorAttr("a");
        ColorAttr b = new ColorAttr("a");
        ColorAttr c = new ColorAttr("a","black");
        ColorAttr d = new ColorAttr("b","black");
        ColorAttr e = new ColorAttr("b");

        a.equals(b);
        a.equals(c);
        a.equals(d);
        a.equals(e);


    }
}
