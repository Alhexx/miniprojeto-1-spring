package com.jeanlima.mvcapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.jeanlima.mvcapp.model.Estudante;
import com.jeanlima.mvcapp.service.EstudanteService;

@Controller
@RequestMapping("/estudante")
public class EstudanteController {


    @Autowired
    @Qualifier("estudanteServiceImpl")
    EstudanteService estudanteService;

    @RequestMapping("/showForm")
    public String showFormEstudante(Model model){

        model.addAttribute("estudante", new Estudante());
        return "estudante/formEstudante";
    }

    @RequestMapping("/addEstudante")
    public String showFormEstudante(@ModelAttribute("estudante") Estudante estudante,  Model model){

        estudanteService.salvarEstudante(estudante);
        model.addAttribute("estudante", estudante);
        return "estudante/paginaEstudante";
    }

    @RequestMapping("/getListaEstudantes")
    public String showListaEstudante(Model model){

        List<Estudante> estudantes = estudanteService.getListaEstudante();
        model.addAttribute("estudantes",estudantes);
        return "estudante/listaEstudantes";

    }

    @RequestMapping("/processaFormCurso")
	public String processFormCurso(@ModelAttribute("estudante") Estudante estudanteCurso, Model model){
        int contador = 0;
        Map<String, List<Estudante>> details = new HashMap<>();
        for(String curso : estudanteCurso.getCursos()) {
            details.put(curso, estudanteService.getEstudantesBycurso(curso));
            contador+=estudanteService.getEstudantesBycurso(curso).size();
        }
        model.addAttribute("quantCursos", contador);
        model.addAttribute("cursos", estudanteCurso.getCursos());
        model.addAttribute("estudantesCurso",details);
		return "estudante/listaEstudantesCurso";
	}

    @RequestMapping("/processaFormLinguagem")
	public String processFormLinguagem(Model model){
        List<Estudante> estudantesJavascript = estudanteService.getEstudantesByLinguagem("Javascript");
        model.addAttribute("estudantesJavascript",estudantesJavascript);
        List<Estudante> estudantesJava = estudanteService.getEstudantesByLinguagem("Java");
        model.addAttribute("estudantesJava",estudantesJava);
        List<Estudante> estudantesC = estudanteService.getEstudantesByLinguagem("C");
        model.addAttribute("estudantesC",estudantesC);
        List<Estudante> estudantesPython = estudanteService.getEstudantesByLinguagem("Python");
        model.addAttribute("estudantesPython",estudantesPython);
        int quantLinguagem = estudantesJavascript.size()+estudantesJava.size()+estudantesC.size()+estudantesPython.size();
        model.addAttribute("quantLinguagem",quantLinguagem);
		return "estudante/listaEstudantesLinguagem";
	}

    @RequestMapping(value = "/detalhes/{id}")
    public String getEstudanteById(@PathVariable String id, Model model) {
        int paramId = Integer.parseInt(id);
        Estudante estudante = estudanteService.getEstudanteById(paramId);
        model.addAttribute("estudante",estudante);
		return "estudante/listaEstudanteId";
    }

    @RequestMapping(value = "/deletar/{id}")
    public String deleteEstudante(@PathVariable String id, Model model) {
        int paramId = Integer.parseInt(id);
        Estudante estudante = estudanteService.getEstudanteById(paramId);
        estudanteService.deletarEstudante(estudante);
		List<Estudante> estudantes = estudanteService.getListaEstudante();
        model.addAttribute("estudantes",estudantes);
        return "estudante/deletionConfirm";
    }



    
    
}
