package com.cloudframe.app.cfcard.dto.serialize;

/**
*  The class WorkSerialized is used to define offsets in order to serialize
*  in a fixed String
*  @author CloudFrame Inc.
*  created on 2024-10-04 at 13:58. using version 5.0.0.136
**/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cloudframe.app.data.Field;
import com.cloudframe.app.exception.CFException;
import java.math.BigDecimal;

public class WorkSerialized  extends Field { 

    protected Logger logger = LoggerFactory.getLogger(WorkSerialized.class);
	/*  Length of the field, if serialized as a String */
	protected static final int WORK_LENGTH = 31;
   /*  offset of each of Child Fields when serialized as a String */
            protected  int beginOutTotal;
            protected  int beginInp1Cnt;
            protected  int beginOutpCntW;
	
	/**
	* Constructor for WorkSerialized
	**/
    public WorkSerialized() {
		   			init(0);
    }
 
	/**
	* initializes the field in WorkSerialized
	**/
	@Override
	protected void init(int begin) {
	   setStartOffset(begin);
	   setLength(WORK_LENGTH);
	   /*  set the offset/position of each field when this object is serialized as String */
  
             beginOutTotal = getStartOffset() + 0;	// set offset for serialization
  
  
  
  
             beginInp1Cnt = getStartOffset() + 13;	// set offset for serialization
  
             beginOutpCntW = getStartOffset() + 22;	// set offset for serialization
  
	   /*  end of offset */
	}
     int localCurrentCardCounter = -1;
     public boolean isCurrentCardModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localCurrentCardCounter != sharedCounter;
         localCurrentCardCounter = sharedCounter; return hasModified;
     }

   protected char[] checkCurrentCardConstraints(char[] value) {
   			return super.checkConstraints(value , 16 ,false, false);
   }
     int localOutTotalCounter = -1;
     
     public boolean isOutTotalModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localOutTotalCounter != sharedCounter;
         localOutTotalCounter = sharedCounter; return hasModified; 
     }  	
	
	 /**
	 *	Returns String value of outTotal
	 *	@return outTotal
	 */
	public char[]  getOutTotalString() {
	    return getCharArray(beginOutTotal,OUT_TOTAL_LEN);
	}

	/**
	 *  This method allows testing if there is a numeric value stored in the serialized String
	 *	@return true if numeric value is stored in the string
	 */
	public boolean outTotalIsNumeric() {
		    return isNumeric(beginOutTotal
	                    ,beginOutTotal + OUT_TOTAL_LEN
	                    ,false/*Signed*/,true/*isSign trailing*/,false/*isSignStoredSeparately*/);
	}

  
  protected  static final int OUT_TOTAL_LEN = 13;
  protected  static final int OUT_TOTAL_SCALE = 3;

   protected BigDecimal checkOutTotalMaxLimit(BigDecimal number) {
		return checkMaxLimit(number,3/*scale*/,13/*precision*/);
   }

  	/**
	 * serializeOutTotal
	 */
	protected void serializeOutTotal(BigDecimal outTotal) {
	       putNumber(beginOutTotal,outTotal,OUT_TOTAL_LEN,OUT_TOTAL_SCALE,false/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/); 
		 localOutTotalCounter = shareString.getSerializedField().getModifiedCounter();
   }
  
    /**
	 * serializeOutTotal
	 */
   	protected  BigDecimal serializeOutTotal(char[] value) throws CFException {
        if (value.length < 13) value = pad(13, value, ' ', RIGHT_PAD);
        replaceValue(
		       padNumber(13,value,false/*isSigned?*/)
		       ,beginOutTotal
		       ,13
		      );		 localOutTotalCounter = shareString.getSerializedField().getModifiedCounter();
		try {
			return  convertString2Decimal(value,OUT_TOTAL_LEN,OUT_TOTAL_SCALE,false/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/);
    	} catch(Exception ex) {
    		throw getSoc7ABend("outTotal", beginOutTotal,OUT_TOTAL_LEN);
    	}
    }
    /**
	 *	refreshOutTotal is used to refresh the latest value of a variable from the Serialized String
	 *  the most common reason to serialize an Object as a string in to write to a file. There can be several other reasons for serialization as well
	 */ 
   	public BigDecimal refreshOutTotal() throws CFException {
   	try {	 
			return (
			         getDecimalNumber(
			             beginOutTotal
			            ,OUT_TOTAL_LEN
			            ,OUT_TOTAL_SCALE
			            ,false/*isSigned*/,true/*isSignTrailing*/,false/*isSignStoredSeparate*/)              			                 			        
			        );
	} catch(Exception ex) {
    	throw getSoc7ABend("outTotal", beginOutTotal,OUT_TOTAL_LEN);
    }
   	}
     int localInp1StatusCounter = -1;
     public boolean isInp1StatusModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localInp1StatusCounter != sharedCounter;
         localInp1StatusCounter = sharedCounter; return hasModified;
     }

   protected char[] checkInp1StatusConstraints(char[] value) {
   			return super.checkConstraints(value , 2 ,false, false);
   }
     int localOutpStatusCounter = -1;
     public boolean isOutpStatusModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localOutpStatusCounter != sharedCounter;
         localOutpStatusCounter = sharedCounter; return hasModified;
     }

   protected char[] checkOutpStatusConstraints(char[] value) {
   			return super.checkConstraints(value , 2 ,false, false);
   }
     int localDoneStatusCounter = -1;
     public boolean isDoneStatusModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localDoneStatusCounter != sharedCounter;
         localDoneStatusCounter = sharedCounter; return hasModified;
     }

   protected char[] checkDoneStatusConstraints(char[] value) {
   			return super.checkConstraints(value , 1 ,false, false);
   }
     int localInp1CntCounter = -1;
     public boolean isInp1CntModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localInp1CntCounter != sharedCounter;
         localInp1CntCounter = sharedCounter; return hasModified; 
     }     

	/**
	 *	Returns String value of inp1Cnt
	 *	@return inp1Cnt
	 */
	public char[]  getInp1CntString() {
	     return getCharArray(beginInp1Cnt,INP_1_CNT_LEN);
	}
	
	 /**
	 *  This method allows testing if there is a numeric value stored in the serialized String
	 *	@return true if numeric value is stored in the string
	 */
	public boolean inp1CntIsNumeric() {
	    return isNumeric(beginInp1Cnt
	                    ,beginInp1Cnt + INP_1_CNT_LEN
	                    ,false/*Signed*/,true/*isSign trailing*/,false/*isSignStoredSeparately*/);
	}

  
   protected  static final int INP_1_CNT_LEN = 9;
  	/**
	 * serializeInp1Cnt
	 */
	protected void serializeInp1Cnt(long inp1Cnt) {
		 putNumber(beginInp1Cnt,inp1Cnt,INP_1_CNT_LEN,false/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/); 
		 localInp1CntCounter = shareString.getSerializedField().getModifiedCounter();

    }
    /**
	 * serializeInp1Cnt
	 */
   	protected  long serializeInp1Cnt(char[] value) {
	    long  inp1Cnt;
	    if(value.length >0 && value.length!= 9)
            value = new String(value).trim().toCharArray();
	    if (value.length < 9) value = pad(9, value, ' ', LEFT_PAD);
	    else if (value.length > 9) value = substring(value,0,9);
	    /*  String can consists of digit or a non digit characters, in case of non digit characters, mimic COBOL Behavior and take only the last 4 bits per char and convert that to a number */
	    inp1Cnt = convertString2Number(value,false/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/);
		replaceValue(
		       padNumber(9,value,false/*isSigned?*/)
		       ,beginInp1Cnt
		       ,9
		      );
		 localInp1CntCounter = shareString.getSerializedField().getModifiedCounter();
		return  inp1Cnt;
    }

   protected long checkInp1CntMaxLimit(long number) {

        return checkMaxLimit(number , MAX_1G/*limit*/  , false/*isSigned*/);
   }
    /**
	 *	refreshInp1Cnt is used to refresh the latest value of a variable from the Serialized String
	 *  the most common reason to serialize an Object as a string in to write to a file. There can be several other reasons for serialization as well
	 */ 
   	public long refreshInp1Cnt() throws CFException {
   	try {	 
			return (
			          getLongNumber(
			                  beginInp1Cnt
			                 ,INP_1_CNT_LEN
			                 ,false/*isSigned*/,true/*isSignTrailing*/,false/*isSignStoredSeparate*/)              			                 
			          ); 
	} catch(Exception ex) {
    	throw getSoc7ABend("inp1Cnt", beginInp1Cnt,INP_1_CNT_LEN);
    }
   	}
     int localOutpCntWCounter = -1;
     public boolean isOutpCntWModified() {
         int sharedCounter = shareString.getSerializedField().getModifiedCounter(); 
         boolean hasModified = localOutpCntWCounter != sharedCounter;
         localOutpCntWCounter = sharedCounter; return hasModified; 
     }     

	/**
	 *	Returns String value of outpCntW
	 *	@return outpCntW
	 */
	public char[]  getOutpCntWString() {
	     return getCharArray(beginOutpCntW,OUTP_CNT_W_LEN);
	}
	
	 /**
	 *  This method allows testing if there is a numeric value stored in the serialized String
	 *	@return true if numeric value is stored in the string
	 */
	public boolean outpCntWIsNumeric() {
	    return isNumeric(beginOutpCntW
	                    ,beginOutpCntW + OUTP_CNT_W_LEN
	                    ,false/*Signed*/,true/*isSign trailing*/,false/*isSignStoredSeparately*/);
	}

  
   protected  static final int OUTP_CNT_W_LEN = 9;
  	/**
	 * serializeOutpCntW
	 */
	protected void serializeOutpCntW(long outpCntW) {
		 putNumber(beginOutpCntW,outpCntW,OUTP_CNT_W_LEN,false/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/); 
		 localOutpCntWCounter = shareString.getSerializedField().getModifiedCounter();

    }
    /**
	 * serializeOutpCntW
	 */
   	protected  long serializeOutpCntW(char[] value) {
	    long  outpCntW;
	    if(value.length >0 && value.length!= 9)
            value = new String(value).trim().toCharArray();
	    if (value.length < 9) value = pad(9, value, ' ', LEFT_PAD);
	    else if (value.length > 9) value = substring(value,0,9);
	    /*  String can consists of digit or a non digit characters, in case of non digit characters, mimic COBOL Behavior and take only the last 4 bits per char and convert that to a number */
	    outpCntW = convertString2Number(value,false/*isSigned?*/,true/*signTrailing?*/,false/*storeSignSeparate?*/);
		replaceValue(
		       padNumber(9,value,false/*isSigned?*/)
		       ,beginOutpCntW
		       ,9
		      );
		 localOutpCntWCounter = shareString.getSerializedField().getModifiedCounter();
		return  outpCntW;
    }

   protected long checkOutpCntWMaxLimit(long number) {

        return checkMaxLimit(number , MAX_1G/*limit*/  , false/*isSigned*/);
   }
    /**
	 *	refreshOutpCntW is used to refresh the latest value of a variable from the Serialized String
	 *  the most common reason to serialize an Object as a string in to write to a file. There can be several other reasons for serialization as well
	 */ 
   	public long refreshOutpCntW() throws CFException {
   	try {	 
			return (
			          getLongNumber(
			                  beginOutpCntW
			                 ,OUTP_CNT_W_LEN
			                 ,false/*isSigned*/,true/*isSignTrailing*/,false/*isSignStoredSeparate*/)              			                 
			          ); 
	} catch(Exception ex) {
    	throw getSoc7ABend("outpCntW", beginOutpCntW,OUTP_CNT_W_LEN);
    }
   	}




}
  
