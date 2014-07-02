package com.seeclickfix.yuri.app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "YURI-MainActivity" ;

    @InjectView(R.id.editText)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }


    @OnClick(R.id.button)
    public void launchUri() {

        String editTextString = editText.getText().toString();
        Log.d(TAG, "LAUNCHING URI:"+editTextString);
        Uri uri = Uri.parse(editTextString);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);

        // Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(i, 0);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe) {
            startActivity(i);
        } else {
            Toast.makeText(this, "No activity found to handle that", Toast.LENGTH_SHORT).show();
        }

    }

}
