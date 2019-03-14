package android.example.com.visualizerpreferences;

import android.os.Bundle;

public class SettingsFragment extends PreferenceFragmentCompat implements Settings_Fragment {

    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) throws InterruptedException {
        wait(R.xml.pref_visualizer);

    }
}