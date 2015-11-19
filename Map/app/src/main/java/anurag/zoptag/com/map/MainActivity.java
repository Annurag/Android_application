package anurag.zoptag.com.map;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSerch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSerch = (Button)findViewById(R.id.buttonSearch);

    }

    public void mapActivity(View v){

        startActivity(new Intent(MainActivity.this, MapsActivity.class));
    }
}
