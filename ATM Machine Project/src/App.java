// import java.io.ObjectInputFilter.Status;
// import java.text.NumberFormat.Style;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {

        AtmOperationInterface op = new AtmOperationImpl();
        int atmnumber = 12345;
        int atmpin = 123;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Atm Number : ");
        int atmNumber = sc.nextInt();
        System.out.print("Enter Atm Pin : ");
        int atmPin = sc.nextInt();
        //sc.close();
        if(atmnumber==atmNumber && atmpin==atmPin){
            //System.out.print("Validation Done");
            while(true){
                System.out.println("1.View Available Balance\n2.Withraw Amount\n3.Deposite Amount\n4.View Mini Statement\n5.Exit");
                System.out.println("Enter Your Choice : ");
                int Choice = sc.nextInt();
                if(Choice==1){
                    op.viewBalance();
                }
                else if(Choice==2){
                    System.out.println("Enetr amount to withraw ");
                    double withrawAmount = sc.nextDouble();
                    op.withrawAmount(withrawAmount);

                }
                else if(Choice==3){
                    System.out.println("Enter Amount to Deposit : ");
                    double depositAmount = sc.nextDouble();
                    op.depositAmount(depositAmount);
                }
                else if(Choice==4){
                    op.viewMiniStatement();
                }
                else if(Choice==5){
                    System.out.println("Collect your ATM card \n Thank you for using ATM Machine");
                    System.exit(0);
                }
                else{
                    System.out.print("Please Eneter valid choice");
                }

            }
        }else{
            System.out.print("Incorrect ATM Number or PIN");
        }
    }
}

// code for the interface 

interface AtmOperationInterface {
    public void viewBalance();
    public void withrawAmount(double withrawAmount);
    public void depositAmount(double depositAmount);
    public void viewMiniStatement();
}

// code for operation Implementaion

class AtmOperationImpl implements AtmOperationInterface{
    HashMap<Double,String> ministmt = new HashMap<>();
    ATM atm = new ATM();
    @Override
    public void viewBalance(){
        System.out.println("Available Balance is : "+atm.getBalance());
    }

    @Override
    public void withrawAmount(double withrawAmount){
        if(withrawAmount < atm.getBalance()){
            ministmt.put(withrawAmount," Amount withrawn");
            System.out.println("Collect the cash : "+withrawAmount);
            atm.setBalance(atm.getBalance()-withrawAmount);
            viewBalance();
        }
        else{
            System.out.println("Insufficient Balance !!");
        }
    }
    @Override
    public void depositAmount(double depositAmount){
        ministmt.put(depositAmount," Amount Deposited");
        System.out.println(depositAmount+" Deposited Successfully !!");
        atm.setBalance(atm.getBalance()+depositAmount);
        viewBalance();
    }
    @Override
    public void viewMiniStatement(){
        for(double key : ministmt.keySet()){
            System.out.println(key+" "+ministmt.get(key));
        }
    }
}

// ATM class code

class ATM{
    private double balance;
    private double depositAmount;
    private double withrawAmount;

    // default constructor
    public ATM(){

    }
    // getter and setter 
    public double getBalance(){
        return balance;
    }
    public void setBalance(double depositAmount){
        this.balance = depositAmount;
    }
}

