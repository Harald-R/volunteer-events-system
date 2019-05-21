package com.pad.cristina.freely.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class ApiRequestsUtil {

    public enum REQUEST_TYPES {
        GET("GET"),
        POST("POST"),
        PUT("PUT"),
        DELETE("DELETE");

        private String type;

        REQUEST_TYPES(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

    };

    public static String sendRequest(String url, REQUEST_TYPES type, Map<String, String> body) {
        HttpURLConnection con = null;
        try {
            // Prepare request body from dictionary
            String reqBody = "";
            for (Iterator<Map.Entry<String, String>> it = body.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = it.next();
                reqBody += entry.getKey() + "=" + entry.getValue();

                if (it.hasNext())
                    reqBody += "&";
            }

            // Open connection to server
            URL u = new URL(url);
            con = (HttpURLConnection) u.openConnection();
            con.setRequestMethod(type.getType());
            con.setDoOutput(true);
            con.getOutputStream().write(reqBody.getBytes());
            con.connect();

            if (con.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                return sb.toString();
            }
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

}
