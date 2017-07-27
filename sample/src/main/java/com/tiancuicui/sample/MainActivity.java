package com.tiancuicui.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tiancuicui.marqueeview.DataSet;
import com.tiancuicui.marqueeview.MarqueeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private MarqueeView<String> mvMarqueeView;
    private MarqueeView<NoticeModel> marqueeViewSecond;
    private ImageView ivRightArrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mvMarqueeView = (MarqueeView<String>) findViewById(R.id.marqueeView);
        marqueeViewSecond = (MarqueeView<NoticeModel>) findViewById(R.id.marqueeView_second);
        ivRightArrow = (ImageView) findViewById(R.id.iv_right_arrow);
        configureFirst();
        configureSecond();
    }

    private void configureFirst() {

        List<String> list = new ArrayList<>();

        list.add("一顶遮阳帽, 给你夏日专属的美好~");
        list.add("夏日轻亲装, 互动送好礼~");
        list.add("好吃, 比外卖更快~");

        mvMarqueeView.startWithList(list);

        mvMarqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener<String>() {
            @Override
            public void onItemClick(int position, String notice) {
                Toast.makeText(getApplicationContext(), notice, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configureSecond() {
        List<NoticeModel> list = new ArrayList<>();

        list.add(new NoticeModel("1", "一顶遮阳帽, 给你夏日专属的美好~"));
        list.add(new NoticeModel("2", "夏日轻亲装, 互动送好礼~"));
        list.add(new NoticeModel("3", "好吃, 比外卖更快~"));

        marqueeViewSecond.startWithList(list, new DataSet.Formatter<NoticeModel>() {
            @Override
            public String format(NoticeModel noticeModel) {
                return String.format(Locale.CHINA, "公告%s %s", noticeModel.getId(), noticeModel.getDesc());
            }
        });

        marqueeViewSecond.setOnItemClickListener(new MarqueeView.OnItemClickListener<NoticeModel>() {
            @Override
            public void onItemClick(int position, NoticeModel notice) {
                Toast.makeText(getApplicationContext(), notice.getDesc(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class NoticeModel {
        String id;
        String desc;

        public NoticeModel(String id, String desc) {
            this.desc = desc;
            this.id = id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
