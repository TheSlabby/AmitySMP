package codes.slab.amitysmp.util;

public class ConcatArgs {

    public static String concat(String[] args) {
        //convert args to string
        String result = "";
        for (String arg : args) {
            result += arg + " ";
        }

        return result;
    }

}
