package cn.drelang;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String word = "hello word";
//        System.out.println(word.substring(0, 0));
        method(null);
    }

    public static void method(String param) {
        if (param == null) return ;
        switch (param) {
            case "sth":
                System.out.println("sth");
                break;
            case "null":
                System.out.println("null");
            default:
                System.out.println("default");
        }
    }
}
