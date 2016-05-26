package fbtt.com.fbtt.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import fbtt.com.fbtt.FBApplication;
import fbtt.com.fbtt.R;
import fbtt.com.fbtt.adapter.TabFragmentPagerAdapter;
import fbtt.com.fbtt.constants.Constants;
import fbtt.com.fbtt.fragment.FindFragment;
import fbtt.com.fbtt.fragment.MainFragment;
import fbtt.com.fbtt.fragment.MyFragment;
import fbtt.com.fbtt.utils.CustomTask;
import fbtt.com.fbtt.utils.DebugUtils;
import fbtt.com.fbtt.utils.StreamUtils;
import fbtt.com.fbtt.utils.UtilityUtils;

public class MainActivity extends FragmentActivity implements View.OnClickListener {


    private String TAG = "MainActivity";

    @Bind(R.id.tab_main_viewpager)
    ViewPager mViewPager;
    @Bind(R.id.ib_tab_main_icon_grey)
    ImageButton ib_tab_main_icon_grey;
    @Bind(R.id.tv_tab_main_icon_grey)
    TextView tv_tab_main_icon_grey;
    @Bind(R.id.id_tab_main)
    LinearLayout id_tab_main;
    @Bind(R.id.ib_tab_find_icon_grey)
    ImageButton ib_tab_find_icon_grey;
    @Bind(R.id.tv_tab_find_icon_grey)
    TextView tv_tab_find_icon_grey;
    @Bind(R.id.id_tab_find)
    LinearLayout id_tab_find;
    @Bind(R.id.ib_tab_my_icon_grey)
    ImageButton ib_tab_my_icon_grey;
    @Bind(R.id.tv_tab_my_icon_grey)
    TextView tv_tab_my_icon_grey;
    @Bind(R.id.id_tab_my)
    LinearLayout id_tab_my;
    @Bind(R.id.navigation_view)
    NavigationView navigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private Locale myLocale;

    private List<Fragment> mFragments;

    private TabFragmentPagerAdapter mAdapter;
    int shouyeRed = 0;
    int shouyeGrep = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main_viewpager);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(MainActivity.this, Color.RED);
        FBApplication.register(this);
        if (UtilityUtils.isNewWorkConnected(this)){
            getLocationInformation();

        }
        getLocationInformation();
        shouyeRed = getResources().getColor(R.color.shouye_tab_red);
        shouyeGrep = getResources().getColor(R.color.shouye_tab_grep);
        initView();
        initClickListener();
        setSelect(0);
        createSlidingMenu();
        initDrawer();
    }



    private String iconUrl;

    private Handler GetLocationHandler=new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Constants.LOCATION_LIST_FLAGE:
                    DebugUtils.i(TAG, "獲取地址的信息：" + msg.obj.toString());

                        try {
                            if (!TextUtils.isEmpty(msg.obj.toString())) {
                               JSONObject json = new JSONObject(msg.obj.toString());
                                boolean success=json.optBoolean("success");

                                if (success){
                                    JSONArray locationArrayList=json.optJSONArray("data");
                                    for(int i=0 ; i < 3 ;i++){
                                        JSONObject myjObject = locationArrayList.getJSONObject(i);
                                        iconUrl=myjObject.optString("iconUrl");
                                        //StringBuffer stringBuffer = new StringBuffer();
                                       // stringBuffer.append(imageUrl);
                                       // stringBuffer.deleteCharAt(0);
                                        //String imageUrlStr=stringBuffer.toString();
                                        //String imageUrlFull = (Constants.ISTEST ? Constants.ClientHTTP : Constants.HOST_ONLINE_HTTPS) + imageUrlStr;
                                        DebugUtils.i(TAG, "iconUrlFull——地址" + iconUrl);
                                        String name=myjObject.optString("name");
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Bitmap locationIcon=StreamUtils.getBitmapFromUrl(iconUrl);
                                                Message mess = Message.obtain();
                                                mess.what =Constants.LOCATION_LIST_FLAGE_ICON;
                                                mess.obj=locationIcon;
                                                sendMessage(mess);
                                            }
                                        }).start();

                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    break;

                case Constants.LOCATION_LIST_FLAGE_ICON:
                    Bitmap locationIcon= (Bitmap) msg.obj;
                    byte[] bytes= StreamUtils.bitmap2Bytes(locationIcon);
                    DebugUtils.i(TAG,"获取的地点信息图"+bytes.toString());
                    break;
            }
        }

    };

    private void getLocationInformation() {

        CustomTask task = new CustomTask(GetLocationHandler, Constants.LOCATION_LIST_FLAGE,
                Constants.LOCATION_LIST, true, null, Constants.UTF8);
        task.execute();



    }

    private void initDrawer() {

        setNavigationViewItemClickListener();
        View headerView = navigationView.getHeaderView(0);
        ImageView ivAreaChoose= (ImageView) headerView.findViewById(R.id.iv_area_choose_drawer);
        navigationView.setItemIconTintList(null);
//        navigationView.setItemTextColor(new ColorStateList(St));
        ivAreaChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "區域選擇", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setNavigationViewItemClickListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_item_hongkong:
//                        switchFragment("MainFragment");
                        Toast.makeText(MainActivity.this, "香港", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_item_taiwan:
//                        switchFragment("BlogFragment");
                        Toast.makeText(MainActivity.this, "台灣", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_item_mainland:
//                        switchFragment("AboutFragment");
                        Toast.makeText(MainActivity.this, "大陸", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_item_international:
//                        switchFragment("AboutFragment");
                        Toast.makeText(MainActivity.this, "國際", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                item.setChecked(true);
                drawerLayout.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }



    private void createSlidingMenu() {


    }

    private void initClickListener() {

        id_tab_main.setOnClickListener(this);
        id_tab_find.setOnClickListener(this);
        id_tab_my.setOnClickListener(this);

    }


    private void initView() {

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


    public void changeLang(String lang) {

        if (lang.equalsIgnoreCase("")) {

            return;
        }

        myLocale = new Locale(lang);
        saveLocale(lang);
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
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
        ButterKnife.unbind(this);

    }


}
