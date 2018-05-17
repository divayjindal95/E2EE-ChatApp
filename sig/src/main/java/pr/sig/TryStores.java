package pr.sig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.whispersystems.libsignal.IdentityKey;
import org.whispersystems.libsignal.IdentityKeyPair;
import org.whispersystems.libsignal.InvalidKeyException;
import org.whispersystems.libsignal.SignalProtocolAddress;
import org.whispersystems.libsignal.state.PreKeyRecord;
import org.whispersystems.libsignal.state.SignedPreKeyRecord;
import org.whispersystems.libsignal.util.KeyHelper;

public class TryStores {

	public static void main(String[] args) throws InvalidKeyException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		IdentityKeyPair    identityKeyPair = KeyHelper.generateIdentityKeyPair();
        int                registrationId  = KeyHelper.generateRegistrationId(false);
        List<PreKeyRecord> preKeys         = KeyHelper.generatePreKeys(0, 10);
		int signedPreKeyId=0;
        SignedPreKeyRecord signedPreKey    = KeyHelper.generateSignedPreKey(identityKeyPair,signedPreKeyId);
        FileOutputStream f = new FileOutputStream(new File("./myKeyBundle.ser"));
		ObjectOutputStream o = new ObjectOutputStream(f);
		
		// Write objects to file
		o.writeObject(identityKeyPair.serialize());
		o.writeObject(registrationId);
		List<byte[]> preKeysSerialized = new ArrayList<>();
		for(PreKeyRecord pre:preKeys) {
			preKeysSerialized.add(pre.serialize());
		}
		o.writeObject(preKeysSerialized);
		o.writeObject(signedPreKey.serialize());

        /*
		FileInputStream fi = new FileInputStream(new File("./myKeyBundle.ser"));
		ObjectInputStream oi = new ObjectInputStream(fi);

		byte[] identityKeyPairSer = (byte[]) oi.readObject();
		int registrationIdSer = (int) oi.readObject();
				
		System.out.println(new String(new IdentityKeyPair(identityKeyPairSer).serialize()));
		System.out.println(new String(identityKeyPair.serialize()));
		*/
		//byte[] iv = (byte[]) oi.readObject();
        
		IdentityKeyPair    identityKeyPair1 = KeyHelper.generateIdentityKeyPair();
        int                registrationId1  = KeyHelper.generateRegistrationId(false);
        int count1=10;
		List<PreKeyRecord> preKeys1         = KeyHelper.generatePreKeys(1, count1);
		int signedPreKeyId1=1;
        SignedPreKeyRecord signedPreKey1    = KeyHelper.generateSignedPreKey(identityKeyPair1, signedPreKeyId1);
        SignalProtocolAddress spa1=new SignalProtocolAddress("remote", registrationId1);
        
        
        
		InMemoryIdentityKeyStoreMy iks = new InMemoryIdentityKeyStoreMy(identityKeyPair, registrationId);
		iks.saveIdentity(spa1, identityKeyPair1.getPublicKey());
		
	}

}
