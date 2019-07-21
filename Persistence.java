package by.zhuk.bankomat;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

class Persistence {

    private String path;

    Persistence(String path) {
        this.path = path;
    }

    List<Account> readAccounts() throws Exception {
        List<Account> accounts;
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            accounts = in.lines().map(this::parseAccount).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception("Error reading file");
        }

        return accounts;
    }

    void writeAccounts(List<Account> accounts) throws Exception {

        try (FileWriter fileWriter = new FileWriter(path);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            accounts.forEach(acc -> printWriter.print(acc.toLine() + "\n"));

        } catch (IOException ex) {
            throw new Exception("File write error");
        }
    }

    private Account parseAccount(String line) {
        String[] words = line.split(" ");
        return new Account(words[0], Integer.parseInt(words[1]), Integer.parseInt(words[2]));
    }
}
