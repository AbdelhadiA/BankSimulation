public class Event implements Comparable<Event> {

	protected Integer time;
	protected int transTime;
	char type;

	/**
	 * This constructor is used to initialize an Arrival event.
	 * 
	 * @param c          : Since the event is an arrival event, it will initialize
	 *                   the type variable with the parameter c, which will be 'A'.
	 * @param arrival:   This parameter, of type int, represents the time of arrival
	 *                   for the customer.
	 * @param transTime: transTime represents the transaction time for a customer
	 *                   once they reach the front of the line.
	 */
	Event(char c, int arrival, int transTime) {
		type = c;
		this.time = arrival;
		this.transTime = transTime;

	}

	/**
	 * This constructor is used to initialize a Departure event.
	 * 
	 * @param c:          Used to intialize the type variable with parameter c,
	 *                    which would be 'D' since it is a departure event.
	 * @param departTime: The departTime parameter is used to initialize the time
	 *                    variable, and represents the time the customer departs the
	 *                    bank.
	 */
	Event(char c, int departTime) {
		type = c;
		this.time = departTime;
	}

	/**
	 * This method gets the arrival time.
	 * 
	 * @return: Returns an integer, representing the time the customer arrives.
	 */
	public int getArrival() {
		return time;
	}

	/**
	 * This method gets the departure time.
	 * 
	 * @return: Returns an integer, representing the time the customer departs.
	 */
	public int getDepart() {
		return time;
	}

	/**
	 * This method is used to check the type of the event. It could be either a
	 * departure event or an arrival event.
	 * 
	 * @return : If the type variable == 'A', it is an arrival event and the method
	 *         returns a boolean value, true. Otherwise, it returns false.
	 */
	public boolean checkType() {

		if (type == 'A') {
			return true;
		}

		else {
			return false;
		}
	}

	/**
	 * This method compares two events based on their time.
	 * 
	 * @param obj: This is the event the current event is being compared to in
	 *             accordance to time.
	 */
	public int compareTo(Event obj) {
		int val;

		val = this.time.compareTo(obj.time);

		if (val == 0 && obj.type == 'D') {
			return -1;
		}

		else if (val == 0 && this.type == 'D') {
			return 1;
		}

		else {
			return val;
		}

	}

	/**
	 * This method returns a string which is the output of our program.
	 * 
	 * @return: Returns a string. If the type of event is == 'A', it returns the
	 *          output corresponding to an arrival event. Otherwise, it returns the
	 *          output corresponding to a departure event.
	 */
	public String toString() {
		if (type == 'A') {
			return "Processing an arrival event at time: " + " " + time + "\n";
		} else {
			return "Processing a departure event at time: " + time + "\n";
		}
	}

}
