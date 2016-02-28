package co.devcenter.android.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.seperate:
                        startActivity(new Intent(MainActivity.this, SeperateActivity.class));
                        break;

                    case R.id.no_button:
                        startActivity(new Intent(MainActivity.this, NoButtonActivity.class));
                        break;

                    case R.id.in_bar:
                        startActivity(new Intent(MainActivity.this, InBarActivity.class));
                        break;
                }
            }
        };

        findViewById(R.id.seperate).setOnClickListener(listener);
        findViewById(R.id.in_bar).setOnClickListener(listener);
        findViewById(R.id.no_button).setOnClickListener(listener);
    }
}
