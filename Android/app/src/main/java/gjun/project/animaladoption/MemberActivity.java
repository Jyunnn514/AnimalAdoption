package gjun.project.animaladoption;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MemberActivity extends AppCompatActivity {

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        userName = findViewById(R.id.userName);
        Intent intent = this.getIntent();
        final String name = intent.getStringExtra("user");
        Log.i("===>", "name => " + name);
        userName.setText(intent.getStringExtra("user"));
        if (name == null) {
            finish();
            return;
        }
    }
    public void resetName(View v){
        Intent intent = new Intent(this, ResetNameActivity.class);
        startActivity(intent);
    }

    public void resetPass(View v){
        Intent intent = new Intent(this,ResetPassActivity.class);
        startActivity(intent);
    }

    public void startPageClick(View v){
        Intent go_to_startPage;
        go_to_startPage = new Intent(MemberActivity.this, StartActivity.class);
        MemberActivity.this.startActivity(go_to_startPage);
    }

    public void dogPageClick(View v){
        Intent go_to_dogPage;
        go_to_dogPage = new Intent(MemberActivity.this, DogActivity.class);
        MemberActivity.this.startActivity(go_to_dogPage);
    }

    public void catPageClick(View v){
        Intent go_to_catPage;
        go_to_catPage = new Intent(MemberActivity.this, CatActivity.class);
        MemberActivity.this.startActivity(go_to_catPage);
    }

    public void elsePageClick(View v){
        Intent go_to_elsePage;
        go_to_elsePage = new Intent(MemberActivity.this, ElseActivity.class);
        MemberActivity.this.startActivity(go_to_elsePage);
    }

    public void accountPageClick(View v){
    }
}




