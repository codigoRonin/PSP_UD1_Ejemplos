import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Ejemplo7B{

  public static void main(String[] args) {
    try {
      File directorio = new File(".\\bin");
      ProcessBuilder proceseBuilder = new ProcessBuilder("java", "Ejemplo7A");
      proceseBuilder.directory(directorio);
      Process proceso = null;

      proceso = proceseBuilder.start();
      
      // Introducimos la información con el outputStream
      OutputStream ops = proceso.getOutputStream();
      ops.write("Jassi".getBytes());
      ops.flush();
      ops.close();

      int c;
      InputStream is = proceso.getInputStream();
      while ((c = is.read()) != -1) { // Imprimimos la salida caracter a caracter
        System.out.print((char) c);
      }

      int valorSalida = proceso.waitFor(); // Recojemos la salida de System.exit
      System.out.println("El valor de la salida: " + valorSalida);
      
      // Si se ha producido un error mostramos el mensaje de error
      if (valorSalida != 0) {
        InputStream errorStream = proceso.getErrorStream(); // Creamos un InpuStream para leer el error
        BufferedReader bre = new BufferedReader(
          new InputStreamReader(errorStream)
        );
        String lineaError;
        while ((lineaError = bre.readLine()) != null) System.out.println(
          "Error-> " + lineaError
        );
        errorStream.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}