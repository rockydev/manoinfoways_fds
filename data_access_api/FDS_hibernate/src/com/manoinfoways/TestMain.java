/**
 * 
 */
package com.manoinfoways;

import org.hibernate.Session;

import com.manoinfoways.ejb.ClinicDataBean;
import com.manoinfoways.model.ClinicData;
import com.manoinfoways.model.HibernateUtil;

/**
 * @author rdevinen
 * 
 */
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ClinicData data = new ClinicData("GHI");

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		ClinicDataBean dataBean = new ClinicDataBean();
		
		//Testing filters
		for (ClinicData clinicData : dataBean.getClinicAbbrs())
		{
			System.out.println("ClinicId: " + new Integer(clinicData.getClinicId()).toString()
					+ " => clinicAbbr: "  + clinicData.getClinicAbbr());
		}
		
		
//		data.setClinicName("udpated test");
//		data.setAddressLine1("456");
//		data.setAddressLine2("789");
//		data.setCountry("India");
//		data.setLocation("Hyderabad");
//		data.setZipcode(500092);
//
//		System.out.println("Persisting clinic data");
//		dataBean.persist(data);
//		System.out.println("Persisted clinic data");
//
//		System.out.println("Getting clinic data for id '12345'");
//		ClinicData foundData = dataBean.findById(2);
//		System.out.println(foundData.toString());
//
//		ArrayList<ClinicData> clinics = (ArrayList<ClinicData>) dataBean
//				.getAllClinicData();
//
//		System.out.println("Complete clinic data in the db:");
//		for (ClinicData clinicData : clinics) {
//			System.out.println("Clinic Data: " + clinicData.toString());
////			
////			System.out.println("list of doctors for this clinic");
////			
////			Iterator<DoctorData> iterator = clinicData.getDoctordatas().iterator();
////			
////			while (iterator.hasNext())
////			{
////				System.out.println(iterator.next().getDoctorId());
////			}
//		}
		
		
		session.getTransaction().commit();
		session.close();
	}

}
