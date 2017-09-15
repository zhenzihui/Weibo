package com.creativestudio.weibo;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector;

import java.io.IOException;
import java.io.Serializable;
import java.net.CookieStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button login;
    EditText name,password;
    static final String  loginPage="https://passport.tianya.cn/m/login.jsp";
    Handler handler;
    static final int Login=0;
    static final int Get=1;
    static final int GetPage=2;
    static final int initLogin=3;
    int Content_length=0;
   HashMap<String ,String >hidform = new HashMap<>();


    OkHttpClient client ;
    PersistentCookieStore persistentCookieStore;
    CookieJarImpl cookieJar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
//        initPage();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initForm();

            }
        });


    }


    void login(String username,String password,int contentLength)
    {
        RequestBody body = new FormBody.Builder()

                .add("vwriter",username)
                .add("vpassword",password)
                .add("rmflag",hidform.get("rmflag"))
                .add("__sid",hidform.get("__sid"))
                .add("fowardURL",hidform.get("fowardURL"))
                .add("returnURL",hidform.get("returnURL"))
                .add("from",hidform.get("from"))
                .add("login_m",hidform.get("login_m"))
                .add("action","f0.1484015219862.0|071529bad60731ec4a970c160e085891|6041de436684c6dd4e7750bee682e7cc|Mozilla/5.0 (MeeGo; NokiaN9) AppleWebKit/534.13 (KHTML, like Gecko) NokiaBrowser/8.5.0 Mobile Safari/534.13|0|1|v2.2")
//                .add("is_auto","1")
//                .add("act","登陆社区")
//                .add("__sid",hidform.get("__sid"))
//                .add("vu",hidform.get("vu"))
////                .add("key",hidform.get("key"))
////                .add("chk",hidform.get("chk"))
//                .add("b","")
////                .add("idwriter",hidform.get("idwriter"))
                .build();

//            System.out.println("请求体:"+body);
//            System.out.println(hids.get(0));
//            System.out.println(hids.get(1));
//            System.out.println(hids.get(2));
//            System.out.println(hids.get(3));

        Request req = new Request.Builder().url(loginPage).post(body)
//                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
//                .addHeader("Accept-Encoding","gzip, deflate, br")
//                .addHeader("Accept-Language","en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4")
//                .addHeader("Cache-Control","no-cache")
//                .addHeader("Connection","keep-alive")
//                .addHeader("Content-Length",Integer.toString(contentLength))
//                .addHeader("Content-Type","application/x-www-form-urlencoded")
//                .addHeader("Host","passport.tianya.cn")
//                .addHeader("Origin","https://passport.tianya.cn")
//                .addHeader("Pragma","no-cache")
//                .addHeader("Referer","https://passport.tianya.cn/m/login.jsp?fowardURL=http://www.tianya.cn/m/&userName=%E9%9A%90")
//                .addHeader("User-Agent","Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Mobile Safari/537.36 Form Data view source view URL encoded")
//                .addHeader("Upgrade-Insecure-Requests","1")
                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .addHeader("Accept-Encoding","gzip, deflate, br")
                .addHeader("Accept-Language","en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4")
                .addHeader("Cache-Control","no-cache")
                .addHeader("Connection","keep-alive")
                .addHeader("Content-Length",Integer.toString(contentLength))
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .addHeader("Cookie","ADVS=34ad73a68f01c4; __cfduid=d1f9f9a06b46c5ef3db65fffc224f5fd51482741102; __cid=52; __guid=324272378; __guid2=324272378; JSESSIONID=abcSeDfK6S9Difmo1B6Kv; Hm_lvt_6654fe18597114a838bf895bc52ed9f2=1482742821; Hm_lpvt_6654fe18597114a838bf895bc52ed9f2=1482744254; tianya1=118768,1482744253,2,84968,20121,1482744134,1,86400,2980,1482745337,1,86400; tianyaATC=tianya,10050102,19056,19193,1483972969166,www.tianya.cn%2Fm%2F; ASL=17176,000wj,dcca9928772757b3; wl_user=id=123259095&k=454556810&chk=5ff3173d1b29693fae584efecd69f613; sso=r=1304101727&sid=&wsid=F6F6F1E978B75A291D793F46E2E9BDD8; user=w=%u9690%u8bc9&id=123259095&f=1; temp=k=566404811&s=&t=1484016106&b=f675bc2b4b2d725ed98dc5ff47111a54&ct=1484016106&et=1486608106; right=web4=n&portal=n; temp4=rm=1ccefe7baabd6c498e87600191945bfa; u_tip=123259095=0; vip=566404811%3D0; ADVC=34b8a677211e4d; __asc=10feae621598619089f270a16a5; __auc=249248b215983a59a2827c8e20d; ty_msg=1484017065199_123259095_0_0_0_0_0_0_0_32_0_0_0; __ptime=1484017065707; time=ct=1484017321.428; Hm_lvt_bc5755e0609123f78d0e816bf7dee255=1483970631; Hm_lpvt_bc5755e0609123f78d0e816bf7dee255=1484017321; __u_a=v2.2.1")
                .addHeader("Host","passport.tianya.cn")
                .addHeader("Origin","https://passport.tianya.cn")
                .addHeader("Pragma","no-cache")
                .addHeader("Referer","https://passport.tianya.cn/m/login.jsp")
                .addHeader("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","Mozilla/5.0 (MeeGo; NokiaN9) AppleWebKit/534.13 (KHTML, like Gecko) NokiaBrowser/8.5.0 Mobile Safari/534.13")
                .build();
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
             String res = response.body().string();
                System.out.println("返回："+res);

                Message message = handler.obtainMessage();
                message.what=GetPage;
                message.obj="http://www.tianya.cn/m/my.jsp?comeURL=http://www.tianya.cn/m/";
                handler.sendMessage(message);

            }
        });


    }
    void getPage(String url)
    {
            Request request = new Request.Builder().url(url)
//                    .header("Cookie","_T_WM=e983a71a8f5306c15186d0356b9f4b5f; SCF=AmCORBYfq_k2PQPwMsRw8ggzi60X2hQPqKT89WY111RmrfMQSWJ-3cUWOi9eA-JjYv_7oRoERGcAUm9A-INrU9A.; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WFlWa.xwqYvkTioGDIOE5065JpX5o2p5NHD95QEe0.RSh2fe0nfWs4Dqcj.i--Xi-zRiKy2i--ciK.Ni-zci--Xi-zRiKn7i--ciKLWi-zX; H5_INDEX=3; H5_INDEX_TITLE=%E4%B8%8D%E6%97%B6%E4%B8%80%E6%9A%B4; SUHB=0h1IucUJGwcNR2; SUB=_2AkMvL6b8dcPhrAFSkfARzW7hZIhH-jyc-s8KAn7oJhMyPRgv7mweqScVzOdiU4SsZWAr2uRCBgeN7I2drw..; M_WEIBOCN_PARAMS=luicode%3D10000011%26lfid%3D102803_ctg1_8999_-_ctg1_8999_home%26fid%3D102803_ctg1_8999_-_ctg1_8999_home%26uicode%3D10000011")
//                    .addHeader("Cookie","_T_WM=a1eaba9d190715b04ac15267a7d5dbd3;SCF=Auu9mejwm4qWf2paSJkWe-eUAUH0Zf3YGbW6_s_mhjw0qsW8NdX59uRox96ZCwRfFQ1SuKHdtPA2W6zlQOfzhIA.;M_WEIBOCN_PARAMS=uicode%3D20000174")
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String resp = response.body().string();
                    System.out.println(resp);
//                    List<HashMap<String,String>> blogs = getBlogList(resp);
//
//                    sendBlogs(bloglist.class,blogs);



                }
            });
    }



//    void initPage()
//    {
//        Request getPage = new Request.Builder().url(loginPage).build();
//        client.newCall(getPage).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String html  = response.body().string();
//                System.out.println(html);
//
//
//
//            }
//        });

//    }
    List<HashMap<String,String>> getBlogList(String resp)
    {
        try {
            JSONObject json = new JSONObject(resp);
            JSONArray cards = json.getJSONArray("cards");
            List<HashMap<String,String>> blogList = new ArrayList<HashMap<String, String>>();
            for(int i=0;i<cards.length();i++)
            {
                JSONObject card=  cards.getJSONObject(i);
//                            System.out.println(card+"和\n");
                if(card.getString("card_type")=="9")
                {
                    JSONObject mblog =card.getJSONObject("mblog");
                    String content=mblog.getString("text");
                    String created_at = mblog.getString("created_at");
                    JSONObject user = new JSONObject(mblog.getString("user"));
                    String username = user.getString("screen_name");
                    HashMap<String,String> blog = new HashMap<String, String>();
                    blog.put("created_at",created_at);
                    blog.put("user",username);
                    blog.put("content",content);
                    blogList.add(blog);

//                                System.out.println(username+"说："+content+"\n");
                }
//
            }
            return blogList;
        }catch (JSONException e)
        {
           return null;
        }

    }




    void sendBlogs(Class<?> cls,List<HashMap<String,String>> list)
    {

        Intent intent = new Intent(MainActivity.this,cls);
        intent.putExtra("list",(Serializable)list);
        startActivity(intent);
    }



    void initForm()
    {
        String url = "https://passport.tianya.cn/m/login.jsp";
        Request request = new Request.Builder().url(url)
                .header("Upgrade-Insecure-Requests","1")
                .addHeader("User-Agent","Mozilla/5.0 (MeeGo; NokiaN9) AppleWebKit/534.13 (KHTML, like Gecko) NokiaBrowser/8.5.0 Mobile Safari/534.13")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    String html = response.body().string();
//                     HtmlParser.GetValue(html,"input[type=hidden]","value");
                System.out.println(html);
                Document doc = Jsoup.parse(html);
                Elements hiddens = doc.getElementsByTag("input");

                Message msg = handler.obtainMessage();
                msg.obj=hiddens;
                msg.what=Login;
                handler.sendMessage(msg);



            }
        });


    }

    void init()
    {

/*


new CookieJar() {
            private final HashMap<String,List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();
            @Override
            public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
                System.out.println("保存"+list);
                cookieStore.put(httpUrl.host(),list);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl httpUrl) {
                    List<Cookie> cookies = cookieStore.get(httpUrl.host());
                System.out.println("请求："+cookies);
                return cookies!=null?cookies:new ArrayList<Cookie>();
            }
        }
 */

        persistentCookieStore = new PersistentCookieStore(getApplicationContext());
        cookieJar=new CookieJarImpl(persistentCookieStore);
        client = new OkHttpClient.Builder().cookieJar(cookieJar).build();
        login=(Button)findViewById(R.id.loginbtn);
        name=(EditText) findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what)
                {
                    case Get:

                        break;
                    case Login:
                        Elements elements=(Elements) msg.obj;
                        int size=0;
                        for(Element e:elements)
                        {
                            System.out.println(e.attr("name")+":"+e.attr("value"));
                            hidform.put(e.attr("name"),e.attr("value"));
                            size+=(e.attr("value")+"&=").length();

                        }





                        String user=name.getText().toString();
                        String psw=password.getText().toString();
                        size+=("&action=f0.1484015219862.0|071529bad60731ec4a970c160e085891|6041de436684c6dd4e7750bee682e7cc|Mozilla/5.0 (MeeGo; NokiaN9) AppleWebKit/534.13 (KHTML, like Gecko) NokiaBrowser/8.5.0 Mobile Safari/534.13|0|1|v2.2").length();

                        size+=(user+psw).length()-1;

                        System.out.println("Content-Length:"+size);
                        login(user,psw,size);

                        break;
                    case GetPage:
                            String url = msg.obj.toString();
                            getPage(url);
                        break;
                    case initLogin:

                        break;
                }
            }
        };
    }
}
