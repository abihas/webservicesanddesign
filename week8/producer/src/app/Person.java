package app;

public class Person {
	private String name;
	private String code;
	
	public Person (String name, String passwd) {
		setName(name);
		setCode(passwd);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
