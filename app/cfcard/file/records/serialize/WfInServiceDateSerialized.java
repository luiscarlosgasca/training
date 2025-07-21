package com.cloudframe.app.cfcard.file.records.serialize;

/**
*  The class WfInServiceDateSerialized is used to define offsets in order to serialize
*  in a fixed String
*  @author CloudFrame Inc.
*  created on 2024-10-04 at 13:58. using version 5.0.0.136
**/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cloudframe.app.data.Field;
import com.cloudframe.app.exception.CFException;

public class WfInServiceDateSerialized  extends Field { 

    protected Logger logger = LoggerFactory.getLogger(WfInServiceDateSerialized.class);
	/*  Length of the field, if serialized as a String */
	protected static final int WF_IN_SERVICE_DATE_LENGTH = 10;
   /*  offset of each of Child Fields when serialized as a String */
            protected  int beginWfInServiceMm;
	
	/**
	* Constructor for WfInServiceDateSerialized
	**/
    public WfInServiceDateSerialized() {
	// TO-DO auto generated code
    }
 
	/**
	* Constructor for WfInServiceDateSerialized. sets the parent value to the parent
	* @param parent
	* @param begin
	**/
    public WfInServiceDateSerialized(Field parent,int begin) {
    	   setParent(parent,begin);
    }
    
	/**
	* sets parent for this WfInServiceDateSerialized to the parent
	* @param parent
	**/
    @Override
    public void setParent(Field parent) {
    	setParent(parent,16); // serialize this field at offset 16 by default 
    }
    
	/**
	* sets parent for this WfInServiceDateSerialized to the parent
	* and set the serialize offset to parameter begin
	* @param parent
	* @param begin - offset used when serializing this object to a String
	**/
    public void setParent(Field parent,int begin) {
    	super.setParent(parent);
    	init(begin); // serialize this field at offset 16 by default
    }    
	/**
	* initializes the field in WfInServiceDateSerialized
	**/
	@Override
	protected void init(int begin) {
	   setStartOffset(begin);
	   setLength(WF_IN_SERVICE_DATE_LENGTH);
	   /*  set the offset/position of each field when this object is serialized as String */
             beginWfInServiceMm = getStartOffset() + 0;	// set offset for serialization
  
	   /*  end of offset */
	}
     int localWfInServiceMmCounter = -1;
     public boolean isWfInServiceMmModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localWfInServiceMmCounter != sharedCounter;
         localWfInServiceMmCounter = sharedCounter; return hasModified;
     }
	protected static final int WF_IN_SERVICE_MM_LEN = 2;
	/**
	 * 	serialize this WfInServiceMm
	 */
   protected void serializeWfInServiceMm(char[] wfInServiceMm) {
        shareString.getSerializedField().incrementCounter();
        arraycopy(wfInServiceMm,0,getStringValue(),beginWfInServiceMm,WF_IN_SERVICE_MM_LEN);
       localWfInServiceMmCounter = shareString.getSerializedField().getModifiedCounter();  	
   }

   protected char[] checkWfInServiceMmConstraints(char[] value) {
   			return super.checkConstraints(value , 2 ,false, false);
   }
    /**
	 *	refreshWfInServiceMm is used to refresh the latest value of a variable from the Serialized String
	 *  the most common reason to serialize an Object as a string in to write to a file. There can be several other reasons for serialization as well
	 */ 
   	public char[] refreshWfInServiceMm() {	 
   		return (substring(getStringValue(),beginWfInServiceMm,beginWfInServiceMm + WF_IN_SERVICE_MM_LEN));
   	}




}
  
