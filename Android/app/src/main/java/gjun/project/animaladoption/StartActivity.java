package gjun.project.animaladoption;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void adoptinnotice(View view){
        Intent toNotice = new Intent(StartActivity.this,AdoptionNotice.class);
        StartActivity.this.startActivity(toNotice);
    }

    public void adoptSteps(View v){
        Intent tosteps = new Intent(StartActivity.this, AdoptSteps.class);
        StartActivity.this.startActivity(tosteps);
    }

    public void startPageClick(View v){

    }

    public void dogPageClick(View v){
        Intent go_to_dogPage;
        go_to_dogPage = new Intent(StartActivity.this, DogActivity.class);
        StartActivity.this.startActivity(go_to_dogPage);
        finish();
    }

    public void catPageClick(View v){
        Intent go_to_catPage;
        go_to_catPage = new Intent(StartActivity.this, CatActivity.class);
        StartActivity.this.startActivity(go_to_catPage);
        finish();
    }

    public void elsePageClick(View v){
        Intent go_to_elsePage;
        go_to_elsePage = new Intent(StartActivity.this, ElseActivity.class);
        StartActivity.this.startActivity(go_to_elsePage);
        finish();
    }

    public void accountPageClick(View v){
        Intent go_to_memberPage;
        go_to_memberPage = new Intent(StartActivity.this, MemberActivity.class);
        StartActivity.this.startActivity(go_to_memberPage);
        finish();
    }
}