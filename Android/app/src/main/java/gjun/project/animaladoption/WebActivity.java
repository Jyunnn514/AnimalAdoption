package gjun.project.animaladoption;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        WebView webView = findViewById(R.id.webViewID);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.tidf.org.tw/zh-hant/films/252");
    }

    public void startPageClick(View v){
        Intent go_to_startPage;
        go_to_startPage = new Intent(WebActivity.this, StartActivity.class);
        WebActivity.this.startActivity(go_to_startPage);
        finish();
    }

    public void dogPageClick(View v){
        Intent go_to_dogPage;
        go_to_dogPage = new Intent(WebActivity.this, DogActivity.class);
        WebActivity.this.startActivity(go_to_dogPage);
        finish();
    }

    public void catPageClick(View v){
        Intent go_to_catPage;
        go_to_catPage = new Intent(WebActivity.this, CatActivity.class);
        WebActivity.this.startActivity(go_to_catPage);
        finish();
    }

    public void elsePageClick(View v){
        Intent go_to_elsePage;
        go_to_elsePage = new Intent(WebActivity.this, ElseActivity.class);
        WebActivity.this.startActivity(go_to_elsePage);
        finish();
    }

    public void accountPageClick(View v){
        Intent go_to_memberPage;
        go_to_memberPage = new Intent(WebActivity.this, MemberActivity.class);
        WebActivity.this.startActivity(go_to_memberPage);
        finish();
    }
}