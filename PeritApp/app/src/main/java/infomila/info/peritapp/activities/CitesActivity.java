package infomila.info.peritapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import java.util.ArrayList;

import infomila.info.peritapp.R;
import infomila.info.peritapp.model.Cita;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class CitesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cites);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);



    }

    public void onGetCitesResult(ArrayList<Cita> cites) {

    }
}
