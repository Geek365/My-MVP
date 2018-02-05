package huyifei.mymvp;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.networkbench.agent.impl.NBSAppAgent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

import home.smart.fly.http.activity.RxJava2MainActivity;
import home.smart.fly.httpurlconnectiondemo.HttpDemoActivity;
import home.smart.fly.httpurlconnectiondemo.RxJavaDemoActivity;
import home.smart.fly.httpurlconnectiondemo.retrofit2.Retrofit2DemoActivity;
import home.times.designpatterns.DesignPatternActivity;
import huyifei.mymvp.architecture.mvc.MVCActivity;
import huyifei.mymvp.architecture.mvp.MVPActivity;
import huyifei.mymvp.broadcastreceiver.BroadcastReceiverActivity;
import huyifei.mymvp.classloader.ClassLoaderActivity;
import huyifei.mymvp.classloader.SimpleHotFixActivity;
import huyifei.mymvp.datastorage.DataStorageActivity;
import huyifei.mymvp.service.ServiceActivity;
import huyifei.mymvp.service.ThreadLocalTestActivity;
import huyifei.mymvp.service.ipc.AIDLActivity;
import huyifei.mymvp.test.HashMapActivity;
import huyifei.mymvp.util.V;

/**
 * Created by rookie on 2016/11/2.
 */

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private Context mContext;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<ItemInfo> demos = new ArrayList<>();

    private Toolbar toolbar;
    private Arrays mArrays;
    AsyncTask mAsyncTask;
    InvocationHandler m;
    Proxy mProxy;

    Handler mHandler;
    HashMapActivity map;
    ArrayList mArrayList;
    Deque mDeque;
    Stack mStack;
    Vector mVector;
    LinkedList mLinkedList;
    Comparable mComparable;

    ClassLoader mClassLoader;

    NotificationManager mNotificationManager;
    NotificationCompat mNotificationCompat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        setData();
        initView();

        NBSAppAgent
                .setLicenseKey("52d5f19f17d849d1b7cf04dc686969dd")
                .withLocationServiceEnabled(true)
                .start(this.getApplicationContext());

        testCopy();
    }

    private void testCopy() {
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        int[] b = new int[a.length * 2];
        b[0] = 12;
        b[1] = 13;
        b[2] = 14;


        System.arraycopy(a, 0, b, 3, a.length);

        for (int i = 0; i < b.length; i++) {
            System.out.printf("a[%d]=%d\n", i, b[i]);
        }


        char[] src = {'a', 'b', 'c', 'd', 'e', 'f'};
        System.arraycopy(src, 0, src, 1, src.length - 1);
        System.out.println("the length=" + src.length);
        System.out.println("now src=" + Arrays.toString(src));


        String code = "abcdefghijklmn";

        int num = code.indexOf("cde");

        System.out.println("the index of cde at abcdefghijklmn is " + num);
    }


    private void setData() {
        demos.add(new ItemInfo(R.string.classloader, LifeCycleActivity.class));
        demos.add(new ItemInfo(R.string.classloader, ClassLoaderActivity.class));
        demos.add(new ItemInfo(R.string.hotfix, SimpleHotFixActivity.class));
        demos.add(new ItemInfo(R.string.service, MemoryLeakActivity.class));
        demos.add(new ItemInfo(R.string.simpleRxJava, RxJavaDemoActivity.class));
        demos.add(new ItemInfo(R.string.AndroidHttp, HttpDemoActivity.class));
        demos.add(new ItemInfo(R.string.Retrofit, Retrofit2DemoActivity.class));
        demos.add(new ItemInfo(R.string.RxJava2, RxJava2MainActivity.class));
        demos.add(new ItemInfo(R.string.MVC, MVCActivity.class));
        demos.add(new ItemInfo(R.string.Mvp, MVPActivity.class));
        demos.add(new ItemInfo(R.string.design, DesignPatternActivity.class));
        demos.add(new ItemInfo(R.string.GlideUse, GlideActivity.class));
        demos.add(new ItemInfo(R.string.DataStorage, DataStorageActivity.class));
        demos.add(new ItemInfo(R.string.BroadcastReceiver, BroadcastReceiverActivity.class));
        demos.add(new ItemInfo(R.string.service, ServiceActivity.class));
        demos.add(new ItemInfo(R.string.aidl, AIDLActivity.class));
        demos.add(new ItemInfo(R.string.service, HashMapActivity.class));
        demos.add(new ItemInfo(R.string.threadLocal, ThreadLocalTestActivity.class));
    }


    private void initView() {
        toolbar = V.f(this, R.id.toolbar);
        setSupportActionBar(toolbar);
        swipeRefreshLayout = V.f(this, R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);
        recyclerView = V.f(this, R.id.list);
        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(myAdapter);

        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 1500);
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {


        public MyAdapter() {

        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.demo_info_item, null);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position) {
            holder.title.setText(demos.get(position).activitys.getSimpleName());
            holder.desc.setText(demos.get(position).desc);
            holder.itemshell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, demos.get(position).activitys);
                    startActivity(intent);
                }
            });
        }


        @Override
        public long getItemId(int id) {
            return id;
        }

        @Override
        public int getItemCount() {
            return demos.size();
        }


        class MyHolder extends RecyclerView.ViewHolder {
            TextView title, desc;
            LinearLayout itemshell;

            public MyHolder(View itemView) {
                super(itemView);
                title = V.f(itemView, R.id.title);
                desc = V.f(itemView, R.id.desc);
                itemshell = V.f(itemView, R.id.itemshell);
            }
        }

    }


    private class ItemInfo {
        private final int desc;
        private final Class<? extends Activity> activitys;

        public ItemInfo(int desc, Class<? extends Activity> demoClass) {
            this.desc = desc;
            this.activitys = demoClass;
        }
    }

}


