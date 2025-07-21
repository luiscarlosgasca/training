package com.cloudframe.app.cfcard.file.records;

/**
*  The class WfRecord is used to handle fields declared in it
*  @author CloudFrame Inc.
*  created on 2024-10-04 at 13:58. using version 5.0.0.136
**/


import com.cloudframe.app.cfcard.file.records.serialize.*;
import com.cloudframe.app.exception.CFException;
import com.cloudframe.app.data.Field;
import java.math.BigDecimal;


public class WfRecord extends WfRecordSerialized {
   

						private char[] wfInCardNumber = Field.fillLowValue(16);
				private WfInServiceDate wfInServiceDate = new WfInServiceDate();

								private BigDecimal wfInBalance = BigDecimal.ZERO;
	
	/**
	* Constructor for WfRecord
	**/
    public WfRecord() {
		super();
		/*  set the parent of each child as this which are a group variable */
	       		wfInServiceDate.setParent(this,getStartOffset() + 16);
	   	/*  end of offset */
    }


 

	/**
	 *	Returns the value of wfInCardNumber
	 *	@return wfInCardNumber
	 */
   public char[] getWfInCardNumber() throws CFException{
     if (isWfInCardNumberModified()) { 
        wfInCardNumber = refreshWfInCardNumber();
     }
   		return wfInCardNumber;
   }

  
	/**
	*  set variable wfInCardNumber
	*  Corresponding COBOL Variable is WF-IN-CARD-NUMBER
	*  @param value
	**/
   public void setWfInCardNumber(char[] value) {
      wfInCardNumber = checkWfInCardNumberConstraints(value);
      serializeWfInCardNumber(wfInCardNumber);
   } 

     /**
	 * 	Update WfInCardNumber 
	 *     with a char[] from an offset and length             
	 *	@param value
	 */
   public void setWfInCardNumber(char[] source, int sourceIndex) {
       replace(source,sourceIndex,source.length,beginWfInCardNumber,wfInCardNumber.length);
   	
   }
   
   public void setWfInCardNumber(char[] source, int sourceIndex , int sourceLen) {
       replace(source,sourceIndex,sourceLen,beginWfInCardNumber,wfInCardNumber.length);
   	
   }
   
     /**
	 * 	Update WfInCardNumber 
	 *     with a char[] from an offset and length  
	 *                     to  an offset and length         
	 *	@param value
	 */
   public void setWfInCardNumber(char[] source, int sourceIndex,int sourceLen, int targetIndex,int targetLen) {
       replace(source,sourceIndex,sourceLen,beginWfInCardNumber+targetIndex,targetLen);
   
   }
   
    /**
	 * 	Update WfInCardNumber with another Field
	 *	@param value
	 */
   public void setWfInCardNumber(Field source) {
       replace(source,0,source.length(),beginWfInCardNumber,WF_IN_CARD_NUMBER_LEN);
   	
   }  
   
     /**
	 * 	Update WfInCardNumber 
	 *     with another Field from an offset and length          
	 *	@param value
	 */
   public void setWfInCardNumber(Field source, int sourceIndex,int sourceLen) {
        replace(source,sourceIndex,sourceLen,beginWfInCardNumber,WF_IN_CARD_NUMBER_LEN);
   	
   }
   
     /**
	 * 	Update WfInCardNumber 
	 *     with another Field from an offset and length  
	 *                         to  an offset and length         
	 *	@param value
	 */
   public void setWfInCardNumber(Field source, int sourceIndex,int sourceLen, int targetIndex,int targetLen) {
       replace(source,sourceIndex,sourceLen,beginWfInCardNumber+targetIndex,targetLen);
    
   }
	/**
	 *	Returns the value of wfInServiceDate
	 *	@return wfInServiceDate
	 */   
	 public WfInServiceDate getWfInServiceDate() {
   	return wfInServiceDate;
   }
   /**
	* 	Update WfInServiceDate with the passed value
	*   Corresponding COBOL Variable is WF-IN-SERVICE-DATE
	*	@param value
	*/
   public void setWfInServiceDate(char[] value) {
      wfInServiceDate.setString(value); 
   }   
    
     /**
	 * 	Update WfInServiceDate 
	 *     with a String from an offset and length             
	 *	@param value
	 */
   public void setWfInServiceDate(char[] source, int sourceIndex,int sourceLen) {
   	replace(source,sourceIndex,sourceLen,wfInServiceDate.begin,wfInServiceDate.length());
   }
   
     /**
	 * 	Update WfInServiceDate 
	 *     with a String from an offset and length  
	 *                     to  an offset and length         
	 *	@param value
	 */
   public void setWfInServiceDate(char[] source, int sourceIndex,int sourceLen, int targetIndex,int targetLen) {
   	replace(source,sourceIndex,sourceLen,wfInServiceDate.begin+targetIndex,targetLen);
   }
   
    /**
	 * 	Update WfInServiceDate with another Field
	 *	@param value
	 */
   public void setWfInServiceDate(Field source) {
   	replace(source,0,source.length(),wfInServiceDate.begin,wfInServiceDate.length());
   }  
   
     /**
	 * 	Update WfInServiceDate 
	 *     with another Field from an offset and length             
	 *	@param value
	 */
   public void setWfInServiceDate(Field source, int sourceIndex,int sourceLen) {
   	replace(source,sourceIndex,sourceLen,wfInServiceDate.begin,wfInServiceDate.length());
   }
   
     /**
	 * 	Update WfInServiceDate 
	 *     with another Field from an offset and length  
	 *                         to  an offset and length         
	 *	@param value
	 */
   public void setWfInServiceDate(Field source, int sourceIndex,int sourceLen, int targetIndex,int targetLen) {
   	replace(source,sourceIndex,sourceLen,wfInServiceDate.begin+targetIndex,targetLen);
   }
	/**
	 *	Returns the value of wfInBalance
	 *	@return wfInBalance
	 */
	public BigDecimal getWfInBalance() throws CFException {
       if (isWfInBalanceModified()) { 
           wfInBalance = refreshWfInBalance();
        }
   		return wfInBalance;
	}
	

    /**
	 *	Returns the String value of wfInBalance
	 *	@return wfInBalance
	 */
	public char[]  getWfInBalanceActualString() {
	    String value = String.valueOf(wfInBalance).trim();
		if(value.startsWith("+") || value.startsWith("-")) {
			value = value.substring(1);
		}
		return value.toCharArray();	
	}
	
	   
	/**
	 * 	Update WfInBalance with the passed number
	 *  Corresponding COBOL Variable is WF-IN-BALANCE
	 *	@param number
	 */
	public void setWfInBalance(BigDecimal number) {
       wfInBalance = checkWfInBalanceMaxLimit(number);
	    serializeWfInBalance(wfInBalance);
   }
	/**
	 * 	Update WfInBalance with the passed value
	 *	@param value (String or char[])
	 */
	public void setWfInBalance(char[] value) throws CFException {
		 wfInBalance = serializeWfInBalance(value);
	}   

	
	
	

		public static int getWfRecordFieldLength() {
			return WF_RECORD_LENGTH;
		}

}
  
