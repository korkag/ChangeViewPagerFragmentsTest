package com.example.fragmenttest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmenttest.R;

/**
 * The child fragment is no different than any other fragment other than it is now being maintained by
 * a child FragmentManager.
 */
public class ChildFragment extends Fragment {

    public static final String POSITION_KEY = "FragmentPositionKey";
    private int position;
	private FragmentManager childFragManager;


    public ChildFragment(int position, FragmentManager childFragManager) {
    	this.position = position;
    	this.childFragManager = childFragManager;
    }


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.child_fragment, container, false);
        TextView textview = (TextView) root.findViewById(R.id.textView);
        textview.setText(Integer.toString(position));

        final Button btn = (Button) root.findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
			
			

			@Override
			public void onClick(View v) {
				
				btn.setVisibility(View.GONE);
				addChildFragment(childFragManager);
				
			}
		});
        
        return root;
    }
	
	  private void addChildFragment(FragmentManager fragmentManager) {
	    	Fragment frag = new ChildFragment(this.position + 1 , fragmentManager);
			
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			transaction.replace(R.id.frame_container, frag);
			transaction.addToBackStack(null).commit();
	    }

}
