package schema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Cliente2 {


	
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
			socket = new Socket(LOCAL_HOST, PORT);
			System.out.println(2);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			
			String mensajeDelServidor = in.readUTF();
			
			String clave = in.readUTF();
			int key = Integer.parseInt(clave, 16);
			bw.write("Su encriptacion fue : " + mensajeDelServidor + ", la clave es: " + clave + "\n");
			bw.write("El mensaje desencriptado es " + desencriptar(mensajeDelServidor, key));
			bw.flush();
			bw.close();
			br.close();
			socket.close();
			in.close();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
private static String desencriptar(String mensajeObtenidoCliente, int key) {
		
		String respuesta = "";
		char caracter ;
		
		if(mensajeObtenidoCliente != "") {
			for (int i = 0; i < mensajeObtenidoCliente.length(); i++) {
				caracter = mensajeObtenidoCliente.charAt(i);
				caracter = (char) (caracter -key);
				respuesta += Character.toString((caracter)) + "";
			}
		}
		else {
			respuesta = "::El cliente no envio texto para encriptar::";
		}
		return respuesta;
		
	}
	
	
	

}
