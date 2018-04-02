package ru.nik66;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {

    private Map<User, List<Account>> users;

    {
        this.users = new HashMap<>();
    }

    //добавление пользователя
    public void addUser(User user) {
        this.users.putIfAbsent(user, new ArrayList<>());
    }

    // удаление пользователя.
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    // добавить счёт пользователю.
    public void addAccountToUser(String passport, Account account) {
        this.getUserAccounts(passport).add(account);
    }

    // удалить один счёт пользователя.
    public void deleteAccountFromUser(String passport, Account account) {
        this.getUserAccounts(passport).remove(account);
    }

    // получить список счетов для пользователя.
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = null;
        for (Map.Entry<User, List<Account>> entry : this.users.entrySet()) {
            if (entry.getKey().getPassport().equals(passport)) {
                result = entry.getValue();
                break;
            }
        }
        return result;
    }

    // метод для перечисления денег с одного счёта на другой счёт:
    // если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) должен вернуть false.
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String dstPassport, String dstRequisite,
                                 double amount) {
        boolean result = false;
        Account accFrom = this.findAccByRequisite(this.getUserAccounts(srcPassport), srcRequisite);
        if (accFrom != null) {
            Account accTo = this.findAccByRequisite(this.getUserAccounts(dstPassport), dstRequisite);
            if (accTo != null) {
                result = accFrom.transferTo(accTo, amount);
            }
        }
        return result;
    }

    private Account findAccByRequisite(List<Account> accounts, String requisite) {
        Account result = null;
        for (Account account : accounts) {
            if (account.getRequisites().equals(requisite)) {
                result = account;
                break;
            }
        }
        return result;
    }

}
