package gjun.project.animaladoption;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import gjun.project.animaladoption.Utils.User;
import gjun.project.animaladoption.Utils.Utils;


public class UserActivity extends AppCompatActivity {

    EditText account, password;
    private Utils Util;
    User us = new User();

    public UserActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

         account = (EditText)findViewById(R.id.login_account);
         password = (EditText)findViewById(R.id.login_password);

    }

    public void index_register(View v){

        Intent intent = new Intent(UserActivity.this, RegisterActivity.class);;

        startActivity(intent);
    }


    public void index_login(View v){


        new Thread(){
            @Override
            public void run() {

                String data="";
                try {
                    data = "account="+ URLEncoder.encode(account.getText().toString(), "UTF-8")+
                            "&password="+ URLEncoder.encode(password.getText().toString(), "UTF-8");

                    Log.i("----->",account.getText().toString() + " " + password.getText().toString());
                    Log.i("----->",data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String request = Util.Post("ls",data);
                String userName = Util.Post("ms",data);

                int msg = 0;
                if (request.equals ("success")){
                    msg = 1;

                    Intent intent = new Intent(UserActivity.this, MemberActivity.class);

                    intent.putExtra("user",userName);

                    Log.i("----->",account.getText().toString() + " " + password.getText().toString() + "  " + userName);

                    startActivity(intent);
                }
                hand1.sendEmptyMessage(msg);
            }
        }.start();
    }
    final Handler hand1 = new Handler(){

        public void handleMessage(Message msg){

            if (msg.what == 1)
            {
                Toast.makeText(UserActivity.this,"登入成功",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(UserActivity.this, StartActivity.class);

                startActivity(intent);

                Log.i("----->",account.getText().toString() + " " + password.getText().toString());

                finish();
            }else
            {
                Toast.makeText(UserActivity.this, "登入失敗", Toast.LENGTH_LONG).show();
            }
        }
    };

}