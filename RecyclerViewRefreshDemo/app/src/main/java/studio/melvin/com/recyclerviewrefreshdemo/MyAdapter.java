package studio.melvin.com.recyclerviewrefreshdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by melvin on 2015/12/18.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener {


    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v, (DataBean) v.getTag());
        }
    }

    //定义接口，实现点击事件
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, DataBean data);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    Context context;
    List<DataBean> dataBean;

    public MyAdapter(Context context, List<DataBean> dataBean) {
        this.context = context;
        this.dataBean = dataBean;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.main_list_item, null);

        MyViewHolder vh = new MyViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //绑定数据
        holder.setData(dataBean.get(position));
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(dataBean.get(position));
    }

    @Override
    public int getItemCount() {
        if (dataBean != null) {
            return dataBean.size();
        }
        return 0;
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView text;

        public MyViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.item_iv);
            text = (TextView) itemView.findViewById(R.id.tv_item);
        }


        public void setData(DataBean data) {
            image.setImageResource(data.icon);
            text.setText(data.name);

        }

    }


}