package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.wafflecopter.charcounttextview.CharCountTextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;


public class ComposeActivity extends AppCompatActivity{

    Button ivSubmit;
    TwitterClient twitterClient;
    Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        twitterClient = TwitterApp.getRestClient(this);

        // character counter
        CharCountTextView charCountTextView = (CharCountTextView)findViewById(R.id.tvTextCounter);
        EditText editText = (EditText)findViewById(R.id.et_simple);

        charCountTextView.setEditText(editText);
        charCountTextView.setCharCountChangedListener(new CharCountTextView.CharCountChangedListener() {
            @Override
            public void onCountChanged(int i, boolean b) {

            }
        });


        // accessing button
        ivSubmit = (Button) findViewById(R.id.ivSubmit);
        ivSubmit.setOnClickListener(new View.OnClickListener(){
            // when button is clicked
            @Override
            public void onClick(View view){
                EditText simpleEditText = (EditText) findViewById(R.id.et_simple);
                final String strValue = simpleEditText.getText().toString();


                twitterClient.sendTweet(strValue, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers,JSONObject response) {
                        try{
                            tweet = Tweet.fromJSON(response);

                            Intent data = new Intent();
                            data.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                            setResult(-1, data);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable error) {
                        Log.d("Send Tweet", error.toString());
                    }
                });


            }
        });
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }





}
