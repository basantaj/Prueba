package com.Prueba.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import modelo.Resultado;

public class Resultados extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Resultados() {
		super();

	}

	private String extraerFecha(String fecha) {

		String formateada = LocalDate
				.parse(fecha.substring(0, fecha.indexOf("T")), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
				.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
		String hora = fecha.substring(fecha.indexOf("T") + 1, fecha.indexOf("+"));

		return formateada + " a las " + hora;

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("selector");

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
				// .uri(URI.create("https://v3.football.api-sports.io/status"))
				.uri(URI.create("https://v3.football.api-sports.io/fixtures?last=1&team=" + opcion))
				.header("x-apisports-key", "afa9e9e9eb09e2d8094b4da899670047")

				.build();

		String local, visitante, fecha;
		int puntajeLocal, puntajeVisitante;

		HttpResponse<String> resp;
		try {

			resp = client.send(req, BodyHandlers.ofString());
			System.out.println(resp.statusCode());
			String cuerpoJson = resp.body();

			JSONObject objeto = new JSONObject(cuerpoJson).getJSONArray("response").getJSONObject(0);

			fecha = objeto.getJSONObject("fixture").get("date").toString();
			JSONObject equipos = objeto.getJSONObject("teams");
			local = equipos.getJSONObject("home").get("name").toString();
			visitante = equipos.getJSONObject("away").get("name").toString();
			JSONObject subobjetoPuntajes = objeto.getJSONObject("goals");
			puntajeLocal = (int) subobjetoPuntajes.get("home");
			puntajeVisitante = (int) subobjetoPuntajes.get("away");

			Resultado res = new Resultado(local, visitante, extraerFecha(fecha), puntajeLocal, puntajeVisitante);

			HttpSession sesion = request.getSession();

			sesion.setAttribute("resultados", res);
			response.sendRedirect("index.jsp");

			PrintWriter out = response.getWriter(); // out.println(cuerpoJson);
			out.println(fecha + local + visitante + puntajeLocal + puntajeVisitante);

		} catch (IOException | InterruptedException e) {

			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
