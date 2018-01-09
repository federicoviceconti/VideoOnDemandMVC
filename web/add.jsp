<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.facade.FacadeFactory"%>
<%@page import="com.facade.FacadeFactory.FacadeType"%>
<%@page import="com.facade.FacadeFilm"%>
<%@page import="com.videoondemand.controller.Validate"%>
<%@page import="com.videoondemand.model.Genere" %>
<%@page import="com.common.Field" %>

<div class="content">
	<c:out value="${requestScope[Field.ALL_GENERI][0].nome}" default="hello" />

	<form method='post' action='AddServlet' enctype="multipart/form-data">
        <input type="hidden" name="type" value="<c:out value="${param[Field.TYPE]}" default=""/>">
        <input type="hidden" name="id" value="<c:out value="${param.id}" default=""/>">

		<div class="part">
			<h3 class="text">Nome film</h3>
            <input class="text" type='text' name='nome' value='<c:out value="${param.nome}" default=""/>'>
		</div>
		<div class="part">
			<h3 class="text">Anno film</h3>
            <input class="text" type='text' name='anno' value='<c:out value="${param.anno}" default=""/>'>
		</div>
		<div class="part">
			<h3 class="text">Genere</h3>		
			<select name="genere">
				<c:forEach var="item" items="${requestScope[Field.ALL_GENERI]}">
					<option
							value='${item.id}'
							<c:if test="${param[Field.PARAM_GENERE].equals(item.nome)}">
								<c:out value="selected='selected'" />
							</c:if>
						>${item.nome}
					</option>
				</c:forEach>
			</select>
		</div>
		<div class="part">
			Copertina
			<input name="file" type="file">
		</div>

		<c:if test="${requestScope[Field.KEY_ERROR] != null}">
            <ul>
                <c:forEach var="error" items="${requestScope[Field.KEY_ERROR]}">
                    <li>${error.getMessage()}</li>
                </c:forEach>
            </ul>
        </c:if>

		<div class="part">
			<input type="submit">
		</div>
	</form>
	
	<div class="footer-link">
		<a class="text" href="List.jsp">Ritorna alla lista dei prodotti</a>
	</div>
</div>