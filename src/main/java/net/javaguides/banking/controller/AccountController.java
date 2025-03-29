package net.javaguides.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.banking.controller.dto.AccountDto;
import net.javaguides.banking.service.AccountService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    //ajouter le rest api du compter
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
         return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);   
    }

    //Get Acoount (recuperer le compte par id) REST API 
    @GetMapping("/{id}/")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //depot dans compt REST API
    @PostMapping("/{id}/deposit/")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id,@RequestBody Map<String, Double> request){

        Double amount = request.get("amount");

        AccountDto accountDto = accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    //retrait dans compte
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String, Double> request) {
        //TODO: process POST request
        Double amount = request.get("amount");

        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    //tout les compte(get all account rest api)
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accountDtos = accountService.getAllAccount();

        return ResponseEntity.ok(accountDtos);
    }

    @DeleteMapping("{id}")
    //suprimer un aompte
    public ResponseEntity<String> deletAccount(Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("compte creer avec succes");
    }
    
    
}
