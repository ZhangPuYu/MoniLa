package com.bawei.monilast;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bawei.monilast.adapter.HomeAdapter;
import com.bawei.monilast.base.BaseActivity;
import com.bawei.monilast.base.BaseFragment;
import com.bawei.monilast.tab.Tab_One;
import com.bawei.monilast.tab.Tab_Three;
import com.bawei.monilast.tab.Tab_Two;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ImageView imageView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<BaseFragment> list = new ArrayList<>();

    @Override
    protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        imageView = findViewById(R.id.image_icon);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initData() {
        list.add(new Tab_One());
        list.add(new Tab_Two());
        list.add(new Tab_Three());
        viewPager.setAdapter(new HomeAdapter(getSupportFragmentManager(),list));
    }

    @Override
    protected void initListener() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000 && resultCode == RESULT_OK){
            Uri uri = data.getData();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
