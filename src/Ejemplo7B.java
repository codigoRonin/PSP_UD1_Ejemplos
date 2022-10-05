import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Ejemplo7B {
    public static void main(String[] args) {

        ProcessBuilder pb = new ProcessBuilder("java", "Ejemplo7A");
        File directorio = new File(".\\bin");
        pb.directory(directorio);
        Process proceso;

        try {
            proceso = pb.start();

            // Introducimos los datos
            OutputStream writeData = proceso.getOutputStream();
            writeData.write("Cora".getBytes());
            writeData.flush();
            writeData.close();
            // captura de salida
            InputStream readData = proceso.getInputStream();
            int resultado;

            while ((resultado = readData.read()) != -1) {
                System.out.print((char) resultado);
            }
            readData.close();

            // mostrar resultados del proceso
            try {
                int valorSalida = proceso.waitFor();

                // Mostrar qué error a podido suceder
                if (valorSalida != 0) {
                    InputStream errorSalida = proceso.getErrorStream();

                    BufferedReader bufferError = new BufferedReader(new InputStreamReader(errorSalida));
                    String linea;
                    while ((linea = bufferError.readLine()) != null) {
                        System.out.println("Error: " + linea);

                    }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
