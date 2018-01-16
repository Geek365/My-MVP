package home.times.designpatterns;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.List;

import home.times.designpatterns.adapter.AdapterActivity;
import home.times.designpatterns.builder.BuilderPatternActivity;
import home.times.designpatterns.factory.FactoryPatternActivity;
import home.times.designpatterns.proxy.normal.StaticProxyActivity;


public class DesignPatternActivity extends AppCompatActivity {



    private Context mContext;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<ItemInfo> demos = new ArrayList<>();

    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_design_pattern);
        setData();
        initView();
    }

    private void setData() {
        demos.add(new ItemInfo(R.string.factory, FactoryPatternActivity.class));
        demos.add(new ItemInfo(R.string.builder, BuilderPatternActivity.class));
        demos.add(new ItemInfo(R.string.static_proxy, StaticProxyActivity.class));
        demos.add(new ItemInfo(R.string.adapter, AdapterActivity.class));

    }


    private void initView() {
        toolbar = V.f(this, R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = V.f(this, R.id.list);
        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(myAdapter);
    }




    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {


        public MyAdapter() {

        }

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.demo_info_item, null);
            MyHolder holder = new MyHolder(view);
            return holder;
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
