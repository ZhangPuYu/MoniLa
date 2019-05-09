package com.bawei.monilast.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.monilast.R;
import com.bawei.monilast.bean.Bean;
import com.bumptech.glide.Glide;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    public static final int TASK_ONE = 0;
    public static final int TASK_TWO = 1;
    public static final int TASK_THREE = 2;

    private Context context;
    private List<Bean.ResultBean.DataBean> list;

    public ListAdapter(Context context, List<Bean.ResultBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int viewType = getItemViewType(position);
        switch (viewType){
            case TASK_ONE:
                ViewHolderOne viewHolderOne;
                if(convertView == null){
                    viewHolderOne = new ViewHolderOne();
                    convertView = View.inflate(context, R.layout.list_one,null);
                    viewHolderOne.image_one = convertView.findViewById(R.id.image_one);
                    viewHolderOne.text_aaa = convertView.findViewById(R.id.text_aaa);
                    convertView.setTag(viewHolderOne);
                }else{
                    viewHolderOne = (ViewHolderOne) convertView.getTag();
                }
                Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(viewHolderOne.image_one);
                viewHolderOne.text_aaa.setText(list.get(position).getTitle());
                break;

            case TASK_TWO:
                ViewHolderTwo viewHolderTwo;
                if(convertView == null){
                    viewHolderTwo = new ViewHolderTwo();
                    convertView = View.inflate(context, R.layout.list_two,null);
                    viewHolderTwo.image_one = convertView.findViewById(R.id.image_one);
                    viewHolderTwo.image_two = convertView.findViewById(R.id.image_two);
                    viewHolderTwo.text_aaa = convertView.findViewById(R.id.text_aaa);
                    convertView.setTag(viewHolderTwo);
                }else{
                    viewHolderTwo = (ViewHolderTwo) convertView.getTag();
                }
                Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(viewHolderTwo.image_one);
                Glide.with(context).load(list.get(position).getThumbnail_pic_s02()).into(viewHolderTwo.image_two);
                viewHolderTwo.text_aaa.setText(list.get(position).getTitle());
                break;

            case TASK_THREE:
                ViewHolderThree viewHolderThree;
                if(convertView == null){
                    viewHolderThree = new ViewHolderThree();
                    convertView = View.inflate(context, R.layout.list_three,null);
                    viewHolderThree.image_one = convertView.findViewById(R.id.image_one);
                    viewHolderThree.image_two = convertView.findViewById(R.id.image_two);
                    viewHolderThree.image_three = convertView.findViewById(R.id.image_three);
                    viewHolderThree.text_aaa = convertView.findViewById(R.id.text_aaa);
                    convertView.setTag(viewHolderThree);
                }else{
                    viewHolderThree = (ViewHolderThree) convertView.getTag();
                }
                Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(viewHolderThree.image_one);
                Glide.with(context).load(list.get(position).getThumbnail_pic_s02()).into(viewHolderThree.image_two);
                Glide.with(context).load(list.get(position).getThumbnail_pic_s03()).into(viewHolderThree.image_three);
                viewHolderThree.text_aaa.setText(list.get(position).getTitle());
                break;
        }

        return convertView;
    }

    class ViewHolderOne{
        ImageView image_one;
        TextView text_aaa;
    }

    class ViewHolderTwo{
        ImageView image_one,image_two;
        TextView text_aaa;
    }

    class ViewHolderThree{
        ImageView image_one,image_two,image_three;
        TextView text_aaa;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        String thumbnail_pic_s = list.get(position).getThumbnail_pic_s();
        String thumbnail_pic_s02 = list.get(position).getThumbnail_pic_s02();
        String thumbnail_pic_s03 = list.get(position).getThumbnail_pic_s03();
        if(thumbnail_pic_s != null && thumbnail_pic_s02 == null && thumbnail_pic_s03 == null){
            return TASK_ONE;
        }else if(thumbnail_pic_s != null && thumbnail_pic_s02 != null && thumbnail_pic_s03 == null){
            return TASK_TWO;
        }else if(thumbnail_pic_s != null && thumbnail_pic_s02 != null && thumbnail_pic_s03 != null){
            return TASK_THREE;
        }else{
            return TASK_ONE;
        }
    }
}
