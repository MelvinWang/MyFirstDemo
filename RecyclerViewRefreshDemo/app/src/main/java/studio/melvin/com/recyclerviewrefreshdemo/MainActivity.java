package studio.melvin.com.recyclerviewrefreshdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final int REFRESH_COMPLETE = 0X110;
    private RecyclerView mrecyclerView;
    private List<DataBean> dataBean;
    private MyAdapter myAdapter;
    private SwipeRefreshLayout mSwipeLayout;

    private ArrayAdapter<String> mAdapter;
    private List<String> mDatas = new ArrayList<String>(Arrays.asList("Java", "Javascript", "C++", "Ruby", "Json",
            "HTML"));

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    DataBean addBean=new DataBean();
                    addBean.icon=R.mipmap.ic_launcher;
                    addBean.name="新增加2";
                    dataBean.add(0,addBean);
                    myAdapter.notifyDataSetChanged();
                    mSwipeLayout.setRefreshing(false);
                    break;

            }
        }
    };
    //刷新操作
    @Override
    public void onRefresh() {
        //这里完全可以异步请求，然后参数带过去就可以
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrecyclerView = (RecyclerView) findViewById(R.id.id_recycler);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);
        //设置刷新监听
        mSwipeLayout.setOnRefreshListener(this);
        //刷新详细操作
        refresh();
        //加载数据
        loadData();

    }

    private void refresh() {
        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mSwipeLayout.setProgressBackgroundColorSchemeResource(android.R.color.holo_red_light); // 设定下拉圆圈的背景
        mSwipeLayout.setSize(SwipeRefreshLayout.LARGE); // 设置圆圈的大小
//        mrecyclerView.setAdapter(myAdapter);
    }


    //加载数据
    private void loadData() {

        dataBean = new ArrayList<>();
        for (int i = 0; i < DATAS.ICONS.length; i++) {
            DataBean bean = new DataBean();
            bean.icon = DATAS.ICONS[i];
            bean.name = "图片-" + i;
            dataBean.add(bean);
        }

//        dataBean = new ArrayList<DataBean>();
//        DataBean bean=new DataBean();
//        bean.icon= R.mipmap.ic_launcher;
//        bean.text="kwgkwg";
//        dataBean.add(bean);
        //设置布局管理器
        mrecyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        //设置适配器
        myAdapter = new MyAdapter(this, dataBean);
        mrecyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, DataBean data) {
                Toast.makeText(MainActivity.this, data.name, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
