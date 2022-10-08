package atmprocess;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Real {
    static Real atmOperations = new Real();
    static CreationATM[] ATM = {
            new CreationATM(2000,20,40000),
            new CreationATM(500,10,5000),
            new CreationATM(100,100,10000)
    };
    static List<CreationATM> Atm_attributes = Arrays.asList(ATM);
    static CustomerDetails[] customerDetails = {
            new CustomerDetails(101,"Suresh",2343,25234),
            new CustomerDetails(102,"Ganesh",5432,34123),
            new CustomerDetails(103,"Magesh",7854,26100),
            new CustomerDetails(104,"Naresh",2345,80000),
            new CustomerDetails(105,"Harish",1907,103400)
    };
    static List<CustomerDetails> CUS = Arrays.asList(customerDetails);
    public int checkBalance(int accountNumber,int pin)
    {
        for(CustomerDetails customer : CUS){
            if(customer.getAccNo() == accountNumber && customer.getPin()==pin){
                return customer.getAccountBalance();
            }
        }
        return 0;
    }

    public boolean withDrawPossible(int accountNumber,int pin,int amt){
        for(CustomerDetails customer : CUS){
            if(customer.getAccNo() == accountNumber && customer.getPin()==pin && customer.getAccountBalance()>=amt){
                customer.setAccountBalance(customer.getAccountBalance()-amt);
                return true;
            }
        }
        return false;
    }

    public boolean transferPossible(int accountNumber,int pin,int amt,int otherAccNo){
        for(CustomerDetails customer : CUS){
            if(customer.getAccNo() == accountNumber && customer.getPin()==pin&&customer.getAccountBalance()>=amt){
                customer.setAccountBalance(customer.getAccountBalance()-amt);
            }
        }
        for(CustomerDetails customer : CUS){
            if(customer.getAccNo() == otherAccNo){
                customer.setAccountBalance(customer.getAccountBalance()+amt);
                return true;
            }
        }

        return false;
    }

    public static boolean correctPinOrNot(int acc)
    {
        for(CustomerDetails customer : CUS){
            if(customer.getAccNo() == acc){
                return true;
            }
        }
        return false;
    }
    public static boolean correctPinOrNot(int acc,int pin)
    {
        for(CustomerDetails customer : CUS){
            if(customer.getAccNo() == acc && customer.getPin()==pin){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int amount;
        Scanner scanner = new Scanner(System.in);
        boolean condition = true;
        int otherAccNo;
        int accNo,pin;
        System.out.println("Welcome to ABCD Bank ATM");
        while (condition) {
            System.out.println("____Why do you use our ATM?____");
            System.out.println("1 . Check Balance");
            System.out.println("2 . Withdraw Amount");
            System.out.println("3 . Transfer Amount");
            System.out.println("4 . AMT Balance");
            System.out.println("5 . Exit");
            System.out.println("_______________________________");
            System.out.println("Enter Your Choice: ");
            int ch = scanner.nextInt();
            switch (ch) {
                case 1: {
                    System.out.println("You have to chosen Balance Checking Option\n");
                    System.out.println("Please Enter Account Number: ");
                    accNo = scanner.nextInt();
                    System.out.println("Enter Your Four Digit Pin: ");
                    pin = scanner.nextInt();
                    if (correctPinOrNot(accNo, pin)) {
                        int balance = atmOperations.checkBalance(accNo, pin);
                        System.out.println("\nYour Current Balance is " + balance);
                    }
                }
                case 2:{
                    System.out.println("You have to chosen Withdrawing Option\n");
                    System.out.println("Please Enter Account Number: ");
                    accNo = scanner.nextInt();
                    System.out.println("Enter Your Four Digit Pin: ");
                    pin = scanner.nextInt();
                    System.out.println("Enter Amount of Money to withdraw: ");
                    amount=scanner.nextInt();
                    if(correctPinOrNot(accNo,pin)){
                        if(amount>=2000){
                            if(atmOperations.withDrawPossible(accNo,pin,amount)){
                                int withdrawAmount=amount;
                                if(Atm_attributes.stream().filter(atm -> atm.getValue()>=withdrawAmount).count()>1){
                                    Atm_attributes.stream().filter(atm -> atm.getDenomination()==2000).forEach(atm->atm.setValue(atm.getValue()-withdrawAmount));
                                    Atm_attributes.stream().filter(atm -> atm.getDenomination()==2000).forEach(atm->atm.setNumber(atm.getNumber()-(withdrawAmount/2000)));
                                    System.out.println("AMUNT WITHDRWAN");
                                }
                            }
                        }
                        else if(amount>=500){
                            if(atmOperations.withDrawPossible(accNo,pin,amount)){
                                int withdrawAmount=amount;
                                if(Atm_attributes.stream().filter(atm -> atm.getValue()>=withdrawAmount).count()>1){
                                    Atm_attributes.stream().filter(atm -> atm.getDenomination()==500).forEach(atm->atm.setValue(atm.getValue()-withdrawAmount));
                                    Atm_attributes.stream().filter(atm -> atm.getDenomination()==500).forEach(atm->atm.setNumber(atm.getNumber()-(withdrawAmount/500)));
                                    System.out.println("AMUNT WITHDRWAN");
                                }
                            }
                        }
                        else if(amount>=100){
                            if(atmOperations.withDrawPossible(accNo,pin,amount)){
                                int withdrawAmount=amount;
                                if(Atm_attributes.stream().filter(atm -> atm.getValue()>=withdrawAmount).count()>1){
                                    Atm_attributes.stream().filter(atm -> atm.getDenomination()==100).forEach(atm->atm.setValue(atm.getValue()-withdrawAmount));
                                    Atm_attributes.stream().filter(atm -> atm.getDenomination()==100).forEach(atm->atm.setNumber(atm.getNumber()-(withdrawAmount/100)));
                                    System.out.println("AMUNT WITHDRWAN");
                                }
                            }
                        }
                    }
                }
                case 3: Atm_attributes.forEach(System.out::println);
                case 4: condition=false;
                default : System.out.println("Enter from 1,2,3,4,5");
            }
        }
    }
}