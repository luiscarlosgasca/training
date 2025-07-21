package com.cloudframe.app.cfcard.file.records;

/**
*  The class WfOutput is used to handle fields declared in it
*  @author CloudFrame Inc.
*  created on 2024-10-04 at 13:58. using version 5.0.0.136
**/


import com.cloudframe.app.cfcard.file.records.serialize.*;
import com.cloudframe.app.exception.CFException;
import com.cloudframe.app.data.Field;
import java.math.BigDecimal;


public class WfOutput extends WfOutputSerialized {
   

						private char[] wfOutCardNumber = Field.fillLowValue(16);

								private int wfOutServiceMm;

								private BigDecimal wfOutTotal = BigDecimal.ZERO;
	
	/**
	* Constructor for WfOutput
	**/
    public WfOutput() {
		super();
		/*  set the parent of each child as this which are a group variable */
	   	/*  end of offset */
    }


 

	/**
	 *	Returns the value of wfOutCardNumber
	 *	@return wfOutCardNumber
	 */
   public char[] getWfOutCardNumber() throws CFException{
     if (isWfOutCardNumberModified()) { 
        wfOutCardNumber = refreshWfOutCardNumber();
     }
   		return wfOutCardNumber;
   }

  
	/**
	*  set variable wfOutCardNumber
	*  Corresponding COBOL Variable is WF-OUT-CARD-NUMBER
	*  @param value
	**/
   public void setWfOutCardNumber(char[] value) {
      wfOutCardNumber = checkWfOutCardNumberConstraints(value);
      serializeWfOutCardNumber(wfOutCardNumber);
   } 

     /**
	 * 	Update WfOutCardNumber 
	 *     with a char[] from an offset and length             
	 *	@param value
	 */
   public void setWfOutCardNumber(char[] source, int sourceIndex) {
       replace(source,sourceIndex,source.length,beginWfOutCardNumber,wfOutCardNumber.length);
   	
   }
   
   public void setWfOutCardNumber(char[] source, int sourceIndex , int sourceLen) {
       replace(source,sourceIndex,sourceLen,beginWfOutCardNumber,wfOutCardNumber.length);
   	
   }
   
     /**
	 * 	Update WfOutCardNumber 
	 *     with a char[] from an offset and length  
	 *                     to  an offset and length         
	 *	@param value
	 */
   public void setWfOutCardNumber(char[] source, int sourceIndex,int sourceLen, int targetIndex,int targetLen) {
       replace(source,sourceIndex,sourceLen,beginWfOutCardNumber+targetIndex,targetLen);
   
   }
   
    /**
	 * 	Update WfOutCardNumber with another Field
	 *	@param value
	 */
   public void setWfOutCardNumber(Field source) {
       replace(source,0,source.length(),beginWfOutCardNumber,WF_OUT_CARD_NUMBER_LEN);
   	
   }  
   
     /**
	 * 	Update WfOutCardNumber 
	 *     with another Field from an offset and length          
	 *	@param value
	 */
   public void setWfOutCardNumber(Field source, int sourceIndex,int sourceLen) {
        replace(source,sourceIndex,sourceLen,beginWfOutCardNumber,WF_OUT_CARD_NUMBER_LEN);
   	
   }
   
     /**
	 * 	Update WfOutCardNumber 
	 *     with another Field from an offset and length  
	 *                         to  an offset and length         
	 *	@param value
	 */
   public void setWfOutCardNumber(Field source, int sourceIndex,int sourceLen, int targetIndex,int targetLen) {
       replace(source,sourceIndex,sourceLen,beginWfOutCardNumber+targetIndex,targetLen);
    
   }
	/**
	 *	Returns the value of wfOutServiceMm
	 *	@return wfOutServiceMm
	 */
	public int getWfOutServiceMm() throws CFException {
       if (isWfOutServiceMmModified()) { 
           wfOutServiceMm = refreshWfOutServiceMm();
        }
   		return wfOutServiceMm;
	}
	

	
	   
	/**
	 * 	Update WfOutServiceMm with the passed value
	 *  Corresponding COBOL Variable is WF-OUT-SERVICE-MM
	 *	@param number
	 */
	public void setWfOutServiceMm(int number) {
	     // Truncate if the number is beyond +/- Max range	
	    wfOutServiceMm = checkWfOutServiceMmMaxLimit(number); 
		serializeWfOutServiceMm(wfOutServiceMm);
	}
	

	public void setWfOutServiceMm(long number) {
	    number = checkWfOutServiceMmMaxLimit(number); // Truncate if value is beyond +/- Max range
		setWfOutServiceMm((int)number);
	}
	
	/**
	 * 	Update WfOutServiceMm with the passed value
	 *	@param value (String or char[])
	 */
	public void setWfOutServiceMm(char[] value) throws CFException {
		 wfOutServiceMm = serializeWfOutServiceMm(value);
	}
	/**
	 * 	Update WfOutServiceMm with the passed value 
	 *
	 *	@param value (String or char[])
	 */
	public void setWfOutServiceMmString(char[] value) throws CFException {
		 setWfOutServiceMm(value);
	}
	/**
	 *	Returns the value of wfOutTotal
	 *	@return wfOutTotal
	 */
	public BigDecimal getWfOutTotal() throws CFException {
       if (isWfOutTotalModified()) { 
           wfOutTotal = refreshWfOutTotal();
        }
   		return wfOutTotal;
	}
	

	
	   
	/**
	 * 	Update WfOutTotal with the passed number
	 *  Corresponding COBOL Variable is WF-OUT-TOTAL
	 *	@param number
	 */
	public void setWfOutTotal(BigDecimal number) {
        number = (number.signum() < 0) ? number.negate():number; // Only positive number allowed  
       wfOutTotal = checkWfOutTotalMaxLimit(number);
	    serializeWfOutTotal(wfOutTotal);
   }
	/**
	 * 	Update WfOutTotal with the passed value
	 *	@param value (String or char[])
	 */
	public void setWfOutTotal(char[] value) throws CFException {
		 wfOutTotal = serializeWfOutTotal(value);
	}   

	
	
	

		public static int getWfOutputFieldLength() {
			return WF_OUTPUT_LENGTH;
		}

}
  
