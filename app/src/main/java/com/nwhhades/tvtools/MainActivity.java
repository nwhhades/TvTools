package com.nwhhades.tvtools;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.nwhhades.common.base.NetBaseActivity;
import com.nwhhades.common.glide.GlideUtils;
import com.nwhhades.common.view.TvSeekBar;
import com.nwhhades.common.view.TvSpinner;
import com.nwhhades.tvtools.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends NetBaseActivity<ActivityMainBinding> {

    private static final String TAG = "MainActivity";

    @Override
    protected ActivityMainBinding getBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected boolean enableBg() {
        return false;
    }

    @Override
    protected boolean enableLoadingView() {
        return true;
    }

    @Override
    protected void init() {

        binding.tvv.create();
        binding.tvv.setTitle("sfadfasfasdfa");
        //binding.tvv.play("http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4", false);

        binding.tvv.play("http://hwrr.jx.chinamobile.com:8080/PLTV/88888888/224/3221225619/index.m3u8", false);


        GlideUtils.loadRoundImg("https://img0.baidu.com/it/u=981218435,2998857702&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1678640400&t=acc34416f4b83e520be5437a07b8e72e", binding.ivBg, this);

        initTvSpinner();
        binding.tv.setText("ssssss");
        binding.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tvSpinner.show();

                //SettingsApi settingsApi = new SettingsApi();
                //settingsApi.get(MainActivity.this, MainActivity.this, null, 0);

                binding.tvv.resume();
            }
        });
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideLoadingView();

                binding.tvv.getPlayerView().replay();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.tvv.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.tvv.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.tvv.destroy();
    }

    private TvSpinner tvSpinner;

    private void initTvSpinner() {
        if (tvSpinner == null) {
            List<String> list = new ArrayList<>();
            list.add("选项一");
            list.add("选项二");
            list.add("选项三");
            list.add("选项四");
            list.add("选项五");

            tvSpinner = new TvSpinner(this, "标题", list, 3);
            tvSpinner.setOnSelectItemListener(new TvSpinner.OnSelectItemListener() {
                @Override
                public void onSelectItem(String item, int index) {
                    Log.d(TAG, "onSelectItem: " + item + " - " + index);
                }
            });
        }
    }

    @Override
    protected void preInit() {
        super.preInit();
        setBgUrl("http://www.bing.com/th?id=OHR.YuanyangChina_ZH-CN7360249295_1920x1080.jpg&rf=LaDigue_1920x1080.jpg");
    }

}