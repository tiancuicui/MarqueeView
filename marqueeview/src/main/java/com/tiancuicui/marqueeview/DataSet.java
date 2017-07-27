package com.tiancuicui.marqueeview;

import java.util.List;

/**
 * Created by like on 2017/7/27.
 */
public class DataSet<T> {

    private List<T> data;
    private Formatter<T> formatter;

    DataSet(List<T> data) {
        this.data = data;
    }

    String format(int position) {
        return format(data.get(position));
    }

    int dataSize() {
        return data == null ? 0 : data.size();
    }

    String format(T t) {
        if (formatter == null) return t.toString();
        else return formatter.format(t);
    }

    public void setFormatter(Formatter<T> formatter) {
        this.formatter = formatter;
    }

    public List<T> getData() {
        return data;
    }

    public T getDataByPosition(int position) {
        return data.get(position);
    }

    public interface Formatter<T> {
        String format(T t);
    }
}
