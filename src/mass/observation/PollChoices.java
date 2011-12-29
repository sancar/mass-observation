package mass.observation;
public class PollChoices {
	public PollChoices(int id, String text) {
		this.setId(id);
		this.setText(text);
		
	}
	public PollChoices() {
		this.setId(0);
		this.setText("<no-name>");
		this.setPollID(0);
		
	}
	@Override
	public String toString(){
		return this.choice_text;
	}

	public void setPollID(int pollID) {
		this.pollID = pollID;
	}
	public int getpollID() {
		return pollID;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setText(String text) {
		this.choice_text = text;
	}
	public String getText() {
		return choice_text;
	}

	private int id;
	private String choice_text;
	private int pollID;

}
