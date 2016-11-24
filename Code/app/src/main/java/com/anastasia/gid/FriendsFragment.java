package com.anastasia.gid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Anastasia on 21.11.2016.
 */
public class FriendsFragment extends Fragment {
    private JSONArray friendsList;
    private ArrayList<String> friends;

    public FriendsFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void show()
    {
        getFriendsList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false);
    }

    private void getFriendsList()
    {
        GraphRequest request = GraphRequest.newMyFriendsRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONArrayCallback(){
                    @Override
                    public void onCompleted(JSONArray objects, GraphResponse response) {
                        JSONObject object = response.getJSONObject();
                        friendsList = object.optJSONArray("data");
                        addFriendsToViewList();
                    }
                });
        request.executeAsync();
    }

    private void addFriendsToViewList(){
        friends = new ArrayList<String>();
        // TODO remove this 10
        for(int i=0;i<friendsList.length(); i++) {
            try {
                friends.add(friendsList.getJSONObject(i % 2).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), R.layout.listview, friends);
        ListView listView = (ListView) getView().findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
    }
}
