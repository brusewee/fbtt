package fbtt.com.fbtt.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fbtt.com.fbtt.R;
import fbtt.com.fbtt.view.CircleImageView;

/**
 * Created by Administrator on 2016/5/23.
 */
public class MyFragment extends Fragment {

    @Bind(R.id.iv_my_shezhi)
    ImageView ivMyShezhi;
    @Bind(R.id.my_circle_view)
    CircleImageView myCircleView;
    @Bind(R.id.my_my_message)
    LinearLayout myMyMessage;
    @Bind(R.id.my_my_shouchang)
    LinearLayout myMyShouchang;
    @Bind(R.id.my_my_dingyue)
    LinearLayout myMyDingyue;
    @Bind(R.id.iv_my_huodong)
    ImageView ivMyHuodong;
    @Bind(R.id.ll_my_huodong)
    LinearLayout llMyHuodong;
    @Bind(R.id.ll_my_yejian)
    LinearLayout llMyYejian;
    @Bind(R.id.ll_my_shenliuliang)
    LinearLayout llMyShenliuliang;
    @Bind(R.id.my_zhouwei_deren)
    LinearLayout myZhouweiDeren;
    @Bind(R.id.ll_my_fangui)
    LinearLayout llMyFangui;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_my_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_my_shezhi, R.id.my_circle_view, R.id.my_my_message, R.id.my_my_shouchang, R.id.my_my_dingyue, R.id.iv_my_huodong, R.id.ll_my_huodong, R.id.ll_my_yejian, R.id.ll_my_shenliuliang, R.id.my_zhouwei_deren, R.id.ll_my_fangui})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_my_shezhi:

                break;
            case R.id.my_circle_view:

                break;
            case R.id.my_my_message:

                break;
            case R.id.my_my_shouchang:
                break;
            case R.id.my_my_dingyue:
                break;
            case R.id.iv_my_huodong:
                break;
            case R.id.ll_my_huodong:
                break;
            case R.id.ll_my_yejian:
                break;
            case R.id.ll_my_shenliuliang:
                break;
            case R.id.my_zhouwei_deren:
                break;
            case R.id.ll_my_fangui:
                break;
        }
    }
}
