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

import infomila.info.peritapp.activities.InformeActivity;
import infomila.info.peritapp.model.InformePericialApp;

/**
 * Created by Mr. Robot on 14/06/2017.
 */

public class GetInformeAsyncTask  extends AsyncTask<Integer, Void, InformePericialApp> {

    private Context context;
    private String ip;
    private int port;
    private Socket socket;

    public GetInformeAsyncTask(Context context){

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
    protected InformePericialApp doInBackground(Integer... params) {
        if(socket != null) {
            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                dos.writeInt(5);

                dos.writeInt(params[0]);
                //-----------------------

                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());

                InformePericialApp informe = (InformePericialApp) is.readObject();

                return informe;


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(InformePericialApp informePericialApp) {
        ((InformeActivity)context).onInformeDownloaded(informePericialApp);
    }
}
