package com.tiancuicui.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiancuicui.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MarqueeView mvMarqueeView;
    private ImageView ivRightArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mvMarqueeView = (MarqueeView) findViewById(R.id.marqueeView);
        ivRightArrow = (ImageView) findViewById(R.id.iv_right_arrow);
        List<String> list = new ArrayList<>();

        String s1 = new String("一顶遮阳帽, 给你夏日专属的美好~");
        String s2 = new String("夏日轻亲装, 互动送好礼~");
        String s3 = new String("好吃, 比外卖更快~");

        list.add(s1);
        list.add(s2);
        list.add(s3);

        mvMarqueeView.startWithList(list);
        mvMarqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getApplicationContext(), textView.getText()+"", Toast.LENGTH_SHORT).show();
            }
        });

        ivRightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "去公告列表页", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
