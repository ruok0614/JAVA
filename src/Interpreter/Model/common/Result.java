package Interpreter.model.common;

public class Result {
    private enum State{
        Success,
        Failure
    }
    private State state;
    private Object value;

    Result(State state,Object value){
        this.state = state;
        this.value = value;
    }

    public static Result createSuccess(Object value){
        return new Result(State.Success, value);
    }

    public static Result createFailure(Object value){
        return new Result(State.Failure,value);
    }

    public boolean isSuccess(){
        if(state == State.Success){
            return true;
        }
        return false;
    }

    public String toString(){
        return String.format("結果：%s \n 値：%s",state.name(), value.toString());
    }
}
