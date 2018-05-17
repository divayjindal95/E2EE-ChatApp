package pr.sig;
import org.whispersystems.libsignal.IdentityKey;
import org.whispersystems.libsignal.IdentityKeyPair;
import org.whispersystems.libsignal.SignalProtocolAddress;
import org.whispersystems.libsignal.state.IdentityKeyStore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class InMemoryIdentityKeyStoreMy implements IdentityKeyStore {

  //private final Map<SignalProtocolAddress, IdentityKey> trustedKeys = new HashMap<>();
	Map<SignalProtocolAddress, byte[]> trustedKeys;
  private final IdentityKeyPair identityKeyPair;
  private final int             localRegistrationId;

  public  InMemoryIdentityKeyStoreMy(IdentityKeyPair identityKeyPair, int localRegistrationId) throws IOException, ClassNotFoundException {
    this.identityKeyPair     = identityKeyPair;
    this.localRegistrationId = localRegistrationId;
    File f = new File("./identityKeyStore.ser");
    if(f.exists()) {
    	FileInputStream fi = new FileInputStream(new File("./identityKeyStore.ser"));
		ObjectInputStream oi = new ObjectInputStream(fi);
		this.trustedKeys =  (Map<SignalProtocolAddress, byte[]>) oi.readObject();
    }
    else {
    	this.trustedKeys = new HashMap<>();
    }
  }

  @Override
  public IdentityKeyPair getIdentityKeyPair() {
    return identityKeyPair;
  }

  @Override
  public int getLocalRegistrationId() {
    return localRegistrationId;
  }

  @Override
  public boolean saveIdentity(SignalProtocolAddress address, IdentityKey identityKey) {
    byte[] existing = trustedKeys.get(address);

    if (!identityKey.equals(existing)) {
      trustedKeys.put(address.toString(), identityKey.serialize());
      try {
	      FileOutputStream f = new FileOutputStream(new File("./identityKeyStore.ser"));
		  ObjectOutputStream o = new ObjectOutputStream(f);
		  o.writeObject(trustedKeys);
      }catch(Exception e){
    	  e.printStackTrace();
      }
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isTrustedIdentity(SignalProtocolAddress address, IdentityKey identityKey, Direction direction) {
    IdentityKey trusted = trustedKeys.get(address);
    return (trusted == null || trusted.equals(identityKey));
  }
}