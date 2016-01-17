package zhou.widget.advancedswiperefreshlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhou on 16-1-17.
 */
public class AdvancedSwipeRefreshLayout extends SwipeRefreshLayout {

    private boolean mMeasured = false;
    private boolean mPreMeasureRefreshing = false;
    private boolean isLoadMore, isError;
    private Snackbar snackbar;
    private String loadMoreText;
    private String cancelText;
    private String retryText;
    private String errorText;

    private OnClickListener cancelAction, errorAction;


    private OnClickListener onCancelClickListener, onRetryClickListener;

    public AdvancedSwipeRefreshLayout(Context context) {
        this(context, null);
    }

    public AdvancedSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AdvancedSwipeRefreshLayout);

        loadMoreText = typedArray.getString(R.styleable.AdvancedSwipeRefreshLayout_load_more_text);
        cancelText = typedArray.getString(R.styleable.AdvancedSwipeRefreshLayout_cancel_text);
        retryText = typedArray.getString(R.styleable.AdvancedSwipeRefreshLayout_retry_text);
        errorText = typedArray.getString(R.styleable.AdvancedSwipeRefreshLayout_error_text);

        if (loadMoreText == null) {
            loadMoreText = context.getString(R.string.text_load_more_text);
        }

        if (cancelText == null) {
            cancelText = context.getString(R.string.text_cancel_text);
        }

        if (retryText == null) {
            retryText = context.getString(R.string.text_retry_text);
        }

        if (errorText == null) {
            errorText = context.getString(R.string.text_error_text);
        }


        typedArray.recycle();

        cancelAction = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelClickListener != null) {
                    onCancelClickListener.onClick(v);
                }
            }
        };

        errorAction = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRetryClickListener != null) {
                    onRetryClickListener.onClick(v);
                }
            }
        };
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!mMeasured) {
            mMeasured = true;
            setRefreshing(mPreMeasureRefreshing);
        }
    }

    public void setOnCancelClickListener(OnClickListener onCancelClickListener) {
        this.onCancelClickListener = onCancelClickListener;
    }


    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        this.onRetryClickListener = onRetryClickListener;
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        if (mMeasured) {
            super.setRefreshing(refreshing);
        } else {
            mPreMeasureRefreshing = refreshing;
        }
    }


    public void setLoadMore(boolean loadMore) {
        if (loadMore) {
            snackbar = Snackbar.make(this, loadMoreText, Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(cancelText, cancelAction);
            snackbar.show();
        } else {
            if (snackbar != null) {
                snackbar.dismiss();
            }
        }
        isLoadMore = loadMore;
        isError = false;
    }

    public void setError(boolean error) {
        if (error) {
            snackbar = Snackbar.make(this, errorText, Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(retryText, errorAction);
            snackbar.show();
        } else {
            if (snackbar != null) {
                snackbar.dismiss();
            }
        }
        isError = error;
        isLoadMore = false;
    }

    public void setLoadMoreText(String loadMoreText) {
        this.loadMoreText = loadMoreText;
    }

    public void setCancelText(String cancelText) {
        this.cancelText = cancelText;
    }

    public void setRetryText(String retryText) {
        this.retryText = retryText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public boolean isError() {
        return isError;
    }

    public boolean isLoadMore() {
        return isLoadMore;
    }
}
