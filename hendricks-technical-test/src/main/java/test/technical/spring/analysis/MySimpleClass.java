package test.technical.spring.analysis;

/*********************************************
 * Study this class file to look at what are the issues we would need to do?
 *
 * 1. Study this class and its implementation. Pick out the issues in the class implementations
 *
 *      Answer: getter and setter for each variable, 
 *
 * 2. Identify ways these class implementation can be improved.
 *
 *      Answer: adding getter and setter. add super() in the empty constructor to register the o
 */
public class MySimpleClass {


    public String name;

    public String value;

    public String work;

    public MySimpleClass () {

    }

    public MySimpleClass(String name, String value, String work ) {
        this.name = name;
        this.value = value;
        this.work = work;
    }

    /**********************************
     *      Questions to this method:
     *      1. Can you identify what are the risks in this function?
     *
     *      Answer : exception will be returned if one of the strings are null
     *
     *      2. What would you do to change the codes into something more meaningful?
     *
     *      Answer : check for null for param1 and param2 before returninig them
     ***********************************/
    public String join2Strings ( String param1, String param2 ) {

        return param1.concat(param2);
    }

    /**********************************
     *       Questions to this method:
     *      1. Can you explain what function do?
     *
     *      Answer : function collects 2 value, x and y. it will return the sum equivalent of x number of y, also same as x*y
     *
     *      2. How do you think the way this function is done up can be improved?
     *
     *      Answer : 1) should use a more specific exception instead, like NumberFormatException
     *      2) there is no need to use a loop in this function, just return (x*y);
     *
     *      3. If there is any implementation you'd like to work on, please feel free to do so in the function
     *
     *      Answer :
     *
     ***********************************/
    private int function1(int x, int y ) throws Exception {
        int value = 0;
        for ( int i = 0; i < x; i++ ) {
            value += y;
        }
        return value;
    }


    /*******************************************
     *      1. Can you identify what could go wrong in this function?
     *
     *      Answer : 1)nothing is returned from this function, the string val could be return from this method instead
     *      2) an empty array could cause the function to return a nullpointer exception 
     *
     *      2. If you were to be assigned ways to change the code, what would you do?
     *
     *      Answer : check the param array if its null before proceeding with the function
     *
     */
    public String function2(int[] param, String val ) {
        for (int i = 0; i < param.length; i++ ) {
            val = val + param[i] + ".";
        }
        return val;
    }

    /*******************************************
     * 1. Can you identify what could go wrong in this function?
     * the main method of this application is declared private, which will not be able to access from the JVM
     * 
     * 2. If you were to be assigned ways to change the code, what would you do?
     * change it to public static void main()
     * 
     */
    private static void main(String[] args) {

        MySimpleClass instance = new MySimpleClass();

        ///Can you explain what what would be the outcome of when this set of codes do?
        int[] param = {1,2,3,4,5};
        String val = "";
        instance.function2(param, val);
        ///End of Code
        
		MySimpleClass c = new MySimpleClass();
	      System.out.println(c.join2Strings("", ""));
	      ///End of Code
			
    }


}
