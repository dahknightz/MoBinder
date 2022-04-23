package com.ktdevspace.mobinder;

import android.content.Context;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchAPIService {
//    public static final String QUERY_FOR_CARD_NAME_EXACT = "https://api.scryfall.com/cards/named?exact=";
    public static final String QUERY_FOR_CARD_NAME_FUZZY = "https://api.scryfall.com/cards/named?fuzzy=";

    Context context;
    String cardID, cardInfoName, cardImgURL, cardRarity, cardSetName, cardCMC, cardColors, cardPrice, cardFoilPrice, cardURL;

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
                            cardInfoName = response.getString("name");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(context,"Card Name: " + cardInfoName.toString(), Toast.LENGTH_SHORT).show();
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

//                        Toast.makeText(context,"Card ID: " + cardID.toString(), Toast.LENGTH_SHORT).show();
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

//                        Toast.makeText(context,"Card Image URL: " + cardImgURL.toString(), Toast.LENGTH_SHORT).show();
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

    //Get card rarity from a fuzzy(partial) name search, will also search full names
    public void getCardRarity(String cardName, VolleyResponseListener volleyResponseListener){

        String cardExactNameURL = QUERY_FOR_CARD_NAME_FUZZY + cardName;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, cardExactNameURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cardRarity ="";
                        try {
                            //Will get card rarity
                            cardRarity = response.getString("rarity");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(context,"Card Rarity: " + cardRarity.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cardRarity);
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

    //Get card set name from a fuzzy(partial) name search, will also search full names
    public void getCardSetName(String cardName, VolleyResponseListener volleyResponseListener){

        String cardExactNameURL = QUERY_FOR_CARD_NAME_FUZZY + cardName;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, cardExactNameURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cardSetName ="";
                        try {
                            //Will get card set name
                            cardSetName = response.getString("set_name");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(context,"Card Set Name: " + cardSetName.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cardSetName);
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

    //Get card cmc from a fuzzy(partial) name search, will also search full names
    public void getCardCMC(String cardName, VolleyResponseListener volleyResponseListener){

        String cardExactNameURL = QUERY_FOR_CARD_NAME_FUZZY + cardName;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, cardExactNameURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cardCMC ="";
                        try {
                            //Will get card cmc
                            cardCMC = response.getString("cmc");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(context,"Card CMC: " + cardCMC.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cardCMC);
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

    //Get card color from a fuzzy(partial) name search, will also search full names
    public void getCardColor(String cardName, VolleyResponseListener volleyResponseListener){

        String cardExactNameURL = QUERY_FOR_CARD_NAME_FUZZY + cardName;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, cardExactNameURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cardColors ="";
                        try {
                            //Will get card color
                            JSONArray cardColorInfo = response.getJSONArray("colors");
                            cardColors = cardColorInfo.getString(0) ;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(context,"Card Color: " + cardColors.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cardColors);
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

    //Get card price from a fuzzy(partial) name search, will also search full names
    public void getCardPrice(String cardName, VolleyResponseListener volleyResponseListener){

        String cardExactNameURL = QUERY_FOR_CARD_NAME_FUZZY + cardName;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, cardExactNameURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cardPrice ="";
                        try {
                            //Will get card price
                            JSONObject cardPriceInfo = response.getJSONObject("prices");
                            cardPrice = cardPriceInfo.getString("usd") ;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(context,"Card Price: " + cardPrice.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cardPrice);
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

    //Get card price from a fuzzy(partial) name search, will also search full names
    public void getCardFoilPrice(String cardName, VolleyResponseListener volleyResponseListener){

        String cardExactNameURL = QUERY_FOR_CARD_NAME_FUZZY + cardName;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, cardExactNameURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cardFoilPrice ="";
                        try {
                            //Will get card foil price
                            JSONObject cardFoilPriceInfo = response.getJSONObject("prices");
                            cardFoilPrice = cardFoilPriceInfo.getString("usd_foil") ;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(context,"Card Foil Price: " + cardFoilPrice.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cardFoilPrice);
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

    //Get card price from a fuzzy(partial) name search, will also search full names
    public void getCardURL(String cardName, VolleyResponseListener volleyResponseListener){

        String cardExactNameURL = QUERY_FOR_CARD_NAME_FUZZY + cardName;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, cardExactNameURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        cardURL ="";
                        try {
                            //Will get card foil price
                            JSONObject cardURLInfo = response.getJSONObject("purchase_uris");
                            cardURL = cardURLInfo.getString("tcgplayer") ;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(context,"Card URL: " + cardURL.toString(), Toast.LENGTH_SHORT).show();
                        volleyResponseListener.onResponse(cardURL);
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
