import java.util.Scanner;

public class codingProject1 {

    public static void main(String[] args) {
        String firstParam, secondParam, format, operation;
        String days_1 = "", days_2 = "", months_1 = "", months_2 = "", years_1 = "", years_2 = "", delimiter;

        Scanner in = new Scanner(System.in);
        System.out.println("Enter your parameters:");
        firstParam = in.nextLine();
        secondParam = in.nextLine();
        format = in.nextLine();
        delimiter = in.nextLine();
        in.close();

        // Extract the operation type (either "DOB=" or "AGE=")
        operation = firstParam.substring(0, 4);

        // Parse dates based on the format provided
        for (int i = 0; i < format.length(); i++) {
            char currentFormat = format.charAt(i);
            if (currentFormat == 'D') {
                days_1 += firstParam.charAt(4 + i);
                days_2 += secondParam.charAt(i);
            } else if (currentFormat == 'M') {
                months_1 += firstParam.charAt(4 + i);
                months_2 += secondParam.charAt(i);
            } else if (currentFormat == 'Y') {
                years_1 += firstParam.charAt(4 + i);
                years_2 += secondParam.charAt(i);
            }
        }
        int day_1 = Integer.parseInt(days_1);
        int day_2 = Integer.parseInt(days_2);
        int month_1 = Integer.parseInt(months_1);
        int month_2 = Integer.parseInt(months_2);
        int year_1 = Integer.parseInt(years_1);
        int year_2 = Integer.parseInt(years_2);
        int yearDifference = year_2 - year_1;
        int monthDifference = month_2 - month_1;
        int dayDifference = day_2 - day_1;
        // Proceed with "DOB=" operation
        if (operation.equals("DOB=")) {

            // Validate the dates
            if (!isValidDate(day_1, month_1, year_1)) {
                System.out.println("Invalid date in first parameter.");
                return;
            }
            if (!isValidDate(day_2, month_2, year_2)) {
                System.out.println("Invalid reference date.");
                return;
            }

            // Calculate the difference in years, months, and days

            if (dayDifference < 0) {
                dayDifference += getDaysInMonth(month_2 - 1, year_2);
                monthDifference--;
            }
            if (monthDifference < 0) {
                monthDifference += 12;
                yearDifference--;
            }

            if (yearDifference < 0) {
                System.out.println("Person is not yet born.");
            } else {
                System.out.println("Person is " + yearDifference + " years, " + monthDifference + " months, and " + dayDifference + " days old.");
            }
        }
        else if(operation.equals("AGE=")){
            // Validate the dates
            if (!isValidDate(day_1, month_1, year_1)) {
                System.out.println("Invalid date in first parameter.");
                return;
            }
            if (!isValidDate(day_2, month_2, year_2)) {
                System.out.println("Invalid reference date.");
                return;
            }
            if (dayDifference <= 0) {
                dayDifference += getDaysInMonth(month_2 - 1, year_2);
                monthDifference--;
            }
            if (monthDifference <= 0) {
                monthDifference += 12;
                yearDifference--;
            }

            if (yearDifference <= 0) {
                System.out.println("Person is not yet born.");
            } else {
                String dateOfBirth = "";
                String [] formatStrings = format.split(delimiter);
                for(String i : formatStrings){
                    if(i.equals("DD")){
                        dateOfBirth+=Integer.toString(dayDifference);
                        dateOfBirth+=delimiter;
                    }
                    else if(i.equals("MM")){
                        dateOfBirth+=Integer.toString(monthDifference);
                        dateOfBirth+=delimiter;
                    }
                    else if(i.equals("YYYY")){
                        dateOfBirth+=Integer.toString(yearDifference);
                        dateOfBirth+=delimiter;
                    }
                }
                System.out.println(dateOfBirth.substring(0, dateOfBirth.length()-1));
            }

        }
    }

    // Function to check if a date is valid
    public static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > getDaysInMonth(month, year)) {
            return false;
        }
        return true;
    }

    // Function to get the number of days in a month, accounting for leap years
    public static int getDaysInMonth(int month, int year) {
        switch (month) {
            case 2: // February
                if ((year % 400 == 0) || (year % 100 != 0 && year % 4 == 0)) {
                    return 29; // Leap year
                } else {
                    return 28;
                }
            case 4: case 6: case 9: case 11: // April, June, September, November
                return 30;
            default: // All other months
                return 31;
        }
    }
}