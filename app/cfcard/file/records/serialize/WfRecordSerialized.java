package com.cloudframe.app.cfcard.file.records.serialize;

/**
*  The class WfRecordSerialized is used to define offsets in order to serialize
*  in a fixed String
*  @author CloudFrame Inc.
*  created on 2024-10-04 at 13:58. using version 5.0.0.136
**/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cloudframe.app.data.Field;
import com.cloudframe.app.exception.CFException;
import java.math.BigDecimal;

public class WfRecordSerialized  extends Field { 

    protected Logger logger = LoggerFactory.getLogger(WfRecordSerialized.class);
	/*  Length of the field, if serialized as a String */
	protected static final int WF_RECORD_LENGTH = 44;
   /*  offset of each of Child Fields when serialized as a String */
            protected  int beginWfInCardNumber;
            protected  int beginWfInBalance;
	
	/**
	* Constructor for WfRecordSerialized
	**/
    public WfRecordSerialized() {
		   			init(0);
    }
 
	/**
	* initializes the field in WfRecordSerialized
	**/
	@Override
	protected void init(int begin) {
	   setStartOffset(begin);
	   setLength(WF_RECORD_LENGTH);
	   /*  set the offset/position of each field when this object is serialized as String */
             beginWfInCardNumber = getStartOffset() + 0;	// set offset for serialization
  
  
             beginWfInBalance = getStartOffset() + 36;	// set offset for serialization
  
	   /*  end of offset */
	}
     int localWfInCardNumberCounter = -1;
     public boolean isWfInCardNumberModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localWfInCardNumberCounter != sharedCounter;
         localWfInCardNumberCounter = sharedCounter; return hasModified;
     }
	protected static final int WF_IN_CARD_NUMBER_LEN = 16;
	/**
	 * 	serialize this WfInCardNumber
	 */
   protected void serializeWfInCardNumber(char[] wfInCardNumber) {
        shareString.getSerializedField().incrementCounter();
        arraycopy(wfInCardNumber,0,getStringValue(),beginWfInCardNumber,WF_IN_CARD_NUMBER_LEN);
       localWfInCardNumberCounter = shareString.getSerializedField().getModifiedCounter();  	
   }

   protected char[] checkWfInCardNumberConstraints(char[] value) {
   			return super.checkConstraints(value , 16 ,false, false);
   }
    /**
	 *	refreshWfInCardNumber is used to refresh the latest value of a variable from the Serialized String
	 *  the most common reason to serialize an Object as a string in to write to a file. There can be several other reasons for serialization as well
	 */ 
   	public char[] refreshWfInCardNumber() {	 
   		return (substring(getStringValue(),beginWfInCardNumber,beginWfInCardNumber + WF_IN_CARD_NUMBER_LEN));
   	}
     int localWfInBalanceCounter = -1;
     
     public boolean isWfInBalanceModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localWfInBalanceCounter != sharedCounter;
         localWfInBalanceCounter = sharedCounter; return hasModified; 
     }  	
	
	 /**
	 *	Returns String value of wfInBalance
	 *	@return wfInBalance
	 */
	public char[]  getWfInBalanceString() {
	    return getCharArray(beginWfInBalance,WF_IN_BALANCE_LEN);
	}

	/**
	 *  This method allows testing if there is a numeric value stored in the serialized String
	 *	@return true if numeric value is stored in the string
	 */
	public boolean wfInBalanceIsNumeric() {
		    return isNumeric(beginWfInBalance
	                    ,beginWfInBalance + WF_IN_BALANCE_LEN
	                    ,true/*Signed*/,true/*isSign trailing*/,false/*isSignStoredSeparately*/);
	}

  
  protected  static final int WF_IN_BALANCE_LEN = 8;
  protected  static final int WF_IN_BALANCE_SCALE = 3;

   protected BigDecimal checkWfInBalanceMaxLimit(BigDecimal number) {
		return checkMaxLimit(number,3/*scale*/,8/*precision*/);
   }

  	/**
	 * serializeWfInBalance
	 */
	protected void serializeWfInBalance(BigDecimal wfInBalance) {
	       putNumber(beginWfInBalance,wfInBalance,WF_IN_BALANCE_LEN,WF_IN_BALANCE_SCALE,true/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/); 
		 localWfInBalanceCounter = shareString.getSerializedField().getModifiedCounter();
   }
  
    /**
	 * serializeWfInBalance
	 */
   	protected  BigDecimal serializeWfInBalance(char[] value) throws CFException {
        if (value.length < 8) value = pad(8, value, ' ', RIGHT_PAD);
        replaceValue(
		       padNumber(8,value,true/*isSigned?*/)
		       ,beginWfInBalance
		       ,8
		      );		 localWfInBalanceCounter = shareString.getSerializedField().getModifiedCounter();
		try {
			return  convertString2Decimal(value,WF_IN_BALANCE_LEN,WF_IN_BALANCE_SCALE,true/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/);
    	} catch(Exception ex) {
    		throw getSoc7ABend("wfInBalance", beginWfInBalance,WF_IN_BALANCE_LEN);
    	}
    }
    /**
	 *	refreshWfInBalance is used to refresh the latest value of a variable from the Serialized String
	 *  the most common reason to serialize an Object as a string in to write to a file. There can be several other reasons for serialization as well
	 */ 
   	public BigDecimal refreshWfInBalance() throws CFException {
   	try {	 
			return (
			         getDecimalNumber(
			             beginWfInBalance
			            ,WF_IN_BALANCE_LEN
			            ,WF_IN_BALANCE_SCALE
			            ,true/*isSigned*/,true/*isSignTrailing*/,false/*isSignStoredSeparate*/)              			                 			        
			        );
	} catch(Exception ex) {
    	throw getSoc7ABend("wfInBalance", beginWfInBalance,WF_IN_BALANCE_LEN);
    }
   	}




}
  
