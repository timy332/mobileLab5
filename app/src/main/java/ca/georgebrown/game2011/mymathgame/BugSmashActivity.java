package ca.georgebrown.game2011.mymathgame;

import android.app.Activity;
import android.os.Bundle;

public class BugSmashActivity extends Activity {

    public static String PACKAGE_NAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        //setContentView(R.layout.activity_bug_smash);
        setContentView(new BugBounce(this,PACKAGE_NAME));
    }
}
