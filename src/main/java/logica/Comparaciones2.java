package logica;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Comparaciones")
public class Comparaciones extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Comparaciones() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String n1 = request.getParameter("numero1");
        String n2 = request.getParameter("numero2");
        String n3 = request.getParameter("numero3");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        if (!validarNumero(n1) || !validarNumero(n2) || !validarNumero(n3)) {
            out.println("Error: Ingrese solo números válidos.");
            return;
        }

        double numero1 = Double.parseDouble(n1);
        double numero2 = Double.parseDouble(n2);
        double numero3 = Double.parseDouble(n3);

        int option = Integer.parseInt(request.getParameter("option"));
        String resultado = "";
        String operacion = "";

        switch (option) {
            case 1:
                resultado = String.valueOf(obtenerMaximo(numero1, numero2, numero3));
                operacion = "Número Mayor";
                break;
            case 2:
                resultado = String.valueOf(obtenerMinimo(numero1, numero2, numero3));
                operacion = "Número Menor";
                break;
            case 3:
                resultado = obtenerRepetido(numero1, numero2, numero3);
                operacion = "Número que más se repite";
                break;
            default:
                out.println("Opción inválida.");
                return;
        }

        mostrarMensaje(out, numero1, numero2, numero3, resultado, operacion);
    }

    private double obtenerMaximo(double v1, double v2, double v3) {
        return Math.max(v1, Math.max(v2, v3));
    }

    private double obtenerMinimo(double v1, double v2, double v3) {
        return Math.min(v1, Math.min(v2, v3));
    }

    private String obtenerRepetido(double v1, double v2, double v3) {
        Map<Double, Integer> contador = new HashMap<>();
        contador.put(v1, contador.getOrDefault(v1, 0) + 1);
        contador.put(v2, contador.getOrDefault(v2, 0) + 1);
        contador.put(v3, contador.getOrDefault(v3, 0) + 1);

        double numeroMasRepetido = v1;
        int maxFrecuencia = 1;

        for (Map.Entry<Double, Integer> entry : contador.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                numeroMasRepetido = entry.getKey();
                maxFrecuencia = entry.getValue();
            }
        }

        if (maxFrecuencia == 1) {
            return "No hay números repetidos";
        } else if (maxFrecuencia == 3) {
            return "Todos los números son iguales";
        } else {
            return numeroMasRepetido + ": Se repite " + maxFrecuencia + " veces";
        }
    }

    private void mostrarMensaje(PrintWriter out, double n1, double n2, double n3, String respuesta, String operacion) {
        String persona = "Arlen Geovany Lopez Garcia";
        String cuenta = "202010040107";
        String persona = "Renán Arturo Rivera Orellana";
        String cuenta = "200820530009";
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
        """.formatted(persona, cuenta, operacion, n1, n2, n3, respuesta));
    }

    private boolean validarNumero(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}