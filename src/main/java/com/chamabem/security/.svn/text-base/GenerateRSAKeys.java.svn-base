package com.chamabem.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.HashMap;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class GenerateRSAKeys {
	private  BASE64Encoder b64 = new BASE64Encoder();
	
	public  HashMap<String, String> getKeys(){
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");

			SecureRandom random = new SecureRandom();
			generator.initialize(1024, random);

			KeyPair pair = generator.generateKeyPair();
			map.put("publicKey", b64.encode(pair.getPublic().getEncoded()));
			map.put("privateKey", b64.encode(pair.getPrivate().getEncoded()));
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return map;
	}
	
	public static String cleanApiKey(String publicKey){
		return publicKey.substring(0, 76) + publicKey.substring(77, 153) + publicKey.substring(154);
	}
	
	public SecureRandom createFixedRandom() {
		return new FixedRand();
	}

	private class FixedRand extends SecureRandom {
		private static final long serialVersionUID = 1L;
		MessageDigest sha;
		byte[] state;

		FixedRand() {
			try {
				this.sha = MessageDigest.getInstance("SHA-1");
				this.state = sha.digest();
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

		public void nextBytes(byte[] bytes) {
			int off = 0;
			sha.update(state);

			while (off < bytes.length) {
				state = sha.digest();

				if (bytes.length - off > state.length) {
					System.arraycopy(state, 0, bytes, off, state.length);
				} else {
					System.arraycopy(state, 0, bytes, off, bytes.length - off);
				}

				off += state.length;

				sha.update(state);
			}
		}
	}
}