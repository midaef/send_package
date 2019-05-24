
public class Package {
	private Character message;
	private String namePackage;
	private Integer weightPackage;
	private Integer timeLifePackage;
	private String timeCreatePackage;

	public Character getMessage() {
		return message;
	}

	public String getNamePackage() {
		return namePackage;
	}

	public Integer getWeightPackage() {
		return weightPackage;
	}

	public Integer getTimeLifePackage() {
		return timeLifePackage;
	}

	public String getTimeCreatePackage() {
		return timeCreatePackage;
	}

	@Override
	public String toString() {
		return "Package{" +
				"message=" + message +
				", namePackage='" + namePackage + '\'' +
				", weightPackage=" + weightPackage +
				", timeLifePackage=" + timeLifePackage +
				", timeCreatePackage='" + timeCreatePackage + '\'' +
				'}';
	}

	public Package(Character message, String namePackage,
				   Integer weightPackage, Integer timeLifePackage,
				   String timeCreatePackage) {
		this.message = message;
		this.namePackage = namePackage;
		this.weightPackage = weightPackage;
		this.timeLifePackage = timeLifePackage;
		this.timeCreatePackage = timeCreatePackage;
	}

}
