package com.cloudframe.app.cfcard.dto;

/**
*  The class Work is used to handle fields declared in it
*  @author CloudFrame Inc.
*  created on 2024-10-04 at 13:58. using version 5.0.0.136
**/


import com.cloudframe.app.cfcard.dto.serialize.*;
import com.cloudframe.app.exception.CFException;
import java.math.BigDecimal;
import com.cloudframe.app.data.Field;


public class Work extends WorkSerialized {
   

						private char[] currentCard = new char[16];

								private BigDecimal outTotal = BigDecimal.ZERO;

						private char[] inp1Status = Field.fillLowValue(2);

						private char[] outpStatus = Field.fillLowValue(2);

						private char[] doneStatus = new char[1];

								private long inp1Cnt;

								private long outpCntW;
	
	/**
	* Constructor for Work
	**/
    public Work() {
		super();
		/*  set the parent of each child as this which are a group variable */
	   	/*  end of offset */
								setCurrentCard(("0000000000000000").toCharArray());
								setOutTotal(BigDecimal.ZERO);
								setDoneStatus(("S").toCharArray());
    }


 

	/**
	 *	Returns the value of currentCard
	 *	@return currentCard
	 */
   public char[] getCurrentCard() throws CFException{
   		return currentCard;
   }

  
	/**
	*  set variable currentCard
	*  Corresponding COBOL Variable is WS-CURRENT-CARD
	*  @param value
	**/
   public void setCurrentCard(char[] value) {
       value = checkCurrentCardConstraints(value);
       arraycopy(value,0,currentCard,0,value.length);
   } 
	public void setCurrentCard(char[] value , int beginIndex, int endIndex) {
       //value = checkL1CtlData801Constraints(value);
       arraycopy(value,beginIndex,currentCard,0,beginIndex + endIndex);
   }
	/**
	 *	Returns the value of outTotal
	 *	@return outTotal
	 */
	public BigDecimal getOutTotal() throws CFException {
       if (isOutTotalModified()) { 
           outTotal = refreshOutTotal();
        }
   		return outTotal;
	}
	

	
	   
	/**
	 * 	Update OutTotal with the passed number
	 *  Corresponding COBOL Variable is WS-OUT-TOTAL
	 *	@param number
	 */
	public void setOutTotal(BigDecimal number) {
        number = (number.signum() < 0) ? number.negate():number; // Only positive number allowed  
       outTotal = checkOutTotalMaxLimit(number);
	    serializeOutTotal(outTotal);
   }
	/**
	 * 	Update OutTotal with the passed value
	 *	@param value (String or char[])
	 */
	public void setOutTotal(char[] value) throws CFException {
		 outTotal = serializeOutTotal(value);
	}   
	/**
	 *	Returns the value of inp1Status
	 *	@return inp1Status
	 */
   public char[] getInp1Status() throws CFException{
   		return inp1Status;
   }

  
	/**
	*  set variable inp1Status
	*  Corresponding COBOL Variable is WS-INP1-STATUS
	*  @param value
	**/
   public void setInp1Status(char[] value) {
       value = checkInp1StatusConstraints(value);
       arraycopy(value,0,inp1Status,0,value.length);
   } 
	public void setInp1Status(char[] value , int beginIndex, int endIndex) {
       //value = checkL1CtlData801Constraints(value);
       arraycopy(value,beginIndex,inp1Status,0,beginIndex + endIndex);
   }
	/**
	 *	Returns the value of outpStatus
	 *	@return outpStatus
	 */
   public char[] getOutpStatus() throws CFException{
   		return outpStatus;
   }

  
	/**
	*  set variable outpStatus
	*  Corresponding COBOL Variable is WS-OUTP-STATUS
	*  @param value
	**/
   public void setOutpStatus(char[] value) {
       value = checkOutpStatusConstraints(value);
       arraycopy(value,0,outpStatus,0,value.length);
   } 
	public void setOutpStatus(char[] value , int beginIndex, int endIndex) {
       //value = checkL1CtlData801Constraints(value);
       arraycopy(value,beginIndex,outpStatus,0,beginIndex + endIndex);
   }
	/**
	 *	Returns the value of doneStatus
	 *	@return doneStatus
	 */
   public char[] getDoneStatus() throws CFException{
   		return doneStatus;
   }

  
	/**
	*  set variable doneStatus
	*  Corresponding COBOL Variable is WS-DONE-STATUS
	*  @param value
	**/
   public void setDoneStatus(char[] value) {
       value = checkDoneStatusConstraints(value);
       arraycopy(value,0,doneStatus,0,value.length);
   } 
	public void setDoneStatus(char[] value , int beginIndex, int endIndex) {
       //value = checkL1CtlData801Constraints(value);
       arraycopy(value,beginIndex,doneStatus,0,beginIndex + endIndex);
   }
	char[] processed88Value = "P".toCharArray();
	/**
	 *	Test condition "P" for isProcessed()
	 *	@return  Returns true if isProcessed() is "P"
	 */
   public boolean isProcessed() throws CFException {
      return (  compareChars( getDoneStatus() , processed88Value)  == 0  );
   }


	/**
	*  set values "P"
	*/
   	public void setProcessedTrue() {  			
    	setDoneStatus( processed88Value);
   	}
	char[] inProgress88Value = "S".toCharArray();
	/**
	 *	Test condition "S" for isInProgress()
	 *	@return  Returns true if isInProgress() is "S"
	 */
   public boolean isInProgress() throws CFException {
      return (  compareChars( getDoneStatus() , inProgress88Value)  == 0  );
   }


	/**
	*  set values "S"
	*/
   	public void setInProgressTrue() {  			
    	setDoneStatus( inProgress88Value);
   	}
	/**
	 *	Returns the value of inp1Cnt
	 *	@return inp1Cnt
	 */
	public long getInp1Cnt() throws CFException {
       if (isInp1CntModified()) { 
           inp1Cnt = refreshInp1Cnt();
        }
   		return inp1Cnt;
	}
	

	
	   
	/**
	 * 	Update Inp1Cnt with the passed value
	 *  Corresponding COBOL Variable is WS-INP1-CNT
	 *	@param number
	 */
	public void setInp1Cnt(long number) {
	     // Truncate if the number is beyond +/- Max range	
	    inp1Cnt = checkInp1CntMaxLimit(number); 
		serializeInp1Cnt(inp1Cnt);
	}
	

	/**
	 * 	Update Inp1Cnt with the passed value
	 *	@param value (String or char[])
	 */
	public void setInp1Cnt(char[] value) throws CFException {
		 inp1Cnt = serializeInp1Cnt(value);
	}
	/**
	 * 	Update Inp1Cnt with the passed value 
	 *
	 *	@param value (String or char[])
	 */
	public void setInp1CntString(char[] value) throws CFException {
		 setInp1Cnt(value);
	}
	/**
	 *	Returns the value of outpCntW
	 *	@return outpCntW
	 */
	public long getOutpCntW() throws CFException {
       if (isOutpCntWModified()) { 
           outpCntW = refreshOutpCntW();
        }
   		return outpCntW;
	}
	

	
	   
	/**
	 * 	Update OutpCntW with the passed value
	 *  Corresponding COBOL Variable is WS-OUTP-CNT-W
	 *	@param number
	 */
	public void setOutpCntW(long number) {
	     // Truncate if the number is beyond +/- Max range	
	    outpCntW = checkOutpCntWMaxLimit(number); 
		serializeOutpCntW(outpCntW);
	}
	

	/**
	 * 	Update OutpCntW with the passed value
	 *	@param value (String or char[])
	 */
	public void setOutpCntW(char[] value) throws CFException {
		 outpCntW = serializeOutpCntW(value);
	}
	/**
	 * 	Update OutpCntW with the passed value 
	 *
	 *	@param value (String or char[])
	 */
	public void setOutpCntWString(char[] value) throws CFException {
		 setOutpCntW(value);
	}

	
	
	

		public static int getWorkFieldLength() {
			return WORK_LENGTH;
		}

}
  
