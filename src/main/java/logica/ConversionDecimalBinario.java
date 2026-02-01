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
			//Decimal a Binario
			if( validarDecimal(valor1) == 1) {
					//Es un entero o double.
					double numero = Double.parseDouble(valor1);
					int Entero = (int) Math.floor(numero);
					double Resultado = numero - Entero;
					String respuesta = "";

					StringBuilder binarioFraccion = new StringBuilder();
					int precision = 10;
				if(Entero > 0) {
					if (Resultado > 0.0) {
					    while (Resultado > 0 && binarioFraccion.length() < precision) {
					        Resultado *= 2;
					        if (Resultado >= 1) {
					            binarioFraccion.append("1");
					            Resultado -= 1;
					        } else {
					            binarioFraccion.append("0");
					        }
					    }
					    
					    respuesta = Integer.toBinaryString(Entero)+"."+binarioFraccion.toString(); 
					}else {
						respuesta = Integer.toBinaryString(Entero) ;
					}
				
					//agregar la funcion mejor para evitar lios aqui es lo mismo
					
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
					""".formatted(persona,cuenta,valor1,respuesta));
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
					""".formatted("Numero Negativos o Cero no Admitidos"));
				}
			} else if (validarDecimal(valor1) == 2) {
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
				""".formatted("Texto No Admitido"));
			}
			//Binario a Decimal
			if (validarBinario(valor2)==1) {
				String resultado ="";
				//validamos si tiene punto el numero que entra del HTML
				boolean tienePunto = valor2.contains(".");
				if(!tienePunto) {
					int binario = Integer.parseInt(valor2,2);
					resultado = String.valueOf(binario);
					
				}else {
					//dividimos la parte entera de la decimal
					String[] partes = valor2.split("\\.");
				    int entero = Integer.parseInt(partes[0], 2);
				    double respuesta = 0.0;
				    for (int i = 0; i < partes[1].length(); i++) {
				        if (partes[1].charAt(i) == '1') {
				            respuesta += 1.0 / Math.pow(2, i + 1);
				        }
				    }
				    double decimalFinal = entero + respuesta;
				    resultado = String.valueOf(decimalFinal); 

				}
				
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
				""".formatted("Texto o Negativos no permitidos"));
			}
				
		} catch (NumberFormatException e) {
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


	//validar si es texto o binario: BinarioEntero o BinarioDecimal
	public int validarBinario(String numero) {
		//"-?[01]+(\\.[01]+)?" numero negativos y positivos
		if (numero.matches("[01]+(\\.[01]+)?")) {
			// es binario
			return 1;
		} else {
			// no es binario
			return 2;
		} 
		
	}
	
	//validar si es texto o numero
	public int validarDecimal(String numero) {
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
