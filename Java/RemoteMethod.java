import java.io.*;
import java.net.*;

public class RemoteMethod implements Serializable{
    private String methodName;
    private Object[] args;
    private Object result;

    public RemoteMethod(String methodName, Object[] args, Object result){
        this.methodName=methodName;
        this.args=args;
        this.result=result;
    }

    public String getMethodName(){
        return methodName;
    }

    public Object[] getArguments(){
        return args;
    }

    public Object getResult(){
        return result;
    }

}