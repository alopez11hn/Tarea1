package logica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Servlet implementation class ComparacionMayorQue
 */
@WebServlet("/Comparaciones")
public class Comparaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comparaciones() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String n1 = request.getParameter("numero1");
		String n2 = request.getParameter("numero2");
		String n3 = request.getParameter("numero3");
		
		double numero1 = Double.parseDouble(n1);
        double numero2 = Double.parseDouble(n2);
        double numero3 = Double.parseDouble(n3);
        
        String resultado, p;
        
        	
        	if(validartxt(n1,n2,n3)==true) {
        		resultado ="numeros";
            	response.getWriter().append(resultado);
            /*int option = Integer.parseInt(request.getParameter("option"));
        	switch (option) {
			case 1: //resultado = String.valueOf(NumeroMaximo(numero1,numero2,numero3));
					p = "Numero Mayor";
					response.getWriter().append(p);
					//mensaje(request,response ,numero1 ,numero2, numero3, resultado, p);
				break;
			case 2: resultado = String.valueOf(NumeroMinimo(numero1,numero2,numero3));
					p = "Numero Menor";
					response.getWriter().append(p);
					//mensaje(request,response ,numero1 ,numero2, numero3, resultado, p);
				break;
			case 3: resultado = //String.valueOf(NumeroRepetido(numero1,numero2,numero3));
					p = "Numero que mas se repite";
					response.getWriter().append(p);
					//mensaje(request,response ,numero1 ,numero2, numero3, resultado, p);
				break;
			default:
				break;
        	}*/
	        } else{
	        	resultado ="letras";
	        	response.getWriter().append(resultado);
	        }
        
		
	}
	public double NumeroMaximo(double valor1, double valor2, double valor3) {
		double maximo = Math.max(valor1, Math.max(valor2,valor3));
		return maximo;
	}
	
	public double NumeroMinimo(double valor1, double valor2, double valor3) {
		double minimo = Math.min(valor1, Math.min(valor2,valor3));
		return minimo;
	}
	
	public String NumeroRepetido(double valor1, double valor2, double valor3) {
		if (valor1 == valor2 && valor2 == valor3) {
            return "Todos los números son iguales";
        } else if (valor1 == valor2 || valor1 == valor3) {
            return String.valueOf(valor1 + ": Se repite 2 veces");
        } else if ( valor2 == valor3) {
            return String.valueOf(valor2+": Se repite 2 Veces");
        } else {
            return "No hay números que se repitan";
        }
	}
	
	public void mensaje(HttpServletRequest request, HttpServletResponse response ,double numero1, double numero2, double numero3, String respuesta, String p) throws ServletException, IOException {
		String persona = "Arlen Geovany Lopez Garcia";
		String cuenta = "202010040107";
		String valores[]= {String.valueOf(numero3),String.valueOf(numero2),String.valueOf(numero3)};
		PrintWriter out = response.getWriter();
		out.println("""
		<!DOCTYPE html>
		<html lang="es">
		<head>
		    <meta charset="UTF-8">
		    <title>Servlet Tarea 1</title>
		</head>
		<body>

		<h2>Servlet Tarea 1: %s</h2>
		<h2>Cuenta: %s</h2>

		<p><strong>Operación Realizada:</strong> %s</p>

		<table border="3" cellpadding="5">
		    <tr>
		        <th>Entrada</th>
		        <th>Respuesta</th>
		    </tr>
		    <tr>
		        <td>%s , %s , %s </td>
		        <td>%s</td>
		    </tr>
		</table>
			<br>
			<div>
				<a href="conversiones.html"><button type="button">Regresar a inicio</button></a>
			</div>
		</body>
		</html>
		""".formatted(persona,cuenta,p,valores[0],valores[1],valores[2],respuesta));
	}
	
	public Boolean validartxt (String numero1, String numero2, String numero3) {
			if (numero1 == null || numero1.trim().isEmpty() || numero2 == null || numero2.trim().isEmpty() || numero2 == null || numero2.trim().isEmpty()) {
				return false;
			}
			try {
				Double.parseDouble(numero1);
				try {
					Double.parseDouble(numero2);
					try {
						Double.parseDouble(numero3);
						return true;
					} catch (Exception e) {
						return false;
					}
				} catch (Exception e) {
					return false;
				}
			} catch (Exception e) {
				return false;
			}
		  
		}
	
}
