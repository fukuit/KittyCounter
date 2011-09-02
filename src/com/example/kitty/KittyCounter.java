package com.example.kitty;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class KittyCounter extends ListActivity {
	private ArrayAdapter<KittyItem> adapter;
    private static final int DELETE_ID = Menu.FIRST + 1;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		adapter = new ArrayAdapter<KittyItem>(this,
				android.R.layout.simple_list_item_1);
		setListAdapter(adapter);

		final Button btn = (Button) findViewById(R.id.exec_btn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				doCalculate();
			}
		});
		
        registerForContextMenu(getListView());
	}

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	KittyItem item = (KittyItem)l.getAdapter().getItem(position);
		StringBuilder sb = new StringBuilder();
		sb.append("êgí∑:").append(item.getHeight()).append("cm / ëÃèd:")
				.append(item.getWeight()).append("kg");
		Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
	}

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
            ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case DELETE_ID:
                AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
                KittyItem kitty = (KittyItem)getListView().getAdapter().getItem(info.position);
                adapter.remove(kitty);
                return true;
        }
        return super.onContextItemSelected(item);
    }
    
	private void doCalculate() {
		EditText hEdit = (EditText) findViewById(R.id.height);
		EditText wEdit = (EditText) findViewById(R.id.weight);
		double h = Double.parseDouble(hEdit.getText().toString());
		double w = Double.parseDouble(wEdit.getText().toString());
		adapter.add(new KittyItem(w, h));
	}
}