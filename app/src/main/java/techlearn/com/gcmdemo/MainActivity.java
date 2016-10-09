package techlearn.com.gcmdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        register= (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences=getSharedPreferences("MyPrefs", MODE_PRIVATE);
                String regToken=preferences.getString("TOKEN","");

                Log.d("GCM_DEMO",  "token stored: "+regToken);
                Toast.makeText(getApplicationContext(), regToken, Toast.LENGTH_LONG).show();

                if (regToken.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, GcmRegisterService.class);
                    startService(intent);

                }

            }
        });
    }
}
