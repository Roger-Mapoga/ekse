package co.za.gmapssolutions.ekse;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapterClass extends FragmentStatePagerAdapter {

    int TabCount;

    public FragmentAdapterClass(FragmentManager fragmentManager, int CountTabs) {

        super(fragmentManager);

        this.TabCount = CountTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab_1_Activity tab1 = new Tab_1_Activity();
                return tab1;

            case 1:
                Tab_2_Activity tab2 = new Tab_2_Activity();
                return tab2;

            case 2:
                Tab_3_Activity tab3 = new Tab_3_Activity();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TabCount;
    }
}