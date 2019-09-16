package pl.kasztany.orlenfirstaid.ui.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import pl.kasztany.orlenfirstaid.R;
import pl.kasztany.orlenfirstaid.model.CategoryContent;

public class AfterCallListAdapter extends BaseAdapter {

    public List<CategoryContent> catz = new ArrayList<>();
    private Context context;

    public AfterCallListAdapter(Context context) {
        this.context = context;
    }

    public void addItem(CategoryContent cat) {
        this.catz.add(cat);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return catz.size();
    }

    @Override
    public Object getItem(int i) {
        return catz.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(context).inflate(R.layout.after_call_list_item, viewGroup, false);
        TextView tv = v.findViewById(R.id.category);
        tv.setText(catz.get(i).getTitlePl());
        tv.setOnClickListener((vide) -> {
            showCallSelectionDialog(catz.get(i), viewGroup);
        });
        return tv;
    }

    private void showCallSelectionDialog(CategoryContent categoryContent, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder(R.layout.videoholder);
        View v = viewHolder.getView(LayoutInflater.from(context), viewGroup);
        WebView wv = v.findViewById(R.id.vwVidContent);
        wv.loadUrl(categoryContent.getContentValue());
        DialogPlus dialog = DialogPlus.newDialog(context)
                .setContentHolder(viewHolder)
                .setExpanded(false)
                .setPadding(20, 100, 10, 100)
                .setGravity(Gravity.CENTER)
                .create();
        dialog.show();
    }
}