package com.example.unsplash.Categries;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyFragmentAdapter2 extends FragmentStatePagerAdapter
{

    public MyFragmentAdapter2(@NonNull FragmentManager fm) {
        super(fm);
    }

    String data1;
    public void setBackground(String data)
    {
        data1=data;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        if(position==0)
        {
            WallpListFragment wallpListFragment=new WallpListFragment();
            wallpListFragment.setBackground(data1);
            return wallpListFragment;
        }
        else
        {
            LargeWallFragment largeWallFragment=new LargeWallFragment();

            return largeWallFragment;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Wallpapers";
        }
        else if (position == 1)
        {
            title = "News";
        }
        return title;
    }

}
