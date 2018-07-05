package com.codepath.apps.restclienttemplate.models;

import android.os.Parcel;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

@org.parceler.Parcel
public class User{
    // list attributes
    public User(){}

    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;

    protected User(Parcel in) {
        name = in.readString();
        uid = in.readLong();
        screenName = in.readString();
        profileImageUrl = in.readString();
    }

    // deserialize the JSON
    public static User fromJSON(JSONObject json) throws JSONException{
        User user = new User();

        Log.d("user", json.toString());
        // extract and fill the values
        user.name = json.getString("name");
        user.uid = json.getLong("id");
        user.screenName = json.getString("screen_name");
        user.profileImageUrl = json.getString("profile_image_url");

        return user;

    }

}
