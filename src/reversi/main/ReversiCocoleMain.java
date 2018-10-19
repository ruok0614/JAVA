package reversi.main;

import reversi.input.ConsoleInputHandler;
import reversi.system.Flow;

public class ReversiCocoleMain {
    public static void main(String args[]){
        Flow flow = new Flow(4,4, new ConsoleInputHandler());
        flow.start();
    }
}
