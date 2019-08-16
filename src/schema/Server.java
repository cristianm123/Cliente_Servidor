package schema;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	
	/**
	 * Puerto 
	 */
	public static final int PORT = 8000;
	/**
	 * Socket del servidor
	 */
	private static  ServerSocket serverSocket;
	/**
	 * El servidor dispone de un socket para un computador
	 */
	private static Socket socket1, socket2;
	

	
	
	public static void main(String[] args) {
		
		DataInputStream in;
		DataOutputStream out;
		
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("::Servidor escuchando a los posibles clientes::");
			
			while(true) {
				
				socket1 = serverSocket.accept();
				
				out = new DataOutputStream(socket1.getOutputStream());
				
				int key = (int)(Math.random()*20);
				out.writeUTF(Integer.toHexString(key));
				in = new DataInputStream(socket1.getInputStream());
				String mensajeObtenidoCliente = in.readUTF();
				String keyhex = in.readUTF();
				socket2 = serverSocket.accept();
				System.out.println("Los clientes se han conectado!");
				
				out = new DataOutputStream(socket2.getOutputStream());
				
				System.out.println("El mensaje enviado por el cliente 1 fue : " + mensajeObtenidoCliente);
				
				//String respuestaServer = metodoServicioServer(mensajeObtenidoCliente, key);
				
				out.writeUTF(mensajeObtenidoCliente);
				out.writeUTF(keyhex);
				socket1.close();
				socket2.close();
				System.out.println("::El cliente fue desconectado del server::");
			
			}
			
			
			
			
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}



	/**
	 * Metodo encargado de realizar la encriptacion de Cesar, sumando 2 posiciones al ASCII
	 * de cada caracter
	 * @param mensajeObtenidoCliente != Null && != ""
	 * @return
	 */

	
	

}
