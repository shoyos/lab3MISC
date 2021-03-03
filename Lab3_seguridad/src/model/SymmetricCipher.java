package model;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class SymmetricCipher {
	
   private SecretKey secretKey;
    private Cipher cipher;
    
    public SymmetricCipher(SecretKey secretkey, String transformation){
        this.secretKey = secretkey;
        
        try {
            cipher = Cipher.getInstance(transformation);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    
    public SecretKey getSecretKey() {
		return secretKey;
	}
	
	public byte[] encrypMessage (String input) {
		byte[] cipherText = null;
		byte[] clearText = input.getBytes();
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			cipherText = cipher.doFinal(clearText);
			
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

}
