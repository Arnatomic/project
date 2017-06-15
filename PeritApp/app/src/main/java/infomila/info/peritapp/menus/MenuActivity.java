package infomila.info.peritapp.menus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import infomila.info.peritapp.R;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new MenuFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
