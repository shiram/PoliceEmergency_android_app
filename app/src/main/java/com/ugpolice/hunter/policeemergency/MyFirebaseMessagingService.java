package com.ugpolice.hunter.policeemergency;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    SharedPreferences sharedPreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
                sendPushNotification(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }
        }

    }

    private void sendPushNotification(JSONObject json) {
        //optionally we can display the json into log
        Log.e(TAG, "Notification JSON " + json.toString());
        try {
            //getting the json data
            JSONObject data = json.getJSONObject("data");

            //parsing json data
            int id = data.getInt("id");
            String title = data.getString("title");
            String book_id = data.getString("book_id");
            String user_id = data.getString("user_id");
            String firstname = data.getString("firstname");
            String phone = data.getString("phone");
            String book_image = data.getString("book_image");
            String book_title = data.getString("book_title");
            String book_authors = data.getString("book_authors");
            String book_isbn = data.getString("book_isbn");
            String book_edition = data.getString("book_edition");
            String date_sent = data.getString("order_date");
            String access_level = data.getString("access_level");
            String quantity = data.getString("quantity");
            String total_amount = data.getString("total_amount");
            String payment_means = data.getString("payment_means");
            String book_price = data.getString("book_price");
            String lastname = data.getString("lastname");
            String reply = data.getString("reply");

            //try storing in shared prefs
            sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("user_id", user_id);
            editor.putString("book_id",book_id);
            editor.putString("fname",firstname);
            editor.putString("lname", lastname);
            editor.putString("phone", phone);
            editor.putString("book_title",book_title);
            editor.putString("book_authors", book_authors);
            editor.putString("date_sent", date_sent);
            editor.putString("book_isbn", book_isbn);
            editor.putString("book_edition", book_edition);
            editor.putString("payment_means", payment_means);
            editor.putString("quantity", quantity);
            editor.putString("total_amount", total_amount);
            editor.putString("book_price", book_price);
            editor.putString("book_image", book_image);
            editor.putInt("id",id);

            editor.commit();

           // Log.e(TAG, "MY DATA " + user_id+movie_id+movie_name+ticket_code+ticket_cost+access_level);


            //set the data for late display

            //creating MyNotificationManager object
            //MyNotificationManager mNotificationManager = new MyNotificationManager(getApplicationContext());




            //try replace with order state

            if(reply != null && !reply.isEmpty()){

                //creating an intent for the notification
             //  Intent cIntent = new Intent(getApplicationContext(), ClientNotification.class);
               // mNotificationManager.showSmallNotification(title, reply, cIntent);
            }else {
              //  Intent intent = new Intent(getApplicationContext(), Notification.class);
               // mNotificationManager.showSmallNotification(title, phone, intent);
            }

        } catch (JSONException e) {
            Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }
    }
}
