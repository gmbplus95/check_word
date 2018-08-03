package checkword;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class CheckOb implements Cloneable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String partnerId;
    private String bucketId;
    private String objectId;
    private Date startDate;
    private Date endDate;
    private String objectName;
    private String objectType;
    private Map<String, String> field;
    private Map<String, String> refChild;
    private Map<String, String> refParent;
    private Map<String, String> refFormula;
    //Transient
    private String meter;
    private String strStartDate;
    private String impulsion;
    private String id;
    private String name;
    private String unit;
    private String aggregation;
    private String type;
    private String lastPulse;
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getBucketId() {
		return bucketId;
	}
	public void setBucketId(String bucketId) {
		this.bucketId = bucketId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public Map<String, String> getField() {
		return field;
	}
	public void setField(Map<String, String> field) {
		this.field = field;
	}
	public Map<String, String> getRefChild() {
		return refChild;
	}
	public void setRefChild(Map<String, String> refChild) {
		this.refChild = refChild;
	}
	public Map<String, String> getRefParent() {
		return refParent;
	}
	public void setRefParent(Map<String, String> refParent) {
		this.refParent = refParent;
	}
	public Map<String, String> getRefFormula() {
		return refFormula;
	}
	public void setRefFormula(Map<String, String> refFormula) {
		this.refFormula = refFormula;
	}
	public String getMeter() {
		return meter;
	}
	public void setMeter(String meter) {
		this.meter = meter;
	}
	public String getStrStartDate() {
		return strStartDate;
	}
	public void setStrStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}
	public String getImpulsion() {
		return impulsion;
	}
	public void setImpulsion(String impulsion) {
		this.impulsion = impulsion;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getAggregation() {
		return aggregation;
	}
	public void setAggregation(String aggregation) {
		this.aggregation = aggregation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLastPulse() {
		return lastPulse;
	}
	public void setLastPulse(String lastPulse) {
		this.lastPulse = lastPulse;
	}
	public CheckOb() {
		super();
	}
	public CheckOb(String partnerId, String bucketId, String objectId, Date startDate, Date endDate, String objectName,
			String objectType, Map<String, String> field, Map<String, String> refChild, Map<String, String> refParent,
			Map<String, String> refFormula, String meter, String strStartDate, String impulsion, String id, String name,
			String unit, String aggregation, String type, String lastPulse) {
		super();
		this.partnerId = partnerId;
		this.bucketId = bucketId;
		this.objectId = objectId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.objectName = objectName;
		this.objectType = objectType;
		this.field = field;
		this.refChild = refChild;
		this.refParent = refParent;
		this.refFormula = refFormula;
		this.meter = meter;
		this.strStartDate = strStartDate;
		this.impulsion = impulsion;
		this.id = id;
		this.name = name;
		this.unit = unit;
		this.aggregation = aggregation;
		this.type = type;
		this.lastPulse = lastPulse;
	}
	@Override
	public String toString() {
		return "CheckOb [partnerId=" + partnerId + ", bucketId=" + bucketId + ", objectId=" + objectId + ", startDate="
				+ startDate + ", endDate=" + endDate + ", objectName=" + objectName + ", objectType=" + objectType
				+ ", field=" + field + ", refChild=" + refChild + ", refParent=" + refParent + ", refFormula="
				+ refFormula + ", meter=" + meter + ", strStartDate=" + strStartDate + ", impulsion=" + impulsion
				+ ", id=" + id + ", name=" + name + ", unit=" + unit + ", aggregation=" + aggregation + ", type=" + type
				+ ", lastPulse=" + lastPulse + "]";
	}
    
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	
	
}
