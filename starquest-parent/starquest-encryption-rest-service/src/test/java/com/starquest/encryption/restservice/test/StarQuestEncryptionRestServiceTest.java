package com.starquest.encryption.restservice.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * 
 */

/**
 * @author mallesh
 *
 */
public class StarQuestEncryptionRestServiceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		String password = null;
		if(null != args && args.length>0){
			password = args[0];
		}
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGetRequest = new HttpGet("http://localhost:8080/starquest-encryption-rest-service/rest/encryption/"+password);
		try {
			HttpResponse response = client.execute(httpGetRequest);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while((line = rd.readLine()) != null){
				System.out.println(line);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
