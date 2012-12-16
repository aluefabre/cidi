package me.cidi;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class CidiActivity extends ListActivity {
    private ItemDbAdapter mDbHelper;
    private static final int ACTIVITY_CREATE=0;
    private static final int ACTIVITY_EDIT=1;
    private static final int DELETE_ID = Menu.FIRST + 1;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mDbHelper = new ItemDbAdapter(this);
        mDbHelper.open();
        fillData();
        registerForContextMenu(getListView());
        
        Button createButton = (Button) findViewById(R.id.create);
        createButton.setOnClickListener(new View.OnClickListener() {

        	public void onClick(View view) {
        		createItem();
        	}

        });
        
        Button refreshButton = (Button) findViewById(R.id.refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		fillData();
        	}
        });
        
        registerForContextMenu(getListView());
    }

	private void createItem() {
		Intent i = new Intent(CidiActivity.this, ItemEdit.class);
    	startActivityForResult(i, ACTIVITY_CREATE);
	}
    
    private void fillData() {
        // Get all of the rows from the database and create the item list
    	Cursor mNotesCursor = mDbHelper.fetchAllNotes();
        startManagingCursor(mNotesCursor);
        
        
        // Create an array to specify the fields we want to display in the list (only TITLE)
        String[] from = new String[]{ItemDbAdapter.KEY_WRITER, ItemDbAdapter.KEY_BODY, ItemDbAdapter.KEY_ADDRESS, ItemDbAdapter.KEY_TIME};
        
        // and an array of the fields we want to bind those fields to (in this case just text1)
        int[] to = new int[]{R.id.writer_text, R.id.body_text, R.id.address_text, R.id.time_text};
        
        // Now create a simple cursor adapter and set it to display
        SimpleCursorAdapter notes = 
        	    new SimpleCursorAdapter(this, R.layout.item_row, mNotesCursor, from, to);
        setListAdapter(notes);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	super.onListItemClick(l, v, position, id);

    	Intent i = new Intent(this, ItemEdit.class);
    	i.putExtra(ItemDbAdapter.KEY_ROWID, id);
    	startActivityForResult(i, ACTIVITY_EDIT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	super.onActivityResult(requestCode, resultCode, intent);
    	fillData();
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
   	        mDbHelper.deleteNote(info.id);
   	        fillData();
   	        return true;
   	    }
   	    return super.onContextItemSelected(item);

   	}
}