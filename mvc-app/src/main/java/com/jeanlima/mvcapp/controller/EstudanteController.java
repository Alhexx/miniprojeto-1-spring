package com.jeanlima.mvcapp.controller;

import java.util.List;

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

    @RequestMapping("/showFormFiltros")
	public String showFormFiltros(Model model){
        model.addAttribute("estudante", new Estudante());
        System.out.print("dasdaw");
		return "estudante/formFiltros";
	}

    @RequestMapping("/processaFormCurso")
	public String processFormCurso(HttpServletRequest request, Model model){
        System.out.print(request.getParameter("curso"));
		String curso = request.getParameter("curso");
        List<Estudante> estudantes = estudanteService.getEstudantesBycurso(curso);
        model.addAttribute("estudantes",estudantes);
		return "estudante/listaEstudantes";
	}

    @RequestMapping("/processaFormLinguagem")
	public String processFormLinguagem(HttpServletRequest request, Model model){
        System.out.print(request.getParameter("linguagem"));
		String linguagem = request.getParameter("linguagem");
        List<Estudante> estudantes = estudanteService.getEstudantesByLinguagem(linguagem);
        model.addAttribute("estudantes",estudantes);
		return "estudante/listaEstudantes";
	}

    @RequestMapping(value = "/detalhes/{id}")
    public String getEstudanteById(@PathVariable String id, Model model) {
        System.out.println("BORA BB: " + id);
        int paramId = Integer.parseInt(id);
        Estudante estudante = estudanteService.getEstudanteById(paramId);
        model.addAttribute("estudante",estudante);
        System.out.println("BORA BB: " + estudante);
		return "estudante/listaEstudanteId";
    }

    @RequestMapping(value = "/deletar/{id}")
    public String deleteEstudante(@PathVariable String id, Model model) {
        
        System.out.println("BORA BB: " + id);
        int paramId = Integer.parseInt(id);
        Estudante estudante = estudanteService.getEstudanteById(paramId);
        estudanteService.deletarEstudante(estudante);
		List<Estudante> estudantes = estudanteService.getListaEstudante();
        model.addAttribute("estudantes",estudantes);
        return "estudante/deletionConfirm";
    }



    
    
}
