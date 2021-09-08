package com.example.unsplash;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class MyFragmentAdapter extends FragmentStatePagerAdapter
{
    public MyFragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
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
            title = "Categories";
        }
        return title;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position==0)
        {
            return new WallpaperFragment();
        }
        else
            return new CategoriesFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
