package io.security.springsecurityv6;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MethodController {

    private final DataService dataService;

    @PostMapping("/writeList")
    public List<Account> writeList(@RequestBody List<Account> accounts) {
        return dataService.writeList(accounts);
    }

    @PostMapping("/writeMap")
    public Map<String, Account> writeMap(@RequestBody List<Account> accounts) {
        Map<String, Account> accountMap = accounts.stream().collect(Collectors.toMap(Account::getOwner, account -> account));
        return dataService.writeMap(accountMap);
    }

    @GetMapping("/readList")
    public List<Account> readList() {
        return dataService.readList();
    }

    @GetMapping("/readMap")
    public Map<String, Account> readMap() {
        return dataService.readMap();
    }


}
