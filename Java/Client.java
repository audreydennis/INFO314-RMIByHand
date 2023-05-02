import java.net.*;
import java.io.*;
import java.util.Arrays;

public class Client {

    /**
     * This method name and parameters must remain as-is
     */
    public static int add(int lhs, int rhs) {
        int result = -1;
        try{
            Socket socket=new Socket("localhost", PORT);
            RemoteMethod add=new RemoteMethod("add", new Object[]{lhs, rhs}, null);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); 
            out.writeObject(add);
            out.flush(); 
            try{
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                RemoteMethod response = (RemoteMethod) in.readObject();
                // System.out.println("Received request: " + response.getMethodName() + "," + Arrays.toString(response.getArguments()));
                System.out.println("Result: " + response.getResult());
                result = (int) response.getResult();
            }catch(IOException e){
                e.printStackTrace();
            }
            socket.close(); 
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("Can't connect to server");
        }
        return result;
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static int divide(int num, int denom) {
        int result = -1;
        try{
            Socket socket=new Socket("localhost", PORT);
            RemoteMethod divide=new RemoteMethod("divide", new Object[]{num, denom}, null);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); 
            out.writeObject(divide);
            out.flush(); 
            try{
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                RemoteMethod response = (RemoteMethod) in.readObject();
                System.out.println("Result: " + response.getResult());
                Object testing = response.getResult(); //had this typecast
                if (testing instanceof Integer) {
                    result = (int) testing;
                } else {
                    throw new ArithmeticException();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
            socket.close(); 
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("Can't connect to server");
        }
        return result;
    }
    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) {
        String result = "Not";
        try{
            Socket socket=new Socket("localhost", PORT);
            RemoteMethod echo=new RemoteMethod("echo", new Object[]{message}, null);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream()); 
                out.writeObject(echo);
                out.flush(); 
            try{
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                RemoteMethod response = (RemoteMethod) in.readObject();
                // System.out.println("Received request: " + response.getMethodName() + "," + Arrays.toString(response.getArguments()));
                System.out.println("Result: " + response.getResult());
                result = response.getResult().toString();
            }catch(IOException e){
                e.printStackTrace();
            }
            socket.close(); 

        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch (IOException e) {
        System.out.println("Can't connect to server");
        }
        return result;
    }

    // Do not modify any code below this line
    // --------------------------------------
    String server = "localhost";
    public static final int PORT = 10314;

    public static void main(String... args) {
        // All of the code below this line must be uncommented
        // to be successfully graded.
        System.out.print("Testing... ");

        if (add(2, 4) == 6)
            System.out.print(".");
        else
            System.out.print("X"); 
        try {
            divide(1, 0);
            System.out.print("X");
        }
        catch (ArithmeticException x) {
            System.out.print(".");
        } 
        if (echo("Hello").equals("You said Hello!"))
            System.out.print(".");
        else
            System.out.print("X");
        
        System.out.println(" Finished");
    }
}