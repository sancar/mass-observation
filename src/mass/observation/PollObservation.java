package mass.observation;
public class PollObservation {
	public PollObservation(int id, String text) {
		this.setId(id);
		this.setText(text);
		
	}
	public PollObservation() {
		this.setId(0);
		this.setText("<no-name>");
		this.setEventID(0);
		
	}
	@Override
	public String toString(){
		return this.text;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public int geteventID() {
		return eventID;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}

	private int id;
	private String text;
	private int eventID;

}
