package com.cloudframe.app.cfcard.file.records;

/**
*  The class WfInServiceDate is used to handle fields declared in it
*  @author CloudFrame Inc.
*  created on 2024-10-04 at 13:58. using version 5.0.0.136
**/


import com.cloudframe.app.cfcard.file.records.serialize.*;
import com.cloudframe.app.exception.CFException;
import com.cloudframe.app.data.Field;


public class WfInServiceDate extends WfInServiceDateSerialized { 
   

						private char[] wfInServiceMm = Field.fillLowValue(2);
	
	/**
	* Constructor for WfInServiceDate
	**/
    public WfInServiceDate() {
	// TO-DO auto generated code
    }


 
	/**
	* Constructor for WfInServiceDate. sets the parent value to the parent
	* @param parent
	* @param begin
	**/
    public WfInServiceDate(Field parent,int begin) {
    	   setParent(parent,begin);
    }
    

	/**
	 *	Returns the value of wfInServiceMm
	 *	@return wfInServiceMm
	 */
   public char[] getWfInServiceMm() throws CFException{
     if (isWfInServiceMmModified()) { 
        wfInServiceMm = refreshWfInServiceMm();
     }
   		return wfInServiceMm;
   }

  
	/**
	*  set variable wfInServiceMm
	*  Corresponding COBOL Variable is WF-IN-SERVICE-MM
	*  @param value
	**/
   public void setWfInServiceMm(char[] value) {
      wfInServiceMm = checkWfInServiceMmConstraints(value);
      serializeWfInServiceMm(wfInServiceMm);
   } 

     /**
	 * 	Update WfInServiceMm 
	 *     with a char[] from an offset and length             
	 *	@param value
	 */
   public void setWfInServiceMm(char[] source, int sourceIndex) {
       replace(source,sourceIndex,source.length,beginWfInServiceMm,wfInServiceMm.length);
   	
   }
   
   public void setWfInServiceMm(char[] source, int sourceIndex , int sourceLen) {
       replace(source,sourceIndex,sourceLen,beginWfInServiceMm,wfInServiceMm.length);
   	
   }
   
     /**
	 * 	Update WfInServiceMm 
	 *     with a char[] from an offset and length  
	 *                     to  an offset and length         
	 *	@param value
	 */
   public void setWfInServiceMm(char[] source, int sourceIndex,int sourceLen, int targetIndex,int targetLen) {
       replace(source,sourceIndex,sourceLen,beginWfInServiceMm+targetIndex,targetLen);
   
   }
   
    /**
	 * 	Update WfInServiceMm with another Field
	 *	@param value
	 */
   public void setWfInServiceMm(Field source) {
       replace(source,0,source.length(),beginWfInServiceMm,WF_IN_SERVICE_MM_LEN);
   	
   }  
   
     /**
	 * 	Update WfInServiceMm 
	 *     with another Field from an offset and length          
	 *	@param value
	 */
   public void setWfInServiceMm(Field source, int sourceIndex,int sourceLen) {
        replace(source,sourceIndex,sourceLen,beginWfInServiceMm,WF_IN_SERVICE_MM_LEN);
   	
   }
   
     /**
	 * 	Update WfInServiceMm 
	 *     with another Field from an offset and length  
	 *                         to  an offset and length         
	 *	@param value
	 */
   public void setWfInServiceMm(Field source, int sourceIndex,int sourceLen, int targetIndex,int targetLen) {
       replace(source,sourceIndex,sourceLen,beginWfInServiceMm+targetIndex,targetLen);
    
   }

	
	
	

		public static int getWfInServiceDateFieldLength() {
			return WF_IN_SERVICE_DATE_LENGTH;
		}

}
  
