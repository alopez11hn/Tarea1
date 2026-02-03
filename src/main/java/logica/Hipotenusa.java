package logica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Hipotenusa
 */
@WebServlet("/Hipotenusa")
public class Hipotenusa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hipotenusa() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Redirigir al método doPost
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cateto1Str = request.getParameter("cateto1");
		String cateto2Str = request.getParameter("cateto2");
		String persona = "JUAN RAFAEL BATRES PADILLA";
		String cuenta = "202310110503";
		
		try {
			// Validar que ambos valores sean números válidos
			if (validarNumero(cateto1Str) == 1 && validarNumero(cateto2Str) == 1) {
				double cateto1 = Double.parseDouble(cateto1Str);
				double cateto2 = Double.parseDouble(cateto2Str);
				
				// Validar que los catetos sean positivos
				if (cateto1 > 0 && cateto2 > 0) {
					// Calcular la hipotenusa usando el teorema de Pitágoras: c = √(a² + b²)
					double hipotenusa = Math.sqrt(Math.pow(cateto1, 2) + Math.pow(cateto2, 2));
					
					// Redondear a 4 decimales para mejor presentación
					hipotenusa = Math.round(hipotenusa * 10000.0) / 10000.0;
					
					PrintWriter out = response.getWriter();
					out.println("""
					<!DOCTYPE html>
					<html lang="es">
					<head>
					    <meta charset="UTF-8">
					    <title>Servlet Tarea 1</title>
					    <style>
					        body {
					            font-family: 'Arial', sans-serif;
					            background-color: #f0f0f0;
					            color: #333;
					            padding: 20px;
					        }
					        h2 {
					            color: #42641b;
					        }
					        table {
					            margin: 20px auto;
					            background-color: white;
					            border-collapse: collapse;
					        }
					        th {
					            background-color: #42641b;
					            color: white;
					            padding: 10px;
					        }
					        td {
					            padding: 10px;
					        }
					        button {
					            background-color: #42641b;
					            color: white;
					            padding: 10px 20px;
					            border: none;
					            border-radius: 5px;
					            cursor: pointer;
					            font-size: 16px;
					            font-weight: bold;
					        }
					        button:hover {
					            background-color: #ffd700;
					            color: #000;
					        }
					        .formula {
					            text-align: center;
					            font-style: italic;
					            color: #555;
					            margin: 15px 0;
					        }
					    </style>
					</head>
					<body>

					<h2>Servlet Tarea 1: %s</h2>
					<h2>Cuenta: %s</h2>

					<p><strong>Operación Realizada:</strong> Cálculo de Hipotenusa (Teorema de Pitágoras)</p>
					<p class="formula">h = √(cateto1² + cateto2²)</p>

					<table border="3" cellpadding="5">
					    <tr>
					        <th>Cateto 1</th>
					        <th>Cateto 2</th>
					        <th>Hipotenusa</th>
					    </tr>
					    <tr>
					        <td>%s</td>
					        <td>%s</td>
					        <td>%s</td>
					    </tr>
					</table>
						<br>
						<div style="text-align: center;">
							<a href="hipotenusa.html"><button type="button">Regresar a inicio</button></a>
						</div>
					</body>
					</html>
					""".formatted(persona, cuenta, cateto1Str, cateto2Str, hipotenusa));
					
				} else {
					// Error: números negativos o cero
					mostrarError(response, "Los catetos deben ser números positivos mayores que cero");
				}
				
			} else {
				// Error: no son números válidos
				mostrarError(response, "Por favor ingrese valores numéricos válidos");
			}
			
		} catch (NumberFormatException e) {
			mostrarError(response, "Error: Valores numéricos inválidos");
		} catch (Exception e) {
			mostrarError(response, "Error al procesar la solicitud");
		}
	}
	
	/**
	 * Método para mostrar mensajes de error
	 */
	private void mostrarError(HttpServletResponse response, String mensaje) throws IOException {
		PrintWriter out = response.getWriter();
		out.println("""
		<!DOCTYPE html>
		<html lang="es">
		<head>
		    <meta charset="UTF-8">
		    <title>Error</title>
		    <style>
		        body {
		            font-family: 'Arial', sans-serif;
		            background-color: #f0f0f0;
		            color: #333;
		            padding: 20px;
		            text-align: center;
		        }
		        h2 {
		            color: #d32f2f;
		        }
		        button {
		            background-color: #42641b;
		            color: white;
		            padding: 10px 20px;
		            border: none;
		            border-radius: 5px;
		            cursor: pointer;
		            font-size: 16px;
		            font-weight: bold;
		            margin-top: 20px;
		        }
		        button:hover {
		            background-color: #ffd700;
		            color: #000;
		        }
		    </style>
		</head>
		<body>

		<h2>ERROR: %s</h2> 
			<div>
				<a href="hipotenusa.html"><button type="button">Regresar a inicio</button></a>
			</div>
		</body>
		</html>
		""".formatted(mensaje));
	}
	
	/**
	 * Valida si una cadena es un número válido
	 * @param numero - Cadena a validar
	 * @return 1 si es número válido, 2 si no lo es, 3 si está vacío
	 */
	public int validarNumero(String numero) {
		if (numero == null || numero.trim().isEmpty()) {
			return 3;
		}
		try {
			Double.parseDouble(numero);
			return 1;
		} catch (NumberFormatException e) {
			return 2;
		}
	}
}