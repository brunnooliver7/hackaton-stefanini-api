package com.stefanini.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.stefanini.model.Pessoa;
import com.stefanini.servico.PessoaServico;

import mockit.Expectations;
import mockit.Tested;

public class PessoaTest {
	
    @Tested
    private PessoaServico pessoaServico;

    @Tested
    private Pessoa pessoa;
    
    private List<Pessoa> listaPessoas;
    
    @Before
    public void setup() {
    	listaPessoas = new ArrayList<Pessoa>();
    	Pessoa p1 = new Pessoa();
    	Pessoa p2 = new Pessoa();
    	listaPessoas.add(p1);
    	listaPessoas.add(p2);
    }

    @Test
    public void listarPessoasVinculadas() {
    	

    	List<Pessoa> pessoas = pessoaServico.listarPessoasVinculadas();
    	assertEquals(6, pessoas.size());
    	
    	
    }

}
