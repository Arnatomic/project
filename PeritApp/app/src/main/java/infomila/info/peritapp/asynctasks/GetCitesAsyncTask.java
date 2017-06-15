package infomila.info.peritapp.asynctasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import infomila.info.peritapp.activities.CitesActivity;
import infomila.info.peritapp.model.Cita;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class GetCitesAsyncTask extends AsyncTask<String, Void, ArrayList<Cita>> {

    private Context context;
    private String ip;
    private int port;
    private Socket socket;

    public GetCitesAsyncTask(Context context){

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
    protected ArrayList<Cita> doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Cita> cites) {
        ((CitesActivity)context).onGetCitesResult(cites);
    }
}
