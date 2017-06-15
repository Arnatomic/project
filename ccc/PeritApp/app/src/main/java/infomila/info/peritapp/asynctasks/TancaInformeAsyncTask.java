package infomila.info.peritapp.asynctasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InterfaceAddress;
import java.net.Socket;

import infomila.info.peritapp.activities.CitesActivity;

/**
 * Created by Mr. Robot on 14/06/2017.
 */

public class TancaInformeAsyncTask extends AsyncTask < Integer, Void, Integer> {

    private Context context;
    private String ip;
    private int port;
    private Socket socket;

    public TancaInformeAsyncTask(Context context){
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
    protected Integer doInBackground(Integer... params) {

        if(socket != null) {
            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                dos.writeInt(3);

                dos.writeInt(params[0]);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return params[0];
    }

    @Override
    protected void onPostExecute(Integer codiInforme) {
        ((CitesActivity)context).onInformeClosed(codiInforme);
    }
}
