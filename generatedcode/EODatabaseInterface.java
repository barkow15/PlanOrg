public class EODatabaseInterface {

	/**
	 * 
	 * @param arrangementid
	 */
	public EOArrangement getEOArrangement(int arrangementid) {
		// TODO - implement EODatabaseInterface.getEOArrangement
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param eventid
	 */
	public EOEvent getEOEvent(int eventid) {
		// TODO - implement EODatabaseInterface.getEOEvent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param eventtypeid
	 */
	public EOEventType getEOEventType(int eventtypeid) {
		// TODO - implement EODatabaseInterface.getEOEventType
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customercontactid
	 */
	public CustomerContactInfo getCustomerContactInfo(int customercontactid) {
		// TODO - implement EODatabaseInterface.getCustomerContactInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param externalcontactid
	 */
	public ExternalContactInfo getExternalContantInfo(int externalcontactid) {
		// TODO - implement EODatabaseInterface.getExternalContantInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param facilitatorcontactid
	 */
	public FacilitatorContactInfo getFacilitatorContactInfo(int facilitatorcontactid) {
		// TODO - implement EODatabaseInterface.getFacilitatorContactInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param sortby
	 * @param pagenumber
	 */
	public void getEOArrangements(String sortby, int pagenumber) {
		// TODO - implement EODatabaseInterface.getEOArrangements
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param arrangementid
	 */
	public void getEOEvents(int arrangementid) {
		// TODO - implement EODatabaseInterface.getEOEvents
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param eventid
	 */
	public void getEOEventTypes(int eventid) {
		// TODO - implement EODatabaseInterface.getEOEventTypes
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param events
	 * @param customer
	 * @param name
	 * @param description
	 * @param datetimestart
	 * @param datetimeend
	 * @param price
	 */
	public void createEOArrangement(EOEvent[] events, CustomerContactInfo customer, String name, String description, LocalDateTime datetimestart, LocalDateTime datetimeend, double price) {
		// TODO - implement EODatabaseInterface.createEOArrangement
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param arrangementid
	 * @param events
	 * @param customer
	 * @param name
	 * @param description
	 * @param datetimestart
	 * @param datetimeend
	 * @param price
	 */
	public void updateEOArrangement(int arrangementid, EOEvent[] events, CustomerContactInfo customer, String name, String description, LocalDateTime datetimestart, LocalDateTime datetimeend, double price) {
		// TODO - implement EODatabaseInterface.updateEOArrangement
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param arrangementid
	 */
	public void deleteEOArrangement(int arrangementid) {
		// TODO - implement EODatabaseInterface.deleteEOArrangement
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param eventtypes
	 * @param datetimestart
	 * @param datetimeend
	 * @param price
	 * @param description
	 */
	public void createEOEvent(EUEventType[] eventtypes, LocalDateTime datetimestart, LocalDateTime datetimeend, double price, String description) {
		// TODO - implement EODatabaseInterface.createEOEvent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param eventid
	 * @param eventtypes
	 * @param datetimestart
	 * @param datetimeend
	 * @param price
	 * @param description
	 */
	public void updateEOEvent(int eventid, EUEventType[] eventtypes, LocalDateTime datetimestart, LocalDateTime datetimeend, double price, String description) {
		// TODO - implement EODatabaseInterface.updateEOEvent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param eventid
	 */
	public void deleteEOEvent(int eventid) {
		// TODO - implement EODatabaseInterface.deleteEOEvent
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param locationstart
	 * @param locationend
	 * @param time
	 * @param name
	 * @param externalcontact
	 * @param description
	 * @param price
	 */
	public void createEOEvenType(String locationstart, String locationend, int time, String name, ExternalContactInfo externalcontact, String description, double price) {
		// TODO - implement EODatabaseInterface.createEOEvenType
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param eventtypeid
	 * @param locationstart
	 * @param locationend
	 * @param time
	 * @param name
	 * @param externalcontact
	 * @param description
	 * @param price
	 */
	public void updateEOEvenType(int eventtypeid, String locationstart, String locationend, int time, String name, ExternalContactInfo externalcontact, String description, double price) {
		// TODO - implement EODatabaseInterface.updateEOEvenType
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param eventtypeid
	 */
	public void deleteEOEvenType(int eventtypeid) {
		// TODO - implement EODatabaseInterface.deleteEOEvenType
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param phone
	 * @param email
	 */
	public void createFacilitatorContactInfo(String name, String phone, String email) {
		// TODO - implement EODatabaseInterface.createFacilitatorContactInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param facilitatorid
	 */
	public void deleteFacilitatorContactInfo(int facilitatorid) {
		// TODO - implement EODatabaseInterface.deleteFacilitatorContactInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param facilitatorid
	 * @param name
	 * @param phone
	 * @param email
	 */
	public void updateFacilitatorContactInfo(int facilitatorid, String name, String phone, String email) {
		// TODO - implement EODatabaseInterface.updateFacilitatorContactInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param phone
	 * @param email
	 * @param company
	 */
	public void createExternalContactInfo(String name, String phone, String email, String company) {
		// TODO - implement EODatabaseInterface.createExternalContactInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param externalcontactid
	 */
	public void deleteExternalContactInfo(int externalcontactid) {
		// TODO - implement EODatabaseInterface.deleteExternalContactInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param externalcontactid
	 * @param name
	 * @param phone
	 * @param email
	 * @param company
	 */
	public void updateExternalContactInfo(int externalcontactid, String name, String phone, String email, String company) {
		// TODO - implement EODatabaseInterface.updateExternalContactInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 * @param phone
	 * @param email
	 * @param company
	 */
	public void createCustomerContactInfo(String name, String phone, String email, String company) {
		// TODO - implement EODatabaseInterface.createCustomerContactInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customercontactid
	 */
	public void deleteCustomerContactInfo(int customercontactid) {
		// TODO - implement EODatabaseInterface.deleteCustomerContactInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param customercontactid
	 * @param name
	 * @param phone
	 * @param email
	 * @param comapny
	 */
	public void updateCustomerContactInfo(int customercontactid, String name, String phone, String email, String comapny) {
		// TODO - implement EODatabaseInterface.updateCustomerContactInfo
		throw new UnsupportedOperationException();
	}

}