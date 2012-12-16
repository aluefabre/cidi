package me.cidi;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;

public class ItemEdit extends Activity {
    private Long mRowId;
	private EditText mBodyText;
	private EditText mWriterText;
    private ItemDbAdapter mDbHelper;
	private LocationManager locmgr;
	protected Location location;
	protected Address mAddress;
	private TextView latitudeText;
	private TextView addressText;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item_edit);
        setTitle(R.string.edit_item);
        
        if(savedInstanceState != null){
        	mRowId = (Long) savedInstanceState.getSerializable(ItemDbAdapter.KEY_ROWID);
        }else{
        	mRowId = null;
        }
        
        if (mRowId == null) {
        	Bundle extras = getIntent().getExtras();
        	if(extras!=null){
        		mRowId = extras.getLong(ItemDbAdapter.KEY_ROWID);
        	}
        }
        
        mDbHelper = new ItemDbAdapter(this);

        mDbHelper.open();
        
        mWriterText = (EditText) findViewById(R.id.writer);
        mBodyText = (EditText) findViewById(R.id.body);
        latitudeText = (TextView) findViewById(R.id.latitude);
        addressText = (TextView) findViewById(R.id.address);
        Button confirmButton = (Button) findViewById(R.id.confirm);
        
        confirmButton.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        	    setResult(RESULT_OK);
        	    finish();
        	}
        });
        
        if(mRowId != null) {
        	populateFieldsForEdit();
        }
        if(mRowId==null){
        	//grab the location manager service
            locmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            prepareForCreate();
        }
    }

	private void prepareForCreate() {
		latitudeText.setText("");
		addressText.setText("等待定位中...");
		resigterLocationUpdates();
	}

	private void populateFieldsForEdit() {
		if(mRowId != null) {
			Cursor note = mDbHelper.fetchNote(mRowId);
			startManagingCursor(note);
			mWriterText.setText(note.getString(note.getColumnIndexOrThrow(ItemDbAdapter.KEY_WRITER)));
			mBodyText.setText(note.getString(note.getColumnIndexOrThrow(ItemDbAdapter.KEY_BODY)));
			String latitude = note.getString(note.getColumnIndexOrThrow(ItemDbAdapter.KEY_LATITUDE));
			String longitude = note.getString(note.getColumnIndexOrThrow(ItemDbAdapter.KEY_LONGITUDE));
			latitudeText.setText("Latitude: " + latitude + ", Longitude:" + longitude);
			addressText.setText(note.getString(note.getColumnIndexOrThrow(ItemDbAdapter.KEY_ADDRESS)));
		}
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(ItemDbAdapter.KEY_ROWID, mRowId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
		if(mRowId==null){
			locmgr.removeUpdates(onLocationChange);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if(mRowId!=null){
			populateFieldsForEdit();
		}
		if(mRowId==null){
			resigterLocationUpdates();	
		}
	}

	private void resigterLocationUpdates() {
		locmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 200, 100.0f, onLocationChange);
		locmgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 200, 100.0f, onLocationChange);
	}
	
	//Start a location listener
    LocationListener onLocationChange = new LocationListener() {

		public void onLocationChanged(Location loc) {
			if (loc != null) {
	            //sets and displays the lat/long when a location is provided
	            location = loc;
	            TextView latitudeText = (TextView) findViewById(R.id.latitude);
	            latitudeText.setText("Latitude: " + location.getLatitude() + ", Longitude:" + location.getLongitude());
	            
	            try {
	            	mAddress = LocationHelper.getAddressByLocation(ItemEdit.this, location);  
					
					TextView addressText = (TextView) findViewById(R.id.address);
					String address = LocationHelper.buildAddressLine(mAddress);
					
					addressText.setText(address);
				} catch (Exception e) {
					addressText.setText(e.getMessage());
				}
			}
        }

        public void onProviderDisabled(String provider) {
        // required for interface, not used
        }
         
        public void onProviderEnabled(String provider) {
        // required for interface, not used
        }
         
        public void onStatusChanged(String provider, int status,  Bundle extras) {
        // required for interface, not used
        }
    };
	
	private void saveState() {
		String writer = mWriterText.getText().toString();
		String body = mBodyText.getText().toString();

		if (mRowId == null) {
			double latitude = 0;
			double longitude = 0;
			String address = "unknown";
			if(location!=null){
				latitude = location.getLatitude();
				longitude = location.getLongitude();
			}
			
			if(mAddress!=null){
				address = LocationHelper.buildAddressLine(mAddress);
			}
			if(body!=null && body.trim().length()>0){
				long id = mDbHelper.createItem(writer, body, new Date(), latitude, longitude, address);
				if (id > 0) {
					mRowId = id;
				}
			}
		} else {
			if(body!=null && body.trim().length()>0){
				mDbHelper.updateNote(mRowId, writer, body);
			}
		}
	}
}
