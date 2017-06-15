package infomila.info.peritapp.menus;


import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import infomila.info.peritapp.R;
/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class MenuFragment extends PreferenceFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork(
                ).detectAll().penaltyLog().build());


        addPreferencesFromResource(R.xml.app_preferences);
    }
}
