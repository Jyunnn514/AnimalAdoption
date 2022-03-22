package gjun.project.animaladoption;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import gjun.project.animaladoption.Utils.Utils;


public class ResetNameActivity extends AppCompatActivity {

    EditText name, account, password;
    private Utils Util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_name);

        name = findViewById(R.id.resetN_name);
        account = findViewById(R.id.resetN_account);
        password = findViewById(R.id.resetN_password);
    }

    public  void clear(View v){

        name.setText("");
        account.setText("");
        password.setText("");

    }

    public void resetN(View v){

        new Thread(){
            @Override
            public void run() {

                String data="";
                try {
                    data = "name="+ URLEncoder.encode(name.getText().toString(), "UTF-8")+
                            "&account="+ URLEncoder.encode(account.getText().toString(), "UTF-8")+
                            "&password="+ URLEncoder.encode(password.getText().toString(), "UTF-8");

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String request = Util.Post("rn",data);

                int msg = 0;
                if (request.equals ("success")){
                    msg = 1;
                }
                hand1.sendEmptyMessage(msg);
            }
        }.start();
    }
    final Handler hand1 = new Handler(){

        public void handleMessage(Message msg){

            if (msg.what == 1)
            {
                Intent intent = new Intent(ResetNameActivity.this,UserActivity.class);

                Toast.makeText(ResetNameActivity.this,"更改成功\n請重新登入",Toast.LENGTH_LONG).show();

                startActivity(intent);
                finish();
            }else
            {
                Toast.makeText(ResetNameActivity.this, "更改失敗", Toast.LENGTH_LONG).show();

                name.setText("");
                account.setText("");
                password.setText("");
            }
        }
    };
}