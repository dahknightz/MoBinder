package com.ktdevspace.mobinder;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RandomAPIService {
    public static final String QUERY_FOR_RANDOM_CARD = "https://api.scryfall.com/cards/random";
    public static final String QUERY_FOR_CARD_NAME_FUZZY = "https://api.scryfall.com/cards/named?fuzzy=";

    Context context;
    String cardNameRandom;
    String cardIDRandom;
    String cardImgURLRandom;

    public RandomAPIService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String cardID);
    }

    //Get random card name from a fuzzy(partial) name search, will also search full names
    public void getRandomCardName(SearchAPIService.VolleyResponseListener volleyResponseListener){

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, QUERY_FOR_RANDOM_CARD, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        RandomAPIService.this.cardNameRandom ="";
                        try {
                            //will get card name
                            cardNameRandom = response.getString("name");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(context,"Random Card: " + cardInfoRandom.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(RandomAPIService.this.cardNameRandom);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Something broke!", Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onError("Something broke!");
                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    //Get random card id from a fuzzy(partial) name search, will also search full names
    public void getRandomCardID(SearchAPIService.VolleyResponseListener volleyResponseListener){

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, QUERY_FOR_RANDOM_CARD, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cardIDRandom ="";
                        try {
                            //Will get card id
                            JSONArray cardIDInfo = response.getJSONArray("multiverse_ids");
                            cardIDRandom = cardIDInfo.getString(0) ;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(context,"Card ID: " + cardID.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cardIDRandom);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Something broke!", Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onError("Something broke!");
                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(request);

    }

    //Get random card image from a fuzzy(partial) name search, will also search full names
    public void getRandomCardImage(SearchAPIService.VolleyResponseListener volleyResponseListener){

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, QUERY_FOR_RANDOM_CARD, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cardImgURLRandom ="";
                        try {
                            //Will get image url
                            JSONObject cardImgInfo = response.getJSONObject("image_uris");
                            cardImgURLRandom = cardImgInfo.getString("normal");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(context,"Card Image URL: " + cardImgURL.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cardImgURLRandom);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Something broke!", Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onError("Something broke!");
                    }
                });
        MySingleton.getInstance(context).addToRequestQueue(request);

    }
}
