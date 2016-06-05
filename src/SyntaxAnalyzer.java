import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author maniar
 */
public class SyntaxAnalyzer {
    
    
    ArrayList<Token> parseToken;
    int index = 0;
       // int I = parseToken.size() ;
        public SyntaxAnalyzer(ArrayList tokens){
        this.parseToken = tokens;
    }
    
    public void getSyntax(){
        
        print("<Start> -> ");
        if ("class".equals(parseToken.get(index).classPart)){
           printandInc("class ");
         
            if (("id").equals(parseToken.get(index).classPart)){
                printandInc("id ");
               
                if (("[").equals(parseToken.get(index).classPart)){
                    printandInc("[");
                
                    callMain();
                    
                    if (("]").equals(parseToken.get(index).classPart)){
                        printandInc("]");
                        print("cfg worked");

                        
                    }else{
                        print("\n Missing ] of class at Line#"+parseToken.get(index).lineNumber);
                        
                    }
                }else{
                    print("\n Expecting [ after class id at Line#"+ parseToken.get(index).lineNumber);
                }
            }else {
                print("\n Expecting ID after class at Line#"+parseToken.get(index).lineNumber);
            }
        } else {
            print("\n Expecting Class in starting at Line#"+parseToken.get(index).lineNumber);
        }
    }
    
    private boolean callMain(){
        
        
        print("<Main> ->");
        
        if ("main".equals(parseToken.get(index).classPart)){
            printandInc("main ");
           
            if (("id").equals(parseToken.get(index).classPart)){
                printandInc("id ");

                if ((":").equals(parseToken.get(index).classPart)){
                        printandInc(":");
                     
                    if (("void").equals(parseToken.get(index).classPart)){
                            printandInc("void");
                  

                        if (("[").equals(parseToken.get(index).classPart)){
                            printandInc("[");
                           
                           if (multiStatement()){
                             //  printandInc(" ");
                            }else{
                           
                            }
                            if (("]").equals(parseToken.get(index).classPart)){
                                printandInc("]");
                              
                                return true;
                            }else{
                                print("\n Missing ] of main Line#"+parseToken.get(index).lineNumber);
                            }
                        } else {
                              print("\n Expecting [ at Line#"+parseToken.get(index).lineNumber);   
                        }
                    }else{
                         print("\n Expecting Void as Main's Data Type at Line#"+parseToken.get(index).lineNumber);
                    }
                }else{
                    print("\n Expecting : at Line#"+parseToken.get(index).lineNumber);         
                }
            }else {
                 print("\n Expecting ID after main at Line#"+parseToken.get(index).lineNumber);
            }
        } else {
             print("\n Expecting main at Line#"+parseToken.get(index).lineNumber);
        }
        return false;
    }
    private boolean multiStatement(){
    
        print("<m_st>");
        
        if (singleStatement()){
            if (multiStatement()){
                if (parseToken.get(index).classPart.equals("]")){
                return true;
                }
            }
//            printandInc("");
             /*   if (multiStatement()){
                        print("<s_st>");
                        return true;
                }*/
            
        }else if (parseToken.get(index).classPart.equals("]")){
                return true;
        }
        return false;
    }
    private boolean singleStatement(){
       
  
//    print("ssss"+parseToken.get(index).classPart+"llll");
//
//    if (parseToken.get(index).classPart == "obj"){
//    
//        object();
//   
//    }
    switch (parseToken.get(index).classPart){

   /* case "id":
        {   /*recheck*/
          /*  if (incdec()){
                return true;
            }
            else{*/
             /*      if (object()){
                        return true;
                    }else{
                        if (declaration())
                        {
                            return true;
                        }
                    }
                */
          /*  }
            break;
        }*/
    
    case "obj":
    {   if (object())
        {
            return true;
        }
        break;
    }
    case "function":
    {   /*recheck*/
            if (function()){
                return true;
            }
            break;
    }    
    case "incdec":
        {   /*recheck*/
            if (incdec()){
                return true;
            }
            break;
        }
        
    case "DataType":
    {
            if (data_Type()){
                return true;
            }
//            else if (intDeclaration()){
//                    return true;
//            }
            break;
    }
    case "if":
    {
        if (checkElse())
        {
            return true;
        }
        break;
    }
    case "DataStructure":
    {   print("array block");
        if (array())
        {
            return true;
        }
        break;
    }
    case "for":
    {
            if (floop())
            {
                return true;
            }
            break;
    }
    case "feloop":
    {
            if (fe_loop())
            {
                return true;
            }
            break;
    }
    case "while":
    {
            if (wloop())
            {
                return true;
            }
            break;
    }
    case "Do":
    {       //no token
            if (Do_wloop())
            {
                return true;
            }
            break;
    }
   /* case "switch":
    {
            if (switchs())
            {
                return true;
            }
            break;
    }*/
    default:
    {
        print("\n\n in default");
                print(parseToken.get(index).classPart);

        return false;
    }

    }
    return false;
}
   
    
    private boolean dataType(){
        if (parseToken.get(index).classPart.equals("void")){
            printandInc("void");
           
            return true;
        }else if (parseToken.get(index).classPart.equals("DataType")){
            printandInc("DataType");
            
            return true;
        }else{
             print("Expecting Data Type or Void at Line#"+parseToken.get(index).lineNumber);
             return false;
        }
    }
    
    private boolean Do_wloop(){
          if (parseToken.get(index).classPart.equals("Do")){
                        printandInc("Do");
                         if(parseToken.get(index).classPart.equals("[")){
                            printandInc("[");
                                if (multiStatement()){
                                            print("<funcbody>");
                                     }else{
                           
                                     }
                                  if(parseToken.get(index).classPart.equals("]")){
                                         printandInc("]");
                                         
                                          if(parseToken.get(index).classPart.equals("while")){
                                                 printandInc("wloop");
                                                 
                                                     if(parseToken.get(index).classPart.equals("(")){
                                                          printandInc("(");
                                                          
                                                          if (cond()){
                                                          
                                                              if(parseToken.get(index).classPart.equals(")")){
                                                                     printandInc(")");
                                                                   return  true;
                                                              
                                                              }else{
                                                              print(") not found");
                                                              }
                                                              
                                                          }else{
                                                              print("condition not true");
                                                          }
                                                     
                                                     }else{
                                                     
                                                     print("( not found");
                                                     }
                                                 
                                          
                                          }else{
                                          print("wloop not found");
                                          }
                                         
                                  }else{
                                  print("] not found");
                                  }
                                
                         }else{
                         print("[ not found");
                         }
          }else{
          print("Do not found");
          }
    return false;
    }
    
    private boolean fe_loop(){
         if (parseToken.get(index).classPart.equals("feloop")){
                        printandInc("feloop");
                        
                      if (parseToken.get(index).classPart.equals("id")){
                        printandInc("id");
                        
                          if (parseToken.get(index).classPart.equals("in")){
                             printandInc("in");
                             if (parseToken.get(index).classPart.equals("id")){
                                     printandInc("id");
                                     if (parseToken.get(index).classPart.equals("[")){
                                              printandInc("[");
                                              if (multiStatement()){
                                            print("<funcbody>");
                                     }else{
                           
                                     }
                                              if (parseToken.get(index).classPart.equals("]")){
                                              printandInc("]");
                                              
                                              return true;
                                               }else{
                                               print("syntax error");
                                               }
                                     
                                     
                                     }else{
                                     
                                     print("syntax error");
                                     }
                                        
                             
                             }else{
                             print("syntax error");
                             }
                          
                           }else{
                        print("syntax error");
                          }
                        
                      }else{
                      print("syntax error");
                      }  
         }else{
             print("syntax error");
         }
         
    return false;
    }
    
    private boolean object(){
     
        if (parseToken.get(index).classPart.equals("obj")){
             printandInc("obj");
               if (parseToken.get(index).classPart.equals("id")){
                    printandInc("id");
                      if (parseToken.get(index).classPart.equals("Assign")){
                         printandInc("=");
                          if (parseToken.get(index).classPart.equals("id")){
                            printandInc("id");
                             if (parseToken.get(index).classPart.equals("(")){
                                printandInc("(");
                                    if (parseToken.get(index).classPart.equals(")")){
                                          printandInc(")");
                                          return true;
                                    }else{
                                         print(") not found");
                                    }
                          
                             }else{
                                 print("( not found");
                             }
                            
                          }else{
                          
                           print("class id not found");
                          }
                         
                      }else{
                       print("= not found");
                      }
      
               }else{
                    print(" id not found");
               }
                        
                        
     }else{
      print("obj keyword not find not found");
     }
        
        
    return false;
    }
    





    private boolean wloop(){
                if (parseToken.get(index).classPart.equals("while")){
                        printandInc("<wloop>");
                        if(parseToken.get(index).classPart.equals("(")){
                            printandInc("(");
                            if((cond())){
                            
                                   if(parseToken.get(index).classPart.equals(")")){ 
                                       printandInc(")");
                                       
                                            if(parseToken.get(index).classPart.equals("[")){
                                                printandInc("[");
                                                if (multiStatement()){
                                                        print("<loopbody>");
                                                 }else{
                           
                                                }
                                                
                                                if(parseToken.get(index).classPart.equals("]")){
                                                printandInc("]");
                                                    return true;
                                                }else{
                                                     print("] not found");
                                                     return false;
                                                }
                                            
                                            } else{
                                                print("[ not found");
                                                return false;
                                            }
                                       
                                   
                                   }else{
                                       print(") not found");
                                       return false;
                                   }
                            
                            
                            }else{
                                print("condition not found");
                            return false;
                            }
                                
                            
                            
                        
                        }else{
                            
                            print("( not found");
                            return false;
                        }
                
                }else{
                    print("wloop not found");
                    return false;
                }
    
    
    }
    
    private boolean floop(){
        
       if (parseToken.get(index).classPart.equals("for")){
           printandInc("<floop>");
           
            if (parseToken.get(index).classPart.equals("(")){
               printandInc("(");
               
               if (init()){
                     print("<init>");
                    if (cond()){
                     print("<cond>");
                        if(incdec()){
                             print("<inc-dec>");
                             if (parseToken.get(index).classPart.equals(")")){
                                 
                                    printandInc(")");
                                    if (parseToken.get(index).classPart.equals("[")){
                                         printandInc("[");
                                  
                                      if (multiStatement()){
                                            print("<floopbody>");
                                     }else{
                           
                                     }
                                  if (parseToken.get(index).classPart.equals("]")){
                                      printandInc("]");
                                      return true;
                                  }
                                  //return true;
                                }
                             }else{
                                 print("Expecting ] at Line#"+parseToken.get(index).lineNumber);
                             }
                         }else{
                             print("not Incremented or decremented correctly at Line#"+parseToken.get(index).lineNumber);
                         }
                    }else{
                         print("Data Type not found at Line#"+parseToken.get(index).lineNumber);
                    }
                }else{
                    print(": not found at Line#"+parseToken.get(index).lineNumber);
                }
            }else{
                 print("id not found at Line#"+parseToken.get(index).lineNumber);
            }
        }else{
            return false;
        }
        return false;    
    }
    
private boolean init(){
          if (parseToken.get(index).classPart.equals("DataType")){
           printandInc("dt");
           
            if (parseToken.get(index).classPart.equals("id")){
               printandInc("id");
               
               if (parseToken.get(index).classPart.equals("Assign")){
                     printandInc("=");
                           if (parseToken.get(index).classPart.equals("IntegerConstant") ||
                               parseToken.get(index).classPart.equals("FloatConstant")    
                             ){
                            printandInc("Int_const");
                                return true;
                           
                                /* if(moreInit()){
                                    
                                }else{
                             print("Array not declared correctly at Line#"+parseToken.get(index).lineNumber);
                           }*/
                       }else{
                           print("not an int const at Line#"+parseToken.get(index).lineNumber);
                       }
                }else{
                   print("= not found at Line#"+parseToken.get(index).lineNumber);
                }
            }else{
                   print("id not found at Line#"+parseToken.get(index).lineNumber);
             }
        }else{
          print("( not found at Line#"+parseToken.get(index).lineNumber);

            return false;
        }
        return false;
    }
    private boolean incdec(){
          if (parseToken.get(index).classPart.equals("id")){
           printandInc("id");
           
            if (parseToken.get(index).classPart.equals("Inc-Dec")){
               printandInc("++");
               return true;
            } else{
                print("id must be increased or decreased"+parseToken.get(index).lineNumber);
            }
          }
        return false;
    }
   
    /* private boolean moreInit(){
        if (parseToken.get(index).classPart.equals(";")){
            printandInc(";");
            if (init()){
                
        }else{
        
        }

    }*/
    
    private boolean checkElse(){
        if (parseToken.get(index).classPart.equals("if")){
           printandInc("\n<check>");
            if(cond()){
                if (("[").equals(parseToken.get(index).classPart)){
                    printandInc("[");
                    /* Logics */
                    if (("]").equals(parseToken.get(index).classPart)){
                        printandInc("]");
                        return true;
                    }else if (("else").equals(parseToken.get(index).classPart)){
                        printandInc("else");
                        if (("[").equals(parseToken.get(index).classPart)){
                            printandInc("[");
                                if (multiStatement()){
                                      print("<checkbody>");
                                     }else{
                           
                                    }
                                  
                            if (("]").equals(parseToken.get(index).classPart)){
                                printandInc("]");
                                 return true;
                            }else{
                                print("Expecting ] at Line#"+parseToken.get(index).lineNumber); 
                                index--;   
                            }
                        }else{
                            print("Expecting [ at Line#"+parseToken.get(index).lineNumber);
                            index--;
                        } 
                    }
                    else{
                        print("Expecting ] at Line#"+parseToken.get(index).lineNumber);
                        index--;
                    }
                }else{
                     print("Expecting [ at Line#"+parseToken.get(index).lineNumber);
                     index--;
                }
            }else{
                 print("Expecting Condition at Line#"+parseToken.get(index).lineNumber);
                 index--;
            }
        }else {
           
        }
        return false;
    }
private boolean array(){
          if (parseToken.get(index).classPart.equals("DataStructure")){
           printandInc("Array");
           
            if (parseToken.get(index).classPart.equals("id")){
               printandInc("id");
               
               if (parseToken.get(index).classPart.equals(":")){
                     printandInc(":");
                           if (parseToken.get(index).classPart.equals("DataType")){
                           printandInc("DT");
                            
                            print(" options");   
                            if(options()){
                               
                                return true;
                           }else{
                             print("Array not declared correctly at Line#"+parseToken.get(index).lineNumber);
                           }
                       }else{
                           print("Data Type not found at Line#"+parseToken.get(index).lineNumber);
                       }
                }else{
                   print(": not found at Line#"+parseToken.get(index).lineNumber);
                }
            }else{
                   print("id not found at Line#"+parseToken.get(index).lineNumber);
             }
        }else{
            
            return false;
        }
        return false;
    
    }
private boolean function(){
        
        if (parseToken.get(index).classPart.equals("function")){
            printandInc("func");
           
            if (parseToken.get(index).classPart.equals("id")){
               printandInc("id");
               
               if (parseToken.get(index).classPart.equals("(")){
                     printandInc("(");
                     
                     print("<parm>");      
                     if (parameter()){
                            
                     }                     
                    if (parseToken.get(index).classPart.equals(")")){
                        printandInc(")");
                        if (parseToken.get(index).classPart.equals(":")){
                            printandInc(":");
               
                            if (dataType()){
                                
                                print("DT");
                              if (parseToken.get(index).classPart.equals("[")){
                                  printandInc("[");
                                  
                                  if (multiStatement()){
                                      print("<funcbody>");
                                     }else{
                           
                                    }
                                  if (parseToken.get(index).classPart.equals("]")){
                                      printandInc("]");
                                      return true;
                                  }else{
                                    print("] statement incorrect at Line#"+parseToken.get(index).lineNumber);
                                  }
                              }else{
                                    print("[ statement incorrect at Line#"+parseToken.get(index).lineNumber);
                              }
                                
                             }else{
                                print("return statement incorrect at Line#"+parseToken.get(index).lineNumber);
                            }
                        }else{
                            print(": not found at Line#"+parseToken.get(index).lineNumber);
                        }
                    }else{
                        print(") not found at Line#"+parseToken.get(index).lineNumber);
                    }
                }else{
                   print(") not found at Line#"+parseToken.get(index).lineNumber);
                }
             }else{
                return false;
             }
        }
        return false;
        
    }
    private boolean parameter(){
        if (parseToken.get(index).classPart.equals("DataType")){
           printandInc("dt");
           
            if (parseToken.get(index).classPart.equals("id")){
               printandInc("id");
               if(more()){
                   return true;
               }
            } else{
                print("id not found"+parseToken.get(index).lineNumber);
            }
          }
        return false;
    
    }
    private boolean more(){
        if (parseToken.get(index).classPart.equals(";")){
           printandInc(";");
           
           if (parameter()){
               
           } else if (parseToken.get(index).classPart.equals(")")){
               
           }
           else{
                print("id must be increased or decreased"+parseToken.get(index).lineNumber);
            }
          }
        return false;
    
    }
    private boolean options(){
        if (parseToken.get(index).classPart.equals("[")){
            printandInc("[");
            if (value()){
                printandInc("array intialize");
               
            }
            if (parseToken.get(index).classPart.equals("]")){
                printandInc("]");
                return true;
            } else{
                print("] not found at Line#"+parseToken.get(index).lineNumber);
            }
        }else{
           print("[ not found at Line#"+parseToken.get(index).lineNumber);
        }
        return false;
    }
    
    private boolean value(){
        if (parseToken.get(index).classPart.equals("IntegerConstant") || 
                parseToken.get(index).classPart.equals("String-Constant") ||
                parseToken.get(index).classPart.equals("FloatConstant") ||
                parseToken.get(index).classPart.equals("CharConstant"))
        { 
            print("value");
            return true;
        } else{
            return false;
        }
    }
    private boolean cond(){
        if (parseToken.get(index).classPart.equals("id")){
           printandInc("id");
           
            if (parseToken.get(index).classPart.equals("Rell-Operator")){
               printandInc("Rell-Operator");
               
               if (parseToken.get(index).classPart.equals("id")){
                     printandInc("id");
                     
                     return true;
                }else{
                   print("ID not found");
                }
            }else{
                   print("Relation Operator not found");
             }
        }else{
            print("ID not found");

        }
        return false;
    }
    
    private boolean data_Type(){
           if (parseToken.get(index).classPart.equals("DataType")){
                 printandInc("DataType");
                      if (parseToken.get(index).classPart.equals("id")){
                           printandInc("id");
                           
                           if (initialize()){
                               print("initialize");
                               
                             if(line_break()){
                                 print(";");
                                 return true;
                             
                             }else{
                             
                             }
                             
                             
                           
                           }else{
                           
                           }
                      
                      }else{
                      
                      }
           
           
           }else{
           
           }
    
    return false;
    }
    
    private boolean initialize(){
        
         if (parseToken.get(index).classPart.equals("Assign")){
                           printandInc("=");
                           
                           if(value()){
                                printandInc("");
                           return true;
                           
                           }else{
                           
                           }
         }else{
             if (parseToken.get(index).classPart.equals(";")){
                 
                    return true;
                 
             }else{
             
             }
         
         }
    
        return false;
    }
 
    private boolean line_break(){
    
         if (parseToken.get(index).classPart.equals(";")){
                printandInc(";");
                return true;
                           
         }else{
         
         }
        return false;
    }
    
    private void printandInc(String value){
        index++;
        System.out.print(value);
        
    }
    private void print(String value){
        System.out.print(value);
    }
}
