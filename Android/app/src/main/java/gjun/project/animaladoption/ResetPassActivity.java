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

import gjun.project.animaladoption.Utils.Utils;


public class ResetPassActivity extends AppCompatActivity {

    EditText account, oldPass, newPass;
    private Utils Util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        account = findViewById(R.id.resetP_name);
        oldPass = findViewById(R.id.resetP_password);
        newPass = findViewById(R.id.resetP_password2);
    }

    public  void clear(View v){

        account.setText("");
        oldPass.setText("");
        newPass.setText("");

    }

    public void resetP(View v){

        new Thread(){
            @Override
            public void run() {

                String data="";
                try {
                    data = "account="+ URLEncoder.encode(account.getText().toString(), "UTF-8")+
                            "&oldPass="+ URLEncoder.encode(oldPass.getText().toString(), "UTF-8")+
                            "&newPass="+ URLEncoder.encode(newPass.getText().toString(), "UTF-8");

                    Log.i("----->",account.getText().toString() + " " + newPass.getText().toString());
                    Log.i("----->",data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String request = Util.Post("rp",data);

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
                Intent intent = new Intent(ResetPassActivity.this,UserActivity.class);

                Toast.makeText(ResetPassActivity.this,"更改成功\n請重新登入",Toast.LENGTH_LONG).show();

                Log.i("----->",account.getText().toString() + " " + newPass.getText().toString());

                startActivity(intent);
                finish();
            }else
            {
                Toast.makeText(ResetPassActivity.this, "更改失敗", Toast.LENGTH_LONG).show();

                account.setText("");
                oldPass.setText("");
                newPass.setText("");
            }
        }
    };
}