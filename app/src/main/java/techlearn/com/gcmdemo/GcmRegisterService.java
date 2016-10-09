package techlearn.com.gcmdemo;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

/**
 * Created by farman on 10/9/2016.
 */
public class GcmRegisterService extends IntentService {


    public GcmRegisterService() {
        super("");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        registerGCM();

    }

    private void registerGCM() {
        String regToken = null;
        try {
            //Creating an instanceid
            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());

            //Getting the token from the instance id
            regToken = instanceID.getToken(getString(R.string.sender_id), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            //after getting from gcm server , storing it locally
            storeRegID(regToken);

            Log.d("GCM_DEMO", "token:" + regToken);

        } catch (Exception e) {
            Log.d("GCM_DEMO", "Registration error");

        }

    }

    private void storeRegID(String regId){

        SharedPreferences preferences=getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();

        editor.putString("TOKEN",regId);
        editor.commit();

    }

}
