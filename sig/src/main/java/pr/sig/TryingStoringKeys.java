//package pr.sig;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.ShortBufferException;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//
//public class TryingStoringKeys {
//
//	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
//		// TODO Auto-generated method stub
//			
//		String p1="asdsd";
//		String p2 = "asdfdf";
//
//		try {
////			FileOutputStream f = new FileOutputStream(new File("./myObjects.txt"));
////			ObjectOutputStream o = new ObjectOutputStream(f);
////
////			// Write objects to file
////			o.writeObject(p1);
////			o.writeObject(p2);
////
////			o.close();
////			f.close();
//
//			FileInputStream fi = new FileInputStream(new File("./key.ser"));
//			ObjectInputStream oi = new ObjectInputStream(fi);
//
//			byte[] key = (byte[]) oi.readObject();
//			byte[] iv = (byte[]) oi.readObject();
//			System.out.println(new String(key));
//			
//			
//			FileInputStream fi1 = new FileInputStream(new File("./msg.ser"));
//			ObjectInputStream oi1 = new ObjectInputStream(fi);
//			byte[] encrypted = (byte[]) oi1.readObject();
//			int enc_len = (int) oi1.readObject();
//			System.out.println(new String(encrypted));
//			
//			oi.close();
//			oi1.close();
//			
//			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//			SecretKeySpec dkey = new SecretKeySpec(key, "AES");
//			IvParameterSpec divSpec = new IvParameterSpec(iv);
//			cipher.init(Cipher.DECRYPT_MODE, dkey, divSpec);
//			byte[] decrypted = new byte[cipher.getOutputSize(enc_len)];
//			int dec_len = cipher.update(encrypted, 0, enc_len, decrypted, 0);
//			dec_len += cipher.doFinal(decrypted, dec_len);
//			
//			System.out.println(new String(decrypted));
//			// Read objects
//			/*
//			Person pr1 = (Person) oi.readObject();
//			Person pr2 = (Person) oi.readObject();
//
//			System.out.println(pr1.toString());
//			System.out.println(pr2.toString());
//			*/
//			//oi.close();
//			
//			//fi.close();
//			
//		} catch (FileNotFoundException e) {
//			System.out.println("File not found");
//		} catch (IOException e) {
//			System.out.println("Error initializing stream");
//		}
//
//		
//	}
//
//}
