package guimodule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Airport {
	
	String name;
	String country;
	double lat;
	double lon;
	String location;
	int altitude;
	
	public Airport(String name, String country, double lat, double lon,int altitude,
			String location) {
		super();
		this.name = name;
		this.country = country;
		this.lat = lat;
		this.lon = lon;
		this.altitude = altitude; 
		this.location = location;
	}

	public int getAltitude() {
		return altitude;
	}

	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public static void main(String[] args){
		String airportFile = "data\\airportdata.dat";
		String airportInfo;
		String[] airport;
		List<Airport> airports= new ArrayList<Airport>();
		boolean found;
		String pattern = "(^\")((\\w)*(\\s)*)+(\")$";
		 Pattern r = Pattern.compile(pattern);
		
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(airportFile));

			while ((sCurrentLine = br.readLine()) != null) {
				airportInfo = sCurrentLine;
				airport = airportInfo.split(",");
				
				for(int i = 0; i < airport.length; i++){
					Matcher m = r.matcher(airport[i]);
					if(m.find()){
						airport[i] = airport[i].substring(1,airport[i].length()-1);
					}
					//System.out.println(airport[i]);
				}
				
				Airport a = new Airport(airport[1], airport[3], Double.parseDouble(airport[6]), Double.parseDouble(airport[7]),Integer.parseInt(airport[8]), airport[11]);
				airports.add(a);
			}
			System.out.println(toFind(airports,"Port Moresby Jacksons Intl"));
			System.out.println(toFindBinary(airports,"Wewak Intl"));
			
			int[] selectionSortArray = new int[10];
			selectionSortArray = toSelectionSort(airports);
			System.out.println(selectionSortArray[9]);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}
	
	private static int[] toSelectionSort(List<Airport> airports) {
		// TODO Auto-generated method stub
		int[] sort = new int[10];
		List<Integer> a = new ArrayList<Integer>();
		int k = 0;
		for(Airport airport : airports){
			sort[k] = airport.getAltitude();
			a.add(airport.getAltitude());
			k++;
		}
		Collections.sort(a);
		System.out.println(a.get(0));
		int smallest = sort[0];
		int indexMin;
		for(int i = 0;i < 9;i++){
			indexMin = i;
			for(int j = i+1; j < 10 ;j++){
				if(sort[j] < sort[indexMin]){
					indexMin = j;
				}
			}
			swap(sort,indexMin,i);
			
		}
		
		return sort;
	}

	private static void swap(int[] sort, int indexMin, int i) {
		// TODO Auto-generated method stub
		int temp = sort[i];
		sort[i] = sort[indexMin];
		sort[indexMin] = temp;
	}

	private static boolean toFindBinary(List<Airport> airports, String name) {
		// TODO Auto-generated method stub
		List<String> s = new ArrayList<String>();
		for(Airport airport: airports){
			s.add(airport.getName());
		}
		
		Collections.sort(s);
		
		int low = 0;
		int high = airports.size() - 1;
		int mid;
		while (low <= high) {
			mid = low + ((high-low)/2);
			int compare = name.compareTo(s.get(mid));
			
			if (compare < 0) {
				high = mid - 1;
			}
			else if (compare > 0) {
				low = mid+1;
			}
			else return true;
		}
		
		return false;
	}

	private static boolean toFind(List<Airport> a,String name){
		for(Airport airport: a){
			if(airport.getName().equals(name)){
				return true;
			}
		}
		
		return false;
	}

}
