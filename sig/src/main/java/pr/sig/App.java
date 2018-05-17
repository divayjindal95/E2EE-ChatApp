//package pr.sig;
//
//import java.io.UnsupportedEncodingException;
//import java.util.List;
//
//import org.whispersystems.libsignal.DuplicateMessageException;
//import org.whispersystems.libsignal.IdentityKeyPair;
//import org.whispersystems.libsignal.InvalidKeyException;
//import org.whispersystems.libsignal.InvalidKeyIdException;
//import org.whispersystems.libsignal.InvalidMessageException;
//import org.whispersystems.libsignal.LegacyMessageException;
//import org.whispersystems.libsignal.SessionBuilder;
//import org.whispersystems.libsignal.SessionCipher;
//import org.whispersystems.libsignal.SignalProtocolAddress;
//import org.whispersystems.libsignal.UntrustedIdentityException;
//import org.whispersystems.libsignal.ecc.ECPublicKey;
//import org.whispersystems.libsignal.protocol.CiphertextMessage;
//import org.whispersystems.libsignal.protocol.PreKeySignalMessage;
//import org.whispersystems.libsignal.state.PreKeyBundle;
//import org.whispersystems.libsignal.state.PreKeyRecord;
//import org.whispersystems.libsignal.state.SessionStore;
//import org.whispersystems.libsignal.state.SignedPreKeyRecord;
//import org.whispersystems.libsignal.util.KeyHelper;
//
///**
// * Hello world!
// *
// */
//public class App 
//{
//    public static void main( String[] args ) throws InvalidKeyException, UntrustedIdentityException, UnsupportedEncodingException, DuplicateMessageException, LegacyMessageException, InvalidMessageException, InvalidKeyIdException
//    {
//        System.out.println( "Hello World!" );
//        
//        IdentityKeyPair    identityKeyPair = KeyHelper.generateIdentityKeyPair();
//        int                registrationId  = KeyHelper.generateRegistrationId(false);
//        int count=10;
//		List<PreKeyRecord> preKeys         = KeyHelper.generatePreKeys(0, count);
//		int signedPreKeyId=0;
//        SignedPreKeyRecord signedPreKey    = KeyHelper.generateSignedPreKey(identityKeyPair,signedPreKeyId);
//        System.out.println(identityKeyPair.getPublicKey().hashCode());
//        System.out.println(registrationId);
//        InMemorySignalProtocolStore signalProtocolStore = new InMemorySignalProtocolStore(identityKeyPair,registrationId,signedPreKey,signedPreKeyId,preKeys);
//
//        
//        IdentityKeyPair    identityKeyPair1 = KeyHelper.generateIdentityKeyPair();
//        int                registrationId1  = KeyHelper.generateRegistrationId(false);
//        int count1=10;
//		List<PreKeyRecord> preKeys1         = KeyHelper.generatePreKeys(1, count1);
//		int signedPreKeyId1=1;
//        SignedPreKeyRecord signedPreKey1    = KeyHelper.generateSignedPreKey(identityKeyPair1, signedPreKeyId1);
//        System.out.println(identityKeyPair1.getPublicKey().hashCode());
//        System.out.println(registrationId1);
//        InMemorySignalProtocolStore signalProtocolStore1 = new InMemorySignalProtocolStore(identityKeyPair1,registrationId1,signedPreKey1,signedPreKeyId1,preKeys1);
//        
//        SignalProtocolAddress spa=new SignalProtocolAddress("remote", registrationId1);
//                
//        SessionBuilder sessionBuilder = new SessionBuilder(signalProtocolStore, spa);
//        
//        PreKeyBundle pkb = new PreKeyBundle(registrationId1,registrationId1, 1, preKeys1.get(1).getKeyPair().getPublicKey(), 1, signedPreKey1.getKeyPair().getPublicKey(), signedPreKey1.getSignature(), identityKeyPair1.getPublicKey());
//        
//        sessionBuilder.process(pkb);
//        
//        SessionCipher     sessionCipher = new SessionCipher(signalProtocolStore,spa);
//        System.out.println(sessionCipher.getRemoteRegistrationId());
//        PreKeySignalMessage message      = (PreKeySignalMessage) sessionCipher.encrypt("Hello world!".getBytes("UTF-8"));
//        
//        System.out.println(message.serialize());
//        
//        
//        
//        
//        SignalProtocolAddress spa1=new SignalProtocolAddress("ori", registrationId);
//        //SessionBuilder sessionBuilder1 = new SessionBuilder(signalProtocolStore1, spa1);
//        //PreKeyBundle pkb1 = new PreKeyBundle(registrationId, 0,0, preKeys.get(0).getKeyPair().getPublicKey(), 0, signedPreKey.getKeyPair().getPublicKey(), signedPreKey.getSignature(), identityKeyPair.getPublicKey());   
//        // sessionBuilder1.process(pkb1);
//     
//        SessionCipher     sessionCipher1 = new SessionCipher(signalProtocolStore1,spa1);
//        
//        sessionCipher1.decrypt((PreKeySignalMessage) message);
//        
//        
//                
//        
//        
//        
//        
//        
//        /*
//        SignalProtocolAddress spa=new SignalProtocolAddress("my", 111);
//        
//        //InMemoryIdentityKeyStore ids = new InMemoryIdentityKeyStore(identityKeyPair, registrationId);
//        //ids.saveIdentity(spa, identityKeyPair.getPublicKey());
//        
//        //InMemoryPreKeyStore pks = new InMemoryPreKeyStore();
//        
//        
//        InMemorySignalProtocolStore signalProtocolStore = new InMemorySignalProtocolStore(identityKeyPair,registrationId);
//        
//
//		SessionBuilder sessionBuilder = new SessionBuilder(signalProtocolStore,
//                spa);
//		
//		
//		ECPublicKey preKeyPublic=new E;
//		PreKeyBundle pkb =new PreKeyBundle(1,111 , 2, preKeyPublic, signedPreKeyId, signedPreKeyPublic, signedPreKeySignature, identityKey)
//		
//		sessionBuilder.process();
//		
//		SessionCipher     sessionCipher = new SessionCipher(signalProtocolStore,spa);
//		CiphertextMessage message       = sessionCipher.encrypt("Hello world!".getBytes("UTF-8"));
//     	*/
//		
//    }
//    
//}
