package com.bitrient.mcchymns.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bitrient.mcchymns.R;

/**
 * @author Cinfwat Probity <czprobity@bitrient.com>
 * @since 6/14/15
 */
public class EmptiableRecyclerView extends RecyclerView {

    private View emptyView;
    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();

            if (adapter != null && emptyView != null) {
                if (adapter.getItemCount() == 0) {
                    emptyView.setVisibility(VISIBLE);
                    EmptiableRecyclerView.this.setVisibility(GONE);
                } else {
                    emptyView.setVisibility(GONE);
                    EmptiableRecyclerView.this.setVisibility(VISIBLE);
                }
            }
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            Adapter<?> adapter = getAdapter();

            if (adapter != null && emptyView != null) {
//                onChanged();

                TextView message = (TextView) emptyView.findViewById(R.id.empty_favorites_message);
                ImageView icon = (ImageView) emptyView.findViewById(R.id.empty_favorites_icon);

                message.setText(getResources().getText(R.string.no_hymns_found));
                icon.setImageResource(R.mipmap.ic_search);
            }
        }
    };

    public EmptiableRecyclerView(Context context) {
        super(context);
    }

    public EmptiableRecyclerView(Context context, AttributeSet set) {
        super(context, set);
    }

    public EmptiableRecyclerView(Context context, AttributeSet set, int defStyle) {
        super(context, set, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        if (adapter != null) {
            adapter.registerAdapterDataObserver(emptyObserver);
        }

        emptyObserver.onChanged();
    }

    /**
     * This sets the view to be displayed when RecycleView is empty.
     * @param emptyView
     */
    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }
}