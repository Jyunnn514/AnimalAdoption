package gjun.project.animaladoption;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class WelcomeActivity extends AppCompatActivity {

    private static int SPLASH_TIMEOUT = 6000; //2秒
    String data_path = "/data/data/gjun.project.animaladoption/animalAdoption.sqlite";  //sqlite位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(WelcomeActivity.this, UserActivity.class));

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File db_file = new File(data_path);
                        check_sqlite(db_file);
                    }
                }).start();

                finish();   //跳轉後刪除當前頁面，不然按上一頁會回來這裡

            }
        },SPLASH_TIMEOUT);
        setContentView(R.layout.activity_welcome);
    }

    //TODO 檢查sqlite檔案是否存在
    private void check_sqlite(File db_file){
        if(db_file.exists()){

            WelcomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(WelcomeActivity.this, "WELCOME", Toast.LENGTH_SHORT).show();
                }
            });
            update_sqlite();
        }else{
            WelcomeActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(WelcomeActivity.this, "您是第一次執行本程式吧!\n初始資料不存在\n現在為您下載", Toast.LENGTH_SHORT).show();
                }
            });
            sqlite_download("https://andoridtest-5999d.web.app/animalAdoption.sqlite");
        }
    }

    //TODO 下載預先做好的sqlite
    private void sqlite_download(String url_path){
        try {
            URL url = new URL(url_path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            InputStream is = con.getInputStream();
            ArrayList<Byte> big = new ArrayList<Byte>();
            byte[] small = new byte[1024];
            int how_many_byte = is.read(small);
            while(how_many_byte != -1){
                for(int i=0;i<how_many_byte;i++){
                    big.add(small[i]);
                }
                how_many_byte = is.read(small);
            }
            byte[] original = new byte[big.size()];
            for(int i=0;i<big.size();i++){
                original[i] = big.get(i);
            }
            new FileOutputStream(data_path).write(original);
            update_sqlite();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO sqlite檔案存在後，接著的動作
    private void update_sqlite(){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(data_path,null,SQLiteDatabase.OPEN_READWRITE);
        if(db != null) {
            Log.i("update_sqlite", "打開" + data_path + "成功");
            db.execSQL("DELETE FROM animal");   //刪除table-animal裡的所有資料，為了增加新資料進去
            getGovDoc();
        }else{
            Log.i("update_sqlite", "打開" + data_path + "失敗" );
            return;
        }
    }

    //TODO 抓政府資料(String)
    private void getGovDoc(){
        String json;
        try {
            String server = "https://data.coa.gov.tw/Service/OpenData/TransService.aspx?UnitId=QcbUEzN6E6DL";
            URL url = new URL(server);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            InputStream is = con.getInputStream();
            ArrayList<Byte> big = new ArrayList<Byte>();
            byte[] small = new byte[1024];
            int how_many_read = is.read(small);
            while(how_many_read != -1) {
                for(int i=0;i<how_many_read;i++) {
                    big.add(small[i]);
                }
                how_many_read = is.read(small);
            }
            byte[] data = new byte[big.size()];
            for(int i=0;i<big.size();i++) {
                data[i] = big.get(i);
            }
            json = new String(data);
            saveIntoSqlite(json);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO 把資料存進sqlite
    private void saveIntoSqlite(String str){
        byte[] picByte = new byte[0];
        SQLiteDatabase db;
        db = SQLiteDatabase.openDatabase(data_path,null,SQLiteDatabase.OPEN_READWRITE);
        try {
            JSONArray jsonArray = new JSONArray(str);
            for (int i = 0; i < 40; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //動物編號(動物自己唯一的ID)  animalID
                int govAnimalID = jsonObject.getInt("animal_id");
                //物種(貓or狗)  kind
                String govAnimalKind = jsonObject.getString("animal_kind");
                //所在地(台北、新北等)  area
                int govAnimalArea = jsonObject.getInt("animal_area_pkid");
                //性別  sex
                String govAnimalSex = jsonObject.getString("animal_sex");
                //體型(大中小)  bodytype
                String govAnimalBodyType = jsonObject.getString("animal_bodytype");
                //絕育  sterilization
                String govAnimalSterilization = jsonObject.getString("animal_sterilization");
                //疫苗  bacterin
                String govAnimalBacterin = jsonObject.getString("animal_bacterin");
                //動物狀態(開放or..)  status
                String govAnimalStatus = jsonObject.getString("animal_status");
                //建立時間  create
                String govAnimalCreateTime = jsonObject.getString("animal_createtime");
                //更新時間  update
                String govAnimalUpdateTime = jsonObject.getString("animal_update");
                //領養單位  shelter
                String govAnimalShelterName = jsonObject.getString("shelter_name");
                //領養地址  address
                String govAnimalShelterAddress = jsonObject.getString("shelter_address");
                //電話  tel
                String govAnimalShelterTel = jsonObject.getString("shelter_tel");
                //動物照片  file(還未決定要放網址還是下載轉binary)
                String animalPicture= jsonObject.getString("album_file");
                if(animalPicture != null) {
                    Bitmap bitmap = getBitmapFromURL(animalPicture);
                    if(bitmap!=null){
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG,0,baos);
                        picByte = baos.toByteArray();
                    }else{
                        picByte = null;
                    }
                }else{
                    picByte = null;
                }
                if(db != null) {
                    Log.i("saveIntoSqlite", "打開" + data_path + "成功");
                    ContentValues data = new ContentValues();
                    data.put("animalID", govAnimalID);
                    data.put("kind", govAnimalKind);
                    data.put("area", govAnimalArea);
                    data.put("sex", govAnimalSex);
                    data.put("bodytype", govAnimalBodyType);
                    data.put("sterilization", govAnimalSterilization);
                    data.put("bacterin", govAnimalBacterin);
                    data.put("status", govAnimalStatus);
                    data.put("createTime", govAnimalCreateTime);
                    data.put("updateTime", govAnimalUpdateTime);
                    data.put("shelter", govAnimalShelterName);
                    data.put("address", govAnimalShelterAddress);
                    data.put("tel", govAnimalShelterTel);
                    data.put("file", picByte);
                    long x = db.insert("animal",null,data);
                    if(x>0){
                        Log.i("SQLITE================>", "新增一筆資料成功");
                    }else{
                        Log.i("SQLITE================>", "新增一筆資料失敗" );
                    }
                    data.clear();
                }else{
                    Log.i("saveIntoSqlite", "打開" + data_path + "失敗" );
                    return;
                }
                //System.out.println(animalPicture);    //測試抓下來的資料正不正常用
            }
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("JsonArray出問題了");
        }
    }

    //TODO 從網路上下載圖片解析為Bitmap
    private Bitmap getBitmapFromURL(String src){
        try {
            URL url = new URL(src);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}