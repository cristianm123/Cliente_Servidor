package schema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Cliente1 {
	
	/*
	 * 
	 * Direccion local de la maquina
	 */
	public static final String LOCAL_HOST = "172.30.190.15";
	/**
	 * Puerto por donde se establecera la conexion
	 */
	public static final int PORT = 8000;
	/**
	 * Socket que permitira la conexion con el servidor
	 */
	private static Socket socket;
	
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		
		DataInputStream in;
		DataOutputStream out;

		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			System.out.println("::Cliente disponible para ser atendido:: \nIngrese "
					+ "el mensaje para encriptar!!::");
			
			socket = new Socket(LOCAL_HOST, PORT);
			in = new DataInputStream(socket.getInputStream());
			int key = Integer.parseInt(in.readUTF(), 16);
			String mensaje = br.readLine();
			
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(metodoServicioServer(mensaje, key) );
			out.writeUTF(Integer.toHexString(key));
			bw.write("Su encriptacion fue : " + metodoServicioServer(mensaje, key) + " Con clave: " +  key);
			
			
			
			bw.flush();
			bw.close();
			br.close();
			socket.close();
			in.close();
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}
	private static String metodoServicioServer(String mensajeObtenidoCliente, int key) {
		
		String respuesta = "";
		char caracter ;
		
		if(mensajeObtenidoCliente != "") {
			for (int i = 0; i < mensajeObtenidoCliente.length(); i++) {
				caracter = mensajeObtenidoCliente.charAt(i);
				caracter = (char) (caracter +key);
				respuesta += Character.toString((caracter)) + "";
			}
		}
		else {
			respuesta = "::El cliente no envio texto para encriptar::";
		}
		return respuesta;
		
	}
	

}
