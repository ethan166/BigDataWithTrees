import java.io.*;
import java.text.*;
import java.util.*;
import java.time.*;

public class ReportProcessor {
	
	private static Instant start, end;
	private static long elapsedTime;

	public static void main(String[] args) throws IOException, ParseException {

		ArrayList<PoliceReport> masterReportList = readList();

		// create a binary search tree with the data
		start = Instant.now();
		BinarySearchTreeWithDups<PoliceReport> reportTree = new BinarySearchTreeWithDups<PoliceReport>();
		int count = 0;
		for (PoliceReport report : masterReportList) {
			reportTree.add(report);
			count++;
			if (count % 5000 == 0) System.out.println("\t...building tree with entry " + count);
		}
		System.out.println("Binary tree complete!");
		elapsedTime = Duration.between(start,  Instant.now()).toMillis();
		System.out.println("\tTime required to build tree: " + elapsedTime + " milliseconds");

		// sort the list for use later on
		Collections.sort(masterReportList);

		/* PART B-1 */
		// examine the number of incidents that took place on each day (5 holidays and 1 random day)
		
		String[] dateString = { "1/1/2003", "3/17/2004", "7/4/2005", "10/31/2006", "12/25/2007", "6/8/2008" };
		for (String date : dateString) {
			ArrayList<PoliceReport> dateReports = new ArrayList<PoliceReport>();

			PoliceReport dummyReport = new PoliceReport();
			dummyReport.setDate(date);

			start = Instant.now();
			dateReports = reportTree.getAllEntriesIterative(dummyReport);
			elapsedTime = Duration.between(start,  Instant.now()).toMillis();
			System.out.println("There were " + dateReports.size() + " incidents on " + date + ".");
			System.out.println("\tTime required to gather reports from tree: " + elapsedTime + " milliseconds.");

			// compare this to iterating through a sorted version of the list
			dateReports.clear();

			start = Instant.now();
			for (PoliceReport report : masterReportList) {
				if (report.equals(dummyReport)) {
					dateReports.add(report);
				}
			}
			elapsedTime = Duration.between(start,  Instant.now()).toMillis();
			System.out.println("There were " + dateReports.size() + " incidents on " + date + ".");
			System.out.println("\tTime required to gather reports from list: " + elapsedTime + " milliseconds.");
		}
		
		/* PART B-2 */
		/*
		// find the records that match a specific incident number for 10 random incident numbers
		Collections.shuffle(masterReportList);
		long[] indicentNumbers = new long[10];
		for (int i = 0; i < 10; i++) {
			indicentNumbers[i] = masterReportList.get(i).getIncidentNum();
		}
		Collections.sort(masterReportList);

		for (long incidentNumber : indicentNumbers) {
			PoliceReport dummyReport = new PoliceReport();
			dummyReport.setIncidentNum(incidentNumber);

			start = Instant.now();
			PoliceReport foundReport = reportTree.getEntry(dummyReport);
			elapsedTime = Duration.between(start,  Instant.now()).toMillis();
			System.out.println("Report: " + foundReport);
			System.out.println("\tTime required to find report from tree: " + elapsedTime + " milliseconds.");

			// compare this to iterating through a sorted version of the list

			start = Instant.now();
			foundReport = masterReportList.get(masterReportList.indexOf(dummyReport));
			elapsedTime = Duration.between(start,  Instant.now()).toMillis();
			System.out.println("Report: " + foundReport);
			System.out.println("\tTime required to find report from list: " + elapsedTime + " milliseconds.");
		}
		*/
		
		/* PART B-3 */
		/*
		// examine the number of incidents that took place on each day of the week
		String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		for (String day : days) {
			ArrayList<PoliceReport> dateReports = new ArrayList<PoliceReport>();

			PoliceReport dummyReport = new PoliceReport();
			dummyReport.setDay(day);

			start = Instant.now();
			// ??? ITERATIVE
			dateReports = reportTree.getAllEntriesIterative(dummyReport);
			// ??? RECURSIVE
			// dateReports = reportTree.getAllEntriesRecursive(dummyReport);
			elapsedTime = Duration.between(start,  Instant.now()).toMillis();
			System.out.println("There were " + dateReports.size() + " incidents on " + day + ".");
			System.out.println("\tTime required to gather reports from tree: " + elapsedTime + " milliseconds.");

			// compare this to iterating through a sorted version of the list
			dateReports.clear();

			start = Instant.now();
			for (PoliceReport report : masterReportList) {
				if (report.equals(dummyReport)) {
					dateReports.add(report);
				}
			}
			elapsedTime = Duration.between(start,  Instant.now()).toMillis();
			System.out.println("There were " + dateReports.size() + " incidents on " + day + ".");
			System.out.println("\tTime required to gather reports from list: " + elapsedTime + " milliseconds.");
		}
		*/
	}
	
	private static ArrayList<PoliceReport> readList() throws IOException {
		ArrayList<PoliceReport> masterReportList = new ArrayList<>();
		
		String fileName = "SFPD_Incidents_TheftLarceny.csv";
		Scanner fileScan = new Scanner(new File(fileName));		
		
		fileScan.nextLine(); // read the column headers
		while (fileScan.hasNextLine()) {
			String singleReport = fileScan.nextLine();
			Scanner reportScan = new Scanner(singleReport);
			reportScan.useDelimiter(",");

			// this code assumes the file is perfectly formatted- it does not
			// account for errors in formatting
			PoliceReport report = new PoliceReport(
					Long.parseLong(reportScan.next()), // incident number
					reportScan.next(), // category
					reportScan.next(), // description
					reportScan.next(), // day
					reportScan.next(), // date
					reportScan.next(), // district
					reportScan.next(), // resolution
					reportScan.next() // address
			);
			masterReportList.add(report);
		}
		Collections.shuffle(masterReportList);
		return masterReportList;
	}

}
