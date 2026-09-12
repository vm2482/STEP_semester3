class AccessChecker {

    static String classifyAccess(String modifier, String context) {

        switch (modifier) {

            case "private":
                return context.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

            case "default":
                return context.equals("DIFFERENT_PACKAGE") ? "DENIED" : "ALLOWED";

            case "protected":
                return context.equals("DIFFERENT_PACKAGE") ? "DENIED" : "ALLOWED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }
}

public class M1 {
    public static void main(String[] args) {

        System.out.println(
            AccessChecker.classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
            AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE")
        );
    }
}