package com.molokotech.utilities;

import java.io.IOException;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

public class GoogleMapsService {

	public static double getLatitude(String address) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyDFuQb3h_4AhG3Kmdf3NIz3aZfzsguDQOE").build();

		GeocodingResult[] request = GeocodingApi.geocode(context, address).await();
		double latitude = request[0].geometry.location.lat;
		return latitude;
	}
	
	public static double getLongitude(String address) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyDFuQb3h_4AhG3Kmdf3NIz3aZfzsguDQOE").build();

		GeocodingResult[] request = GeocodingApi.geocode(context, address).await();
		double longitude = request[0].geometry.location.lng;
		return longitude;
	}

	public static void main(String[] args) throws ApiException, InterruptedException, IOException {

	}

}
