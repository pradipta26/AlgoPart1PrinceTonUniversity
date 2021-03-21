public class HelloGoodbye {
    public static void main(String[] args) {
        if (null != args && args.length >= 2)
            System.out.println(String.format("Hello %s and %s.\nGoodbye %s and %s.", args[0], args[1], args[1], args[0]));
        else
            System.out.println("Insufficient Argument.");

    }
}
