package pr.sig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Jugad {
	public static void main(String args[]) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException {
		System.out.println(args[0]);
		//String arg="ENCRYPT";
		if(args[0].equals("ENCRYPT")) {
			System.out.println("Encrypting");
			KeyGenerator keygen = KeyGenerator.getInstance("AES") ; // key generator to be used with AES algorithm.
			keygen.init(128) ; // Key size is specified here.
			byte[] key = keygen.generateKey().getEncoded();
			SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
			//System.out.println(args[0]);
			//Cipher cipher = Cipher.getInstance("AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecureRandom randomSecureRandom = SecureRandom.getInstance("SHA1PRNG");
			byte[] iv = new byte[cipher.getBlockSize()];
			randomSecureRandom.nextBytes(iv);
			IvParameterSpec ivParams = new IvParameterSpec(iv);
			
			FileOutputStream fos = new FileOutputStream("../keyStore/Mykey.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// write object to file
			oos.writeObject(key);
			oos.writeObject(iv);
			
//			FileOutputStream fos = new FileOutputStream("./Mykey.ser");
//			ObjectOutputStream oos = new ObjectOutputStream(fos);
//			// write object to file
//			oos.writeObject(key);
//			oos.writeObject(iv);
			
			// wrap key data in Key/IV specs to pass to cipher
			SecretKeySpec ekey = new SecretKeySpec(key, "AES");
			IvParameterSpec eivSpec = new IvParameterSpec(iv);
			// create the cipher with the algorithm you choose
			// see javadoc for Cipher class for more info, e.g.
			//Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			String inputs = args[1];
			byte[] input=inputs.getBytes();
			cipher.init(Cipher.ENCRYPT_MODE, ekey, eivSpec);
			byte[] encrypted= new byte[cipher.getOutputSize(input.length)];
			int enc_len = cipher.update(input, 0, input.length, encrypted, 0);
			enc_len += cipher.doFinal(encrypted, enc_len);
			FileOutputStream fos1 = new FileOutputStream("../messageStore/msg.ser");
			ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
			oos1.writeObject(encrypted);
			oos1.writeObject(enc_len);
			oos1.close();
			oos.close();
			
//			FileOutputStream fos1 = new FileOutputStream("./msg.ser");
//			ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
//			oos1.writeObject(encrypted);
//			oos1.writeObject(enc_len);
//			oos1.close();
//			oos.close();
			System.out.println(new String(input));
			System.out.println(new String(encrypted));
			
		}
		
		if(args[0].equals("DECRYPT")) {
			System.out.println("Decrypting");
			FileInputStream fi = new FileInputStream(new File(args[1]));
			ObjectInputStream oi = new ObjectInputStream(fi);

			byte[] key = (byte[]) oi.readObject();
			byte[] iv = (byte[]) oi.readObject();
			//System.out.println(new String(key));
			//System.out.println(args[1]);
			//System.out.println(args[2]);
			FileInputStream fi1 = new FileInputStream(new File(args[2]));
			ObjectInputStream oi1 = new ObjectInputStream(fi1);
			byte[] encrypted = (byte[]) oi1.readObject();
			int enc_len = Integer.parseInt(oi1.readObject().toString());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			SecretKeySpec dkey = new SecretKeySpec(key, "AES");
			IvParameterSpec divSpec = new IvParameterSpec(iv);
			cipher.init(Cipher.DECRYPT_MODE, dkey, divSpec);
			byte[] decrypted = new byte[cipher.getOutputSize(enc_len)];
			int dec_len = cipher.update(encrypted, 0, enc_len, decrypted, 0);
			dec_len += cipher.doFinal(decrypted, dec_len);
			
			System.out.println(new String(encrypted));
			System.out.println(new String(decrypted));
		}
		
	}
}
