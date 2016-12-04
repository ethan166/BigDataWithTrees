import java.text.*;
import java.util.*;

public class PoliceReport implements Comparable<PoliceReport>{

	private long incidentNum;
	private String category;
	private String description;
	private int day;
	private String dateString;
	private String district;
	private String resolution;
	private String address;
	
	public PoliceReport(long incidentNum, String category, String description, String dayString, String dateString, String district,
			String resolution, String address)  {
		this.incidentNum = incidentNum;
		this.category = category;
		this.description = description;
		this.dateString = dateString;
		setDay(dayString);
		this.district = district;
		this.resolution = resolution;
		this.address = address;	
	}
	
	public PoliceReport() {
		this(-1, null, null, null, null, null, null, null);
	}
	
	public long getIncidentNum() {
		return incidentNum;
	}
	public void setIncidentNum(long incidentNum) {
		this.incidentNum = incidentNum;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDay() {
		switch(day) {
		case 1:
			return "Sunday";
		case 2:
			return "Monday";
		case 3:
			return "Tuesday";
		case 4:
			return "Wednesday";
		case 5:
			return "Thursday";
		case 6:
			return "Friday";
		case 7:
			return "Saturday";
		}
		return "";
	}
	public void setDay(String dayString) {
		if(dayString!=null) {
		switch(dayString) {
		case "Sunday":
			day = 1;
			break;
		case "Monday":
			day = 2;
			break;
		case "Tuesday":
			day = 3;
			break;
		case "Wednesday":
			day = 4;
			break;
		case "Thursday":
			day = 5;
			break;
		case "Friday":
			day = 6;
			break;
		case "Saturday":
			day = 7;
			break;
		default:
			day = -1;
		}
		} else {
			day = -1;
		}
	
	}
	public String getDate() {
		return dateString;
	}
	public void setDate(String dateString) {
		this.dateString = dateString;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		String s = "Incident #" + incidentNum +
				"\tDate: " + dateString + " (" + getDay() + ")"+ 
				"\n\tCategory: " + category +
				"\tDescription: " + description + 
				"\tResolution: " + resolution + 	
				"\n\tDistrict: " + district + 
				"\tAddress: " + address;
		return s;
	}
	
	/* PART B-1 */
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof PoliceReport) {
			PoliceReport otherReport = (PoliceReport) other;
			return dateString.equalsIgnoreCase(otherReport.dateString);
		} else {
			return false;
		}
	}
	@Override
	public int compareTo(PoliceReport otherReport) {
		return dateString.compareToIgnoreCase(otherReport.dateString);
	}
	
	/* PART B-2 */
	/*
	@Override
	public boolean equals(Object other) {
		if(other instanceof PoliceReport) {
			PoliceReport otherReport = (PoliceReport) other;
			return incidentNum==otherReport.incidentNum;
		} else {
			return false;
		}
	}
	@Override
	public int compareTo(PoliceReport otherReport) {
		if(incidentNum < otherReport.incidentNum) 
			return -1;
		else if(incidentNum > otherReport.incidentNum) 
			return 1;
		else 
			return 0;
	}
	*/
	
	/* PART B-3 */
	/*
	@Override
	public boolean equals(Object other) {
		if(other instanceof PoliceReport) {
			PoliceReport otherReport = (PoliceReport) other;
			return day==otherReport.day;
		} else {
			return false;
		}
	}
	@Override
	public int compareTo(PoliceReport otherReport) {
		return this.day - otherReport.day;
	}
	*/
	
}
