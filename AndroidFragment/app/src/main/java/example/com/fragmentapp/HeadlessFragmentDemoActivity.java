package example.com.fragmentapp;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Memento
 *
 *
 * Headless Fragments are created purely programmatically.
 * Since they do not have any UI, there is no resource id from a XML related to them. Fortunately,
 * the FragmentManager class offers alternative methods for managing such Fragments based on the so called Tag,
 * which is just a String. Take in account, it is our job to make sure these Tags are unique for
 * Headless Fragments inside an Activity. The following code snippet demostrates how a Headless Fragment
 * can be initialized and added to an Activity (for example in its onCreate method).
 * Notice that we first check if the Fragment is already available by searching for it using the Tag.
 * We create it only if no Fragment with the requested Tag is found by the FragmentManager.
 *
 *
 * Some uses cases

 So what could we use this type of Fragments for? It’s simple, they do not get detroyed,
 therefore one can put inside whatever object needed and it will stay there even across configuration changes.
 For simpler objects this is an overkill and I recommend just keeping the state by implementing
 the onSaveInstanceState and onRestoreInstance methods. However,
 not everything can be implemented in a way that it can fit into a Bundle.
 Some classes are just too complicated to be implemented as a Parceable.
 Sometimes one simply does not want to write all the extra boilerplate code needed for it.
 Or sometimes one wants a simple AsyncTask to do something and keep doing it across orientation change.
 In such cases, one can simply save a reference to the needed objects inside a
 Headless Fragment and can be sure that whenever he needs this reference,
 it will be there because the Fragment will not be destroyed.


 Now, Headless Fragments are not some magic solution for everything and should be used with caution.
 I’m not advocating doing really long operations like downloading files inside a Headless Fragment.
 One should use some proper background functionality like a long running Service for that.
 In addition, one has to be careful with saving references into a Headless Fragments since they
 potentially have longer lifecycle than other UI components. For example, doing something stupid
 like saving a reference to a whole Activty could cause a memory leak on orientation change.
 My point is, be mindful about what you use Headless Fragments for.
 */

public class HeadlessFragmentDemoActivity extends Activity {


    private HeadlessCounterFragment mHeadlessCounterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlessfragmentdemo);

        mHeadlessCounterFragment = (HeadlessCounterFragment)getFragmentManager()
                .findFragmentByTag("counter_fragment");
        if(mHeadlessCounterFragment == null) {
            mHeadlessCounterFragment = new HeadlessCounterFragment();
            getFragmentManager().beginTransaction().add(mHeadlessCounterFragment, "counter_fragment").commit();
        }

        if(savedInstanceState == null) {
            // Setting the TextView for the count only initially
            mHeadlessCounterFragment.setCounterTextView((TextView) findViewById(R.id.textView));
        }

        findViewById(R.id.btn_startCounting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HeadlessCounterFragment counterState = (HeadlessCounterFragment) getFragmentManager()
                        .findFragmentByTag("counter_fragment");
                if (counterState != null) counterState.startCounting();
            }
        });

        findViewById(R.id.btn_stopCounting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HeadlessCounterFragment counterState = (HeadlessCounterFragment) getFragmentManager()
                        .findFragmentByTag("counter_fragment");
                if (counterState != null) counterState.stopCounting();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Making sure we clean references on destroy
        if(mHeadlessCounterFragment != null) {
            mHeadlessCounterFragment.setCounterTextView(null);
            mHeadlessCounterFragment = null;
        }
    }
}
