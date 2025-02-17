package com.AcademicoPlus.AcademicoPlus.models;

import java.util.Date;
import java.util.List;

// Classe base Usuario
public abstract class Usuario {
    protected int id;
    protected String nome;
    protected String email;
    protected String senha;

    public abstract void fazerLogin();
    public abstract void fazerLogout();
}
