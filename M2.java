class AccessCheck {

    static String classifyAccess(String modifier, String context) {

        switch (modifier) {

            case "private":
                return context.equals("SAME_CLASS")
                        ? "ALLOWED" : "DENIED";

            case "default":
                return context.equals("SAME_CLASS") ||
                        context.equals("SAME_PACKAGE")
                        ? "ALLOWED" : "DENIED";

            case "protected":
                if (context.equals("SAME_CLASS") ||
                        context.equals("SAME_PACKAGE") ||
                        context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
                    return "ALLOWED";
                }
                return "DENIED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    static String firstDeniedAttempt(String[][] attempts) {

        for (int i = 0; i < attempts.length; i++) {

            String result =
                    classifyAccess(attempts[i][0], attempts[i][1]);

            if (result.equals("DENIED")) {
                return attempts[i][0] + " via "
                        + attempts[i][1] + " (attempt #" + (i + 1) + ")";
            }
        }

        return "None Denied";
    }
}

public class M2 {

    public static void main(String[] args) {

        String[][] attempts = {
                {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
                {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(
                AccessCheck.firstDeniedAttempt(attempts)
        );
    }
}