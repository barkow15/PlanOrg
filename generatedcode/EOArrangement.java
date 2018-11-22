public class EOArrangement {

	private EOEvent[] events;
	private CustomerContactInfo customer;
	private FacilitatorContactInfo[] facilitators;
	private string name;
	private string description;
	private LocalDateTime datetimestart;
	private LocalDateTime datetimeend;
	private double price;

	public EOEvent[] getEvents() {
		return this.events;
	}

	public CustomerContactInfo getCustomer() {
		return this.customer;
	}

	public FacilitatorContactInfo[] getFacilitators() {
		return this.facilitators;
	}

	public String getName() {
		// TODO - implement EOArrangement.getName
		throw new UnsupportedOperationException();
	}

	public String getDescription() {
		// TODO - implement EOArrangement.getDescription
		throw new UnsupportedOperationException();
	}

	public LocalDateTime getDateTimeStart() {
		// TODO - implement EOArrangement.getDateTimeStart
		throw new UnsupportedOperationException();
	}

	public LocalDateTime getDateTimeEnd() {
		// TODO - implement EOArrangement.getDateTimeEnd
		throw new UnsupportedOperationException();
	}

	public double getPrice() {
		return this.price;
	}

}