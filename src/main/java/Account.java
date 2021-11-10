import java.sql.PreparedStatement;

public class Account{

    private String[] accountInfo;
    private String username;
    private String password;
    protected double balance;


SQLConnection connect = new SQLConnection();
AccountDAO DAO = new AccountDAO();

    public Account(){

    }
    public Account(String[] accountInfo){
        this.accountInfo = accountInfo;
        balance = 0;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountInfo(String[] accountInfo) {
        this.accountInfo = accountInfo;
    }
    public String[] getAccountInfo(){
        return this.accountInfo;
    }
    //balance getter
    public double getBalance(){

        return this.balance;
    }
    //check balance method
    public void checkBalance(){
        System.out.println("The balance of this account is $" + DAO.getAccountBalance());
    }
    public void deposit(double amount){
        if(amount > 0) {
            balance += amount;
            try {
                String sql = "insert into jbank.transactions (deposits) values (?)";
                PreparedStatement stmt = connect.getInstance().prepareStatement(sql);
                stmt.setDouble(1, amount);
                stmt.executeUpdate();
                //System.out.println(stmt);

                sql = "update jbank.accounts set balance = balance + (?)";
                PreparedStatement stm = connect.getInstance().prepareStatement(sql);
                stm.setDouble(1, amount);
                stm.executeUpdate();
            }catch(java.sql.SQLException l){
                    System.out.println(l.getMessage());


                }
            System.out.println("You have deposited $" + amount + ". Your balance is now: $" + DAO.getAccountBalance());

        }else{
            System.out.println("Invalid deposit amount. Please try again.");
        }
    }
    public void withdraw(double amount) {
        if (amount > 0) {
            if (amount <= balance) {
                balance -= amount;

                try{ String sql = "insert into jbank.transactions (withdrawals) values (?)";
                    PreparedStatement stmt = connect.getInstance().prepareStatement(sql);
                    stmt.setDouble(1,amount);
                    stmt.executeUpdate();

                    sql = "update jbank.accounts set balance = balance - (?)";
                    PreparedStatement stm = connect.getInstance().prepareStatement(sql);
                    stm.setDouble(1, amount);
                    stm.executeUpdate();

                }catch(java.sql.SQLException o){
                    System.out.println(o.getMessage());
                    //System.out.println(stmt);
                }
                System.out.println("$" + amount + " withdrawn from account. Your remaining balance is $" + DAO.getAccountBalance());


            }
        else {
            System.out.println("invalid withdraw amount. Please try again.");
        }
        }

    }


}
