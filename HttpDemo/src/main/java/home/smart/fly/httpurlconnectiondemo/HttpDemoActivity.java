package home.smart.fly.httpurlconnectiondemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by rookie on 2016/11/3.
 */

public class HttpDemoActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_demo);
        findViewById(R.id.httpDemo).setOnClickListener(this);
        findViewById(R.id.volley).setOnClickListener(this);
        findViewById(R.id.okhttp).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;


        int i = v.getId();
        if (i == R.id.httpDemo) {
            intent = new Intent(HttpDemoActivity.this, HttpUrlConnectionAcitivity.class);

        } else if (i == R.id.volley) {
            intent = new Intent(HttpDemoActivity.this, VolleyDemoActivity.class);

        } else if (i == R.id.okhttp) {
            intent = new Intent(HttpDemoActivity.this, Okhttp3DemoActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
