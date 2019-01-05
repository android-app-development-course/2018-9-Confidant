package com.example.chickenattack.zhiyin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int Kind=0;
    public static String[] Kind_string={"无","生活","体育","经济","娱乐","教育","美食","历史","健康","人文"};

    private boolean Pattern_simple,Pattern_no_picture,Pattern_nav_filter;

    private ImageView header;

    private TextView header_name,header_sign;

    private List<Item_list> Item_List = new ArrayList<>();

    private JsonManager jsonManager;

    private String JsonData="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initEvent();

        Pattern_simple=false;
        Pattern_no_picture=false;
        Pattern_nav_filter=false;


        jsonManager=new JsonManager(this);                          //访问服务器，按要求获取数据
        if(Kind!=0){
            Item_List.clear();
            Item_List=jsonManager.getdata(JsonData);
        }
        ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this, R.layout.activity_item, Item_List);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                    //设置筛选信息
                Intent intent=new Intent(MainActivity.this,KindManager.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {            //模式切换
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_simple) {
            Pattern_simple=true;
        } else if (id == R.id.nav_no_picture) {         //无图模式
            Pattern_no_picture=true;
            Pattern_nav_filter=false;
            for(int i=0;i<Item_List.size();i++){
                Item_List.get(i).getItem_main().getDetailsImage().clear();
                Item_List.get(i).getItem_main().getDetailsImage().add("null");
                Item_List.get(i).getItem_other().getDetailsImage().clear();
                Item_List.get(i).getItem_other().getDetailsImage().add("null");
                ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this, R.layout.activity_item, Item_List);
                ListView listView = (ListView) findViewById(R.id.listview);
                listView.setAdapter(itemAdapter);
            }
        } else if (id == R.id.nav_filter) {         //普通筛选模式
            Pattern_nav_filter=true;
            Pattern_no_picture=false;

            Item_List.clear();
            Kind=0;
            Item_List=jsonManager.getdata(JsonData);
            ItemAdapter itemAdapter = new ItemAdapter(MainActivity.this, R.layout.activity_item, Item_List);
            ListView listView = (ListView) findViewById(R.id.listview);
            listView.setAdapter(itemAdapter);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void initEvent() {
        startNetThread("192.168.199.217",8080,"data");
    }

    private void startNetThread(final String host, final int port, final String data) {
        new Thread() {
            public void run() {
                try {
                    //创建客户端对象
                    Socket socket = new Socket(host, port);
                    //获取客户端对象的输出流
                    OutputStream outputStream = socket.getOutputStream();
                    //把内容以字节流的形式写入(data).getBytes();
                    outputStream.write(data.getBytes());
                    //刷新流管道
                    outputStream.flush();
                    System.out.println("打印客户端中的内容：" + socket);
                    //拿到客户端输入流
                    InputStream is = socket.getInputStream();

                    byte[] bytes = new byte[2048];
                    //回应数据
                    String tempdata="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

                    while(!tempdata.substring(tempdata.length()-37,tempdata.length()).matches("huchaoshunlixiongbochenzijieluojiajun")){
                        int n = is.read(bytes);
                        tempdata=new String(bytes,0,n);
                        JsonData+=tempdata;;
                    }
                    JsonData=JsonData.substring(0,JsonData.length()-37);

                    //关闭流
                    is.close();
                    //关闭客户端
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //启动线程
        }.start();
    }



}
