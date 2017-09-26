package fixedasset.data;

import com.component.datamodel.ATree;

public class TypeTree extends ATree {
	private String levelId;
	private String unit;
	private double netSalvage;

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getNetSalvage() {
		return netSalvage;
	}

	public void setNetSalvage(double netSalvage) {
		this.netSalvage = netSalvage;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}
}
