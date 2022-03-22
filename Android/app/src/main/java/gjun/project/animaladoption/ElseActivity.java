package gjun.project.animaladoption;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ElseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_else);
    }

    public void beforeClick(View v){
        Intent go_to_beforePage;
        go_to_beforePage = new Intent(ElseActivity.this, BeforeActivity.class);
        ElseActivity.this.startActivity(go_to_beforePage);
    }

    public void storyClick(View v){
        Intent go_to_webPage;
        go_to_webPage = new Intent(ElseActivity.this, WebActivity.class);
        ElseActivity.this.startActivity(go_to_webPage);
    }

    public void shelterClick(View v){
        String url = "https://www.google.com/maps/search/" + "動物收容所";
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
        startActivity(intent);
    }

    public void hospitalClick(View v){
        String url = "https://www.google.com/maps/search/" + "動物醫院";
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
        startActivity(intent);
    }

    //////////////////////////////////////////////////////////////////

    public void startPageClick(View v){
        Intent go_to_startPage;
        go_to_startPage = new Intent(ElseActivity.this, StartActivity.class);
        ElseActivity.this.startActivity(go_to_startPage);
        finish();
    }

    public void dogPageClick(View v){
        Intent go_to_dogPage;
        go_to_dogPage = new Intent(ElseActivity.this, DogActivity.class);
        ElseActivity.this.startActivity(go_to_dogPage);
        finish();
    }

    public void catPageClick(View v){
        Intent go_to_catPage;
        go_to_catPage = new Intent(ElseActivity.this, CatActivity.class);
        ElseActivity.this.startActivity(go_to_catPage);
        finish();
    }

    public void elsePageClick(View v){
        //本身是其他頁面，不需功能
    }

    public void accountPageClick(View v){
        Intent go_to_memberPage;
        go_to_memberPage = new Intent(ElseActivity.this, MemberActivity.class);
        ElseActivity.this.startActivity(go_to_memberPage);
        finish();
    }
}