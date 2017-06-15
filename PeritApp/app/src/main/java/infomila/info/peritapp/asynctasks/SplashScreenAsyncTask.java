package infomila.info.peritapp.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import infomila.info.peritapp.activities.SplashScreenActivity;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class SplashScreenAsyncTask extends AsyncTask<Void, Void, Integer> {


    private Context context;

    public SplashScreenAsyncTask(Context context){
        this.context = context;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 1;
    }

    @Override
    protected void onPostExecute(Integer integer) {
        ((SplashScreenActivity)context).onAsyncEnded();
    }
}
