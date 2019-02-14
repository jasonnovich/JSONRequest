/*
 * Copyright (c) 2019 by O2 Product Management (O2PM)
 *
 * Authored by Jason Novich
 *
 * All Rights Reserved
 *
 * This software is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 */

package o2pm.com.jsonrequest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RequestQueue queue = Volley.newRequestQueue(this);
        Button changeSymbol = findViewById(R.id.changeTickerSymbolButtonID);

        changeSymbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final EditText symbol = findViewById(R.id.tickerSymbolID);
                final TextView copmpany = findViewById(R.id.companyNameID);
                final TextView price = findViewById(R.id.symbolPriceID);

                final String companyName;
                final String companySymbol = " ";
                final Double symbolPrice;
                final String urlPat1 = "https://api.iextrading.com/1.0/stock/";
                final String companyURL = urlPat1.concat(companySymbol).concat("/company");
                final String priceURL = urlPat1.concat(companySymbol).concat("/price");

                StringRequest coPrice = new StringRequest(Request.Method.GET, priceURL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                //currentPriceResults.setText(response);
                                price.setText("$ " + response);
                                Log.d("Stock1", response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        price.setText("That didn't work!");
                    }
                });

                queue.add(coPrice);
            }
        });
    }
}
