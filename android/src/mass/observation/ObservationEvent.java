package mass.observation;
public class ObservationEvent {
	public ObservationEvent(int id, String name, String desc) {
		this.setId(id);
		this.setName(name);
		this.setDesc(desc);
	}
	@Override
	public String toString(){
		return name;
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
	private int id;
	private String name;
	private String desc;
	static int OE_MAX = 10;
}
