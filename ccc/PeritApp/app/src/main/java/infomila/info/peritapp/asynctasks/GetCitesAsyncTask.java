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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import infomila.info.peritapp.activities.CitesActivity;
import infomila.info.peritapp.model.ResumSinistreApp;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class GetCitesAsyncTask extends AsyncTask<String, Void, ArrayList<ResumSinistreApp>> {

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
    protected ArrayList<ResumSinistreApp> doInBackground(String... params) {

        ArrayList<ResumSinistreApp> cites = new ArrayList<>();
        if(socket != null){
            try {
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


                dos.writeInt(2);

                int size = dis.readInt();
                System.out.println("^^^^^^^^SIZE: " + size);

                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                for(int i = 0; i<size; i++){
                    try {
                        ResumSinistreApp resum =(ResumSinistreApp) is.readObject();
                        cites.add(resum);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                Collections.sort(cites, new Comparator<ResumSinistreApp>() {
                    @Override
                    public int compare(ResumSinistreApp resum1, ResumSinistreApp resum2) {
                        return resum1.getDiaHora().compareTo(resum2.getDiaHora());
                    }
                });

                return cites;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            return null;
        }

        return null;


    }

    @Override
    protected void onPostExecute(ArrayList<ResumSinistreApp> cites) {
        ((CitesActivity)context).onGetCitesResult(cites);
    }
}
