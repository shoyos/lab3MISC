package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Model {
	
	SecretKey secretKey = null;
    private Cipher cipher;
    FileOutputStream fileOut;
    FileInputStream fileIn;
    ObjectOutputStream out;
    ObjectInputStream input;
    String filename = "key_";
    String transformacion = "DES/ECB/PKCS5Padding";

	public Model() throws NoSuchAlgorithmException, NoSuchPaddingException {
		// TODO Auto-generated constructor stub
        this.cipher = Cipher.getInstance(transformacion);
	}
	
	public void generateKey() {
		try {
			secretKey = KeyGenerator.getInstance("DES").generateKey();
			System.out.println("Llave generada");
			Util.printByteArrayInt(secretKey.getEncoded());

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	public byte[] encrypMessage (String input) {

		byte[] cipherText = null;
		byte[] clearText = input.getBytes();
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			cipherText = cipher.doFinal(clearText);
			Util.printByteArrayInt(cipherText);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return cipherText;
	}
	
	public String decriptMessage(byte[] input) {
		String output = "";
		
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] clearText = cipher.doFinal(input);
			output = new String(clearText);
		} catch (Exception e) {
				System.err.print(e.getMessage());
		}
		return output;
	}
	
	public void saveKeyFile() {
		String filenameGenerada = "";
		try {
		    Random rand = new Random(); //instance of random class
			filenameGenerada = this.filename+Integer.toString(rand.nextInt(10000));
			fileOut = new FileOutputStream(filenameGenerada);
			out = new ObjectOutputStream(fileOut);
			out.writeObject(secretKey);
			out.flush();
			out.close();
			
		} catch (Exception e) {
			System.out.println("Error al generar llave");
		}
	}
	
	public void loadKeyFromFile(String filename) {
		try {
			fileIn = new FileInputStream(filename);
			input = new ObjectInputStream(fileIn);
			secretKey = (SecretKey) input.readObject();
			input.close();
		} catch (Exception e) {
			System.out.println("Error al cargar llave");
		}
	}
	
	
}
