package infomila.info.peritapp.asynctasks;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import infomila.info.peritapp.activities.CitesActivity;
import infomila.info.peritapp.activities.InformeActivity;

/**
 * Created by Mr. Robot on 14/06/2017.
 */

public class RefrescaSinistresAsyncTask extends AsyncTask<Integer, Void, Integer> {

    private Context context;
    private static final int espera = 60000;

    public RefrescaSinistresAsyncTask(Context context){

        this.context = context;
    }

    @Override
    protected Integer doInBackground(Integer... params) {
        while(true) {
            espera(espera);
            publishProgress();

            if(false)return null;
        }


    }


    @Override
    protected void onProgressUpdate(Void... values) {
        ((CitesActivity)context).onRefreshSinistres();
    }

    private void espera(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
        }
    }
}
