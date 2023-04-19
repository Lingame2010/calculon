import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner; // считыватель с клавиатуры
// TODO : разделить на 2 класса
public class calculon {
    public static void main(String[] args) {
        // шапка
		int value1 = 0;
        int value2 = 0;
		Scanner scanner = new Scanner(System.in); // аапрос данных подряд?
		String val1 = null;
		String val2 = null;
		boolean arab = false;
 
		
		
		
		 int otvet = 0;
		System.out.println(" калькулятор принимает римские и арабские числа от 1 до 10 ");
		
			System.out.println("Введите выражение "); 
		String str = scanner.next();
		str = str.replaceAll("\s{2,}", "");
		// считаем и выводим ответ в зависимости от системы счисления				
		if (str.contains("+")) { 
			String regex  = "\\+";
			val1 = getval1(str,regex);
			val2 = getval2(str,regex);
			value1 = romanToArabic(val1);
			value2 = romanToArabic(val2);
								if(value1==-1 || value2==-1){
			value1 = (Integer.parseInt(val1));		
			value2 = (Integer.parseInt(val2));
			arab=true;
			}
			if ((value1 <= 0) || (value1 > 10)) {
		        throw new IllegalArgumentException("число не их допустимого диапазона");
			}
			if ((value2 <= 0) || (value2 > 10)) {
		        throw new IllegalArgumentException("число не их допустимого диапазона");
			}

		
            otvet = (value1 + value2); // расчет
			
			
			if (arab == false){ // проверка системы счисления
				String otvets = arabicToRoman(otvet);
				System.out.println(otvets);
			}else 
				System.out.println(otvet); // вывод ответа на экран
        }
        if (str.contains("-")) {
            String regex  = "\\-";
			val1 = getval1(str,regex);
			val2 = getval2(str,regex);
			value1 = romanToArabic(val1);
			value2 = romanToArabic(val2);
					if(value1==-1 || value2==-1){
			value1 = (Integer.parseInt(val1));		
			value2 = (Integer.parseInt(val2));
			arab=true;
					}
			if ((value1 <= 0) || (value1 > 10)) {
		        throw new IllegalArgumentException("число не их допустимого диапазона");
			}
			if ((value2 <= 0) || (value2 > 10)) {
		        throw new IllegalArgumentException("число не их допустимого диапазона");
			}

			otvet = (value1 - value2);
			if (arab == false){
			String otvets = arabicToRoman(otvet);
			System.out.println(otvets);
			
			}else 
			System.out.println(otvet);
        }
        if (str.contains("*")) {
			String regex  = "\\*";
			val1 = getval1(str,regex);
			val2 = getval2(str,regex);
			value1 = romanToArabic(val1);
			value2 = romanToArabic(val2);
					if(value1==-1 || value2==-1){
			value1 = (Integer.parseInt(val1));		
			value2 = (Integer.parseInt(val2));
			arab=true;			
			}	
            if ((value1 <= 0) || (value1 > 10)) {
		        throw new IllegalArgumentException("число не их допустимого диапазона");
			}
			if ((value2 <= 0) || (value2 > 10)) {
		        throw new IllegalArgumentException("число не их допустимого диапазона");
			}

			
			otvet = (value1 * value2);
			if (arab == false){
			String otvets = arabicToRoman(otvet);
			System.out.println(otvets);
			}else 
			System.out.println(otvet);
				
        }
        if (str.contains("/")) {
			String regex  = "/";
			val1 = getval1(str,	regex);
			val2 = getval2(str,regex);
			value1 = romanToArabic(val1);
			value2 = romanToArabic(val2);
								if(value1==-1 || value2==-1){
			value1 = (Integer.parseInt(val1));		
			value2 = (Integer.parseInt(val2));
					arab=true;
					}
			if ((value1 <= 0) || (value1 > 10)) {
		        throw new IllegalArgumentException("число не их допустимого диапазона");
			}
			if ((value2 <= 0) || (value2 > 10)) {
		        throw new IllegalArgumentException("число не их допустимого диапазона");
			}

            otvet = (value1 / value2);
			if (arab == false){
			String otvets = arabicToRoman(otvet);
			System.out.println(otvets);
			}else 
			System.out.println(otvet);
        } 
    }

  static String getval1(String str, String regex) {
	         
	   String[] data = str.split(regex, 0);
        return data[0]; }
	
	
  static String getval2(String str, String regex) {
        String[] data = str.split(regex, 0);
        return data[1];  }
//конвертер в арабские цифры	
private enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10), 
    XL(40), L(50), XC(90), C(100), 
    CD(400), D(500), CM(900), M(1000);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static List<RomanNumeral> getReverseSortedValues() {
        return Arrays.stream(values())
          .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
          .collect(Collectors.toList());
    }
}

private static int romanToArabic(String input) {
    String romanNumeral = input.toUpperCase();
    int result = 0;
        
    List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

    int i = 0;

    while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
        RomanNumeral symbol = romanNumerals.get(i);
        if (romanNumeral.startsWith(symbol.name())) {
            result += symbol.getValue();
            romanNumeral = romanNumeral.substring(symbol.name().length());
			   
	   } else {
            i++;
        }
    }

    if (romanNumeral.length() > 0) {	    
			return -1;
    }

    return result;
}
// конвертер в римские цифры
public static String arabicToRoman(int number) {
    if ((number <= 0) ) {
        throw new IllegalArgumentException(number + " в римской системе счисления нет отрицательных цифр ");
    }
	
    List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

    int i = 0;
    StringBuilder sb = new StringBuilder();

    while ((number > 0) && (i < romanNumerals.size())) {
        RomanNumeral currentSymbol = romanNumerals.get(i);
        if (currentSymbol.getValue() <= number) {
            sb.append(currentSymbol.name());
            number -= currentSymbol.getValue();
        } else {
            i++;
        }
    }

    return sb.toString();
}
}
