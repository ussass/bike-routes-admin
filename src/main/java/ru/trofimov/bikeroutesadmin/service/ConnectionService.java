package ru.trofimov.bikeroutesadmin.service;

import ru.trofimov.bikeroutesadmin.model.Admin;
import ru.trofimov.bikeroutesadmin.model.User;

import java.util.List;

public interface ConnectionService {

    void authorization(Admin admin);

    List<User> getUserList();
}
