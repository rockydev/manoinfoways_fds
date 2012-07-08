package com.manoinfoways.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import com.manoinfoways.ejb.InboundVoiceFilesDataBean;
import com.manoinfoways.ejb.TranscriberDataBean;
import com.manoinfoways.ejb.TranscriberDoctorPriorityBean;
import com.manoinfoways.model.DoctorData;
import com.manoinfoways.model.InboundVoiceFilesData;
import com.manoinfoways.model.TranscriberData;
import com.manoinfoways.model.TranscriberDoctorPriority;

public class MainPriorityMatrix {

	public static Hashtable<String, ArrayList<InboundVoiceFilesData>> fileDocList = new Hashtable<String, ArrayList<InboundVoiceFilesData>>();
	public static Hashtable fileLengthList = new Hashtable();
	public static ArrayList<InboundVoiceFilesData> fileList = new ArrayList<InboundVoiceFilesData>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String transName = new String();

		Hashtable transAttendance = new Hashtable();
		transAttendance.put("T1", "1");
		transAttendance.put("T2", "1");
		transAttendance.put("T3", "1");
		transAttendance.put("T4", "1");
		transAttendance.put("T5", "1");
		transAttendance.put("T6", "1");
		transAttendance.put("T7", "1");
		transAttendance.put("T8", "1");

		TranscriberDataBean transcriberDataBean = new TranscriberDataBean();
		TranscriberDoctorPriorityBean transcriberDoctorPriorityBean = new TranscriberDoctorPriorityBean();
		InboundVoiceFilesDataBean inboundVoiceFilesDataBean = new InboundVoiceFilesDataBean();
		
		ArrayList<TranscriberData> transcribersList = (ArrayList<TranscriberData>) transcriberDataBean
				.getTranscribersByTranscriberTypeId(1);

		Object[][][] transcriberPriorityArray = new Object[transcribersList
				.size()][][];

		for (int k = 0; k < transcribersList.size(); k++) {
			
			System.out.println(transcribersList.get(k));
			int priorityCount = transcriberDoctorPriorityBean.getPriorityCount(transcribersList.get(k).getTranscriberId());
			transcriberPriorityArray[k] = new Object[priorityCount +1][];
			transcriberPriorityArray[k][0] = new Object[1];
			transcriberPriorityArray[k][0][0] = transcribersList.get(k);
			for (int i = 1; i <= priorityCount; i++) {
				ArrayList<DoctorData> doctors = (ArrayList<DoctorData>) transcriberDoctorPriorityBean
						.getTranscriberPriorityDoctors(transcribersList.get(k)
								.getTranscriberId(), 1, i);
				System.out.println(i);
				transcriberPriorityArray[k][i] = new Object[doctors.size()];
				for (int j = 0; j < doctors.size(); j++) {
					transcriberPriorityArray[k][i][j] = doctors.get(j);
					
				}
			}
		}

//		int transcriberIndex = 0;
//		for (TranscriberData transcriber : transcribersList) {
//
//			// transcriberpriorityArray[transcriberIndex] = transcriber;
//			Object[][] transcriberArray = new Object[10][10];
//			// transcriberArray[0] = transcriber;
//			for (int priority = 1; priority < 4; priority++) {
//				int doctorIndex = 0;
//				for (DoctorData doctor : transcriberDoctorPriorityBean
//						.getTranscriberPriorityDoctors(
//								transcriber.getTranscriberId(), 1, priority)) {
//					transcriberpriorityArray[transcriberIndex][priority][doctorIndex++] = doctor;
//				}
//
//			}
//		}

		// String docpriorityarray[][]=
		// {{"T1","T8","T7","T6","T5","T4","T3","T2"},{"T2","T1","T8","T7","T6","T5","T4","T3"},{"T3","T2","T1","T8","T7","T6","T5","T4"},{"T4","T3","T2","T1","T8","T7","T6","T5"},{"T5","T4","T3","T2","T1","T8","T7","T6"},{"T6","T5","T4","T3","T2","T1","T8","T7"},{"T7","T6","T5","T4","T3","T2","T1","T8"},{"T8","T7","T6","T5","T4","T3","T2","T1"}};
		// System.out.println("Row size= " + docpriorityarray.length);
		// System.out.println("Column size = " + docpriorityarray[0].length);
		// String transcriberPriorityArray[][]=
		// {{"T1","D1","D2","D3","D4","D5","D6","D7","D8"},{"T2","D8","D1","D2","D3","D4","D5","D6","D7"},{"T3","D7","D8","D1","D2","D3","D4","D5","D6"},{"T4","D6","D7","D8","D1","D2","D3","D4","D5"},{"T5","D5","D6","D7","D8","D1","D2","D3","D4"},{"T6","D4","D5","D6","D7","D8","D1","D2","D3"},{"T7","D3","D4","D5","D6","D7","D8","D1","D2"},{"T8","D2","D3","D4","D5","D6","D7","D8","D1"}};
		String transcriberpriorityArray[][][] = {
				{ { "Vishal.G" }, { "CLPT", "DBMD", "EOPT" },
						{ "IHPT", "JRRS", "TSPT" }, { "LWDC", "TJPT" },
						{ "GYDO", "JHPT" } },
				{ { "Ganesh TL" }, { "CLPT", "GYDO" }, {},
						{ "DBMD", "EOPT", "JHPT", "TSPT" },
						{ "CLPT", "IHPT", "JRRS", "LWDC", "TJPT" } },
				{ { "GeethaRani AR" }, { "IHPT", "JHPT", "TSPT", "TJPT" },
						{ "CLPT", "GYDO", "EOPT", "LWDC" }, { "JRRS" },
						{ "DBMD" } },
				{ { "VijayaLakshmi K" }, { "JRRS", "LWDC" },
						{ "DBMD", "JHPT", "TJPT" }, { "CLPT", "GYDO", "IHPT" },
						{ "EOPT", "TSPT" } } };
		// outputArray(docpriorityarray);
		System.out.println("----------------------------------");
		outputArray(transcriberPriorityArray);
		
		
		fileDocList = new Hashtable<String, ArrayList<InboundVoiceFilesData>>();
		fileList = new ArrayList<InboundVoiceFilesData>();
//		fileList.add("CLPT0809");
//		fileLengthList.put("CLPT0809", 6.52);
//		fileDocList.put("CLPT", fileList);
		
		ArrayList<InboundVoiceFilesData> voiceFiles = (ArrayList<InboundVoiceFilesData>) inboundVoiceFilesDataBean.getTodaysVoiceFiles();
		
		for (InboundVoiceFilesData file : voiceFiles)
		{
			String doctorAbbr = file.getDoctordata().getDoctorAbbr();
			
			if (fileDocList.containsKey(doctorAbbr))
			{
				fileDocList.get(doctorAbbr).add(file);
				fileLengthList.put(doctorAbbr, file.getLengthOfDictation());
			}
			else
			{
				fileDocList.put(doctorAbbr, new ArrayList<InboundVoiceFilesData>());
				fileDocList.get(doctorAbbr).add(file);
				fileLengthList.put(doctorAbbr, file.getLengthOfDictation());
			}
		}
//		System.exit(0);
//		fileList = new ArrayList<String>();
//		fileList.add("DBMD8334");
//		fileList.add("DBMD8335");
//		fileList.add("DBMD8336");
//		fileList.add("DBMD8337");
//		fileList.add("DBMD8338");
//		fileList.add("DBMD8339");
//		fileList.add("DBMD8340");
//		fileList.add("DBMD8341");
//		fileList.add("DBMD8342");
//		fileList.add("DBMD8343");
//		fileList.add("DBMD8344");
//		fileList.add("DBMD8345");
//		fileList.add("DBMD8346");
//		fileList.add("DBMD8347");
//		fileLengthList.put("DBMD8334", 2.22);
//		fileLengthList.put("DBMD8335", 6.19);
//		fileLengthList.put("DBMD8336", 1.48);
//		fileLengthList.put("DBMD8337", 1.11);
//		fileLengthList.put("DBMD8338", 7.9);
//		fileLengthList.put("DBMD8339", 1.52);
//		fileLengthList.put("DBMD8340", 3.37);
//		fileLengthList.put("DBMD8341", 0.56);
//		fileLengthList.put("DBMD8342", 1.11);
//		fileLengthList.put("DBMD8343", 1.37);
//		fileLengthList.put("DBMD8344", 1.58);
//		fileLengthList.put("DBMD8345", 1.44);
//		fileLengthList.put("DBMD8346", 7.4);
//		fileLengthList.put("DBMD8347", 1.38);
//		fileDocList.put("DBMD", fileList);

//		fileList = new ArrayList<String>();
//		// fileList=null;
//		fileList.add("GYDO2521");
//		fileList.add("GYDO2522");
//		fileList.add("GYDO2523");
//		fileList.add("GYDO2524");
//		fileList.add("GYDO2525");
//		fileList.add("GYDO2526");
//		fileList.add("GYDO2527");
//		fileList.add("GYDO2528");
//		fileList.add("GYDO2529");
//		fileLengthList.put("GYDO2521", 6.45);
//		fileLengthList.put("GYDO2522", 4.4);
//		fileLengthList.put("GYDO2523", 2.42);
//		fileLengthList.put("GYDO2524", 3.33);
//		fileLengthList.put("GYDO2525", 2.47);
//		fileLengthList.put("GYDO2526", 5.20);
//		fileLengthList.put("GYDO2527", 6.58);
//		fileLengthList.put("GYDO2528", 1.29);
//		fileLengthList.put("GYDO2529", 2.21);
//		fileDocList.put("GYDO", fileList);
//
//		fileList = new ArrayList<String>();
//		// fileList=null;
//		fileDocList.put("EOPT", fileList);
//
//		fileList = new ArrayList<String>();
//		// fileList=null;
//		fileDocList.put("IHPT", fileList);
//
//		fileList = new ArrayList<String>();
//		// fileList=null;
//		fileDocList.put("JHPT", fileList);
//
//		fileList = new ArrayList<String>();
//		fileList.add("JRRS0011");
//		fileLengthList.put("JRRS0011", 59.3);
//		fileDocList.put("JRRS", fileList);
//
//		fileList = new ArrayList<String>();
//		// fileList=null;
//		fileDocList.put("LWDC", fileList);
//
//		fileList = new ArrayList<String>();
//		fileList.add("TSPT1380");
//		fileLengthList.put("TSPT1380", 9.38);
//		fileDocList.put("TSPT", fileList);
//
//		fileList = new ArrayList<String>();
//		// fileList=null;
//		fileList.add("TJPT1329");
//		fileList.add("TJPT1330");
//		fileList.add("TJPT1331");
//		fileLengthList.put("TJPT1329", 4.49);
//		fileLengthList.put("TJPT1330", 4.44);
//		fileLengthList.put("TJPT1331", 7.11);
//		fileDocList.put("TJPT", fileList);

		System.out.println(fileDocList);
		System.out.println(fileLengthList);

		InputStreamReader istream = new InputStreamReader(System.in);
		BufferedReader bufRead = new BufferedReader(istream);

		System.out.println("Welcome To PriorityMatrix");
		assignFiletoTrans(transcriberPriorityArray, fileDocList,
				transAttendance, fileLengthList);

		/*
		 * while(true) { try {
		 * System.out.println("Please Enter In Your Name: "); transName =
		 * bufRead.readLine(); if(transName.equalsIgnoreCase("exit")) {
		 * System.exit(0); } else {
		 * assignFiletoTrans(transName,transcriberPriorityArray
		 * ,fileDocList,transAttendance,fileLengthList); }
		 * System.out.println("Your Name:"+transName); } catch (IOException err)
		 * { System.out.println("Error reading line"); }
		 * catch(NumberFormatException err) {
		 * System.out.println("Error Converting Number"); }
		 * 
		 * }
		 */

	}

	/*
	 * tName=TranscriberName tPArray=Transcriber Priority Matrix Doc Wise
	 * dFileList=Doctors File List tAttendance=Transcriber Attendance
	 */
	public static void assignFiletoTrans(Object[][][] transcriberPriorityArray,
			Hashtable dFileList, Hashtable tAttendance, Hashtable fileLengths) {
		int fileCount = 0;
		ArrayList arrayObj = new ArrayList();
		Hashtable transfilesht = new Hashtable();

		for (int k = 0; k < transcriberPriorityArray.length; k++) {
			ArrayList tFileList = new ArrayList();
			for (int i = 1; i < transcriberPriorityArray[k].length; i++) {
				ArrayList arrayObjList = new ArrayList();
				for (int j = 0; j < transcriberPriorityArray[k][i].length; j++) {
					if (dFileList.containsKey(((DoctorData)transcriberPriorityArray[k][i][j]).getDoctorAbbr()))
					{
						arrayObj = (ArrayList) dFileList.get(((DoctorData)transcriberPriorityArray[k][i][j]).getDoctorAbbr());
						if (arrayObj.isEmpty()) {
							arrayObj.add("");
						}
					}
					else
					{
						System.out.println("in else" + ((DoctorData)transcriberPriorityArray[k][i][j]).getDoctorAbbr());
						arrayObj.add("");
					}
					arrayObjList.addAll(arrayObj);
				}
				tFileList.add(arrayObjList);
				// break;
			}
			fileCount = tFileList.size();
			System.out.println("The K value is " + k
					+ " and the Transcriber is " + transcriberPriorityArray[k][0][0]
					+ " and the file list is " + tFileList);
			transfilesht.put(((TranscriberData)transcriberPriorityArray[k][0][0]).getTranscriberId(), tFileList);
		}
		System.out.println(transfilesht);

		Hashtable transLengthsList = new Hashtable();
		Hashtable finalTFList = new Hashtable();
		System.out.println("Hellooooooooooo" + fileCount);
		ArrayList tempFiles = new ArrayList();

		for (int i = 0; i < fileCount; i++) {

			Enumeration e = transfilesht.keys();
			// iterate through Hashtable keys Enumeration
			while (e.hasMoreElements()) {
				String temp = new String();
				String key = e.nextElement().toString();
				System.out.println(key);
				ArrayList transfileshtValue = (ArrayList) transfilesht.get(key);
				System.out.println(transfileshtValue.size());
				ArrayList files_eachValue = new ArrayList();

				files_eachValue = (ArrayList) transfileshtValue.get(i);
				for (int x = 0; x < files_eachValue.size(); x++) {
					// System.out.println("The file is:"+files_eachValue.get(x));
					// System.out.println("KEY IS "+key+" && "+tempArray.get(i));
					if (!tempFiles.contains(files_eachValue.get(x))) {
						if (finalTFList.get(key) != null)
							temp = finalTFList.get(key).toString();

						if (!transLengthsList.containsKey(key)) {
							if (fileLengths.get(files_eachValue.get(x)) != null) {
								// System.out.println("Hiii: "+key+"###"+fileLengths.get(tempArray.get(i)));
								transLengthsList.put(key, (Double
										.parseDouble(fileLengths.get(
												files_eachValue.get(x))
												.toString())));
								tempFiles.add(files_eachValue.get(x));
								// System.out.println("Key is "+key+"   "+tempArray.get(i));
								String value = temp + " "
										+ files_eachValue.get(x).toString();
								finalTFList.put(key, value);
							}
						} else {
							double filelength = 0;
							// System.out.println("Inside else "+key+" filelist "+transLengthsList.get(key));
							if (transLengthsList.get(key) != null)
								filelength = Double
										.parseDouble(transLengthsList.get(key)
												.toString());
							if (filelength < 60 && filelength != 0) {
								if (fileLengths.get(files_eachValue.get(x)) != null) {
									transLengthsList
											.put(key,
													filelength
															+ Double.parseDouble(fileLengths
																	.get(files_eachValue
																			.get(x))
																	.toString()));
									tempFiles.add(files_eachValue.get(x));
									// System.out.println("Key is "+key+"   "+tempArray.get(i));
									String value = temp + " "
											+ files_eachValue.get(x).toString();
									finalTFList.put(key, value);
								}
							}

						}
					}
				}
			}
		}
		System.out.println("The Final List " + finalTFList);
		System.out.println("The Final Lengths " + transLengthsList);
		// System.out.println("The File List for Transcriber "+tName
		// +" is "+tFileList );
		// System.out.println("The File Length List for Transcriber "+tName
		// +" is "+tFileLengthList);

		/*
		 * for(int i=0;i<tPArray.length;i++) {
		 * System.out.println("The TPArray is:"+tPArray[i][0]); }
		 */
	}

	public static void outputArray(Object[][][] transcriberPriorityArray) {
		int rowSize = transcriberPriorityArray.length;
		int columnSize = transcriberPriorityArray[1].length;
		for (int i = 0; i < transcriberPriorityArray.length; i++) {
			System.out.print("[");
			for (int j = 0; j < transcriberPriorityArray[i].length; j++) {
				System.out.print("(");
				for (int k = 0; k < transcriberPriorityArray[i][j].length; k++) {
//					if (transcriberPriorityArray[i][j][k] != null)
						System.out.print(" " + transcriberPriorityArray[i][j][k]);
				}
				System.out.print(")");
			}
			System.out.println(" ]");
		}
		System.out.println();
	}

}
