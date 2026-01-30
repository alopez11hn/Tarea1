package logica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//usamos la libreria PrintWriter para escribir todo el HTML de respuesta

/**
 * Servlet implementation class ConversionDecimalBinario
 */
@WebServlet("/ConversionDecimalBinario")
public class ConversionDecimalBinario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConversionDecimalBinario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doPost(request,response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String valor1 =request.getParameter("decimal");
		String valor2 =request.getParameter("binario");
		String persona = "Arlen Geovany Lopez Garcia";
		String cuenta = "202010040107";
		try {
			
			if(valor1 !=null && !valor1.isEmpty()) {
				int decimal = Integer.parseInt(valor1);
				String resultado = Integer.toBinaryString(decimal) ;
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
				<h3>Cuenta: %s</h3>

				<p><strong>Operación Realizada:</strong> Coversion de Decimal a Binario</p>

				<table border="3" cellpadding="5">
				    <tr>
				        <th>Entrada</th>
				        <th>Respuesta</th>
				    </tr>
				    <tr>
				        <td>%s</td>
				        <td>%s</td>
				    </tr>
				</table>
					<br>
					<div>
						<a href="conversiones.html"><button type="button">Regresar a inicio</button></a>
					</div>
				</body>
				</html>
				""".formatted(persona,cuenta,valor1,resultado));
				
			}else if (valor2 != null && !valor2.isEmpty()) {
				int binario = Integer.parseInt(valor2,2);
				String resultado = Integer.toString(binario) ;
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
				<h3>Cuenta: %s</h3>

				<p><strong>Operación Realizada:</strong> Coversion de Binario a decimal</p>

				<table border="3" cellpadding="5">
				    <tr>
				        <th>Entrada</th>
				        <th>Respuesta</th>
				    </tr>
				    <tr>
				        <td>%s</td>
				        <td>%s</td>
				    </tr>
				</table>
					<br>
					<div>
						<a href="conversiones.html"><button type="button">Regresar a inicio</button></a>
					</div>
				</body>
				</html>
				""".formatted(persona,cuenta,valor2,resultado));
			} else {
				PrintWriter out = response.getWriter();
				out.println("""
				<!DOCTYPE html>
				<html lang="es">
				<head>
				    <meta charset="UTF-8">
				    <title>Error</title>
				</head>
				<body>

				<h2>ERROR: %s</h2> 
					<div>
						<a href="conversiones.html"><button type="button">Regresar a inicio</button></a>
					</div>
				</body>
				</html>
				""".formatted("Campos Vacios"));
			}
				
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			out.println("""
			<!DOCTYPE html>
			<html lang="es">
			<head>
			    <meta charset="UTF-8">
			    <title>Error</title>
			</head>
			<body>

			<h2>ERROR: %s</h2> 
				<div>
					<a href="conversiones.html"><button type="button">Regresar a inicio</button></a>
				</div>
			</body>
			</html>
			""".formatted("Numeros Invalidos"));
		}
			
		}
}
