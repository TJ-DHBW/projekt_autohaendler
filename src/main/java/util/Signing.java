package util;

import java.io.IOException;
import java.io.Serializable;
import java.security.*;

public class Signing {

    // TODO Test if this works
    public static SignedObject sign(PrivateKey privateKey, Serializable obj){
        Signature sig = getSignature(privateKey.getAlgorithm());
        if (sig == null) return null;
        SignedObject ret;
        try {
            ret = new SignedObject(obj, privateKey, sig);
        } catch (IOException e) {
            System.out.println("An exception occured while serializing the object for signing: " + obj);
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            System.out.println("The given private key for signing was invalid: " + privateKey);
            return null;
        } catch (SignatureException e) {
            System.out.println("The signing failed with signer: " + sig);
            e.printStackTrace();
            return null;
        }
        return ret;
    }

    // TODO Test if this works
    public static boolean verify(PublicKey publicKey, SignedObject obj){
        Signature sig = getSignature(publicKey.getAlgorithm());
        if (sig == null) return false;
        boolean valid;
        try {
            valid =  obj.verify(publicKey, sig);
        } catch (InvalidKeyException e) {
            System.out.println("The given public key for verification was invalid: " + publicKey);
            return false;
        } catch (SignatureException e) {
            System.out.println("Signature verification failed due to Signature engine: " + sig);
            e.printStackTrace();
            return false;
        }
        return valid;
    }

    public static KeyPairGenerator getKeyPairGenerator(){
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            return keyGen;
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Was not able to create a KeyPairGenerator with the RSA Algorithm.");
            return null;
        }
    }

    private static Signature getSignature(String algorithm){
        try {
            return Signature.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Was not able to create a Signature with the algorithm: " + algorithm);
            return null;
        }
    }
}
