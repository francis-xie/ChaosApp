

package com.basic.face.widget.searchview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.basic.code.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索结果选项列表适配器
 *

 * @since 2019/1/2 下午4:04
 */
public class SearchAdapter extends BaseAdapter implements Filterable, AbstractSearchFilter.OnFilterResultListener {

    private List<String> mData;
    private String[] mSuggestions;
    private Drawable mSuggestionIcon;
    private LayoutInflater mInflater;
    private boolean mEllipsize;

    private AbstractSearchFilter mSearchFilter;

    public SearchAdapter(Context context, String[] suggestions) {
        mInflater = LayoutInflater.from(context);
        mData = new ArrayList<>();
        mSuggestions = suggestions;
        setSearchFilter(new DefaultSearchFilter());
    }

    public SearchAdapter(Context context, String[] suggestions, Drawable suggestionIcon, boolean ellipsize) {
        mInflater = LayoutInflater.from(context);
        mData = new ArrayList<>();
        mSuggestions = suggestions;
        mSuggestionIcon = suggestionIcon;
        mEllipsize = ellipsize;
        setSearchFilter(new DefaultSearchFilter());
    }

    @Override
    public Filter getFilter() {
        return mSearchFilter;
    }

    /**
     * 设置搜索过滤器
     *
     * @param searchFilter
     * @return
     */
    public SearchAdapter setSearchFilter(AbstractSearchFilter searchFilter) {
        mSearchFilter = searchFilter;
        mSearchFilter.setSuggestions(mSuggestions);
        mSearchFilter.setOnFilterResultListener(this);
        return this;
    }

    @Override
    public void publishResults(List<String> results) {
        mData = results;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        SuggestionsViewHolder viewHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.xui_layout_search_view_suggest_item, parent, false);
            viewHolder = new SuggestionsViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (SuggestionsViewHolder) convertView.getTag();
        }

        String currentListData = (String) getItem(position);

        viewHolder.textView.setText(currentListData);
        if (mEllipsize) {
            viewHolder.textView.setSingleLine();
            viewHolder.textView.setEllipsize(TextUtils.TruncateAt.END);
        }

        return convertView;
    }


    private class SuggestionsViewHolder {

        TextView textView;
        ImageView imageView;

        public SuggestionsViewHolder(View convertView) {
            textView = convertView.findViewById(R.id.suggestion_text);
            if (mSuggestionIcon != null) {
                imageView = convertView.findViewById(R.id.suggestion_icon);
                imageView.setImageDrawable(mSuggestionIcon);
            }
        }
    }
}