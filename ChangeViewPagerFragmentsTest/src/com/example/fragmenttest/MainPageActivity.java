package com.example.fragmenttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fragmenttest.fragment.ChildFragment;
import com.example.fragmenttest.fragment.TabFragment;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

public class MainPageActivity extends FragmentActivity {

    private ViewPager mPager;
	private TabFragmentAdapter mAdapter;
	private TabPageIndicator mIndicator;


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_page_main);
        
        mAdapter = new TabFragmentAdapter(getSupportFragmentManager());

		mPager = (ViewPager)findViewById(R.id.pager);
	    mPager.setAdapter(mAdapter);
	    mPager.setOffscreenPageLimit(5); //  predefine number of "cached" pages
	
	    mIndicator = (TabPageIndicator)findViewById(R.id.indicator);
	    mIndicator.setTabIconLocation (TabPageIndicator.LOCATION_UP);
	    mIndicator.setViewPager(mPager);

	    
	  //We set this on the indicator, NOT the pager
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                Toast.makeText(MainPageActivity.this, "Changed to page " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        
    }
    
    public void onBackPressed() {
		
		 final Button btn = (Button) findViewById(R.id.button1);
    	 btn.setVisibility(View.VISIBLE);
    	 
    	// We retrieve the fragment manager of the activity
	    FragmentManager frgmtManager = getSupportFragmentManager();
	    
	    // We retrieve the fragment container showed right now
	    // The viewpager assigns tags to fragment automatically like this
	    // mPager is our ViewPager instance
	    Fragment fragment = frgmtManager.findFragmentByTag("android:switcher:" + mPager.getId() + ":" + mPager.getCurrentItem());

	    // And thanks to the fragment container, we retrieve its child fragment manager
	    // holding our fragment in the back stack
	    FragmentManager childFragmentManager = fragment.getChildFragmentManager();

	    // And here we go, if the back stack is empty, we let the back button doing its job
	    // Otherwise, we show the last entry in the back stack (our FragmentToShow) 
	    if(childFragmentManager.getBackStackEntryCount() == 0){
	        super.onBackPressed();
	    } else {
	        childFragmentManager.popBackStack();
	    }
	    
    	    

	}
    
    protected void onResume() {
    	super.onResume();
    	
    }
    
    
    private void addFragment(FragmentManager fragmentManager, int position) {
    	Fragment frag = new ChildFragment(position, fragmentManager);
		
		fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.frame1, frag);
		transaction.addToBackStack(null).commit();
    }
    
    
    ///////////////////////
    
    public class TabFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter{


    	public static final int TAB_1 = 0;
    	public static final int TAB_2 = 1;

    	private  final String[] CONTENT = new String[] { "Tab 1", "Tab 2"};
    	 
    	 private FragmentManager fragMan;
    	 
    	 private final int[] ICONS = new int[] {
             R.drawable.ic_launcher,
             R.drawable.ic_launcher,
    	 };
    	 
    	 
//    	private MainActivity	mActivity;

//    	private OnTabListener	mOnTabItemClickListener;
//    	 /**
//         * Interface definition for a callback to be invoked when a tab is clicked.
//         */
//        public interface OnTabListener {
//            /**
//             * Called when a tab item has been clicked.
//             *
//             * @param position One of {@link #TAB_POSITION_HOME}, {@link #TAB_POSITION_POPULAR}, {@link #TAB_POSITION_CLIP_TV}, {#link #PAGE_TV_SHOWS} or , {#link #PAGE_PROFILE}
//             */
//            Fragment onTabItemClick(int position);
//        }
    	

        public TabFragmentAdapter(FragmentManager fm) {
            super(fm);
            
            fragMan = fm;
        }
        
        @Override
        public int getIconResId(int index) {
            return ICONS[index];
        }

          @Override
        public Fragment getItem(int position) 
        {
        	  switch (position)
        	  {
        	  	case TabFragmentAdapter.TAB_1:
        	  		return new TabFragment(position, R.color.red);

        	  	case TabFragmentAdapter.TAB_2:
        	  		return new TabFragment(position, R.color.green);
        	  }
        	  
        	  return null;
        }
          
          
          @Override
        public int getCount() {
            return CONTENT.length;
        }
        
          @Override
        public CharSequence getPageTitle(int position){
        	return CONTENT[position % CONTENT.length];
        }

    }

}
