package mass.observation;

/** 
 * An observation event.
 * 
 *  @author Oguz Demir
 *  @version 1.0
 *  
 */

public class ObservationEvent {
	/**
	 * Class constructor.
	 * 
	 * @param id Identifier of this object.
	 * @param name Name of this object.
	 * @param desc Description of this object.
	 * 
	 */
	public ObservationEvent(int id, String name, String desc) {
		this.setId(id);
		this.setName(name);
		this.setDesc(desc);
	}
	
	/**
	 * Class constructor.
	 *   
	 */
	public ObservationEvent() {
		this.setId(0);
		this.setName("<no-name>");
		this.setDesc("<no-desc>");
	}
	
	/** 
	 * Overrides toString() 
	 * 
	 * @return String representation of this object.
	 * */
	@Override
	public String toString(){
		return this.name;
	}
	
	/** 
	 * Sets the description of this object.
	 * 
	 * @param desc The description of this object.
	 * 
	 * */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * gets the description of this object.
	 * 
	 * @return desc The description of this object.
	 * 
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * Sets the name of this object.
	 * @param name The name of this object
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the name of this object.
	 * @return The name of this object.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the ID of this object.
	 * @param id ID of this object.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Gets the ID of this object.
	 * @return ID of this object.
	 */
	public int getId() {
		return id;
	}
	/** 
	 * Sets poll value.
	 * @param poll The poll value of this object.
	 */
	public void setPoll(String poll) {
		this.poll = poll;
	}
	/**
	 * Gets poll value.
	 *
	 * @return The poll value of this object.
	 */
	public String getPoll() {
		return poll;
	}
	/**
	 * Sets text value.
	 * @param text The text value of this object.
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * Gets text value.
	 *
	 * @return The text value of this object.
	 */
	public String getText() {
		return text;
	}
	/**
	 * Sets video value.
	 * @param video The video value of this object.
	 */
	public void setVideo(String video) {
		this.video = video;
	}
	/**
	 * Gets video value.
	 * @return The video value of this object.
	 */
	public String getVideo() {
		return video;
	}
	/**
	 * Sets video value.
	 * @param audio The video value of this object.
	 */
	public void setAudio(String audio) {
		this.audio = audio;
	}
	/**
	 * Gets audio value.
	 * @return The audio value of this object.
	 */
	public String getAudio() {
		return audio;
	}
	/**
	 * Sets image value.
	 * @param image The image value of this object.
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * Gets image value.
	 * @return The image value of this object.
	 */
	public String getImage() {
		return image;
	}
	/**
	 * Sets observability of this object.
	 * @param canObservable The observability of this object.
	 */
	public void setObservable(boolean canObservable) {
		this.canObservable = canObservable;
	}
	/**
	 * Gets observability information.
	 * @return The observability information of this object.
	 */
	public boolean isObservable() {
		return canObservable;
	}
	
	private int id;
	private String name;
	private String desc;
	private String poll;
	private String text;
	private String video;
	private String audio;
	private String image;
	private boolean canObservable;
	static int OE_MAX = 10;
}
