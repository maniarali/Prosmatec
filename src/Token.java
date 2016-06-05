
import java.util.ArrayList;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muhammad Salman
 */


public class Token {
   
    public String classPart;
    public String valuePart;
    public int lineNumber;
    
    private String lastValidToken = "invalid";
    private String temp = "";
    String value = "";
  
    ArrayList<Token> tokens = new ArrayList();
    
    
    public Token(){
        classPart = "";
        valuePart = "";
        lineNumber = 1;
                
    }
    
    public ArrayList<Token> getToken(String tempText){
    
        // convert string to arry
         char[] text = tempText.toCharArray();
      
         // make sure that line no is 1 at start
         int line = 1;
        
         // initialize Re class object
         Re objectRe = new Re();
         
         // chek all array and genrate token in this loop
         for(int i=0;i<text.length;i++){
             
             // initialize token object to store class,value,linenumber in one token object
             Token token = new Token();
             
                 // for  Single line coment 
                 if(text[i]=='%'){
              
                        // ignore memberz untill  member is \n
                         while(text[i]!='\n'){
                             
                              i++;
                             // System.out.println(i);
                                 if(i==(text.length-1)){
                                         // if member is last member of array
                                          i++;
                                          break;
                                      }
                          }
                        
                         // when 1 or more then member can exist in array
                         if(i<(text.length-1)){
                              i++;
                         }
                         
                          // increase line number 
                          line++;
                    }
              

        
            // make sure that  member must exist in array then genrate token of this members        
             if(i<=(text.length-1)){

                 // append one by one array member to temp string
                 temp += text[i];
                 
                 // pass this temp to RE class and get class part acording to temp string
                 String fromRe = objectRe.getClassPart(temp);
        
                     // if class part of member is \n(line change) then line++  
                      if(fromRe == "\n"){
                     
                              line++;
                              
                              // temp convert to empty to read next member as a new string
                              temp = "";
                      }
                    
                 
                      // if class part of string is 'n0' thats mean our token member is exist at i-- position in array 
                      if (fromRe=="no"){
                       
                          // store token data bcz as we know that token exist here  
                          token.classPart =lastValidToken;
                          token.valuePart = value;
                          token.lineNumber = line;
                          
                          // generate all token instead of space token
                          if(token.classPart != "space"){
                          
                              tokens.add(token);
                          }
                          // to read member as a new string
                          temp ="";
                          
                          // again read member at i--
                          i--;
        
                       // update lastValidToken  
                        }else{
                           
                          lastValidToken = fromRe;
                          value = temp;
                          token.lineNumber = line;
           
                            // if member is last member of array 
                            if((i+1)==text.length){
                                   //generate token
                                  token.classPart =lastValidToken;
                                  token.valuePart = value;
                               
                                  // generate all type token except space token and line change token
                                  if(token.classPart != "space" && token.classPart != "\n"){
                                      tokens.add(token);
                                      }  
                               }
                    }// end check class part condition
                
             }// end main if condtion
             
         
          }// end first for loop
         
                         
         /*changed*/
         // return tokens
         return tokens;

    }// end function
    public void printToken(){
        // print all token
         for(int i = 0 ; i<tokens.size();i++){
           
             System.out.println("("+(tokens.get(i)).classPart+","+(tokens.get(i)).valuePart+ ","+tokens.get(i).lineNumber+")");
         
         }
         
    }
}// end class
