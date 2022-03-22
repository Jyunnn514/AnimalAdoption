package gjun.project.animaladoption;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

public class CatActivity extends AppCompatActivity {

    ScrollView scrollView;
    String data_path = "/data/data/gjun.project.animaladoption/animalAdoption.sqlite";  //sqlite位置
    Dialog dialog;
    String shelterMap, addressMap, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        scrollView = findViewById(R.id.scrollviewID);

        createRow();
    }

    //  點列表上的領養單位會打開google map app
    public void clickShelter(View v){
        String url = "https://www.google.com/maps/search/" + shelterMap;
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
        startActivity(intent);
    }
    //  點列表上的領養地址會打開google map app
    public void clickAddress(View v){
        String url = "https://www.google.com/maps/search/" + addressMap;
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(url));
        startActivity(intent);
    }

    // 點列表上的電話號碼會打開電話
    public void clickCall(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void startPageClick(View v){
        Intent go_to_startPage;
        go_to_startPage = new Intent(CatActivity.this, StartActivity.class);
        CatActivity.this.startActivity(go_to_startPage);
        finish();
    }

    public void dogPageClick(View v){
        Intent go_to_dogPage;
        go_to_dogPage = new Intent(CatActivity.this, DogActivity.class);
        CatActivity.this.startActivity(go_to_dogPage);
        finish();
    }

    public void catPageClick(View v){
        //本身是貓頁，不需功能
    }

    public void elsePageClick(View v){
        Intent go_to_elsePage;
        go_to_elsePage = new Intent(CatActivity.this, ElseActivity.class);
        CatActivity.this.startActivity(go_to_elsePage);
        finish();
    }

    public void accountPageClick(View v){
        Intent go_to_memberPage;
        go_to_memberPage = new Intent(CatActivity.this, MemberActivity.class);
        CatActivity.this.startActivity(go_to_memberPage);
        finish();
    }

    //TODO 表格
    public void createRow(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                //將原有的舊資料清除
                LinearLayout container = (LinearLayout) scrollView.getChildAt(0);
                //container.removeAllViews();
                SQLiteDatabase db;
                db = SQLiteDatabase.openDatabase(data_path,null,SQLiteDatabase.OPEN_READONLY);
                if(db != null){
                    Log.i("createRow", "打開" + data_path + "成功");
                    LayoutInflater inflater = CatActivity.this.getLayoutInflater();
                    Cursor table = db.rawQuery("select * from animal where kind='貓'", null);
                    while(table.moveToNext()){
                        ConstraintLayout row = (ConstraintLayout) inflater.inflate(R.layout.row,null);
                        ConstraintLayout inner_row = row.findViewById(R.id.clickResponderID);

                        int idIn = table.getInt(1); //編號
                        String sexIn = table.getString(4);  //性別
                        String sizeIn = table.getString(5); //體型
                        String sterilizationIn = table.getString(6);    //絕育
                        String bacterinIn = table.getString(7); // 疫苗
                        String statusIn = table.getString(8);   //狀態
                        String createTimeIn = table.getString(9);//建立時間
                        String updateTimeIn = table.getString(10);//更新時間
                        String shelterIn = table.getString(11);//領養單位
                        String addressIn = table.getString(12);//領養地址
                        String telIn = table.getString(13);//電話
                        byte[] picIn = table.getBlob(14);//圖片

                        inner_row.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog = new Dialog(CatActivity.this);
                                dialog.setContentView(R.layout.dialog);

                                TextView innerID = dialog.findViewById(R.id.innerIDID);
                                TextView innerSex = dialog.findViewById(R.id.innerSexID);
                                TextView innerSize = dialog.findViewById(R.id.innerSizeID);
                                TextView innerSterilization = dialog.findViewById(R.id.innerSterilizationID);
                                TextView innerBacterin = dialog.findViewById(R.id.innerBacterinID);
                                TextView innerStatus = dialog.findViewById(R.id.innerStatusID);
                                TextView innerCreateTime = dialog.findViewById(R.id.innerCreateTimeID);
                                TextView innerUpdateTime = dialog.findViewById(R.id.innerUpdateTimeID);
                                TextView innerShelter = dialog.findViewById(R.id.innerShelterID);
                                TextView innerAddress = dialog.findViewById(R.id.innerAddressID);
                                TextView innerTel = dialog.findViewById(R.id.innerTelID);
                                ImageView innerPic = dialog.findViewById(R.id.innerPicID);

                                shelterMap = shelterIn;
                                addressMap = addressIn;
                                phoneNumber = telIn;

                                //動物ID
                                innerID.setText(idIn+" ");
                                //性別
                                if(sexIn.equals("M")){
                                    innerSex.setText("公");
                                }else if(sexIn.equals("F")){
                                    innerSex.setText("母");
                                }else if(sexIn.equals("N")){
                                    innerSex.setText("未知");
                                }
                                //體型
                                if(sizeIn.equals("SMALL")){
                                    innerSize.setText("大型");
                                }else if(sizeIn.equals("MEDIUM")){
                                    innerSize.setText("中型");
                                }else if(sizeIn.equals("BIG")){
                                    innerSize.setText("小型");
                                }
                                //節育
                                if(sterilizationIn.equals("T")){
                                    innerSterilization.setText("已結紮");
                                }else if(sterilizationIn.equals("F")){
                                    innerSterilization.setText("未結紮");
                                }else if(sterilizationIn.equals("N")){
                                    innerSterilization.setText("未知");
                                }
                                //疫苗
                                if(bacterinIn.equals("T")){
                                    innerBacterin.setText("已打疫苗");
                                }else if(bacterinIn.equals("F")){
                                    innerBacterin.setText("未打疫苗");
                                }else if(bacterinIn.equals("N")){
                                    innerBacterin.setText("未知");
                                }
                                //狀態
                                if(statusIn.equals("NONE")){
                                    innerStatus.setText("未公告");
                                }else if(statusIn.equals("OPEN")){
                                    innerStatus.setText("開放認養");
                                }else if(statusIn.equals("ADOPTED")){
                                    innerStatus.setText("已認養");
                                }else if(statusIn.equals("OTHER")){
                                    innerStatus.setText("其他");
                                }else if(statusIn.equals("DEAD")){
                                    innerStatus.setText("死亡");
                                }
                                //建立時間
                                innerCreateTime.setText(createTimeIn);
                                //更新時間
                                innerUpdateTime.setText(updateTimeIn);
                                //動物所屬收容所名稱
                                innerShelter.setText(shelterIn);
                                //地址
                                innerAddress.setText(addressIn);
                                //電話
                                innerTel.setText(telIn);
                                //照片
                                if(picIn != null){
                                    Bitmap p = BitmapFactory.decodeByteArray(picIn,0,picIn.length);
                                    innerPic.setImageBitmap(p);
                                }else{
                                    innerPic.setImageResource(R.drawable.noimage);
                                }

                                dialog.show();
                            }
                        });
                        TextView sex = row.findViewById(R.id.sexID);
                        TextView area = row.findViewById(R.id.areaID);
                        TextView sterilization = row.findViewById(R.id.sterilizationID);
                        TextView bacterin = row.findViewById(R.id.bacterinID);
                        ImageView picture = row.findViewById(R.id.pictureID);
                        ImageView sexPicture = row.findViewById(R.id.sexPictureID);
                        //性別判定
                        if(sexIn.equals("M")){
                            sex.setText("性別：公");
                            sexPicture.setImageResource(R.drawable.male32);
                        }else if(sexIn.equals("F")){
                            sex.setText("性別：母");
                            sexPicture.setImageResource(R.drawable.female32);
                        }else if(sexIn.equals("N")){
                            sex.setText("性別未知");
                            sexPicture.setImageResource(R.drawable.sex32);
                        }
                        //所在地
                        int areaS = table.getInt(3);
                        switch (areaS){
                            case 0:
                                area.setText("未知");
                                break;
                            case 2:
                                area.setText("臺北市");
                                break;
                            case 3:
                                area.setText("新北市");
                                break;
                            case 4:
                                area.setText("基隆市");
                                break;
                            case 5:
                                area.setText("宜蘭縣");
                                break;
                            case 6:
                                area.setText("桃園縣");
                                break;
                            case 7:
                                area.setText("新竹縣");
                                break;
                            case 8:
                                area.setText("新竹市");
                                break;
                            case 9:
                                area.setText("苗栗縣");
                                break;
                            case 10:
                                area.setText("臺中市");
                                break;
                            case 11:
                                area.setText("彰化縣");
                                break;
                            case 12:
                                area.setText("南投縣");
                                break;
                            case 13:
                                area.setText("雲林縣");
                                break;
                            case 14:
                                area.setText("嘉義縣");
                                break;
                            case 15:
                                area.setText("嘉義市");
                                break;
                            case 16:
                                area.setText("臺南市");
                                break;
                            case 17:
                                area.setText("高雄市");
                                break;
                            case 18:
                                area.setText("屏東縣");
                                break;
                            case 19:
                                area.setText("花蓮縣");
                                break;
                            case 20:
                                area.setText("臺東縣");
                                break;
                            case 21:
                                area.setText("澎湖縣");
                                break;
                            case 22:
                                area.setText("金門縣");
                                break;
                            case 23:
                                area.setText("連江縣");
                                break;
                        }
                        //sterilization(結紮)
                        if(sterilizationIn.equals("T")){
                            sterilization.setText("已結紮");
                        }else if(sterilizationIn.equals("F")){
                            sterilization.setText("未結紮");
                        }else if(sterilizationIn.equals("N")){
                            sterilization.setText("未知");
                        }
                        //bacterin(打狂犬疫苗)
                        if(bacterinIn.equals("T")){
                            bacterin.setText("已打疫苗");
                        }else if(bacterinIn.equals("F")){
                            bacterin.setText("未打疫苗");
                        }else if(bacterinIn.equals("N")){
                            bacterin.setText("未知");
                        }
                        //圖片
                        if(picIn != null){
                            final BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = 8;
                            Bitmap p = BitmapFactory.decodeByteArray(picIn,0,picIn.length,options);
                            picture.setImageBitmap(p);
                        }else{
                            picture.setImageResource(R.drawable.noimage);
                        }
                        CatActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                container.addView(row);     //在container裡增加做好的row
                            }
                        });
                    }
                    db.close();
                }else{
                    Log.i("createRow","打開" + data_path + "失敗");
                    return;
                }
            }
        }).start();
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