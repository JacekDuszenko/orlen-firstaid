package pl.kasztany.orlenfirstaid.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import pl.kasztany.orlenfirstaid.model.CategoryContent;

public class VideoAdapter extends BaseAdapter {

    List<CategoryContent> catContent;

    @Override
    public int getCount() {
        return catContent.size();
    }

    @Override
    public Object getItem(int i) {
        return catContent.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
