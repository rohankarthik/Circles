package com.example.rohan.circleanimation;

        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.view.MenuItem;

public class MainActivity extends ActionBarActivity  {


    circleDrawView circleView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        circleView = new circleDrawView(this);
        setContentView(circleView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
