package me.cidi;

import java.util.List;
import java.util.Locale;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

public class LocationHelper {
	static Location location = null;
	
	public static Address getAddressByLocation(ItemEdit cntext, Location location) {
		Address result = null;
		try {
			if (location != null) {
				Geocoder gc = new Geocoder(cntext, Locale.CHINA);
				List<Address> lstAddress = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
				if (lstAddress.size() > 0) {
					result = lstAddress.get(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String buildAddressLine(Address mAddress) {
		String address = "";
		if(mAddress.getLocality()!=null){
			address = address+mAddress.getLocality();
		}else if(mAddress.getSubAdminArea()!=null){
			address = address+mAddress.getSubAdminArea();
		}else if(mAddress.getAdminArea()!=null){
			address = address+mAddress.getAdminArea();
		}else if(mAddress.getCountryName()!=null){
			address = address+mAddress.getCountryName();
		}
		
		if(mAddress.getSubLocality()!=null){
			address = address+mAddress.getSubLocality();
		}
		
		if(mAddress.getThoroughfare()!=null){
			address = address + mAddress.getThoroughfare();
		}
		
		if(mAddress.getSubThoroughfare()!=null){
			address = address+mAddress.getSubThoroughfare();
		}
		
		for(int i=0;i<mAddress.getMaxAddressLineIndex();i++){
			String line  = mAddress.getAddressLine(i);
			
			if(mAddress.getLocality()!=null){
				line = line.replaceFirst(mAddress.getLocality(), "");
			}
			
			if(mAddress.getSubAdminArea()!=null){
				line = line.replaceFirst(mAddress.getSubAdminArea(), "");
			}
			
			if(mAddress.getAdminArea()!=null){
				line = line.replaceFirst(mAddress.getAdminArea(), "");
			}
			
			if(mAddress.getCountryName()!=null){
				line = line.replaceFirst(mAddress.getCountryName(), "");
			}
			
			if(mAddress.getSubLocality()!=null){
				line = line.replaceFirst(mAddress.getSubLocality(), "");
			}
			
			if(mAddress.getThoroughfare()!=null){
				line = line.replaceFirst(mAddress.getThoroughfare(), "");
			}
			
			if(mAddress.getSubThoroughfare()!=null){
				line = line.replaceFirst(mAddress.getSubThoroughfare(), "");
			}
			
			address = address + "," + line;
		}
		return address;
	}

	private static String debugAddress(Address mAddress) {
		String address = "CountryName:" + mAddress.getCountryName()+ "," + 
						 "Locality:" + mAddress.getLocality() + "," + 
						 "AdminArea:" + mAddress.getAdminArea() + "," + 
						 "CountryCode:" + mAddress.getCountryCode() + "," + 
						 "FeatureName:" + mAddress.getFeatureName() + "," + 
						 "Phone:" + mAddress.getPhone() + "," + 
						 "PostalCode:" + mAddress.getPostalCode() + "," + 
						 "Premises:" + mAddress.getPremises()+ "," + 
						 "SubAdminArea:" + mAddress.getSubAdminArea() + "," + 
						 "SubThoroughfare:" + mAddress.getSubThoroughfare() + "," + 
						 "Thoroughfare:" + mAddress.getThoroughfare() + "," + 
						 "Url:" + mAddress.getUrl() + "," + 
						 "Extras:" + mAddress.getExtras() + "," + 
						 "SubLocality:" + mAddress.getSubLocality();
		for(int i=0;i<mAddress.getMaxAddressLineIndex();i++){
			address = address + ", AddressLine:" + mAddress.getAddressLine(i);
		}
		return address;
	}
}
