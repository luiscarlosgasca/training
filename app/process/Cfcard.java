  package com.cloudframe.app.process;
  /* 
*---------------------------------------------
* cfcard  - aggregate balance for credit card
*           to output file
*--------------------------------------------
*/
  
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.beans.factory.annotation.Qualifier;
  import com.cloudframe.app.cfcard.file.*;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  import com.cloudframe.app.exception.CFException;
  import org.springframework.stereotype.Component;
  import org.springframework.web.bind.annotation.RestController;
  import org.springframework.web.bind.annotation.RequestParam;
  import com.cloudframe.app.cfcard.file.records.WfInServiceDate;
  import java.math.BigDecimal;
  import java.math.RoundingMode;
  import com.cloudframe.app.data.Field;
  import com.cloudframe.app.exception.Terminate;
  import com.cloudframe.app.cfcard.dto.*;
  import com.cloudframe.app.cfcard.dto.Parm;
  import com.cloudframe.app.cfcard.file.records.WfOutput;
  import com.cloudframe.app.cfcard.file.records.WfRecord;
  import com.cloudframe.app.cfcard.dto.Work;
  import com.cloudframe.app.common.CONSTANTS;
  import com.cloudframe.app.utility.CFUtil;
  
  @Component("cfcard")
  
  public class Cfcard extends CommonProcess {
  
  Logger logger = LoggerFactory.getLogger(Cfcard.class);
  
  private Parm parm = new Parm() ;
  private WfOutput wfOutput = new WfOutput() ;
  private WfRecord wfRecord = new WfRecord() ;
  private Work work = new Work() ;
  
  
  
  @Autowired 
  @Qualifier("cfcard_flInputFile")
  FlInputFile flInputFile;
  @Autowired 
  @Qualifier("cfcard_flOutputFile")
  FlOutputFile flOutputFile;
  
  
  
  
  
  
      public int setParameter(String parm) throws Exception {
      		if(parm != null)
      		    this.parm.setString(com.cloudframe.app.data.Field.getParm(parm),new String(CONSTANTS.EBCDIC_ENCODING));
      		setInitDone(false);
      		process();
      		return getRc();
      }
      /**
      * process 
      * Input  : None 

      * Output : None 

      * @throws CFException
      */
      @Override
      public int process() throws Exception {
       initVars();
       try {
       setCodePage("1047");
          mainline();/*0000-MAINLINE*/
          if (this.isProgramEnded()) {
              return getRc();
          }
       } catch(Exception e) {
            handleErrorCode(e);
            throw e;
       }
        finally {
      		if(flInputFile.hasOpened() && !flInputFile.isReadOnly()) { 
      			flInputFile.flush(); 
      		}
      		if(flOutputFile.hasOpened() && !flOutputFile.isReadOnly()) { 
      			flOutputFile.flush(); 
      		}
      }
      
       return getRc(); // Exit with return code
      // end of process method
      }
      /**
      * mainline 
      *   This method is derived from 
  *   COBOL Paragraph - 0000-MAINLINE COBOL Cyclomatic complexity - 2
      * Input  :  

      * - parmMonth                      COBOL Name: LK-PARM-MONTH
      * - doneStatus                     COBOL Name: WS-DONE-STATUS
      *
      * Output : None 

      * @throws CFException
      */
      private void mainline() throws Exception {

// *

// *
          logger.info("SERVICE MONTH {}", String.valueOf(parm.getParmMonthString())); 
          initialize();/*0100-INITIALIZE*/
          if (this.isProgramEnded()) {
              return ;
          }

// *
          while (!(work.isProcessed()) ) {
             processRecord();/*0200-PROCESS-RECORD*/
             if (this.isProgramEnded()) {
                 return ;
             }
          }

// *
          terminate();/*0300-TERMINATE*/
          if (this.isProgramEnded()) {
              return ;
          }
      
      }
      /**
      * initialize 
      *   This method is derived from 
  *   COBOL Paragraph - 0100-INITIALIZE COBOL Cyclomatic complexity - 3
      * Input  : None 

      * Output :  

      * - inp1Status                     COBOL Name: WS-INP1-STATUS
      * - outpStatus                     COBOL Name: WS-OUTP-STATUS
      * - inp1Cnt                        COBOL Name: WS-INP1-CNT
      * - outpCntW                       COBOL Name: WS-OUTP-CNT-W
      *
      * @throws CFException
      */
      private void initialize() throws Exception {
			// Declare local variables used in the method
			char[] inp1Status = null;
			char[] outpStatus = null;
			// End of variable declaration

      
// *

// *
          flInputFile.open(new String(CONSTANTS.MODE_READ_ONLY_36242),flInputFile.getFileName(),flInputFile.getFlInputFileCharSet(),flInputFile.getFlInputFileCrlfFlag());
          work.setInp1Status(flInputFile.getStatusString() );
          inp1Status = work.getInp1Status();
//  LITERAL_00 = '00'
          if ((		compareChars(inp1Status,CONSTANTS.LITERAL_00) != 0 )) { 
              logger.info("OPEN INP1-FILE ERROR {}", new String(work.getInp1Status())); 
              abend();/*9100-ABEND*/
              if (this.isProgramEnded()) {
                  return ;
              }
          }
  
          flOutputFile.open(new String(CONSTANTS.MODE_WRITE_ONLY_36397),flOutputFile.getFileName(),flOutputFile.getFlOutputFileCharSet(),flOutputFile.getFlOutputFileCrlfFlag());
          work.setOutpStatus(flOutputFile.getStatusString() );
          outpStatus = work.getOutpStatus();
//  LITERAL_00 = '00'
          if ((		compareChars(outpStatus,CONSTANTS.LITERAL_00) != 0 )) { 
              logger.info("OPEN OUTP-FILE ERROR {}", new String(work.getOutpStatus())); 
              abend();/*9100-ABEND*/
              if (this.isProgramEnded()) {
                  return ;
              }
          }
  
          work.setInp1Cnt((long)0);
          work.setOutpCntW((long)0);
          ;
      
      }
      /**
      * processRecord 
      *   This method is derived from 
  *   COBOL Paragraph - 0200-PROCESS-RECORD COBOL Cyclomatic complexity - 8
      * Input  :  

      * - inp1Cnt                        COBOL Name: WS-INP1-CNT
      * - parmMonth                      COBOL Name: LK-PARM-MONTH
      * - currentCard                    COBOL Name: WS-CURRENT-CARD
      *
      * Output :  

      * - wfRecord                       COBOL Name: WF-RECORD
      * - inp1Status                     COBOL Name: WS-INP1-STATUS
      * - inp1Cnt                        COBOL Name: WS-INP1-CNT
      * - wfInServiceMm                  COBOL Name: WF-IN-SERVICE-MM
      * - doneStatus                     COBOL Name: WS-DONE-STATUS
      * - wfInCardNumber                 COBOL Name: WF-IN-CARD-NUMBER
      * - currentCard                    COBOL Name: WS-CURRENT-CARD
      * - outTotal                       COBOL Name: WS-OUT-TOTAL
      * - wfInBalance                    COBOL Name: WF-IN-BALANCE
      *
      * @throws CFException
      */
      private void processRecord() throws Exception {
			// Declare local variables used in the method
			char[] inp1Status = null;
			char[] wfInServiceMm = null;
			char[] currentCard = null;
			char[] wfInCardNumber = null;
			WfInServiceDate wfInServiceDate = wfRecord.getWfInServiceDate();
			int parmMonth = 0;
			BigDecimal tempDecimal = BigDecimal.ZERO;
			// End of variable declaration

      
// *

// *

// *
          flInputFile.read();
          work.setInp1Status(flInputFile.getStatusString());
          if (!flInputFile.hasEnded()) {
              wfRecord.setString(flInputFile.getRecord());
          }
          inp1Status = work.getInp1Status();
//  LITERAL_10 = '10'
          if ((		compareChars(inp1Status,CONSTANTS.LITERAL_00) != 0  && 		compareChars(inp1Status,CONSTANTS.LITERAL_10) != 0 )) { 
              logger.info("READ  INP1-FILE ERROR {}{}", new String(work.getInp1Status()), wfRecord.toString()); 
              abend();/*9100-ABEND*/
              if (this.isProgramEnded()) {
                  return ;
              }
          }
  

// *
          work.setInp1Cnt(work.getInp1Cnt()+(long)1);
          parmMonth = parm.getParmMonth();
          inp1Status = work.getInp1Status();
          wfInServiceMm = wfInServiceDate.getWfInServiceMm();
          if (		compareChars(inp1Status,CONSTANTS.LITERAL_10) == 0  || compareChars(wfInServiceMm,String.valueOf(parm.getParmMonthString()).toCharArray()) > 0) { 
              work.setProcessedTrue(); 
              
//cobolCode::GO TO 0200-EXIT
return ;
//cobolCodeEnds::GO TO 0200-EXIT
          }
  

// *
// * If the month on the file is lower than requested skip
// *
          parmMonth = parm.getParmMonth();
          wfInServiceMm = wfInServiceDate.getWfInServiceMm();
          if (compareChars(wfInServiceMm,String.valueOf(parm.getParmMonthString()).toCharArray()) < 0) { 
//cobolCode::GO TO 0200-EXIT
return ;
//cobolCodeEnds::GO TO 0200-EXIT
          }
  

// *
          currentCard = work.getCurrentCard();
          if (( allZeros(currentCard) ) /*  ==  zeros*/) { 
              work.setCurrentCard(wfRecord.getWfInCardNumber());
              work.setOutTotal(BigDecimal.ZERO);
          }
  

// *
          wfInCardNumber = wfRecord.getWfInCardNumber();
          currentCard = work.getCurrentCard();
          if (		compareChars(currentCard,wfInCardNumber) == 0 ) { 
              tempDecimal = work.getOutTotal().add(wfRecord.getWfInBalance()).setScale(3,RoundingMode.DOWN);
              work.setOutTotal(tempDecimal);
              //
          }
  
          else { 

// *
              writeFile();/*0250-WRITE-FILE*/
              if (this.isProgramEnded()) {
                  return ;
              }

// *
              
// *
              work.setCurrentCard(wfRecord.getWfInCardNumber());
              work.setOutTotal(wfRecord.getWfInBalance());
          }
      
      }
      /**
      * writeFile 
      *   This method is derived from 
  *   COBOL Paragraph - 0250-WRITE-FILE COBOL Cyclomatic complexity - 3
      * Input  :  

      * - parmMonth                      COBOL Name: LK-PARM-MONTH
      * - currentCard                    COBOL Name: WS-CURRENT-CARD
      * - outTotal                       COBOL Name: WS-OUT-TOTAL
      * - wfOutput                       COBOL Name: WF-OUTPUT
      * - outpCntW                       COBOL Name: WS-OUTP-CNT-W
      *
      * Output :  

      * - wfOutServiceMm                 COBOL Name: WF-OUT-SERVICE-MM
      * - parmMonth                      COBOL Name: LK-PARM-MONTH
      * - wfOutCardNumber                COBOL Name: WF-OUT-CARD-NUMBER
      * - currentCard                    COBOL Name: WS-CURRENT-CARD
      * - wfOutTotal                     COBOL Name: WF-OUT-TOTAL
      * - outTotal                       COBOL Name: WS-OUT-TOTAL
      * - outpStatus                     COBOL Name: WS-OUTP-STATUS
      * - outpCntW                       COBOL Name: WS-OUTP-CNT-W
      *
      * @throws CFException
      */
      private void writeFile() throws Exception {
			// Declare local variables used in the method
			char[] outpStatus = null;
			// End of variable declaration

      
// *

// *
          wfOutput.setWfOutServiceMm(parm.getParmMonth());
          wfOutput.setWfOutCardNumber(work.getCurrentCard());
          wfOutput.setWfOutTotal(work.getOutTotal());

// *

// *
          flOutputFile.write(wfOutput.toCharArray()); 
          wfOutput.setString(CONSTANTS.LOW_VALUE_1253883881);
          work.setOutpStatus(flOutputFile.getStatusString() );
          outpStatus = work.getOutpStatus();
//  LITERAL_22 = '22'
          if ((		compareChars(outpStatus,CONSTANTS.LITERAL_00) != 0  && 		compareChars(outpStatus,CONSTANTS.LITERAL_22) != 0 )) { 
              logger.info("WRITE OUTP-FILE ERROR {}{}", new String(work.getOutpStatus()), wfOutput.toString()); 
              abend();/*9100-ABEND*/
              if (this.isProgramEnded()) {
                  return ;
              }
          }
  

// *
          work.setOutpCntW(work.getOutpCntW()+(long)1);
      
      }
      /**
      * terminate 
      *   This method is derived from 
  *   COBOL Paragraph - 0300-TERMINATE COBOL Cyclomatic complexity - 6
      * Input  :  

      * - currentCard                    COBOL Name: WS-CURRENT-CARD
      * - inp1Status                     COBOL Name: WS-INP1-STATUS
      * - outpStatus                     COBOL Name: WS-OUTP-STATUS
      * - outpCntW                       COBOL Name: WS-OUTP-CNT-W
      *
      * Output : None 

      * @throws CFException
      */
      private void terminate() throws Exception {
			// Declare local variables used in the method
			char[] currentCard = null;
			char[] inp1Status = null;
			char[] outpStatus = null;
			// End of variable declaration

      
// *

// *

// *        When input file had no records then skip
          currentCard = work.getCurrentCard();
          if (        ( !allSpaces(currentCard)  ) && !( checkLowValue(currentCard) ) ) { 

// *        Write the last city record
              writeFile();/*0250-WRITE-FILE*/
              if (this.isProgramEnded()) {
                  return ;
              }
          }
  

// *
          flInputFile.close(); 
          work.setInp1Status(flInputFile.getStatusString() );
          inp1Status = work.getInp1Status();
//  LITERAL_00 = '00'
          if ((		compareChars(inp1Status,CONSTANTS.LITERAL_00) != 0 )) { 
              logger.info("CLOSE INP1-FILE ERROR {}", new String(work.getInp1Status())); 
              abend();/*9100-ABEND*/
              if (this.isProgramEnded()) {
                  return ;
              }
          }
  
          flOutputFile.close(); 
          work.setOutpStatus(flOutputFile.getStatusString() );
          outpStatus = work.getOutpStatus();
//  LITERAL_00 = '00'
          if ((		compareChars(outpStatus,CONSTANTS.LITERAL_00) != 0 )) { 
              logger.info("CLOSE OUTP-FILE ERROR {}", new String(work.getOutpStatus())); 
              abend();/*9100-ABEND*/
              if (this.isProgramEnded()) {
                  return ;
              }
          }
  
          logger.info("RECORDS WRITTEN    {}", String.valueOf(work.getOutpCntWString())); 
          logger.info("PROGRAM CFCITY  ENDED SUCCESSFULLY"); 
          setNotLogged(false); // no need to log, it is a normal termination
          this.setProgramEnded(true);
          return ;
      
      }
      /**
      * abend 
      *   This method is derived from 
  *   COBOL Paragraph - 9100-ABEND COBOL Cyclomatic complexity - 2
      * Input  : None 

      * Output :  

      * - rc                             COBOL Name: RETURN-CODE
      *
      * @throws CFException
      */
      private void abend() throws Exception {
          logger.info("PROGRAM CFCARD  ENDED UNSUCCESSFULLY"); 
          this.setRc( 12);
          setNotLogged(false); // no need to log, it is a normal termination
          this.setProgramEnded(true);
          return ;
      
      }
  
  
      /**
* This method pre-initializes variables consistent with
* what a mainframe program would have done at the start of a program
*/
      @Override
      public void initVars() throws CFException {
      setProgramEnded(false);
          if(!isInitDone()) {
          	this.setRc(0);
          	setInitDone(true);
          }
        CFUtil.resetDecimalAsComma();
       }
  
      public int call(Object[] params) throws Exception {
      
      int len = params.length;
         if (len > 0 && params[0] != null )
            parm.set((Field)params[0]);
         // invoke the process and return rc
         return process();
         
      }
      
      public int call(Field... parameters) throws Exception {
         for (int index = 0; index < parameters.length;index++) {
             switch(index) {
              case 0:
                      if(parameters[index] != null ) {
              		if (parameters[index] instanceof Parm) {
                       	this.parm = ((Parm) parameters[index]);
                  	} else {
                       	this.parm.set(parameters[index]);
                  	}
                  }
                
                  break;
            }
         }
          return process();
      }
      
      
  
  
  
  
  
  }
