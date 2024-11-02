import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {


    public static void print( ) {
        System.out.println("Enter 1 to add the numbers");
        System.out.println("Enter 2 to subtract the numbers");
        System.out.println("Enter 3 to multiply the numbers");
        System.out.println("Enter 4 to divide the numbers");
        System.out.println("Enter 5 to modulus the numbers");
        System.out.println("Enter 6 to find the minimum number");
        System.out.println("Enter 7 to find the maximum number");
        System.out.println("Enter 8 to find the average of numbers");
        System.out.println("Enter 9 to print the last result in calculator");
        System.out.println("Enter 10 to print the list of all results in calculator");
        System.out.println("Enter 0 to exit");
        System.out.print("\n->");

    }

    public static int [] get_num ( ) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int x = input.nextInt();
        System.out.print("Enter second number: ");
        int y = input.nextInt();

        return new int [] {x,y};
    }

    public static void main (String[] args ) {
        Scanner input = new Scanner(System.in);
        int choice=1;
        ArrayList<Float> results = new ArrayList();
        try {


            System.out.println("Welcome to the Calculator");
            System.out.println("----------------------------");
            while (choice != 0) {

                print();
                choice = input.nextInt();

                int []  num;
                float result=0;
                switch (choice) {
                    case 1:
                        num = get_num();
                        result = addition(num[0],num[1]);
                        System.out.println("\n"+num[0]+" + "+num[1]+" = "+result+"\n");
                        results.add(result);
                        break;

                    case 2:
                        num = get_num();
                        result = subtraction(num[0],num[1]);
                        System.out.println("\n"+num[0]+" - "+ num[1]+" = "+result+"\n");
                        results.add(result);
                        break;

                    case 3:
                        num = get_num();
                        result = multiplication(num[0],num[1]);
                        System.out.println("\n"+num[0]+" * "+num[1]+" = "+result+"\n");
                        results.add(result);
                        break;

                    case 4:
                        num = get_num();
                        result = division(num[0],num[1]);
                        System.out.println("\n"+num[0]+" / "+num[1]+" = "+result+"\n");
                        results.add(result);
                        break;

                    case 5:
                        num = get_num();
                        result = modulo(num[0],num[1]);
                        System.out.println("\n"+num[0]+" % "+ num[1]+" = "+result+"\n");
                        results.add(result);
                        break;

                    case 6:
                        num = get_num();
                        result = minimum(num[0],num[1]);
                        System.out.println("\nMinimum number"+result+"\n");
                        results.add(result);
                        break;

                    case 7:
                        num = get_num();
                        result = maximum(num[0],num[1]);
                        System.out.println("\nMaximum number"+result+"\n");
                        results.add(result);
                        break;

                    case 8:
                        num = get_num();
                        result = average(num[0],num[1]);
                        System.out.println("\n"+num[0]+" + "+ num[1]+" /2 "+" = "+result+"\n");
                        results.add(result);
                        break;

                    case 9:

                        System.out.println("last result "+results.getLast());
                        break;

                    case 10:

                        System.out.println("list of all results in calculator");
                        for(float var : results){
                            System.out.println(var);
                        }

                        break;


                    default:
                        System.out.println("Invalid choice");
                        break;
                }


            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input");
        }catch (ArithmeticException e){
            System.out.println("Divide by zero");
        }
    }


    public static int addition  (int a, int b ) {
        return a + b;
    }
    public static int subtraction (int a, int b ) {
        return a - b;
    }
    public static int multiplication (int a, int b ) {
        return a * b;
    }
    public static int division (int a, int b ) {
        return a / b;
    }

    public static int modulo (int a, int b ) {
        return a % b;
    }

    public static int  minimum (int a, int b ) {
        return Math.min(a, b);
    }

    public static int maximum (int a, int b ) {
        return Math.max(a, b);
    }

    public static float average (int a, int b ) {
        return (float) (a + b) / 2;
    }



}
