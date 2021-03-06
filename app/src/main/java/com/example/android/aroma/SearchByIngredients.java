package com.example.android.aroma;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.aroma.ViewHolder.FoodListAdapter;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class SearchByIngredients extends AppCompatActivity implements FoodListAdapter.OnItemClickListener{
    public static final String Search_ID = "id";
    private ArrayList<Food> foodList;
    FoodListAdapter foodListAdapter;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    String categoryID = "";
    private RequestQueue mQueue,iQueue;
    Button removeBtn;
    ImageButton imgButton, searchImgButton,mushroom,onion,broccoli,bellpaper,carrot,corn;
    ImageButton potato,peas,soybean,pear,cabbage,beet,avocado;
    ArrayList<String> addArray = new ArrayList<>();
    AutoCompleteTextView inputEditText;
    ListView showIngredients;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_ingredients);
        mQueue = Volley.newRequestQueue(this);
        iQueue = Volley.newRequestQueue(this);
        foodList = new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recycler_food_searchIng);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        inputEditText =(AutoCompleteTextView )findViewById(R.id.editText);

        showIngredients=(ListView)findViewById(R.id.added_ingredients);
        imgButton=(ImageButton)findViewById(R.id.button3);
        mushroom=(ImageButton)findViewById(R.id.mushroom);
        peas=(ImageButton)findViewById(R.id.peas);
        pear=(ImageButton)findViewById(R.id.pear);
        avocado=(ImageButton)findViewById(R.id.avocado);
        beet=(ImageButton)findViewById(R.id.beet);
        soybean=(ImageButton)findViewById(R.id.soybean);
        potato=(ImageButton)findViewById(R.id.potato);
        cabbage=(ImageButton)findViewById(R.id.cabbage);
        onion=(ImageButton)findViewById(R.id.onion);
        broccoli=(ImageButton)findViewById(R.id.broccoli);
        corn=(ImageButton)findViewById(R.id.corn);
        carrot=(ImageButton)findViewById(R.id.carrot);
        bellpaper=(ImageButton)findViewById(R.id.bellpaper);
        removeBtn=(Button)findViewById(R.id.removeings_button);
        searchImgButton=(ImageButton)findViewById(R.id.search_byings_button) ;
        final List<String> responseList = new ArrayList<String>();
        String url ="http://aroma-env.wv5ap2cp4n.us-west-1.elasticbeanstalk.com/ingredients";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONObject jobj = response.getJSONObject("data");
                            JSONArray jsonArray = jobj.getJSONArray("ingredients");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject ingredients = jsonArray.getJSONObject(i);
                                String name = ingredients.getString("name");
                                responseList.add(name);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        });
        iQueue.add(request);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, responseList);
        inputEditText.setAdapter(adapter);
        broccoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.add("broccoli");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
            }
        });

        mushroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.add("mushroom");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
            }
        });
        onion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.add("onion");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
            }
        });
        bellpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.add("bell pepper");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
            }
        });
        corn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.add("corn");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
            }
        });
        cabbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.add("cabbage");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
            }
        });
        avocado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.add("avocado");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
            }
        });
        pear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.add("pear");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
            }
        });
        peas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.add("peas");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
            }
        });
        potato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.add("potato");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
            }
        });
        beet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.add("beets");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
            }
        });
        carrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.add("carrots");
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
            }
        });


        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputEditText.getText().toString();


                if(addArray.contains(input))
                {
                    Toast.makeText(getBaseContext(),"Ingredient already added to search list",Toast.LENGTH_LONG).show();
                }
                else if (input==null)
                {
                    Toast.makeText(getBaseContext(),"Enter ingredient",Toast.LENGTH_LONG).show();
                }
                else
                {
                    addArray.add(input);
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                            R.layout.simple_textview,R.id.textViewIng,addArray);
                    showIngredients.setAdapter(arrayAdapter);
                    ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");
                }
            }
        });
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addArray.removeAll(addArray);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchByIngredients.this,
                        R.layout.simple_textview,R.id.textViewIng,addArray);
                showIngredients.setAdapter(arrayAdapter);
                ((AutoCompleteTextView) findViewById(R.id.editText)).setText("");

            }
        });
        searchImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(addArray.isEmpty())
                {
                    Toast.makeText(getBaseContext(),"Enter ingredients to search",Toast.LENGTH_LONG).show();
                }
                else{
                    try {
                        foodList.removeAll(foodList);
                        foodListAdapter = new FoodListAdapter(SearchByIngredients.this, foodList);
                        recyclerView.setAdapter(foodListAdapter);
                        jsonParse(addArray);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }

    private void jsonParse(ArrayList addArray) throws UnsupportedEncodingException {
        String base_url ="http://aroma-env.wv5ap2cp4n.us-west-1.elasticbeanstalk.com/recipes?search=ingredient";

        for (int counter = 0; counter < addArray.size(); counter++) {
            String ing = addArray.get(counter).toString();
            base_url= base_url+ "&includes="+ URLEncoder.encode(ing, "UTF-8").replace("+","%20");
        }
        //String encodedUrl = URLEncoder.encode(base_url, "UTF-8");
        System.out.println("base url is:"+base_url);

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, base_url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {

                        try {
                            JSONObject jobj = response.getJSONObject("data");
                            JSONArray jsonArray = jobj.getJSONArray("recipes");
                            if(jsonArray.length()!=0) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject recipes = jsonArray.getJSONObject(i);
                                    String id = recipes.getString("id");
                                    String name = recipes.getString("title");
                                    String image = recipes.getString("image_url");
                                    foodList.add(new Food(name, image, id));
                                }
                                foodListAdapter = new FoodListAdapter(SearchByIngredients.this, foodList);
                                recyclerView.setAdapter(foodListAdapter);
                                foodListAdapter.setOnItemClickListener(SearchByIngredients.this);
                            }
                            else
                            {
                                Toast.makeText(getBaseContext(),"No recipes to show",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }

    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, RecipeDetails.class);
        Food clickedItem = foodList.get(position);
        detailIntent.putExtra(Search_ID, clickedItem.getId());
        Intent oldIntent=getIntent();
        detailIntent.putExtra("user",oldIntent.getSerializableExtra("user"));

        startActivity(detailIntent);
    }

}
