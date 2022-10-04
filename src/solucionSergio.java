import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class solucionSergio {
    
       
    public static void main(String[] args) {
        File file = new File(".\\bin");
           
        ProcessBuilder processBuilder = new ProcessBuilder("java","Ejemplo7A");
        
        processBuilder.directory(file);
        
        Process proceso = null;
        
        try {
          
          proceso = processBuilder.start();

          OutputStream outputStream = proceso.getOutputStream(); 
          OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
          BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

          bufferedWriter.write("Sergio");
          bufferedWriter.close();
                    
          int caracterUnicode;

          InputStream inputStream = proceso.getInputStream();

          while ((caracterUnicode = inputStream.read()) != -1) { 
            System.out.print((char) caracterUnicode);
          }
          
          inputStream.close();
        
          InputStream errorStream = proceso.getErrorStream(); 
          BufferedReader br = new BufferedReader(
            new InputStreamReader(errorStream)
          );

          String linea;

          while ((linea = br.readLine()) != null) System.out.println(
            "Error-> " + linea
          );

          errorStream.close();

        } catch (IOException e) {
          e.printStackTrace();
        }
    
        try {
          int valorSalida = proceso.waitFor(); 
          System.out.println("El valor de la salida: " + valorSalida);
        
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
    }
}
