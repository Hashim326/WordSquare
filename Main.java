public class Main {
    

    public static void main(String args[]) {
        int rowSize;
        String inputString;

        if (args.length != 2) {
            System.out.println("Usage: java Main <rowSize> <inputString>");
            return;
        }

        try {
            rowSize = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("First argument must be an integer.");
            return;
        }

        if (rowSize <= 0) {
            System.out.println("Row size must be greater than 0.");
            return;
        }

        inputString = args[1];

        if (inputString.length() != rowSize * rowSize) {
            System.out.println("Input string length must match the square of the row size.");
            return;
        }

        System.out.println("Row Size: " + rowSize);
        System.out.println("Input String: " + inputString);

        Dictionary dict = new Dictionary();
    }
}
