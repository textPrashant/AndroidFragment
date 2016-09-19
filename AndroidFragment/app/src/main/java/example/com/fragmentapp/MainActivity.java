//package example.com.fragmentapp;
//
//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//import android.view.Menu;
//import android.view.MenuItem;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                //Hello world!
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    /**
//     * Passing parameters to fragments
//     * An activity can pass a bundle to the fragment.
//     */
//    public void passParmsToFragment() {
//
//
//        DetailFragment detailFragment = new DetailFragment();
//
//        // configure link
//        Bundle bundle = new Bundle();
//        bundle.putString("link", link);
//        detailFragment.setArguments(bundle);
//    }
//
//
//    //The fragment gets this in its onActivityCreated method.
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        Bundle bundle = getArguments();
//        if (bundle != null) {
//            setText(bundle.getString("link"));
//        }
//    }
//
//    /*
//     Fragment life-cycle
//A fragment has its own life cycle. But it is always connected to the life cycle of the activity which uses the fragment.
//http://www.vogella.com/tutorials/AndroidFragments/img/xfragmentlifecycle10.png.pagespeed.ic.enq8LZV2uu.png
//
//Fragment lifecycle
//If an activity stops, its fragments are also stopped. If an activity is destroyed, its fragments are also destroyed.
//     */
//
//
//    public void manageFragments() {
//        // You use the FragmentManager to replace the container with a fragment.
//
//        // get fragment manager
//        FragmentManager fm = getFragmentManager();
//
//        // add
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.add(R.id.your_placehodler, new YourFragment());
//        // alternatively add it with a tag
//        // trx.add(R.id.your_placehodler, new YourFragment(), "detail");
//        ft.commit();
//
//        // replace
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.replace(R.id.your_placehodler, new YourFragment());
//        ft.commit();
//
//        // remove
//        Fragment fragment = fm.findFragmentById(R.id.your_placehodler);
//        FragmentTransaction ft = fm.beginTransaction();
//        ft.remove(fragment);
//        ft.commit();
//        /* A new fragment replaces an existing fragment in this container.
//
//                If you want to add the transaction to the backstack of Android, you use the addToBackStack() method. This adds the action to the history stack of the activity and allows the user to revert this change via the back button.
//                */
//
//
//
////        Check if a fragment is present in the layout
////        To check if a fragment is part of your layout you can use the FragmentManager class. The isInLayout() method works on if the fragment as added to the activity via its layout.
//
//        DetailFragment fragment = (DetailFragment) getFragmentManager().
//                findFragmentById(R.id.detail_frag);
//        if (fragment==null || ! fragment.isInLayout()) {
//            // start new Activity
//        }
//        else {
//            fragment.update(...);
//        }
//    }
//}
