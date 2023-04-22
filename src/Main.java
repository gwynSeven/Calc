import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exceptions {
        System.out.println("Введите выражение");
        Scanner console = new Scanner(System.in);
        String print = console.nextLine();
        System.out.println(calc(print));

    }

    public static String calc(String input) throws Exceptions {
        String[] romanArray = {"null","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};


        String operation = input;

        operation = operation.replaceAll(" ", "");
        operation = operation.replaceAll("[()]", "");

        String[] arrayOperation = operation.split("[-+*/]");

        if (arrayOperation.length <= 1) {
            throw new Exceptions("строка не является математической операцией");
        }

        if (arrayOperation.length > 2) {
            throw new Exceptions("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *) \n также проверьте отсутствие отрицательных значений у операндов");
        }

        hasNextRoman romanCheck = new hasNextRoman();


        //Если операнды римские
        if (romanCheck.hasNextRoman(romanArray, arrayOperation[0]) && romanCheck.hasNextRoman(romanArray, arrayOperation[1])) {
            int transformOne = romanCheck.hasRomanNumberInt(romanArray, arrayOperation[0]);
            int transformTwo = romanCheck.hasRomanNumberInt(romanArray, arrayOperation[1]);


            int result = 0;
            if (operation.indexOf('+') > 0) {
                result = transformOne + transformTwo;
            } else if (operation.indexOf('-') > 0) {
                result = transformOne - transformTwo;
            } else if (operation.indexOf('*') > 0) {
                result = transformOne * transformTwo;
            } else if (operation.indexOf('/') > 0) {
                result = transformOne / transformTwo;
            }

            if (result <= 0) {
                throw new Exceptions("в римской системе нет отрицательных чисел");
            }

            int number = result;
            String resultString = "";


            //Сотни
            if (number % 100 == 0) {
                number -= number;
                resultString += "C";
            }

            // Пятьдесят
            int fiftys = number / 50;

            if (fiftys == 1) {
                number = number - 50;
                resultString += "L";
            }

            // Десятки
            int tens = number / 10;

            if (tens > 0) {
                number = number - (10 * tens);

                for (int i = 0; i < tens; i++) {
                    resultString += "X";
                }
            }

            // Остаток в единицах
            if (number > 0) {
                resultString += romanArray[number];
            }

            return resultString;
        }

        // Арабские

        int a = 0;
        int b = 0;

        // Проверка на возможность преобразования обоих чисел в арабские
        boolean compare;
        try {
            a = Integer.parseInt(arrayOperation[0]);
            b = Integer.parseInt(arrayOperation[1]);
            compare = true;

        } catch (NumberFormatException e) {
            compare = false;
        }


        // Проверка на границы арабских чисел
        if (a > 10 || b > 10 ) {
            throw new Exceptions("Вы вне диапозона доступных значений");
        }

        // Арабское вычисление

        if (compare) {
            int result = 0;

            if (operation.indexOf('+') > 0) {
                result = Integer.parseInt(arrayOperation[0]) + Integer.parseInt(arrayOperation[1]);
            } else if (operation.indexOf('-') > 0) {
                result = Integer.parseInt(arrayOperation[0]) - Integer.parseInt(arrayOperation[1]);
            } else if (operation.indexOf('*') > 0) {
                result = Integer.parseInt(arrayOperation[0]) * Integer.parseInt(arrayOperation[1]);
            } else if (operation.indexOf('/') > 0) {
                result = Integer.parseInt(arrayOperation[0]) / Integer.parseInt(arrayOperation[1]);
            }
            return result + "";
        } else { // Если не сработало ни одно преобразования логично предположить, что ввод был изначально ошибочным
            throw new Exceptions("используются одновременно разные системы счисления");
        }
    }
}






