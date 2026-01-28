package logica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		
		try {
			if(valor1 !=null && !valor1.isEmpty()) {
				int decimal = Integer.parseInt(valor1);
				String resultado = Integer.toBinaryString(decimal) ;
				response.getWriter().append("Decimal --> Binario ");
				response.getWriter().append("<h1> Resultado = </h1>"+resultado);
			}else if (valor2 != null && !valor2.isEmpty()) {
				int binario = Integer.parseInt(valor2,2);
				String resultado = Integer.toString(binario) ;
				response.getWriter().append("Binario --> Decimal ");
				response.getWriter().append("<h1> Resultado = </h1>"+resultado);
			} else {
				response.getWriter().append("Valores vacios");
			}
				
		} catch (Exception e) {
			 response.getWriter().append("Error: valor ingresado no es válido ingresa un numero");
		}
		
	}

}
