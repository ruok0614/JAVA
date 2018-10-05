package jpl.ch06.ex04;

public class Ex04 {
    enum TrafficLightColor{
        RED("RED"),
        BLUE("BLUE"),
        YELLOW("YELLOW");

        String name;

        TrafficLightColor(String name){
            this.name = name;
        }
        public String toString(){return name;}
    }

    public static void main(String[] args){
        System.out.println(TrafficLightColor.RED.toString());
        System.out.println(TrafficLightColor.BLUE.toString());
        System.out.println(TrafficLightColor.YELLOW.toString());

    }

}
