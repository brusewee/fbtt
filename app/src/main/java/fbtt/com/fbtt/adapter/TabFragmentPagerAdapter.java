package fbtt.com.fbtt.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 */
public class TabFragmentPagerAdapter extends FragmentPagerAdapter {

    //继承FragmentPagerAdapter类 ,并自定义的构造器
    private List<Fragment> fragments;
    public TabFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments =fragments;
    }

    @Override
    public Fragment getItem(int position) {


        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
