package example.com.fragmentapp.headless;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import example.com.fragmentapp.R;

/**
 * This Activity displays the screen's UI, creates a TaskFragment
 * to manage the task, and receives progress updates and results
 * from the TaskFragment when they occur.
 *
 * http://www.androiddesignpatterns.com/2013/04/retaining-objects-across-config-changes.html
 *
 *
 */
public class MainActivity extends Activity implements TaskFragment.TaskCallbacks {

    private static final String TAG_TASK_FRAGMENT = "task_fragment";

    private TaskFragment mTaskFragment;
    private ProgressBar mProgressBar;
    private TextView mTextProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_headless);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mTextProgress = (TextView) findViewById(R.id.textProgress);


        FragmentManager fm = getFragmentManager();
        mTaskFragment = (TaskFragment) fm.findFragmentByTag(TAG_TASK_FRAGMENT);

        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (mTaskFragment == null) {
            mTaskFragment = new TaskFragment();
            fm.beginTransaction().add(mTaskFragment, TAG_TASK_FRAGMENT).commit();
        }

    }

    // The four methods below are called by the TaskFragment when new
    // progress updates or results are available. The MainActivity
    // should respond by updating its UI to indicate the change.

    @Override
    public void onPreExecute() {
        Toast.makeText(MainActivity.this, "Pre", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProgressUpdate(int percent) {
        mProgressBar.setProgress(percent);
        mTextProgress.setText(""+percent);
    }

    @Override
    public void onCancelled() {
        Toast.makeText(MainActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostExecute() {
        Toast.makeText(MainActivity.this, "Post", Toast.LENGTH_SHORT).show();

    }
}