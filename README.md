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
              compile 'com.tiancuicui:marqueeview:1.0.3'
  	}
```
    
属性
-----------

<div>
    <table border="0">
	  <tr>
	    <th>自定义属性</th>
	    <th>含义</th>
	  </tr>
	  <tr>
	    <td>mvAnimDuration</td>
	    <td>一行文字动画执行时间</td>
	  </tr>
     <tr>
	    <td>mvInterval</td>
	    <td>两行文字翻页时间间隔</td>
	  </tr>
     <tr>
	    <td>mvTextSize</td>
	    <td>文字大小</td>
	  </tr>
     <tr>
	    <td>mvTextColor</td>
	    <td>文字颜色</td>
	  </tr>
     <tr>
	    <td>mvGravity</td>
	    <td>文字显示的位置:left、center、right</td>
	  </tr>
     <tr>
	    <td>mvSingleLine</td>
	    <td>单行设置: true、false</td>
	  </tr>
    </table>
</div>

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
MarqueeView marqueeView = (MarqueeView<String>) findViewById(R.id.mv_marqueeview);

List<String> info = new ArrayList<>();
info.add("this is content No.1");
info.add("this is content No.2");
info.add("this is content No.3");
info.add("this is content No.4");
marqueeView.startWithList(info);
```

或使用自定义的模型

```java
MarqueeView marqueeView = (MarqueeView<NoticeModel>) findViewById(R.id.mv_marqueeview);
list.add(new NoticeModel("1", "一顶遮阳帽, 给你夏日专属的美好~"));
list.add(new NoticeModel("2", "夏日轻亲装, 互动送好礼~"));
list.add(new NoticeModel("3", "好吃, 比外卖更快~"));

marqueeViewSecond.startWithList(list, new DataSet.Formatter<NoticeModel>() {
    @Override
    public String format(NoticeModel noticeModel) {
    		// 格式化要是显示的内容
		return String.format(Locale.CHINA, "公告%s %s", noticeModel.getId(), noticeModel.getDesc());
    }
});
```

设置事件监听
-----------

```Java
marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener<NoticeModel>() {
    @Override
    public void onItemClick(int position, NoticeModel notice) {
        Toast.makeText(getApplicationContext(), notice.getDesc(), Toast.LENGTH_SHORT).show();
    }
});
```
有任何问题请联系我(email:tiancuicui0626@163.com 或 qq: 2820218929),欢迎issue,pr!<br>
