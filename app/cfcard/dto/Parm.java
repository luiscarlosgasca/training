package com.cloudframe.app.cfcard.dto;

/**
*  The class Parm is used to handle fields declared in it
*  @author CloudFrame Inc.
*  created on 2024-10-04 at 13:58. using version 5.0.0.136
**/


import com.cloudframe.app.cfcard.dto.serialize.*;
import com.cloudframe.app.exception.CFException;


public class Parm extends ParmSerialized {
   

								private short parmLen;

								private int parmMonth;
	
	/**
	* Constructor for Parm
	**/
    public Parm() {
		super();
		/*  set the parent of each child as this which are a group variable */
	   	/*  end of offset */
    }


 

	/**
	 *	Returns the value of parmLen
	 *	@return parmLen
	 */
	public short getParmLen() throws CFException {
        if (isParmLenModified()) { 
           parmLen = refreshParmLen();
        }
   		return parmLen;
	}
	
	/**
	 * 	Update ParmLen with the passed value
	 *  Corresponding COBOL Variable is LK-PARM-LEN
	 *	@param number
	 */
	public void setParmLen(short number) {
	     // Truncate if the number is beyond +/- Max range
	    parmLen = checkParmLenMaxLimit(number); 
		serializeParmLen(parmLen);
	}

	public void setParmLen(int number) {
	    number = checkParmLenMaxLimit((short)number); // Truncate if value is beyond +/- Max range
		setParmLen((short)number);
	}
	public void setParmLen(long number) {
	    number = checkParmLenMaxLimit((short)number); // Truncate if value is beyond +/- Max range
		setParmLen((short)number);
	}
	

	/**
	 *	Returns the value of parmMonth
	 *	@return parmMonth
	 */
	public int getParmMonth() throws CFException {
       if (isParmMonthModified()) { 
           parmMonth = refreshParmMonth();
        }
   		return parmMonth;
	}
	

	
	   
	/**
	 * 	Update ParmMonth with the passed value
	 *  Corresponding COBOL Variable is LK-PARM-MONTH
	 *	@param number
	 */
	public void setParmMonth(int number) {
	     // Truncate if the number is beyond +/- Max range	
	    parmMonth = checkParmMonthMaxLimit(number); 
		serializeParmMonth(parmMonth);
	}
	

	public void setParmMonth(long number) {
	    number = checkParmMonthMaxLimit(number); // Truncate if value is beyond +/- Max range
		setParmMonth((int)number);
	}
	
	/**
	 * 	Update ParmMonth with the passed value
	 *	@param value (String or char[])
	 */
	public void setParmMonth(char[] value) throws CFException {
		 parmMonth = serializeParmMonth(value);
	}
	/**
	 * 	Update ParmMonth with the passed value 
	 *
	 *	@param value (String or char[])
	 */
	public void setParmMonthString(char[] value) throws CFException {
		 setParmMonth(value);
	}

	
	
	

		public static int getParmFieldLength() {
			return PARM_LENGTH;
		}

}
  
