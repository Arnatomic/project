package infomila.info.peritapp.asynctasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import infomila.info.peritapp.activities.LoginActivity;
import infomila.info.peritapp.utils.MD5Encrypter;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class LoginAsyncTask extends AsyncTask <String, Void, Long>{

    private Context context;
    private Socket socket;
    private String ip;
    private  int port;


    public LoginAsyncTask(Context context){
        this.context = context;

        SharedPreferences prefs =  PreferenceManager.getDefaultSharedPreferences(context);
        ip = prefs.getString("ip", "127.0.0.1");
        port = Integer.parseInt(prefs.getString("port", "1234"));

        try {
            socket = new Socket(ip,port);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Long doInBackground(String... params) {
        Long response;
       if(socket != null){
           try {
               DataInputStream dis = new DataInputStream(socket.getInputStream());
               DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

               dos.writeInt(1);

               dos.writeUTF(params[0]);
               dos.writeUTF(MD5Encrypter.encrypt(params[1]));

               response = dis.readLong();

               System.out.println("IDPERIT: " + response);

               return response > 0 ? response : null;


           } catch (IOException e) {
               e.printStackTrace();
           }

       }else{
           return 0L;
       }

        return -1L;
    }

    @Override
    protected void onPostExecute(Long result) {

        ((LoginActivity)context).onLoginResult(result);

    }
}
