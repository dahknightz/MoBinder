package com.ktdevspace.mobinder;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchAPIService {
//    public static final String QUERY_FOR_CARD_NAME_EXACT = "https://api.scryfall.com/cards/named?exact=";
    public static final String QUERY_FOR_CARD_NAME_FUZZY = "https://api.scryfall.com/cards/named?fuzzy=";

    Context context;
    String cardID;
    String cardInfoName;
    String cardImgURL;

    public SearchAPIService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String cardID);
    }

    //Get card name from a fuzzy(partial) name search, will also search full names
    public void getCardName(String cardName, VolleyResponseListener volleyResponseListener){

        String cardExactNameURL = QUERY_FOR_CARD_NAME_FUZZY + cardName;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, cardExactNameURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cardInfoName ="";
                        try {
                            //will get card name
                            String cardNameInfo = response.getString("name");
                            cardInfoName = cardNameInfo;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(context,"Card Name: " + cardInfoName.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cardInfoName);
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

    //Get card id from a fuzzy(partial) name search, will also search full names
    public void getCardID(String cardName, VolleyResponseListener volleyResponseListener){

        String cardExactNameURL = QUERY_FOR_CARD_NAME_FUZZY + cardName;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, cardExactNameURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cardID ="";
                        try {
                            //Will get card id
                            JSONArray cardIDInfo = response.getJSONArray("multiverse_ids");
                            cardID = cardIDInfo.getString(0) ;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(context,"Card ID: " + cardID.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cardID);
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

    //Get card image from a fuzzy(partial) name search, will also search full names
    public void getCardImage(String cardName, VolleyResponseListener volleyResponseListener){

        String cardExactNameURL = QUERY_FOR_CARD_NAME_FUZZY + cardName;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, cardExactNameURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cardImgURL ="";
                        try {
                            //Will get image url
                            JSONObject cardImgInfo = response.getJSONObject("image_uris");
                            cardImgURL = cardImgInfo.getString("normal");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(context,"Card Image URL: " + cardImgURL.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cardImgURL);
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
