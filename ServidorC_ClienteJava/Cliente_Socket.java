import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Cliente_Socket
{
	public static void main (String [] args)
	{
		new Cliente_Socket();
	}

	public Cliente_Socket()
	{
		try
		{
			Socket socket = new Socket ("127.0.0.1", 15557);
//			System.out.println ("conectado");

			socket.setSoLinger(true, 15);

			DataOutputStream bufferSalida = new DataOutputStream (socket.getOutputStream());
			int valor;
			do
			{
				//Registramos el numero
				Scanner lectura = new Scanner(System.in);
				System.out.println ("\nNumero a enviar a servidor: ");
				String info = lectura.next();

				DatoSocket aux = new DatoSocket(info);			
				aux.writeObject(bufferSalida);
			
				DataInputStream bufferEntrada = new DataInputStream (socket.getInputStream());

				DatoSocket dato = new DatoSocket("0");
				dato.readObject(bufferEntrada);
				System.out.println ("Servidor respondio: " + dato.toString());
				
				valor = Integer.parseInt(dato.toString());
			}while(valor != 1);
			
			System.out.println ("\nFin de conexion");
			socket.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
