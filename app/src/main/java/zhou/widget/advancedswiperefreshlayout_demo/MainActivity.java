package zhou.widget.advancedswiperefreshlayout_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import zhou.widget.advancedswiperefreshlayout.AdvancedSwipeRefreshLayout;

public class MainActivity extends AppCompatActivity {

    private boolean loadMore, error;
    private AdvancedSwipeRefreshLayout advancedSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        advancedSwipeRefreshLayout = (AdvancedSwipeRefreshLayout) findViewById(R.id.root);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "load more");
        menu.add(0, 1, 1, "error");
        menu.add(0, 2, 2, "Refresh");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                advancedSwipeRefreshLayout.setLoadMore(!advancedSwipeRefreshLayout.isLoadMore());
                return true;
            case 1:
                advancedSwipeRefreshLayout.setError(!advancedSwipeRefreshLayout.isError());
                return true;
            case 2:
                advancedSwipeRefreshLayout.setRefreshing(!advancedSwipeRefreshLayout.isRefreshing());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
