package example.com.fragmentapp;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class HeadlessCounterFragment extends Fragment {
    private SimpleCounterAsync mSimpleCounterAsync = new SimpleCounterAsync();
    private WeakReference<TextView> mCounterTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * Headless Fragments, have one really useful feature - they can be retained by the FragmentManager
         * across configuration changes. Since they do not have any UI related to them,
         * they do not have to be detroyed and rebuilt again when the user rotates the device for example.
         * In order to activate this behaviour, one just has to set the retained flag of the Fragment when it is initialized.
         * This can be done in the onCreate method of the Fragment.
         */


        setRetainInstance(true);

    }

    public void setCounterTextView(TextView textView) {
        mCounterTextView = new WeakReference<TextView>(textView);
    }

    public void startCounting() {
        if(mSimpleCounterAsync.getStatus() == AsyncTask.Status.RUNNING) return;
        if(mSimpleCounterAsync.getStatus() == AsyncTask.Status.FINISHED)
            mSimpleCounterAsync = new SimpleCounterAsync();
        mSimpleCounterAsync.execute();
    }

    public void stopCounting() {
        if(mSimpleCounterAsync.getStatus() != AsyncTask.Status.RUNNING) return;
        mSimpleCounterAsync.cancel(true);
    }

    private class SimpleCounterAsync extends AsyncTask<Void, Integer, Void> {
        private int mViewIsGoneCount = 0;

        @Override
        protected Void doInBackground(Void... voids) {
            int count = 0;

            while(!isCancelled()) {
                count++;
                publishProgress(count);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            TextView view = mCounterTextView.get();
            if(view != null) {
                view.setText("Counter: " + values[0]);
                mViewIsGoneCount = 0;
            } else {
                mViewIsGoneCount++;
                if(mViewIsGoneCount < 5) {
                    Toast.makeText(getActivity(), "TextView is not there for "
                                    + mViewIsGoneCount + " time.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "TextView was gone for too long. " +
                                    "Terminating the counter.",
                            Toast.LENGTH_SHORT).show();
                    cancel(true);
                }
            }
        }
    };
}