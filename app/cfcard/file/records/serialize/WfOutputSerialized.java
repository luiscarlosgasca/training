package com.cloudframe.app.cfcard.file.records.serialize;

/**
*  The class WfOutputSerialized is used to define offsets in order to serialize
*  in a fixed String
*  @author CloudFrame Inc.
*  created on 2024-10-04 at 13:58. using version 5.0.0.136
**/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cloudframe.app.data.Field;
import com.cloudframe.app.exception.CFException;
import java.math.BigDecimal;

public class WfOutputSerialized  extends Field { 

    protected Logger logger = LoggerFactory.getLogger(WfOutputSerialized.class);
	/*  Length of the field, if serialized as a String */
	protected static final int WF_OUTPUT_LENGTH = 31;
   /*  offset of each of Child Fields when serialized as a String */
            protected  int beginWfOutCardNumber;
            protected  int beginWfOutServiceMm;
            protected  int beginWfOutTotal;
	
	/**
	* Constructor for WfOutputSerialized
	**/
    public WfOutputSerialized() {
		   			init(0);
    }
 
	/**
	* initializes the field in WfOutputSerialized
	**/
	@Override
	protected void init(int begin) {
	   setStartOffset(begin);
	   setLength(WF_OUTPUT_LENGTH);
	   /*  set the offset/position of each field when this object is serialized as String */
             beginWfOutCardNumber = getStartOffset() + 0;	// set offset for serialization
  
             beginWfOutServiceMm = getStartOffset() + 16;	// set offset for serialization
  
             beginWfOutTotal = getStartOffset() + 18;	// set offset for serialization
  
	   /*  end of offset */
	}
     int localWfOutCardNumberCounter = -1;
     public boolean isWfOutCardNumberModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localWfOutCardNumberCounter != sharedCounter;
         localWfOutCardNumberCounter = sharedCounter; return hasModified;
     }
	protected static final int WF_OUT_CARD_NUMBER_LEN = 16;
	/**
	 * 	serialize this WfOutCardNumber
	 */
   protected void serializeWfOutCardNumber(char[] wfOutCardNumber) {
        shareString.getSerializedField().incrementCounter();
        arraycopy(wfOutCardNumber,0,getStringValue(),beginWfOutCardNumber,WF_OUT_CARD_NUMBER_LEN);
       localWfOutCardNumberCounter = shareString.getSerializedField().getModifiedCounter();  	
   }

   protected char[] checkWfOutCardNumberConstraints(char[] value) {
   			return super.checkConstraints(value , 16 ,false, false);
   }
    /**
	 *	refreshWfOutCardNumber is used to refresh the latest value of a variable from the Serialized String
	 *  the most common reason to serialize an Object as a string in to write to a file. There can be several other reasons for serialization as well
	 */ 
   	public char[] refreshWfOutCardNumber() {	 
   		return (substring(getStringValue(),beginWfOutCardNumber,beginWfOutCardNumber + WF_OUT_CARD_NUMBER_LEN));
   	}
     int localWfOutServiceMmCounter = -1;
     public boolean isWfOutServiceMmModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localWfOutServiceMmCounter != sharedCounter;
         localWfOutServiceMmCounter = sharedCounter; return hasModified; 
     }     

	/**
	 *	Returns String value of wfOutServiceMm
	 *	@return wfOutServiceMm
	 */
	public char[]  getWfOutServiceMmString() {
	     return getCharArray(beginWfOutServiceMm,WF_OUT_SERVICE_MM_LEN);
	}
	
	 /**
	 *  This method allows testing if there is a numeric value stored in the serialized String
	 *	@return true if numeric value is stored in the string
	 */
	public boolean wfOutServiceMmIsNumeric() {
	    return isNumeric(beginWfOutServiceMm
	                    ,beginWfOutServiceMm + WF_OUT_SERVICE_MM_LEN
	                    ,false/*Signed*/,true/*isSign trailing*/,false/*isSignStoredSeparately*/);
	}

  
   protected  static final int WF_OUT_SERVICE_MM_LEN = 2;
  	/**
	 * serializeWfOutServiceMm
	 */
	protected void serializeWfOutServiceMm(int wfOutServiceMm) {
		 putNumber(beginWfOutServiceMm,wfOutServiceMm,WF_OUT_SERVICE_MM_LEN,false/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/); 
		 localWfOutServiceMmCounter = shareString.getSerializedField().getModifiedCounter();

    }
    /**
	 * serializeWfOutServiceMm
	 */
   	protected  int serializeWfOutServiceMm(char[] value) {
	    int  wfOutServiceMm;
	    if(value.length >0 && value.length!= 2)
            value = new String(value).trim().toCharArray();
	    if (value.length < 2) value = pad(2, value, ' ', LEFT_PAD);
	    else if (value.length > 2) value = substring(value,0,2);
	    /*  String can consists of digit or a non digit characters, in case of non digit characters, mimic COBOL Behavior and take only the last 4 bits per char and convert that to a number */
	    wfOutServiceMm = (int) convertString2Number(value,false/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/);
		replaceValue(
		       padNumber(2,value,false/*isSigned?*/)
		       ,beginWfOutServiceMm
		       ,2
		      );
		 localWfOutServiceMmCounter = shareString.getSerializedField().getModifiedCounter();
		return  wfOutServiceMm;
    }

   protected int checkWfOutServiceMmMaxLimit(long number) {

	   return (int)checkMaxLimit(number , MAX_100/*limit*/  , false/*isSigned*/);
   }
    /**
	 *	refreshWfOutServiceMm is used to refresh the latest value of a variable from the Serialized String
	 *  the most common reason to serialize an Object as a string in to write to a file. There can be several other reasons for serialization as well
	 */ 
   	public int refreshWfOutServiceMm() throws CFException {
   	try {	 
			return (
			          getIntNumber(
			                  beginWfOutServiceMm
			                 ,WF_OUT_SERVICE_MM_LEN
			                 ,false/*isSigned*/,true/*isSignTrailing*/,false/*isSignStoredSeparate*/)              			                 
			          ); 
	} catch(Exception ex) {
    	throw getSoc7ABend("wfOutServiceMm", beginWfOutServiceMm,WF_OUT_SERVICE_MM_LEN);
    }
   	}
     int localWfOutTotalCounter = -1;
     
     public boolean isWfOutTotalModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localWfOutTotalCounter != sharedCounter;
         localWfOutTotalCounter = sharedCounter; return hasModified; 
     }  	
	
	 /**
	 *	Returns String value of wfOutTotal
	 *	@return wfOutTotal
	 */
	public char[]  getWfOutTotalString() {
	    return getCharArray(beginWfOutTotal,WF_OUT_TOTAL_LEN);
	}

	/**
	 *  This method allows testing if there is a numeric value stored in the serialized String
	 *	@return true if numeric value is stored in the string
	 */
	public boolean wfOutTotalIsNumeric() {
		    return isNumeric(beginWfOutTotal
	                    ,beginWfOutTotal + WF_OUT_TOTAL_LEN
	                    ,false/*Signed*/,true/*isSign trailing*/,false/*isSignStoredSeparately*/);
	}

  
  protected  static final int WF_OUT_TOTAL_LEN = 13;
  protected  static final int WF_OUT_TOTAL_SCALE = 3;

   protected BigDecimal checkWfOutTotalMaxLimit(BigDecimal number) {
		return checkMaxLimit(number,3/*scale*/,13/*precision*/);
   }

  	/**
	 * serializeWfOutTotal
	 */
	protected void serializeWfOutTotal(BigDecimal wfOutTotal) {
	       putNumber(beginWfOutTotal,wfOutTotal,WF_OUT_TOTAL_LEN,WF_OUT_TOTAL_SCALE,false/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/); 
		 localWfOutTotalCounter = shareString.getSerializedField().getModifiedCounter();
   }
  
    /**
	 * serializeWfOutTotal
	 */
   	protected  BigDecimal serializeWfOutTotal(char[] value) throws CFException {
        if (value.length < 13) value = pad(13, value, ' ', RIGHT_PAD);
        replaceValue(
		       padNumber(13,value,false/*isSigned?*/)
		       ,beginWfOutTotal
		       ,13
		      );		 localWfOutTotalCounter = shareString.getSerializedField().getModifiedCounter();
		try {
			return  convertString2Decimal(value,WF_OUT_TOTAL_LEN,WF_OUT_TOTAL_SCALE,false/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/);
    	} catch(Exception ex) {
    		throw getSoc7ABend("wfOutTotal", beginWfOutTotal,WF_OUT_TOTAL_LEN);
    	}
    }
    /**
	 *	refreshWfOutTotal is used to refresh the latest value of a variable from the Serialized String
	 *  the most common reason to serialize an Object as a string in to write to a file. There can be several other reasons for serialization as well
	 */ 
   	public BigDecimal refreshWfOutTotal() throws CFException {
   	try {	 
			return (
			         getDecimalNumber(
			             beginWfOutTotal
			            ,WF_OUT_TOTAL_LEN
			            ,WF_OUT_TOTAL_SCALE
			            ,false/*isSigned*/,true/*isSignTrailing*/,false/*isSignStoredSeparate*/)              			                 			        
			        );
	} catch(Exception ex) {
    	throw getSoc7ABend("wfOutTotal", beginWfOutTotal,WF_OUT_TOTAL_LEN);
    }
   	}




}
  
