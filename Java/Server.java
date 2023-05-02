import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Server {

    public static void main(String[] args){
        try{
            ServerSocket server= new ServerSocket(10314);
            
            while (true) {
                // System.out.println("Waiting for connection...");
                Socket socket = server.accept();
                // System.out.println("Accepted connection from: " + socket.getInetAddress());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                RemoteMethod remoteMethod = (RemoteMethod) in.readObject(); // read and deserialize the RemoteMethod object
                System.out.println("Received: " + remoteMethod.getMethodName() + "," + Arrays.toString(remoteMethod.getArguments()) + "," + remoteMethod.getResult());
                
                String methodName = remoteMethod.getMethodName();
                Object[] arguments = remoteMethod.getArguments();

                Object results = null;
                if(methodName.equals("echo")){
                    results = echo((String) arguments[0]);
                }else if(methodName.equals("add")){
                    results = add((Integer) arguments[0], (Integer) arguments[1]);
                }else if(methodName.equals("divide")){
                    try{
                       results = divide((Integer) arguments[0], (Integer) arguments[1]); 
                    } catch(ArithmeticException e){
                        System.out.println(e);
                        results=e.getClass().getSimpleName();
                        // results=e; sends java.lang.ArithmeticException
                    }
                    //results = divide((Integer) arguments[0], (Integer) arguments[1]);
                }
                
                
                // Send the result back to the client
                RemoteMethod response=new RemoteMethod(methodName, arguments, results);
                System.out.println("Sending Response: " + response.getMethodName() + "," + Arrays.toString(response.getArguments()) + "," + response.getResult());
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); 
                out.writeObject(response);
                out.flush();  
                in.close();  
                socket.close(); 
            }

        }catch(Exception err){
            err.printStackTrace();
        }
    }

    // Do not modify any code below tihs line
    // --------------------------------------
    public static String echo(String message) { 
        return "You said " + message + "!";
    }
    public static int add(int lhs, int rhs) {
        return lhs + rhs;
    }
    public static int divide(int num, int denom) {
        if (denom == 0)
            throw new ArithmeticException();

        return num / denom;
    }
}