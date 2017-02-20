package cc.shawn.zzj.module;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.githang.statusbar.StatusBarCompat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.shawn.zzj.R;
import cc.shawn.zzj.base.BaseActivity;
import cc.shawn.zzj.base.BaseFragment;
import cc.shawn.zzj.module.home.HomeContract;
import cc.shawn.zzj.module.home.HomeFragment;
import cc.shawn.zzj.module.me.MeFragment;
import cc.shawn.zzj.module.message.MessageFragment;
import cc.shawn.zzj.module.news.NewsFragment;
import cc.shawn.zzj.module.social.SocialFragment;
import cc.shawn.zzj.util.DebugLog;
import cc.shawn.zzj.util.ViewUtils;

public class MainActivity extends BaseActivity {

    @BindView(R.id.content)View content;
    @BindView(R.id.bottom_nav)
    RadioGroup bottomNav;
    FragmentManager fragmentManager;
    BaseFragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.colorPrimary));
        fragmentManager = getSupportFragmentManager();


        currentFragment = (BaseFragment) fragmentManager.findFragmentById(R.id.content);
        if (currentFragment == null) {
            currentFragment = ViewUtils.createFragment(HomeFragment.class);
            fragmentManager.beginTransaction().add(R.id.content, currentFragment).commit();
        }
        FragmentTransaction trans = fragmentManager.beginTransaction();
        if (null != savedInstanceState) {
            List<Fragment> fragments = fragmentManager.getFragments();
            for (int i = 0; i < fragments.size(); i++) {
                trans.hide(fragments.get(i));
            }
        }
        trans.show(currentFragment).commitAllowingStateLoss();
        bottomNav.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Class<?> clazz = null;
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.home:
                        clazz = HomeFragment.class;
                    break;
                    case R.id.social:
                        clazz = SocialFragment.class;
                        break;
                    case R.id.news:
                        clazz = NewsFragment.class;
                        break;
                    case R.id.message:
                        clazz = MessageFragment.class;
                        break;
                    case R.id.me:
                        clazz = MeFragment.class;
                        break;

                }
                switchFragment(clazz);
            }
        });


    }
    private void switchFragment(Class<?> clazz) {
        if (clazz == null) return;

        BaseFragment to = ViewUtils.createFragment(clazz);
        if (to.isAdded()) {
            fragmentManager.beginTransaction().hide(currentFragment).show(to).commit();
        } else {
            to.setUserVisibleHint(true);
            fragmentManager.beginTransaction().hide(currentFragment).add(R.id.content, to).commit();
        }
        currentFragment = to;
    }
    public void showNav(){
        bottomNav.setVisibility(View.VISIBLE);
    }
    public void hideNav(){
        bottomNav.setVisibility(View.GONE);
    }
}
