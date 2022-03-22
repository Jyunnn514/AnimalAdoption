package gjun.project.animaladoption.Utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    //The accessed serlvers are different
    //The parameters passed are different

    public static String Post(String url,String data)
    {
        String msg = "";
        try{
            //http://ms-yffprtappszi:8080/AndroidWeb/LoginServlet
            HttpURLConnection conn = (HttpURLConnection) new URL("http://192.168.31.104:8080/Project/"+url).openConnection();
            //Set request mode and request timeout information
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            //Set operation input and output:
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //The post mode cannot be cached and needs to be manually set to false
            conn.setUseCaches(false);
            //Data we requested:


            //Get output stream
            OutputStream out = conn.getOutputStream();
            out.write(data.getBytes());
            out.flush();
            if (conn.getResponseCode() == 200) {
                //Gets the input stream object of the response
                InputStream is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                StringBuffer response = new StringBuffer();

                String line=null;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                msg=response.toString();
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return msg;
    }
}

