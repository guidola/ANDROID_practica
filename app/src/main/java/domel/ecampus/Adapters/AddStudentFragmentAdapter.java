package domel.ecampus.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import domel.ecampus.Fragment.AddSubjectFirstStepFragment;
import domel.ecampus.Fragment.AddSubjectSecondStepFragment;
import domel.ecampus.Fragment.AddSubjectThirdStepFragment;

/**
 * Created by Guillermo on 16/5/16.
 */
public class AddStudentFragmentAdapter extends SmartFragmentStatePagerAdapter {

    private static int NUM_ITEMS = 3;

    public AddStudentFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new AddSubjectFirstStepFragment();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new AddSubjectSecondStepFragment();
            case 2: // Fragment # 1 - This will show SecondFragment
                return new AddSubjectThirdStepFragment();
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return "Paso " + position;
    }
}