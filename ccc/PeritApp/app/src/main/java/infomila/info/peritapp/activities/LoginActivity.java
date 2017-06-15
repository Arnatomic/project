package infomila.info.peritapp.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import infomila.info.peritapp.R;
import infomila.info.peritapp.asynctasks.GetCitesAsyncTask;
import infomila.info.peritapp.asynctasks.LoginAsyncTask;
import infomila.info.peritapp.menus.MenuActivity;

/**
 * Created by Mr. Robot on 08/06/2017.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText etUser;
    private EditText etPassword;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork().penaltyLog().build());


        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etUser = (EditText) findViewById(R.id.etLogin);
        etPassword = (EditText) findViewById(R.id.etPassword);

        context = this;

        Button btnEntra = (Button) findViewById(R.id.btnLogin);

        btnEntra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etUser.getText().toString().isEmpty() || etPassword.getText().toString().isEmpty()){
                    new AlertDialog.Builder(context)
                            .setTitle("Error")
                            .setMessage("Usuari o contrasenya necessaris")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }else{
                    LoginAsyncTask task = new LoginAsyncTask(context);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, etUser.getText().toString(), etPassword.getText().toString());
                }
            }
        });

    }

    public void onClickMenu(View view){
        registerForContextMenu(view);
        openContextMenu(view);

        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void onClickEntrar(View view){




    }

    public void onLoginResult(Long result){
        if(result == null){
            new AlertDialog.Builder(this)
                    .setTitle("Conexió Fallida!")
                    .setMessage("Usuari o contrasenya invàlids")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else if(result <= 0){
            new AlertDialog.Builder(this)
                    .setTitle("Conexió Fallida!")
                    .setMessage("Impossible conectar amb el servidor, disculpi les molèsties")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }else{
//            //Enregistrem l'id d'usuari
            PreferenceManager.getDefaultSharedPreferences(this).edit().putLong("userId",result).apply();

            Intent intent = new Intent(this,CitesActivity.class);
            startActivity(intent);
        }
    }
}
