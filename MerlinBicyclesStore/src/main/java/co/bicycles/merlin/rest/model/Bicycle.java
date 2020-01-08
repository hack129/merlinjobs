package co.bicycles.merlin.rest.model;

public class Bicycle {

	private String id;
	private final String name;
	private final String color;
	private final String brand;
	private final String rim;
	private final String frame;
	private final Double price;
	private final String nickName;
	
	public Bicycle(String id, String name, String color, String brand, String rim, String frame, Double price,
			String nickName) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.brand = brand;
		this.rim = rim;
		this.frame = frame;
		this.price = price;
		this.nickName = nickName;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public String getBrand() {
		return brand;
	}

	public String getRim() {
		return rim;
	}

	public String getFrame() {
		return frame;
	}

	public Double getPrice() {
		return price;
	}

	public String getNickName() {
		return nickName;
	}
}