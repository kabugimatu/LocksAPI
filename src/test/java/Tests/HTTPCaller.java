/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 *
 * @author zkmatu
 */
public class HTTPCaller {

    public String makeHTTPCall(String jsonString, String url) {
        String jsonResponse  = "";
        try {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("text/plain");

            RequestBody requestBody = RequestBody.create(mediaType, jsonString);

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            okhttp3.Response response = client.newCall(request).execute();
             jsonResponse = response.body().string();
          //  System.out.println("Response String >> " + jsonResponse);

        } catch (IOException ex) {
            Logger.getLogger(HTTPCaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonResponse;
    }
}
