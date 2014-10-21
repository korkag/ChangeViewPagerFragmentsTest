package com.example.fragmenttest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.fragmenttest.R;

/**
 * The child fragment is no different than any other fragment other than it is now being maintained by
 * a child FragmentManager.
 */
public class TabFragment extends Fragment {

    public static final String POSITION_KEY = "FragmentPositionKey";
    private int position;
	private int colorId;
	private int counter = 0;


    public TabFragment(int position, int colorId) {
    	this.position = position;
    	this.colorId = colorId;
    }


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.tab_fragment, container, false);
        RelativeLayout rl = (RelativeLayout)root.findViewById(R.id.tab_fragment);
        rl.setBackgroundColor(getResources().getColor(colorId));
        
        final Button btn = (Button) root.findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				
				btn.setVisibility(View.GONE);
				FragmentManager childFragmentManager = getChildFragmentManager();
				addChildFragment(childFragmentManager);
			}
		});
        
        final Button button2 = (Button) root.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				
				counter++;
				button2.setText("Pressed " + counter);
			}
		});
        
        
        
        
        

        return root;
    }
	
	
    private void addChildFragment(FragmentManager fragmentManager) {
    	Fragment frag = new ChildFragment(0, fragmentManager);
		
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.frame_container, frag);
		transaction.addToBackStack(null).commit();
    }

//    @Override
//    public void onClick(View v) {
//        Toast.makeText(v.getContext(), "Clicked Position: " + position, Toast.LENGTH_LONG).show();
//    }
}
