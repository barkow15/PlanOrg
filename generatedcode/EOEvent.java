public class EOEvent {

	private EOEventType[] eventtypes;
	private LocalDateTime datetimestart;
	private LocalDataTime datetimeend;
	private double price;
	private String description;

	public EOEventType[] getEventTypes() {
		// TODO - implement EOEvent.getEventTypes
		throw new UnsupportedOperationException();
	}

	public LocalDateTime getDateTimeStart() {
		// TODO - implement EOEvent.getDateTimeStart
		throw new UnsupportedOperationException();
	}

	public LocalDateTime getDateTimeEnd() {
		// TODO - implement EOEvent.getDateTimeEnd
		throw new UnsupportedOperationException();
	}

	public double getPrice() {
		return this.price;
	}

	public String getDescription() {
		return this.description;
	}

}