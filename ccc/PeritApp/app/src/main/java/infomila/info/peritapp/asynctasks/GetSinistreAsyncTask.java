package infomila.info.peritapp.asynctasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import infomila.info.peritapp.activities.SinistreReadOnlyActivity;
import infomila.info.peritapp.model.SinistreApp;

/**
 * Created by Mr. Robot on 14/06/2017.
 */

public class GetSinistreAsyncTask extends AsyncTask <Integer, Void, SinistreApp> {

    private Context context;
    private String ip;
    private int port;
    private Socket socket;

    public GetSinistreAsyncTask(Context context){
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
    protected SinistreApp doInBackground(Integer... params) {

        if(socket != null) {
            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                dos.writeInt(4);

                dos.writeInt(params[0]);
                //-----------------------

                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

                 SinistreApp s =(SinistreApp) is.readObject();

                return s;


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(SinistreApp sinistreApp) {
        ((SinistreReadOnlyActivity)context).onSinistreDownloaded(sinistreApp);
    }
}
