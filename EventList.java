
import java.util.*;

public class EventList {

	public ArrayList<Event> list = new ArrayList<Event>();

	/**
	 * Default constructor.
	 */
	EventList() {

	}

	/**
	 * This method checks the list to find out whether or not it is empty.
	 * 
	 * @return: Returns a boolean value. If the list is empty, it returns true. If
	 *          it is not empty, it returns false.
	 */
	public boolean isEmpty() {
		if (this.list == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method inserts an event into the list and then sorts the list according
	 * to time.
	 * 
	 * @param e: This parameter, of type Event, represents the event being added to
	 *           the list.
	 */
	public void insert(Event e) {

		list.add(e);
		Collections.sort(list);

	}

	/**
	 * This method removes the first event from the list.
	 */
	public void remove() {
		list.remove(0);
	}

	/**
	 * This method gets the first event in the list.
	 * 
	 * @return: Returns an Event from the list.
	 */
	public Event retrieve() {
		return list.get(0);
	}

}
