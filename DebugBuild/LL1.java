/*
* LL1.java
*
* Author: Paul von Ebers
*
* IT327 Assignment 1
*/
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LL1
{

  protected static LinkedList<Character> input = new LinkedList<>();

  //
  protected static char token;


  //  The end result
  protected static String valid = "Yes.";


  //  Used for debugging
  protected static boolean debugFlag = false;
  protected static int debugSize = 0;
  protected static int debugRule = 0;
  protected static LinkedList<String> debugMethods = new LinkedList<>();


  public static void main(String[] args)
  {
  	System.out.println();
    //  Prep input
    String in;
    if(args.length > 0)
      in = args[0];
    else
      in = "";

    input = formatInput(in);


    /* Prepare Debugging if Flag is Set */
    /**/ debugSize = input.size();
    /**/ if((args.length > 1) && (args[1].equals("-debug"))) { debugFlag = true; debugTable(in); }
    /**/ debugMethods.push("E ");
    /**/

    //  Get the first character
    token = input.remove();


    //  Start with E
    EA();

    if(debugFlag) { System.out.println("====================================================================================\n");}

    System.out.println(valid);
  }

  /**
  * NTS Method: E
  */
  static void EA()
  {

    if(debugFlag) { debugData(); }
    debugMethods.pop();

    switch(token)
    {
      case 'n': //  Rule 1
        debugRule = 1; debugMethods.push("E'"); debugMethods.push("T ");
        TA();
        EB();
        break;

      case '(': //  Rule 1
        debugRule = 1; debugMethods.push("E'"); debugMethods.push("T ");
        TA();
        EB();
        break;

      default:
        debugRule = -1;
        valid = "No.";
        break;
    }
  }

  /**
  * NTS Method: E'
  */
  static void EB()
  {

    if(debugFlag) { debugData(); }
    debugMethods.pop();

    switch(token)
    {
      case '+': //  Rule 2
        debugRule = 2; debugMethods.push("E'");debugMethods.push("T ");
        get();
        TA();
        EB();
        break;

      case '-': //  Rule 3
        debugRule = 3;debugMethods.push("E'");debugMethods.push("T ");
        get();
        TA();
        EB();
        break;

      case ')': //  Rule 4
        debugRule = 4;
        break;

      case '$': //  Rule 4
        debugRule = 4;
        break;

      default:
        debugRule = -1;
        valid = "No.";
        break;
    }
  }

  /**
  * NTS Method: T
  */
  static void TA()
  {

    if(debugFlag) { debugData(); }

    debugMethods.pop();
    switch(token)
    {
      case 'n': //  Rule 5
        debugRule = 5; debugMethods.push("T "); debugMethods.push("F ");
        FA();
        TB();
        break;

      case '(': //  Rule 5
        debugRule = 5; debugMethods.push("T'"); debugMethods.push("F ");
        FA();
        TB();
        break;

      default:
        debugRule = -1;
        valid = "No.";
        break;
    }
  }

  /**
  * NTS Method: T'
  */
  static void TB()
  {

    if(debugFlag) { debugData(); }

    debugMethods.pop();
    switch(token)
    {
      case '+': //  Rule 8
        debugRule = 8;
        break;

      case '-': //  Rule 8
        debugRule = 8;
        break;

      case '*': //  Rule 6
        debugRule = 6; debugMethods.push("T'"); debugMethods.push("F ");
        get();
        FA();
        TB();
        break;

      case '/': //  Rule 7

        debugRule = 7; debugMethods.push("T'"); debugMethods.push("F ");
        get();
        FA();
        TB();
        break;

      case ')': //  Rule 8
        debugRule = 8;
        break;

      case '$': //  Rule 8
        debugRule = 8;
        break;

      default:
        debugRule = -1;
        valid = "No.";
        break;

    }
  }

  /**
  * NTS Method: F
  */
  static void FA()
  {

    if(debugFlag) { debugData(); }

    debugMethods.pop();
    switch(token)
    {
      case 'n': //  Rule 10
        debugRule = 10;
        get();
        break;

      case '(': //  Rule 9
        debugRule = 9;debugMethods.push("E ");

        get();
        EA();
        get();
        break;

      default:
        debugRule = -1;
        valid = "No.";
        break;
    }

  }


  /**
  * Assigns token char to the front of the input Queue, then removes it.
  */
  static void get()
  {
    if(!input.isEmpty())
      token = input.remove();
  }


  /**
  * Formats the input to simplify the rest of the program
  * @param in, the input from command line arguments
  * @return result, a LinkedList
  */
  static LinkedList<Character> formatInput(String in)
  {
    LinkedList<Character> result = new LinkedList<>();
    for(int i = 0; i < in.length(); i++)
    {
      if(Character.isDigit(in.charAt(i)))
      {
        if(result.size() > 0 && result.get(result.size()-1) != 'n')
          result.offer('n');
        else if(result.size() == 0)
          result.offer('n');
        }
      else
        result.offer(in.charAt(i));
    }
    result.offer('$');
    return result;
  }



/**************************************/
/*            Debug  Stuff            */
/**************************************/
  static void debugTable(String dIn)
  {
    System.out.print("Test Case: " + dIn + "\tFormatted: ");
    printInput();
    System.out.println("\n");

    System.out.println("====================================================================================");
    System.out.print("||Token  ||Rule   ||Input Remaining");
    for(int i = 0; i < 15; i++) { System.out.print(" "); }
    System.out.println("||Non-Terminal Methods          ||");
    System.out.print("||=======||");
    System.out.print("=======||");
    System.out.print("==============================||");
    System.out.print("==============================||");
    System.out.println();
  }

  static void debugData()
  {
    System.out.print("||   " + token + "   ||  ");
    if(debugRule < 10) { System.out.print(" ");}
    System.out.print(debugRule + "   ||  ");
    printInput();
    System.out.print("||  ");
    printDebug();
    System.out.print("||");
    System.out.println();
  }

  static void printInput()
  {
    for(char i : input)
    {
      System.out.print(i);
    }
    for(int i = 0; i < 28 - input.size(); i++)
    {
      System.out.print(" ");
    }
  }

  static void printDebug()
  {
    for(String i : debugMethods)
    {
      System.out.print(i + " ");
    }
    for(int i = 0; i < 28 - (3*debugMethods.size()); i++)
    {
      System.out.print(" ");
    }
  }

}
