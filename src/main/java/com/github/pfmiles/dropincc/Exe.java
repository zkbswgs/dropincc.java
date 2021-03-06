/*******************************************************************************
 * Copyright (c) 2012 pf_miles.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     pf_miles - initial API and implementation
 ******************************************************************************/
package com.github.pfmiles.dropincc;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import com.github.pfmiles.dropincc.impl.AnalyzedLang;
import com.github.pfmiles.dropincc.impl.runtime.Token;
import com.github.pfmiles.dropincc.impl.runtime.impl.Lexer;
import com.github.pfmiles.dropincc.impl.runtime.impl.Parser;

/**
 * The compiled representation of the constructing language. It's the main
 * interface for later code execution or evaluation. It is thread-safe, so we'd
 * better cache it, and reuse it again and again in the future.
 * 
 * @author pf-miles
 * 
 */
@SuppressWarnings("unchecked")
public class Exe {

    private AnalyzedLang al;

    public Exe(AnalyzedLang al) {
        this.al = al;
    }

    /**
     * Execute the new language's code
     * 
     * @param code
     *            the evaluating code
     * @param arg
     *            the argument passed to this evaluation
     * @return the execution return value of the inputed code, if any
     */
    public <T> T eval(String code, Object arg) {
        Lexer lexer = al.newLexer(code);
        Parser p = al.newParser(lexer);
        return (T) p.parse(arg);
    }

    /**
     * Execute the new language's code with no-args
     * 
     * @param code
     *            the evaluating code
     * @return the execution return value of the inputed code, if any
     */
    public <T> T eval(String code) {
        return (T) this.eval(code, null);
    }

    /**
     * Just do lexing. It's useful when you just want to tokenize the input. And
     * it's also helpful when debuging your language.
     * 
     * @param code
     * @return
     */
    public List<Token> lexing(String code) {
        Enumeration<Token> lexer = al.newLexer(code);
        List<Token> ret = new ArrayList<Token>();
        while (lexer.hasMoreElements())
            ret.add(lexer.nextElement());
        return ret;
    }
}
