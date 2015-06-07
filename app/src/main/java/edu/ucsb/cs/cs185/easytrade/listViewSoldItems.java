package edu.ucsb.cs.cs185.easytrade;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

/**
 * Created by wengzixia on 6/6/15.
 */
public class listViewSoldItems extends ActionBarActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_solditems);

        User userPassedIn = MainActivity.EasyTradeDataBase.get(MainActivity.CurrentUser.getUsername());
        Log.d("Debug", "=====" + userPassedIn.getUsername());
        String[] strings = {"","","","",""};
        Log.d("Debug", "boughtItem size is" + userPassedIn.getBoughtItems().size());
        for(int i = 0; i<userPassedIn.getPostedItems().size();i++){
            strings[i] = userPassedIn.getPostedItems().get(i).getItemTitle();
        }
//        String[] string = new String[9];
//        for (int j=0;j<9;j++){
//            string[j] = strings[j+1];
//        }
        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(new listViewAdapterforSold(this, strings));
    }
}
