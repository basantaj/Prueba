<%@page import="modelo.Resultado" %>

<html>

<head>
<link rel="stylesheet" href="css/estilo.css">
</head>
<body>




<form action="Resultados" method="GET" >
<select name="selector">
	<option value=26>Argentina</option>
	<option value=7>Uruguay</option>
	<option value=25>Alemania</option>
	<option value=6>Brasil</option>
	<option value=10>Inglaterra</option>
	

</select>


<input type="submit" value="Confirmar">
</form>

<%

	Resultado res=(Resultado) request.getSession().getAttribute("resultados");
if(res!=null){

%>
<label><%=res.getLocal()%> <%=res.getPuntajeLocal() %> - <%=res.getPuntajeVisitante() %> <%=res.getVisitante() %></label> <br>
<label><%=res.getFecha() %></label> 
<%}%>


</body>
</html>
