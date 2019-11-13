package com.example.itime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 901;
    private AppBarConfiguration mAppBarConfiguration;
    private FloatingActionButton fab;
    private ListView listViewTime;
    private List<Timer> listTime=new ArrayList<>();
    private TimeAdapter timeAdapter;
    private  Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = findViewById(R.id.fab);

      DrawerLayout drawer = findViewById(R.id.drawer_layout);
      NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        init();

        listViewTime=(ListView) findViewById(R.id.list_view_main);
        timeAdapter=new TimeAdapter(MainActivity.this,R.layout.list_view_main_time,listTime);
        listViewTime.setAdapter(timeAdapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, AddNewActivity.class);

                startActivityForResult(intent,REQUEST_CODE);

            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE:
                if(resultCode==RESULT_OK){
                    String biaoti=data.getStringExtra("biaoti");
                    String beizhu=data.getStringExtra("beizhu");
                    String riqi=data.getStringExtra("riqi");
                    //随机生成封面图片
                    Random r = new Random();

                    int num = (int) (Math.random() * 8 + 1);
                    switch(num){
                        case 1:
                            listTime.add(new Timer(biaoti,riqi,R.drawable.imagine_view_cover2));
                            break;
                        case 2:
                            listTime.add(new Timer(biaoti,riqi,R.drawable.imagine_view_cover3));
                            break;
                        case 3:
                            listTime.add(new Timer(biaoti,riqi,R.drawable.imagine_view_cover4));
                            break;
                        case 4:
                            listTime.add(new Timer(biaoti,riqi,R.drawable.imagine_view_cover5));
                            break;
                        case 5:
                            listTime.add(new Timer(biaoti,riqi,R.drawable.imagine_view_cover6));
                            break;
                        case 6:
                            listTime.add(new Timer(biaoti,riqi,R.drawable.imagine_view_cover7));
                            break;
                        case 7:
                            listTime.add(new Timer(biaoti,riqi,R.drawable.imagine_view_cover8));
                            break;
                        case 8:
                            listTime.add(new Timer(biaoti,riqi,R.drawable.imagine_view_cover9));
                            break;
                    }



                    timeAdapter.notifyDataSetChanged();
                }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void init(){
        listTime.add(new Timer("my first timer","2019年12月31日",R.drawable.imagine_view_cover2));
    }



    class TimeAdapter extends ArrayAdapter<Timer> {

        private int resourceId;

        public TimeAdapter(Context context, int resource, List<Timer> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Timer timer = getItem(position);//获取当前项的实例
            View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            ((ImageView) view.findViewById(R.id.image_view_cover)).setImageResource(timer.getCoverResourceId());
            ((TextView) view.findViewById(R.id.text_view_name)).setText(timer.getTitle());
            ((TextView) view.findViewById(R.id.text_view_ddl)).setText(timer.getDdl());
            return view;
        }
    }

}
