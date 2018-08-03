package checkword;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;

public class main {
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
	
	
	public static void main(String[] args) throws Exception {
		//read file
		File file = new File("c:\\Users\\nvbac\\Desktop\\tma142.txt");
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
			    int count = 0;
	            FileWriter writer  = new FileWriter("c:\\Users\\nvbac\\Desktop\\script.txt"); 
			    for(String line:lineContainPartnerId) {
			    	int i=line.indexOf("Request body: ");
			    	String myjson=line.substring(i+14);
			        LinkedHashMap<String, Object> json = new Gson().fromJson(myjson, LinkedHashMap.class);
			     // iterating
//			        for(Map.Entry<String, Object> entry : json.entrySet()){
//			            System.out.println(entry.getKey() + " -> " + entry.getValue());
//			        }
			        CheckOb checkOb=new CheckOb();
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
//			       System.out.println(checkOb.getEndDate()==null?null:checkOb.getStartDate().getTime());
//			        System.out.println(CheckOb.InsertStatement(checkOb));
			        
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
					insert.append("{'");
					//
					insert.append("nature_fluid':'");
					insert.append(checkOb.getField().get("nature_fluid"));
					insert.append("',");
					insert.append("'");
					insert.append("storage_capacity':'");
					insert.append(checkOb.getField().get("storage_capacity"));
					insert.append("',");
					insert.append("'");
					insert.append("coefficient_pci':'");
					insert.append(checkOb.getField().get("coefficient_pci"));
					insert.append("',");
					insert.append("'");
					insert.append("calculation_formula':'");
					insert.append(checkOb.getField().get("calculation_formula"));
					insert.append("',");
					insert.append("'");
					insert.append("delivery_id':'");
					insert.append(checkOb.getField().get("delivery_id"));
					insert.append("',");
					insert.append("'");
					insert.append("stock_meter':'");
					insert.append(checkOb.getField().get("stock_meter"));
					insert.append("',");
					insert.append("'");
					insert.append("fluid_name':'");
					insert.append(checkOb.getField().get("fluid_name"));
					insert.append("',");
					insert.append("'");
					insert.append("meter_type':'");
					insert.append(checkOb.getField().get("meter_type"));
					insert.append("',");
					insert.append("'");
					insert.append("releve_priority':'");
					insert.append(checkOb.getField().get("releve_priority"));
					insert.append("',");
					insert.append("'");
					insert.append("meter_billed':'");
					insert.append(checkOb.getField().get("meter_billed"));
					insert.append("',");
					insert.append("'");
					insert.append("coefficient_correction':'");
					insert.append(checkOb.getField().get("coefficient_correction"));
					insert.append("',");
					insert.append("'");
					insert.append("included_calculation_nc':'");
					insert.append(checkOb.getField().get("included_calculation_nc"));
					insert.append("',");
					insert.append("'");
					insert.append("libel':'");
					insert.append(checkOb.getField().get("libel"));
					insert.append("',");
					insert.append("'");
					insert.append("number_of_wheel':'");
					insert.append(checkOb.getField().get("number_of_wheel"));
					insert.append("',");
					insert.append("'");
					insert.append("sub_type':'");
					insert.append(checkOb.getField().get("sub_type"));
					insert.append("',");
					insert.append("'");
					insert.append("unit_code':'");
					insert.append(checkOb.getField().get("unit_code"));
					insert.append("',");
					insert.append("'");
					insert.append("object_code':'");
					insert.append(checkOb.getField().get("object_code"));
					insert.append("',");
					insert.append("'");
					insert.append("fluid':'");
					insert.append(checkOb.getField().get("fluid"));
					insert.append("',");
					insert.append("'");
					insert.append("obligatory_to_entry':'");
					insert.append(checkOb.getField().get("obligatory_to_entry"));
					insert.append("',");
					insert.append("'");
					insert.append("time_resolution':'");
					insert.append(checkOb.getField().get("time_resolution"));
					insert.append("',");
					insert.append("'");
					insert.append("coefficient_pcs':'");
					insert.append(checkOb.getField().get("coefficient_pcs"));
					insert.append("',");
					insert.append("'");
					insert.append("consumption_threshold':'");
					insert.append(checkOb.getField().get("consumption_threshold"));
					insert.append("',");
					insert.append("'");
					insert.append("source':'");
					insert.append(checkOb.getField().get("source"));
					insert.append("',");
					insert.append("'");
					insert.append("actif':'");
					insert.append(checkOb.getField().get("actif"));
					insert.append("',");
					insert.append("'");
					insert.append("is_deleted':'");
					insert.append(checkOb.getField().get("is_deleted"));
					insert.append("',");
					insert.append("'");
					insert.append("order_number':'");
					insert.append(checkOb.getField().get("order_number"));
					insert.append("},");
					insert.append("'");
					insert.append(checkOb.getObjectName());
					insert.append("',");
					insert.append("'");
					insert.append(checkOb.getObjectType());
					insert.append("',");
					insert.append("{'");
					insert.append("iot_params.object.object_id':'");
					insert.append(checkOb.getRefChild().get("iot_params.object.object_id"));
					insert.append("',");
					insert.append("'");
					insert.append("iot_params.site.site_id':'");
					insert.append(checkOb.getRefChild().get("iot_params.site.site_id"));
					insert.append("'},");
					insert.append("null,");
					insert.append("{'");
					insert.append("iot_params.object.object_id':'");
					insert.append(checkOb.getRefParent().get("iot_params.object.object_id"));
					insert.append("',");
					insert.append("'");
					insert.append("iot_params.site.site_id':'");
					insert.append(checkOb.getRefParent().get("iot_params.site.site_id"));
					insert.append("'})\n");
			        //
		            String b=insert.toString();
				    System.out.println(b);

	            	writer.write(b);
	            	
	            	count++;
//					list.add(b);
			    }

			    writer.close();
			    
			    
		    
			    
			    
			    
//			    //find object_code
//			    List<Integer> listOc=new ArrayList<Integer>();
//			    for(CheckOb mycheckOb: listCheckOb) {
//			    	String objectCode=mycheckOb.getField().get("object_code");
//			    	listOc.add(Integer.parseInt(objectCode));
//			    	}
//		    	Map<String,String> myoc = null;
//			    List<CheckOb> lastResult=new ArrayList<CheckOb>();
//			    for(CheckOb mycheckOb2: listCheckOb) {
//			    	CheckOb newCheckOb=(CheckOb) mycheckOb2.clone();
//			    	newCheckOb
////			    	myoc.put("object_code", String.valueOf(a));
////			    	newCheckOb.setField(myoc);
//				    System.out.println(newCheckOb.getField().get("object_code"));
//			    }

//			    //find duplicate object_code
//			    Map<String, Integer> Counts = new HashMap<String, Integer>();
//			    Map<String, Integer> duplicateCounts = new HashMap<String, Integer>();
//			    for(String oc:listOc) {
//			    	if (Counts.containsKey(oc)) {
//			    		Counts.put(oc, Counts.get(oc) + 1);
//			        } else {
//			        	Counts.put(oc,1);
//			        }
//			    }
//			    for (Map.Entry<String, Integer> entry : Counts.entrySet()) {
//			        for(String oc:listOc) {
//				    	if (entry.getValue()>1 && entry.getKey()==oc) {
//				    		duplicateCounts.put(oc,entry.getValue());
//				        }
//				    }
//			    }
//			    
//			    for (Map.Entry<String, Integer> entry : duplicateCounts.entrySet()) {
//			        System.out.println(entry.getKey() + " = " + entry.getValue());
//			    }
//			    
			    
			    
			    
			    
			    
			    
			    
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
