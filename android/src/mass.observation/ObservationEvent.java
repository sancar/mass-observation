package mass.observation;
public class ObservationEvent {
	public ObservationEvent(int id, String name, String desc) {
		this.setId(id);
		this.setName(name);
		this.setDesc(desc);
	}
	public ObservationEvent() {
		this.setId(0);
		this.setName("<no-name>");
		this.setDesc("<no-desc>");
	}
	@Override
	public String toString(){
		return this.name;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return desc;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setPoll(String poll) {
		this.poll = poll;
	}
	public String getPoll() {
		return poll;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getText() {
		return text;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getVideo() {
		return video;
	}
	public void setAudio(String audio) {
		this.audio = audio;
	}
	public String getAudio() {
		return audio;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage() {
		return image;
	}
	private int id;
	private String name;
	private String desc;
	private String poll;
	private String text;
	private String video;
	private String audio;
	private String image;
	static int OE_MAX = 10;
}
