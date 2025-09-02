package br.com.muniz.controllers;

/*
import br.com.muniz.exception.UnsupportedMathOperationException;
import br.com.muniz.math.SimpleMath;
import br.com.muniz.request.NumberConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    private SimpleMath math = new SimpleMath();


    @RequestMapping("/sum/{n1}/{n2}")
    public Double sum(
            @PathVariable("n1") String n1,
            @PathVariable("n2") String n2
    ) throws Exception{
        if(!NumberConverter.isNumeric(n1) || !NumberConverter.isNumeric(n2)) throw new UnsupportedMathOperationException("set a numeric value");
        return math.sum(NumberConverter.convertToDouble(n1), NumberConverter.convertToDouble(n2));
    }
    @RequestMapping("/sub/{n1}/{n2}")
    public Double sub(
            @PathVariable("n1") String n1,
            @PathVariable("n2") String n2
    ) throws Exception{
        if(!NumberConverter.isNumeric(n1) || !NumberConverter.isNumeric(n2)) throw new UnsupportedMathOperationException("set a numeric value");
        return math.sub(NumberConverter.convertToDouble(n1), NumberConverter.convertToDouble(n2));
    }
    @RequestMapping("/div/{n1}/{n2}")
    public Double div(
            @PathVariable("n1") String n1,
            @PathVariable("n2") String n2
    ) throws Exception{
        if(!NumberConverter.isNumeric(n1) || !NumberConverter.isNumeric(n2)) throw new UnsupportedMathOperationException("set a numeric value");
        return math.div(NumberConverter.convertToDouble(n1), NumberConverter.convertToDouble(n2));
    }
    @RequestMapping("/mult/{n1}/{n2}")
    public Double mult(
            @PathVariable("n1") String n1,
            @PathVariable("n2") String n2
    ) throws Exception{
        if(!NumberConverter.isNumeric(n1) || !NumberConverter.isNumeric(n2)) throw new UnsupportedMathOperationException("set a numeric value");
        return math.mult(NumberConverter.convertToDouble(n1), NumberConverter.convertToDouble(n2));
    }
    @RequestMapping("/sqrt/{n1}")
    public Double sqrt(
            @PathVariable("n1") String n1
    ) throws Exception{
        if(!NumberConverter.isNumeric(n1)) throw new UnsupportedMathOperationException("set a numeric value");
        return math.sqrt(NumberConverter.convertToDouble(n1));
    }




}

*/
