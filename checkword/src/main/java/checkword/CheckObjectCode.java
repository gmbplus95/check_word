package checkword;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
/**
 * @author nvbac
 */
public class CheckObjectCode {
	
	private static final String EMPTY="";
	
	private static final String OBJECT_CODE="object_code";

	private static final String PARTNER_ID="partnerId";
	
	private static final String BUCKIT_ID="bucketId";

	private static final String OBJECT_ID="objectId";
	
	private static final String START_DATE="startDate";

	private static final String END_DATE="endDate";
	
	private static final String OBJECT_NAME="objectName";
	
	private static final String OBJECT_TYPE="objectType";

	private static final String FIELD="field";
	
	private static final String REFCHILD="refChild";

	private static final String REFPARENT="refParent";

	private static final String REFFORMULA="refFormula";

	private static final String METER="meter";

	private static final String STRSTARTDATE="strStartDate";

	private static final String IMPULSION="impulsion";

	private static final String ID="id";

	private static final String NAME="name";

	private static final String UNIT="unit";

	private static final String AGGREGATION="aggregation";

	private static final String TYPE="type";
	
	private static final String LASTPULSE="lastPulse";
	
	
	public static void main(String[] args){
		//read file
		File file = new File("c:\\Users\\Admin\\Desktop\\tma142.txt");
		try {
			 @SuppressWarnings("resource")
			Scanner scanner = new Scanner(file);
			    //now read the file line by line...
			 	List<String> lineContainPartnerId=new ArrayList<String>();
			    while (scanner.hasNextLine()) {
			        String line = scanner.nextLine();
			        if(line.contains(PARTNER_ID)) { 
			        	lineContainPartnerId.add(line);
			        }
			    }
	            FileWriter writer  = new FileWriter("c:\\Users\\Admin\\Desktop\\script.txt");
	            CheckOb checkOb=null;
			    for(String line:lineContainPartnerId) {
			    	int i=line.indexOf("Request body: ");
			    	String myjson=line.substring(i+14);
			        @SuppressWarnings("unchecked")
					LinkedHashMap<String, Object> json = new Gson().fromJson(myjson, LinkedHashMap.class);
			        checkOb=new CheckOb();
			        checkOb.setPartnerId(json.get(PARTNER_ID)==null?EMPTY:json.get(PARTNER_ID).toString());
			        checkOb.setBucketId(json.get(BUCKIT_ID)==null?EMPTY:json.get(BUCKIT_ID).toString());
			        checkOb.setObjectId(json.get(OBJECT_ID)==null?EMPTY:json.get(OBJECT_ID).toString());
			        //start date & end date
			        double start_date = (Double) (json.get(START_DATE)==null?0D:json.get(START_DATE));
			        double end_date = (Double) (json.get(END_DATE)==null?0D:json.get(END_DATE));
			        if(start_date==0D) {
			        	checkOb.setStartDate(null);
			        }
			        else {
				        Date startDate=new Date((long) start_date);
			        	checkOb.setStartDate(startDate);
			        }
			        if(end_date==0D) {
				        checkOb.setEndDate(null);
				        }
			        else {
			        	Date endDate=new Date((long) end_date);
			        	checkOb.setEndDate(endDate);
			        }
			        checkOb.setObjectName(json.get(OBJECT_NAME)==null?EMPTY:json.get(OBJECT_NAME).toString());
			        checkOb.setObjectType(json.get(OBJECT_TYPE)==null?EMPTY:json.get(OBJECT_TYPE).toString());
			        checkOb.setMeter(json.get(METER)==null?EMPTY:json.get(METER).toString());
			        checkOb.setStrStartDate(json.get(STRSTARTDATE)==null?EMPTY:json.get(STRSTARTDATE).toString());
			        checkOb.setImpulsion(json.get(IMPULSION)==null?EMPTY:json.get(IMPULSION).toString());
			        checkOb.setId(json.get(ID)==null?EMPTY:json.get(ID).toString());
			        checkOb.setName(json.get(NAME)==null?EMPTY:json.get(NAME).toString());
			        checkOb.setUnit(json.get(UNIT)==null?EMPTY:json.get(UNIT).toString());
			        checkOb.setAggregation(json.get(AGGREGATION)==null?EMPTY:json.get(AGGREGATION).toString());
			        checkOb.setType(json.get(TYPE)==null?EMPTY:json.get(TYPE).toString());
			        checkOb.setLastPulse(json.get(LASTPULSE)==null?EMPTY:json.get(LASTPULSE).toString());
			        //field
			        Map<String,String> field= (Map<String, String>) json.get(FIELD);
			        //refchild
			        Map<String,String> refChild= (Map<String, String>) json.get(REFCHILD);
			        //refparent
			        Map<String,String> refParent= (Map<String, String>) json.get(REFPARENT);
			        //refformula
			        Map<String,String> refFormula= (Map<String, String>) json.get(REFFORMULA);
			        //+1 object code
			    	int a=Integer.parseInt(field.get(OBJECT_CODE))+1;
			    	field.remove(OBJECT_CODE);
			    	field.put(OBJECT_CODE, String.valueOf(a));
			        checkOb.setField(field);
			        checkOb.setRefChild(refChild);
			        checkOb.setRefParent(refParent);
			        checkOb.setRefFormula(refFormula);
			        //generate script
			        StringBuilder insert=new StringBuilder();
					insert.append("INSERT INTO iot_params.object ");
					insert.append("(partner_id,bucket_id,object_id,start_date,end_date,field,object_name,object_type,ref_child,ref_formula,ref_parent) ");
					insert.append("VALUES ");
					insert.append("('");
					insert.append(checkOb.getPartnerId());
					insert.append("',");
					insert.append("'");
					insert.append(checkOb.getBucketId());
					insert.append("',");
					insert.append("'");
					insert.append(checkOb.getObjectId());
					insert.append("',");
					insert.append("'");
					insert.append(checkOb.getStartDate()==null?null:checkOb.getStartDate().getTime());
					insert.append("',");
					insert.append("'");
					insert.append(checkOb.getEndDate()==null?null:checkOb.getEndDate().getTime());
					insert.append("',");
					//field
					insert.append("{");
					insert.append(isContainsKey(checkOb.getField(),"nature_fluid")==null?EMPTY:isContainsKey(checkOb.getField(), "nature_fluid"));
					insert.append(isContainsKey(checkOb.getField(),"storage_capacity")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "storage_capacity"));
					insert.append(isContainsKey(checkOb.getField(),"coefficient_pci")==null?"":","+ isContainsKey(checkOb.getField(), "coefficient_pci"));
					insert.append(isContainsKey(checkOb.getField(),"calculation_formula")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "calculation_formula"));
					insert.append(isContainsKey(checkOb.getField(),"delivery_id")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "delivery_id"));
					insert.append(isContainsKey(checkOb.getField(),"stock_meter")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "stock_meter"));
					insert.append(isContainsKey(checkOb.getField(),"fluid_name")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "fluid_name"));
					insert.append(isContainsKey(checkOb.getField(),"meter_type")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "meter_type"));
					insert.append(isContainsKey(checkOb.getField(),"releve_priority")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "releve_priority"));
					insert.append(isContainsKey(checkOb.getField(),"meter_billed")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "meter_billed"));
					insert.append(isContainsKey(checkOb.getField(),"coefficient_correction")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "coefficient_correction"));
					insert.append(isContainsKey(checkOb.getField(),"included_calculation_nc")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "included_calculation_nc"));
					insert.append(isContainsKey(checkOb.getField(),"libel")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "libel"));
					insert.append(isContainsKey(checkOb.getField(),"number_of_wheel")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "number_of_wheel"));
					insert.append(isContainsKey(checkOb.getField(),"sub_type")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "sub_type"));
					insert.append(isContainsKey(checkOb.getField(),"unit_code")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "unit_code"));
					insert.append(isContainsKey(checkOb.getField(),"object_code")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "object_code"));
					insert.append(isContainsKey(checkOb.getField(),"fluid")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "fluid"));
					insert.append(isContainsKey(checkOb.getField(),"obligatory_to_entry")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "obligatory_to_entry"));
					insert.append(isContainsKey(checkOb.getField(),"time_resolution")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "time_resolution"));
					insert.append(isContainsKey(checkOb.getField(),"coefficient_pcs")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "coefficient_pcs"));
					insert.append(isContainsKey(checkOb.getField(),"consumption_threshold")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "consumption_threshold"));
					insert.append(isContainsKey(checkOb.getField(),"source")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "source"));
					insert.append(isContainsKey(checkOb.getField(),"actif")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "actif"));
					insert.append(isContainsKey(checkOb.getField(),"is_deleted")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "is_deleted"));
					insert.append(isContainsKey(checkOb.getField(),"order_number")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "order_number"));
					insert.append(isContainsKey(checkOb.getField(),"ref_supply")==null?EMPTY:","+ isContainsKey(checkOb.getField(), "ref_supply"));
					insert.append("},");
					insert.append("'");
					insert.append(checkOb.getObjectName());
					insert.append("',");
					insert.append("'");
					insert.append(checkOb.getObjectType());
					insert.append("',");
					//refchild
					if(checkOb.getRefChild()==null) {
						insert.append("null,");
					}
					else {
						if(checkOb.getRefChild().containsKey("iot_params.object.object_id") && checkOb.getRefChild().containsKey("iot_params.site.site_id")) {
							insert.append("{");
							insert.append(isContainsKey(checkOb.getRefChild(),"iot_params.object.object_id")==null?EMPTY:isContainsKey(checkOb.getRefChild(), "iot_params.object.object_id"));
							insert.append(isContainsKey(checkOb.getRefChild(),"iot_params.site.site_id")==null?EMPTY:","+isContainsKey(checkOb.getRefChild(), "iot_params.site.site_id"));
							insert.append("},");
						}
						else if(checkOb.getRefChild().containsKey("iot_params.object.object_id")){
							insert.append("{");
							insert.append(isContainsKey(checkOb.getRefChild(),"iot_params.object.object_id")==null?EMPTY:isContainsKey(checkOb.getRefChild(), "iot_params.object.object_id"));
							insert.append("},");
						}
							else {
								insert.append("{");
								insert.append(isContainsKey(checkOb.getRefChild(),"iot_params.site.site_id")==null?EMPTY:isContainsKey(checkOb.getRefChild(), "iot_params.site.site_id"));
								insert.append("'},");
							}
							
					}
					//refformula
					if(checkOb.getRefFormula()==null) {
						insert.append("null,"); 
					}
					//refparent
					if(checkOb.getRefParent()==null) {
						insert.append("null})\n");
					}
					else {
						if(checkOb.getRefParent().containsKey("iot_params.object.object_id") && checkOb.getRefParent().containsKey("iot_params.site.site_id")) {
							insert.append("{");
							insert.append(isContainsKey(checkOb.getRefParent(),"iot_params.object.object_id")==null?EMPTY:isContainsKey(checkOb.getRefParent(), "iot_params.object.object_id"));
							insert.append(isContainsKey(checkOb.getRefParent(),"iot_params.site.site_id")==null?EMPTY:","+isContainsKey(checkOb.getRefParent(), "iot_params.site.site_id"));
							insert.append("}\n");
						}
						else if(checkOb.getRefParent().containsKey("iot_params.object.object_id")){
							insert.append("{");
							insert.append(isContainsKey(checkOb.getRefParent(),"iot_params.object.object_id")==null?EMPTY:isContainsKey(checkOb.getRefParent(), "iot_params.object.object_id"));
							insert.append("}\n");
						}
							else {
								insert.append("{");
								insert.append(isContainsKey(checkOb.getRefParent(),"iot_params.site.site_id")==null?EMPTY:isContainsKey(checkOb.getRefParent(), "iot_params.site.site_id"));
								insert.append("'}\n");
							}
							
							
					}
					//write file
		            String b=insert.toString();		            
	            	writer.write(b);
			    }
			    writer.close();
		} catch (Exception e) {
			System.out.println("ERROR:"+e.getMessage());
		}

	}
	static final String isContainsKey(Map<String,String> a,String key) {
		StringBuilder sb=new StringBuilder();
		if(a.containsKey(key)) {
			sb.append("'");
			sb.append(key);
			sb.append("':'");
			sb.append(a.get(key));
			sb.append("'");
			return sb.toString();
		}
		else return null;
	}
	
	
}
