package com.ls.wod.util;

import com.ls.wod.exception.GenericException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 *
 * @author francisco
 */

public class NullValidator {

    public static Object validateObjectProperties(Object pObjeto) {
        Object objeto = pObjeto;
        Method metodos[] = pObjeto.getClass().getMethods();
        for (int i = 0; i < metodos.length; i++) {
            Method metodo = metodos[i];
            //Si es un metodo get o is lo utilizo con su equivalente set
            if ((metodo.getName().substring(0, 3).equalsIgnoreCase("get") || metodo.getName().substring(0, 2).equalsIgnoreCase("is")) && !metodo.getName().equals("getClass")) {
                String methodNameSet = "";
                if (metodo.getName().substring(0, 3).equalsIgnoreCase("get")) {
                    methodNameSet = metodo.getName().replaceFirst("get", "set");
                } else {
                    methodNameSet = methodNameSet.replaceFirst("is", "set");
                }
                try {
                    Method metodoSet = pObjeto.getClass().getMethod(methodNameSet, metodo.getReturnType());
                    if (metodoSet != null) {
                        //Datos numericos
                        //Si es byte
                        if (metodo.getReturnType().equals(Byte.class)) {
                            Byte valor = (Byte) metodo.invoke(pObjeto, new Object[0]);
                            if (valor == null) {
                                metodoSet.invoke(pObjeto, 0);
                            }
                        }
                        //Si es bigDecimal
                        if (metodo.getReturnType().equals(BigDecimal.class)) {
                            BigDecimal valor = (BigDecimal) metodo.invoke(pObjeto, new Object[0]);
                            if (valor == null) {
                                metodoSet.invoke(pObjeto, new BigDecimal(0));
                            }
                        }
                        // Si es Double
                        if (metodo.getReturnType().equals(Double.class)) {
                            Double valor = (Double) metodo.invoke(pObjeto, new Object[0]);
                            if (valor == null) {
                                metodoSet.invoke(pObjeto, new Double(0));
                            }
                        }
                        //Si es un string
                        if (metodo.getReturnType().equals(String.class)) {
                            String valor = (String) metodo.invoke(pObjeto, new Object[0]);
                            if (valor == null) {
                                metodoSet.invoke(pObjeto, "");
                            }
                        }
                        //Si es una lista
                        if (metodo.getReturnType().equals(List.class)) {
                            List objetosList = (List) metodo.invoke(pObjeto, new Object[0]);
                            for (Object objetoFromList : objetosList) {
                                NullValidator.validateObjectProperties(objetoFromList);
                            }
                        }
                        //Si es date
                        if (metodo.getReturnType().equals(Date.class)) {
                            Date valor = (Date) metodo.invoke(pObjeto, new Object[0]);
                            if (valor == null) {
                                metodoSet.invoke(pObjeto, new Date());
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new GenericException(e.getMessage());
                }
            }
        }
        return objeto;
    }

}
