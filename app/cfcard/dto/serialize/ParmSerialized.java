package com.cloudframe.app.cfcard.dto.serialize;

/**
*  The class ParmSerialized is used to define offsets in order to serialize
*  in a fixed String
*  @author CloudFrame Inc.
*  created on 2024-10-04 at 13:58. using version 5.0.0.136
**/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cloudframe.app.data.Field;
import com.cloudframe.app.exception.CFException;

public class ParmSerialized  extends Field { 

    protected Logger logger = LoggerFactory.getLogger(ParmSerialized.class);
	/*  Length of the field, if serialized as a String */
	protected static final int PARM_LENGTH = 4;
   /*  offset of each of Child Fields when serialized as a String */
            protected  int beginParmLen;
            protected  int beginParmMonth;
	
	/**
	* Constructor for ParmSerialized
	**/
    public ParmSerialized() {
		   			init(0);
    }
 
	/**
	* initializes the field in ParmSerialized
	**/
	@Override
	protected void init(int begin) {
	   setStartOffset(begin);
	   setLength(PARM_LENGTH);
	   /*  set the offset/position of each field when this object is serialized as String */
             beginParmLen = getStartOffset() + 0;	// set offset for serialization
  
             beginParmMonth = getStartOffset() + 2;	// set offset for serialization
  
	   /*  end of offset */
	}
         int localParmLenCounter = -1;
         public boolean isParmLenModified() {
            int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
            boolean hasModified = localParmLenCounter != sharedCounter;
            localParmLenCounter = sharedCounter; return hasModified; 
         }
   protected static final int PARM_LEN_LEN = 2;
  	/**
	 * serializeParmLen
	 */
	protected void serializeParmLen(short parmLen) {
           replaceValue( //  save the value as string
                   getBinaryString( parmLen,PARM_LEN_LEN)
                  ,beginParmLen
                  ,PARM_LEN_LEN
                 );
            localParmLenCounter = shareString.getSerializedField().getModifiedCounter();
                 
   }
       
      
   protected short checkParmLenMaxLimit(long number) {
	   return (short)checkMaxLimit(number, true/*isSigned*/,2/*dataLen*/);
   }
     /**
	 *	refreshParmLen is used to refresh the latest value of a variable from the Serialized String
	 *  the most common reason to serialize an Object as a string in to write to a file. There can be several other reasons for serialization as well
	 */ 
   	public short refreshParmLen() {	 
			return (getShort(beginParmLen));
   	}
     int localParmMonthCounter = -1;
     public boolean isParmMonthModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localParmMonthCounter != sharedCounter;
         localParmMonthCounter = sharedCounter; return hasModified; 
     }     

	/**
	 *	Returns String value of parmMonth
	 *	@return parmMonth
	 */
	public char[]  getParmMonthString() {
	     return getCharArray(beginParmMonth,PARM_MONTH_LEN);
	}
	
	 /**
	 *  This method allows testing if there is a numeric value stored in the serialized String
	 *	@return true if numeric value is stored in the string
	 */
	public boolean parmMonthIsNumeric() {
	    return isNumeric(beginParmMonth
	                    ,beginParmMonth + PARM_MONTH_LEN
	                    ,false/*Signed*/,true/*isSign trailing*/,false/*isSignStoredSeparately*/);
	}

  
   protected  static final int PARM_MONTH_LEN = 2;
  	/**
	 * serializeParmMonth
	 */
	protected void serializeParmMonth(int parmMonth) {
		 putNumber(beginParmMonth,parmMonth,PARM_MONTH_LEN,false/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/); 
		 localParmMonthCounter = shareString.getSerializedField().getModifiedCounter();

    }
    /**
	 * serializeParmMonth
	 */
   	protected  int serializeParmMonth(char[] value) {
	    int  parmMonth;
	    if(value.length >0 && value.length!= 2)
            value = new String(value).trim().toCharArray();
	    if (value.length < 2) value = pad(2, value, ' ', LEFT_PAD);
	    else if (value.length > 2) value = substring(value,0,2);
	    /*  String can consists of digit or a non digit characters, in case of non digit characters, mimic COBOL Behavior and take only the last 4 bits per char and convert that to a number */
	    parmMonth = (int) convertString2Number(value,false/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/);
		replaceValue(
		       padNumber(2,value,false/*isSigned?*/)
		       ,beginParmMonth
		       ,2
		      );
		 localParmMonthCounter = shareString.getSerializedField().getModifiedCounter();
		return  parmMonth;
    }

   protected int checkParmMonthMaxLimit(long number) {

	   return (int)checkMaxLimit(number , MAX_100/*limit*/  , false/*isSigned*/);
   }
    /**
	 *	refreshParmMonth is used to refresh the latest value of a variable from the Serialized String
	 *  the most common reason to serialize an Object as a string in to write to a file. There can be several other reasons for serialization as well
	 */ 
   	public int refreshParmMonth() throws CFException {
   	try {	 
			return (
			          getIntNumber(
			                  beginParmMonth
			                 ,PARM_MONTH_LEN
			                 ,false/*isSigned*/,true/*isSignTrailing*/,false/*isSignStoredSeparate*/)              			                 
			          ); 
	} catch(Exception ex) {
    	throw getSoc7ABend("parmMonth", beginParmMonth,PARM_MONTH_LEN);
    }
   	}




}
  
