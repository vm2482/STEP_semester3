class AccessChecker {

    static String classifyAccess(String modifier, String context) {

        switch (modifier) {

            case "private":
                return context.equals("SAME_CLASS")
                        ? "ALLOWED" : "DENIED";

            case "default":
                return context.equals("DIFFERENT_PACKAGE")
                        ? "DENIED" : "ALLOWED";

            case "protected":
                return context.equals("DIFFERENT_PACKAGE") ? "DENIED" : "ALLOWED";

            case "public":
                return "ALLOWED";

            default:
                return "DENIED";
        }
    }

    static String summarizeByModifier(String[][] attempts) {

        int pa = 0, pd = 0;
        int da = 0, dd = 0;
        int pra = 0, prd = 0;
        int pua = 0, pud = 0;

        for (String[] a : attempts) {

            String result = classifyAccess(a[0], a[1]);

            if (a[0].equals("private")) {
                if (result.equals("ALLOWED")) pa++;
                else pd++;
            }

            else if (a[0].equals("default")) {
                if (result.equals("ALLOWED")) da++;
                else dd++;
            }

            else if (a[0].equals("protected")) {
                if (result.equals("ALLOWED")) pra++;
                else prd++;
            }

            else if (a[0].equals("public")) {
                if (result.equals("ALLOWED")) pua++;
                else pud++;
            }
        }

        return "private: " + pa + " allowed / " + pd + " denied"
                + " | default: " + da + " allowed / " + dd + " denied"
                + " | protected: " + pra + " allowed / " + prd + " denied"
                + " | public: " + pua + " allowed / " + pud + " denied";
    }
}

public class M1 {

    public static void main(String[] args) {

        System.out.println(
                AccessChecker.classifyAccess("private", "SAME_CLASS")
        );

        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                AccessChecker.summarizeByModifier(attempts)
        );
    }
}