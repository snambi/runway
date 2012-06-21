/*
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  copyright (C) 2012 nambi sankaran.
 */

package org.runway.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.springframework.security.core.codec.Base64;

@SuppressWarnings("restriction")
public class StringEncryptDecryptUtil {

	private static final String ALGORITHM = "PBEWithMD5AndDES";
    private static final char[] PASSWORD = "hjknkdrs,;.nhcrwsxxfi754fv v".toCharArray();
    private static final byte[] SALT = {
        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
    };

    public static void main(String[] args) throws Exception {
        String originalPassword = "THIS_IS_MY_SITE12@";
        System.out.println("Original password: " + originalPassword);
        String encryptedPassword = encrypt(originalPassword);
        System.out.println("Encrypted password: " + encryptedPassword);
        String decryptedPassword = decrypt(encryptedPassword);
        System.out.println("Decrypted password: " + decryptedPassword);
        
        String anotherKey1 = "10237474";
        System.out.println("another key1: " + anotherKey1);
        String encryptedKey1 = encrypt(anotherKey1);
        System.out.println("encrypted key1: "+ encryptedKey1);
        String decryptedKey1 = decrypt(encryptedKey1);
        System.out.println("decrypted key1: "+ decryptedKey1);
        
        
        System.out.println("decrypted : " + decrypt(URLDecoder.decode("j9L6SiY7PhcUxHLHdT4YdQ%3D%3D", "UTF-8")));
    }

    public static String urlEncrypt( String value) throws RunwaySecurityException{
    	String enc = encrypt(value);
    	String result = null;
    	try {
    		result = URLEncoder.encode(enc, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RunwaySecurityException(e);
		}
		return result;
    }
    public static String encrypt(String property) throws RunwaySecurityException  {
    	String result = null;
        SecretKeyFactory keyFactory;
		try {
			keyFactory = SecretKeyFactory.getInstance( ALGORITHM);
	        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
	        Cipher pbeCipher = Cipher.getInstance(ALGORITHM);
	        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
	        result = base64Encode(pbeCipher.doFinal(property.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			throw new RunwaySecurityException(e);
		} catch (InvalidKeySpecException e) {
			throw new RunwaySecurityException(e);
		} catch (NoSuchPaddingException e) {
			throw new RunwaySecurityException(e);
		} catch (InvalidKeyException e) {
			throw new RunwaySecurityException(e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new RunwaySecurityException(e);
		} catch (IllegalBlockSizeException e) {
			throw new RunwaySecurityException(e);
		} catch (BadPaddingException e) {
			throw new RunwaySecurityException(e);
		}
		
		return result;
    }

    public static String base64Encode(byte[] bytes) {
        // NB: This class is internal, and you probably should use another impl
        return new String(Base64.encode(bytes));
    }

    public static String urlDecrypt(String value) throws RunwaySecurityException{
    	
    	String enc = null;
    	try {
			enc = URLDecoder.decode( value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RunwaySecurityException(e);
		}
		return decrypt(enc);
    }
    
    public static String decrypt(String value) throws RunwaySecurityException  {
    	String result = null;
        SecretKeyFactory keyFactory;
        
		try {
			
			keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
	        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
	        Cipher pbeCipher = Cipher.getInstance( ALGORITHM );
	        pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
	        result= new String(pbeCipher.doFinal(base64Decode(value)));
	        
		} catch (NoSuchAlgorithmException e) {
			throw new RunwaySecurityException(e);
		} catch (InvalidKeySpecException e) {
			throw new RunwaySecurityException(e);
		} catch (NoSuchPaddingException e) {
			throw new RunwaySecurityException(e);
		} catch (InvalidKeyException e) {
			throw new RunwaySecurityException(e);
		} catch (InvalidAlgorithmParameterException e) {
			throw new RunwaySecurityException(e);
		} catch (IllegalBlockSizeException e) {
			throw new RunwaySecurityException(e);
		} catch (BadPaddingException e) {
			throw new RunwaySecurityException(e);
		} catch (IOException e) {
			throw new RunwaySecurityException(e);
		}
		
		return result;
    }

    public static byte[] base64Decode(String property) throws IOException {
        // NB: This class is internal, and you probably should use another impl
    	byte[] bytes = property.getBytes();
        return Base64.decode( bytes );
    }

}
