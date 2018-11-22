public enum EOOPERATION {
	START;

	private Object data;

	/**
	 * 
	 * @param data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	public Object getData() {
		return this.data;
	}

	EOOPERATION() {
		// TODO - implement OPERATION.OPERATION
		throw new UnsupportedOperationException();
	}

}