package com.bongn.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;
import com.bongn.qlib.update.UpdateHelper;
import com.bongn.qlib.utils.LogUtil;
import com.bongn.qlib.utils.task.TaskManager2;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends SherlockActivity {

    @InjectView(R.id.test_get)
    Button testGet;
    @InjectView(R.id.test_post)
    Button testPost;
    @InjectView(R.id.test_fresco)
    Button testFresco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);


    }

    private void checkUpdate(){
        /**
         {
         "appName": "应用名称",
         "packageName": "com.bongn.testapp",
         "versionCode": "105",
         "versionName": "1.0.5",
         "apkUrl": "apk的下载地址形如：http://xxxxx.com/xx.apk",
         "changeLog": "优化登录功能\n优化插件添加功能",
         "updateTips": "版本更新"
         }
         */
        UpdateHelper updateHelper = new UpdateHelper.Builder(this)
                .checkUrl("http://xxxx")
                .isAutoInstall(true) //设置为false需在下载完手动点击安装;默认值为true，下载后自动安装。
                .isHintNewVersion(false)
                .build();
        updateHelper.check();
    }


    @OnClick({R.id.test_get, R.id.test_post,R.id.test_fresco})
    public void testGet(Button button) {
        if (button.getId() == R.id.test_get) {
            String url = "http://219.148.31.135:8181/certhelper/phone/getNews.action?countPerpage=10&currentPage=1";
            TaskManager2.init(this, "请稍候").get(url, JSONObject.class, new TaskManager2.Callback<JSONObject>() {
                @Override
                public void onComplate(JSONObject result) {
                    LogUtil.i(result.toString());
                }
            });
        } else if (button.getId() == R.id.test_post) {
            String url = "http://219.148.31.135:8181/certhelper/phone/getNews.action";
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("countPerpage", 1);
            param.put("currentPage", 1);
            TaskManager2.init(this, "请稍候").post(url, param, JSONObject.class, new TaskManager2.Callback<JSONObject>() {
                @Override
                public void onComplate(JSONObject result) {
                    LogUtil.i(result.toString());
                }
            });
        }else if(button.getId() == R.id.test_fresco){
            startActivity(new Intent(this,TestFresco.class));
        }

    }

}
