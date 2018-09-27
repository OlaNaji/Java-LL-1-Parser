/*
* LL1.java
*
* Author: Paul von Ebers
*
* IT327 Assignment 1
*/
import java.util.LinkedList;

public class LL1
{

  //  The tokens to parse from the input
  protected static LinkedList<Character> input = new LinkedList<>();

  // The current token being parsed
  protected static char token;


  //  The end result
  protected static String valid = "Yes.";


  public static void main(String[] args)
  {

    //  Prep input
    String in;
    if(args.length > 0)
      in = args[0];
    else
      in = "";

    input = formatInput(in);


    //  Get the first character
    token = input.remove();

    //  Start with E
    EA();

    System.out.println(valid);
  }

  /**
  * NTS Method: E
  */
  static void EA()
  {
    switch(token)
    {
      case 'n': //  Rule 1
        TA();
        EB();
        break;

      case '(': //  Rule 1
        TA();
        EB();
        break;

      default:
        valid = "No.";
        break;
    }
  }

  /**
  * NTS Method: E'
  */
  static void EB()
  {
    switch(token)
    {
      case '+': //  Rule 2
        get();
        TA();
        EB();
        break;

      case '-': //  Rule 3
        get();
        TA();
        EB();
        break;

      case ')': //  Rule 4
        break;

      case '$': //  Rule 4
        break;

      default:
        valid = "No.";
        break;
    }
  }

  /**
  * NTS Method: T
  */
  static void TA()
  {
    switch(token)
    {
      case 'n': //  Rule 5
        FA();
        TB();
        break;

      case '(': //  Rule 5
        FA();
        TB();
        break;

      default:
        valid = "No.";
        break;
    }
  }

  /**
  * NTS Method: T'
  */
  static void TB()
  {
    switch(token)
    {
      case '+': //  Rule 8
        break;

      case '-': //  Rule 8
        break;

      case '*': //  Rule 6
        get();
        FA();
        TB();
        break;

      case '/': //  Rule 7
        get();
        FA();
        TB();
        break;

      case ')': //  Rule 8
        break;

      case '$': //  Rule 8
        break;

      default:
        valid = "No.";
        break;
    }
  }

  /**
  * NTS Method: F
  */
  static void FA()
  {
    switch(token)
    {
      case 'n': //  Rule 10
        get();
        break;

      case '(': //  Rule 9
        get();
        EA();
        get();
        break;

      default:
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
}
