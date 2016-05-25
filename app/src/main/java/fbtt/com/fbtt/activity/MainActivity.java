package fbtt.com.fbtt.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import fbtt.com.fbtt.FBApplication;
import fbtt.com.fbtt.R;
import fbtt.com.fbtt.adapter.TabFragmentPagerAdapter;
import fbtt.com.fbtt.fragment.FindFragment;
import fbtt.com.fbtt.fragment.MainFragment;
import fbtt.com.fbtt.fragment.MyFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private Locale myLocale;

    private LinearLayout id_tab_main;
    private LinearLayout id_tab_find;
    private LinearLayout id_tab_my;
    private ImageButton ib_tab_main_icon_grey;
    private ImageButton ib_tab_find_icon_grey;
    private ImageButton ib_tab_my_icon_grey;
    private TextView tv_tab_main_icon_grey;
    private TextView tv_tab_find_icon_grey;
    private TextView tv_tab_my_icon_grey;
    private ViewPager mViewPager;

    private List<Fragment> mFragments;

    private TabFragmentPagerAdapter mAdapter;
    int shouyeRed=0;
    int shouyeGrep=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main_viewpager);
        FBApplication.register(this);
        shouyeRed=getResources().getColor(R.color.shouye_tab_red);
        shouyeGrep=getResources().getColor(R.color.shouye_tab_grep);
        initView();
        initClickListener();
        setSelect(0);
        createSlidingMenu();
    }

    private void createSlidingMenu() {




    }

    private void initClickListener() {

        id_tab_main.setOnClickListener(this);
        id_tab_find.setOnClickListener(this);
        id_tab_my.setOnClickListener(this);

    }


    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.tab_main_viewpager);
        id_tab_main= (LinearLayout) findViewById(R.id.id_tab_main);
        id_tab_find= (LinearLayout) findViewById(R.id.id_tab_find);
        id_tab_my= (LinearLayout) findViewById(R.id.id_tab_my);
        ib_tab_main_icon_grey= (ImageButton) findViewById(R.id.ib_tab_main_icon_grey);
        ib_tab_find_icon_grey= (ImageButton) findViewById(R.id.ib_tab_find_icon_grey);
        ib_tab_my_icon_grey= (ImageButton) findViewById(R.id.ib_tab_my_icon_grey);
        tv_tab_main_icon_grey= (TextView) findViewById(R.id.tv_tab_main_icon_grey);
        tv_tab_find_icon_grey=(TextView) findViewById(R.id.tv_tab_find_icon_grey);
        tv_tab_my_icon_grey=(TextView) findViewById(R.id.tv_tab_my_icon_grey);
        mFragments = new ArrayList<Fragment>();
        Fragment mTab_01 = new MainFragment();
        Fragment mTab_02 = new FindFragment();
        Fragment mTab_03 = new MyFragment();
        mFragments.add(mTab_01);
        mFragments.add(mTab_02);
        mFragments.add(mTab_03);
        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mAdapter);

        //设置滑动监听器

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            //滑动时 改变图标状态
            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();
                initTabImage();
                switch (currentItem) {
                    case 0:
                        ib_tab_main_icon_grey.setImageResource(R.drawable.tab_main_icon_red);
                        tv_tab_main_icon_grey.setTextColor(shouyeRed);
                        break;
                    case 1:
                        ib_tab_find_icon_grey.setImageResource(R.drawable.tab_find_icon_red);
                        tv_tab_find_icon_grey.setTextColor(shouyeRed);
                        break;
                    case 2:
                        ib_tab_my_icon_grey.setImageResource(R.drawable.tab_my_icon_red);
                        tv_tab_my_icon_grey.setTextColor(shouyeRed);
                        break;

                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //初始的图标状态(滑动和点击事件改变的时候都要初始化)
    private void initTabImage() {
        ib_tab_main_icon_grey.setImageResource(R.drawable.tab_main_icon_grey);
        tv_tab_main_icon_grey.setTextColor(shouyeGrep);
        ib_tab_find_icon_grey.setImageResource(R.drawable.tab_find_icon_grey);
        tv_tab_find_icon_grey.setTextColor(shouyeGrep);
        ib_tab_my_icon_grey.setImageResource(R.drawable.tab_my_icon_grey);
        tv_tab_my_icon_grey.setTextColor(shouyeGrep);
    }




    public void changeLang(String lang){

        if (lang.equalsIgnoreCase("")){

            return;
        }

        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        Configuration config=new Configuration();
        config.locale=myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        updateTexts();
    }


    public void saveLocale(String lang) {
        String langPref = "Language";
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(langPref, lang);
        editor.commit();
    }



    private void updateTexts() {


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_tab_main:
                //注意上面修改的只是图标的状态,还要修改相对应的fragment;
                setSelect(0);
                break;
            case R.id.id_tab_find:
                setSelect(1);
                break;
            case R.id.id_tab_my:
                setSelect(2);
                break;
        }

    }

    //设置将点击的那个图标为亮色,切换内容区域
    private void setSelect(int i) {
        initTabImage();
        switch (i) {
            case 0:
                ib_tab_main_icon_grey.setImageResource(R.drawable.tab_main_icon_red);
                tv_tab_main_icon_grey.setTextColor(shouyeRed);
                break;
            case 1:
                ib_tab_find_icon_grey.setImageResource(R.drawable.tab_find_icon_red);
                tv_tab_find_icon_grey.setTextColor(shouyeRed);
                break;
            case 2:
                ib_tab_my_icon_grey.setImageResource(R.drawable.tab_my_icon_red);
                tv_tab_my_icon_grey.setTextColor(shouyeRed);
                break;
            default:
                break;
        }
        mViewPager.setCurrentItem(i);
    }

    long waitTime = 2000;
    long touchTime = 0;

    @Override
    public void onBackPressed() {

        long currentTime = System.currentTimeMillis();
        if ((currentTime - touchTime) >= waitTime) {
            Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            touchTime = currentTime;
        } else {
            FBApplication.finishAllActivity();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FBApplication.unRegister(this);

    }
}
