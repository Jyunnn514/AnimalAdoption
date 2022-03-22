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


public class RegisterActivity extends AppCompatActivity {

    EditText name, account, password, password2;
    private Utils Util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        account = findViewById(R.id.login_account);
        password = findViewById(R.id.login_password);
        password2 = findViewById(R.id.password2);
    }

    public void clear(View v){

        name.setText("");
        account.setText("");
        password.setText("");
        password2.setText("");
    }

    public void register(View v) {

        String username = name.getText().toString();
        String useracc = account.getText().toString();
        String userpass = password.getText().toString();
        String userpass2 = password2.getText().toString();

        Log.i("----->", "1" + username);

        if(username.length() < 2 ) {

            Toast.makeText(RegisterActivity.this, "名稱尚未輸入", Toast.LENGTH_LONG).show();

            if(useracc.length() < 2){

                Toast.makeText(RegisterActivity.this, "帳號尚未輸入", Toast.LENGTH_LONG).show();

                if (userpass.length() < 2){

                    Toast.makeText(RegisterActivity.this, "密碼尚未輸入", Toast.LENGTH_LONG).show();

                    if(userpass2.length() < 2){

                        Toast.makeText(RegisterActivity.this, "密碼尚未輸入", Toast.LENGTH_LONG).show();

                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }



        Log.i("----->", "2" + username);

        User us = new User();

        us.setName(username);
        us.setAccount(useracc);
        us.setPassword(userpass);
        us.setPassword2(userpass2);

        Log.i("----->", "3" + username);

        new Thread() {
            @Override
            public void run() {

                String data = "";
                try {

                    Log.i("----->", "4" + username);

                    data = "&name=" + URLEncoder.encode(us.getName(), "UTF-8") +
                            "&account=" + URLEncoder.encode(us.getAccount().toString(), "UTF-8") +
                            "&password=" + URLEncoder.encode(us.getPassword().toString(), "UTF-8") +
                            "&password2=" + URLEncoder.encode(us.getPassword2().toString(), "UTF-8");

                    Log.i("----->", "5" + username);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                Log.i("----->", "6" + username);

                String request = Util.Post("rs", data);

                int msg = 0;

                if (request.equals("not same")){
                    msg = 3;
                }
                if (request.equals("success")) {
                    msg = 2;
                }
                //Already exists
                if (request.equals("already exists")) {
                    msg = 1;
                }

                hand.sendEmptyMessage(msg);

            }
        }.start();

    }

    final Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Toast.makeText(RegisterActivity.this, "註冊失敗", Toast.LENGTH_LONG).show();

            }
            if (msg.what == 1) {
                Toast.makeText(RegisterActivity.this, "此帳號已經註冊過", Toast.LENGTH_LONG).show();

            }

            if(msg.what == 2)
            {
                Intent intent = new Intent(RegisterActivity.this, UserActivity.class);
                Toast.makeText(RegisterActivity.this, "註冊成功", Toast.LENGTH_LONG).show();

                startActivity(intent);
                finish();
            }

            if (msg.what == 3){
                Toast.makeText(RegisterActivity.this, "兩次密碼輸入不相同", Toast.LENGTH_LONG).show();
            }
        }
    };
}