package com.example.testing.DistanceMatrix;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DistanceMatrix extends AsyncTask<String, Void, String> {

    ProgressDialog dialog;
    Context mContext;
    Double duration;
    Geo geo;

    public DistanceMatrix(Context mContext) {
        this.mContext = mContext;
        geo= (Geo) mContext;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog=new ProgressDialog(mContext);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.show();
    }

    @Override
    protected void onPostExecute(String aDouble) {
        super.onPostExecute(aDouble);
        if(aDouble!=null)
        {
            geo.setDouble(aDouble);
            dialog.dismiss();
        }
        else
            Toast.makeText(mContext, "Error!Please Try Again with proper values", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            URL url=new URL(params[0]);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int statuscode=con.getResponseCode();
            if(statuscode==HttpURLConnection.HTTP_OK)
            {
                BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb=new StringBuilder();
                String line=br.readLine();
                while(line!=null)
                {
                    sb.append(line);
                    line=br.readLine();
                }
                String json=sb.toString();
                Log.d("JSON",json);
                JSONObject root=new JSONObject(json);
                JSONArray array_rows=root.getJSONArray("rows");
                Log.d("JSON","array_rows:"+array_rows);
                JSONObject object_rows=array_rows.getJSONObject(0);
                Log.d("JSON","object_rows:"+object_rows);
                JSONArray array_elements=object_rows.getJSONArray("elements");
                Log.d("JSON","array_elements:"+array_elements);
                JSONObject  object_elements=array_elements.getJSONObject(0);
                Log.d("JSON","object_elements:"+object_elements);
                JSONObject object_duration=object_elements.getJSONObject("duration");
                JSONObject object_distance=object_elements.getJSONObject("distance");

                Log.d("JSON","object_duration:"+object_duration);
                return object_duration.getString("value")+","+object_distance.getString("value");

            }
        } catch (MalformedURLException e) {
            Log.d("error", e.getMessage());
        } catch (IOException e) {
            Log.d("error", e.getMessage());
        } catch (JSONException e) {
            Log.d("error",e.getMessage());
        }


        return null;
    }

    public interface Geo{
        public void setDouble(String min);
    }
}




