package com.jeanlima.mvcapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jeanlima.mvcapp.model.Estudante;

@Component
public class EstudanteServiceImpl implements EstudanteService{

    public List<Estudante> estudantes = new ArrayList<Estudante>();    

    @Override
    public void salvarEstudante(Estudante estudante) {
        System.out.println(estudante.toString());
        try{
            this.estudantes.add(estudante);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }
        
        
    }

    @Override
    public void deletarEstudante(Estudante estudante) {
            this.estudantes.remove(estudante);
    }

    @Override
    public Estudante getEstudanteById(Integer id) {
        for (Estudante estudante : this.estudantes) {
            if(estudante.getId() == id){
                return estudante;
            }
        }
        return null;
    }

    @Override
    public List<Estudante> getListaEstudante() {
        return this.estudantes;
    }

    @Override
    public List<Estudante> getEstudantesBycurso(String curso) {
        System.out.println("LOAKO");
        List<Estudante> estudantesByCurso = new ArrayList<Estudante>();
        for (Estudante estudante : this.estudantes) {
            if(estudante.getCurso().equalsIgnoreCase(curso)){
                
                System.out.println("ACHOW");
                estudantesByCurso.add(estudante);
            }
        }
        return estudantesByCurso;

    }

    @Override
    public List<Estudante> getEstudantesByLinguagem(String linguagem) {
        List<Estudante> estudantesByLinguagem = new ArrayList<Estudante>();
        for (Estudante estudante : this.estudantes) {
            if(estudante.getLinguagem().equalsIgnoreCase(linguagem)){
                estudantesByLinguagem.add(estudante);
            }
        }
        return estudantesByLinguagem;
        
    }

    
}
