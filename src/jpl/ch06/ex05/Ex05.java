package jpl.ch06.ex05;

public class Ex05 {

    enum TrafficLightColor{
        RED("RED") {
            String getColor(){
                return this.color;
            }
        },
        BLUE("BLUE"){
            String getColor(){
                return this.color;
            }
        },
        YELLOW("YELLOW"){
            String getColor(){
                return this.color;
            }
        };

        String color;
        TrafficLightColor(String color){
            this.color = color;
        }
        public String toString(){return color;}

        abstract String getColor();
    }

    public static void main(String[] args){
        System.out.println(TrafficLightColor.RED.getColor());
        System.out.println(TrafficLightColor.BLUE.getColor());
        System.out.println(TrafficLightColor.YELLOW.getColor());

    }

}
