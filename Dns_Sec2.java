import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;

class En_Dec
{
		private static SecretKeySpec secretKey;
	    private static byte[] key;
	  
	    public static void setKey(String myKey) 
	    {
	        MessageDigest sha = null;
	        
	        try {
	        		key = myKey.getBytes("UTF-8");
	        		sha = MessageDigest.getInstance("SHA-1");
	        		key = sha.digest(key);
	        		key = Arrays.copyOf(key,16); 
	        		secretKey = new SecretKeySpec(key, "AES");
	        	} 
	        
	        catch (NoSuchAlgorithmException e) 
	        {
	            e.printStackTrace();
	        } 
	        
	        catch (UnsupportedEncodingException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	  
	
	  
	
	  public static String encryptText(String plainText,String secret) 
	  {

		  try
	        {   // AES defaults to AES/ECB/PKCS5Padding in Java 7
			  
			  setKey(secret); 
			  
			  Cipher aesCipher = Cipher.getInstance("AES");

			  aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);

			  return Base64.getEncoder().encodeToString(aesCipher.doFinal(plainText.getBytes("UTF-8")));

			 }
		  
		  catch (Exception e) 
	        {
	            System.out.println("Error while encrypting: " + e.toString());
	        }
	        return null;

	  }
	  
	  
	  public static String decryptText(String strTodecrypt, String secret) 
	  {

	        // AES defaults to AES/ECB/PKCS5Padding in Java 7
		  try
	      { 
			  setKey(secret);
			  
			  Cipher aesCipher = Cipher.getInstance("AES");
			  
			  aesCipher.init(Cipher.DECRYPT_MODE, secretKey);

			  return new String(aesCipher.doFinal(Base64.getDecoder().decode(strTodecrypt)));

		      

	      }

	      catch(Exception e)
	      {
	    	  System.out.println("Error while decrypting: " + e.toString());
	      }
	    return null;
     }

}  
	  
public class Dns_Sec2
{
	public static void main(String args[]) throws InterruptedException
	{
		
		En_Dec AES = new En_Dec();
		Scanner sc1 = new Scanner(System.in);
		String web1 = "www.facebook.com";
		String web2 = "www.google.com";
		String web3 = "www.yahoo.com";
		
		final String secretKey = "101HRAS";
	    System.out.println("Enter the name you want to resolve: ");
	    String originalString = sc1.next();
	    
	    String encryptedString = AES.encryptText(originalString, secretKey);
	    String decryptedString = AES.decryptText(encryptedString, secretKey);
	     
	    System.out.println("Original String Query: "+originalString);
	    System.out.println("Encrypted String Query: "+encryptedString);
	    System.out.println("Enter Key: ");
	    String entred_key = sc1.next();
	    
	    
	    
	   
	    
	    if(entred_key.equals(secretKey))
	    {
	    	System.out.println("Verifying Key......");
		    Thread.sleep(5000);
		    
		    System.out.println("Decrypted String Query: "+decryptedString);
		    
	    	System.out.println("Searching Host File for Entry......");
	    	Thread.sleep(5000);
	    	if(web1.equals(originalString))
	    	{
	    		System.out.println("Entered Address is: "+web1);
	    		System.out.println("Key Match Found");
	    		System.out.println("IP Address is: 192.168.10.2");
	    	}
	    	
	    	else if(web2.equals(originalString))
	    	{
	    		System.out.println("Entered Address is: "+web2);
	    		System.out.println("Key Match Found");
	    		System.out.println("IP Address is: 192.168.10.3");
	    	}
	    	
	    	else if(web3.equals(originalString))
	    	{
	    		System.out.println("Entered Address is: "+web3);
	    		System.out.println("Key Match Found");
	    		System.out.println("IP Address is: 192.168.10.4");
	    	}
	    	
	    	else
	    	{
	    		System.out.println("No Entry Found Querying Another Server For Reply!!");
	    	}
	    }
	    
	    else
	    {
	    	System.out.println("Wrong Key Entered!!IP Address for the specified query cannot be retreived.");
	    }
	    
	}
}
	
	

