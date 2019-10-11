package com.angle.communication.service;

import com.angle.communication.Model.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface AccountService {
    public Account findByAccountname(String username);
    public int add(Account account);
    public Account edit(Account account);
    public int editPassword(Account account);
    public int delete(String id);
    public List<Account> findList(Map<String, Object> queryMap);
    public int getTotal(Map<String, Object> queryMap);
}