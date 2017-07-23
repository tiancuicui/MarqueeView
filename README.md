MarqueeView 
==========

名称：垂直滚动的公告栏
-----------
效果图
-----------
![image](https://github.com/tiancuicui/MarqueeView/blob/master/screenshot/screenshot.gif)

使用
-----------
在app的build.gradle中的 dependencies里添加:<br>

```Java
  	dependencies {  
              compile 'com.tiancuicui:marqueeview:1.0.2'
  	}
```
    
属性
-----------

各个属性代表的含义:<br><br>
mvAnimDuration	  一行文字动画执行时间<br>
mvInterval	  两行文字翻页时间间隔<br>
mvTextSize	  文字大小<br>
mvTextColor	  文字颜色<br>
mvGravity	  文字位置:left、center、right<br>
mvSingleLine	  单行设置<br>

XML
-----------

```Java
<com.tiancuicui.marqueeview.MarqueeView
    android:id="@+id/mv_marqueeview"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:mvAnimDuration="1000"
    app:mvInterval="3000"
    app:mvTextColor="@color/white"
    app:mvTextSize="14sp"
    app:mvSingleLine="true"/>
```  
设置列表数据
-----------

```Java
MarqueeView marqueeView = (MarqueeView) findViewById(R.id.mv_marqueeview);

List<String> info = new ArrayList<>();
info.add("this is content No.1");
info.add("this is content No.2");
info.add("this is content No.3");
info.add("this is content No.4");
marqueeView.startWithList(info);
```

设置事件监听
-----------

```Java
marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
    @Override
    public void onItemClick(int position, TextView textView) {
        Toast.makeText(getApplicationContext(), String.valueOf(marqueeView.getPosition()) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();
    }
});
```

重影问题可参考以下解决方案
-----------

```Java
@Override
public void onStart() {
    super.onStart(); 
    marqueeView.startFlipping();
}

@Override
public void onStop() {
    super.onStop();
    marqueeView.stopFlipping();
}
```

有任何问题请联系我(email:tiancuicui0626@163.com 或 qq: 2820218929),欢迎issue,pr!<br>
