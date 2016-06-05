import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.*;



public class Re {
      private Pattern pat;
      private String classPart;
      private ArrayList<Re> matching = new ArrayList();
     // public Array[] a = new Array[5];
   
      
      // generate ALL RE's
      public Re(){
      //Class
         matching.add(new Re((Pattern.compile("class")),"class") );
          
         matching.add(new Re((Pattern.compile("main")),"main") );
          
          // Data Types
         matching.add(new Re((Pattern.compile("norm|deci|alpha|word|bool")),"DataType") ); 
         matching.add(new Re((Pattern.compile("void")),"void") ); 
          
          //Relation operator
         matching.add(new Re((Pattern.compile("<=|>=|==|<|>|!=")),"Rell-Operator") );
         
         //Airthmetic operator
         matching.add(new Re((Pattern.compile("\\+|\\-")),"Add-Sub") );
         matching.add(new Re((Pattern.compile("/")),"Divide") );
         matching.add(new Re((Pattern.compile("\\*")),"Multiply") );
           
         //Assignment Operator 
         matching.add(new Re((Pattern.compile("=")),"Assign") );
         matching.add(new Re((Pattern.compile("=|\\+=|\\-=|/=|\\*=|=\\+|=\\-|=/|=\\*")),"Assign-Operator") );
            
         matching.add(new Re((Pattern.compile("array")),"DataStructure") ); 
           
         //Increament/Decreament
          matching.add(new Re((Pattern.compile("\\+\\+|\\-\\-")),"Inc-Dec") );
            
          //Condition
          matching.add(new Re((Pattern.compile("check")),"if") );
           matching.add(new Re((Pattern.compile("else")),"else") );
         // matching.add(new Re((Pattern.compile("check-else")),"if-else") );
          matching.add(new Re((Pattern.compile("catch")),"switch") );
          matching.add(new Re((Pattern.compile("that")),"that") );
          matching.add(new Re((Pattern.compile("func")),"function") );
          matching.add(new Re((Pattern.compile("Do")),"Do") );
          matching.add(new Re((Pattern.compile("feloop")),"feloop") );
          matching.add(new Re((Pattern.compile("in")),"in") );
          matching.add(new Re((Pattern.compile("obj")),"obj") );


          
          //loop
          matching.add(new Re((Pattern.compile("floop")),"for") );
          matching.add(new Re((Pattern.compile("wloop")),"while") );
          //matching.add(new Re((Pattern.compile("wloop")),"wloop") );
          matching.add(new Re((Pattern.compile("feloop")),"for-in") );
          
          matching.add(new Re((Pattern.compile("break")),"break") );
           
          //constant 
          matching.add(new Re((Pattern.compile("[0-9]+")),"IntegerConstant") );
          matching.add(new Re((Pattern.compile("'[a-z]*'")),"String-Constant") );
          matching.add(new Re((Pattern.compile("\"[a-z]\"")),"CharConstant") );  
          
          //Id
          matching.add(new Re((Pattern.compile("[a-z]+[[a-z]*[0-9]*]*")),"id") );
          
          matching.add(new Re((Pattern.compile("[0-9]+.[0-9]*|[0-9]*.[0-9]+")),"FloatConstant") );

          
          //Error
          // matching.add(new Re((Pattern.compile("''[a-z]*\\n")),"Char-Constant-Error") );
          matching.add(new Re((Pattern.compile("!|@|#|\\$|\\^|\\&|\\?|_")),"Symbol-error") );
          matching.add(new Re((Pattern.compile("'[a-z]*")),"String-Constant-Error") );
          matching.add(new Re((Pattern.compile("\"[a-z]")),"Char-Constant-Error") ); 
     //     matching.add(new Re((Pattern.compile("[a-z]+.[0-9]*")),"FloatConstant") );
          //constant

         //coment 
//           matching.add(new Re((Pattern.compile("_!")),"MulComent") );
//           matching.add(new Re((Pattern.compile("_![.+|\\n]*")),"MulComent") );
//           matching.add(new Re((Pattern.compile("_![.+|\\n]*!")),"mulComent") );
//           matching.add(new Re((Pattern.compile("_![.+|\\n]*!_")),"mulComent") );
           
         // puncuator
         matching.add(new Re((Pattern.compile("\\:")),":") );
         matching.add(new Re((Pattern.compile("\\{")),"{") );
         matching.add(new Re((Pattern.compile("\\}")),"}") );
         matching.add(new Re((Pattern.compile("\\;")),";") );
         matching.add(new Re((Pattern.compile("\\,")),",") );
         matching.add(new Re((Pattern.compile("\\(")),"(") );
         matching.add(new Re((Pattern.compile("\\)")),")") );
         matching.add(new Re((Pattern.compile("\\[")),"[") );
         matching.add(new Re((Pattern.compile("\\]")),"]") );
         matching.add(new Re((Pattern.compile(" ")),"space") );
         matching.add(new Re((Pattern.compile(".")),".") );
         matching.add(new Re((Pattern.compile("\\n")),"\n") );
      }
      
      
      
      
        public Re(Pattern ptr,String clsPart){
  
            pat = ptr;
            classPart = clsPart;
        }
       
             
     
      // function that returns class part acording to string
        public  String getClassPart(String value){
            
            // create RE object
             Re reObject = new Re();
             
             // compare upComing string to all RE's
              for(int i = 0 ; i<reObject.matching.size();i++){
               
                  // if string compare to any RE then return class part
                  if((((reObject.matching).get(i)).pat.matcher(value)).matches()){
                  
                         return ((reObject.matching).get(i)).classPart;
              
                     }
              }
              
            // if all RE's are not compare to our string  
            return "no";
      
         }//end getClassPart function
}