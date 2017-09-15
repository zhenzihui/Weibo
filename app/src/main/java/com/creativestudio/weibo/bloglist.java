package com.creativestudio.weibo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.jsoup.Jsoup;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class bloglist extends AppCompatActivity {
//    Bundle bundle;
    ListView blogs;
    List<HashMap<String,String>> blogList;
    SimpleAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bloglist);
        init();
        ShowOnList(blogList);

    }


    void init()
    {
//        bundle = getIntent().getExtras();
//       blogList =(List<HashMap<String,String>>) bundle.getSerializable("list");
        blogList = (List<HashMap<String,String>>)getIntent().getSerializableExtra("list");
        blogs =(ListView)findViewById(R.id.blogView);

        ShowOnList(blogList);

    }




    void ShowOnList(List<HashMap<String,String>> list)
    {
    for(HashMap<String,String> blog:list)
    {
       String text =  blog.get("content");
        text= Jsoup.parse(text).text();
        blog.put("content",text);
    }


        adapter =new SimpleAdapter(this,list,R.layout.weibo_content,new String[]{"user","content","created_at"},new int[]{R.id.user,R.id.content,R.id.created_at});
        blogs.setAdapter(adapter);

    }


}
