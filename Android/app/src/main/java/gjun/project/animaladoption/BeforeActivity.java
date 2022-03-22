package gjun.project.animaladoption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BeforeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before);
    }

    //TODO 暫時測試用，首頁按鈕(測試開啟程式後自動下載sqlite)
    public void startPageClick(View v){
        Intent go_to_startPage;
        go_to_startPage = new Intent(BeforeActivity.this, StartActivity.class);
        BeforeActivity.this.startActivity(go_to_startPage);
        finish();
    }

    public void dogPageClick(View v){
        Intent go_to_dogPage;
        go_to_dogPage = new Intent(BeforeActivity.this, DogActivity.class);
        BeforeActivity.this.startActivity(go_to_dogPage);
        finish();
    }

    //TODO 暫時測試用，貓咪按鈕
    public void catPageClick(View v){
        Intent go_to_catPage;
        go_to_catPage = new Intent(BeforeActivity.this, CatActivity.class);
        BeforeActivity.this.startActivity(go_to_catPage);
        finish();
    }

    public void elsePageClick(View v){
        Intent go_to_elsePage;
        go_to_elsePage = new Intent(BeforeActivity.this, ElseActivity.class);
        BeforeActivity.this.startActivity(go_to_elsePage);
        finish();
    }

    public void accountPageClick(View v){
        Intent go_to_memberPage;
        go_to_memberPage = new Intent(BeforeActivity.this, MemberActivity.class);
        BeforeActivity.this.startActivity(go_to_memberPage);
        finish();
    }
}