package com.example.demo2.ui.dashboard;


import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.demo2.R;
import com.example.demo2.ui.GetedHardwareData;



//进度条绘制
public class ProgressBarViewActivity extends Activity  {
    private ProgressBar progesss;
    private TextView progesssValue;
    private LinearLayout full;
    TextView slipTextResult;
    TextView text1;

    ImageView bloodback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar);

        progesss = (ProgressBar) findViewById(R.id.progesss1);
        progesssValue = (TextView) findViewById(R.id.progesss_value1);
        full = (LinearLayout) findViewById(R.id.full);

        slipTextResult = findViewById(R.id.tv_showText);
        slipTextResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        int numBloodOxygen = GetedHardwareData.BLOODOXYGEN;
        if (numBloodOxygen >= 95) {
            slipTextResult.setText("血氧饱和度："+numBloodOxygen+"%。一切正常！");
        } else if (numBloodOxygen >= 90 && numBloodOxygen < 95) {
            slipTextResult.setText("血氧饱和度："+numBloodOxygen+"%。为供氧不足。");
        } else if (numBloodOxygen < 90 && numBloodOxygen >= 85) {
            slipTextResult.setText("血氧饱和度："+numBloodOxygen+"%。为轻度低氧血氧症，应减少活动、适当休息。");
        } else if (numBloodOxygen >= 80 && numBloodOxygen < 85) {
            slipTextResult.setText("血氧饱和度：" + numBloodOxygen + "%。为中度低氧血氧症，应适量吸氧，服用抗缺氧药物。");
        } else if (numBloodOxygen < 80) {
            slipTextResult.setText("血氧饱和度：" + numBloodOxygen + "%。为重度缺氧，停止一切活动，立刻及时就医。");
        }

        text1 = findViewById(R.id.tv_text1);
        text1.setMovementMethod(ScrollingMovementMethod.getInstance());





        bloodback = findViewById(R.id.iv_bloodoxygen_back);
        bloodback.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             ProgressBarViewActivity.this.finish();
                                         }
                                     });

        initview();
    }

    private void initview() {

        progesss.setProgress(GetedHardwareData.BLOODOXYGEN);
        progesssValue.setText(new StringBuffer().append(progesss.getProgress()).append("%"));

        setPosWay1();


    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            setPos();
        }
    }
    private void setPosWay1() {
        progesssValue.post(new Runnable() {
            @Override
            public void run() {
                setPos();
            }
        });
    }

    /**
     * 设置进度显示在对应的位置
     */
    public void setPos() {
        int w = getWindowManager().getDefaultDisplay().getWidth();
        Log.e("w=====", "" + w);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) progesssValue.getLayoutParams();
        int pro = progesss.getProgress();
        int tW = progesssValue.getWidth();
        if (w * pro / 100 + tW * 0.3 > w) {
            params.leftMargin = (int) (w - tW * 1.1);
        } else if (w * pro / 100 < tW * 0.7) {
            params.leftMargin = 0;
        } else {
            params.leftMargin = (int) (w * pro / 100 - tW * 0.7);
        }
        progesssValue.setLayoutParams(params);

    }
}
